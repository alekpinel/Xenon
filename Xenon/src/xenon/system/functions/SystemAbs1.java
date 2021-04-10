package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemAbs1 extends SystemCode {
	
	public SystemAbs1() {
		name = "sin";
		params = new Vector<String>(Arrays.asList("x"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		double x = GetParameter(scope, "x").getValue().doubleValue();
		
		Value result = new ValueRational(Math.sin(x));
		
		return new Reference(result);
	}

}
