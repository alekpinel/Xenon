package xenon.interpreter;

import java.util.Vector;

import xenon.system.SystemCode;
import xenon.values.Reference;

public class SystemFunction extends Function {
	
	SystemCode systemfunction;
	
	public SystemFunction(Scope parent, SystemCode code) {
		this.parent = parent;
		this.name = code.name;
		argumentCount = 0;
		systemfunction = code;
		
		if (code.params != null)
			defineParameters(code.params);
	}
	
	public SystemFunction (String name, Scope parent, SystemCode code, Vector<String> params) {
		this.parent = parent;
		this.name = name;
		argumentCount = 0;
		systemfunction = code;
		
		if (params != null)
			defineParameters(params);
	}
	
	//Instantiate
	public Function Instantiate() {
		Function newobject = new SystemFunction(name, parent, systemfunction, parameters);
		newobject.definition = false;
		newobject.inheritedfromobject = this.inheritedfromobject;
		
		return newobject;
	}
	
	/** Execute this invocation. */
	public Reference execute(Parser parser) {
		return systemfunction.Invoke(this);
	}
}
