package xenon.system.classes.queue;

import java.util.Arrays;
import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Parser;
import xenon.interpreter.Class;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemPushCol1 extends SystemCode {
	
	public SystemPushCol1() {
		name = "pushcol";
		params = new Vector<String>(Arrays.asList("collection"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Class thisqueue = (Class) GetFunctionScope(scope);
		Reference list = GetParameter(scope, "collection");
		
		SystemQueue ext = (SystemQueue) GetClass(scope);
		
		Parser p = Parser.getInstance();
		
		Reference i = new Reference(new ValueInteger(0));
		Reference e = new Reference();
		while (true) {
			Value size = p.getDisplay().InvokeMethod(Function.SIZE, list.getValue().classValue(), p).getValue();
			
			// evaluate loop test
			Value hopefullyValueBoolean = i.getValue().lt(size);
			if (!((ValueBoolean)hopefullyValueBoolean).booleanValue())
				break;
			
			//Set the element
			Vector<Reference> paramlist = new Vector<Reference>();
			paramlist.add(i);
			e.SetValue(p.getDisplay().InvokeMethod(Function.ITERATE, paramlist, list.getValue().classValue(), p).getValue());
			
			Vector<Reference> paramlist2 = new Vector<Reference>();
			paramlist2.add(e);
			p.getDisplay().InvokeMethod("push", paramlist2, thisqueue, p);
			//ext.vector.add(new Reference(e.getValue()));
			
			
			// assign loop increment
			i.SetValue(i.getValue().add(new ValueInteger(1)));
		}
		
		return null;
	}

}
