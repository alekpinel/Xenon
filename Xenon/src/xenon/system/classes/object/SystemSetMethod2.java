package xenon.system.classes.object;

import java.util.Arrays;
import java.util.Vector;
import java.util.Map.Entry;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.Class;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemSetMethod2 extends SystemCode {
	
	public SystemSetMethod2() {
		name = "setmethod";
		params = new Vector<String>(Arrays.asList("function", "name"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Reference ref = GetParameter(scope, "function");
		Function function = ref.getValue().functionValue();
		
		String name = GetParameter(scope, "name").getValue().stringValue();
		
		((Class) GetFunctionScope(scope)).AddMethod(function, name);
		
		return null;
	}
}
