package xenon.values;

import xenon.interpreter.Class;
import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Function;

public class ValueFunction extends ValueScope {
	
	public ValueFunction(Function functdef) {
		this.instance = functdef;
	}
	
	@Override
	public Value clone() {
		return new ValueFunction(((Function) instance).Instantiate());
	}
}
