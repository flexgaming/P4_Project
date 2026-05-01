package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;

/*
    Phase 1: Symbol assignments and declerations
    Phase 2: Reference linking
    Phase 3: Type checking
    Phase 4: vtable and ftable generation
    -> Phase 5: Java Code Gen
*/

public class CodeGenVisitor extends OurGrammarBaseVisitor<String> {
    private final CompilationContext ctx;

    public CodeGenVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }

    private int indentLevel = 0; // Track current indentation level for pretty-printing
    private static final String INDENT = "    ";

    private String indent() {
        return INDENT.repeat(Math.max(0, indentLevel)); // Ensure non-negative repeat count
    }

    @Override
    public String visitProgram(OurGrammarParser.ProgramContext ctx) {
        StringBuilder result = new StringBuilder();

        for (OurGrammarParser.StatementContext stmt : ctx.statement()) {
            String stmtCode = visit(stmt);
            if (stmtCode != null && !stmtCode.isEmpty()) {
                result.append(stmtCode);
            }
        }

        return result.toString();
    }

    @Override
    public String visitStatement(OurGrammarParser.StatementContext ctx) {
        if (ctx.expr() != null) {
            return indent() + visit(ctx.expr()) + ";\n";
        }
        return visitChildren(ctx);
    }

    @Override
    public String visitAssignment(OurGrammarParser.AssignmentContext ctx) {
        String type = ctx.typeRef().TYPE().getText();
        String id = ctx.ID().getText();

        if (ctx.assFunc() != null) {
            // Function definition
            String params = visit(ctx.assFunc());     // Let assFunc generate the parameter list
            String blockCode = visit(ctx.assFunc().block());
            // If blockCode starts with the current indent, strip it so the '{' lands
            // directly after the function header (`void main() {`).
            if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());

            return indent() + type + " " + id + params + blockCode;
        } 
        else if (ctx.assVar() != null) {
            String exprCode = visit(ctx.assVar().expr());
            return indent() + type + " " + id + " = " + exprCode + ";\n";
        } 
        else {
            return "";
        }
    }

    @Override
    public String visitReassignment(OurGrammarParser.ReassignmentContext ctx) {
        return indent() + ctx.ID().getText() + " = " + visit(ctx.expr()) + ";\n";
    }

    @Override
    public String visitDeclaration(OurGrammarParser.DeclarationContext ctx) {
        return indent() + ctx.typeRef().TYPE().getText() + " " + ctx.ID().getText() + ";\n";
    }

    @Override
    public String visitIfStatement(OurGrammarParser.IfStatementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append("if (").append(visit(ctx.expr(0))).append(") ");
        String thenCode = visit(ctx.statement(0));
        if (thenCode.startsWith(indent())) thenCode = thenCode.substring(indent().length());
        sb.append(thenCode);

        for (int i = 1; i < ctx.expr().size(); i++) {
            sb.append(indent()).append("else if (").append(visit(ctx.expr(i))).append(") ");
            String elifCode = visit(ctx.statement(i));
            if (elifCode.startsWith(indent())) elifCode = elifCode.substring(indent().length());
            sb.append(elifCode);
        }

        if (ctx.statement().size() > ctx.expr().size()) {
            sb.append(indent()).append("else ");
            String elseCode = visit(ctx.statement(ctx.statement().size() - 1));
            if (elseCode.startsWith(indent())) elseCode = elseCode.substring(indent().length());
            sb.append(elseCode);
        }

        return sb.toString();
    }

    @Override
    public String visitAssFunc(OurGrammarParser.AssFuncContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        if (ctx.typeRef() != null && !ctx.typeRef().isEmpty()) {
            for (int i = 0; i < ctx.typeRef().size(); i++) {
                String paramType = ctx.typeRef(i).TYPE().getText();
                String paramName = ctx.ID(i).getText();     // Note: adjust index if needed

                sb.append(paramType).append(" ").append(paramName);

                if (i < ctx.typeRef().size() - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(")");
        return sb.toString();
    }

    @Override
    public String visitReturnStatement(OurGrammarParser.ReturnStatementContext ctx) {
        if (ctx.expr() != null) {
            return indent() + "return " + visit(ctx.expr()) + ";\n";
        }
        return indent() + "return;\n";
    }

    @Override
    public String visitBlock(OurGrammarParser.BlockContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append("{\n");

        indentLevel++;
        for (OurGrammarParser.StatementContext stmt : ctx.statement()) {
            String stmtCode = visit(stmt);
            if (stmtCode == null || stmtCode.isEmpty()) continue;
            sb.append(stmtCode);
        }
        indentLevel--;

        sb.append(indent()).append("}\n");
        return sb.toString();
    }

    @Override
    public String visitExpr(OurGrammarParser.ExprContext ctx) {
        String left = visit(ctx.equal());
        if (ctx.expr() != null) {
            String op = ctx.getChild(1).getText();
            String right = visit(ctx.expr());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitEqual(OurGrammarParser.EqualContext ctx) {
        String left = visit(ctx.comp());
        if (ctx.equal() != null) {
            String op = ctx.getChild(1).getText();
            String right = visit(ctx.equal());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitComp(OurGrammarParser.CompContext ctx) {
        String left = visit(ctx.additive());
        if (ctx.comp() != null) {
            String op = ctx.getChild(1).getText();
            String right = visit(ctx.comp());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitAdditive(OurGrammarParser.AdditiveContext ctx) {
        String left = visit(ctx.mult());
        if (ctx.additive() != null) {
            String op = ctx.getChild(1).getText();
            String right = visit(ctx.additive());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitMult(OurGrammarParser.MultContext ctx) {
        String left = visit(ctx.power());
        if (ctx.mult() != null) {
            String op = ctx.getChild(1).getText();
            String right = visit(ctx.mult());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitPower(OurGrammarParser.PowerContext ctx) {
        String left = visit(ctx.factor());
        if (ctx.power() != null) {
            String op = ctx.getChild(1).getText();
            String right = visit(ctx.power());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitArrayLiteral(OurGrammarParser.ArrayLiteralContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < ctx.expr().size(); i++) {
            sb.append(visit(ctx.expr(i)));
            if (i < ctx.expr().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String visitArrayIndex(OurGrammarParser.ArrayIndexContext ctx) {
        String base = visit(ctx.expr(0));
        String index = visit(ctx.expr(1));
        return base + "[" + index + "]";
    }

    @Override
    public String visitFactor(OurGrammarParser.FactorContext ctx) {
        // Prefer checking specific child contexts instead of relying on the
        // start token: several alternatives (functionCall, arrayIndex) start
        // with an ID token and would otherwise be mis-dispatched.
        if (ctx.NEGATIVE() != null) {
            return "-" + visit(ctx.factor());
        }
        if (ctx.functionCall() != null) {
            return visitFunctionCall(ctx.functionCall());
        }
        if (ctx.arrayIndex() != null) {
            return visitArrayIndex(ctx.arrayIndex());
        }
        if (ctx.arrayLiteral() != null) {
            return visitArrayLiteral(ctx.arrayLiteral());
        }
        if (ctx.ID() != null) {
            return ctx.ID().getText();
        }
        if (ctx.INT() != null) {
            return ctx.INT().getText();
        }
        if (ctx.FLOAT() != null) {
            return ctx.FLOAT().getText();
        }
        if (ctx.BOOL() != null) {
            return ctx.BOOL().getText();
        }
        if (ctx.CHAR() != null) {
            return ctx.CHAR().getText();
        }
        if (ctx.STRING() != null) {
            return ctx.STRING().getText();
        }
        if (ctx.THREAD() != null) {
            return ctx.THREAD().getText();
        }
        if (ctx.castExpression() != null) {
            OurGrammarParser.CastExpressionContext castCtx = ctx.castExpression();
            String targetType = castCtx.TYPE().getText().toLowerCase();
            String expr = visit(castCtx.expr());
            return "(" + targetType + ") " + expr;
        }
        if (ctx.expr() != null) {
            // parenthesised expression
            return "(" + visit(ctx.expr()) + ")";
        }

        return visitChildren(ctx);
    }

    @Override
    public String visitFunctionCall(OurGrammarParser.FunctionCallContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.ID().getText());
        sb.append("(");
        if (ctx.expr() != null && !ctx.expr().isEmpty()) {
            for (int i = 0; i < ctx.expr().size(); i++) {
                sb.append(visit(ctx.expr(i)));
                if (i < ctx.expr().size() - 1) sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null) return nextResult;
        if (nextResult == null) return aggregate;
        return aggregate + nextResult;
    }
}
