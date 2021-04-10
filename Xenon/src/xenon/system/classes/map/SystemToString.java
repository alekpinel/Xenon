package xenon.system.classes.map;

import java.util.Arrays;
import java.util.Vector;
import java.util.Map.Entry;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemToString extends SystemCode {
	
	public SystemToString() {
		name = Function.TOSTRING;
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemMap ext = (SystemMap) GetClass(scope);
		String str = "[";
		
		int i = 0;
		for (Entry<Reference, Reference> entry : ext.map.entrySet()) {
			if (i > 0)
			{
				str += ", ";
			}
			
			str += entry.getKey() + ": " + entry.getValue();
			
			i++;
	    }
		
		str = str + "]";
		
		return new Reference(new ValueString(str));
	}
}
