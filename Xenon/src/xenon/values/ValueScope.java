package xenon.values;

import xenon.interpreter.Class;
import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;
import xenon.interpreter.Scope;

public class ValueScope extends ValueAbstract {
	
	protected Scope instance;
	
	protected ValueScope() {
	}
	
	public ValueScope (Scope scope) {
		this.instance = scope;
	}

	@Override
	public String getName() {
		return instance.getName();
	}

	@Override
	public int compare(Value v) {
		if (scopeValue() == v.scopeValue()) {
			return 0;
		}
		return 1;
	}
	
	public Value assign(Value v) {
		instance = v.scopeValue();
		return this;
	}
	
	/** Convert this to a primitive String. */
	public String stringValue() {
		String classdescription = instance.toString();
		
		return classdescription;
	}
	
	public String toString() {
		return stringValue();
	}
	
	public Scope scopeValue() {
		return instance;
	}
	
	/** Convert this to a primitive function definition. */
	public Function functionValue() {
		return (Function) instance;
	}
	
	/** Convert this to a primitive class definition. */
	public Class classValue() {
		return (Class) instance;
	}


	@Override
	public Value clone() {
		return new ValueScope(instance);
	}
	
	public Object mainValue() {
		return instance;
	}
}
