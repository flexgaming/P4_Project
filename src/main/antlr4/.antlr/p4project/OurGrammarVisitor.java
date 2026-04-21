// Generated from d:/Projecter/P4_Project/src/main/antlr4/OurGrammar.g4 by ANTLR 4.13.1

package p4project;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link OurGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface OurGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(OurGrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(OurGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(OurGrammarParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#reassignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReassignment(OurGrammarParser.ReassignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(OurGrammarParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#threadAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThreadAssignment(OurGrammarParser.ThreadAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#awaitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAwaitStatement(OurGrammarParser.AwaitStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(OurGrammarParser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#typeRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeRef(OurGrammarParser.TypeRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(OurGrammarParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(OurGrammarParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(OurGrammarParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(OurGrammarParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(OurGrammarParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(OurGrammarParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(OurGrammarParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#criticalSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCriticalSection(OurGrammarParser.CriticalSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(OurGrammarParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#readStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStatement(OurGrammarParser.ReadStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(OurGrammarParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#equal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(OurGrammarParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp(OurGrammarParser.CompContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#additive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive(OurGrammarParser.AdditiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#mult}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(OurGrammarParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#power}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(OurGrammarParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(OurGrammarParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#arrayIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayIndex(OurGrammarParser.ArrayIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(OurGrammarParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link OurGrammarParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(OurGrammarParser.FunctionCallContext ctx);
}