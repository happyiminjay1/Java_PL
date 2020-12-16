package edu.handong.csee.plt;

import edu.handong.csee.plt.ast.AST;
import edu.handong.csee.plt.ast.Add;
import edu.handong.csee.plt.ast.Sub;
import edu.handong.csee.plt.ast.Num;
import edu.handong.csee.plt.ast.Id;
import edu.handong.csee.plt.ast.Fun;
import edu.handong.csee.plt.ast.App;

public class Subst {

	public AST subst(AST ast, String idtf, AST val) {
		
		if(ast instanceof Num) {
			return ast;
		}
		
		if(ast instanceof Add) {
			Add add = (Add)ast;
			return new Add(
					subst(add.getLhs(),idtf,val),
					subst(add.getRhs(),idtf,val)
			);
		}
		
		if(ast instanceof Sub) {
			Sub sub = (Sub)ast;
			return new Sub(
					subst(sub.getLhs(),idtf,val),
					subst(sub.getRhs(),idtf,val)
			);
		}
		
		if(ast instanceof App) {
			App app = (App)ast;
			return new App(
					subst(app.getftn(),idtf,val),
					subst(app.getarg(),idtf,val)
			);
		}
		
		if(ast instanceof Id) {
			if(((Id)ast).getStrId().equals(idtf))
			{
				return val;
			}
			return ((Id)ast);
		}
		
		if(ast instanceof Fun) {
			Fun fun = (Fun)ast;
			if(((Fun)ast).getParam().equals(idtf))
			{
				return ast;
			}
			return new Fun(fun.getParam(),subst(fun.getBody(),idtf,val));
		}
		
		
		return null;
	}

}
