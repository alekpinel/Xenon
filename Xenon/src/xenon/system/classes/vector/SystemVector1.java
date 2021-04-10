package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemVector1 extends SystemCode {
	
	public SystemVector1() {
		name = Function.INIT;
		params = new Vector<String>(Arrays.asList("size"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		long size = scope.getLocalVar("size").getValue().longValue();
		
		if (size < 0) {
			throw new ExceptionSemantic("The size of " + name + " must be >= 0, received: " +  size);
		}
		
		SystemVector ext = (SystemVector) GetClass(scope);
		
		ext.vector = new Vector<Reference>((int) size);
		
		for (int i = 0; i < size; i++) {
			ext.vector.add(new Reference());
		}
		
		return null;
	}

}
