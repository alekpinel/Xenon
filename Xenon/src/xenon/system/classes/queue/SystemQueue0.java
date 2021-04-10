package xenon.system.classes.queue;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemQueue0 extends SystemCode {
	
	public SystemQueue0() {
		name = Function.INIT;
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		
		SystemQueue ext = (SystemQueue) GetClass(scope);
		
		ext.vector = new Vector<Reference>();
		
		return null;
	}

}
