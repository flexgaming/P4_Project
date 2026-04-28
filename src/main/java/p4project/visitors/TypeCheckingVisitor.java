package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;

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
    public String visitIntLiteral(OurGrammarParser.IntLiteralContext tree) {
        return "int"; // 1 is always an integer
    }

    @Override
    public String visitAddExpr(OurGrammarParser.AddExprContext tree) {
        String leftType = visit(tree.expr(0));
        String rightType = visit(tree.expr(1));
        
        if (!"int".equals(leftType) || !"int".equals(rightType)) {
            throw new RuntimeException("Type Error: '+' requires integer operands.");
        }
        return "int"; // The result of int + int is an int
    }

    @Override
    public String visitAssign(OurGrammarParser.AssignContext tree) {
        String id = tree.ID().getText();
        String declaredType = ctx.symbolTable.resolve(id).getType();
        String exprType = visit(tree.expr());
        
        if (!declaredType.equals(exprType)) {
            throw new RuntimeException("Type Error: Cannot assign " + exprType + " to " + declaredType);
        }
        return null;
    }
}
