package p4project;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import p4project.context.CompilationContext;
import p4project.context.TypeSymbol;
import p4project.context.VariableSymbol;
import p4project.visitors.AssDecVisitor;
import p4project.visitors.CodeGenVisitor;
import p4project.visitors.FtableGenVisitor;
import p4project.visitors.RefLinkingVisitor;
import p4project.visitors.TypeCheckingVisitor;

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

    @ParameterizedTest(name = "Testing assignment: {0}")
    @CsvSource({
        "'int i = 5;', 'i', 'int', false, false",
        "'const int j = 10;', 'j', 'int', true, false",
        "'shared float radius = 3.14;', 'radius', 'float', false, true"
    })
    void testAssDecVisitorAssignment(String input, String expectedVarName, String expectedType, boolean expectConst, boolean expectShared) {
        System.out.println("========== Running testAssDecVisitorAssignment for: " + input + " ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment(input);
        ctx.symbolTable.pushScope(assignmentCtx); // Dummy global scope

        AssDecVisitor visitor = new AssDecVisitor(ctx);
        visitor.visitAssignment(assignmentCtx);

        try {
            // Verify that variable was added to the symbol table
            VariableSymbol symbol = (VariableSymbol) ctx.symbolTable.resolve(expectedVarName);
            assertNotNull(symbol, "Variable '" + expectedVarName + "' should be in the symbol table.");
            assertEquals(expectedType, symbol.type.name, "Type of '" + expectedVarName + "' should be '" + expectedType + "'.");
            assertEquals(expectConst, symbol.isConst(), "Const prefix check failed for '" + expectedVarName + "'.");
            assertEquals(expectShared, symbol.isShared(), "Shared prefix check failed for '" + expectedVarName + "'.");
            System.out.println("testAssDecVisitorAssignment = success! Variable '" + expectedVarName + "' added correctly");
        } catch (AssertionError e) {
            System.out.println("testAssDecVisitorAssignment = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @ParameterizedTest(name = "Testing ref linking assignment: {0}")
    @CsvSource({
        "'int j = 10;', 'j', 'int'",
        "'float radius = 3.14;', 'radius', 'float'",
        "'shared float radius = 3.14;', 'radius', 'float'"
    })
    void testRefLinkingVisitorAssignment(String input, String expectedVarName, String expectedTypeName) {
        System.out.println("========== Running testRefLinkingVisitorAssignment for: " + input + " ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment(input);
        ctx.symbolTable.pushScope(assignmentCtx);
        
        // Simulating data received from AssDecVisitor
        ctx.symbolTable.define(new VariableSymbol(expectedVarName, TypeSymbol.fromString(expectedTypeName)));

        RefLinkingVisitor visitor = new RefLinkingVisitor(ctx);
        visitor.visitAssignment(assignmentCtx);

        try {
            // Verify resolved types are tracked
            assertFalse(ctx.resolvedSymbols.isEmpty(), "Reference linking should resolve symbols.");
            assertEquals(expectedVarName, ctx.resolvedSymbols.get(assignmentCtx.ID()).ID, "Symbol '" + expectedVarName + "' should be resolved correctly.");
            System.out.println("testRefLinkingVisitorAssignment = success! Symbol '" + expectedVarName + "' resolved properly");
        } catch (AssertionError e) {
            System.out.println("testRefLinkingVisitorAssignment = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @ParameterizedTest(name = "Testing type checking assignment: {0}")
    @CsvSource({
        "'int k = 15;', 'k', 'int'",
        "'float radius = 3.14;', 'radius', 'float'",
        "'shared float radius = 3.14;', 'radius', 'float'"
    })
    void testTypeCheckingVisitorAssignment(String input, String expectedVarName, String expectedTypeName) {
        System.out.println("========== Running testTypeCheckingVisitorAssignment for: " + input + " ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment(input);
        ctx.symbolTable.pushScope(assignmentCtx);

        // Predefine data expected from past phases
        VariableSymbol kSym = new VariableSymbol(expectedVarName, TypeSymbol.fromString(expectedTypeName));
        ctx.symbolTable.define(kSym);
        ctx.resolvedSymbols.put(assignmentCtx.ID(), kSym);

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

    @ParameterizedTest(name = "Testing ftable gen assignment: {0}")
    @CsvSource({
        "'int l = 20;', 'l', 'int'",
        "'float radius = 3.14;', 'radius', 'float'",
        "'shared float radius = 3.14;', 'radius', 'float'"
    })
    void testFtableGenVisitorAssignment(String input, String expectedVarName, String expectedTypeName) {
        System.out.println("============ Running testFtableGenVisitorAssignment for: " + input + " ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment(input);
        ctx.symbolTable.pushScope(assignmentCtx);

        // Predefine data expected from past phases
        VariableSymbol lSym = new VariableSymbol(expectedVarName, TypeSymbol.fromString(expectedTypeName));
        ctx.symbolTable.define(lSym);
        ctx.resolvedSymbols.put(assignmentCtx.ID(), lSym);

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

    @ParameterizedTest(name = "Testing code gen assignment: {0}")
    @CsvSource({
        "'int m = 25;', 'm', 'int', 'int m = 25;'",
        "'float radius = 3.14;', 'radius', 'float', 'float radius = 3.14;'",
        "'shared float radius = 3.14;', 'radius', 'float', 'float radius = 3.14;'"
    })
    void testCodeGenVisitorAssignment(String input, String expectedVarName, String expectedTypeName, String expectedCodeContent) {
        System.out.println("========== Running testCodeGenVisitorAssignment for: " + input + " ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment(input);
        ctx.symbolTable.pushScope(assignmentCtx);

        // Predefine data expected from past phases
        VariableSymbol mSym = new VariableSymbol(expectedVarName, TypeSymbol.fromString(expectedTypeName));
        ctx.symbolTable.define(mSym);
        ctx.resolvedSymbols.put(assignmentCtx.ID(), mSym);

        CodeGenVisitor visitor = new CodeGenVisitor(ctx);
        String generatedCode = visitor.visitAssignment(assignmentCtx);
        
        try {
            assertNotNull(generatedCode, "CodeGenVisitor should return a non-null string.");
            assertTrue(generatedCode.contains(expectedCodeContent), "Generated code should contain '" + expectedCodeContent + "', but got: " + generatedCode);
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
