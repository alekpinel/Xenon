package xenon.system.classes.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.SystemClass;
import xenon.system.SystemCode;
import xenon.system.SystemClassExtension;
import xenon.values.*;

public class SystemClone extends SystemCode {
	
	public SystemClone() {
		name = Function.CLONE;
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		
		SystemMap ext = (SystemMap) GetClass(scope);
		
		SystemMap ext2 = new SystemMap();
		
		ext2.map = new HashMap<Reference, Reference>();
		//ext2.map = (HashMap<Reference, Reference>) ext.map.clone();
		for (Entry<Reference, Reference> entry : ext.map.entrySet()) {
			ext2.map.put(entry.getKey().clone(), entry.getValue().clone());
		}
		
		return new Reference(new ValueClass(new SystemClass(scope.GetParent().GetParent(), ext2)));
	}

}
