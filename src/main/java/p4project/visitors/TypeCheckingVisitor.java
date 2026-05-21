package p4project.visitors;

import org.antlr.v4.runtime.tree.TerminalNode;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Deque;

import p4project.context.*;

/*
    Phase 1: Symbol assignments and declarations
    Phase 2: Reference linking
    -> Phase 3: Type checking
    Phase 4: vtable and ftable generation
    Phase 5: Mutex and deep nested critical section checking
    Phase 6: Java Code Gen
*/

public class TypeCheckingVisitor extends OurGrammarBaseVisitor<String> {

    private final CompilationContext ctx;
    private final ArrayValidator arrayValidator;
    private int loopDepth = 0;
    private final Deque<FuncCtx> funcStack = new ArrayDeque<>();

    private static class FuncCtx {
        public final String name;
        public final String returnType;
        public boolean hasReturn;
        public FuncCtx(String name, String returnType) {
            this.name = name;
            this.returnType = returnType;
            this.hasReturn = false;
        }
    }

    public TypeCheckingVisitor(CompilationContext ctx) {
        this.ctx = ctx;
        this.arrayValidator = new ArrayValidator();
    }

    @Override
    public String visitDeclaration(OurGrammarParser.DeclarationContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id);

        if (symbol.arrType != null && context.typeRef().ID() != null) {
            for (int i = 0; i < context.typeRef().ID().size(); i++) {
                String typeName = this.ctx.symbolTable.resolve(context.typeRef().ID(i).getText()).type.toString();
                if (!(typeName.equals("int") || typeName.equals(""))) {
                    throw new RuntimeException("Type Error: Array dimensions must be of type int, but got " + typeName);
                }
            }
            
        }
        return visitChildren(context);
    }

    @Override
    public String visitAssignment(OurGrammarParser.AssignmentContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id);

        String declaredType = symbol.type.name.toLowerCase();
        
        if (context.assVar() != null) {
            String exprType = visit(context.assVar().expr());
            if (!declaredType.equals(exprType)) {
                boolean override = false;
            
                if (symbol.arrType != null) { // If an array is being reassigned to a specific array. Then override exception.
                    if (context.assVar().expr().getText().contains("{")) override = true; // Example -> float[2] x = {5,1} | override is true in this case.
                }
                if (!override) {
                    throw new RuntimeException("Type Error: Cannot assign '" + exprType + " to '" + declaredType + "'");
                }
            } else if (symbol.arrType != null) {
                int[] arr = arrayValidator.validate(context.assVar().expr().getText(), symbol.arrType);
            }
            return declaredType;
        } else if (context.assFunc() != null) {
            // Enter function context to type-check its statements and returns
            FuncCtx fctx = new FuncCtx(id, declaredType);
            funcStack.push(fctx);
            if (context.assFunc().block() != null) {
                visit(context.assFunc().block());
            }
            funcStack.pop();
            if (!fctx.hasReturn && !fctx.returnType.equals("void")) {
                throw new RuntimeException("Type Error: Function '" + id + "' must have a return statement returning " + fctx.returnType);
            }
            return declaredType;
        }
        throw new RuntimeException("Type Error: Invalid assignment");
    }

    @Override
    public String visitReassignment(OurGrammarParser.ReassignmentContext context) {
        String id = "";
        boolean isArrayIndex = (context.arrayIndex() != null);
        if (context.ID() == null) {
            id = context.arrayIndex().ID().getText();
        } else {
            id = context.ID().getText();
        }
        Symbol symbol = this.ctx.symbolTable.resolve(id);

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        if (symbol.arrType != null) {
            if (context.expr().getText().chars().filter(ch -> ch == '{').count() > 0) {
                int[] arr = arrayValidator.validate(context.expr().getText(), symbol.arrType);
            }
        }

        String declaredType = symbol.type.name.toLowerCase();
        String exprType = visit(context.expr());

        // If assigning to an array element (arr[index] = expr)
        if (isArrayIndex) {
            // RHS must not be an array (can't assign whole array to an element)
            Symbol rhsSymbol = this.ctx.symbolTable.resolve(context.expr().getText());
            if (rhsSymbol != null && rhsSymbol.arrType != null) {
                throw new RuntimeException("Type Error: Cannot assign array '" + rhsSymbol.ID + "' to element of array '" + id + "'");
            }
            if (!declaredType.equals(exprType)) {
                throw new RuntimeException("Type Error: Cannot assign " + exprType + " to " + declaredType + " (array element)");
            }
            return declaredType;
        }

        // Assignment to a variable (possibly whole-array assignment)
        Symbol contextSymbol = this.ctx.symbolTable.resolve(context.expr().getText());
        if (contextSymbol != null && symbol.arrType == null && contextSymbol.arrType != null) {
            throw new RuntimeException("Type Error: Cannot assign array value '"
            + contextSymbol.ID + "' to non-array variable '" + id + "'");
        } else if (contextSymbol != null && symbol.arrType != null && contextSymbol.arrType == null) {
            throw new RuntimeException("Type Error: Cannot assign value '"
            + contextSymbol.ID + "' to array variable '" + id + "'");
        } else if (contextSymbol != null && symbol.arrType != null && contextSymbol.arrType != null) {
            if (!Arrays.equals(symbol.arrType.dimSize, contextSymbol.arrType.dimSize)) {
                throw new RuntimeException("Array dimension size mismatch: '" + symbol.ID + "' " + symbol.arrType + " = '" + contextSymbol.ID + "' " + contextSymbol.arrType);
            }

        } else if (!declaredType.equals(exprType)) {
            boolean override = false;

            if (symbol.arrType != null) { // If an array is being reassigned with a specific size. Then override exception.
                if (context.expr().getText().contains("[")) override = true; // Example -> float[] x; x = [5] | override is true in this case.
            }
            if (!override) {
                throw new RuntimeException("Type Error: Cannot assign " + exprType + " to " + declaredType);
            }
        }
        return declaredType;
    }

    @Override 
    public String visitCriticalSection(OurGrammarParser.CriticalSectionContext context) {
        for (TerminalNode id : context.ID()) {
            Symbol symbol = this.ctx.resolvedSymbols.get(id);
            if (symbol != null && !symbol.isShared()) {
                throw new RuntimeException("'" + id.getText() + "' must be declared 'shared' to be used in a critical section");
            } 
        }
        return visitChildren(context);
    }

    @Override
    public String visitThreadAssignment(OurGrammarParser.ThreadAssignmentContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id);

        // check if the operator is '=>'. if not throw an error
        if (!context.getChild(2).getText().equals("=>")) {
            throw new RuntimeException("Syntax Error: Invalid thread assignment operator '" + context.getChild(1).getText() + "'. Expected '=>'.");
        }
        String declaredType = symbol.type.name.toLowerCase();
        if (!declaredType.equals("thread")) {
            throw new RuntimeException("Type Error: Cannot assign non-thread to thread variable '" + id + "'");
        }
        return declaredType;
    }

    @Override
    public String visitAwaitStatement(OurGrammarParser.AwaitStatementContext context) {
        for (TerminalNode idNode : context.ID()) {
            String id = idNode.getText();
            Symbol symbol = this.ctx.symbolTable.resolve(id);
            
            String declaredType = symbol.type.name.toLowerCase();
            if (!declaredType.equals("thread")) {
                throw new RuntimeException("Type Error: Cannot await non-thread variable '" + id + "'");
            }
        }
        return null;
    }

    @Override
    public String visitIfStatement(OurGrammarParser.IfStatementContext context) {
        for (OurGrammarParser.ExprContext expr : context.expr()) {
            String conditionType = visit(expr);
            if (!conditionType.equals("bool")) {
                throw new RuntimeException("Type Error: Condition expression must be of type bool, but got " + conditionType);
            }
        }
        return visitChildren(context);
    }

    @Override
    public String visitForStatement(OurGrammarParser.ForStatementContext context) {
        // Restore the for-statement scope created during declaration phase,
        // then type-check the for-loop body and related expressions.
        this.ctx.symbolTable.restoreScope(context);
        loopDepth++;

        if (context.forVar() != null) {
            visit(context.forVar());
        }
        // Loop variable initialization, condition and update are handled by visitChildren
        if (context.expr() != null) {
            String conditionType = visit(context.expr());
            if (!conditionType.equals("bool")) {
                throw new RuntimeException("Type Error: For loop condition must be of type bool, but got " + conditionType);
            }
        }
        if (context.reassignment() != null) {
            visit(context.reassignment());
        }
        visitChildren(context);
        this.ctx.symbolTable.popScope();
        loopDepth--;
        return null;

    }

    @Override
    public String visitForVar(OurGrammarParser.ForVarContext context) {
        // This should have been resolved in the reference linking phase, just need to type check the initialization expression
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id);
        
        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        String declaredType = symbol.type.name.toLowerCase();
        if (context.assVar() != null) {
            if (context.ID() != null) {
                if (this.ctx.symbolTable.resolve(id).type == TypeSymbol.THREAD || 
                    this.ctx.symbolTable.resolve(id).type == TypeSymbol.VOID) {
                    throw new RuntimeException("Cannot use void or thread, as for loop variable.");
                }
            }
            String exprType = visit(context.assVar().expr());
            if (!declaredType.equals(exprType)) {
                throw new RuntimeException("Type Error: Cannot assign " + exprType + " to " + declaredType);
            }
            return declaredType;
        } else if (context.reassignment().ID() != null) {
            if (this.ctx.symbolTable.resolve(context.reassignment().ID().getText()).type == TypeSymbol.THREAD || 
                this.ctx.symbolTable.resolve(context.reassignment().ID().getText()).type == TypeSymbol.VOID) {
                throw new RuntimeException("Cannot use void or thread, as for loop variable.");
            } 
            return visitChildren(context);
        } else if (context.ID() != null) {
            if (this.ctx.symbolTable.resolve(id).type == TypeSymbol.THREAD || 
                this.ctx.symbolTable.resolve(id).type == TypeSymbol.VOID) {
                throw new RuntimeException("Cannot use void or thread, as for loop variable.");
            }
            return this.ctx.symbolTable.resolve(id).type.name.toLowerCase();
        }
        throw new RuntimeException("Type Error: Invalid for-loop variable declaration");
    }

    @Override
    public String visitWhileStatement(OurGrammarParser.WhileStatementContext context) {
        this.ctx.symbolTable.restoreScope(context);
        loopDepth++;
        String conditionType = visit(context.expr());
        if (!conditionType.equals("bool")) {
            throw new RuntimeException("Type Error: While loop condition must be of type bool, but got " + conditionType);
        }
        visitChildren(context);
        this.ctx.symbolTable.popScope();
        loopDepth--;
        return null;
    }

    @Override
    public String visitBreakStatement(OurGrammarParser.BreakStatementContext context) {
        if (loopDepth == 0) {
            throw new RuntimeException("Syntax Error: 'break' statement not within a loop");
        }
        return null;
    }

    @Override
    public String visitContinueStatement(OurGrammarParser.ContinueStatementContext context) {
        if (loopDepth == 0) {
            throw new RuntimeException("Syntax Error: 'continue' statement not within a loop");
        }
        return null;
    }

    @Override
    public String visitReturnStatement(OurGrammarParser.ReturnStatementContext context) {
        if (funcStack.isEmpty()) {
            throw new RuntimeException("Syntax Error: 'return' statement not within a function");
        }
        FuncCtx cur = funcStack.peek();
        if (context.expr() == null) {
            // return without expression
            if (!cur.returnType.equals("void")) {
                throw new RuntimeException("Type Error: Function '" + cur.name + "' must return a value of type " + cur.returnType);
            }
        } else {
            String exprType = visit(context.expr());
            if (cur.returnType.equals("void")) {
                throw new RuntimeException("Type Error: Void function '" + cur.name + "' cannot return a value");
            }
            if (!cur.returnType.equals(exprType)) {
                throw new RuntimeException("Type Error: Return type mismatch in function '" + cur.name + "': expected " + cur.returnType + " but got " + exprType);
            }
        }
        cur.hasReturn = true;
        return cur.returnType;
    }

    @Override
    public String visitBlock(OurGrammarParser.BlockContext context) {
        this.ctx.symbolTable.restoreScope(context);
        visitChildren(context);
        this.ctx.symbolTable.popScope();
        return null;
    }

    @Override
    public String visitPrintStatement(OurGrammarParser.PrintStatementContext context) {
        for (OurGrammarParser.ExprContext expr : context.expr()) {
            visit(expr); // Just need to type-check the expressions being printed
        }
        visitChildren(context);
        return null;
    }

    @Override
    public String visitRead(OurGrammarParser.ReadContext context) {
        // find the declared type without the id
        String text = context.getText();
        int startIndex = text.indexOf('(') + 1;
        int endIndex = text.lastIndexOf(')');

        String declaredType = text.substring(startIndex, endIndex);
        if (!declaredType.matches("int|float|bool|string")) {
            throw new RuntimeException("Type Error: Invalid type '" + declaredType + "' in read statement");
        }
        visitChildren(context);
        return declaredType.toLowerCase();
    }

    @Override
    public String visitExpr(OurGrammarParser.ExprContext context) {
        if (context.expr() == null) {
            return visit(context.equal());
        }
        // Check if operator is '&&' or '||', if so, both sides must be bool
        if (context.getChild(1).getText().equals("&&") || context.getChild(1).getText().equals("||")) {
            String leftType = visit(context.equal());
            String rightType = visit(context.expr());

            if (!leftType.equals("bool") || !rightType.equals("bool")) {
                throw new RuntimeException("Type Error: Operator '" + context.getChild(1).getText() + "' requires boolean operands, but got " + leftType + " and " + rightType);
            }
            return "bool";
        }

        throw new RuntimeException("Type Error: Invalid operator '" + context.getChild(1).getText() + "' in expression");
    }

    @Override
    public String visitEqual(OurGrammarParser.EqualContext context) {
        if (context.equal() == null) {
            return visit(context.comp());
        }
        if (context.getChild(1).getText().matches("==|!=")) {
            String leftType = visit(context.comp());
            String rightType = visit(context.equal());
            if (!leftType.equals(rightType)) {
                throw new RuntimeException("Type Error: Cannot compare different types " + leftType + " and " + rightType);
            }
            return "bool";
        }
        throw new RuntimeException("Type Error: Invalid equality operator '" + context.getChild(1).getText() + "'");
    }

    @Override
    public String visitComp(OurGrammarParser.CompContext context) {
        if (context.comp() == null) {
            return visit(context.additive());
        }
        if (context.getChild(1).getText().matches("<|>|<=|>=")) {
            // Check data types of both sides, they must be the same and either int or float
            String leftType = visit(context.additive());
            String rightType = visit(context.comp());
            if (!leftType.equals(rightType)) {
                throw new RuntimeException("Type Error: Cannot compare " + leftType + " and " + rightType);
            }
            return "bool";
        }
        throw new RuntimeException("Type Error: Invalid comparison operator '" + context.getChild(1).getText() + "'");
    }
    
    @Override
    public String visitAdditive(OurGrammarParser.AdditiveContext context) {
        if (context.additive() == null) {
            return visit(context.mult());
        }
        String leftType = visit(context.mult());
        String rightType = visit(context.additive());
        
        // checks if both sides are int or float, otherwise throws an error in an if else if statement
        if (leftType.equals("int") && rightType.equals("int")) {
            return "int";
        } else if (leftType.equals("float") && rightType.equals("float")) {
            return "float";
        } else if (leftType.equals("string") && rightType.equals("string") && context.getChild(1).getText().equals("+")) {
            return "string"; 
        } else {
            throw new RuntimeException("Type Error: Cannot apply operator '" + context.getChild(1).getText() + "' to types " + leftType + " and " + rightType);
        }
    }

    @Override
    public String visitMult(OurGrammarParser.MultContext context) {
        if (context.mult() == null) {
            return visit(context.power());
        }
        String leftType = visit(context.power());
        String rightType = visit(context.mult());
        // checks if both sides are int or float, otherwise throws an error in an if else if statement
        if (leftType.equals("int") && rightType.equals("int")) {
            return "int";
        } else if (leftType.equals("float") && rightType.equals("float")) {
            return "float";
        } else {
            throw new RuntimeException("Type Error: Cannot apply operator '" + context.getChild(1).getText() + "' to types " + leftType + " and " + rightType);
        }
    }

    @Override
    public String visitPower(OurGrammarParser.PowerContext context) {
        if (context.power() == null) {
            return visit(context.factor());
        }
        String leftType = visit(context.factor());
        String rightType = visit(context.power());
        if (!leftType.matches("int|float") || !rightType.matches("int|float")) {
            throw new RuntimeException("Type Error: Cannot apply operator '" + context.getChild(1).getText() + "' to types " + leftType + " and " + rightType);
        }
        if (leftType.equals(rightType)) {
            return leftType;
        } 
        throw new RuntimeException("Type Error: Cannot apply operator '" + context.getChild(1).getText() + "' to different types " + leftType + " and " + rightType);
    }

    @Override
    public String visitFactor(OurGrammarParser.FactorContext context) {
        // Unary negation
        if (context.NEGATIVE() != null) {
            String factorType = visit(context.factor());
            if (!factorType.matches("int|float")) {
                throw new RuntimeException("Type Error: Unary '-' operator cannot be applied to type " + factorType);
            }
            return factorType;
        }

        // function call
        if (context.functionCall() != null) {
            OurGrammarParser.FunctionCallContext fc = context.functionCall();
            if (fc.expr() != null) {
                for (OurGrammarParser.ExprContext e : fc.expr()) {
                    visit(e);
                }
            }
            Symbol functionSymbol = this.ctx.resolvedSymbols.get(fc.ID());
            if (functionSymbol == null) {
                throw new RuntimeException("Function '" + fc.ID().getText() + "' not declared.");
            }
            return functionSymbol.type.name.toLowerCase();
        }

        // array indexing
        if (context.arrayIndex() != null) {
            OurGrammarParser.ArrayIndexContext ai = context.arrayIndex();
            Symbol symbol = this.ctx.resolvedSymbols.get(ai.ID());
            if (symbol == null) {
                throw new RuntimeException("Variable '" + ai.ID().getText() + "' not declared.");
            } else if (symbol.arrType == null) {
                throw new RuntimeException("Type Error: '" + ai.ID().getText() + "' is not an array.");
            }

            return symbol.type.name.toLowerCase();
        }

        // array literal
        if (context.arrayLiteral() != null) {
            OurGrammarParser.ArrayLiteralContext al = context.arrayLiteral();
            if (al.expr() != null && !al.expr().isEmpty()) {
                String firstType = visit(al.expr(0));
                for (int i = 1; i < al.expr().size(); i++) {
                    String t = visit(al.expr(i));
                    if (!t.equals(firstType)) {
                        throw new RuntimeException("Type Error: Array literal elements must have same type: " + firstType + " vs " + t);
                    }
                }
                return firstType;
            }
            throw new RuntimeException("Type Error: Empty array literal");
        }

        if (context.read() != null) {
            return visit(context.read());
        }

        // simple identifier (variable)
        if (context.ID() != null) {
            Symbol symbol = this.ctx.resolvedSymbols.get(context.ID());
            if (symbol == null) {
                throw new RuntimeException("Variable '" + context.ID().getText() + "' not declared.");
            }
            return symbol.type.name.toLowerCase();
        }

        if (context.INT() != null) return "int";
        if (context.FLOAT() != null) return "float";
        if (context.BOOL() != null) return "bool";
        if (context.CHAR() != null) return "char";
        if (context.STRING() != null) return "string";
        if (context.THREAD() != null) return "thread";

        // parenthesized expression
        if (context.expr() != null) {
            return visit(context.expr());
        }

        // cast expression
        if (context.castExpression() != null) {
            OurGrammarParser.CastExpressionContext castCtx = context.castExpression();
            if (castCtx == null) {
                throw new RuntimeException("Type Error: Malformed cast expression");
            }
            String targetType = castCtx.TYPE().getText().toLowerCase();
            String exprType = visit(castCtx.expr());
            // Cannot cast to char as they could be the source to a lot of casting problems.
            if (!targetType.matches("int|float|bool|string") || !exprType.matches("int|float|bool|string|char")) {
                throw new RuntimeException("Type Error: Invalid cast from " + exprType + " to " + targetType);
            }
            switch (targetType) {
                case "int":
                    if (!exprType.matches("float|bool")) {
                        throw new RuntimeException("Type Error: Cannot cast " + exprType + " to int");
                    }
                    return "int";
                case "bool":
                    if (!exprType.matches("int|float")) {
                        throw new RuntimeException("Type Error: Cannot cast " + exprType + " to bool");
                    }
                    return "bool";
                case "float":
                    if (!exprType.matches("int|bool")) {
                        throw new RuntimeException("Type Error: Cannot cast " + exprType + " to float");
                    }
                    return "float"; 
                case "string":
                    if (!exprType.matches("char|int|float|bool")) {
                        throw new RuntimeException("Type Error: Cannot cast " + exprType + " to string");
                    }
                    return "string";
                default:
                    throw new RuntimeException("Type Error: Unsupported cast from " + exprType + " to " + targetType);
            }
            
        }

        throw new RuntimeException("Type Error: Invalid factor");
    }

}
