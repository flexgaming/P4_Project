package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;

/*
    Phase 1: Symbol assignments and declerations
    -> Phase 2: Reference linking
    Phase 3: Type checking
    Phase 4: vtable and ftable generation
    Phase 5: Java Code Gen
*/

public class TypeResolverVisitor extends OurGrammarBaseVisitor<Void> {

    private final CompilationContext ctx;

    public TypeResolverVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Void visitDeclaration(OurGrammarParser.DeclarationContext ctx) {
        String id = ctx.ID().getText();
        String typeStr = ctx.typeRef().TYPE().getText();

        this.ctx.symbolTable.define(new p4project.context.Symbol(id, 
            p4project.context.TypeSymbol.fromString(typeStr)));

        return visitChildren(ctx);
    }

    @Override
    public Void visitAssignment(OurGrammarParser.AssignmentContext ctx) {
        String id = ctx.ID().getText();
        p4project.context.Symbol symbol = this.ctx.symbolTable.resolve(id);

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        return visitChildren(ctx);
    }
}