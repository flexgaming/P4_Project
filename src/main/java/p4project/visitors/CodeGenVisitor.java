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
    private boolean inMain = false;

    public CodeGenVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }

    private static final String INDENT = "    ";

    private String indent() {
        if (!ctx.ftable.containsKey("main")) {
            return INDENT.repeat(Math.max(0, ctx.symbolTable.getDepth()+2)); // compensate for the extra indent level of the generated main method
        }
        return INDENT.repeat(Math.max(0, ctx.symbolTable.getDepth())); // Ensure non-negative repeat count
        
    }

    @Override
    public String visitProgram(OurGrammarParser.ProgramContext context) {
        StringBuilder result = new StringBuilder();

        for (OurGrammarParser.StatementContext stmt : context.statement()) {
            String stmtCode = visit(stmt);
            if (stmtCode != null && !stmtCode.isEmpty()) {
                result.append(stmtCode);
            }
        }

        return result.toString();
    }

    @Override
    public String visitStatement(OurGrammarParser.StatementContext context) {
        if (context.statementPrime() != null) {
            return visit(context.statementPrime());
        }
        return visitChildren(context);
    }

    @Override
    public String visitStatementPrime(OurGrammarParser.StatementPrimeContext context) {
        if (context.expr() != null) {
            return indent() + visit(context.expr()) + ";\n";
        }
        return visitChildren(context);
    }

    @Override
    public String visitAssignment(OurGrammarParser.AssignmentContext context) {
        String type = context.typeRef().TYPE().getText();
        String id = context.ID().getText();

        if (context.assFunc() != null) {
            
            // Check if this is the main function to set the inMain flag.
            if (id.equals("main")) {
                inMain = true;
                // Function definition
                String blockCode = visit(context.assFunc().block());
                // If blockCode starts with the current indent, strip it so the '{' lands directly after the function header.
                if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());
                return "public class Main " + blockCode;
            }

            // Function definition
            String params = visit(context.assFunc());
            String blockCode = visit(context.assFunc().block());
            if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());
            return indent() + type + " " + id + params + blockCode + "\n";
        } 
        else if (context.assVar() != null) {
            String exprCode = visit(context.assVar().expr());
            return indent() + type + " " + id + " = " + exprCode + ";\n";
        } 
        else {
            return "";
        }
    }

    @Override
    public String visitReassignment(OurGrammarParser.ReassignmentContext context) {
        // check if it is called in a for header
        if (this.ctx.symbolTable.getNodeScope().getText().contains("for")) {
            return context.ID().getText() + " = " + visit(context.expr());
        }

        return indent() + context.ID().getText() + " = " + visit(context.expr()) + ";\n";
    }

    @Override
    public String visitDeclaration(OurGrammarParser.DeclarationContext context) {
        return indent() + context.typeRef().TYPE().getText() + " " + context.ID().getText() + ";\n";
    }

    @Override
    public String visitIfStatement(OurGrammarParser.IfStatementContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append("if (").append(visit(context.expr(0))).append(") ");
        String thenCode = visit(context.statement(0));
        if (thenCode.startsWith(indent())) thenCode = thenCode.substring(indent().length());
        sb.append(thenCode + "\n");

        for (int i = 1; i < context.expr().size(); i++) { // else-if statements
            sb.append(indent()).append("else if (").append(visit(context.expr(i))).append(") ");
            String elseIfCode = visit(context.statement(i));
            if (elseIfCode.startsWith(indent())) elseIfCode = elseIfCode.substring(indent().length());
            sb.append(elseIfCode + "\n");
        }

        if (context.statement().size() > context.expr().size()) { // else statement
            sb.append(indent()).append("else ");
            String elseCode = visit(context.statement(context.statement().size() - 1));
            if (elseCode.startsWith(indent())) elseCode = elseCode.substring(indent().length());
            sb.append(elseCode + "\n");
        }

        return sb.toString();
    }

    @Override
    public String visitAssFunc(OurGrammarParser.AssFuncContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        if (context.typeRef() != null && !context.typeRef().isEmpty()) {
            for (int i = 0; i < context.typeRef().size(); i++) {
                String paramType = context.typeRef(i).TYPE().getText();
                String paramName = context.ID(i).getText();

                sb.append(paramType).append(" ").append(paramName);

                if (i < context.typeRef().size() - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(") ");
        return sb.toString();
    }

    @Override
    public String visitThreadAssignment(OurGrammarParser.ThreadAssignmentContext context) {
        String id = context.ID().getText();
        String blockCode = visit(context.block());
        if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());
        return indent() + "CompletableFuture<Void> " + id + " = CompletableFuture.runAsync(() -> " + blockCode + ");\n";
    }

    @Override
    public String visitAwaitStatement(OurGrammarParser.AwaitStatementContext context) {
        StringBuilder sb = new StringBuilder();
        // check if it is awaitAll or awaitAny
        if (context.getChild(0).getText().equals("awaitAll")) {
            
            sb.append(indent()).append("try {\n")
            .append(indent()).append("    CompletableFuture.allOf(");
            for (int i = 0; i < context.ID().size(); i++) {
                String id = context.ID(i).getText();
                sb.append(id);
                if (i < context.ID().size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(").get();\n")
            .append(indent()).append("} catch (InterruptedException | ExecutionException e) {\n")
            .append(indent()).append("    e.printStackTrace();\n")
            .append(indent()).append("}\n");
        } 
        else if (context.getChild(0).getText().equals("awaitAny")) {
            sb.append(indent()).append("try {\n")
            .append(indent()).append("    CompletableFuture.anyOf(");
            for (int i = 0; i < context.ID().size(); i++) {
                String id = context.ID(i).getText();
                sb.append(id);
                if (i < context.ID().size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(").get();\n")
            .append(indent()).append("} catch (InterruptedException | ExecutionException e) {\n")
            .append(indent()).append("    e.printStackTrace();\n")
            .append(indent()).append("}\n");
        } 
        else {
            throw new RuntimeException("Unexpected await statement type: " + context.getChild(0).getText());
        }
        
        return sb.toString();
    }

    @Override
    public String visitForStatement(OurGrammarParser.ForStatementContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append("for (");

        // Initialization
        if (context.forVar() != null) {
            sb.append(visit(context.forVar()) + ";");
        } 

        // Condition
        if (context.expr() != null) {
            sb.append(" ").append(visit(context.expr())).append(";");
        } 

        // Update
        if (context.reassignment() != null) {
            sb.append(" ").append(visit(context.reassignment()));
        }

        sb.append(") ");
        String blockCode = visit(context.statement());
        if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());
        sb.append(blockCode + "\n");
        return sb.toString();
    }

    @Override
    public String visitForVar(OurGrammarParser.ForVarContext context) {
        String type = context.typeRef().TYPE().getText();
        String id = context.ID().getText();

        if (context.assFunc() != null) {
            // Function definition
            String params = visit(context.assFunc());     // Let assFunc generate the parameter list
            String blockCode = visit(context.assFunc().block());
            // If blockCode starts with the current indent, strip it so the '{' lands
            // directly after the function header (`void main() {`).
            if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());

            return type + " " + id + params + blockCode;
        } 
        else if (context.assVar() != null) {
            String exprCode = visit(context.assVar().expr());
            return type + " " + id + " = " + exprCode;
        } 
        else {
            return "";
        }
    }

    @Override
    public String visitWhileStatement(OurGrammarParser.WhileStatementContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append("while (").append(visit(context.expr())).append(") ");
        String blockCode = visit(context.statement());
        if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());
        sb.append(blockCode + "\n");
        return sb.toString();
    }

    @Override
    public String visitBreakStatement(OurGrammarParser.BreakStatementContext context) {
        return indent() + "break;\n";
    }

    @Override
    public String visitContinueStatement(OurGrammarParser.ContinueStatementContext context) {
        return indent() + "continue;\n";
    }

    @Override
    public String visitBlock(OurGrammarParser.BlockContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append("{\n");

        ctx.symbolTable.restoreScope(context);
        if (inMain) { // Main block
            sb.append(indent() + "public static void main(String[] args) {\n");
            ctx.symbolTable.restoreScope(context);
            sb.append(indent() + "Scanner scanner = new Scanner(System.in);\n");
            sb.append(indent() + "ExecutorService executor = Executors.newCachedThreadPool();\n");
            inMain = false;
            for (OurGrammarParser.StatementContext stmt : context.statement()) {
                String stmtCode = visit(stmt);
                if (stmtCode == null || stmtCode.isEmpty()) continue;
                sb.append(stmtCode);
            }
            sb.append(indent() + "executor.shutdown();\n");
            sb.append(indent() + "scanner.close();\n");
            ctx.symbolTable.popScope();
            sb.append(indent()).append("}\n");
        } else { // All other blocks
            for (OurGrammarParser.StatementContext stmt : context.statement()) {
                String stmtCode = visit(stmt);
                if (stmtCode == null || stmtCode.isEmpty()) continue;
                sb.append(stmtCode);
            }
        }
        ctx.symbolTable.popScope();

        sb.append(indent()).append("}");
        return sb.toString();
    }

    @Override
    public String visitReturnStatement(OurGrammarParser.ReturnStatementContext context) {
        if (context.expr() != null) {
            return indent() + "return " + visit(context.expr()) + ";\n";
        }
        return indent() + "return;\n";
    }

    @Override
    public String visitPrintStatement(OurGrammarParser.PrintStatementContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append("System.out.print(");
        for (int i = 0; i < context.expr().size(); i++) {
            sb.append(visit(context.expr(i)));
            if (i < context.expr().size() - 1) sb.append(" + ");
        }
        sb.append(");\n");
        return sb.toString();
    }

    @Override
    public String visitRead(OurGrammarParser.ReadContext context) {
        if (context.TYPE() == null) {
            throw new RuntimeException("Expected a type in read statement.");
        }
        switch (context.TYPE().getText()) {
            case "int":
                return "scanner.nextInt()";
            case "float":
                return "scanner.nextFloat()";
            case "bool":
                return "scanner.nextBoolean()";
            case "string":
                return "scanner.nextLine()";
            default:
                throw new RuntimeException("Unsupported type in read statement: " + context.TYPE().getText());
        }
    }

    @Override
    public String visitExpr(OurGrammarParser.ExprContext context) {
        String left = visit(context.equal());
        if (context.expr() != null) {
            String op = context.getChild(1).getText();
            String right = visit(context.expr());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitEqual(OurGrammarParser.EqualContext context) {
        String left = visit(context.comp());
        if (context.equal() != null) {
            String op = context.getChild(1).getText();
            String right = visit(context.equal());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitComp(OurGrammarParser.CompContext context) {
        String left = visit(context.additive());
        if (context.comp() != null) {
            String op = context.getChild(1).getText();
            String right = visit(context.comp());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitAdditive(OurGrammarParser.AdditiveContext context) {
        String left = visit(context.mult());
        if (context.additive() != null) {
            String op = context.getChild(1).getText();
            String right = visit(context.additive());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitMult(OurGrammarParser.MultContext context) {
        String left = visit(context.power());
        if (context.mult() != null) {
            String op = context.getChild(1).getText();
            String right = visit(context.mult());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitPower(OurGrammarParser.PowerContext context) {
        String left = visit(context.factor());
        if (context.power() != null) {
            String op = context.getChild(1).getText();
            String right = visit(context.power());
            return left + " " + op + " " + right;
        }
        return left;
    }

    @Override
    public String visitArrayLiteral(OurGrammarParser.ArrayLiteralContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < context.expr().size(); i++) {
            sb.append(visit(context.expr(i)));
            if (i < context.expr().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String visitArrayIndex(OurGrammarParser.ArrayIndexContext context) {
        String base = visit(context.expr(0));
        String index = visit(context.expr(1));
        return base + "[" + index + "]";
    }

    @Override
    public String visitFactor(OurGrammarParser.FactorContext context) {
        // Prefer checking specific child contexts instead of relying on the
        // start token: several alternatives (functionCall, arrayIndex) start
        // with an ID token and would otherwise be mis-dispatched.
        if (context.NEGATIVE() != null) {
            return "-" + visit(context.factor());
        }
        if (context.functionCall() != null) {
            return visitFunctionCall(context.functionCall());
        }
        if (context.arrayIndex() != null) {
            return visitArrayIndex(context.arrayIndex());
        }
        if (context.arrayLiteral() != null) {
            return visitArrayLiteral(context.arrayLiteral());
        }
        if (context.ID() != null) {
            return context.ID().getText();
        }
        if (context.INT() != null) {
            return context.INT().getText();
        }
        if (context.FLOAT() != null) {
            return context.FLOAT().getText();
        }
        if (context.BOOL() != null) {
            return context.BOOL().getText();
        }
        if (context.CHAR() != null) {
            return context.CHAR().getText();
        }
        if (context.STRING() != null) {
            return context.STRING().getText();
        }
        if (context.THREAD() != null) {
            return context.THREAD().getText();
        }
        if (context.castExpression() != null) {
            OurGrammarParser.CastExpressionContext castCtx = context.castExpression();
            String targetType = castCtx.TYPE().getText().toLowerCase();
            String expr = visit(castCtx.expr());
            return "(" + targetType + ") " + expr;
        }
        if (context.expr() != null) {
            // parenthesised expression
            return "(" + visit(context.expr()) + ")";
        }

        return visitChildren(context);
    }

    @Override
    public String visitFunctionCall(OurGrammarParser.FunctionCallContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.ID().getText());
        sb.append("(");
        if (context.expr() != null && !context.expr().isEmpty()) {
            for (int i = 0; i < context.expr().size(); i++) {
                sb.append(visit(context.expr(i)));
                if (i < context.expr().size() - 1) sb.append(", ");
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
