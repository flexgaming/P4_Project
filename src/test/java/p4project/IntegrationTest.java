package p4project;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    private static final String TEST_DIR = "src/test/resources/";
    private static final String EXPECTED_DIR = TEST_DIR + "expected/";
    private static final String OUTPUT_DIR = TEST_DIR + "test-outputs/";

    @BeforeAll
    static void setup() throws IOException {
        Files.createDirectories(Paths.get(OUTPUT_DIR));
    }

    @Test
    public void runAllIntegrationTests() {
        List<String> testFiles = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(TEST_DIR + "test-inputs/"))) {
            testFiles = paths
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".txt"))
                .map(p -> p.getFileName().toString())
                .toList();
        } catch (IOException e) {
            fail("Failed to read test files: " + e.getMessage());
        }

        for (int i = 0; i < testFiles.size(); i++) {
            String testName = "test" + (i + 1);
            String inputFile = TEST_DIR + "test-inputs/" + testFiles.get(i);
            String outputFile = OUTPUT_DIR + "testrun" + (i + 1) + "output.txt";  // e.g. testrun1output.txt
            String expectedFile = EXPECTED_DIR + testFiles.get(i).replace(".txt", "_expected.txt");

            runSingleTest(testName, inputFile, outputFile, expectedFile);
        }
    }

    private void runSingleTest(String testName, String inputPath, String outputPath, String expectedPath) {
        System.out.println("=== Running " + testName + " ===");

        try {
            String input = Files.readString(Paths.get(inputPath));

            String actualOutput = ParserDriver.runFullPipeline(input);

            // Save output
            Files.writeString(Paths.get(outputPath), actualOutput);

            // Compare
            String expected = Files.readString(Paths.get(expectedPath)).trim().replace("\r\n", "\n");
            actualOutput = actualOutput.trim().replace("\r\n", "\n");
            
            if (actualOutput.equals(expected)) {
                System.out.println(testName + " = success! Matches expected");
            } else {
                System.out.println(testName + " = Failure! didn't match expected result. See " + outputPath);
                assertEquals(expected, actualOutput, testName + " output did not match expected");
            }

        } catch (RuntimeException e) {
            System.out.println(testName + " = failure! Error: (" + e.getMessage() + ")");
            try {
                Files.writeString(Paths.get(outputPath), "RuntimeException caught:\n" + e.getMessage() + "\n" + e.toString());
            } catch (IOException ignored) {}
        } catch (Exception e) {
            System.out.println(testName + " = failure! Unexpected error: " + e.getMessage());
        }
    }
}