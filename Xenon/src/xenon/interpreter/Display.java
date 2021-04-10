package xenon.interpreter;

import java.util.Vector;

import xenon.interpreter.Parser.VariableUse;
import xenon.parser.ast.SimpleNode;
import xenon.values.*;
import xenon.system.SystemInitializer;

/** A display manages run-time access to variable and parameter scope where
 * functions may be nested.
 */ 
public class Display {

	//Global scope - Always available
	private Scope globalinstance = Scope.MainScope();
	//Display - Instance where we search for variables
	private Scope display = globalinstance;
	//Working Function - Where we search for parameters
	private Scope functioninstance = display;
	
	/** Ctor */
	Display() {
		//Initialize System Functions and Classes
		SystemInitializer.InitializeSystem(this);
	}
	
	public Scope getScope() {
		return display;
	}
	
	public Class getGlobal() {
		return (Class) globalinstance;
	}
	
	public Class getThis() {
		Scope scp = display.GetParent();
		while (scp != null && !(scp instanceof Class)) {
			scp = scp.GetParent();
		}
		
		if (scp == null) {
			throw new ExceptionSemantic("There is no \"this\" in this scope");
		}
		
		return (Class) scp;
	}
	
	//Functionality
	
	/** Execute a function in its scope, using a specified parser. */
	Reference executeFunction(Function fn, Parser p) {
		
		Scope oldContext = display;
		Scope oldfunctioninstance = functioninstance;
		display = fn;
		functioninstance = fn;
		
		Reference v = ((Function) display).execute(p);
		
		display = oldContext;
		functioninstance = oldfunctioninstance;
		
		if (v == null) {
			v = new Reference();
		}
		return v;
	}
	
	/** Execute a node with a new scope. */
	public Object executeNodeinContext(Scope context, SimpleNode node, Parser p, Object data) {
		Scope oldContext = display;
		display = context;
		
		Object v = p.executeNode(node, data);
		
		display = oldContext;
		
		if (v == null) {
			v = new Reference();
		}
		return v;
	}
	
	//Execute a node in the instance of the last function (It is meant for the arguments)
	public Object executeNodeinFunction(SimpleNode node, Parser p, Object data) {
		return executeNodeinContext(functioninstance, node, p, data);
	}
		
	//Arguments
	void executeArguments(SimpleNode arglist, Function functioninvocation, Parser p) {
		Scope oldContext = display;
		display = functioninstance;
		
		p.executeNode(arglist, functioninvocation);
		
		display = oldContext;
	}
	
	void executeArguments(Vector<Reference> arglist, Function functioninvocation) {
		for (int i = 0; i < arglist.size(); i++) {
			functioninvocation.setArgument(arglist.get(i));
		}
	}
	
	//Function invocations (different functions for different ways of give the parameters.
	
	public Reference InvokeFunction(Function fnt, SimpleNode parameters, Parser p) {
		Function newInvocation = (Function) fnt.Instantiate();
		
		// Child 1 - arglist
		if (parameters != null) {
			executeArguments(parameters, newInvocation, p);
		}
		
		// Execute
		return executeFunction(newInvocation, p);
	}
	
	public Reference InvokeFunction(Function fnt, Vector<Reference> parameters, Parser p) {
		Function newInvocation = (Function) fnt.Instantiate();
		
		// Child 1 - arglist
		if (parameters != null) {
			executeArguments(parameters, newInvocation);
		}
		
		// Execute
		return executeFunction(newInvocation, p);
	}
	
	//Invoke a method
	public Reference InvokeMethod(String funname, SimpleNode parameters, Class object, Parser p) {
		int nparams = 0;
		if (parameters != null) {
			nparams = Parser.getChildrenCount(parameters);
		}
		
		Scope oldContext = display;
		display = object;
		
		//Function fnt = findFunction(funname, nparams);
		Reference functionref = object.getLocalFunction(funname, nparams);
		
		if (functionref == null) {
			throw new ExceptionSemantic("The method " + funname + " with " + nparams + " parameters of the object " + object.getName() + " does not exists");
		}
		
		Function fnt = functionref.getValue().functionValue();
		
		Reference v = InvokeFunction(fnt, parameters, p);
		
		display = oldContext;
		return v;
	}
	
	public Reference InvokeMethod(String funname, Class object, Parser p) {
		int nparams = 0;
		Scope oldContext = display;
		display = object;
		
		Reference functionref = object.getLocalFunction(funname, nparams);
		
		
		if (functionref == null) {
			throw new ExceptionSemantic("The method " + funname + " with " + nparams + " parameters of the object " + object.getName() + " does not exists");
		}
		
		Function fnt = functionref.getValue().functionValue();
		
		Reference v = InvokeFunction(fnt, (SimpleNode) null, p);
		
		display = oldContext;
		return v;
	}
	
	//Invoke a method
	public Reference InvokeMethod(String funname, Vector<Reference> parameters, Class object, Parser p) {
		int nparams = 0;
		if (parameters != null) {
			nparams = parameters.size();
		}
		Scope oldContext = display;
		display = object;
		
		Reference functionref = object.getLocalFunction(funname, nparams);
		
		if (functionref == null) {
			throw new ExceptionSemantic("The method " + funname + " with " + nparams + " of the object " + object.getName() + " parameters does not exists");
		}
		
		Function fnt = functionref.getValue().functionValue();
		
		Reference v = InvokeFunction(fnt, parameters, p);
		
		display = oldContext;
		return v;
	}
	
	//Has the function found a return statement?
	boolean IsReturning() {
		return display instanceof Function && ((Function) display).returnvalue;
	}
	
	//Return a value
	void ReturnFunction(Reference returnvalue) {
		if (!(display instanceof Function) || display == globalinstance) {
			throw new ExceptionSemantic("Return expression outside of a function");
		}
		((Function) display).returnvalue = true;
	}
	
	//New definitions
	
	public Function defineNewFunction(Function newfunction) {
		ValueFunction functval = new ValueFunction(newfunction);
		display.defineVariable(newfunction.getCode(), functval);
		return newfunction;
	}
	
	Function defineNewFunction(String functionname, SimpleNode body, Vector<String> parameters) {
		Function newfunction = new Function(functionname, display, body, parameters);
		
		ValueFunction functval = new ValueFunction(newfunction);
		display.defineVariable(newfunction.getCode(), functval);
		return newfunction;
	}

	/** Find a function.  Return null if it doesn't exist. */
	Function findFunction(String name, int nparam) {
		String code = Function.getCode(name, nparam);
		Reference ref = getReference(code);
		
		if (ref == null) {
			//If the function with the complete name is not found search the simple name
			ref = getReference(name);
		}
		
		if (ref != null) {
			return (Function) ref.getValue().functionValue();
		}
		else {
			return null;
		}
	}
	
	//Classes
	public Class defineNewClass(Class newclass) {
		ValueClass classval = new ValueClass(newclass);
		display.defineVariable(newclass.getName(), classval);
		
		return newclass;
	}
	
	public Class defineNewClass(String newclassname, Vector<Class> parents, SimpleNode body, Parser p) {
		
		Class newclass = new Class(newclassname, display, parents);
		executeNodeinContext(newclass, body, p, null);
		
		ValueClass classval = new ValueClass(newclass);
		display.defineVariable(newclass.getName(), classval);
		
		return newclass;
	}
	
	/** Find an inner class definition.  Return null if it doesn't exist. */
	Class findClass(String name) {
		Reference ref = getReference(name);
		
		if (ref != null && ref.getValue() != null && ref.getValue() instanceof ValueClass) {
			Class class_ = ((ValueClass) ref.getValue()).classValue();
			return (Class) class_;
		}
		else {
			return null;
		}
	}
	
	//Instantiate a new object
	Reference newObject(Class theclass, int paramcount, SimpleNode params, Parser p) {
		if (!theclass.IsDefinition()) {
			throw new ExceptionSemantic("Trying to instantiate from an object (use "+ theclass.getName() + ".clone() instead)");
		}
		
		Class newobject = (Class) theclass.Instantiate();
		
		if (paramcount > 0 || !newobject.DefaultConstructor()) {
			InvokeMethod(Function.INIT, params, newobject, p);
		}
		
		return new Reference(new ValueClass(newobject));
	}
	
	//Variables
	
	public Reference defineVariable(String name, Value val){
		return display.defineVariable(name, val);
	}
	
	public Reference getReference(String varName) {
		return display.getReference(varName, true);
	}
	
	public Reference getVariable(String name, boolean write, boolean local) {
		Reference var;
		
		//Search locally only
		if (local) {
			var = display.getLocalVar(name);
		}
		else {
			//Doesn't look in the global layer
			if (write) {
				var = display.getReference(name, false);
			}
			//Look everywhere
			else {
				var = display.getReference(name, true);
			}
		}
		
		//If we need to write and the value is null, we define it
		if (var == null && write) {
			var = display.defineVariable(name);
		}
		
		//If it was not found
		if (var == null)
		{
			throw new ExceptionSemantic("Variable " + name + " does not exist in the current context.");
		}
		
		display.definedVars.add(name);
		
		return var;
	}
	
	
	//Debug
	
	public void DebugPrintDisplay() {
		display.DebugPrintAllVariables();
	}
}
