package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemRemove1 extends SystemCode {
	
	public SystemRemove1() {
		name = "remove";
		params = new Vector<String>(Arrays.asList("index"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		int i = (int) GetParameter(scope, "index").getValue().longValue();
		
		SystemVector ext = (SystemVector) GetClass(scope);
		
		if (i < 0 || i > ext.vector.size()) {
			throw new ExceptionSemantic("The index " + i + " is outside of the bounds of the " +  name);
		}
		
		return ext.vector.remove(i);
	}

}
