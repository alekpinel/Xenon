package xenon.interpreter;

import java.util.HashMap;
import java.util.Vector;

import xenon.parser.ast.SimpleNode;
import xenon.values.Reference;

public class Function extends Scope {
	
	
	//Static methods
	public static String SEPARATOR = "__";
	
	//special methods
	public static String INIT = "init";
	public static String BRACKETS = "brackets";
	public static String ITERATE = "iterate";
	public static String SIZE = "size";
	public static String CLONE = "clone";
	public static String TOSTRING = "tostring";
	public static String EQUALS = "equals";
	public static String COMPARE = "compare";
	public static String LAMBDA = "lambda";
	public static String OBJECT = "object";
	public static String MAIN = "main";
	
	
	public static String getCode(String name, int nparam) {
		return name + SEPARATOR + nparam;
	}
	
	
	
	protected Vector<String> parameters = new Vector<String>();
	
	protected SimpleNode ASTFunctionBody = null;
	
	protected int argumentCount = 0;
	
	public boolean returnvalue = false;
	public boolean inheritedfromobject = false;
	
	//Constructors
	
	protected Function() {
	}
	
	public Function(String name, Scope parent, SimpleNode body) {
		super(name, parent);
		ASTFunctionBody = body;
		argumentCount = 0;
	}
	
	public Function(String name, Scope parent, SimpleNode body, Vector<String> params) {
		super(name, parent);
		ASTFunctionBody = body;
		argumentCount = 0;
		
		if (params != null)
			defineParameters(params);
	}
	
	//Instantiate
	public Function Instantiate() {
		Function newobject = new Function(name, parent, ASTFunctionBody, parameters);
		newobject.definition = false;
		newobject.inheritedfromobject = this.inheritedfromobject;
		
		return newobject;
	}
	
	//Getters and Setters
	
	public boolean IsLambda() {
		return getName().equals(LAMBDA);
	}
	
	public boolean IsFromObject() {
		return inheritedfromobject;
	}
	
	/** Set the function body of this function. */
	public void setFunctionBody(SimpleNode node) {
		ASTFunctionBody = node;
	}
	
	/** Get the function body of this function. */
	public SimpleNode getFunctionBody() {
		return ASTFunctionBody;
	}
	
	public String getCode() {
		return getCode(getName(), getParameterCount());
	}
	
	public String getNameAndParams() {
		String str = getName() + "(";
		
		for (int i = 0; i < getParameterCount(); i++) {
			String name = getParameterName(i);
			if (i == 0)
				str += name;
			else
				str += ", " + name;
		}
		
		str += ")";
		
		return str;
	}
	
	/** Get count of parameters. */
	public int getParameterCount() {
		return parameters.size();
	}
	
	public String getParameterName(int i) {
		return parameters.get(i);
	}
	
	//Parameters
	
	/** Define a list of parameter. */
	public void defineParameters(Vector<String> params) {
		for (int i = 0; i < params.size(); i++) {
			defineParameter(params.get(i));
		}
	}
	
	/** Define a parameter. */
	public void defineParameter(String name) {
		if (getLocalVar(name) != null)
			throw new ExceptionSemantic("Parameter " + name + " already exists in function " + getName());
		defineVariable(name);
		parameters.add(name);
	}
	
	//Calling
	
	/** Set an argument value. */
	public void setArgument(Reference v) {
		if (argumentCount >= getParameterCount())
			throw new ExceptionSemantic("Function " + getName() + " expected " + getParameterCount() + " arguments but got " + (argumentCount + 1) + ".");
		
		// First slots are always arguments
		String paramname = getParameterName(argumentCount);
		setLocalVar(paramname, v);
		argumentCount++;
	}
	
	/** Check argument count. */
	public void checkArgumentCount() {
		if (argumentCount < getParameterCount())
			throw new ExceptionSemantic("Function " + getName() + " expected " + getParameterCount() + " arguments but got " + (argumentCount) + ".");		
	}
	
	/** Execute this invocation. */
	public Reference execute(Parser parser) {
		return (Reference) parser.doChildren(getFunctionBody(), null);
	}
	
	//To String
	public String toString() {
		String str = "";
		
		str += getNameAndParams();
		return str;
	}
}
