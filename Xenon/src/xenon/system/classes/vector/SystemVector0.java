package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemVector0 extends SystemCode {
	
	public SystemVector0() {
		name = Function.INIT;
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		
		SystemVector ext = (SystemVector) GetClass(scope);
		
		ext.vector = new Vector<Reference>();
		
		return null;
	}

}
