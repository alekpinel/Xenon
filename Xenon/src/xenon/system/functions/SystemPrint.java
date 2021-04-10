package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Parser;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemPrint extends SystemCode {
	
	public SystemPrint() {
		name = "print";
		params = new Vector<String>(Arrays.asList("str"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		String str = GetParameter(scope, "str").getValue().stringValue();
		
		
		System.out.println(str);
		
		return null;
	}
}
