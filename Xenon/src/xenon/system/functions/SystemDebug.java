package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.Display;
import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.interpreter.Parser;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemDebug extends SystemCode {
	
	public SystemDebug() {
		name = "debug";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		
		return null;
	}
}
