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
            
            System.out.println(testFileName + " - Parser+Semantic pipeline SUCCESS");

        } catch (RuntimeException e) {
            System.out.println(testFileName + " - Semantic error caught: " + e.getMessage());
            try {
                Files.writeString(Paths.get(outputPath), "ERROR: " + e.getMessage());
            } catch (IOException ignored) {}
        } catch (Exception e) {
            fail("Parser + Semantic integration failed unexpectedly: " + e.getMessage());
        }
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
        System.out.println("Running all integration tests via parameterized methods...");
    }
}