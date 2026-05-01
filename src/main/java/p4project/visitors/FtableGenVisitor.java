package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.context.CompilationContext;

/*
    Phase 1: Symbol assignments and declerations
    Phase 2: Reference linking
    Phase 3: Type checking
    -> Phase 4: vtable and ftable generation
    Phase 5: Java Code Gen
*/

public class FtableGenVisitor extends OurGrammarBaseVisitor<Void> {
    private final CompilationContext ctx;

    public FtableGenVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }

    /*
    // Visit function declarations and get function names, types and parameter types to populate the function table (ftable).
    @Override
    public Void visitFuncDec(p4project.OurGrammarParser.FuncDecContext ctx) {
        String funcName = ctx.ID().getText();
        String returnType = ctx.typeRef().TYPE().getText();

        // Get parameter types
        var params = ctx.paramList() != null ? ctx.paramList().param() : null;
        String[] paramTypes = new String[params != null ? params.size() : 0];
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                paramTypes[i] = params.get(i).typeRef().TYPE().getText();
            }
        }

        // Add function to the function table
        this.ctx.ftable.addFunction(funcName, returnType, paramTypes);
        return visitChildren(ctx);
    }
    */
    


    // In a simple script, no functions or classes exist.
    // When they do, intercept them here to populate your vt/ftables:
    //
    // @Override
    // public Void visitFuncDec(OurGrammarParser.FuncDecContext tree) {
    //     ctx.ftable.addFunction(tree.ID().getText());
    //     return visitChildren(tree);
    // }
}