package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.values.*;

public class SystemRange2 extends SystemCode {
	public SystemRange2() {
		name = "range";
		params = new Vector<String>(Arrays.asList("min", "max"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		int min = (int) GetParameter(scope, "min").getValue().doubleValue();
		int max = (int) GetParameter(scope, "max").getValue().doubleValue();
		
		Vector<Reference> v = new Vector<Reference>(max - min);
		
		for (int i = 0; i < (max - min); i++) {
			v.add(new Reference(new ValueInteger(i + min)));
		}
		
		return SystemInitializer.CreateNewVector(v);
	}

}
