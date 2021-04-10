package xenon.values;

import xenon.interpreter.ExceptionSemantic;

public class ValueNull extends ValueAbstract {
	
	public ValueNull() {
	}
	
	public String getName() {
		return "null";
	}
	
	public Value assign(Value v) {
		return this;
	}
	
	/** Convert this to a String. */
	public String stringValue() {
		return "null";		
	}

	public int compare(Value v) {
		if (v instanceof ValueNull) {
			return 0;
		}
		return -1;
	}
	
	/** Return a copy of this value */
	public Value clone() {
		return new ValueNull();
	}
	
	public String toString() {
		return "null";
	}
	
	/** Convert this to a primitive boolean. */
	public boolean booleanValue() {
		return false;
	}
	
	public Object mainValue() {
		return null;
	}
}
