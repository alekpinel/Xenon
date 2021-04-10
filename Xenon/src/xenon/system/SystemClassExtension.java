package xenon.system;

import java.util.Vector;

import xenon.interpreter.*;

public abstract class SystemClassExtension {
	public String name;
	
	public SystemClassExtension() {
	}
	
	public abstract Vector<Function> LoadMethods(Scope scope);
	
	public abstract SystemClassExtension clone();
}
