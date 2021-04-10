package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.system.classes.vector.SystemVector;
import xenon.values.*;

public class SystemMatrixVectorProduct2 extends SystemCode {
	
	public SystemMatrixVectorProduct2() {
		name = "matrixvectordot";
		params = new Vector<String>(Arrays.asList("m", "v"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Scope c1 = GetParameter(scope, "m").getValue().classValue();
		Vector<Reference> m = ((SystemVector) GetClassObject(c1)).vector;
		
		Scope c2 = GetParameter(scope, "v").getValue().classValue();
		Vector<Reference> v = ((SystemVector) GetClassObject(c2)).vector;
		
		int x = m.size();
		int y = v.size();
		
		
		Vector<Reference> v3 = new Vector<Reference>(x);
		Vector<Reference> mv;
		for (int i = 0; i < x; i++) {
			mv = ((SystemVector) GetClassObject(m.get(i).getValue().classValue())).vector;
			
			if (mv.size() != y) {
				throw new ExceptionSemantic("The matrix and the vector are not compatible");
			}
			
			double result = 0;
			for (int j = 0; j < y; j++) {
				result += mv.get(j).getValue().doubleValue() * v.get(j).getValue().doubleValue();
			}
			
			v3.add(new Reference(new ValueRational(result)));
		}
		
		return SystemInitializer.CreateNewVector(v3);
	}
}
