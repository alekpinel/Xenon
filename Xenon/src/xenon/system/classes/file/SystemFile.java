package xenon.system.classes.file;

import java.util.Vector;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

import xenon.interpreter.Function;
import xenon.interpreter.Scope;
import xenon.interpreter.SystemFunction;
import xenon.system.*;
import xenon.system.classes.vector.SystemToString;
import xenon.system.classes.vector.SystemVector;
import xenon.values.*;

public class SystemFile extends SystemClassExtension {
	public File file;
	public Scanner scanner;
	public FileWriter filewriter;
	
	public SystemFile() {
		name = "file";
	}
	
	public Vector<Function> LoadMethods(Scope scope) {
		Vector<Function> methods = new Vector<Function>();
		
		methods.add(new SystemFunction(scope, new SystemFile1()));
		methods.add(new SystemFunction(scope, new SystemFile2()));
		methods.add(new SystemFunction(scope, new SystemClose()));
		methods.add(new SystemFunction(scope, new SystemReadLine()));
		methods.add(new SystemFunction(scope, new SystemWrite1()));
		methods.add(new SystemFunction(scope, new SystemNewLine0()));
		
		return methods;
	}
	
	public SystemClassExtension clone() {
		SystemFile v = new SystemFile();
		if (file != null)
			v.file = file;
		if (scanner != null)
			v.scanner = scanner;
		if (filewriter != null)
			v.filewriter = filewriter;
		return v;
	}
}
