package xenon.system.functions;

import java.util.Arrays;
import java.util.Vector;
import java.util.Scanner;

import xenon.interpreter.ExceptionSemantic;
import xenon.interpreter.Scope;
import xenon.system.SystemCode;
import xenon.values.*;

public class SystemInput extends SystemCode {
	
	public static Scanner scanner = new Scanner(System.in);
	
	public SystemInput() {
		name = "input";
		params = new Vector<String>();
	}

	@Override
	public Reference Invoke(Scope scope) {
		
		String input = scanner.nextLine();
		
		return new Reference(new ValueString(input));
	}
}
