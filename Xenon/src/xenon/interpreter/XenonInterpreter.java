package xenon.interpreter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

import xenon.parser.ast.ASTCode;
import xenon.parser.ast.Xenon;
import xenon.parser.ast.XenonVisitor;
import xenon.values.*;

public class XenonInterpreter {
	String codepath;
	Vector<Reference> parameters;
	Parser parser;

	public XenonInterpreter(String codepath) {
		this.codepath = codepath;
		parameters = new Vector<Reference>();
		Run();
	}
	
	public void Run() {
		FileInputStream is;
		try {
			is = new FileInputStream(new File(codepath));
			Xenon language = new Xenon(new BufferedInputStream(is));
			
			ASTCode ast = language.code();
			parser = new Parser();
			ast.jjtAccept(parser, null);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public Object RunFunction(String functionname) {
		Function function = parser.getDisplay().findFunction(functionname, parameters.size());
		Reference result = parser.getDisplay().InvokeFunction(function, parameters, parser);
		parameters.clear();
		if (result.getValue() != null) {
			return result.getValue().mainValue();
		}
		
		return null;
	}
	
	public void AddParameter(String type, Object value) {
		parameters.add(new Reference(CreateValue(type, value)));
	}
	
	public Value CreateValue(String type, Object value) {
		Value val = null;
		switch(type) {
		case "int":
		case "long":
			val = new ValueInteger((long) value);
			break;
		case "float":
		case "double":
			val = new ValueRational((double) value);
			break;
		case "String":
			val = new ValueString((String) value);
			break;
		case "Boolean":
			val = new ValueBoolean((Boolean) value);
			break;
		default:
			throw new ExceptionSemantic("The only types accepted are: int, float, String, Boolean");
		}
		return val;
	}
}
