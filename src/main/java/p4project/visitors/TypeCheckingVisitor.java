package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;
import p4project.context.Symbol;

/*
    Phase 1: Symbol assignments and declerations
    Phase 2: Reference linking
    -> Phase 3: Type checking
    Phase 4: vtable and ftable generation
    Phase 5: Java Code Gen
*/

public class TypeCheckingVisitor extends OurGrammarBaseVisitor<String> {

    private final CompilationContext ctx;

    public TypeCheckingVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public String visitFactor(OurGrammarParser.FactorContext ctx) {
        if (ctx.INT() != null) {
            return "int";
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitAdditive(OurGrammarParser.AdditiveContext ctx) {
        String leftType = visit(ctx.mult());
        String rightType = visit(ctx.mult());
        if (!"int".equals(leftType) || !"int".equals(rightType)) {
            throw new RuntimeException("Type Error: '+' requires integer operands.");
        }
        return "int";
    }

    @Override
    public String visitAssignment(OurGrammarParser.AssignmentContext ctx) {
        String id = ctx.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id);

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        // FIXED: Use direct field access (TypeSymbol has public 'name' or 'type' field)
        String declaredType = symbol.type.name.toLowerCase();   // <<< CHANGED

        String exprType = visit(ctx.assVar().expr());

        if (!declaredType.equals(exprType)) {
            throw new RuntimeException("Type Error: Cannot assign " + exprType + " to " + declaredType);
        }
        return null;
    }
}
