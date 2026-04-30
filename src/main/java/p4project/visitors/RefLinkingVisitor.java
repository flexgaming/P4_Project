package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;
import p4project.context.Symbol;

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

    // ==================== Variable / Identifier References ====================

    @Override
    public Void visitAssignment(OurGrammarParser.AssignmentContext ctx) {
        String id = ctx.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        this.ctx.resolvedSymbols.put(ctx.ID(), symbol);
        return visitChildren(ctx);
    }

    @Override
    public Void visitReassignment(OurGrammarParser.ReassignmentContext ctx) {
        String id = ctx.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        this.ctx.resolvedSymbols.put(ctx.ID(), symbol);
        return visitChildren(ctx);
    }

    @Override
    public Void visitArrayIndex(OurGrammarParser.ArrayIndexContext ctx) {
        String id = ctx.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        this.ctx.resolvedSymbols.put(ctx.ID(), symbol);
        return visitChildren(ctx);
    }

    @Override
    public Void visitReadStatement(OurGrammarParser.ReadStatementContext ctx) {
        String id = ctx.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        this.ctx.resolvedSymbols.put(ctx.ID(), symbol);
        return visitChildren(ctx);
    }

    // ========================= FUNCTION CALLS =========================

    @Override
    public Void visitFunctionCall(OurGrammarParser.FunctionCallContext ctx) {
        String id = ctx.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)

        if (symbol == null) {
            throw new RuntimeException("Function '" + id + "' not declared.");
        }

        // TODO look up the function in the ftable and check if it is a function 
        // and if the arguments match the parameters

        this.ctx.resolvedSymbols.put(ctx.ID(), symbol);
        return visitChildren(ctx);
    }

    // ======================== Critical sections ========================

    @Override
    public Void visitCriticalSection(OurGrammarParser.CriticalSectionContext ctx) {
        // TODO check that all variables used inside the critical section that are declared as 'shared' are only mutated inside the critical section
        return visitChildren(ctx);
    }

    // ======================== For loops statements ========================

}

// ctx.resolvedSymbols skal skrives til. Husk det er et hashmap<String, Symbol> hvor key er navnet på symbolet og value er selve symbolet. Det skal fyldes ud i denne visitor, og så kan de andre visitors bruge det til at slå op i stedet for at skulle gøre det selv.