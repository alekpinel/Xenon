package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemBrackets2 extends SystemCode {
	
	public SystemBrackets2() {
		name = Function.BRACKETS;
		params = new Vector<String>(Arrays.asList("from", "to"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		int min = (int) scope.getLocalVar("from").getValue().longValue();
		int max = (int) scope.getLocalVar("to").getValue().longValue();
		
		SystemVector ext = (SystemVector) GetClass(scope);
		
		if (!(min <= max && min >= 0 && max <= ext.vector.size())) {
			throw new ExceptionSemantic("The indexes " + min + " and " + max + " are outside of the bounds of the " +  name);
		}
		
		Vector<Reference> v = new Vector<Reference>(max - min);
		
		for (int i = min; i < max; i++) {
			v.add(new Reference(ext.vector.get(i).getValue()));
		}
		
		return SystemInitializer.CreateNewVector(v);
	}

}
