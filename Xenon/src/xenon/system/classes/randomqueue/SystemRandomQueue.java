package xenon.system.classes.randomqueue;

import java.util.Vector;

import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.SystemFunction;
import xenon.system.*;
import xenon.system.classes.queue.SystemQueue;
import xenon.values.*;

public class SystemRandomQueue extends SystemQueue {
	
	public SystemRandomQueue() {
		name = "randomqueue";
	}
	
	public Vector<Function> LoadMethods(Scope scope) {
		Vector<Function> methods = new Vector<Function>();
		
		methods.add(new SystemFunction(scope, new SystemPush1()));
		methods.add(new SystemFunction(scope, new SystemTakeRandom1()));
		
		return methods;
	}
	
	public SystemClassExtension clone() {
		SystemRandomQueue v = new SystemRandomQueue();
		if (vector != null)
			v.vector = new Vector(vector);
		return v;
	}
}
