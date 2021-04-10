package xenon.values;

public class ValueBoolean extends ValueAbstract {

	private boolean internalValue;
	
	public ValueBoolean(boolean b) {
		internalValue = b;
	}
	
	public String getName() {
		return "boolean";
	}
	
	public Value assign(Value v) {
		internalValue = v.booleanValue();
		return this;
	}
	
	/** Return a copy of this value */
	public Value clone() {
		return new ValueBoolean(internalValue);
	}
	
	/** Convert this to a primitive boolean. */
	public boolean booleanValue() {
		return internalValue;
	}
	
	/** Convert this to a primitive long. */
	public long longValue() {
		if (booleanValue()) {
			return 1;
		}
		return 0;
	}
	
	/** Convert this to a primitive double. */
	public double doubleValue() {
		if (booleanValue()) {
			return 1;
		}
		return 0;
	}
	
	/** Convert this to a primitive string. */
	public String stringValue() {
		return (internalValue) ? "true" : "false";
	}
	
	public Value or(Value v) {
		return new ValueBoolean(internalValue || v.booleanValue());
	}

	public Value and(Value v) {
		return new ValueBoolean(internalValue && v.booleanValue());
	}

	public Value not() {
		return new ValueBoolean(!internalValue);
	}

	public int compare(Value v) {
		if (internalValue == v.booleanValue())
			return 0;
		else if (internalValue)
			return 1;
		else
			return -1;
	}
	
	public String toString() {
		return "" + internalValue;
	}
	
	public Object mainValue() {
		return internalValue;
	}
}
