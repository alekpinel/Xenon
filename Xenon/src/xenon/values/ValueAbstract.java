package xenon.values;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.interpreter.Function;

import java.util.Vector;

import xenon.interpreter.Class;

public abstract class ValueAbstract implements Value {

	public abstract String getName();

	public abstract int compare(Value v);
	
	public abstract Value assign(Value v);
	
	public abstract Value clone();
	
	public Value or(Value v) {
		//throw new ExceptionSemantic("Cannot perform OR on " + getName() + " and " + v.getName());
		return new ValueBoolean(booleanValue() || v.booleanValue());
	}

	public Value and(Value v) {
		//throw new ExceptionSemantic("Cannot perform AND on " + getName() + " and " + v.getName());
		return new ValueBoolean(booleanValue() && v.booleanValue());
	}

	public Value not() {
		throw new ExceptionSemantic("Cannot perform NOT on " + getName());
	}

	public Value add(Value v) {
		throw new ExceptionSemantic("Cannot perform + on " + getName() + " and " + v.getName());
	}

	public Value subtract(Value v) {
		throw new ExceptionSemantic("Cannot perform - on " + getName() + " and " + v.getName());
	}

	public Value mult(Value v) {
		throw new ExceptionSemantic("Cannot perform * on " + getName() + " and " + v.getName());
	}

	public Value div(Value v) {
		throw new ExceptionSemantic("Cannot perform / on " + getName() + " and " + v.getName());
	}
	
	public Value mod(Value v) {
		throw new ExceptionSemantic("Cannot perform % on " + getName() + " and " + v.getName());
	}
	
	public Reference bracket(Vector<Reference> params) {
		throw new ExceptionSemantic("Cannot perform " + getName() + "[" + "]");
	}

	public Value unary_plus() {
		throw new ExceptionSemantic("Cannot perform + on " + getName());
	}

	public Value unary_minus() {
		throw new ExceptionSemantic("Cannot perform - on " + getName());
	}
		
	/** Convert this to a primitive boolean. */
	public boolean booleanValue() {
		throw new ExceptionSemantic("Cannot convert " + getName() + " to boolean.");
	}

	/** Convert this to a primitive long. */
	public long longValue() {
		throw new ExceptionSemantic("Cannot convert " + getName() + " to integer.");
	}

	/** Convert this to a primitive double. */
	public double doubleValue() {
		throw new ExceptionSemantic("Cannot convert " + getName() + " to rational.");
	}

	/** Convert this to a primitive string. */
	public String stringValue() {
		throw new ExceptionSemantic("Cannot convert " + getName() + " to string.");
	}
	
	/** Convert this to a primitive instance. */
	public Scope scopeValue() {
		throw new ExceptionSemantic(getName() + " is not an scope.");
	}
	
	/** Convert this to a primitive class definition. */
	public Class classValue() {
		throw new ExceptionSemantic(getName() + " is not an object.");
	}
	
	/** Convert this to a primitive function definition. */
	public Function functionValue() {
		throw new ExceptionSemantic(getName() + " is not a function.");
	}

	/** Test this value and another for equality. */
	public Value eq(Value v) {
		return new ValueBoolean(compare(v) == 0);
	}
	
	/** Test this value and another for non-equality. */
	public Value neq(Value v) {
		return new ValueBoolean(compare(v) != 0);
	}
	
	/** Test this value and another for >= */
	public Value gte(Value v) {
		return new ValueBoolean(compare(v) >= 0);
	}
	
	/** Test this value and another for <= */
	public Value lte(Value v) {
		return new ValueBoolean(compare(v) <= 0);
	}
	
	/** Test this value and another for > */
	public Value gt(Value v) {
		return new ValueBoolean(compare(v) > 0);
	}
	
	/** Test this value and another for < */	
	public Value lt(Value v) {
		return new ValueBoolean(compare(v) < 0);
	}
	
	
	@Override
    public int hashCode() {
        final int prime = 31;
        long result = 1;
        result =  prime * result + mainValue().hashCode();  
        return (int) result;
    }
}
