package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemAdd1 extends SystemCode {
	
	public SystemAdd1() {
		name = "add";
		params = new Vector<String>(Arrays.asList("element"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Reference e = GetParameter(scope, "element");
		
		SystemVector ext = (SystemVector) GetClass(scope);
		
		ext.vector.add(new Reference(e.getValue()));
		
		return null;
	}

}
