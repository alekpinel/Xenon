package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemFunction1 extends SystemCode {
	
	public SystemFunction1() {
		name = "function";
		params = new Vector<String>(Arrays.asList("name"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		String name = GetParameter(scope, "name").getValue().stringValue();
		int nparams = 0;
		
		Reference function = GetFunctionScope(scope).getLocalFunction(name, nparams);
		
		return function;
	}

}
