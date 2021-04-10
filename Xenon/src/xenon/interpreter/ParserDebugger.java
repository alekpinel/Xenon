package xenon.interpreter;

import xenon.parser.ast.*;

public class ParserDebugger implements XenonVisitor {
	
	private int indent = 0;
	
	private String indentString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < indent; ++i) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
	/** Debugging dump of a node. */
	private Object dump(SimpleNode node, Object data) {
		System.out.println(indentString() + node);
		++indent;
		data = node.childrenAccept(this, data);
		--indent;
		return data;
	}
	
	public Object visit(SimpleNode node, Object data) {
		System.out.println(node + ": acceptor not implemented in subclass?");
		return data;
	}
	
	// Execute a Xenon program
	public Object visit(ASTCode node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Execute a statement
	public Object visit(ASTStatement node, Object data) {
		dump(node, data);
		return data;
	}

	// Execute a block
	public Object visit(ASTBlock node, Object data) {
		dump(node, data);
		return data;	
	}

	// Execute an IF 
	public Object visit(ASTIfStatement node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Function definition parameter list
	public Object visit(ASTParmlist node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Function body
	public Object visit(ASTFnBody node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Function definition
	public Object visit(ASTFnDef node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Function return expression
	public Object visit(ASTReturnExpression node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Function argument list
	public Object visit(ASTArgList node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Function invocation in an expression
	public Object visit(ASTFnInvoke node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Execute a FOR loop
	public Object visit(ASTForLoop node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Process an identifier
	// This doesn't do anything, but needs to be here because we need an ASTIdentifier node.
	public Object visit(ASTIdentifier node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Execute an assignment statement, by popping a value off the stack and assigning it
	// to a variable.
	public Object visit(ASTAssignment node, Object data) {
		dump(node, data);
		return data;
	}

	// OR
	public Object visit(ASTOr node, Object data) {
		dump(node, data);
		return data;
	}

	// AND
	public Object visit(ASTAnd node, Object data) {
		dump(node, data);
		return data;		
	}

	// ==
	public Object visit(ASTCompEqual node, Object data) {
		dump(node, data);
		return data;
	}

	// !=
	public Object visit(ASTCompNequal node, Object data) {
		dump(node, data);
		return data;
	}

	// >=
	public Object visit(ASTCompGTE node, Object data) {
		dump(node, data);
		return data;
	}

	// <=
	public Object visit(ASTCompLTE node, Object data) {
		dump(node, data);
		return data;
	}

	// >
	public Object visit(ASTCompGT node, Object data) {
		dump(node, data);
		return data;
	}

	// <
	public Object visit(ASTCompLT node, Object data) {
		dump(node, data);
		return data;
	}

	// +
	public Object visit(ASTAdd node, Object data) {
		dump(node, data);
		return data;
	}

	// -
	public Object visit(ASTSubtract node, Object data) {
		dump(node, data);
		return data;
	}

	// *
	public Object visit(ASTTimes node, Object data) {
		dump(node, data);
		return data;
	}

	// /
	public Object visit(ASTDivide node, Object data) {
		dump(node, data);
		return data;
	}

	// NOT
	public Object visit(ASTUnaryNot node, Object data) {
		dump(node, data);
		return data;
	}

	// + (unary)
	public Object visit(ASTUnaryPlus node, Object data) {
		dump(node, data);
		return data;
	}

	// - (unary)
	public Object visit(ASTUnaryMinus node, Object data) {
		dump(node, data);
		return data;
	}

	// Push string literal to stack
	public Object visit(ASTCharacter node, Object data) {
		dump(node, data);
		return data;
	}
	
	// Push integer literal to stack
	public Object visit(ASTInteger node, Object data) {
		dump(node, data);
		return data;
	}

	// Push floating point literal to stack
	public Object visit(ASTRational node, Object data) {
		dump(node, data);
		return data;
	}

	// Push true literal to stack
	public Object visit(ASTTrue node, Object data) {
		dump(node, data);
		return data;
	}

	// Push false literal to stack
	public Object visit(ASTFalse node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTWhileLoop node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTClassDef node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTNewObject node, Object data) {
		dump(node, data);
		return data;
	}
	
	@Override
	public Object visit(ASTVariable node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTDotExpression node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTUnaryIncrement node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTSqrBrackets node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTForEachLoop node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTThis node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTGlobal node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTNullLiteral node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTModule node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTReferenceList node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTLambda node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTLocalVarDef node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTUnaryDecrement node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTAssignIncrement node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTAssignDecrement node, Object data) {
		dump(node, data);
		return data;
	}

	@Override
	public Object visit(ASTVectorLiteral node, Object data) {
		dump(node, data);
		return data;
	}
}
