package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemVector2 extends SystemCode {
	
	public SystemVector2() {
		name = Function.INIT;
		params = new Vector<String>(Arrays.asList("size1", "size2"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		long size1 = scope.getLocalVar("size1").getValue().longValue();
		long size2 = scope.getLocalVar("size2").getValue().longValue();
		
		if (size1 < 0) {
			throw new ExceptionSemantic("The size of " + name + " must be >= 0, received: " +  size1);
		}
		if (size2 < 0) {
			throw new ExceptionSemantic("The size of " + name + " must be >= 0, received: " +  size2);
		}
		
		SystemVector ext = (SystemVector) GetClass(scope);
		
		ext.vector = new Vector<Reference>((int) size1);
		
		for (int i = 0; i < size1; i++) {
			Vector<Reference> v = new Vector<Reference>((int) size2);
			
			for (int j = 0; j < size2; j++) {
				v.add(new Reference());
			}
			
			Reference columns = SystemInitializer.CreateNewVector(v);
			ext.vector.add(columns);
		}
		
		return null;
	}

}
