package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;
import p4project.context.FunctionSymbol;
import p4project.context.Symbol;

/*
    Phase 1: Symbol assignments and declerations
    Phase 2: Reference linking
    Phase 3: Type checking
    Phase 4: vtable and ftable generation
    -> Phase 5: Mutex and deep nested critical section checking
    Phase 6: Java Code Gen
*/

public class MutexVisitor extends OurGrammarBaseVisitor<Void> {

    private final CompilationContext ctx;

    private boolean inCriticalSection = false;

    public MutexVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }
    
    @Override
    public Void visitAssignment(OurGrammarParser.AssignmentContext context) {
        // Check if assignment is to a function, if so, check if we are in a critical section.
        if (context.assFunc() != null && inCriticalSection) {
            // -- Check if function contains critical section, if it does, we are doomed.. --
            if (this.ctx.ftable.get(context.assFunc().ID().toString()).containsCriticalSection) {
                throw new RuntimeException("Function '" + context.ID().getText() +
                    "' contains a critical section and cannot be called from another critical section.");
            }
        }
        return visitChildren(context);
    }

    @Override
    public Void visitCriticalSection(OurGrammarParser.CriticalSectionContext context) {
        // If already within a nested critical section, just visit the children without changing the flag.
        if (inCriticalSection) {
            return visitChildren(context);
        }

        inCriticalSection = true;
        visitChildren(context);
        inCriticalSection = false; // reset flag after visiting outermost critical section.
        return null;
    }

    @Override
    public Void visitFunctionCall(OurGrammarParser.FunctionCallContext context) {
        if (inCriticalSection) {
            // Check if the function being called contains shared variables. If it does, throw an error.
            String functionName = context.ID().getText();
            FunctionSymbol functionSymbol = this.ctx.ftable.get(functionName);

            /** 
             * In the future, we should instead check if the function contains critical sections,
             * but for now we will just check the parameters of the function being called.
             * Instead of throwing an error, it should be handlel like nested critical sections,
             * as is done in CodeGenVisitor, but for now we will just throw an error.
             */ 
            if (functionSymbol != null && functionSymbol.containsCriticalSection) {
                throw new RuntimeException("Function '" + functionName +
                "' contains a critical section and cannot be called within another critical section.");
            }
            System.out.println("Visited function call to '" + functionName + "' from within a critical section. " + functionName + " containsCriticalSection: " + functionSymbol.containsCriticalSection);
            visitAssignment(functionSymbol.context); // visit the function's context to check for nested critical sections.
            // THINGS ARE GETTING SPICY!!! 🌶️🌶️🌶️

        }
        return visitChildren(context);
    }   

    @Override
    public Void visitBlock(OurGrammarParser.BlockContext context) {
        this.ctx.symbolTable.restoreScope(context);
        Void result = visitChildren(context);
        this.ctx.symbolTable.popScope();
        return result;
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
        this.ctx.symbolTable.pushScope(context);
        visitChildren(context);
        this.ctx.symbolTable.popScope();
        return null;
    }
}