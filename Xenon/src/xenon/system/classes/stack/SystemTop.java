package xenon.system.classes.stack;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemTop extends SystemCode {
	
	public SystemTop() {
		name = "top";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemStack ext = (SystemStack) GetClass(scope);
		
		Reference top = ext.vector.get(ext.vector.size() - 1);
		
		return top;
	}

}
