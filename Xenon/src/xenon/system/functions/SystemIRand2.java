package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;
import java.util.Random;

public class SystemIRand2 extends SystemCode {
	
	public SystemIRand2() {
		name = "irand";
		params = new Vector<String>(Arrays.asList("min", "max"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		long min = scope.getLocalVar("min").getValue().longValue();
		long max = scope.getLocalVar("max").getValue().longValue();
		
		long x = (int)(Math.random()*((max-min)))+min;
		
		return new Reference(new ValueInteger(x));
	}
}