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

public class SystemRemove1 extends SystemCode {
	
	public SystemRemove1() {
		name = "remove";
		params = new Vector<String>(Arrays.asList("key"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		Reference key = scope.getLocalVar("key");
		
		SystemMap ext = (SystemMap) GetClass(scope);
		
		return ext.map.remove(key);
	}

}
