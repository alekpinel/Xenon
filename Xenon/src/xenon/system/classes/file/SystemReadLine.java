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

public class SystemReadLine extends SystemCode {
	
	public SystemReadLine() {
		name = "readline";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemFile ext = (SystemFile) GetClass(scope);
		
		if (ext.scanner.hasNextLine()) {
			String str = ext.scanner.nextLine();
			
			return new Reference(new ValueString(str));
		}
		
		return new Reference();
	}

}
