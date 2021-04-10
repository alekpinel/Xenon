package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemInt extends SystemCode {
	
	public SystemInt() {
		name = "int";
		params = new Vector<String>(Arrays.asList("x"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		long x = scope.getLocalVar("x").getValue().longValue();
		
		Value result = new ValueInteger(x);
		
		return new Reference(result);
	}

}
