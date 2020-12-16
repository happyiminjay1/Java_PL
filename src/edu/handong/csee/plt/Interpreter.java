package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.ast.Add;
import edu.handong.csee.plt.ast.Num;
import edu.handong.csee.plt.ast.Sub;
import edu.handong.csee.plt.ast.Id;
import edu.handong.csee.plt.ast.Fun;
import edu.handong.csee.plt.ast.App;

public class Interpreter {

	public AST interp(AST ast) {
		
		if(ast instanceof Num) {
			return (Num)ast;
		}
		
		if(ast instanceof Id) {
			System.err.print("'"+((Id)ast).getStrId()+"'"+" is a free identifier");
			System.exit(0);
		}
		
		if(ast instanceof Add) {
			Add add = (Add)ast;
			Num lh  = (Num)interp(add.getLhs());
			Num rh  = (Num)interp(add.getRhs());
			return new Num(
					Integer.toString(
							lh.getIntNum() + rh.getIntNum()
					));
		}
		
		if(ast instanceof Sub) {
			Sub sub = (Sub)ast;
			Num lh  = (Num)interp(sub.getLhs());
			Num rh  = (Num)interp(sub.getRhs());
			return new Num(
					Integer.toString(
							lh.getIntNum() - rh.getIntNum()
					));
		}
		
		
		if(ast instanceof Fun) {
			return ast;
		}
		
		if(ast instanceof App) {
			App app = (App)ast;
			Fun ftn = (Fun)interp(app.getftn());
			Subst st = new Subst();
			return interp(st.subst(ftn.getBody(),ftn.getParam(),interp(app.getarg())));
		}

		return null;
	}
	
	
}
