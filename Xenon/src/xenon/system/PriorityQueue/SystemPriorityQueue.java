package xenon.system.PriorityQueue;

import java.util.Vector;

import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.SystemFunction;
import xenon.system.*;
import xenon.system.classes.queue.SystemQueue;
import xenon.values.*;

public class SystemPriorityQueue extends SystemQueue {
	
	public SystemPriorityQueue() {
		name = "priorityqueue";
	}
	
	public Vector<Function> LoadMethods(Scope scope) {
		Vector<Function> methods = new Vector<Function>();
		
		methods.add(new SystemFunction(scope, new SystemPush1()));
		
		return methods;
	}
	
	public SystemClassExtension clone() {
		SystemPriorityQueue v = new SystemPriorityQueue();
		if (vector != null)
			v.vector = new Vector(vector);
		return v;
	}
}
