package xenon.system.classes.file;

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

public class SystemFile1 extends SystemCode {
	
	public SystemFile1() {
		name = Function.INIT;
		params = new Vector<String>(Arrays.asList("string"));
	}

	@Override
	public Reference Invoke(Scope scope) {
		String str = (String) scope.getLocalVar("string").getValue().stringValue();
		
		SystemFile ext = (SystemFile) GetClass(scope);
		
		try {
			
			ext.file = new File(str);
			ext.file.createNewFile();
			ext.scanner = new Scanner(ext.file);
			ext.filewriter = new FileWriter(ext.file, true);
			
	    } catch (FileNotFoundException e) {
	    	throw new ExceptionSemantic("File " + str + " not found");
	    } catch (IOException e) {
	    	throw new ExceptionSemantic("The file " + str + " could not been created");
		}
		
		return null;
	}

}
