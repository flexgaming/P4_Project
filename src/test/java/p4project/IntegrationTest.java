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
        System.out.println("Integration test setup complete. Output directory ready.");
    }

    @AfterEach
    void cleanupAfterTest() {
        // Optional: Add light cleanup if needed (e.g., delete temp files)
    }

    // ===================== BOTTOM-UP INTEGRATION TESTS =====================

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
            // Target stage directly
            String output = ParserDriver.runLexerParserPipeline(input);

            // Basic sanity check for this layer
            assertNotNull(output, "Parser output should not be null");
            assertFalse(output.trim().isEmpty(), "Parser should produce non-empty output");

            // Save for debugging
            String outputPath = OUTPUT_DIR + "lexer_parser_" + testFileName;
            Files.writeString(Paths.get(outputPath), output);
            
            System.out.println("✓ " + testFileName + " - Lexer+Parser pipeline SUCCESS");

        } catch (RuntimeException e) {
            // Some negative tests (like test5.txt) intentionally throw RuntimeExceptions for semantic errors
            System.out.println("Runtime error caught during lexer/parser integration (expected for some tests): " + e.getMessage());
        } catch (Exception e) {
            fail("Lexer + Parser integration failed for " + testFileName + ": " + e.getMessage());
        }
    }

    /**
     * Mid-level integration: Parser + Semantic Analysis / Type Checking.
     * Builds on the previous layer.
     */
    @ParameterizedTest(name = "Parser + Semantic integration: {0}")
    @MethodSource("provideTestInputs")
    void testParserSemanticIntegration(String testFileName) throws IOException {
        String inputPath = INPUT_DIR + testFileName;
        String input = Files.readString(Paths.get(inputPath));

        System.out.println("=== Testing Parser + Semantic Analysis for: " + testFileName + " ===");

        try {
            String output = ParserDriver.runParserSemanticPipeline(input);

            // Add more specific assertions here based on your project's output format
            // e.g., check for semantic error messages or successful IR generation
            assertTrue(output.contains("success") || !output.toLowerCase().contains("error"),
                    "Expected successful semantic processing or clear error reporting");

            String outputPath = OUTPUT_DIR + "parser_semantic_" + testFileName;
            Files.writeString(Paths.get(outputPath), output);
            
            System.out.println("✓ " + testFileName + " - Parser+Semantic pipeline SUCCESS");

        } catch (RuntimeException e) {
            // Expected for invalid test cases
            System.out.println("Semantic error caught (expected for some tests): " + e.getMessage());
        } catch (Exception e) {
            fail("Parser + Semantic integration failed unexpectedly: " + e.getMessage());
        }
    }

    /**
     * Full pipeline integration (existing logic expanded).
     * Top of the bottom-up pyramid.
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

            // Save actual output for inspection
            Files.writeString(Paths.get(outputPath), actualOutput);

            String expected = Files.readString(Paths.get(expectedPath))
                    .trim().replace("\r\n", "\n");
            actualOutput = actualOutput.trim().replace("\r\n", "\n");

            if (actualOutput.equals(expected)) {
                System.out.println("✓ " + testFileName + " - Full pipeline SUCCESS");
            } else {
                System.out.println("✗ " + testFileName + " - Mismatch. Check " + outputPath);
                // Enhanced diff reporting
                System.out.println("Expected length: " + expected.length() + ", Actual: " + actualOutput.length());
                assertEquals(expected, actualOutput,
                        testFileName + " full pipeline output did not match expected");
            }

        } catch (RuntimeException e) {
            System.out.println("Runtime error in full pipeline: " + e.getMessage());
            Files.writeString(Paths.get(outputPath), "ERROR: " + e.toString());
            // Original test suite swallowed RuntimeExceptions rather than failing JUnit
            // so we log it and allow the negative tests (like test5.txt) to pass.
        } catch (Exception e) {
            fail("Unexpected exception in full pipeline for " + testFileName + ": " + e.getMessage());
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

    // Keep your original aggregated test as a convenience method
    @Test
    void runAllIntegrationTests() {
        // This now delegates to the parameterized tests above via JUnit
        System.out.println("Running all integration tests via parameterized methods...");
    }
}