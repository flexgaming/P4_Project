package p4project.context;

import java.util.Arrays;

    /**
     * This function is used to check if the 'input' is a valid array input.
     * @param input Is gone through to check if each dimension size is the same throught the array.
     * @return Is an int[] with the size of the dimensions and contains each dimension's correct size.
     */
public class ArrayValidator {
    public int[] validate(String input) {
        
        // Get the number of dimensions. Example -> {{},{},{}} | Amount of '{' in "{{}," is 2.
        int firstComma = input.indexOf(",");
        int dimensions = (int) input.substring(0, firstComma).chars().filter(ch -> ch == '{').count();

        int[] arrLiteral = new int[dimensions]; // Used to uphold each dimension's rightful size, based on the first input.
        int[] dimensionSize = new int[dimensions]; // Used to store the current dimension's size, and is used to compare with arrLiteral.

        Arrays.fill(arrLiteral, -1); // None of the dimension sizes has been set yet.
        Arrays.fill(dimensionSize, 1); // Setting 1 foreach index, instead of adding it after the commas has been counted.
        int currentDepth = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '{') {
                currentDepth++;
            } else if (c == ',') dimensionSize[currentDepth - 1]++;
            else if (c == '}') {
                
                if (arrLiteral[currentDepth - 1] == -1) { // Has the dimension size been set? If not set the size.
                    arrLiteral[currentDepth - 1] = dimensionSize[currentDepth - 1];
                } else if (arrLiteral[currentDepth - 1] != dimensionSize[currentDepth - 1]) { // Throw error if current dimension size is not the same as arrLiteral.
                    throw new RuntimeException("Uneven array construction." + input);
                }
                dimensionSize[currentDepth - 1] = 1; // Reset the index for next time the same dimension has to be counted.
                currentDepth--;
            }
            
        }
        if (currentDepth != 0) {
            throw new RuntimeException("You have an uneven amount of curly brackets in context: " + input);
        }

        return arrLiteral;
    }

    /**
     * This function is used to check if the 'input' is the same as what is expected from the limits in 'base'.
     * @param input Is gone through to check if each dimension size is the same throught the array.
     * @param base Is used to check the correct size of each dimension.
     * @return Is an int[] with the size of the dimensions and contains each dimension's correct size.
     */
    public int[] validate(String input, ArrayTypeSymbol base) {
        int[] arrLiteral = new int[base.dimensions]; // Used to uphold each dimension's rightful size, based on the first input.
        int[] dimensionSize = new int[base.dimensions]; // Used to store the current dimension's size, and is used to compare with arrLiteral

        Arrays.fill(arrLiteral, -1); // None of the dimension sizes has been set yet.
        Arrays.fill(dimensionSize, 1); // Setting 1 foreach index, instead of adding it after the commas has been counted.
        int currentDepth = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '{') {
                currentDepth++;
            } else if (c == ',') dimensionSize[currentDepth - 1]++;
            else if (c == '}') {
                
                String expectedStr = base.dimSize[currentDepth - 1]; // Expected size of the dimension 'currentDepth - 1'.
                if (expectedStr != null && expectedStr.matches("\\d+")) { // Check if string only consist of digits 0-9.
                    int expected = Integer.parseInt(expectedStr);
                    if (dimensionSize[currentDepth - 1] != expected) { // Throw error if current dimension size is not the same as expected.
                        throw new RuntimeException("Dimension " + currentDepth + " expects " + expected + " elements but got " + dimensionSize[currentDepth - 1]);
                    }
                } else if (!expectedStr.matches("\\d+")) { // Throw error if the excepted input is anything other than digits.
                    throw new RuntimeException("Integer input '" + expectedStr + "' in '" + java.util.Arrays.toString(base.dimSize) + "' is wrong.");
                }
                
                if (arrLiteral[currentDepth - 1] == -1) { // Has the dimension size been set? If not set the size.
                    arrLiteral[currentDepth - 1] = dimensionSize[currentDepth - 1];
                }
                dimensionSize[currentDepth - 1] = 1; // Reset the index for next time the same dimension has to be counted.
                currentDepth--;
            }
        }
        if (currentDepth != 0) {
            throw new RuntimeException("You have an uneven amount of curly brackets in context: " + input);
        }

        return arrLiteral;
    }
}
