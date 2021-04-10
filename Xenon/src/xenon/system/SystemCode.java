package xenon.system;

import java.util.Vector;

import xenon.interpreter.*;
import xenon.values.Reference;

public abstract class SystemCode {
	
	public String name;
	public Vector<String> params;
	
	public SystemCode() {
	}
	
	public abstract Reference Invoke(Scope scope);
	
	//Auxiliar Methods
	
	public SystemClassExtension GetClass(Scope scope) {
		return ((SystemClass) scope.GetParent()).getClassExtension();
	}
	
	public SystemClassExtension GetClassObject(Scope scope) {
		return ((SystemClass) scope).getClassExtension();
	}
	
	public Reference GetParameter(Scope scope, String name) {
		return scope.getLocalVar(name);
	}
	
	public Scope GetFunctionScope(Scope scope) {
		return scope.GetParent();
	}
}
