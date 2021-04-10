package xenon.system;

import java.util.Vector;

import xenon.interpreter.*;
import xenon.interpreter.Class;
import xenon.system.functions.*;
import xenon.system.PriorityQueue.SystemPriorityQueue;
import xenon.system.classes.*;
import xenon.system.classes.file.SystemFile;
import xenon.system.classes.map.SystemMap;
import xenon.system.classes.object.SystemObject;
import xenon.system.classes.queue.SystemQueue;
import xenon.system.classes.randomqueue.SystemRandomQueue;
import xenon.system.classes.stack.SystemStack;
import xenon.system.classes.vector.SystemVector;
import xenon.values.Reference;
import xenon.values.*;

public class SystemInitializer {
	
	public static Scope globalscope;
	
	public static void DefineSystemFunction(Display display, SystemCode function) {
		SystemFunction systemfunction = new SystemFunction(function.name, display.getScope(), function, function.params);
		display.defineNewFunction(systemfunction);
	}
	
	public static void DefineSystemClass(Display display, SystemClassExtension classextension) {
		SystemClass objectclass = new SystemClass(display.getScope(), classextension);
		display.defineNewClass(objectclass);
	}
	
	public static void DefineSystemClass(Display display, SystemClassExtension classextension, Class father) {
		Vector<Class> sup = new Vector<Class>();
		sup.add(father);
		SystemClass objectclass = new SystemClass(classextension.name, display.getScope(), classextension, sup);
		display.defineNewClass(objectclass);
	}
	
	public static void DefineSystemVar(Display display, String name, Value value) {
		display.defineVariable(name, value);
	}
	
	public static void InitializeSystem(Display display) {
		globalscope = display.getGlobal();
		
		InitializeBaseClass(display);
		InitializeSystemVariables(display);
		InitializeSystemFunctions(display);
		InitializeSystemClasses(display);
	}
	
	public static void InitializeBaseClass(Display display) {
		//Create the base class
		SystemClass objectclass = new SystemClass(display.getScope(), new SystemObject());
		display.defineNewClass(objectclass);
		
		Class.OBJECTCLASS = objectclass;
	}
	
	public static void InitializeSystemClasses(Display display) {
		
		DefineSystemClass(display, new SystemVector());
		DefineSystemClass(display, new SystemFile());
		DefineSystemClass(display, new SystemMap());
		DefineSystemClass(display, new SystemQueue());
		DefineSystemClass(display, new SystemRandomQueue(), GetClass(display, "queue"));
		DefineSystemClass(display, new SystemStack(), GetClass(display, "queue"));
		DefineSystemClass(display, new SystemPriorityQueue(), GetClass(display, "queue"));
	}
	
	public static void InitializeSystemFunctions(Display display) {
		//List of all system functions
		
		//Input-output
		DefineSystemFunction(display, new SystemPrint());
		DefineSystemFunction(display, new SystemInput());
		
		//Casts
		DefineSystemFunction(display, new SystemInt());
		DefineSystemFunction(display, new SystemFloat());
		DefineSystemFunction(display, new SystemBool());
		DefineSystemFunction(display, new SystemString());
		
		//Get Functions
		DefineSystemFunction(display, new SystemFunction1());
		DefineSystemFunction(display, new SystemFunction2());
		
		//Vector helpers
		DefineSystemFunction(display, new SystemRange1());
		DefineSystemFunction(display, new SystemRange2());
		
		//String Functions
		DefineSystemFunction(display, new SystemLen1());
		DefineSystemFunction(display, new SystemSplit());
		
		//Random Values
		DefineSystemFunction(display, new SystemRand1());
		DefineSystemFunction(display, new SystemRand2());
		DefineSystemFunction(display, new SystemIRand1());
		DefineSystemFunction(display, new SystemIRand2());
		
		//Maths
		DefineSystemFunction(display, new SystemSqrt());
		DefineSystemFunction(display, new SystemAbs1());
		DefineSystemFunction(display, new SystemSin1());
		DefineSystemFunction(display, new SystemCos1());
		DefineSystemFunction(display, new SystemTan1());
		DefineSystemFunction(display, new SystemPow2());
		
		//Vectors
		DefineSystemFunction(display, new SystemDotProduct2());
		DefineSystemFunction(display, new SystemCrossProduct2());
		DefineSystemFunction(display, new SystemMatrixVectorProduct2());
		DefineSystemFunction(display, new SystemVectorAdd2());
		DefineSystemFunction(display, new SystemVectorSubtract2());
		DefineSystemFunction(display, new SystemVectorScale2());
		DefineSystemFunction(display, new SystemVectorMult2());
				
		//Quit
		DefineSystemFunction(display, new SystemQuit());
		
		//Debug
		DefineSystemFunction(display, new SystemDebug());
		
	}
	
	public static void InitializeSystemVariables(Display display) {
		DefineSystemVar(display, "newline", new ValueString(System.lineSeparator()));
		DefineSystemVar(display, "constPI", new ValueRational(Math.PI));
		DefineSystemVar(display, "constE", new ValueRational(Math.E));
	}
	
	public static Class GetClass(Display display, String name) {
		return display.getReference(name).getValue().classValue();
	}
	
	//Help function to create a new vector
	public static Reference CreateNewVector(Vector<Reference> newvector) {
		SystemVector function = new SystemVector();
		function.vector = newvector;
		SystemClass objectclass = new SystemClass(globalscope, function);
		return new Reference(new ValueClass(objectclass));
	}
	
}
