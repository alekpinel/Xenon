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

public class SystemBrackets extends SystemCode {
	
	public SystemBrackets() {
		name = Function.BRACKETS;
		params = new Vector<String>(Arrays.asList("key"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Reference key = scope.getLocalVar("key").clone();
		
		SystemMap ext = (SystemMap) GetClass(scope);
		
		Reference val = ext.map.get(key);
		if (val == null) {
			val = new Reference();
			ext.map.put(key, val);
		}
		
		return val;
	}

}
