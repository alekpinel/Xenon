package xenon.interpreter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import xenon.parser.ast.ASTCode;
import xenon.parser.ast.Xenon;
import xenon.parser.ast.XenonVisitor;
import xenon.interpreter.Parser;

public class Interpreter {
	
	private static void usage() {
		System.out.println("Usage: xenon [-d1] <source>");
		System.out.println("          -d1 -- output AST");
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		boolean debugAST = false;
		String testinput = "";
		if (args.length == 2) {
			testinput = args[1];
			if (args[0].equals("-d1"))
				debugAST = true;
			else {
				usage();
				return;
			}
		}
		else if (args.length ==1) {
			testinput = args[0];
		}
		else{
			usage();
			return;
		}
		
		//Xenon language = new Xenon(System.in);
		
		
		//String testinput = "Test_Sorting.xen";
		//String testinput = "Test_Cards.xen";
		//String testinput = "Test_GameOfLife.xen";
		//String testinput = "Test_CesarCode.xen";
		//String testinput = "Test_NeuralNetwork.xen";
		//String testinput = "test.xen";
		//debugAST = false;
		FileInputStream is = new FileInputStream(new File(testinput));
		Xenon language = new Xenon(new BufferedInputStream(is));
		
		
		try {
			ASTCode parser = language.code();
			XenonVisitor nodeVisitor;
			if (debugAST)
				nodeVisitor = new ParserDebugger();
			else
				nodeVisitor = new Parser();
			parser.jjtAccept(nodeVisitor, null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
