package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;
import p4project.context.Symbol;

/*
    Phase 1: Symbol assignments and declarations
    Phase 2: Reference linking
    -> Phase 3: Type checking
    Phase 4: vtable and ftable generation
    Phase 5: Java Code Gen
*/

public class TypeCheckingVisitor extends OurGrammarBaseVisitor<String> {

    private final CompilationContext ctx;

    public TypeCheckingVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public String visitAssignment(OurGrammarParser.AssignmentContext ctx) {
        String id = ctx.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id);

        if (symbol == null) {
            throw new RuntimeException("Variable '" + id + "' not declared.");
        }

        String declaredType = symbol.type.name.toLowerCase();

        if (ctx.assVar() != null) {
            String exprType = visit(ctx.assVar().expr());
            if (!declaredType.equals(exprType)) {
                throw new RuntimeException("Type Error: Cannot assign " + exprType + " to " + declaredType);
            }
            return declaredType;
        } else if (ctx.assFunc() != null) {
            // Visit function body to type-check its statements
            if (ctx.assFunc().block() != null) {
                visit(ctx.assFunc().block());
            }
            return declaredType;
        }
        throw new RuntimeException("Type Error: Invalid assignment");
    }

    @Override
    public String visitExpr(OurGrammarParser.ExprContext ctx) {
        if (ctx.expr() == null) {
            return visit(ctx.equal());
        }
        // Check if operator is '&&' or '||', if so, both sides must be bool
        if (ctx.getChild(1).getText().equals("&&") || ctx.getChild(1).getText().equals("||")) {
            String leftType = visit(ctx.equal());
            String rightType = visit(ctx.expr());

            if (!leftType.equals("bool") || !rightType.equals("bool")) {
                throw new RuntimeException("Type Error: Operator '" + ctx.getChild(1).getText() + "' requires boolean operands, but got " + leftType + " and " + rightType);
            }
            return "bool";
        }

        throw new RuntimeException("Type Error: Invalid operator '" + ctx.getChild(1).getText() + "' in expression");
    }

    @Override
    public String visitEqual(OurGrammarParser.EqualContext ctx) {
        if (ctx.equal() == null) {
            return visit(ctx.comp());
        }
        if (ctx.getChild(1).getText().matches("==|!=")) {
            String leftType = visit(ctx.comp());
            String rightType = visit(ctx.equal());
            if (!leftType.equals(rightType)) {
                throw new RuntimeException("Type Error: Cannot compare different types " + leftType + " and " + rightType);
            }
            return "bool";
        }
        throw new RuntimeException("Type Error: Invalid equality operator '" + ctx.getChild(1).getText() + "'");
    }

    @Override
    public String visitComp(OurGrammarParser.CompContext ctx) {
        if (ctx.comp() == null) {
            return visit(ctx.additive());
        }
        if (ctx.getChild(1).getText().matches("<|>|<=|>=")) {
            // Check data types of both sides, they must be the same and either int or float
            String leftType = visit(ctx.additive());
            System.out.println("Left type: " + leftType);
            String rightType = visit(ctx.comp());
            System.out.println("Right type: " + rightType);
            if (!leftType.equals(rightType)) {
                throw new RuntimeException("Type Error: Cannot compare " + leftType + " and " + rightType);
            }
            return "bool";
        }
        throw new RuntimeException("Type Error: Invalid comparison operator '" + ctx.getChild(1).getText() + "'");
    }
    
    @Override
    public String visitAdditive(OurGrammarParser.AdditiveContext ctx) {
        if (ctx.additive() == null) {
            return visit(ctx.mult());
        }
        String leftType = visit(ctx.mult());
        String rightType = visit(ctx.additive());
        
        // checks if both sides are int or float, otherwise throws an error in an if else if statement
        if (leftType.equals("int") && rightType.equals("int")) {
            return "int";
        } else if (leftType.equals("float") && rightType.equals("float")) {
            return "float";
        } else {
            throw new RuntimeException("Type Error: Cannot apply operator '" + ctx.getChild(1).getText() + "' to types " + leftType + " and " + rightType);
        }
    }

    @Override
    public String visitMult(OurGrammarParser.MultContext ctx) {
        if (ctx.mult() == null) {
            return visit(ctx.power());
        }
        String leftType = visit(ctx.power());
        String rightType = visit(ctx.mult());
        // checks if both sides are int or float, otherwise throws an error in an if else if statement
        if (leftType.equals("int") && rightType.equals("int")) {
            return "int";
        } else if (leftType.equals("float") && rightType.equals("float")) {
            return "float";
        } else {
            throw new RuntimeException("Type Error: Cannot apply operator '" + ctx.getChild(1).getText() + "' to types " + leftType + " and " + rightType);
        }
    }

    @Override
    public String visitPower(OurGrammarParser.PowerContext ctx) {
        if (ctx.power() == null) {
            return visit(ctx.factor());
        }
        String leftType = visit(ctx.factor());
        String rightType = visit(ctx.power());
        if (!leftType.matches("int|float") || !rightType.matches("int|float")) {
            throw new RuntimeException("Type Error: Cannot apply operator '" + ctx.getChild(1).getText() + "' to types " + leftType + " and " + rightType);
        }
        if (leftType.equals(rightType)) {
            return leftType;
        } 
        throw new RuntimeException("Type Error: Cannot apply operator '" + ctx.getChild(1).getText() + "' to different types " + leftType + " and " + rightType);
    }

    @Override
    public String visitFactor(OurGrammarParser.FactorContext ctx) {

        // Unary negation
        if (ctx.NEGATIVE() != null) {
            String factorType = visit(ctx.factor());
            if (!factorType.matches("int|float")) {
                throw new RuntimeException("Type Error: Unary '-' operator cannot be applied to type " + factorType);
            }
            return factorType;
        }

        // function call
        if (ctx.functionCall() != null) {
            OurGrammarParser.FunctionCallContext fc = ctx.functionCall();
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
        if (ctx.arrayIndex() != null) {
            OurGrammarParser.ArrayIndexContext ai = ctx.arrayIndex();
            Symbol symbol = this.ctx.resolvedSymbols.get(ai.ID());
            if (symbol == null) {
                throw new RuntimeException("Variable '" + ai.ID().getText() + "' not declared.");
            }
            return symbol.type.name.toLowerCase();
        }

        // array literal
        if (ctx.arrayLiteral() != null) {
            OurGrammarParser.ArrayLiteralContext al = ctx.arrayLiteral();
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

        // simple identifier (variable)
        if (ctx.ID() != null) {
            Symbol symbol = this.ctx.resolvedSymbols.get(ctx.ID());
            if (symbol == null) {
                throw new RuntimeException("Variable '" + ctx.ID().getText() + "' not declared.");
            }
            return symbol.type.name.toLowerCase();
        }

        if (ctx.INT() != null) return "int";
        if (ctx.FLOAT() != null) return "float";
        if (ctx.BOOL() != null) return "bool";
        if (ctx.CHAR() != null) return "char";
        if (ctx.STRING() != null) return "string";
        if (ctx.THREAD() != null) return "thread";

        // parenthesized expression
        if (ctx.expr() != null) {
            return visit(ctx.expr());
        }

        // cast expression
        if (ctx.castExpression() != null) {
            OurGrammarParser.CastExpressionContext castCtx = ctx.castExpression();
            if (castCtx == null) {
                throw new RuntimeException("Type Error: Malformed cast expression");
            }
            String targetType = castCtx.TYPE().getText().toLowerCase();
            String exprType = visit(castCtx.expr());
            if (!targetType.matches("int|float|bool|char|string") || !exprType.matches("int|float|bool|char|string")) {
                throw new RuntimeException("Type Error: Invalid cast from " + exprType + " to " + targetType);
            }
            return targetType;
        }

        throw new RuntimeException("Type Error: Invalid factor");
    }

}
