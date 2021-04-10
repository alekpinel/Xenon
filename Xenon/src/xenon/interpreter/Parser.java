package xenon.interpreter;

import java.util.Vector;

import xenon.parser.ast.*;
import xenon.system.SystemInitializer;
import xenon.values.*;

public class Parser implements XenonVisitor {
	
	private static Parser instance;
	public static Parser getInstance() {
		return instance;
	}
	
	public class VariableUse {
		boolean write = false;
		boolean local = false;
		
		public VariableUse(boolean write, boolean local) {
			this.write = write;
			this.local = local;
		}
	}
	
	public class ParameterType{
		boolean system = false;
		
		public ParameterType(boolean issystem) {
			system = issystem;
		}
	}
	
	public Parser(){
		instance = this;
	}
	
	// Scope display handler
	private Display display = new Display();
	
	public Display getDisplay() {
		return display;
	}
	
	public static int getChildrenCount(SimpleNode node) {
		return node.jjtGetNumChildren();
	}
	
	// Get the ith child of a given node.
	public static SimpleNode getChild(SimpleNode node, int childIndex) {
		return (SimpleNode)node.jjtGetChild(childIndex);
	}
	
	// Get the token value of the ith child of a given node.
	public static String getTokenOfChild(SimpleNode node, int childIndex) {
		return getChild(node, childIndex).tokenValue;
	}
	
	public Object executeNode(SimpleNode node, Object data) {
		return node.jjtAccept(this, data);
	}
	
	// Execute a given child of the given node
	private Object doChild(SimpleNode node, int childIndex, Object data) {
		return node.jjtGetChild(childIndex).jjtAccept(this, data);
	}
	
	// Execute a given child of a given node, and return its value as a Value.
	// This is used by the expression evaluation nodes.
	Reference doChild(SimpleNode node, int childIndex) {
		return (Reference)doChild(node, childIndex, null);
	}
	
	// Execute all children of the given node
	Object doChildren(SimpleNode node, Object data) {
		
		int nchildren = getChildrenCount(node);
		Object result = null;
		
        for (int i = 0; i < nchildren && !display.IsReturning(); ++i) {
        	result = node.jjtGetChild(i).jjtAccept(this, data);
        }
        
	    return result;
		//return node.childrenAccept(this, data);
	}
	
	VariableUse getVariableContext(Object data) {
		if (data != null && data instanceof VariableUse) {
			return (VariableUse) data;
		}
		return new VariableUse(false, false);
	}
	
	Reference getVarOfChild(SimpleNode node, int childnumber, boolean write, boolean local) {
		return (Reference) doChild(node, childnumber, (Object) new VariableUse(write, local));
	}
	
	// Called if one of the following methods is missing...
	public Object visit(SimpleNode node, Object data) {
		System.out.println(node + ": acceptor not implemented in subclass?");
		return data;
	}
	
	// Execute a Xenon program
	public Object visit(ASTCode node, Object data) {
		doChildren(node, data);
		Function main = display.findFunction(Function.MAIN, 0);
		if (main != null) {
			return display.InvokeFunction(main, (SimpleNode) null, this);
		}
		return null;
	}
	
	// Execute a statement
	public Object visit(ASTStatement node, Object data) {
		return doChildren(node, data);	
	}

	// Execute a block
	public Object visit(ASTBlock node, Object data) {
		return doChildren(node, data);	
	}

	// Function definition parameter list
	public Object visit(ASTParmlist node, Object data) {
		
		Function currentDefinition = (Function)data;
		for (int i=0; i<node.jjtGetNumChildren(); i++)
			currentDefinition.defineParameter(getTokenOfChild(node, i));
		return data;
	}
	
	// Function body
	public Object visit(ASTFnBody node, Object data) {
		return doChildren(node, data);
	}
	
	// Function return expression
	public Object visit(ASTReturnExpression node, Object data) {
		Reference expr = (Reference) doChildren(node, data);
		display.ReturnFunction(expr);
		return expr;
	}
	
	// Function invocation argument list.
	public Object visit(ASTArgList node, Object data) {
		boolean issystem = data != null && data instanceof ParameterType && ((ParameterType) data).system;
		//Parameters must be returned as a list
		if (issystem) {
			Vector<Reference> paramlist = new Vector<Reference>(node.jjtGetNumChildren());
			for (int i=0; i<node.jjtGetNumChildren(); i++) {
				paramlist.add(((Reference) display.executeNodeinFunction(getChild(node, i), this, data)).refclone());
				//paramlist.add(doChild(node, i).refclone());
			}
				
			return paramlist;
		}
		//Parameters must be executed
		else {
			Function newInvocation = (Function)data;
		
			if (newInvocation != null) {
				for (int i=0; i<node.jjtGetNumChildren(); i++)
					newInvocation.setArgument(doChild(node, i).refclone());
				newInvocation.checkArgumentCount();
			}
			
			return data;
		}
	}
	
	// Execute an IF 
	public Object visit(ASTIfStatement node, Object data) {
		Object result = null;
		// evaluate boolean expression
		Value hopefullyValueBoolean = doChild(node, 0).getValue();
		if (!(hopefullyValueBoolean instanceof ValueBoolean))
			throw new ExceptionSemantic("The test expression of an if statement must be boolean.");
		if (((ValueBoolean)hopefullyValueBoolean).booleanValue())
			result = doChild(node, 1);							// if(true), therefore do 'if' statement
		else if (node.ifHasElse)						// does it have an else statement?
			result = doChild(node, 2);							// if(false), therefore do 'else' statement
		return result;
	}
	
	//WHILE loop
	public Object visit(ASTWhileLoop node, Object data) {
		Object result = null;
		while (true) {
			// evaluate loop test
			Value hopefullyValueBoolean = doChild(node, 0).getValue();
			if (!(hopefullyValueBoolean instanceof ValueBoolean))
				throw new ExceptionSemantic("The test expression of a while loop must be boolean.");
			if (!((ValueBoolean)hopefullyValueBoolean).booleanValue())
				break;
			// do loop statement
			result = doChild(node, 1);
			
			if (display.IsReturning()) {
				return result;
			}
		}
		return data;
	}
	
	// Execute a FOR loop
	public Object visit(ASTForLoop node, Object data) {
		Object result = null;
		// loop initialisation
		getVarOfChild(node, 0, true, false);
		//expressionDefinition(node, 0);
		while (true) {
			// evaluate loop test
			Value hopefullyValueBoolean = doChild(node, 1).getValue();
			if (!(hopefullyValueBoolean instanceof ValueBoolean))
				throw new ExceptionSemantic("The test expression of a for loop must be boolean.");
			if (!((ValueBoolean)hopefullyValueBoolean).booleanValue())
				break;
			// do loop statement
			result = doChild(node, 3);
			if (display.IsReturning()) {
				return result;
			}
			// assign loop increment
			doChild(node, 2);
		}
		return result;
	}
	
	//A FOREACH loop
	public Object visit(ASTForEachLoop node, Object data) {
		Object result = null;
		
		// loop initialization
		Reference e = getVarOfChild(node, 0, true, false);
		Reference i;
		Reference list;
		SimpleNode body;
		if (getChildrenCount(node) == 3) {
			i = new Reference(new ValueInteger(0));
			list = doChild(node, 1);
			body = getChild(node, 2);
		}
		else {
			i = getVarOfChild(node, 1, true, false);
			i.SetValue(new ValueInteger(0));
			list = doChild(node, 2);
			body = getChild(node, 3);
		}
		
		//e is the element of the loop and i is the index
		
		while (true) {
			Value size = display.InvokeMethod(Function.SIZE, list.getValue().classValue(), this).getValue();
			// evaluate loop test
			Value hopefullyValueBoolean = i.getValue().lt(size);
			if (!(hopefullyValueBoolean instanceof ValueBoolean))
				throw new ExceptionSemantic("The test expression of a for loop must be boolean.");
			if (!((ValueBoolean)hopefullyValueBoolean).booleanValue())
				break;
			
			//Set the element
			Vector<Reference> paramlist = new Vector<Reference>();
			paramlist.add(i);
			e.SetValue(display.InvokeMethod(Function.ITERATE, paramlist, list.getValue().classValue(), this).getValue());
			
			// do loop statement
			result = executeNode(body, data);
			
			if (display.IsReturning()) {
				return result;
			}
			
			// assign loop increment
			i.SetValue(i.getValue().add(new ValueInteger(1)));
		}
		return data;
	}
	
	// Process an identifier
	// This doesn't do anything, but needs to be here because we need an ASTIdentifier node.
	public Object visit(ASTIdentifier node, Object data) {
		return data;
	}
	
	////////////////////////////// Assignement ///////////////////////////////
	
	// Execute an assignment statement.
	public Object visit(ASTAssignment node, Object data) {
		Reference right = doChild(node, 1);
		Reference left = getVarOfChild(node, 0, true, false);
		left.SetValue(right.getValue());
		return left;
	}
	
	// Execute an assignment increment statement.
	public Object visit(ASTAssignIncrement node, Object data) {
		Reference right = doChild(node, 1);
		Reference left = getVarOfChild(node, 0, true, false);
		left.SetValue(left.getValue().add(right.getValue()));
		return left;
	}

	// Execute an assignment decrement statement.
	public Object visit(ASTAssignDecrement node, Object data) {
		Reference right = doChild(node, 1);
		Reference left = getVarOfChild(node, 0, true, false);
		left.SetValue(left.getValue().subtract(right.getValue()));
		return left;
	}

	// OR
	public Object visit(ASTOr node, Object data) {
		Reference left = doChild(node, 0);
		
		if (left.getValue().booleanValue() == true) {
			return new Reference(new ValueBoolean(true));
		}
		
		return new Reference(left.getValue().or(doChild(node, 1).getValue()));
	}

	// AND
	public Object visit(ASTAnd node, Object data) {
		Reference left = doChild(node, 0);
		
		if (left.getValue().booleanValue() == false) {
			return new Reference(new ValueBoolean(false));
		}
		
		return new Reference(left.getValue().and(doChild(node, 1).getValue()));
	}

	// ==
	public Object visit(ASTCompEqual node, Object data) {
		return new Reference(doChild(node, 0).getValue().eq(doChild(node, 1).getValue()));
	}

	// !=
	public Object visit(ASTCompNequal node, Object data) {
		return new Reference(doChild(node, 0).getValue().neq(doChild(node, 1).getValue()));
	}

	// >=
	public Object visit(ASTCompGTE node, Object data) {
		return new Reference(doChild(node, 0).getValue().gte(doChild(node, 1).getValue()));
	}

	// <=
	public Object visit(ASTCompLTE node, Object data) {
		return new Reference(doChild(node, 0).getValue().lte(doChild(node, 1).getValue()));
	}

	// >
	public Object visit(ASTCompGT node, Object data) {
		return new Reference(doChild(node, 0).getValue().gt(doChild(node, 1).getValue()));
	}

	// <
	public Object visit(ASTCompLT node, Object data) {
		return new Reference(doChild(node, 0).getValue().lt(doChild(node, 1).getValue()));
	}

	// +
	public Object visit(ASTAdd node, Object data) {
		return new Reference(doChild(node, 0).getValue().add(doChild(node, 1).getValue()));
	}

	// -
	public Object visit(ASTSubtract node, Object data) {
		return new Reference(doChild(node, 0).getValue().subtract(doChild(node, 1).getValue()));
	}

	// *
	public Object visit(ASTTimes node, Object data) {
		return new Reference(doChild(node, 0).getValue().mult(doChild(node, 1).getValue()));
	}

	// /
	public Object visit(ASTDivide node, Object data) {
		return new Reference(doChild(node, 0).getValue().div(doChild(node, 1).getValue()));
	}
	
	//%
	public Object visit(ASTModule node, Object data) {
		return new Reference(doChild(node, 0).getValue().mod(doChild(node, 1).getValue()));
	}

	// NOT
	public Object visit(ASTUnaryNot node, Object data) {
		return new Reference(doChild(node, 0).getValue().not());
	}

	// + (unary)
	public Object visit(ASTUnaryPlus node, Object data) {
		return new Reference(doChild(node, 0).getValue().unary_plus());
	}

	// - (unary)
	public Object visit(ASTUnaryMinus node, Object data) {
		return new Reference(doChild(node, 0).getValue().unary_minus());
	}
	
	//++ (unary)
	public Object visit(ASTUnaryIncrement node, Object data) {
		Reference left = getVarOfChild(node, 0, false, false);
		Reference before = new Reference(left.getValue());
		left.SetValue(left.getValue().add(new ValueInteger(1)));
		
		return before;
	}
	
	//-- (unary)
	public Object visit(ASTUnaryDecrement node, Object data) {
		Reference left = getVarOfChild(node, 0, false, false);
		Reference before = new Reference(left.getValue());
		left.SetValue(left.getValue().add(new ValueInteger(-1)));
		
		return before;
	}

	// Return string literal
	public Object visit(ASTCharacter node, Object data) {
		if (node.optimised == null)
			node.optimised = new Reference(ValueString.stripDelimited(node.tokenValue));
		return node.optimised;
	}

	// Return integer literal
	public Object visit(ASTInteger node, Object data) {
		if (node.optimised == null)
			node.optimised = new Reference(new ValueInteger(Long.parseLong(node.tokenValue)));
		return node.optimised;
	}

	// Return floating point literal
	public Object visit(ASTRational node, Object data) {
		if (node.optimised == null)
			node.optimised = new Reference(new ValueRational(Double.parseDouble(node.tokenValue)));
		return node.optimised;
	}

	// Return true literal
	public Object visit(ASTTrue node, Object data) {
		if (node.optimised == null)
			node.optimised = new Reference(new ValueBoolean(true));
		return node.optimised;
	}

	// Return false literal
	public Object visit(ASTFalse node, Object data) {
		if (node.optimised == null)
			node.optimised = new Reference(new ValueBoolean(false));
		return node.optimised;
	}
	
	//This word
	public Object visit(ASTThis node, Object data) {
		return new Reference(new ValueClass(display.getThis()));
	}

	//Global word
	public Object visit(ASTGlobal node, Object data) {
		return new Reference(new ValueClass(display.getGlobal()));
	}
	
	//Null literal
	public Object visit(ASTNullLiteral node, Object data) {
		return new Reference(new ValueNull());
	}
	
	//Vector literal
	public Object visit(ASTVectorLiteral node, Object data) {
		return SystemInitializer.CreateNewVector((Vector<Reference>) doChild(node, 0, new ParameterType(true)));
	}
	
	//ParentList
	public Object visit(ASTReferenceList node, Object data) {
		Vector<Reference> list = new Vector<Reference>();
		
		for (int i=0; i<node.jjtGetNumChildren(); i++) {
			Reference arg = doChild(node, i);
			
			list.add(arg);
		}
			
		return list;
	}

	//Class definitions
	public Object visit(ASTClassDef node, Object data) {
		// Child 0 - identifier (class name)
		String classname = getTokenOfChild(node, 0);
		SimpleNode body;
		Vector<Class> parents = new Vector<Class>();
		//Without inheritance
		if (getChildrenCount(node) == 2) {
			body = getChild(node, 1);
		}
		//Inherit
		else {
			Vector<Reference> references = (Vector<Reference>) doChild(node, 1, data);
			
			for (int i = 0; i < references.size(); i++) {
				Class c = references.get(i).getValue().classValue();
				
				if (!c.IsDefinition())
					throw new ExceptionSemantic("" + c.getName() + " is given as a parent, but it is an object. A parent must be a definition.");
				
				parents.add(c);
			}
			
			body = getChild(node, 2);
		}
		
		display.defineNewClass(classname, parents, body, this);
		return data;
	}
	
	////////////////////////// Functions //////////////////////////
	
	// Function definition
	public Object visit(ASTFnDef node, Object data) {
		
		// Child 0 - identifier (fn name)
		String fnname = getTokenOfChild(node, 0);
		
		// Child 1 - function definition parameter list
		SimpleNode param = getChild(node, 1);
		int nparams = getChildrenCount(param);
	
		// Child 2 - function body
		SimpleNode body = getChild(node, 2);
		
		 Vector<String> paramlist = new Vector<String>(nparams);
		 
		 for (int i = 0; i < nparams; i++) {
			 paramlist.add(getTokenOfChild(param, i));
		 }
		
		Function currentFunctionDefinition = display.defineNewFunction(fnname, body, paramlist);
		
		return data;
	}
	
	//Lambda function definition
	public Object visit(ASTLambda node, Object data) {
		// Already defined?
		if (node.optimised != null)
			return new Reference(new ValueFunction(((Function) node.optimised).Instantiate()));
		
		Scope parent = display.getScope();
		SimpleNode param, body;
		if (getChildrenCount(node) == 2) {
			param = getChild(node, 0);
			body = getChild(node, 1);
		}
		else {
			parent = doChild(node, 0).getValue().scopeValue();
			param = getChild(node, 1);
			body = getChild(node, 2);
		}
		
		int nparams = getChildrenCount(param);
		 Vector<String> paramlist = new Vector<String>(nparams);
		 for (int i = 0; i < nparams; i++) {
			 paramlist.add(getTokenOfChild(param, i));
		 }
		
		Function currentFunctionDefinition = new Function(Function.LAMBDA, parent, body, paramlist);
		
		// Preserve this definition for future reference, and so we don't define
		// it every time this node is processed.
		node.optimised = currentFunctionDefinition;
		
		return new Reference(new ValueFunction(currentFunctionDefinition));
	}
	
	// Function invocation
	public Object visit(ASTFnInvoke node, Object data) {
		Function fndef;
		SimpleNode parameters = getChild(node, 1);
		// Child 0 - identifier (fn name)
		String fnname = getTokenOfChild(node, 0);
		
		//Child 1 - Parameters
		int nparam = getChildrenCount(parameters);
		
		fndef = display.findFunction(fnname, nparam);
		if (fndef == null)
			throw new ExceptionSemantic("Function " + fnname + " is undefined.");
		
		return display.InvokeFunction(fndef, parameters, this);
	}
	
	//Invocation of the method brackets[]
	public Object visit(ASTSqrBrackets node, Object data) {
		return doChild(node, 0).getValue().bracket((Vector<Reference>) doChild(node, 1, new ParameterType(true)));
	}

	//New objects
	public Object visit(ASTNewObject node, Object data) {
		
		//Token 0 is the name
		Class classt = doChild(node, 0).getValue().classValue();
		
		int argumentcount = 0;
		SimpleNode params = null;
		
		//Child 1 (if exists) are the arguments for the contructor
		if (getChildrenCount(node) == 2) {
			params = getChild(node, 1);
			argumentcount = getChildrenCount(params);
		}
		
		return display.newObject(classt, argumentcount, params, this);
	}

	//References to variables
	public Object visit(ASTVariable node, Object data) {
		String name = node.tokenValue;
		
		VariableUse use = getVariableContext(data);
		
		return display.getVariable(name, use.write, use.local);	
	}
	
	//Reference a new variable
	public Object visit(ASTLocalVarDef node, Object data) {
		String name = getTokenOfChild(node, 0);
		
		display.getScope().CheckVarRedefinition(name);
		return display.defineVariable(name, null);	
	}

	//Dot expression - left must be and object
	public Object visit(ASTDotExpression node, Object data) {
		Value left = ((Reference) doChild(node, 0)).getValue();
		Class newinstance = left.classValue();
		
		VariableUse use = getVariableContext(data);
		
		use.local = true;
		
		return display.executeNodeinContext(newinstance, getChild(node, 1), this, use);
	}
}
