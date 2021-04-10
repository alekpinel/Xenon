package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;
import java.util.Random;

public class SystemRand2 extends SystemCode {
	
	public SystemRand2() {
		name = "rand";
		params = new Vector<String>(Arrays.asList("min", "max"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		double min = scope.getLocalVar("min").getValue().doubleValue();
		double max = scope.getLocalVar("max").getValue().doubleValue();
		
		double x = (Math.random()*((max-min)))+min;
		
		return new Reference(new ValueRational(x));
	}
}