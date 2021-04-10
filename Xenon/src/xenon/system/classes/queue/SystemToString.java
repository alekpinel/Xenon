package xenon.system.classes.queue;

import java.util.Arrays;
import java.util.Vector;

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
		SystemQueue ext = (SystemQueue) GetClass(scope);
		String str = "[";
		
		if (ext.vector != null)
		{
			for (int i = 0; i < ext.vector.size(); i++) {
				if (i == 0) {
					str = str + ext.vector.get(i);
				}
				else {
					str = str + ", " + ext.vector.get(i);
				}
			}
		}
		
		str = str + "]";
		
		return new Reference(new ValueString(str));
	}
}
