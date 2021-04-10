package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.*;
import xenon.values.*;

public class SystemBool extends SystemCode {
	
	public SystemBool() {
		name = "str";
		params = new Vector<String>(Arrays.asList("x"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		boolean x = scope.getLocalVar("x").getValue().booleanValue();
		
		Value result = new ValueBoolean(x);
		
		return new Reference(result);
	}

}
