package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemSize0 extends SystemCode {
	
	public SystemSize0() {
		name = "size";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemVector ext = (SystemVector) GetClass(scope);
		
		return new Reference(new ValueInteger(ext.vector.size()));
	}

}
