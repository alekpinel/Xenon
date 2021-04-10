package xenon.system.classes.map;

import java.util.HashMap;
import java.util.Vector;

import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.SystemFunction;
import xenon.system.*;
import xenon.system.classes.queue.SystemQueue;
import xenon.values.*;

public class SystemMap extends SystemClassExtension {
	public HashMap<Reference, Reference> map;
	
	public SystemMap() {
		name = "map";
	}
	
	public Vector<Function> LoadMethods(Scope scope) {
		Vector<Function> methods = new Vector<Function>();
		
		methods.add(new SystemFunction(scope, new SystemInit0()));
		methods.add(new SystemFunction(scope, new SystemBrackets()));
		methods.add(new SystemFunction(scope, new SystemClear()));
		methods.add(new SystemFunction(scope, new SystemClone()));
		methods.add(new SystemFunction(scope, new SystemGet1()));
		methods.add(new SystemFunction(scope, new SystemKeys()));
		methods.add(new SystemFunction(scope, new SystemRemove1()));
		methods.add(new SystemFunction(scope, new SystemSet()));
		methods.add(new SystemFunction(scope, new SystemSize()));
		methods.add(new SystemFunction(scope, new SystemValues()));
		methods.add(new SystemFunction(scope, new SystemToString()));
		methods.add(new SystemFunction(scope, new SystemIterate()));
		
		
		return methods;
	}
	
	public SystemClassExtension clone() {
		SystemMap v = new SystemMap();
		if (map != null)
			v.map = new HashMap<Reference, Reference>(map);
		return v;
	}
}
