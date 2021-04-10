package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemFloat extends SystemCode {
	
	public SystemFloat() {
		name = "float";
		params = new Vector<String>(Arrays.asList("x"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		double x = scope.getLocalVar("x").getValue().doubleValue();
		
		Value result = new ValueRational(x);
		
		return new Reference(result);
	}

}
