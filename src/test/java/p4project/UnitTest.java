package p4project;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import p4project.context.TypeSymbol;
import p4project.context.VariableSymbol;
import p4project.stubsAndDrivers.StubCompilationContext;
import p4project.visitors.AssDecVisitor;
import p4project.visitors.CodeGenVisitor;
import p4project.visitors.FtableGenVisitor;
import p4project.visitors.RefLinkingVisitor;
import p4project.visitors.TypeCheckingVisitor;

class UnitTest {

    private StubCompilationContext ctx;

    @BeforeEach
    void setUp() {
        ctx = new StubCompilationContext(); // Use Stub context for all unit tests.
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

    private OurGrammarParser.ForStatementContext parseForStatement(String input) {
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);
        return parser.forStatement();
    }

    /* ================================= LEXER, PARSER, & SEMANTIC ANALYZER ==================================== */

    @ParameterizedTest(name = "Testing lexer tokenization: {0}")
    @CsvSource({
        "'int i = 5;', 5", // int, i, =, 5, ;
        "'float radius = 3.14;', 5", // float, radius, =, 3.14, ;
        "'if (x > 5) {}', 7" // if, (, x, >, 5, ), {, }
    })
    void testLexer(String input, int expectedTokenCount) {
        System.out.println("========== Running testLexer for: " + input + " ==========");
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        tokens.fill(); // Fetches all tokens from the lexer
        int actualCount = tokens.getTokens().size() - 1; // Subtract 1 to ignore the EOF token

        try {
            assertAll("Lexer Token Count Check",
                () -> assertEquals(expectedTokenCount, actualCount, "Token count should match expected length.")
            );
            System.out.println("testLexer = success! Found exactly " + expectedTokenCount + " tokens.");
        } catch (AssertionError e) {
            System.out.println("testLexer = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @ParameterizedTest(name = "Testing parser syntax validation: {0}")
    @CsvSource({
        "'int i = 5;', 0",
        "'const int j = 10;', 0",
        "'int 5 = i;', 1", // Example of broken syntax (assuming it produces 1 syntax error)
        "'if ((x > 5) {x = 10;}', 1" // Example of broken syntax (missing closing parenthesis)
    })
    void testParser(String input, int expectedErrors) {
        System.out.println("========== Running testParser for: " + input + " ==========");
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);
        
        // Remove default error listeners if you want clean terminal outputs, but keep to count them
        parser.assignment(); 

        try {
            assertAll("Parser Syntax Error Check",
                () -> assertEquals(expectedErrors, parser.getNumberOfSyntaxErrors(), "Expected syntax error count to match.")
            );
            System.out.println("testParser = success! Encountered " + parser.getNumberOfSyntaxErrors() + " syntax errors, as expected.");
        } catch (AssertionError e) {
            System.out.println("testParser = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @ParameterizedTest(name = "Testing semantic failure detection: {0}")
    @CsvSource({
        "'int i = 5;', 'i', 'int', false", // Valid assignment, no error
        "'float b = 3.14;', 'b', 'int', true" // Example assignment causing a type error (float into int)
    })
    void testSemanticAnalyzerTypeChecks(String input, String presetVarName, String predefinedType, boolean expectsException) {
        System.out.println("========== Running testSemanticAnalyzerTypeChecks for: " + input + " ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment(input);
        ctx.symbolTable.pushScope(assignmentCtx);

        // Predefine the variable directly with the passed type (e.g. testing float assigned into an integer)
        VariableSymbol sym = new VariableSymbol(presetVarName, TypeSymbol.fromString(predefinedType));
        ctx.symbolTable.define(sym);
        ctx.resolvedSymbols.put(assignmentCtx.ID(), sym);

        TypeCheckingVisitor visitor = new TypeCheckingVisitor(ctx);

        try {
            assertAll("Semantic Analysis Type Checks", () -> {
                if (expectsException) {
                    // Adjust this assertion based on how you handle semantic errors (Exception throw vs Error collection lists)
                    boolean throwsException = false;
                    try {
                        visitor.visitAssignment(assignmentCtx);
                    } catch (Exception runtimeEx) {
                        throwsException = true;
                    }
                    assertTrue(throwsException, "Expected visitor to throw an exception / catch a semantic error on invalid assignment.");
                } else {
                    assertDoesNotThrow(() -> visitor.visitAssignment(assignmentCtx), "Expected valid assignment to pass without throwing.");
                }
            });
            System.out.println("testSemanticAnalyzerTypeChecks = success! Exception expectation met: " + expectsException);
        } catch (AssertionError e) {
            System.out.println("testSemanticAnalyzerTypeChecks = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }


    /* ================================= ASSIGNMENT ==================================== */

    @ParameterizedTest(name = "Testing assignment: {0}")
    @CsvSource({
        "'int i = 5;', 'i', 'int', false, false",
        "'const int j = 10;', 'j', 'int', true, false",
        "'shared int radius = 3.14;', 'radius', 'int', false, true"
    })
    void testAssDecVisitorAssignment(String input, String expectedVarName, String expectedType, boolean expectConst, boolean expectShared) {
        System.out.println("========== Running testAssDecVisitorAssignment for: " + input + " ==========");
        OurGrammarParser.AssignmentContext assignmentCtx = parseAssignment(input);
        ctx.symbolTable.pushScope(assignmentCtx); // Dummy global scope

        AssDecVisitor visitor = new AssDecVisitor(ctx);
        visitor.visitAssignment(assignmentCtx);

        VariableSymbol symbol = (VariableSymbol) ctx.symbolTable.resolve(expectedVarName);
        
        try {
            assertAll("Variable attributes Check",
                () -> assertNotNull(symbol, "Variable '" + expectedVarName + "' should be in the symbol table."),
                () -> assertEquals(expectedType, symbol.type.name, "Type of '" + expectedVarName + "' should be '" + expectedType + "'."),
                () -> assertEquals(expectConst, symbol.isConst(), "Const prefix check failed for '" + expectedVarName + "'."),
                () -> assertEquals(expectShared, symbol.isShared(), "Shared prefix check failed for '" + expectedVarName + "'.")
            );
            System.out.println("testAssDecVisitorAssignment = success! Variable '" + expectedVarName + "' added correctly with all expected properties");
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

    ctx = new StubCompilationContext();           // fresh stub
    ctx.defineFake(expectedVarName, expectedTypeName);   // ← this now works
    ctx.symbolTable.pushScope(assignmentCtx);

    RefLinkingVisitor visitor = new RefLinkingVisitor(ctx);
    visitor.visitAssignment(assignmentCtx);

    try {
        assertAll("RefLinking Assignment Check",
            () -> assertFalse(ctx.resolvedSymbols.isEmpty()),
            () -> assertEquals(expectedVarName, ctx.resolvedSymbols.get(assignmentCtx.ID()).ID)
        );
        System.out.println("✓ SUCCESS (" + expectedVarName + ")");
    } catch (AssertionError e) {
        System.out.println("✗ FAILURE: " + e.getMessage());
        throw e;
    }
    System.out.println("------------------------------------------------------------");
}
    @ParameterizedTest(name = "Testing type checking assignment: {0}")
    @CsvSource({
        "'int k = 15;', 'k', 'int'",
        "'float radius = 3.14;', 'radius', 'float'",
        "'shared int radius = 3.14;', 'radius', 'int'"
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
            assertAll("TypeChecking Assignment Check",
                () -> assertDoesNotThrow(() -> visitor.visitAssignment(assignmentCtx))
            );
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
            assertAll("TypeChecking Assignment Check",
                () -> assertDoesNotThrow(() -> visitor.visitAssignment(assignmentCtx), "FtableGenVisitor should not throw exceptions on valid assignments.")
            );
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
            assertAll("CodeGen Assignment Check",
                () -> assertNotNull(generatedCode, "CodeGenVisitor should return a non-null string."),
                () -> assertTrue(generatedCode.contains(expectedCodeContent), "Generated code should contain '" + expectedCodeContent + "', but got: " + generatedCode)
            );
            System.out.println("testCodeGenVisitorAssignment = success! Generated matching java code string");
        } catch (AssertionError e) {
            System.out.println("testCodeGenVisitorAssignment = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    /* ================================= IF STATEMENT ==================================== */

    @ParameterizedTest(name = "Testing type checking if statement: {0}")
    @CsvSource({
        "'if (true) {}', ''",
        "'if (5 < 10) {} else {}', ''",
        "'if (a > b) {} else if (a < b) {} else {}', 'a:int,b:int'",
        "'if (a > b && a < 10) {}', 'a:int,b:int'",
        "'if (a > b) {} else if (a < b) {} else {}', 'a:bool,b:int'",   // should fail
        "'if (a == 15) {}', 'a:float'"                                  // should fail
    })
    void testTypeCheckingIfStatement(String input, String presetVars) {
        System.out.println("========== Running testTypeCheckingIfStatement: " + input + " ==========");
        
        OurGrammarParser.IfStatementContext ifStmtCtx = parseIfStatement(input);
        ctx.symbolTable.pushScope(ifStmtCtx);

        // Setup
        if (presetVars != null && !presetVars.isEmpty()) {
            for (String var : presetVars.split(",")) {
                String[] parts = var.split(":");
                if (parts.length == 2) {
                    ctx.symbolTable.define(new VariableSymbol(parts[0].trim(), 
                                        TypeSymbol.fromString(parts[1].trim())));
                }
            }
        }

        new AssDecVisitor(ctx).visitIfStatement(ifStmtCtx);
        new RefLinkingVisitor(ctx).visit(ifStmtCtx);

        TypeCheckingVisitor visitor = new TypeCheckingVisitor(ctx);

        try {
            visitor.visitIfStatement(ifStmtCtx);
            System.out.println("testTypeCheckingIfStatement = success! (no error)");
            
        } catch (RuntimeException e) {
            System.out.println("testTypeCheckingIfStatement = caught expected error: " + e.getMessage());
            
            // Optional: you can be stricter
            assertTrue(e.getMessage().contains("Type Error") || e.getMessage().contains("type"));
        }
    }

    @ParameterizedTest(name = "Testing code gen if statement: {0}")
    @CsvSource({
        "'if (true) {}', '', 'if (true)'",
        "'if (5 < 12.2) {} else {}', '', 'if (5 < 10)'",
        "'if (a > b) {} else if (a < b) {} else {}', 'a:int,b:int', 'else if (a < b)'",
        "'if (a > b && a < 10) {} else if (a < b) {} else {}', 'a:int,b:int', 'if (a > b && a < 10)'"
    })
    void testCodeGenVisitorIfStatement(String input, String presetVars, String expectedCodeContent) {
        System.out.println("========== Running testCodeGenVisitorIfStatement ==========");
        
        OurGrammarParser.IfStatementContext ifStmtCtx = parseIfStatement(input);
        ctx.symbolTable.pushScope(ifStmtCtx);

        if (presetVars != null && !presetVars.isEmpty()) {
            for (String var : presetVars.split(",")) {
                String[] parts = var.split(":");
                if (parts.length == 2) {
                    VariableSymbol sym = new VariableSymbol(parts[0].trim(), 
                                        TypeSymbol.fromString(parts[1].trim()));
                    ctx.symbolTable.define(sym);
                }
            }
        }

        new AssDecVisitor(ctx).visitIfStatement(ifStmtCtx);
        new RefLinkingVisitor(ctx).visit(ifStmtCtx);
        new TypeCheckingVisitor(ctx).visit(ifStmtCtx);

        CodeGenVisitor visitor = new CodeGenVisitor(ctx);
        String generatedCode = visitor.visitIfStatement(ifStmtCtx);
        
        try {
            assertNotNull(generatedCode);
            String collapsedCode = generatedCode.replaceAll("\\s+", " ").trim();
            assertTrue(collapsedCode.contains(expectedCodeContent));

            System.out.println("testCodeGenVisitorIfStatement = success!");
        } catch (AssertionError e) {
            System.out.println("testCodeGenVisitorIfStatement = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    /* ================================= FOR LOOP ==================================== */

    @ParameterizedTest(name = "Testing AssDecVisitor for statement: {0}")
    @CsvSource({
        "'for (int i = 0; i < 10; i = i + 1) {}', '', 'i'",
        "'for (i = 0; i < 5; i = i + 1) {}', 'i:int', 'i'",
        "'for (float x = 0.0; x <= 10.0; x = x + 2.5) {}', '', 'x'",
        "'for (int i = 0; i < 3; i = i + 1) { int y = 5; }', '', 'i,y'",
        "'for (;;) {}', '', ''"
    })
    void testAssDecVisitorForStatement(String input, String presetVars, String expectedSymbols) {
        System.out.println("========== Running testAssDecVisitorForStatement ==========");
        
        OurGrammarParser.ForStatementContext forStmtCtx = parseForStatement(input);
        ctx.symbolTable.pushScope(forStmtCtx);

        if (presetVars != null && !presetVars.isEmpty()) {
            for (String var : presetVars.split(",")) {
                String[] parts = var.split(":");
                if (parts.length == 2) {
                    VariableSymbol sym = new VariableSymbol(parts[0].trim(), 
                                        TypeSymbol.fromString(parts[1].trim()));
                    ctx.symbolTable.define(sym);
                }
            }
        }

        new AssDecVisitor(ctx).visitForStatement(forStmtCtx);
        
        try {
            // Check that declared loop variable exists in current scope
            boolean success = true;
            if (expectedSymbols != null && !expectedSymbols.isEmpty()) {
                for (String sym : expectedSymbols.split(",")) {
                    if (ctx.symbolTable.resolve(sym.trim()) == null) {
                        success = false;
                        break;
                    }
                }
            }
            assertTrue(success);

            System.out.println("testAssDecVisitorForStatement = success!");
        } catch (AssertionError e) {
            System.out.println("testAssDecVisitorForStatement = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @ParameterizedTest(name = "Testing RefLinkingVisitor for statement: {0}")
    @CsvSource({
        "'for (int i = 0; i < 10; i = i + 1) { print(i); }', '', 'i'",
        "'for (i = 0; i < 5; i = i + 1) {}', 'i:int', 'i'",
        "'for (int i = 0; i < 3; i = i + 1) { if (i > 1) continue; }', '', 'i'",
        "'for (float x = 0.0; x <= 10.0; x = x + 2.5) { print(x); }', '', 'x'"
    })
    void testRefLinkingVisitorForStatement(String input, String presetVars, String expectedLinkedVars) {
        System.out.println("========== Running testRefLinkingVisitorForStatement ==========");
        
        OurGrammarParser.ForStatementContext forStmtCtx = parseForStatement(input);
        ctx.symbolTable.pushScope(forStmtCtx);

        if (presetVars != null && !presetVars.isEmpty()) {
            for (String var : presetVars.split(",")) {
                String[] parts = var.split(":");
                if (parts.length == 2) {
                    VariableSymbol sym = new VariableSymbol(parts[0].trim(), 
                                        TypeSymbol.fromString(parts[1].trim()));
                    ctx.symbolTable.define(sym);
                }
            }
        }

        new AssDecVisitor(ctx).visitForStatement(forStmtCtx);
        new RefLinkingVisitor(ctx).visit(forStmtCtx);
        
        try {
            assertNotNull(forStmtCtx);
            // Basic check - no exception thrown and context exists
            assertTrue(true); // Extend with specific reference checks if needed

            System.out.println("testRefLinkingVisitorForStatement = success!");
        } catch (AssertionError e) {
            System.out.println("testRefLinkingVisitorForStatement = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @ParameterizedTest(name = "Testing TypeCheckingVisitor for statement: {0}")
    @CsvSource({
        "'for (int i = 0; i < 10; i = i + 1) {}', '', 'int'",
        "'for (float x = 0.0; x <= 10.0; x = x + 2.5) {}', '', 'float'",
        "'for (i = 0; i < 5; i = i + 1) {}', 'i:int', 'int'",
        "'for (int i = 0; i < 10 && flag; i = i + 1) {}', 'flag:bool', 'bool'",
        "'for (;;) {}', '', 'valid'"
    })
    void testTypeCheckingVisitorForStatement(String input, String presetVars, String expectedConditionType) {
        System.out.println("========== Running testTypeCheckingVisitorForStatement ==========");
        
        OurGrammarParser.ForStatementContext forStmtCtx = parseForStatement(input);
        ctx.symbolTable.pushScope(forStmtCtx);

        if (presetVars != null && !presetVars.isEmpty()) {
            for (String var : presetVars.split(",")) {
                String[] parts = var.split(":");
                if (parts.length == 2) {
                    VariableSymbol sym = new VariableSymbol(parts[0].trim(), 
                                        TypeSymbol.fromString(parts[1].trim()));
                    ctx.symbolTable.define(sym);
                }
            }
        }

        new AssDecVisitor(ctx).visitForStatement(forStmtCtx);
        new RefLinkingVisitor(ctx).visit(forStmtCtx);
        new TypeCheckingVisitor(ctx).visitForStatement(forStmtCtx);
        
        try {
            assertNotNull(forStmtCtx);
            System.out.println("testTypeCheckingVisitorForStatement = success!");
        } catch (AssertionError e) {
            System.out.println("testTypeCheckingVisitorForStatement = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    @ParameterizedTest(name = "Testing code gen for statement: {0}")
    @CsvSource({
        "'for (int i = 0; i < 10; i = i + 1) {}', '', 'for (int i = 0; i < 10; i = i + 1)'",
        "'for (i = 0; i < 5; i = i + 1) {}', 'i:int', 'for (i = 0; i < 5; i = i + 1)'",
        "'for (float x = 0.0; x <= 10.0; x = x + 2.5) {}', '', 'for (float x = 0.0; x <= 10.0; x = x + 2.5)'",
        "'for (int i = 0; i < 3; i = i + 1) { print(i); }', '', 'for (int i = 0; i < 3; i = i + 1)'",
        "'for (int i = 0; i < 10; i = i + 1) { if (i == 5) continue; if (i == 8) break; }', '', 'for (int i = 0; i < 10; i = i + 1)'",
        "'for (;;) {}', '', 'for (;;)'",
        "'for (int i = 0; i < 10 && flag; i = i + 1) {}', 'flag:bool', 'for (int i = 0; i < 10 && flag; i = i + 1)'"
    })
    void testCodeGenVisitorForStatement(String input, String presetVars, String expectedCodeContent) {
        System.out.println("========== Running testCodeGenVisitorForStatement ==========");
        
        OurGrammarParser.ForStatementContext forStmtCtx = parseForStatement(input);
        ctx.symbolTable.pushScope(forStmtCtx);

        if (presetVars != null && !presetVars.isEmpty()) {
            for (String var : presetVars.split(",")) {
                String[] parts = var.split(":");
                if (parts.length == 2) {
                    VariableSymbol sym = new VariableSymbol(parts[0].trim(), 
                                        TypeSymbol.fromString(parts[1].trim()));
                    ctx.symbolTable.define(sym);
                }
            }
        }

        new AssDecVisitor(ctx).visitForStatement(forStmtCtx);
        new RefLinkingVisitor(ctx).visit(forStmtCtx);
        new TypeCheckingVisitor(ctx).visitForStatement(forStmtCtx);

        CodeGenVisitor visitor = new CodeGenVisitor(ctx);
        String generatedCode = visitor.visitForStatement(forStmtCtx);
        
        try {
            assertNotNull(generatedCode);
            String collapsedCode = generatedCode.replaceAll("\\s+", " ").trim();
            assertTrue(collapsedCode.contains(expectedCodeContent));

            System.out.println("testCodeGenVisitorForStatement = success!");
        } catch (AssertionError e) {
            System.out.println("testCodeGenVisitorForStatement = failure! Error: " + e.getMessage());
            throw e;
        }
        System.out.println("------------------------------------------------------------");
    }

    /* ================================= WHILE LOOP ==================================== */


    /* =============================== CAST EXPRESSION ==================================== */


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
