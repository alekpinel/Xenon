package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.system.classes.vector.SystemVector;
import xenon.values.*;

public class SystemVectorScale2 extends SystemCode {
	
	public SystemVectorScale2() {
		name = "vectorscale";
		params = new Vector<String>(Arrays.asList("vector", "scalar"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Scope c1 = GetParameter(scope, "vector").getValue().classValue();
		Vector<Reference> v1 = ((SystemVector) GetClassObject(c1)).vector;
		
		Value scalar = GetParameter(scope, "scalar").getValue();
		
		int len = v1.size();
		
		Vector<Reference> v3 = new Vector<Reference>(len);
		
		for (int i = 0; i < len; i++) {
			v3.add(new Reference(v1.get(i).getValue().mult(scalar)));
		}
		
		return SystemInitializer.CreateNewVector(v3);
	}
}
