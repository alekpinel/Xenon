package test;

import xenon.interpreter.XenonInterpreter;

public class Test {

	public static void main(String[] args) {
		System.out.println("Starting test of the embedded interpreter.");
		System.out.println("In this code, Xenon code and java code will work together.");
		
		XenonInterpreter xenon = new XenonInterpreter("./Examples/Test4_CesarCode.xen");
		
		System.out.println("First, we get the input in Xenon:");
		xenon.AddParameter("String", "Introduce a sentence to encode");
		String sentence = (String) xenon.RunFunction("getInput");
		
		System.out.println("Now, we use java to turn to lowercase the input");
		sentence = sentence.toLowerCase();
		System.out.println("New sentence: " + sentence);
		
		System.out.println("In xenon code, we use the cesar code to encode the sentence: ");
		xenon.AddParameter("String", sentence);
		String code = (String) xenon.RunFunction("encode");
		System.out.println("Code: " + code);
		
		System.out.println("And again, Xenon code will decode the message: ");
		xenon.AddParameter("String", code);
		String decoded = (String) xenon.RunFunction("decode");
		System.out.println("Decoded: " + decoded);
	}

}
