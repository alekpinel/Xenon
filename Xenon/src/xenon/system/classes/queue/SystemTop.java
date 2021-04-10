package xenon.system.classes.queue;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemTop extends SystemCode {
	
	public SystemTop() {
		name = "top";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemQueue ext = (SystemQueue) GetClass(scope);
		
		Reference top = ext.vector.get(0);
		
		return top;
	}

}
