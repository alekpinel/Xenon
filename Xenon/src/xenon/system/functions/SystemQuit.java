package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemQuit extends SystemCode {
	
	public SystemQuit() {
		name = "quit";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		System.exit(0);
		return null;
	}
}
