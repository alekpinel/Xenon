package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemFunction2 extends SystemCode {
	
	public SystemFunction2() {
		name = "function";
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
