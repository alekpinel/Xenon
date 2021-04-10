package xenon.system.classes.queue;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.Class;
import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Parser;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemQueue1 extends SystemCode {
	
	public SystemQueue1() {
		name = Function.INIT;
		params = new Vector<String>(Arrays.asList("collection"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Class thisqueue = (Class) GetFunctionScope(scope);
		Reference list = GetParameter(scope, "collection");
		SystemQueue ext = (SystemQueue) GetClass(scope);
		
		ext.vector = new Vector<Reference>();
		
		Parser p = Parser.getInstance();
		Vector<Reference> paramlist2 = new Vector<Reference>();
		paramlist2.add(list);
		p.getDisplay().InvokeMethod("pushcol", paramlist2, thisqueue, p);
		
		return null;
	}

}
