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

public class SystemToString extends SystemCode {
	
	public SystemToString() {
		name = Function.TOSTRING;
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		Class obj = (Class) scope.GetParent();
		String str = "";
		
		if (!obj.IsDefinition())
			str += "Object ";
		
		str += obj.getName() + " {";
		
		int i = 0;
		for (Entry<String, Reference> entry : obj.getAllSlots().entrySet()) {
	        String name = entry.getKey();
	        
	        Reference var = obj.getLocalVar(name);
			if (var.getValue() instanceof ValueFunction && var.getValue().functionValue().IsFromObject()) {
				continue;
			}
	        
			if (i != 0)
				str += ", ";
			
			if (var.getValue() instanceof ValueFunction && name.contains(Function.SEPARATOR)) {
				if (name.split(Function.SEPARATOR)[0].equals(((ValueFunction) var.getValue()).functionValue().getName())) {
					str += var.toString();
				}
				else {
					str += name + ": " + var.toString();
				}
			}
			else {
				str += name + ": " + var.toString();
			}
			
			i++;
	    }
		
		str += "}";
		
		return new Reference(new ValueString(str));
	}
}
