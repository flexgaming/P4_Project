package p4project.visitors;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.antlr.v4.runtime.tree.TerminalNode;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.ArrayTypeSymbol;
import p4project.context.CompilationContext;
import p4project.context.Symbol;
import p4project.context.ArrayValidator;

/*
    Phase 1: Symbol assignments and declerations
    -> Phase 2: Reference linking
    Phase 3: Type checking
    Phase 4: vtable and ftable generation
    Phase 5: Mutex and deep nested critical section checking
    Phase 6: Java Code Gen
*/

public class RefLinkingVisitor extends OurGrammarBaseVisitor<Void> {

    private final CompilationContext ctx;
    private final ArrayValidator arrayValidator;

    public RefLinkingVisitor(CompilationContext ctx) {
        this.ctx = ctx;
        this.arrayValidator = new ArrayValidator(ctx);
    }

    @Override
    public Void visitAssignment(OurGrammarParser.AssignmentContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)
        

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }
        if (context.assFunc() != null) {
            if (!(symbol instanceof p4project.context.FunctionSymbol)) {
                throw new RuntimeException("'" + id + "' assigned a function but is not declared as a function.");
            }
        }

        this.ctx.resolvedSymbols.put(context.ID(), symbol);
        return visitChildren(context);
    }

    @Override
    public Void visitReassignment(OurGrammarParser.ReassignmentContext context) {
        String id = "";
        if (context.arrayIndex() == null) {
            id = context.ID().getText();
        } else {
            id = context.arrayIndex().ID().getText();
        }
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)
        
        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        } 
        statement: if (symbol.arrType != null) {
            int[] dimensions = symbol.arrType.dimSize;
            int dimCount = dimensions.length;
            String contextStr = context.getText();
            int equalsIndex = contextStr.indexOf('=');
            
            System.out.println("Array reassignment: " + context.getText());
            // If the string does not contain any brackets before the equals sign, 
            // we can treat it as a normal array reassignment and link it to the symbol without further checks.
            if (!contextStr.contains("[")) {
                break statement; // Jumps out of the if statement.
            }
            String afterEquals = contextStr.substring(equalsIndex + 1, contextStr.length());
            String beforeEquals = contextStr.substring(0, equalsIndex);
            
            if (beforeEquals.contains("[")) {
                // If a string like "test[1][2] = 5" is given, we only extract "[1][2]".
                beforeEquals = beforeEquals.substring(contextStr.indexOf('['), equalsIndex);
                for (int i = 0; i < dimCount && beforeEquals.contains("["); i++) {
                    int currentDimIndex = Integer.parseInt(beforeEquals.substring(1, beforeEquals.indexOf(']')));
                    if (!(currentDimIndex <= dimensions[i])) {
                        throw new RuntimeException("Array index out of bounds for dimension " + i + ": " + currentDimIndex + " >= " + dimensions[i]);
                    }
                    beforeEquals = beforeEquals.substring(beforeEquals.indexOf(']') + 1);
                }
                if (afterEquals.contains("{") || afterEquals.contains("[")) {
                    throw new RuntimeException("Right-hand side of array reassignment must not contain braces or brackets.");
                } 
                // set the specific array element to the variable.


            } /// ER KOMMET HERTIL
            if (afterEquals.contains("{") || afterEquals.contains("[")) {
                // set either the size of the array or to the defined array literal.
                System.out.println("Old value from arr: " + java.util.Arrays.toString(dimensions));
                if (afterEquals.contains("{")) {
                    arrayValidator.inferDimensions(afterEquals, symbol, context);
                } else if (afterEquals.contains("[")) {
                    // If the right-hand side is an array size, we re-assign the size of the array.
                    for (int dim : dimensions) {
                        if (dim != 0) {
                            throw new RuntimeException("Cannot reassign size of an array that already has a defined size. Current size: " + java.util.Arrays.toString(dimensions));
                        }
                    }
                    int[] newSize = new int[dimCount];
                    System.out.println("Reassigning array size for '" + id + "', using string " + afterEquals);
                    for (int i = 0; i < dimCount && afterEquals.contains("["); i++) {
                        newSize[i] = Integer.parseInt(afterEquals.substring(1, afterEquals.indexOf(']')));
                        System.out.println("New size for dimension " + i + ": " + newSize[i]);
                        if (newSize[i] <= 0) {
                            throw new RuntimeException("Array size must be greater than 0 for dimension " + i + ": Size " + newSize[i]);
                        }
                        afterEquals = afterEquals.substring(afterEquals.indexOf(']') + 1);
                    }
                    symbol.arrType.dimSize = newSize;
                    System.out.println("Dimcount: " + dimCount + ", New value from arr: " + java.util.Arrays.toString(symbol.arrType.dimSize));
                }
            }
        }

        this.ctx.resolvedSymbols.put(context.ID(), symbol);
        return visitChildren(context);
    }

    @Override
    public Void visitThreadAssignment(OurGrammarParser.ThreadAssignmentContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id); // looks up the symbol in the symbol table using the variable name (id)

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        this.ctx.resolvedSymbols.put(context.ID(), symbol);
        return visitChildren(context);
    }

    @Override
    public Void visitAwaitStatement(OurGrammarParser.AwaitStatementContext context) {
        for (TerminalNode idNode : context.ID()) {
            String id = idNode.getText();
            Symbol symbol = this.ctx.symbolTable.resolve(id);
            
            if (symbol == null) {
                throw new RuntimeException("Variable '" + id + "' not declared.");
            }
            
            this.ctx.resolvedSymbols.put(idNode, symbol);
        }
        return null;
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
        for (TerminalNode id : context.ID()) {
            Symbol symbol = this.ctx.resolvedSymbols.get(id);
            if (symbol != null && !symbol.isShared()) {
                throw new RuntimeException("'" + id.getText() + "' must be declared 'shared' to be used in a critical section");
            } 
        }
        return visitChildren(context);
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