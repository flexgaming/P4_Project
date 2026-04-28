package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;

/*
    Phase 1: Symbol assignments and declerations
    Phase 2: Reference linking
    Phase 3: Type checking
    Phase 4: vtable and ftable generation
    -> Phase 5: Java Code Gen
*/

public class CodeGenVisitor extends OurGrammarBaseVisitor<String> {
    
    @Override
    public String visitVarDec(OurGrammarParser.VarDecContext ctx) {
        return ctx.TYPE().getText() + " " + ctx.ID().getText() + ";\n";
    }

    @Override
    public String visitAssign(OurGrammarParser.AssignContext ctx) {
        return ctx.ID().getText() + " = " + visit(ctx.expr()) + ";\n";
    }

    @Override
    public String visitAddExpr(OurGrammarParser.AddExprContext ctx) {
        return visit(ctx.expr(0)) + " + " + visit(ctx.expr(1));
    }

    @Override
    public String visitIntLiteral(OurGrammarParser.IntLiteralContext ctx) {
        return ctx.getText();
    }
    
    // Aggregator to combine multiple strings automatically 
    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null) return nextResult;
        if (nextResult == null) return aggregate;
        return aggregate + nextResult;
    }
}
