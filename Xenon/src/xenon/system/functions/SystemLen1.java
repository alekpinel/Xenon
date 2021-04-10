package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.values.*;

public class SystemLen1 extends SystemCode {
	
	public SystemLen1() {
		name = "len";
		params = new Vector<String>(Arrays.asList("str"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		String str = GetParameter(scope, "str").getValue().stringValue();
		
		return new Reference(new ValueInteger(str.length()));
	}

}
