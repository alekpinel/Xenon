package xenon.interpreter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

import xenon.values.Reference;
import xenon.values.Value;

public class Scope implements Comparable<Scope> {
	
	protected String name;
	protected Scope parent;
	
	//All the variables of a given Scope
	protected HashMap<String, Reference> slots = new HashMap<String, Reference>();
	
	//It represent if the Scope is a definition or an object
	protected boolean definition = true;
	
	protected Set<String> definedVars = new HashSet<String>(); 
	
	
	public static Scope MainScope() {
		return new Class("Global", null, null);
	}
	
	//Constructors
	
	public Scope() {
		this.parent = null;
	}
	
	public Scope(String name) {
		this.parent = null;
		this.name = name;
	}
	
	public Scope(String name, Scope parent) {
		this.parent = parent;
		this.name = name;
	}

	//Accessors
	
	protected void setParent(Scope metaclass) {
		this.parent = metaclass;
	}
	
	/** Get the name of this Scope. */
	public String getName() {
		return name;
	}
	
	public Scope GetParent() {
		return parent;
	}
	
	public boolean IsDefinition() {
		return definition;
	}
	
	/** Comparison operator.  Class of the same name are the same. */
	public int compareTo(Scope o) {
		return name.compareTo(o.name);
	}
	
	//Slots
	
	//Get Local variable
	public Reference getLocalVar(String name) {
		Reference ref = slots.get(name);
		if (ref == null) {
			ref = searchIncompleteFunction(name);
		}
		return ref;
	}
	
	//get local var of a function given only the name, not the code
	public Reference searchIncompleteFunction(String name) {
		for (Entry<String, Reference> entry : slots.entrySet()) {
			if (entry.getKey().contains(Function.SEPARATOR)) {
				String[] parts = entry.getKey().split(Function.SEPARATOR);
				if (parts[0].equals(name)) {
					return entry.getValue();
				}
			}
	    }
		
		return null;
	}
	
	/** Given a var name, set its value. */
	public void setLocalVar(String name, Reference value) {
		slots.put(name, value);
	}
	
	public HashMap<String, Reference> getAllSlots(){
		return slots;
	}

	
	
	//Size of the slots
	public int getLocalCount() {
		return slots.size();
	}
	
	//Variables
	
	/** Define a variable.  Return its reference */
	public Reference defineVariable(String name) {
		
		Reference ref = slots.get(name);
		if (ref != null)
			return ref;
		
		ref = new Reference();
		slots.put(name, ref);
		CheckVarRedefinition(name);
		definedVars.add(name);
		
		return ref;
	}
	
	/** Define a variable.  Return its reference */
	public Reference defineVariable(String name, Value val) {
		Reference ref = defineVariable(name);
		ref.SetValue(val);
		return ref;
	}
	
	public void CheckVarRedefinition(String name) {
		if (definedVars.contains(name)) {
			throw new ExceptionSemantic("Trying to redefine variable " + name + ". Possibly you have used a variable of the same name of other scope. If you want to assign a global var you need to specify it. In this case: global."+ name + "");
		}
	}
	
	/** Return the value of a variable in the scope of this instance. If not found, it will return null. */
	public Reference getReference(String varName, boolean searchglobal) {
		
		//First, we search in this scope
		Reference ref = getLocalVar(varName);
		
		if (ref != null) {
			//System.out.println("searching var: " + varName + " has found " + ref.toString());
			return ref;
		}
		
		if (parent != null) {
			//Do not search in global scope if not ordered
			if (searchglobal || parent.GetParent() != null) {
				return parent.getReference(varName, searchglobal);
			}
		}
		
		//System.out.println("searching var: " + varName + " has not been found");
		return null;
	}
	
	public Reference getFunction(String name, int parameters) {
		return getReference(Function.getCode(name, parameters), true);
	}
	
	public Reference getLocalFunction(String name, int parameters) {
		return getLocalVar(Function.getCode(name, parameters));
	}
	
	//Description of the scope
	public String toString() {
		String str = "";
		
		if (!definition)
			str += "Object ";
		
		str += getName() + " {";
		
		int i = 0;
		for (Entry<String, Reference> entry : slots.entrySet()) {
	        String name = entry.getKey();
			if (i == 0)
				str += name + ": " + getLocalVar(name).toString();
			else
				str += ", " + name + ": " + getLocalVar(name).toString();
			i++;
	    }
		
		str += "}";
		return str;
	}
	
	//Print all the variables - Debug
	public void DebugPrintAllVariables() {
		System.out.println(getName() + " has: " + getLocalCount());
		
		for (Entry<String, Reference> entry : slots.entrySet()) {
	        System.out.println(entry.getKey());
	    }
		System.out.println("end");
	}
	
	public void DebugPrintState() {
		System.out.println(toString());
	}
}
