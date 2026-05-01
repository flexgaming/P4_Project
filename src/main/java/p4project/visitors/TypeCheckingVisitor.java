package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarLexer;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;
import p4project.context.Symbol;

/*
    Phase 1: Symbol assignments and declerations
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
            // Function declarations are handled in the FtableGenVisitor, so we can skip type checking here.
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
        // Type checker only checks for the operator and if the operator is '==' or '!=', it returns bool.
        // int 4 == float 4     -> false
        // int 5 == int 2       -> false
        // float 4.3 != int 3   -> true
    }

    @Override
    public String visitComp(OurGrammarParser.CompContext ctx) {
        if (ctx.comp() == null) {
            return visit(ctx.additive());
        }
        if (ctx.getChild(1).getText().matches("<|>|<=|>=")) {
            // Check data types of both sides, they must be the same and either int or float
            String leftType = visit(ctx.additive());
            String rightType = visit(ctx.comp());
            if (!leftType.equals(rightType)) {
                throw new RuntimeException("Type Error: Cannot compare " + leftType + " and " + rightType);
            }
            return "bool";
        }
        throw new RuntimeException("Type Error: Invalid comparison operator '" + ctx.getChild(1).getText() + "'");
        // int 4 < float 4                  -> error (cannot compare int and float)
        // int 4 >= cast(int) float 4.5     -> true (Must be same data type, but can be casted)
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
        switch (ctx.getStart().getType()) {
            case OurGrammarParser.NEGATIVE:
                String factorType = visit(ctx.factor());
                if (!factorType.matches("int|float")) {
                    throw new RuntimeException("Type Error: Unary '-' operator cannot be applied to type " + factorType);
                }
                return factorType;
            case OurGrammarParser.INT:
                return "int";
            case OurGrammarParser.FLOAT:
                return "float";
            case OurGrammarParser.BOOL:
                return "bool";
            case OurGrammarParser.ID:
                Symbol symbol = this.ctx.resolvedSymbols.get(ctx.ID());
                if (symbol == null) {
                    throw new RuntimeException("Variable '" + ctx.ID().getText() + "' not declared.");
                }
                return symbol.type.name.toLowerCase();
            // case for cast expressions (starts with the 'cast' literal token)
            case OurGrammarParser.T__8: // 'cast'
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

            default:
                throw new RuntimeException("Type Error: Invalid factor");
        }
    }


}
