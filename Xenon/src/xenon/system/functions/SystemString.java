package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemString extends SystemCode {
	
	public SystemString() {
		name = "str";
		params = new Vector<String>(Arrays.asList("x"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		String x = scope.getLocalVar("x").getValue().stringValue();
		
		Value result = new ValueString(x);
		
		return new Reference(result);
	}

}
