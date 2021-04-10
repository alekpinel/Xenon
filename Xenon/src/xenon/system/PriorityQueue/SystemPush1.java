package xenon.system.PriorityQueue;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemPush1 extends SystemCode {
	
	public SystemPush1() {
		name = "push";
		params = new Vector<String>(Arrays.asList("element"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Reference e = GetParameter(scope, "element");
		
		SystemPriorityQueue ext = (SystemPriorityQueue) GetClass(scope);
		
		ext.vector.add(e.refclone());
		
		for (int j = ext.vector.size() - 1; j > 0 && (ext.vector.get(j).getValue().lt(ext.vector.get(j - 1).getValue())).booleanValue(); j--){
			Value aux = ext.vector.get(j).getValue();
			ext.vector.get(j).SetValue(ext.vector.get(j - 1).getValue());
			ext.vector.get(j - 1).SetValue(aux);
		}
		
		return null;
	}

}
