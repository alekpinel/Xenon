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

public class SystemSetMethod1 extends SystemCode {
	
	public SystemSetMethod1() {
		name = "setmethod";
		params = new Vector<String>(Arrays.asList("function"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Reference ref = GetParameter(scope, "function");
		Function function = ref.getValue().functionValue();
		
		if (function.IsLambda()) {
			throw new ExceptionSemantic("Cannot add an anonymous function without a name. Use setmethod(function, name)");
		}
		
		((Class) GetFunctionScope(scope)).AddMethod(function, function.getName());
		
		return null;
	}
}
