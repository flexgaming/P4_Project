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
    public String visitProgram(OurGrammarParser.ProgramContext ctx) {
        StringBuilder result = new StringBuilder();
        for (OurGrammarParser.StatementContext stmt : ctx.statement()) {
            String stmtCode = visit(stmt);
            if (stmtCode != null && !stmtCode.isEmpty()) {
                result.append(stmtCode);
            }
        }
        return result.toString();
    }

    @Override
    public String visitStatement(OurGrammarParser.StatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitDeclaration(OurGrammarParser.DeclarationContext ctx) {
        return ctx.typeRef().TYPE().getText() + " " + ctx.ID().getText() + ";\n";
    }

    @Override
    public String visitAssignment(OurGrammarParser.AssignmentContext ctx) {
        return ctx.typeRef().TYPE().getText() + " " + ctx.ID().getText() + " = " + visit(ctx.assVar().expr()) + ";\n";
    }
    
    @Override
    public String visitReassignment(OurGrammarParser.ReassignmentContext ctx) {
        return ctx.ID().getText() + " = " + visit(ctx.expr()) + ";\n";
    }

    @Override
    public String visitAdditive(OurGrammarParser.AdditiveContext ctx) {
        String left = visit(ctx.mult());
        if (ctx.additive() != null) {
            String op = ctx.getChild(1).getText();
            String right = visit(ctx.additive());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitMult(OurGrammarParser.MultContext ctx) {
        String left = visit(ctx.power());
        if (ctx.mult() != null) {
            String op = ctx.getChild(1).getText();
            String right = visit(ctx.mult());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitPower(OurGrammarParser.PowerContext ctx) {
        String left = visit(ctx.factor());
        if (ctx.power() != null) {
            String op = ctx.getChild(1).getText();
            String right = visit(ctx.power());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitArrayLiteral(OurGrammarParser.ArrayLiteralContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < ctx.expr().size(); i++) {
            sb.append(visit(ctx.expr(i)));
            if (i < ctx.expr().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String visitArrayIndex(OurGrammarParser.ArrayIndexContext ctx) {
        String base = visit(ctx.expr(0));
        String index = visit(ctx.expr(1));
        return base + "[" + index + "]";
    }

    @Override
    public String visitFactor(OurGrammarParser.FactorContext ctx) {
        switch (ctx.getStart().getType()) {
            case OurGrammarParser.NEGATIVE:
                return "-" + visit(ctx.factor());
            case OurGrammarParser.INT:
                return ctx.INT().getText();
            case OurGrammarParser.FLOAT:
                return ctx.FLOAT().getText();
            case OurGrammarParser.BOOL:
                return ctx.BOOL().getText();
            case OurGrammarParser.CHAR:
                return ctx.CHAR().getText();
            case OurGrammarParser.STRING:
                return ctx.STRING().getText();
            case OurGrammarParser.THREAD:
                return ctx.THREAD().getText();
            case OurGrammarParser.ID:
                return ctx.ID().getText();
            default:
                return visitChildren(ctx);
        }
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null) return nextResult;
        if (nextResult == null) return aggregate;
        return aggregate + nextResult;
    }
}

