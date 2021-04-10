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

public class SystemSize extends SystemCode {
	
	public SystemSize() {
		name = Function.SIZE;
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemMap ext = (SystemMap) GetClass(scope);
		
		return new Reference(new ValueInteger(ext.map.size()));
	}

}
