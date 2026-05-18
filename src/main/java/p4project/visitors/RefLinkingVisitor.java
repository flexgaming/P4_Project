package p4project.visitors;

import org.antlr.v4.runtime.tree.TerminalNode;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
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
        this.arrayValidator = new ArrayValidator();
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
        } else if (symbol.arrType != null) {
            
            String contextStr = context.getText();
            int equalsIndex = contextStr.indexOf('=');
            String afterEquals = contextStr.substring(equalsIndex + 1, contextStr.length());

            if (afterEquals.contains("{")) {
                int[] correctDimSize = arrayValidator.validate(afterEquals);

                for (int i = 0; i < symbol.arrType.dimensions; i++) {
                    symbol.arrType.dimSize[i] = String.valueOf(correctDimSize[i]);
                }
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
            String[] dimensions = symbol.arrType.dimSize;
            int dimCount = dimensions.length;
            String contextStr = context.getText();
            int equalsIndex = contextStr.indexOf('=');
            
            // If the string does not contain any brackets before the equals sign, 
            // we can treat it as a normal array reassignment and link it to the symbol without further checks.
            if (!contextStr.contains("[")) {
                break statement; // Jumps out of the if statement.
            }
            String beforeEquals = contextStr.substring(0, equalsIndex);
            String afterEquals = contextStr.substring(equalsIndex + 1, contextStr.length());
            
            if (beforeEquals.contains("[")) {
                // If a string like "test[1][2] = 5" is given, we only extract "[1][2]".
                beforeEquals = beforeEquals.substring(contextStr.indexOf('['), equalsIndex);
                for (int i = 0; i < dimCount && beforeEquals.contains("["); i++) {
                    int sqBracket1 = beforeEquals.indexOf('[');
                    int sqBracket2 = beforeEquals.indexOf(']');
                    String currentDimIndex = beforeEquals.substring(sqBracket1 + 1, sqBracket2);
                    if (currentDimIndex.matches("\\d+") && dimensions[i] != null && dimensions[i].matches("\\d+")) {
                        if (!(Integer.parseInt(currentDimIndex) <= Integer.parseInt(dimensions[i]))) {
                            throw new RuntimeException("Array index out of bounds for dimension " + i + ": " + currentDimIndex + " >= " + dimensions[i]);
                        }
                    }
                    beforeEquals = beforeEquals.substring(sqBracket2 + 1);
                }
                if (afterEquals.contains("{") || afterEquals.contains("[")) {
                    throw new RuntimeException("Right-hand side of array reassignment must not contain braces or brackets.");
                }

            } // Reassign the size of each dimension.
            if (afterEquals.contains("{") || afterEquals.contains("[")) {
                // set either the size of the array or to the defined array literal.
                if (afterEquals.contains("{")) {

                    int[] correctDimSize = arrayValidator.validate(afterEquals, symbol.arrType);

                    for (int i = 0; i < dimCount; i++) {
                        symbol.arrType.dimSize[i] = String.valueOf(correctDimSize[i]);
                    }
                } else if (afterEquals.contains("[")) {
                    // If the right-hand side is an array size, we re-assign the size of the array.
                    for (String dim : dimensions) {
                        if (dim != null && !dim.equals("0") && !dim.trim().isEmpty()) {
                            throw new RuntimeException("Cannot reassign size of an array that already has a defined size. Current size: " + java.util.Arrays.toString(dimensions));
                        }
                    }
                    String[] newSize = new String[dimCount];
                    for (int i = 0; i < dimCount && afterEquals.contains("["); i++) {
                        int sqBracket1 = afterEquals.indexOf('[');
                        int sqBracket2 = afterEquals.indexOf(']');
                        newSize[i] = afterEquals.substring(sqBracket1 + 1, sqBracket2).trim();
                        if (newSize[i].matches("\\d+")) {
                            if (Integer.parseInt(newSize[i]) <= 0) {
                                throw new RuntimeException("Array size must be greater than 0 for dimension " + i + ": Size " + newSize[i]);
                            }
                        }
                        afterEquals = afterEquals.substring(sqBracket2 + 1);
                    }
                    symbol.arrType.dimSize = newSize.clone();
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
}