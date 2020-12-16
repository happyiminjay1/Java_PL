package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.AST;

public class Main {
	
	static boolean onlyParser = false; // for -p option
	
	public static void main(String[] args) {
		
		// This is just an example code. Use args to get -p option and actuall code from CLI
		String exampleCode ="{+ x 2}";
		//{with {x {+ 1 2}} {+ x {- 2 x}}}
		//{with {f {fun {y} {+ 3 y}}} {f 4}}
		// Parser
		Parser parser = new Parser();
		AST ast = parser.parse(exampleCode);
		
		if(ast == null)
			System.out.println("Syntax Error!");
		
		//if(onlyParser)
			System.out.println(ast.getASTCode());
		
		// interpreter
			
		Interpreter interpreter = new Interpreter();
		
		AST result = interpreter.interp(ast);
		
		System.out.println("result : " + result.getASTCode());
	}
}
