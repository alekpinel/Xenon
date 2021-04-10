package xenon.system.classes.object;

import java.util.Arrays;
import java.util.Vector;
import java.util.Map.Entry;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.Class;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemClone extends SystemCode {
	
	public SystemClone() {
		name = Function.CLONE;
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		Class obj = (Class) scope.GetParent();
		
		return new Reference(new ValueClass((Class)obj.Instantiate()));
	}
}
