package p4project.visitors;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;
import p4project.context.FunctionSymbol;
import p4project.context.Symbol;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Collections;

/*
    Phase 1: Symbol assignments and declerations
    Phase 2: Reference linking
    Phase 3: Type checking
    Phase 4: vtable and ftable generation
    Phase 5: Mutex and deep nested critical section checking
    -> Phase 6: Java Code Gen
*/

public class CodeGenVisitor extends OurGrammarBaseVisitor<String> {
    private final CompilationContext ctx;
    private boolean inMain = false;
    private boolean inCriticalSection = false;
    private boolean inFuncAssignment = false;
    private List<Integer> sharedIndexes = new ArrayList<>(); // To track the index of shared variables for mutex naming
    private List<Integer> mutexList = new ArrayList<>(); // To track which mutexes are currently aquired.
    
    public CodeGenVisitor(CompilationContext ctx) {
        this.ctx = ctx;
    }
    
    private static final String INDENT = "    ";
    
    private String indent() {
        if (!ctx.ftable.containsKey("main")) {
            return INDENT.repeat(Math.max(0, ctx.symbolTable.getDepth()+2)); // compensate for the extra indent level of the generated main method
        }
        return INDENT.repeat(Math.max(0, ctx.symbolTable.getDepth()+1)); // Ensure non-negative repeat count
        
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
            String primeCode = visit(context.statementPrime());
            if (context.statementPrime().expr() != null) {
                return indent() + primeCode + ";\n";
            }
            return primeCode;
        }
        return visitChildren(context);
    }

    @Override
    public String visitStatementPrime(OurGrammarParser.StatementPrimeContext context) {
        return visitChildren(context);
    }

    @Override
    public String visitAssignment(OurGrammarParser.AssignmentContext context) {
        String type = context.typeRef().TYPE().getText();
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id);
        if (symbol.arrType != null) {
            type = symbol.arrType.toString();
        }

        if (context.assFunc() != null) {
            inFuncAssignment = true;
            // Check if this is the main function to set the inMain flag.
            if (id.equals("main")) {
                inMain = true;
                String blockCode = visit(context.assFunc().block());
                if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());
                return indent() + "public static void main(String[] args) " + blockCode + "\n";
            }

            // Function definition
            String params = visit(context.assFunc());
            String blockCode = visit(context.assFunc().block());
            inFuncAssignment = false;
            if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());
            return indent() + "public static " + type + " " + id + params + blockCode + "\n";
        } 
        else if (context.assVar() != null) {
            String exprCode = visit(context.assVar().expr());
            if (this.ctx.symbolTable.resolve(id).arrType != null) {
                String arrayPrefix = "" + symbol.type.name.toLowerCase();
                if (symbol.arrType != null) {
                    for (String i : symbol.arrType.dimSize) {
                        arrayPrefix += "[]";
                    }
                }
                return indent() + arrayPrefix + " " + id + " = " + "new " + arrayPrefix + exprCode + ";\n";
            }
            else return indent() + type + " " + id + " = " + exprCode + ";\n";
        } 
        else {
            return "";
        }
    }

    @Override
    public String visitReassignment(OurGrammarParser.ReassignmentContext context) {
        // check if it is called in a for header
        String id = "";
        if (context.ID() == null) {
            id = context.arrayIndex().ID().getText();
        } else {
            id = context.ID().getText();
        }
        Symbol symbol = this.ctx.symbolTable.resolve(id);
        if (this.ctx.symbolTable.getNodeScope().getText().contains("for")) {
            return id + " = " + visit(context.expr());

        } else if (symbol.arrType != null) {
            int equals = context.getText().indexOf('=');
            String beforeEquals = context.getText().substring(0, equals);
            String afterEquals = context.getText().substring(equals + 1);
            String bracketType = (afterEquals.contains("[")) ? "[" : (afterEquals.contains("{")) ? "{" : "";
            
            if (beforeEquals.contains("[")) {
                return indent() + beforeEquals + " = " + visit(context.expr()) + ";\n";
            }

            if (bracketType.equals("[")) { // Handle array resizing with the new size of the array.
                return "";
                // We do not reassign this value, because it is already defined, because the value is of the same scope
                // and we reassign the size of each dimension in RefLinkingVisitor.
                //return indent() + id + " = new " + symbol.type.name + afterEquals + ";\n";

            } else if (bracketType.equals("{")) { // Handle array resizing with actual input.
                String brackets = "";
                for (int i = 0; i < symbol.arrType.dimensions; i++) {
                    brackets += "[]";
                }
                return indent() + id + " = new " + symbol.type.name + brackets + visit(context.expr()) + ";\n";

            } else if (this.ctx.symbolTable.resolve(context.expr().getText()).arrType != null) { 
                return indent() + context.ID().getText() + " = " + visit(context.expr()) + ".clone();\n";
            } else {
                throw new RuntimeException("Cannot assign non-array value to array variable '" + id + "'");
            }
        }
        return indent() + context.ID().getText() + " = " + visit(context.expr()) + ";\n";
    }
    

    @Override
    public String visitDeclaration(OurGrammarParser.DeclarationContext context) {
        String id = context.ID().getText();
        Symbol symbol = this.ctx.symbolTable.resolve(id);
        if (symbol.arrType != null) {
            String brackets = "";
            for (int i = 0; i < symbol.arrType.dimensions; i++) {
                brackets += "[]";
            }
            return indent() + symbol.type.name + brackets + " " + id + " = new " + symbol.arrType.toString() + ";\n";
        }
        String type = context.typeRef().TYPE().getText();
        switch(type) {
            case "int":
                return indent() + type + " " + id + " = 0;\n";
            case "float":
                return indent() + type + " " + id + " = 0f;\n";
            case "bool":
                return indent() + type + " " + id + " = NULL;\n";
            case "string":
                return indent() + type + " " + id + " = " + " " +  ";\n";
            case "char":
                return indent() + type + " " + id + " = '\\u0000';\n"; // default char value
            default:
                return indent() + type + " " + id + ";\n";
        }
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
    public String visitCriticalSection(OurGrammarParser.CriticalSectionContext context) {
        StringBuilder sb = new StringBuilder();
        if(!inFuncAssignment) {
            for (TerminalNode id : context.ID()) {
                Integer index = ctx.sharedVariables.indexOf(id.getText());
                sharedIndexes.add(index);
            } sharedIndexes.sort(Integer::compareTo); // Ensure locks are always acquired in the same order to prevent deadlocks
            inCriticalSection = true;
        }
        String blockCode = visit(context.block());
        if (blockCode.startsWith(indent())) blockCode = blockCode.substring(indent().length());
        sb.append(indent() + blockCode + "\n");
        return sb.toString();
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

        if (context.assVar() != null) {
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
            sb.append(indent() + "Scanner scanner = new Scanner(System.in);\n");
            sb.append(indent() + "ExecutorService executor = Executors.newCachedThreadPool();\n");

            for (String shared : ctx.sharedVariables) {
                sb.append(indent() +"Lock " + "m" + ctx.sharedVariables.indexOf(shared) + " = new ReentrantLock();\n");
            }

            inMain = false;
            for (OurGrammarParser.StatementContext stmt : context.statement()) {
                String stmtCode = visit(stmt);
                if (stmtCode == null || stmtCode.isEmpty()) continue;
                sb.append(stmtCode);
            }
            sb.append(indent() + "executor.shutdown();\n");
            sb.append(indent() + "scanner.close();\n");
        } else if (inCriticalSection) {
            inCriticalSection = false;

            List<Integer> mutexLocalList = new ArrayList<>(sharedIndexes); // To track which mutexes we want to acquire.
            List<Integer> unlockableMutexes = new ArrayList<>(mutexLocalList); // To track which mutexes are not needed in the parent scope.
            sharedIndexes = new ArrayList<>(); // Reset sharedIndexes for the next critical section
            for (int index : new ArrayList<>(unlockableMutexes)) {
                if (mutexList.contains(index)) {
                    mutexLocalList.removeAll(Collections.singletonList(index)); // Remove from mutexLocalList to avoid trying to re-aquire it in this critical section
                    unlockableMutexes.removeAll(Collections.singletonList(index)); // Remove from unlockableMutexes if it's already aquired in an outer critical section
                    mutexLocalList.sort(Integer::compareTo);
                    unlockableMutexes.sort(Integer::compareTo);
                }
            }
            
            unlockableMutexes.sort(Integer::compareTo);
            mutexLocalList.sort(Integer::compareTo);
            for (int value : mutexList) {
                if (!mutexList.isEmpty() && !mutexLocalList.isEmpty() && mutexLocalList.get(0) < value) {
                    
                    for (int mutex : mutexList) {
                        sb.append(indent() + "m" + mutex + ".unlock();\n");
                        mutexLocalList.add(mutex);
                    }
                    mutexLocalList.sort(Integer::compareTo); // Ensure locks are always released in the same order they were acquired to prevent deadlocks.
                    break;
                } 
            }
            // Update the global mutexList to reflect the currently aquired locks after unlocking those that needed to be released.
            mutexList = new ArrayList<>(mutexLocalList); 
            
            // Acquire locks in the current critical section using a spinlock approach with a exponentially increasing sleep time to reduce CPU contention.
            for (int index : mutexLocalList) { 
                sb.append(indent() + "for (double i = 100; !m" + index + ".tryLock(); i = i*1.2-((i*1.2)%1)) Thread.sleep((long) i);\n");
            }
            sb.append(indent() + "try {\n");
            for (OurGrammarParser.StatementContext stmt : context.statement()) {
                String stmtCode = visit(stmt);
                if (stmtCode == null || stmtCode.isEmpty()) continue;
                sb.append("    " + stmtCode);
            }
            sb.append(indent() + "} finally {\n");
            for (int index : unlockableMutexes) {
                sb.append(indent() + "    " + "m" + index + ".unlock();\n");
            }
            mutexList.removeAll(unlockableMutexes); // Remove the unlocked mutexes from the global mutexList to reflect that they are no longer aquired.
            sb.append(indent() + "}\n");


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
            String right = visit(context.power());
            return "(float)Math.pow(" + left + ", " + right + ")";
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
        String id = context.ID().getText();
        StringBuilder sb = new StringBuilder();
        for (OurGrammarParser.ExprContext index : context.expr()) {
            sb.append("[" + visit(index) + "]"); 
        }
        return id + sb.toString();
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
            return context.FLOAT().getText() + "f";
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
        FunctionSymbol funcSymbol = (FunctionSymbol) ctx.ftable.get(context.ID().getText());
        if (funcSymbol.containsCriticalSection) {
            if (inCriticalSection) {
                throw new RuntimeException("Cannot call function '" + funcSymbol.toString() + "' from a critical section because it contains a critical section itself.");
            }
            for (OurGrammarParser.ExprContext expr : context.expr()) {
                String arg = expr.getText();
                if (this.ctx.sharedVariables.contains(arg)) {
                    Integer index = ctx.sharedVariables.indexOf(arg);
                    sharedIndexes.add(index);
                }
            }
            sharedIndexes.sort(Integer::compareTo); // Ensure locks are always acquired in the same order to prevent deadlocks
            
            List<Integer> mutexLocalList = new ArrayList<>(sharedIndexes); // To track which mutexes we want to acquire.
            List<Integer> unlockableMutexes = new ArrayList<>(mutexLocalList); // To track which mutexes are not needed in the parent scope.
            sharedIndexes = new ArrayList<>(); // Reset sharedIndexes for the next critical section
            for (int index : new ArrayList<>(unlockableMutexes)) {
                if (mutexList.contains(index)) {
                    mutexLocalList.removeAll(Collections.singletonList(index)); // Remove from mutexLocalList to avoid trying to re-aquire it in this critical section
                    unlockableMutexes.removeAll(Collections.singletonList(index)); // Remove from unlockableMutexes if it's already aquired in an outer critical section
                    mutexLocalList.sort(Integer::compareTo);
                    unlockableMutexes.sort(Integer::compareTo);
                }
            }
            
            unlockableMutexes.sort(Integer::compareTo);
            mutexLocalList.sort(Integer::compareTo);
            for (int value : mutexList) {
                if (!mutexList.isEmpty() && !mutexLocalList.isEmpty() && mutexLocalList.get(0) < value) {
                    
                    for (int mutex : mutexList) {
                        sb.append(indent() + "m" + mutex + ".unlock();\n");
                        mutexLocalList.add(mutex);
                    }
                    mutexLocalList.sort(Integer::compareTo); // Ensure locks are always released in the same order they were acquired to prevent deadlocks.
                    break;
                } 
            }
            // Update the global mutexList to reflect the currently aquired locks after unlocking those that needed to be released.
            mutexList = new ArrayList<>(mutexLocalList); 
            
            // Acquire locks in the current critical section using a spinlock approach with a exponentially increasing sleep time to reduce CPU contention.
            boolean firstLine = true;
            for (int index : mutexLocalList) { 
                if (!firstLine) sb.append(indent());
                sb.append("for (double i = 100; !m" + index + ".tryLock(); i = i*1.2-((i*1.2)%1)) Thread.sleep((long) i);\n");
                firstLine = false;
            }
            if (!firstLine) sb.append(indent());
            sb.append("try {\n");

            //Construct the function call as usual here.
            sb.append(indent()).append("    ").append(context.ID().getText());
            sb.append("(");
            if (context.expr() != null && !context.expr().isEmpty()) {
                for (int i = 0; i < context.expr().size(); i++) {
                    sb.append(visit(context.expr(i)));
                    if (i < context.expr().size() - 1) sb.append(", ");
                }
            }
            sb.append(")\n");
            
            sb.append(indent()).append("} finally {\n");
            for (int index : unlockableMutexes) {
                sb.append(indent()).append("    m").append(index).append(".unlock();\n");
            }
            mutexList.removeAll(unlockableMutexes); // Remove the unlocked mutexes from the global mutexList to reflect that they are no longer aquired.
            sb.append(indent()).append("}");

            return sb.toString();
        }
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
