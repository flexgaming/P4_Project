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

public class RefLinkingVisitor extends OurGrammarBaseVisitor<Void> {
    private final CompilationContext ctx;

    public RefLinkingVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Void visitAssign(OurGrammarParser.AssignContext tree) {
        String id = tree.ID().getText();
        // Check if variable 'x' was declared in Phase 1
        if (ctx.symbolTable.resolve(id) == null) {
            throw new RuntimeException("Reference Error: Variable '" + id + "' is not declared.");
        }
        return visitChildren(tree);
    }
}
