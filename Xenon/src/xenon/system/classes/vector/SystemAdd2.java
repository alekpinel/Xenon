package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemAdd2 extends SystemCode {
	
	public SystemAdd2() {
		name = "add";
		params = new Vector<String>(Arrays.asList("index", "element"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		int i = (int) scope.getLocalVar("index").getValue().longValue();
		Reference e = scope.getLocalVar("element");
		
		SystemVector ext = (SystemVector) GetClass(scope);
		
		if (i < 0 || i > ext.vector.size()) {
			throw new ExceptionSemantic("The index " + i + " is outside of the bounds of the " +  name);
		}
		
		ext.vector.add(i, e.refclone());
		
		return null;
	}

}
