package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;
import p4project.context.FunctionSymbol;
import p4project.context.Symbol;
import p4project.context.TypeSymbol;
import p4project.context.VariableSymbol;

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

    @Override
    public Void visitAssignment(OurGrammarParser.AssignmentContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id);

        if (context.assFunc() != null) {
            if (!(symbol instanceof FunctionSymbol functionSymbol)) {
                throw new RuntimeException("'" + id + "' is not a function declaration.");
            }

            functionSymbol.declaredAtDepth = this.ctx.symbolTable.getDepth();
            functionSymbol.parameters.clear();

            var parameterTypes = context.assFunc().typeRef();
            var parameterNames = context.assFunc().ID();
            for (int i = 0; i < parameterNames.size(); i++) {
                String parameterName = parameterNames.get(i).getText();
                String parameterType = parameterTypes.get(i).TYPE().getText();
                functionSymbol.parameters.add(new VariableSymbol(parameterName, TypeSymbol.fromString(parameterType)));
            }

            this.ctx.ftable.putIfAbsent(id, functionSymbol);
            return visitChildren(context);
        }
        return visitChildren(context);
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