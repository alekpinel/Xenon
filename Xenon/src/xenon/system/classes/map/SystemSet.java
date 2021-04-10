package xenon.system.classes.map;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemSet extends SystemCode {
	
	public SystemSet() {
		name = "set";
		params = new Vector<String>(Arrays.asList("key", "value"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Reference key = scope.getLocalVar("key").clone();
		Reference value = scope.getLocalVar("value").clone();
		
		SystemMap ext = (SystemMap) GetClass(scope);
		
		ext.map.put(key, value);
		
		return null;
	}

}
