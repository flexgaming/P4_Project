// Generated from OurGrammar.g4 by ANTLR 4.13.1

package p4project;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link OurGrammarParser}.
 */
public interface OurGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(OurGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(OurGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(OurGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(OurGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(OurGrammarParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(OurGrammarParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void enterTypeRef(OurGrammarParser.TypeRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void exitTypeRef(OurGrammarParser.TypeRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(OurGrammarParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(OurGrammarParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(OurGrammarParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(OurGrammarParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(OurGrammarParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(OurGrammarParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(OurGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(OurGrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#equal}.
	 * @param ctx the parse tree
	 */
	void enterEqual(OurGrammarParser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#equal}.
	 * @param ctx the parse tree
	 */
	void exitEqual(OurGrammarParser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#comp}.
	 * @param ctx the parse tree
	 */
	void enterComp(OurGrammarParser.CompContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#comp}.
	 * @param ctx the parse tree
	 */
	void exitComp(OurGrammarParser.CompContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#additive}.
	 * @param ctx the parse tree
	 */
	void enterAdditive(OurGrammarParser.AdditiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#additive}.
	 * @param ctx the parse tree
	 */
	void exitAdditive(OurGrammarParser.AdditiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#mult}.
	 * @param ctx the parse tree
	 */
	void enterMult(OurGrammarParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#mult}.
	 * @param ctx the parse tree
	 */
	void exitMult(OurGrammarParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#power}.
	 * @param ctx the parse tree
	 */
	void enterPower(OurGrammarParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#power}.
	 * @param ctx the parse tree
	 */
	void exitPower(OurGrammarParser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(OurGrammarParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(OurGrammarParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#arrayIndex}.
	 * @param ctx the parse tree
	 */
	void enterArrayIndex(OurGrammarParser.ArrayIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#arrayIndex}.
	 * @param ctx the parse tree
	 */
	void exitArrayIndex(OurGrammarParser.ArrayIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(OurGrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(OurGrammarParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(OurGrammarParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(OurGrammarParser.FunctionCallContext ctx);
}