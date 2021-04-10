package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemPow2 extends SystemCode {
	
	public SystemPow2() {
		name = "pow";
		params = new Vector<String>(Arrays.asList("x", "exp"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		double x = GetParameter(scope, "x").getValue().doubleValue();
		double exp = GetParameter(scope, "exp").getValue().doubleValue();
		
		Value result = new ValueRational(Math.pow(x, exp));
		
		return new Reference(result);
	}

}
