package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;
import java.util.Random;

public class SystemIRand1 extends SystemCode {
	
	public SystemIRand1() {
		name = "irand";
		params = new Vector<String>(Arrays.asList("max"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		long min = 0;
		long max = scope.getLocalVar("max").getValue().longValue();
		
		long x = (long)(Math.random()*((max-min)))+min;
		
		return new Reference(new ValueInteger(x));
	}
}