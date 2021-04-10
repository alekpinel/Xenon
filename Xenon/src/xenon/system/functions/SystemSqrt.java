package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemSqrt extends SystemCode {
	
	public SystemSqrt() {
		name = "sqrt";
		params = new Vector<String>(Arrays.asList("x"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		double x = scope.getLocalVar("x").getValue().doubleValue();
		
		if (x < 0) {
			throw new ExceptionSemantic("Math error " +  name + "(" + name + ") is not allowed");
		}
		
		Value result = new ValueRational(Math.sqrt(x));
		
		return new Reference(result);
	}

}
