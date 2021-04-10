package xenon.interpreter;

import java.util.HashMap;
import java.util.Vector;
import java.util.Map.Entry;

import xenon.parser.ast.SimpleNode;
import xenon.values.Reference;
import xenon.values.Value;
import xenon.values.ValueFunction;

public class Class extends Scope {
	
	public static Class OBJECTCLASS;
	
	Vector<Class> supers;
	
	public Class(String name, Scope parent) {
		super(name, parent);
		SetDefaultParent();
	}
	
	public Class(String name, Scope parent, Vector<Class> supers) {
		super(name, parent);
		
		if (supers == null || supers.size() == 0) {
			SetDefaultParent();
		}
		else {
			this.supers = supers;
		}
		
		for (int i = 0; i < this.supers.size(); i++) {
			CopyAllComponentsFrom(this.supers.get(i));
		}
	}
	
	public void SetDefaultParent() {
		supers = new Vector<Class>();
		if (OBJECTCLASS != null) {
			supers.add(OBJECTCLASS);
		}
	}
	
	//Instantiate
	public Scope Instantiate() {
		Class newobject = new Class(name, parent, supers);
		newobject.definition = false;
		newobject.supers = supers;
		
		for (HashMap.Entry<String, Reference> var : slots.entrySet()) {
			Reference newvar = var.getValue().clone();
		    newobject.slots.put(var.getKey(), newvar);
		    if (newvar.getValue() instanceof ValueFunction) {
		    	newvar.getValue().functionValue().setParent(newobject);
		    }
		}
		
		return newobject;
	}
	
	public void CopyAllComponentsFrom(Class other) {
		for (HashMap.Entry<String, Reference> var : other.slots.entrySet()) {
			Reference newvar = var.getValue().clone();
		    
		    if (newvar.getValue() instanceof ValueFunction) {
		    	newvar = new Reference(newvar.getValue().clone());
		    	newvar.getValue().functionValue().setParent(this);
		    	//this.slots.put(var.getKey(), newvar);
		    	
		    	if (other.getName() == OBJECTCLASS.getName()) {
		    		newvar.getValue().functionValue().inheritedfromobject = true;
		    	}
		    }
		    
		    this.slots.put(var.getKey(), newvar);
		}
	}
	
	public boolean DefaultConstructor() {
		return searchIncompleteFunction(Function.INIT) == null;
	}
	
	//Add a method to the class
	public void AddMethod(Function method, String name) {
		Reference newvar = new Reference(new ValueFunction(method.Instantiate()));
    	newvar.getValue().functionValue().setParent(this);
    	this.slots.put(Function.getCode(name, method.getParameterCount()), newvar);
	}
	
	//Invoke method of the class
	public Reference InvokeMethod(String functionname) {
		Parser p = Parser.getInstance();
		return p.getDisplay().InvokeMethod(functionname, this, p);
	}
	
	//Invoke method of the class
	public Reference InvokeMethod(String functionname, Vector<Reference> params) {
		Parser p = Parser.getInstance();
		return p.getDisplay().InvokeMethod(functionname, params, this, p);
	}
	
	//Invoke method of the class
	public Reference InvokeMethod(String functionname, SimpleNode params) {
		Parser p = Parser.getInstance();
		return p.getDisplay().InvokeMethod(functionname, params, this, p);
	}
}
