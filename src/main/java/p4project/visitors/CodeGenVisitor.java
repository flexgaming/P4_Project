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
    public String visitDeclaration(OurGrammarParser.DeclarationContext ctx) {
        return ctx.typeRef().TYPE().getText() + " " + ctx.ID().getText() + ";\n";
    }

    @Override
    public String visitAssignment(OurGrammarParser.AssignmentContext ctx) {
        return ctx.ID().getText() + " = " + visit(ctx.assVar().expr()) + ";\n";
    }

    @Override
    public String visitAdditive(OurGrammarParser.AdditiveContext ctx) {
        String left = visit(ctx.mult());
        if (ctx.additive() != null) {
            String op = ctx.getChild(1).getText();  // The operator
            String right = visit(ctx.additive());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitFactor(OurGrammarParser.FactorContext ctx) {
        if (ctx.INT() != null) {
            return ctx.INT().getText();
        }
        return visitChildren(ctx);
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null) return nextResult;
        if (nextResult == null) return aggregate;
        return aggregate + nextResult;
    }
}