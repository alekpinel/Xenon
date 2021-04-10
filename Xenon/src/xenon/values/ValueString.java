package xenon.values;

import java.util.Vector;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;

public class ValueString extends ValueAbstract {
	
	private String internalValue;
	
	/** Return a ValueString given a quote-delimited source string. */
	public static ValueString stripDelimited(String b) {
		return new ValueString(b.substring(1, b.length() - 1));
	}
	
	public ValueString(String b) {
		internalValue = b;
	}
	
	public String getName() {
		return "string";
	}
	
	public Value assign(Value v) {
		internalValue = v.stringValue();
		return this;
	}
	
	/** Convert this to a String. */
	public String stringValue() {
		return internalValue;		
	}

	public int compare(Value v) {
		return internalValue.compareTo(v.stringValue());
	}
	
	/** Return a copy of this value */
	public Value clone() {
		return new ValueString(internalValue);
	}
	
	/** Add performs string concatenation. */
	public Value add(Value v) {
		return new ValueString(internalValue + v.stringValue());
	}
	
	public String toString() {
		return internalValue;
	}
	
	/** Brackets operator */
	public Reference bracket(Vector<Reference> params) {
		if (params.size() != 1) {
			throw new ExceptionSemantic("Cannot perform " + getName() + "[] with " + params.size() + " parameters.");
		}
		
		int index = (int)params.get(0).getValue().longValue();
		
		if (index < 0 || index >= internalValue.length()) {
			throw new ExceptionSemantic("Index " + index + " out of range in \"" + toString() + "\"");
		}
		
		return new Reference(new ValueString(Character.toString(internalValue.charAt(index))));
	}
	
	//Casts
	
	/** Convert this to a primitive boolean. */
	public boolean booleanValue() {
		return internalValue != null && !internalValue.isEmpty();
	}

	/** Convert this to a primitive long. */
	public long longValue() {
		return Long.parseLong(internalValue);
	}

	/** Convert this to a primitive double. */
	public double doubleValue() {
		return Double.parseDouble(internalValue);
	}
	
	public Object mainValue() {
		return internalValue;
	}
}
