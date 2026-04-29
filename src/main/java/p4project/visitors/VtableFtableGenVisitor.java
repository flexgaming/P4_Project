package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.context.CompilationContext;   // <<< ADDED

/*
    Phase 1: Symbol assignments and declerations
    Phase 2: Reference linking
    Phase 3: Type checking
    -> Phase 4: vtable and ftable generation
    Phase 5: Java Code Gen
*/

public class VtableFtableGenVisitor extends OurGrammarBaseVisitor<Void> {

    private final CompilationContext ctx;

    public VtableFtableGenVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }

    // In a simple script, no functions or classes exist.
    // When they do, intercept them here to populate your vt/ftables:
    //
    // @Override
    // public Void visitFuncDec(OurGrammarParser.FuncDecContext tree) {
    //     ctx.ftable.addFunction(tree.ID().getText());
    //     return visitChildren(tree);
    // }
}