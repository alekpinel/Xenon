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

public class SystemGet1 extends SystemCode {
	
	public SystemGet1() {
		name = "get";
		params = new Vector<String>(Arrays.asList("key"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Reference key = scope.getLocalVar("key");
		
		SystemMap ext = (SystemMap) GetClass(scope);
		
		Reference value = ext.map.get(key);
		
		if (value == null) {
			value = new Reference();
		}
		else {
			value = value.clone();
		}
		
		return value;
	}

}
