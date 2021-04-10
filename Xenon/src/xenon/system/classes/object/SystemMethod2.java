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

public class SystemMethod2 extends SystemCode {
	
	public SystemMethod2() {
		name = "method";
		params = new Vector<String>(Arrays.asList("name", "nparams"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		String name = GetParameter(scope, "name").getValue().stringValue();
		int nparams = (int) GetParameter(scope, "nparams").getValue().longValue();
		
		Reference function = GetFunctionScope(scope).getLocalFunction(name, nparams);
		
		return function;
	}
}
