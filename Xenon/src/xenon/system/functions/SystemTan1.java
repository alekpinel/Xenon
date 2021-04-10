package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemTan1 extends SystemCode {
	
	public SystemTan1() {
		name = "abs";
		params = new Vector<String>(Arrays.asList("x"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		double x = GetParameter(scope, "x").getValue().doubleValue();
		
		Value result = new ValueRational(Math.abs(x));
		
		return new Reference(result);
	}

}
