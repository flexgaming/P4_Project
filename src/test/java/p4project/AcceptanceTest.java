package p4project;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AcceptanceTest {
    private ParserDriver driver;

    private static final String TEST_DIR = "src/test/resources/";
    private static final String INPUT_DIR = TEST_DIR + "test-inputs/";
    private static final String EXPECTED_DIR = TEST_DIR + "expected/";
    private static final String OUTPUT_DIR = TEST_DIR + "test-outputs/";

    @BeforeAll
    static void initAll() throws IOException {
        Files.createDirectories(Paths.get(OUTPUT_DIR));
        System.out.println("Acceptance test setup complete. Output directory ready.");
    }

    @BeforeEach
    void setUp() {
        driver = new ParserDriver();   // adjust if constructor differs
        System.out.println("=== Acceptance Test Setup Complete ===");
    }

    private String normalize(String code) {
        return code
        .replaceAll("//.*", "")
        .replaceAll("\\{\\s+\\}", "{}")
        .replaceAll("\\s+", " ")
        .replaceAll("\\{\\s+", "{")
        .replaceAll("\\s+\\}", "}")
        .trim();
    }

    // Requirement 1: Create multiple threads
    @Test
    void testRequirement1() {
        String input = """
            void main() {
                thread t1 => {};
                thread t2 => {};
            }

            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {});"));
        assertTrue(normalized.contains("CompletableFuture<Void> t2 = CompletableFuture.runAsync(() -> {});"));
    }

    // Requirement 2: An input in ParaLang file gets transpiled to Java file
    @Test
    void testTranspileFile() throws IOException {
        Path in = Path.of("src", "test", "resources", "test-inputs", "transpile_input.txt");
        // Read ParaLang input file
        String input = Files.readString(in, StandardCharsets.UTF_8);

        // Run full pipeline in-test to produce Java code
        String javaCode = ParserDriver.runFullPipeline(input);

        // Write output into test-outputs/<basename>.java
        String base = in.getFileName().toString();
        int idx = base.lastIndexOf('.');
        if (idx > 0) base = base.substring(0, idx);
        Path out = Path.of("test-outputs", base + ".java");
        if (out.getParent() != null) Files.createDirectories(out.getParent());
        Files.writeString(out, javaCode, StandardCharsets.UTF_8);

        // Assertions
        assertTrue(Files.exists(out), "Expected transpiled file to exist: " + out.toAbsolutePath());
        String normalized = normalize(javaCode);
        System.out.println("=== Transpiled Java Path: " + out.toAbsolutePath() + " ===");
        System.out.println(javaCode);
        assertTrue(normalized.contains("System.out.print(\"Hello, World!\");"));
    }

    // Requirement 5: Data Types: char, int, float, bool, string
    @Test
    void testRequirement5() {
        String input = """
            void main() {
                char c = 'a';
                int i = 42;
                float f = 3.14;
                bool b = true;
                string s = "Hello";
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("char c = 'a';"));
        assertTrue(normalized.contains("int i = 42;"));
        assertTrue(normalized.contains("float f = 3.14f;"));
        assertTrue(normalized.contains("Boolean b = true;"));
        assertTrue(normalized.contains("String s = \"Hello\";"));
    }
    // Requirement 6: Arrays
    @Test
    void testRequirement6() {
        System.out.println("=== Running Acceptance Test: requirement 6 ===");
    }

    // Requirement 7: Operators: +, -, *, /, %, ^, ==, !=, ||, <, >, &&, >=, <=
    @Test
    void requirement7() {
        String input = """
            void main() {
                int add = 5 + 3;
                int minus = 5 - 3;
                int mult = 10 * 2;
                int div = 10 / 2;
                int mod = 10 % 3;
                float pow = (3.5 ^ 2.5);
                if (8 == 8) {}
                if (7 != 8) {}
                if (true || false) {}
                if (5 < 10) {}
                if (5 > 10) {}
                if (true && false) {}
                if (5 >= 4) {}  
                if (5 <= 4) {}       
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        assertTrue(normalized.contains("int add = 5 + 3;"));
        assertTrue(normalized.contains("int minus = 5 - 3;"));
        assertTrue(normalized.contains("int mult = 10 * 2;"));
        assertTrue(normalized.contains("int div = 10 / 2;"));
        assertTrue(normalized.contains("int mod = 10 % 3;"));
        assertTrue(normalized.contains("float pow = ((float)Math.pow(3.5f, 2.5f));"));
        assertTrue(normalized.contains("if (8 == 8) {}"));
        assertTrue(normalized.contains("if (7 != 8) {}"));
        assertTrue(normalized.contains("if (true || false) {}"));
        assertTrue(normalized.contains("if (5 < 10) {}"));
        assertTrue(normalized.contains("if (5 > 10) {}"));
        assertTrue(normalized.contains("if (true && false) {}"));
        assertTrue(normalized.contains("if (5 >= 4) {}"));
        assertTrue(normalized.contains("if (5 <= 4) {}"));
    }

    // Requirement 8: Control flow: if-else, for loops, while loops, variable declarations, assignments, and reassignments, print statements, read statements
    @Test
    void requirement8() {
        String input = """
            void main() {
                if (5 < 10) {}
                if (5 < 10) {} else {}
                if (5 < 10) {} else if (3 > 1) {} else {}
                for (int i = 0; i < 10; i = i + 1) {}
                while (5 < 10) {break;}
                int x;
                int y = 5;
                y = 10;
                print("Hello, World!");
                x = read(int);
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("if (5 < 10) {}"));
        assertTrue(normalized.contains("if (5 < 10) {} else {}"));
        assertTrue(normalized.contains("if (5 < 10) {} else if (3 > 1) {} else {}"));
        assertTrue(normalized.contains("for (int i = 0; i < 10; i = i + 1) {}"));
        assertTrue(normalized.contains("while (5 < 10) {break;}"));
        assertTrue(normalized.contains("int x = 0;"));
        assertTrue(normalized.contains("int y = 5;"));
        assertTrue(normalized.contains("y = 10;"));
        assertTrue(normalized.contains("System.out.print(\"Hello, World!\");"));
        assertTrue(normalized.contains("x = scanner.nextInt();"));
    }

    // Requirement 9: A method for handling critical sections

    // Requirement 10: Functionality to prevent deadlocks

    // Requirement 11: input and output via console
    @Test
    void requirement11() {
        String input = """
            void main() {
                print("Enter your name:");
                string name = read(string);
                print("Hello", name + "!");

                print("Age?: ");
                int age = read(int);
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("System.out.print(\"Enter your name:\");"));
        assertTrue(normalized.contains("String name = scanner.nextLine();"));
        assertTrue(normalized.contains("System.out.print(\"Hello\" + name + \"!\");")); 
    }

    // Requirement 12: Wait function
    @Test
    void requirement12a() {
        String input = """
                void main() {

                thread t1 => { print("in t1")}
                thread t2 => { print("in t2")}
                awaitAll(t1, t2);
            }
            """;
        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {System.out.print(\"in t1\");});"));
        assertTrue(normalized.contains("CompletableFuture<Void> t2 = CompletableFuture.runAsync(() -> {System.out.print(\"in t2\");});"));
        assertTrue(normalized.contains("CompletableFuture.allOf(t1, t2).get();"));
    }

    @Test
    void requirement12b() {
        String input = """
                void main() {
                thread t1 => { print("in t1")}
                thread t2 => { print("in t2")}
                awaitAny(t1, t2);
            }
            """;
        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {System.out.print(\"in t1\");});"));
        assertTrue(normalized.contains("CompletableFuture<Void> t2 = CompletableFuture.runAsync(() -> {System.out.print(\"in t2\");});"));
        assertTrue(normalized.contains("CompletableFuture.anyOf(t1, t2).get();"));
    }
    
    // Requirement 13: Data type: Thread

    // Requirement 14: Explicit casting between data types
    @Test
    void requirement14() {
        String input = """
            void main() {
                int srcInt = 32;
                float srcFloat = 1.0;
                bool srcBool = true;
                char srcChar = 'a';

                // Int to other types
                float intToFloat = cast(float) srcInt;
                bool intToBool = cast(bool) srcInt;
                string intToString = cast(string) srcInt;

                // Float to other types
                int floatToInt = cast(int) srcFloat;
                bool floatToBool = cast(bool) srcFloat;
                string floatToString = cast(string) srcFloat;

                // Bool to other types
                int boolToInt = cast(int) srcBool;
                float boolToFloat = cast(float) srcBool;
                string boolToString = cast(string) srcBool;

                // Char to string
                string charToString = cast(string) srcChar;

                bool funcToBool = cast(bool) myFunc();

                int myFunc() {
                    return 0;
                }
            }                
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("float intToFloat = (float) srcInt;"));
        assertTrue(normalized.contains("Boolean intToBool = (srcInt == 0) ? false : true;"));
        assertTrue(normalized.contains("String intToString = String.valueOf(srcInt);"));
        assertTrue(normalized.contains("int floatToInt = (int) srcFloat;"));
        assertTrue(normalized.contains("Boolean floatToBool = (srcFloat == 0.0f) ? false : true;"));
        assertTrue(normalized.contains("String floatToString = String.valueOf(srcFloat);"));
        assertTrue(normalized.contains("int boolToInt = srcBool ? 1 : 0;"));
        assertTrue(normalized.contains("float boolToFloat = srcBool ? 1.0f : 0.0f;"));
        assertTrue(normalized.contains("String boolToString = String.valueOf(srcBool);"));
        assertTrue(normalized.contains("String charToString = String.valueOf(srcChar);"));
    }

    // Requirement 15: Parallel Implementation avoiding deadlocks and race conditions

    // Requirement 18: Shared variables gets wrapped in mutex
    @Test
    void requirement18() {
        String input = """
            void main() {
                shared int counter = 2;
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("Lock m0 = new ReentrantLock();"));
        
    }

    // Requirement 20: Single and multiline comments
    @Test
    void requirement20() {
        String input = """
            void main() {
                // This is a single-line comment
                /* This 
                is 
                a 
                multi-line 
                comment */
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertFalse(normalized.contains("//"), "Single-line comments should be removed");
        assertFalse(normalized.contains("/*"), "Multi-line comments should be removed");
    }

    // Req 24 We have it, but test idk

    // Req 25 maybe not

    // Requirement 26: input and output via console
    @Test
    void requirement26() {
        String input = """
            void main() {
                print("Enter your name:");
                string name = read(string);
                print("Hello", name + "!");

                print("Age?: ");
                int age = read(int);
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);
        System.out.println(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("System.out.print(\"Enter your name:\");"));
        assertTrue(normalized.contains("String name = scanner.nextLine();"));
        assertTrue(normalized.contains("System.out.print(\"Hello\" + name + \"!\");")); 
    }

    /**
     * Full pipeline integration test (big bang): Lexer + Parser + Semantic Analysis + Code Generation.
     */
    @ParameterizedTest(name = "Full Pipeline Integration: {0}")
    @MethodSource("provideTestInputs")
    void testFullPipelineIntegration(String testFileName) throws IOException {
        String inputPath = INPUT_DIR + testFileName;
        String input = Files.readString(Paths.get(inputPath));

        String baseName = testFileName.replace(".txt", "");
        String outputPath = OUTPUT_DIR + baseName + "_actual.txt";
        String expectedPath = EXPECTED_DIR + baseName + "_expected.txt";

        System.out.println("=== Running Full Pipeline for: " + testFileName + " ===");

        try {
            String actualOutput = ParserDriver.runFullPipeline(input);

            Files.writeString(Paths.get(outputPath), actualOutput);

            String expected = Files.readString(Paths.get(expectedPath))
                    .trim().replace("\r\n", "\n");
            actualOutput = actualOutput.trim().replace("\r\n", "\n");

            if (actualOutput.equals(expected)) {
                System.out.println(testFileName + " - Full pipeline SUCCESS");
            } else {
                System.out.println(testFileName + " - Mismatch. Check " + outputPath);
                System.out.println("Expected length: " + expected.length() + ", Actual: " + actualOutput.length());
                assertEquals(expected, actualOutput,
                        testFileName + " full pipeline output did not match expected");
            }

        } catch (RuntimeException e) {
            System.out.println("Runtime error in full pipeline: " + e.getMessage());
            Files.writeString(Paths.get(outputPath), "ERROR: " + e.toString());
        } catch (Exception e) {
            fail("Unexpected exception in full pipeline for " + testFileName + ": " + e.getMessage());
        }
    }

    private static Stream<Arguments> provideTestInputs() throws IOException {
        List<Arguments> args = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(INPUT_DIR))) {
            paths.filter(Files::isRegularFile)
                 .filter(p -> p.toString().endsWith(".txt"))
                 .sorted()
                 .forEach(p -> args.add(Arguments.of(p.getFileName().toString())));
        }
        return args.stream();
    }
}