package xenon.system.classes.queue;

import java.util.Vector;

import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.SystemFunction;
import xenon.system.*;
import xenon.system.classes.vector.SystemVector;
import xenon.values.*;

public class SystemQueue extends SystemClassExtension {
	public Vector<Reference> vector;
	
	public SystemQueue() {
		name = "queue";
	}
	
	public Vector<Function> LoadMethods(Scope scope) {
		Vector<Function> methods = new Vector<Function>();
		
		methods.add(new SystemFunction(scope, new SystemQueue0()));
		methods.add(new SystemFunction(scope, new SystemQueue1()));
		methods.add(new SystemFunction(scope, new SystemIterate()));
		methods.add(new SystemFunction(scope, new SystemPop0()));
		methods.add(new SystemFunction(scope, new SystemPush1()));
		methods.add(new SystemFunction(scope, new SystemPushCol1()));
		methods.add(new SystemFunction(scope, new SystemSize0()));
		methods.add(new SystemFunction(scope, new SystemTop()));
		methods.add(new SystemFunction(scope, new SystemClear0()));
		methods.add(new SystemFunction(scope, new SystemToString()));
		
		return methods;
	}
	
	public SystemClassExtension clone() {
		SystemQueue v = new SystemQueue();
		if (vector != null)
			v.vector = new Vector(vector);
		return v;
	}
}
