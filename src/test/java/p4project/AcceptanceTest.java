package p4project;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class AcceptanceTest {
    private ParserDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ParserDriver();   // adjust if constructor differs
        System.out.println("=== Acceptance Test Setup Complete ===");
    }

    private OurGrammarParser.ProgramContext parseProgram(String input) {
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);
        return parser.program();
    }

    private OurGrammarParser.ExprContext parseExpression(String input) {
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);
        return parser.expr();
    }

    @Test
    void testRequirement1() {
        System.out.println("=== Running Acceptance Test: requirement 1 ===");
    }

    @Test
    void testRequirement2() {
        System.out.println("=== Running Acceptance Test: requirement 2 ===");
    }

    @Test
    void testRequirement4() {
        System.out.println("=== Running Acceptance Test: requirement 4 ===");
    }

    @Test
    void testRequirement5() {
        System.out.println("=== Running Acceptance Test: requirement 5 ===");
    }

    @Test
    void testRequirement6() {
        System.out.println("=== Running Acceptance Test: requirement 6 ===");
    }

    @ParameterizedTest(name = "Req 7 - {0} => {1}")
    @CsvSource({
        // Arithmetic
        "5 + 3, 8",
        "5 - 3, 2",
        "5 * 3, 15",
        "5 % 3, 2",
        // Java integer division for int literals
        "5 / 3, 1",
        // Comparison
        "5 == 3, false",
        "5 != 3, true",
        "5 < 3, false",
        "5 > 3, true",
        "5 >= 3, true",
        "5 <= 3, false",
        // Logical
        "true || false, true",
        "true && false, false",

        // Mixed precedence
        "5 + 3 * 2, 11",
        "true || false && false, true",
    })
    void testRequirement7WithOutput(String expression, String expected) throws Exception {
        String program = "void main() { print(" + expression + "); }";

        String javaCode = ParserDriver.runFullPipeline(program);
        String actualOutput = compileAndRunGeneratedMain(javaCode);

        assertEquals(expected, actualOutput,
                "Unexpected runtime output for expression: " + expression);
    }

    
    // Reusable acceptance template: generate Java, compile it, run Main.main, assert output.
    private static String compileAndRunGeneratedMain(String javaCode) throws Exception {
        Path tempDir = Files.createTempDirectory("acceptance-main-");
        Path mainFile = tempDir.resolve("Main.java");
        Files.writeString(mainFile, javaCode, StandardCharsets.UTF_8);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        assertNotNull(compiler,
                "No Java compiler available. Run tests with a JDK, not a JRE.");

        ByteArrayOutputStream compileErrors = new ByteArrayOutputStream();
        int exitCode = compiler.run(null, null, new PrintStream(compileErrors), mainFile.toString());
        assertEquals(0, exitCode,
                "Generated Java failed to compile:\n" + compileErrors.toString(StandardCharsets.UTF_8));

        PrintStream originalOut = System.out;
        ByteArrayOutputStream runtimeOutput = new ByteArrayOutputStream();

        try (URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{tempDir.toUri().toURL()})) {
            System.setOut(new PrintStream(runtimeOutput));

            Class<?> mainClass = Class.forName("Main", true, classLoader);
            Method mainMethod = mainClass.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[]{});
        } finally {
            System.setOut(originalOut);
            deleteDirectoryRecursively(tempDir);
        }

        return runtimeOutput.toString(StandardCharsets.UTF_8)
                .replace("\r\n", "\n")
                .trim();
    }

    private static void deleteDirectoryRecursively(Path root) throws IOException {
        if (!Files.exists(root)) {
            return;
        }
        try (var paths = Files.walk(root)) {
            paths.sorted((a, b) -> b.compareTo(a))
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException ignored) {
                            // Best-effort cleanup for temp acceptance artifacts.
                        }
                    });
        }
    }
}
