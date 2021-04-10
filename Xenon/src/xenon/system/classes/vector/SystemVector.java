package xenon.system.classes.vector;

import java.util.Vector;

import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.SystemFunction;
import xenon.system.*;
import xenon.values.*;

public class SystemVector extends SystemClassExtension {
	public Vector<Reference> vector;
	
	public SystemVector() {
		name = "vector";
	}
	
	public Vector<Function> LoadMethods(Scope scope) {
		Vector<Function> methods = new Vector<Function>();
		
		methods.add(new SystemFunction(scope, new SystemVector0()));
		methods.add(new SystemFunction(scope, new SystemVector1()));
		methods.add(new SystemFunction(scope, new SystemVector2()));
		methods.add(new SystemFunction(scope, new SystemGet()));
		methods.add(new SystemFunction(scope, new SystemIterate()));
		methods.add(new SystemFunction(scope, new SystemBrackets1()));
		methods.add(new SystemFunction(scope, new SystemBrackets2()));
		methods.add(new SystemFunction(scope, new SystemAdd1()));
		methods.add(new SystemFunction(scope, new SystemAdd2()));
		methods.add(new SystemFunction(scope, new SystemSize0()));
		methods.add(new SystemFunction(scope, new SystemToString()));
		methods.add(new SystemFunction(scope, new SystemClone()));
		methods.add(new SystemFunction(scope, new SystemClear0()));
		methods.add(new SystemFunction(scope, new SystemRemove1()));
		methods.add(new SystemFunction(scope, new SystemSort0()));
		methods.add(new SystemFunction(scope, new SystemShuffle0()));
		
		return methods;
	}

	public SystemClassExtension clone() {
		SystemVector v = new SystemVector();
		if (vector != null)
			v.vector = new Vector(vector);
		return v;
	}
}
