package xenon.system.classes.vector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
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
		
		SystemVector ext = (SystemVector) GetClass(scope);
		
		SystemVector ext2 = new SystemVector();
		
		ext2.vector = new Vector<Reference>(ext.vector.size());
		
		for (int i = 0; i < ext.vector.size(); i++) {
			ext2.vector.add(ext.vector.get(i).clone());
		}
		
		return new Reference(new ValueClass(new SystemClass(scope.GetParent().GetParent(), ext2)));
	}

}
