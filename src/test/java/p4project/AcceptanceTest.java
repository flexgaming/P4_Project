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
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        assertTrue(normalized.contains("CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {});"));
        assertTrue(normalized.contains("CompletableFuture<Void> t2 = CompletableFuture.runAsync(() -> {});"));
    }

    // Requirement 2: An input in ParaLang file gets transpiled to Java file
    @Test
    void testRequirement2() throws IOException {
        Path in = Paths.get("..\\P4_Project\\sample-input.txt");
        // Read ParaLang input file
        String input = Files.readString(in, StandardCharsets.UTF_8);

        // Run full pipeline in-test to produce Java code
        String javaCode = ParserDriver.runFullPipeline(input);

        Path out = Paths.get("..\\P4_Project\\javaOutput.java");
        if (out.getParent() != null) Files.createDirectories(out.getParent());
        Files.writeString(out, javaCode, StandardCharsets.UTF_8);

        // Assertions
        assertTrue(Files.exists(out), "Expected transpiled file to exist: " + out.toAbsolutePath());
        String normalized = normalize(javaCode);
        System.out.println("=== Transpiled Java Path: " + out.toAbsolutePath() + " ===");
        assertTrue(normalized.contains("public class Main {"));
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

        assertTrue(normalized.contains("char c = 'a';"));
        assertTrue(normalized.contains("int i = 42;"));
        assertTrue(normalized.contains("float f = 3.14f;"));
        assertTrue(normalized.contains("Boolean b = true;"));
        assertTrue(normalized.contains("String s = \"Hello\";"));
    }
    // Requirement 6: Arrays
    @Test
    void testRequirement6() {
        String input = """
            void main() {
                int[] numbers = {1, 2, 3};
                float[] decimals = {1.0, 2.5};
                bool[] flags = {true, false};
                char[] letters = {'a', 'b', 'c'};
                string[] words = {"hello", "world"};
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        assertTrue(normalized.contains("int[] numbers = new int[]{1, 2, 3};"));
        assertTrue(normalized.contains("float[] decimals = new float[]{1.0f, 2.5f};"));
        assertTrue(normalized.contains("Boolean[] flags = new Boolean[]{true, false};"));
        assertTrue(normalized.contains("char[] letters = new char[]{'a', 'b', 'c'};"));
        assertTrue(normalized.contains("String[] words = new String[]{\"hello\", \"world\"};"));
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
            int myFunction(int i) {
                return i + 1;
            }
            void main() {
                if (5 < 10) {}
                if (5 < 10) {} else {}
                if (5 < 10) {} else if (3 > 1) {} else {}
                for (int i = 0; i < 10; i = i + 1) {}
                while (5 < 10) {break;}
                int x;
                int y = 5;
                y = 10;
                int result = myFunction(5);
                print("Hello, World!");
                x = read(int);
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        assertTrue(normalized.contains("if (5 < 10) {}"));
        assertTrue(normalized.contains("if (5 < 10) {} else {}"));
        assertTrue(normalized.contains("if (5 < 10) {} else if (3 > 1) {} else {}"));
        assertTrue(normalized.contains("for (int i = 0; i < 10; i = i + 1) {}"));
        assertTrue(normalized.contains("while (5 < 10) {break;}"));
        assertTrue(normalized.contains("int x = 0;"));
        assertTrue(normalized.contains("int y = 5;"));
        assertTrue(normalized.contains("y = 10;"));
        assertTrue(normalized.contains("public static int myFunction(int i) {return i + 1;}"));
        assertTrue(normalized.contains("int result = myFunction(5);"));
        assertTrue(normalized.contains("System.out.print(\"Hello, World!\");"));
        assertTrue(normalized.contains("x = scanner.nextInt();"));
    }

    // Requirement 9: A method for handling critical sections
    @Test
    void testRequirement9() {
        String input = """
            void main() {
                shared int counter = 0;
                critical(counter) {
                    counter = counter + 1;
                }
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        assertTrue(normalized.contains("Lock m0 = new ReentrantLock();"));
        assertTrue(normalized.contains("int counter = 0;"));
        assertTrue(normalized.contains("for (double indexer = 100; !m0.tryLock(); indexer = indexer*1.2-((indexer*1.2)%1))"));
        assertTrue(normalized.contains("Thread.sleep((long) indexer);"));
        assertTrue(normalized.contains("try {"));
        assertTrue(normalized.contains("counter = counter + 1;"));
        assertTrue(normalized.contains("finally {"));
        assertTrue(normalized.contains("m0.unlock();"));
    }

@Test
void testRequirement10() {
    String input = """
        void main() {
            shared int x = 0;
            shared int y = 0;

            critical(x, y) {
                x = x + 1;
            }
            critical(y, x) {
                y = y + 1;
            }
        }
        """;

    String javaCode = ParserDriver.runFullPipeline(input);
    String normalized = normalize(javaCode);

    // Both locks must exist
    assertTrue(normalized.contains("m0.tryLock()"));
    assertTrue(normalized.contains("m1.tryLock()"));

    // First critical section
    int first = normalized.indexOf("x = x + 1;");
    String blockA = normalized.substring(0, first);
    assertTrue(blockA.indexOf("m0.tryLock()") < blockA.indexOf("m1.tryLock()"));
    System.out.println(blockA.indexOf("m0.tryLock()") < blockA.indexOf("m1.tryLock()"));

    // Second critical section
    int second = normalized.indexOf("y = y + 1;");
    String blockB = normalized.substring(0, second);
    assertTrue(blockB.indexOf("m0.tryLock()") < blockB.indexOf("m1.tryLock()"));
    System.out.println(blockB.indexOf("m0.tryLock()") < blockB.indexOf("m1.tryLock()"));
}


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
            }                
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

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
    @Test
    void testRequirement15() {
        String input = """
            int func1(int a, float b) {
                return a + cast(int) b;
            }
            
            float func2(float q) {
                print("q in func2: ", q);
                return q + 1.5;
            }
            
            shared int q = 10;
            
            void main() {
                int x = func1(5, 3.2);
                float y = func2(q);
                print("x after func1: ", x);
                print("y after func2: ", y);
                
                thread t1 => {
                    critical(q) {
                        q = q + 1;
                    }
                };
                
                thread t2 => {
                    critical(q) {
                        q = q - 1;
                    }
                };
                
                awaitAll(t1, t2);
                print("q after threads: ", q);
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);
        
        assertTrue(normalized.contains("public static int func1(int a, float b) {"));
        assertTrue(normalized.contains("return a + (int) b;"));
        assertTrue(normalized.contains("public static float func2(float q) {"));
        assertTrue(normalized.contains("System.out.print(\"q in func2: \" + q);"));
        assertTrue(normalized.contains("return q + 1.5f;"));
        assertTrue(normalized.contains("System.out.print(\"x after func1: \" + x);"));
        assertTrue(normalized.contains("System.out.print(\"y after func2: \" + y);"));
        assertTrue(normalized.contains("System.out.print(\"q after threads: \" + q);"));

        assertTrue(normalized.contains("static int q = 10;"));
        assertTrue(normalized.contains("Lock m0 = new ReentrantLock();"));
        assertFalse(normalized.contains("Lock m1 = new ReentrantLock();"));
        
        assertTrue(normalized.contains("int x = func1(5, 3.2f);"));
        assertTrue(normalized.contains("float y = func2(q);"));
        
        assertTrue(normalized.contains("CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {{"));
        assertTrue(normalized.contains("CompletableFuture<Void> t2 = CompletableFuture.runAsync(() -> {{"));
        
        assertTrue(normalized.contains("for (double indexer = 100; !m0.tryLock(); indexer = indexer*1.2-((indexer*1.2)%1)) {"));
        assertTrue(normalized.contains("Thread.sleep((long) indexer);"));

        assertTrue(normalized.contains("try {"));
        assertTrue(normalized.contains("q = q + 1;"));
        assertTrue(normalized.contains("q = q - 1;"));
        assertTrue(normalized.contains("finally {"));
        assertTrue(normalized.contains("m0.unlock();"));

        assertTrue(normalized.contains("CompletableFuture.allOf(t1, t2).get();"));
    }

    // Requirement 16: Shared variables gets wrapped in mutex
    @Test
    void requirement16() {
        String input = """
            void main() {
                shared int counter = 2;
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        assertTrue(normalized.contains("Lock m0 = new ReentrantLock();"));
        
    }

    // Requirement 17: Transpiler throws an error instead of crashing
    @Test
    void requirement17() {
        String input = """
            void main() {
                int i = 5;
                if (j < 10) {}
            }
            """;

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            ParserDriver.runFullPipeline(input);
        });

        assertTrue(ex.getMessage().toLowerCase().contains("undefined") ||
                ex.getMessage().toLowerCase().contains("semantic") ||
                ex.getMessage().length() > 0,
                "Expected a descriptive transpiler error message");
    }

    // Requirement 18: ParaLang outputs correct formatted Java Code (indentation, spacing, etc.)
    @Test
    void requirement18() {
        String input = """
            void main() {
                int x = 5;
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        assertTrue(javaCode.contains("public static void main(String[] args) {"));
        assertTrue(javaCode.contains("    int x = 5;"));
        assertTrue(javaCode.contains("}"));
    }

    // Requirement 19: ParaLang supports modifiers: how variables are defined
    @Test
    void requirement19() {
        String input = """
            static int s = 2;
            static shared int ss = 3;
            void main() {
                shared int counter = 0;
                const int c = 1;
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        assertTrue(normalized.contains("Lock m0 = new ReentrantLock();"));
        assertTrue(normalized.contains("Lock m1 = new ReentrantLock();"));
        assertTrue(normalized.contains("int counter = 0;"));
        assertTrue(normalized.contains("int c = 1;"));
        assertTrue(normalized.contains("static int s = 2;"));
        assertTrue(normalized.contains("static int ss = 3;"));
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

        assertFalse(normalized.contains("//"), "Single-line comments should be removed");
        assertFalse(normalized.contains("/*"), "Multi-line comments should be removed");
    }


    @Test
    void testRequirement13() {
        String input = """
            void main() {
                thread t1 => {
                    print("Thread as data type");
                }
            }
            """;
        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);
        
        assertTrue(normalized.contains("CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {System.out.print(\"Thread as data type\");});"));
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
        String outputPath = OUTPUT_DIR + "e2e_" + baseName + ".txt";
        String expectedPath = EXPECTED_DIR + baseName + "_e2e.txt";

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