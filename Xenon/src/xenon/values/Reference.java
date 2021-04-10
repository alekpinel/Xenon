package xenon.values;

public class Reference implements Comparable {
	protected Value value;
	
	public Reference(){
		value = null;
	}
	
	public Reference(Value val){
		value = val;
	}
	
	public Reference SetValue(Value val) {
		value = val;
		return this;
	}
	
	public Reference AssignValue(Value val) {
		if (value == null) {
			SetValue(val);
		}
		else {
			value.assign(val);
		}
		
		return this;
	}
	
	public Value getValue() {
		if (value == null) {
			return new ValueNull();
		}
		return value;
	}
	
	public String toString() {
		if (value != null)
			return value.toString();
		else
			return "null";
	}
	
	public Reference clone() {
		return new Reference(getValue().clone());
	}
	
	public Reference refclone() {
		return new Reference(getValue());
	}
	
	@Override 
	public boolean equals(Object o) {
        return (o instanceof Reference) && (getValue().compare(((Reference) o).getValue())) == 0;
    }
	
	@Override
    public int hashCode() {
        return value.hashCode();
    }
	
	/** Compare this value and another. */
	public int compare(Reference v) {
		return getValue().compare(v.getValue());
	}

	@Override
	public int compareTo(Object o) {
		return compare((Reference) o);
	}
}
