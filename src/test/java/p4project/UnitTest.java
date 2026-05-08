package p4project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import p4project.context.CompilationContext;
import p4project.context.TypeSymbol;
import p4project.context.VariableSymbol;
import p4project.visitors.AssDecVisitor;
import p4project.visitors.CodeGenVisitor;
import p4project.visitors.FtableGenVisitor;
import p4project.visitors.RefLinkingVisitor;
import p4project.visitors.TypeCheckingVisitor;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    private CompilationContext ctx;

    @BeforeEach
    void setUp() {
        ctx = new CompilationContext();
    }

    private OurGrammarParser.AssignmentContext parseAssignment(String input) {
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);
        return parser.assignment();
    }

    private OurGrammarParser.IfStatementContext parseIfStatement(String input) {
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);
        return parser.ifStatement();
    }

    private OurGrammarParser.FactorContext parseFactor(String input) {
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);
        return parser.factor();
    }

    @Test
    void testAssDecVisitorAssignment() {
        System.out.println("========== Running testAssDecVisitorAssignment ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment("int i = 5;");
        ctx.symbolTable.pushScope(assignmentCtx); // Dummy global scope

        AssDecVisitor visitor = new AssDecVisitor(ctx);
        visitor.visitAssignment(assignmentCtx);

        try {
            // Verify that variable 'i' was added to the symbol table
            VariableSymbol symbol = (VariableSymbol) ctx.symbolTable.resolve("i");
            assertNotNull(symbol, "Variable 'i' should be in the symbol table.");
            assertEquals("int", symbol.type.name, "Type of 'i' should be 'int'.");
            System.out.println("testAssDecVisitorAssignment = success! Variable 'i' added correctly");
        } catch (AssertionError e) {
            System.out.println("testAssDecVisitorAssignment = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @Test
    void testRefLinkingVisitorAssignment() {
        System.out.println("========== Running testRefLinkingVisitorAssignment ==========");
        // Setup table with existing int type if needed by the phase
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment("int j = 10;");
        ctx.symbolTable.pushScope(assignmentCtx);
        
        // Setup initial phase
        AssDecVisitor assDecVisitor = new AssDecVisitor(ctx);
        assDecVisitor.visitAssignment(assignmentCtx);

        RefLinkingVisitor visitor = new RefLinkingVisitor(ctx);
        visitor.visitAssignment(assignmentCtx);

        try {
            // Verify resolved types are tracked
            assertFalse(ctx.resolvedSymbols.isEmpty(), "Reference linking should resolve symbols.");
            System.out.println("testRefLinkingVisitorAssignment = success! Symbols resolved properly");
        } catch (AssertionError e) {
            System.out.println("testRefLinkingVisitorAssignment = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @Test
    void testTypeCheckingVisitorAssignment() {
        System.out.println("========== Running testTypeCheckingVisitorAssignment ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment("int k = 15;");
        ctx.symbolTable.pushScope(assignmentCtx);

        new AssDecVisitor(ctx).visitAssignment(assignmentCtx);
        new RefLinkingVisitor(ctx).visitAssignment(assignmentCtx);

        TypeCheckingVisitor visitor = new TypeCheckingVisitor(ctx);
        try {
            // Expecting type name "int" or no exceptions, depending on implementation
            assertDoesNotThrow(() -> visitor.visitAssignment(assignmentCtx));
            System.out.println("testTypeCheckingVisitorAssignment = success! Type check passed without errors");
        } catch (AssertionError e) {
            System.out.println("testTypeCheckingVisitorAssignment = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @Test
    void testFtableGenVisitorAssignment() {
        System.out.println("============ Running testFtableGenVisitorAssignment ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment("int l = 20;");
        ctx.symbolTable.pushScope(assignmentCtx);

        new AssDecVisitor(ctx).visitAssignment(assignmentCtx);
        new RefLinkingVisitor(ctx).visitAssignment(assignmentCtx);
        new TypeCheckingVisitor(ctx).visitAssignment(assignmentCtx);

        FtableGenVisitor visitor = new FtableGenVisitor(ctx);
        try {
            assertDoesNotThrow(() -> visitor.visitAssignment(assignmentCtx), "FtableGenVisitor should not fail on basic assignment.");
            System.out.println("testFtableGenVisitorAssignment = success! Ftable generated without errors");
        } catch (AssertionError e) {
            System.out.println("testFtableGenVisitorAssignment = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @Test
    void testCodeGenVisitorAssignment() {
        System.out.println("========== Running testCodeGenVisitorAssignment ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment("int m = 25;");
        ctx.symbolTable.pushScope(assignmentCtx);

        new AssDecVisitor(ctx).visitAssignment(assignmentCtx);
        new RefLinkingVisitor(ctx).visitAssignment(assignmentCtx);
        new TypeCheckingVisitor(ctx).visitAssignment(assignmentCtx);
        new FtableGenVisitor(ctx).visitAssignment(assignmentCtx);

        CodeGenVisitor visitor = new CodeGenVisitor(ctx);
        String generatedCode = visitor.visitAssignment(assignmentCtx);
        
        try {
            assertNotNull(generatedCode, "CodeGenVisitor should return a non-null string.");
            assertTrue(generatedCode.contains("int m = 25;"), "Generated code should contain 'int m = 25;'");
            System.out.println("testCodeGenVisitorAssignment = success! Generated matching java code string");
        } catch (AssertionError e) {
            System.out.println("testCodeGenVisitorAssignment = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @Test
    void testCodeGenVisitorIfStatement() {
        System.out.println("========== Running testCodeGenVisitorIfStatement ==========");
        OurGrammarParser.IfStatementContext ifStmtCtx = parseIfStatement("if (true) { int n = 30; }");
        ctx.symbolTable.pushScope(ifStmtCtx);

        new AssDecVisitor(ctx).visitIfStatement(ifStmtCtx);
        new RefLinkingVisitor(ctx).visitIfStatement(ifStmtCtx);
        new TypeCheckingVisitor(ctx).visitIfStatement(ifStmtCtx);
        new FtableGenVisitor(ctx).visitIfStatement(ifStmtCtx);

        CodeGenVisitor visitor = new CodeGenVisitor(ctx);
        String generatedCode = visitor.visitIfStatement(ifStmtCtx);
        
        try {
            assertNotNull(generatedCode, "CodeGenVisitor should return a non-null string.");
            String collapsedCode = generatedCode.replaceAll("\\s+", " ").trim();
            assertTrue(collapsedCode.contains("if (true) { int n = 30; }"), "Generated code should contain 'if (true) { int n = 30; }'");

            System.out.println("testCodeGenVisitorIfStatement = success! Generated matching java code string");
        } catch (AssertionError e) {
            System.out.println("testCodeGenVisitorIfStatement = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @Test
    void testTypeCheckingVisitorFactor() {
        // Test for cast Expression
        System.out.println("========== Running testTypeCheckingVisitorFactor (casting) ==========");
        OurGrammarParser.FactorContext factorCtx = parseFactor("cast(int) 3.14");
        
        ctx.symbolTable.pushScope(factorCtx);
        new AssDecVisitor(ctx).visitFactor(factorCtx);
        new RefLinkingVisitor(ctx).visitFactor(factorCtx);
        TypeCheckingVisitor visitor = new TypeCheckingVisitor(ctx);
        try {
            assertDoesNotThrow(() -> visitor.visitFactor(factorCtx), "Type checking should allow valid cast expressions.");
            System.out.println("testTypeCheckingVisitorFactor = success! Type check passed for cast expression");
        } catch (AssertionError e) {
            System.out.println("testTypeCheckingVisitorFactor = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @Test
    void testCodeGenVisitorFactor() {
        // Test for cast Expression
        System.out.println("========== Running testCodeGenVisitorFactor (casting) ==========");
        OurGrammarParser.FactorContext factorCtx = parseFactor("cast(int) 2.718");
        ctx.symbolTable.pushScope(factorCtx);

        // TODO - extract the correct data to a json file or similar
        new AssDecVisitor(ctx).visitFactor(factorCtx);
        new RefLinkingVisitor(ctx).visitFactor(factorCtx);
        new TypeCheckingVisitor(ctx).visitFactor(factorCtx);
        new FtableGenVisitor(ctx).visitFactor(factorCtx);

        CodeGenVisitor visitor = new CodeGenVisitor(ctx);
        String generatedCode = visitor.visitFactor(factorCtx);
        
        try {
            assertNotNull(generatedCode, "CodeGenVisitor should return a non-null string.");
            assertTrue(generatedCode.contains("(int) 2.718"), "Generated code should contain the java cast or expected output");

            System.out.println("testCodeGenVisitorFactor = success! Generated matching java code string");
        } catch (AssertionError e) {
            System.out.println("testCodeGenVisitorFactor = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }
}
