package xenon.interpreter;

import java.util.HashMap;
import java.util.Vector;

import xenon.system.SystemClassExtension;
import xenon.values.Reference;
import xenon.values.ValueFunction;

public class SystemClass extends Class {
	
	protected SystemClassExtension classextension;
	
	public SystemClass(Scope parent, SystemClassExtension classextension) {
		super(classextension.name, parent);
		Initialize(classextension);
	}
	
	public SystemClass(String name, Scope parent, SystemClassExtension classextension, Vector<Class> supers) {
		super(name, parent, supers);
		Initialize(classextension);
	}

	public SystemClassExtension getClassExtension() {
		return classextension;
	}
	
	public void Initialize(SystemClassExtension classextension) {
		this.classextension = classextension;
		
		Vector<Function> methods = classextension.LoadMethods(this);
		for (int i = 0; i < methods.size(); i++) {
			defineVariable(methods.get(i).getCode(), new ValueFunction(methods.get(i)));
		}
	}
	
	//Instantiate
	public Scope Instantiate() {
		SystemClass newobject = new SystemClass(name, parent, classextension.clone(), supers);
		newobject.definition = false;
		
		for (HashMap.Entry<String, Reference> var : slots.entrySet()) {
			Reference newvar = var.getValue().clone();
		    newobject.slots.put(var.getKey(), newvar);
		    if (newvar.getValue() instanceof ValueFunction) {
		    	newvar.getValue().functionValue().setParent(newobject);
		    }
		}
		
		return newobject;
	}
}
