package xenon.system.classes.randomqueue;

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
		
		SystemRandomQueue ext = (SystemRandomQueue) GetClass(scope);
		
		int max = ext.vector.size();
		
		int x = (int)(Math.random()*((max)+1));
		
		if (x == max) {
			ext.vector.add(e.refclone());
		}
		else {
			ext.vector.add(ext.vector.get(x));
			ext.vector.set(x, e.refclone());
		}
		
		return null;
	}

}
