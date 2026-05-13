package p4project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AcceptanceTest {
    private ParserDriver driver;

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
        .trim();
    }

    // Requirement 1: Create, maintain, and terminate threads
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

    // Requirement 2: 

    // Requirement 5: Data Types: char, int, float, bool
    @Test
    void testRequirement5() {
        String input = """
            void main() {
                char c = 'a';
                int i = 42;
                float f = 3.14;
                bool b = true;
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("char c = 'a';"));
        assertTrue(normalized.contains("int i = 42;"));
        assertTrue(normalized.contains("float f = 3.14f;"));
        assertTrue(normalized.contains("bool b = true;"));
    }
    // Arrays
    @Test
    void testRequirement6() {
        System.out.println("=== Running Acceptance Test: requirement 6 ===");
    }

    // Operators: +, -, *, /, %, ^, ==, !=, ||, <, >, &&, >=, <=
    @Test
    void requirement7() {
        String input = """
            void main() {
                int add = 5 + 3;
                int minus = 5 - 3;
                int mult = 10 * 2;
                int div = 10 / 2;
                int mod = 10 % 3;
                float pow = 3.5 ^ 2.5;
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

        // 1. Full pipeline (your existing helper)
        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        assertTrue(normalized.contains("int add = 5 + 3;"));
        assertTrue(normalized.contains("int minus = 5 - 3;"));
        assertTrue(normalized.contains("int mult = 10 * 2;"));
        assertTrue(normalized.contains("int div = 10 / 2;"));
        assertTrue(normalized.contains("int mod = 10 % 3;"));
        assertTrue(normalized.contains("float pow = 3.5 ^ 2.5;"));
        assertTrue(normalized.contains("if (8 == 8) {}"));
        assertTrue(normalized.contains("if (7 != 8) {}"));
        assertTrue(normalized.contains("if (true || false) {}"));
        assertTrue(normalized.contains("if (5 < 10) {}"));
        assertTrue(normalized.contains("if (5 > 10) {}"));
        assertTrue(normalized.contains("if (true && false) {}"));
        assertTrue(normalized.contains("if (5 >= 4) {}"));
        assertTrue(normalized.contains("if (5 <= 4) {}"));
    }

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
                print("Hello, World!");
                x = read(int);
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
        assertTrue(normalized.contains("int x;"));
        assertTrue(normalized.contains("int y = 5;"));
        assertTrue(normalized.contains("System.out.print(\"Hello, World!\");"));
        assertTrue(normalized.contains("x = scanner.nextInt();"));
    }


    // Requirement 10: Functionality to prevent deadlocks

    // Requirement 11: Reassignments
    @Test
    void requirement11() {
        String input = """
            void main() {
                int x = 5;
                x = 10;
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("int x = 5;"));
        assertTrue(normalized.contains("x = 10;"));
    }

    // Requirement 12: Wait function

    // Requirement 13: Data type: Thread

    // Requirement 14: Parallel Implementation avoiding deadlocks and race conditions

    // Requirement 15: Strings
    @Test
    void requirement15() {
        String input = """
            void main() {
                string status = "good";
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("string status = \"good\";"));
    }

    // Requirement 18: Shared variables gets wrapped in mutex
    @Test
    void requirement18() {
        String input = """
            void main() {
                shared int counter = 0;
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("Lock m0 = new ReentrantLock();"));
        
    }


    // Maybe idk



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
            }
            """;

        String javaCode = ParserDriver.runFullPipeline(input);
        String normalized = normalize(javaCode);

        System.out.println(javaCode);
        System.out.println(normalized);
        assertTrue(normalized.contains("System.out.print(\"Enter your name:\");"));
        assertTrue(normalized.contains("string name = scanner.nextLine();"));
        assertTrue(normalized.contains("System.out.print(\"Hello\" + name + \"!\");")); 
    }
}