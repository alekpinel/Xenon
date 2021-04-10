package xenon.system.classes.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.system.SystemInitializer;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemKeys extends SystemCode {
	
	public SystemKeys() {
		name = "keys";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		SystemMap ext = (SystemMap) GetClass(scope);
		
		Vector<Reference> keys = new Vector<Reference>(ext.map.keySet());
		
		
		return SystemInitializer.CreateNewVector(keys);
	}

}
