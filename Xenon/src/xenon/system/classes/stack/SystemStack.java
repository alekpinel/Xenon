package xenon.system.classes.stack;

import java.util.Vector;

import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.SystemFunction;
import xenon.system.*;
import xenon.system.classes.queue.SystemQueue;
import xenon.values.*;

public class SystemStack extends SystemQueue {
	
	public SystemStack() {
		name = "stack";
	}
	
	public Vector<Function> LoadMethods(Scope scope) {
		Vector<Function> methods = new Vector<Function>();
		
		methods.add(new SystemFunction(scope, new SystemPop0()));
		methods.add(new SystemFunction(scope, new SystemTop()));
		
		return methods;
	}
	
	public SystemClassExtension clone() {
		SystemStack v = new SystemStack();
		if (vector != null)
			v.vector = new Vector(vector);
		return v;
	}
}
