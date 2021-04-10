package xenon.system.classes.queue;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemSize0 extends SystemCode {
	
	public SystemSize0() {
		name = Function.SIZE;
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemQueue ext = (SystemQueue) GetClass(scope);
		
		return new Reference(new ValueInteger(ext.vector.size()));
	}

}
