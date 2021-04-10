package xenon.system.classes.randomqueue;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemTakeRandom1 extends SystemCode {
	
	public SystemTakeRandom1() {
		name = "takerandom";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemRandomQueue ext = (SystemRandomQueue) GetClass(scope);
		
		Reference top = ext.vector.get(0);
		ext.vector.remove(0);
		
		int max = ext.vector.size();
		
		int x = (int)(Math.random()*((max)+1));
		
		if (x == max) {
			ext.vector.add(top.refclone());
		}
		else {
			ext.vector.add(ext.vector.get(x));
			ext.vector.set(x, top.refclone());
		}
		
		return top;
	}

}
