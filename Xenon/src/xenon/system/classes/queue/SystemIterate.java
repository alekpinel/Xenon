package xenon.system.classes.queue;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemIterate extends SystemCode {
	
	public SystemIterate() {
		name = Function.ITERATE;
		params = new Vector<String>(Arrays.asList("index"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		int i = (int) scope.getLocalVar("index").getValue().longValue();
		
		SystemQueue ext = (SystemQueue) GetClass(scope);
		
		if (i < 0 || i >= ext.vector.size()) {
			throw new ExceptionSemantic("The index " + i + " is outside of the bounds of the " +  name);
		}
		
		return ext.vector.get(i);
	}

}
