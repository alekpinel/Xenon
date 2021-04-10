package xenon.system.classes.file;

import java.util.Arrays;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemWrite1 extends SystemCode {
	
	public SystemWrite1() {
		name = "write";
		params = new Vector<String>(Arrays.asList("string"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		String str = (String) scope.getLocalVar("string").getValue().stringValue();
		SystemFile ext = (SystemFile) GetClass(scope);
		
		try {
			ext.filewriter.write(str);
			
		} catch (IOException e) {
			throw new ExceptionSemantic("Error when writing file.");
		}
		
		return null;
	}
}
