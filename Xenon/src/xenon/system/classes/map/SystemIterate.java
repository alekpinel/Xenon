package xenon.system.classes.map;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemIterate extends SystemCode {
	
	public SystemIterate() {
		name = Function.ITERATE;
		params = new Vector<String>(Arrays.asList("index"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		int index = (int) scope.getLocalVar("index").getValue().longValue();
		
		SystemMap ext = (SystemMap) GetClass(scope);
		
		Vector<Reference> values = new Vector<Reference>(ext.map.values());
		
		return values.get(index);
	}

}
