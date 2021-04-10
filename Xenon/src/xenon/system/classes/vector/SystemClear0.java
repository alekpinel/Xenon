package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.classes.queue.SystemQueue;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemClear0 extends SystemCode {
	
	public SystemClear0() {
		name = "clear";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemVector ext = (SystemVector) GetClass(scope);
		
		ext.vector.clear();
		
		return null;
	}

}
