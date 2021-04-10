package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemSort0 extends SystemCode {
	
	public SystemSort0() {
		name = "sort";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemVector ext = (SystemVector) GetClass(scope);
		
		Collections.sort( (List) ext.vector);
		
		return null;
	}

}
