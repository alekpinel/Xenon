package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemBrackets1 extends SystemCode {
	
	public SystemBrackets1() {
		name = "brackets";
		params = new Vector<String>(Arrays.asList("index"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		int i = (int) scope.getLocalVar("index").getValue().longValue();
		
		SystemVector ext = (SystemVector) GetClass(scope);
		
		if (i < -ext.vector.size()  || i >= ext.vector.size()) {
			throw new ExceptionSemantic("The index " + i + " is outside of the bounds of the " +  name);
		}
		
		if (i < 0) {
			i = ext.vector.size() + i;
		}
		
		return ext.vector.get(i);
	}

}
