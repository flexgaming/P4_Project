// Generated from d:/Github Rep UNI/P4_Project/src/main/antlr4/p4project/OurGrammar.g4 by ANTLR 4.13.1
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
	 * Enter a parse tree produced by {@link OurGrammarParser#statementPrime}.
	 * @param ctx the parse tree
	 */
	void enterStatementPrime(OurGrammarParser.StatementPrimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#statementPrime}.
	 * @param ctx the parse tree
	 */
	void exitStatementPrime(OurGrammarParser.StatementPrimeContext ctx);
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
	 * Enter a parse tree produced by {@link OurGrammarParser#assVar}.
	 * @param ctx the parse tree
	 */
	void enterAssVar(OurGrammarParser.AssVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#assVar}.
	 * @param ctx the parse tree
	 */
	void exitAssVar(OurGrammarParser.AssVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#assFunc}.
	 * @param ctx the parse tree
	 */
	void enterAssFunc(OurGrammarParser.AssFuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#assFunc}.
	 * @param ctx the parse tree
	 */
	void exitAssFunc(OurGrammarParser.AssFuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#reassignment}.
	 * @param ctx the parse tree
	 */
	void enterReassignment(OurGrammarParser.ReassignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#reassignment}.
	 * @param ctx the parse tree
	 */
	void exitReassignment(OurGrammarParser.ReassignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(OurGrammarParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(OurGrammarParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#threadAssignment}.
	 * @param ctx the parse tree
	 */
	void enterThreadAssignment(OurGrammarParser.ThreadAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#threadAssignment}.
	 * @param ctx the parse tree
	 */
	void exitThreadAssignment(OurGrammarParser.ThreadAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#awaitStatement}.
	 * @param ctx the parse tree
	 */
	void enterAwaitStatement(OurGrammarParser.AwaitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#awaitStatement}.
	 * @param ctx the parse tree
	 */
	void exitAwaitStatement(OurGrammarParser.AwaitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(OurGrammarParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(OurGrammarParser.CastExpressionContext ctx);
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
	 * Enter a parse tree produced by {@link OurGrammarParser#forVar}.
	 * @param ctx the parse tree
	 */
	void enterForVar(OurGrammarParser.ForVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#forVar}.
	 * @param ctx the parse tree
	 */
	void exitForVar(OurGrammarParser.ForVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(OurGrammarParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(OurGrammarParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(OurGrammarParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(OurGrammarParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(OurGrammarParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(OurGrammarParser.BreakStatementContext ctx);
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
	 * Enter a parse tree produced by {@link OurGrammarParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(OurGrammarParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(OurGrammarParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#criticalSection}.
	 * @param ctx the parse tree
	 */
	void enterCriticalSection(OurGrammarParser.CriticalSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#criticalSection}.
	 * @param ctx the parse tree
	 */
	void exitCriticalSection(OurGrammarParser.CriticalSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(OurGrammarParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(OurGrammarParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link OurGrammarParser#read}.
	 * @param ctx the parse tree
	 */
	void enterRead(OurGrammarParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by {@link OurGrammarParser#read}.
	 * @param ctx the parse tree
	 */
	void exitRead(OurGrammarParser.ReadContext ctx);
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