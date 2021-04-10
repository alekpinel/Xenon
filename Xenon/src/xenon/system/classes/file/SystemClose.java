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

public class SystemClose extends SystemCode {
	
	public SystemClose() {
		name = "close";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemFile ext = (SystemFile) GetClass(scope);
		
		try {
			ext.scanner.close();
			ext.filewriter.close();
		} catch (IOException e) {
			throw new ExceptionSemantic("Error when closing file.");
		}
		
		return null;
	}

}
