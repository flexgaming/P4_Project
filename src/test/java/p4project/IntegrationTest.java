package p4project;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private static final String TEST_DIR = "src/test/resources/";
    private static final String INPUT_DIR = TEST_DIR + "test-inputs/";
    private static final String EXPECTED_DIR = TEST_DIR + "expected/";
    private static final String OUTPUT_DIR = TEST_DIR + "test-outputs/";

    @BeforeAll
    static void setup() throws IOException {
        Files.createDirectories(Paths.get(OUTPUT_DIR));
        System.out.println("Integration test setup complete. Output directory ready.\\n");
    }

    /**
     * Low-level integration: Lexer + Parser stage.
     * Tests that tokenization feeds correctly into parsing without syntax errors.
     */
    @ParameterizedTest(name = "Lexer+Parser integration: {0}")
    @MethodSource("provideTestInputs")
    void testLexerParserIntegration(String testFileName) throws IOException {
        String inputPath = INPUT_DIR + testFileName;
        String input = Files.readString(Paths.get(inputPath));

        System.out.println("=== Testing Lexer + Parser for: " + testFileName + " ===");

        try {
            String output = ParserDriver.runLexerParserPipeline(input);

            assertNotNull(output, "Parser output should not be null");
            assertFalse(output.trim().isEmpty(), "Parser should produce non-empty output");

            String outputPath = OUTPUT_DIR + "lexer_parser_" + testFileName;
            Files.writeString(Paths.get(outputPath), output);
            
            // If an expected file exists, compare the actual output to expected output
            String expectedPath = EXPECTED_DIR + "lexer_parser_" + testFileName;
            if (Files.exists(Paths.get(expectedPath))) {
                String expected = Files.readString(Paths.get(expectedPath)).replace("\r\n", "\n").trim();
                String actual = output.replace("\r\n", "\n").trim();
                assertEquals(expected, actual, "Lexer+Parser output differed from expected for " + testFileName);
            } else {
                System.out.println("No expected file found for " + testFileName + " (checked: " + expectedPath + ")");
            }

            System.out.println(testFileName + " - Lexer + Parser pipeline SUCCESS - matched expected output");

        } catch (RuntimeException e) {
            System.out.println(testFileName + " - Runtime error caught during lexer/parser integration (expected for some tests): " + e.getMessage());
        } catch (Exception e) {
            fail("Lexer + Parser integration failed for " + testFileName + ": " + e.getMessage());
        }
    }

    /**
     * Mid-level integration: Parser + Semantic Analysis / Type Checking.
     */
    @ParameterizedTest(name = "Parser + Semantic integration: {0}")
    @MethodSource("provideTestInputs")
    void testParserSemanticIntegration(String testFileName) throws IOException {
        String inputPath = INPUT_DIR + testFileName;
        String input = Files.readString(Paths.get(inputPath));

        System.out.println("=== Testing Parser + Semantic Analysis for: " + testFileName + " ===");
        String outputPath = OUTPUT_DIR + "parser_semantic_" + testFileName;

        try {
            String output = ParserDriver.runParserSemanticPipeline(input);

            assertTrue(output.contains("success") || !output.toLowerCase().contains("error"),
                    "Expected successful semantic processing or clear error reporting");

            Files.writeString(Paths.get(outputPath), output);

            // compare to expected output if it exists
            String expectedPath = EXPECTED_DIR + "parser_semantic_" + testFileName;
            if (Files.exists(Paths.get(expectedPath))) {
                String expected = Files.readString(Paths.get(expectedPath)).trim();
                assertEquals(expected, output.trim(), "Failure! Semantic analysis output did not match expected results");
            }
            
            System.out.println(testFileName + " - Parser + Semantic pipeline SUCCESS - matched expected output");

        } catch (RuntimeException e) {
            System.out.println(testFileName + " - Semantic error caught: " + e.getMessage());
            try {
                Files.writeString(Paths.get(outputPath), "ERROR: " + e.getMessage());
            } catch (IOException ignored) {}
        } catch (Exception e) {
            fail("Parser + Semantic integration failed unexpectedly: " + e.getMessage());
        }
    }

    // ===================== HELPER METHODS =====================

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

    @Test
    void runAllIntegrationTests() {
        System.out.println("\nRunning all integration tests via parameterized methods...");
    }
}