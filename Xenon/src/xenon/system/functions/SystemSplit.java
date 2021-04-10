package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.values.*;

public class SystemSplit extends SystemCode {
	
	public SystemSplit() {
		name = "split";
		params = new Vector<String>(Arrays.asList("string", "del"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		String string = GetParameter(scope, "string").getValue().stringValue();
		String del = "[" + GetParameter(scope, "del").getValue().stringValue() + "]";
		
		String[] parts = string.split(del);
		int nparts = parts.length;
		
		Vector<Reference> v = new Vector<Reference>(nparts);
		
		for (int i = 0; i < nparts; i++) {
			if (!parts[i].isEmpty()) {
				v.add(new Reference(new ValueString(parts[i])));
			}
		}
		
		return SystemInitializer.CreateNewVector(v);
	}

}
