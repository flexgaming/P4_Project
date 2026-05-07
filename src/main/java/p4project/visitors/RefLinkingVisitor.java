package p4project.visitors;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

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

    @Override
    public Void visitAssignment(OurGrammarParser.AssignmentContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)
        

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        this.ctx.resolvedSymbols.put(context.ID(), symbol);
        return visitChildren(context);
    }

    @Override
    public Void visitReassignment(OurGrammarParser.ReassignmentContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        this.ctx.resolvedSymbols.put(context.ID(), symbol);
        return visitChildren(context);
    }

    @Override
    public Void visitArrayIndex(OurGrammarParser.ArrayIndexContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        this.ctx.resolvedSymbols.put(context.ID(), symbol);
        return visitChildren(context);
    }

    @Override
    public Void visitRead(OurGrammarParser.ReadContext context) {
        return visitChildren(context);
    }

    @Override
    public Void visitBlock(OurGrammarParser.BlockContext context) {
        this.ctx.symbolTable.restoreScope(context);
        visitChildren(context);
        this.ctx.symbolTable.popScope();
        return null;
    }

    @Override
    public Void visitFunctionCall(OurGrammarParser.FunctionCallContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)

        if (symbol == null) {
            throw new RuntimeException("Function '" + id + "' not declared.");
        }

        this.ctx.resolvedSymbols.put(context.ID(), symbol);
        return visitChildren(context);
    }

    @Override
    public Void visitFactor(OurGrammarParser.FactorContext context) {
        if (context.ID() != null) {
            String id = context.ID().getText();
            Symbol symbol = this.ctx.symbolTable.resolve(id);

            if (symbol == null) {
                throw new RuntimeException("Variable '" + id + "' not declared.");
            }
            this.ctx.resolvedSymbols.put(context.ID(), symbol);
        }
        return visitChildren(context);
    }

    @Override
    public Void visitCriticalSection(OurGrammarParser.CriticalSectionContext context) {
        this.ctx.symbolTable.restoreScope(context);
        List<TerminalNode> sharedVars = context.ID().stream()
            .filter(id -> this.ctx.sharedVariables.contains(id.getText()))
            .toList();
        for (var id : sharedVars) {
            this.ctx.resolvedSymbols.put(id, this.ctx.symbolTable.resolve(id.getText()));
        }
        visitChildren(context);
        this.ctx.symbolTable.popScope();
        return null;
    }
    
    @Override
    public Void visitIfStatement(OurGrammarParser.IfStatementContext context) {
        for (OurGrammarParser.ExprContext e : context.getRuleContexts(OurGrammarParser.ExprContext.class)) {
            visit(e);
        }

        // If the statement is a block the visitBlock will create a new scope, otherwise no new scope is needed.
        for (OurGrammarParser.StatementContext s : context.statement()) {
            visit(s);
        }

        return null;
    }

    @Override
    public Void visitForStatement(OurGrammarParser.ForStatementContext context) {
        this.ctx.symbolTable.restoreScope(context);
        visitChildren(context);
        this.ctx.symbolTable.popScope();
        return null;
    }

    @Override
    public Void visitWhileStatement(OurGrammarParser.WhileStatementContext context) {
        this.ctx.symbolTable.restoreScope(context);
        visitChildren(context);
        this.ctx.symbolTable.popScope();
        return null;
    }

    @Override
    public Void visitBreakStatement(OurGrammarParser.BreakStatementContext context) {
        return visitChildren(context);
    }

    @Override
    public Void visitContinueStatement(OurGrammarParser.ContinueStatementContext context) {
        return visitChildren(context);
    }
}

// ctx.resolvedSymbols skal skrives til. Husk det er et hashmap<String, Symbol> hvor key er navnet på symbolet og value er selve symbolet. Det skal fyldes ud i denne visitor, og så kan de andre visitors bruge det til at slå op i stedet for at skulle gøre det selv.