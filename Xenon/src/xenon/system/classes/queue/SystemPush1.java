package xenon.system.classes.queue;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemPush1 extends SystemCode {
	
	public SystemPush1() {
		name = "push";
		params = new Vector<String>(Arrays.asList("element"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Reference e = GetParameter(scope, "element");
		
		SystemQueue ext = (SystemQueue) GetClass(scope);
		
		ext.vector.add(e.refclone());
		
		return null;
	}

}
