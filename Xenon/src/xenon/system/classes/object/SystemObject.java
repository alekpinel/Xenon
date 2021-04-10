package xenon.system.classes.object;

import java.util.HashMap;
import java.util.Vector;

import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.SystemFunction;
import xenon.system.*;
import xenon.system.classes.map.SystemInit0;
import xenon.system.classes.vector.SystemVector;
import xenon.values.*;

public class SystemObject extends SystemClassExtension {
	
	public SystemObject() {
		name = "object";
	}
	
	public Vector<Function> LoadMethods(Scope scope) {
		Vector<Function> methods = new Vector<Function>();
		
		methods.add(new SystemFunction(scope, new SystemToString()));
		methods.add(new SystemFunction(scope, new SystemClone()));
		methods.add(new SystemFunction(scope, new SystemMethod2()));
		methods.add(new SystemFunction(scope, new SystemSetMethod1()));
		methods.add(new SystemFunction(scope, new SystemSetMethod2()));
		
		return methods;
	}
	
	public SystemClassExtension clone() {
		return new SystemObject();
	}
}
