package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.values.*;

public class SystemRange1 extends SystemCode {
	
	public SystemRange1() {
		name = "range";
		params = new Vector<String>(Arrays.asList("max"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		int x = (int) GetParameter(scope, "max").getValue().doubleValue();
		
		if (x < 0) {
			throw new ExceptionSemantic("The size of " + name + " must be >= 0, received: " +  x);
		}
		
		Vector<Reference> v = new Vector<Reference>(x);
		
		for (int i = 0; i < x; i++) {
			v.add(new Reference(new ValueInteger(i)));
		}
		
		return SystemInitializer.CreateNewVector(v);
	}

}
