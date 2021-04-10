package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.system.classes.vector.SystemVector;
import xenon.values.*;

public class SystemDotProduct2 extends SystemCode {
	
	public SystemDotProduct2() {
		name = "dot";
		params = new Vector<String>(Arrays.asList("v1", "v2"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Scope c1 = GetParameter(scope, "v1").getValue().classValue();
		Vector<Reference> v1 = ((SystemVector) GetClassObject(c1)).vector;
		
		Scope c2 = GetParameter(scope, "v2").getValue().classValue();
		Vector<Reference> v2 = ((SystemVector) GetClassObject(c2)).vector;
		
		int len = v1.size();
		
		if (len != v2.size()) {
			throw new ExceptionSemantic("The size of the vectors must be the same to do a dot product");
		}
		
		double result = 0;
		
		for (int i = 0; i < len; i++) {
			result += v1.get(i).getValue().doubleValue() * v2.get(i).getValue().doubleValue();
		}
		
		return new Reference(new ValueRational(result));
	}

}
