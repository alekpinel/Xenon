package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.system.classes.vector.SystemVector;
import xenon.values.*;

public class SystemCrossProduct2 extends SystemCode {
	
	public SystemCrossProduct2() {
		name = "cross";
		params = new Vector<String>(Arrays.asList("v1", "v2"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Scope c1 = GetParameter(scope, "v1").getValue().classValue();
		Vector<Reference> v1 = ((SystemVector) GetClassObject(c1)).vector;
		
		Scope c2 = GetParameter(scope, "v2").getValue().classValue();
		Vector<Reference> v2 = ((SystemVector) GetClassObject(c2)).vector;
		
		int len = 3;
		
		if (len != v1.size() || len != v2.size()) {
			throw new ExceptionSemantic("The size of the vectors must be 3 to do a cross product");
		}
		
		double m1 = v1.get(0).getValue().doubleValue();
		double m2 = v1.get(1).getValue().doubleValue();
		double m3 = v1.get(2).getValue().doubleValue();
		double n1 = v2.get(0).getValue().doubleValue();
		double n2 = v2.get(1).getValue().doubleValue();
		double n3 = v2.get(2).getValue().doubleValue();
		
		Vector<Reference> v3 = new Vector<Reference>(3);
		v3.add(new Reference(new ValueRational(m2*n3 - m3*n2)));
		v3.add(new Reference(new ValueRational(m3*n1 - m1*n3)));
		v3.add(new Reference(new ValueRational(m1*n2 - m2*n1)));
		
		return SystemInitializer.CreateNewVector(v3);
	}
}
