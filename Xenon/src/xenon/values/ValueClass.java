package xenon.values;

import java.util.Vector;

import xenon.interpreter.Class;
import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;

public class ValueClass extends ValueScope {
	
	public ValueClass (Class classdef) {
		this.instance = classdef;
	}

	@Override
	public String getName() {
		return instance.getName();
	}
	
	@Override
	public String stringValue() {
		return classValue().InvokeMethod(Function.TOSTRING).getValue().stringValue();
	}
	
	@Override
	public Value clone() {
		return classValue().InvokeMethod(Function.CLONE).getValue();
	}
	
	@Override
	public Reference bracket(Vector<Reference> params) {
		return classValue().InvokeMethod(Function.BRACKETS, params);
	}
	
	@Override
	public int compare(Value v) {
		Vector<Reference> params = new Vector<Reference>();
		params.add(new Reference(v));
		return (int) classValue().InvokeMethod(Function.COMPARE, params).getValue().longValue();
	}
}
