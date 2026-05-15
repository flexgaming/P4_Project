package p4project.context;

import java.util.Arrays;

public class ArrayValidator {
    public int[] validate(String input) {
        
        int firstComma = input.indexOf(",");
        int dimensions = (int) input.substring(0, firstComma).chars().filter(ch -> ch == '{').count();

        int[] arrLiteral = new int[dimensions];
        int[] dimensionSize = new int[dimensions];
        Arrays.fill(arrLiteral, -1);
        Arrays.fill(dimensionSize, 1);
        int depth = 0;
        int currentDepth = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '{') {
                depth++; 
                currentDepth++;
            } else if (c == ',') dimensionSize[currentDepth - 1]++;
            else if (c == '}') {
                depth--;
                
                if (arrLiteral[currentDepth - 1] == -1) {
                    arrLiteral[currentDepth - 1] = dimensionSize[currentDepth - 1];
                } else if (arrLiteral[currentDepth - 1] != dimensionSize[currentDepth - 1]) {
                    throw new RuntimeException("Uneven array construction." + input);
                }
                dimensionSize[currentDepth - 1] = 1;
                currentDepth--;
            }
            
        }
        if (depth != 0) {
            throw new RuntimeException("You have an uneven amount of curly brackets in context: " + input);
        }

        return arrLiteral;
    }

    public int[] validate(String input, ArrayTypeSymbol base) {
        int[] arrLiteral = new int[base.dimensions];
        int[] dimensionSize = new int[base.dimensions];
        Arrays.fill(arrLiteral, -1);
        Arrays.fill(dimensionSize, 1);
        int depth = 0;
        int currentDepth = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '{') {
                depth++; 
                currentDepth++;
            } else if (c == ',') dimensionSize[currentDepth - 1]++;
            else if (c == '}') {
                depth--;
                
                if (arrLiteral[currentDepth - 1] == -1) {
                    arrLiteral[currentDepth - 1] = dimensionSize[currentDepth - 1];
                } else if (arrLiteral[currentDepth - 1] != dimensionSize[currentDepth - 1]) {
                    throw new RuntimeException("Uneven array construction." + input);
                }
                if (!(dimensionSize[currentDepth - 1] == base.dimSize[currentDepth - 1])) {
                    throw new RuntimeException("Dimension " + currentDepth + " expects " + base.dimSize[currentDepth - 1] + " elements but got " + dimensionSize[currentDepth - 1]);
                }
                dimensionSize[currentDepth - 1] = 1;
                currentDepth--;
            }
            
        }
        if (depth != 0) {
            throw new RuntimeException("You have an uneven amount of curly brackets in context: " + input);
        }
        if (!Arrays.equals(arrLiteral, base.dimSize)) {
            throw new RuntimeException("Array dimensions size '"+ java.util.Arrays.toString(arrLiteral) + "' does not match the variable'" + java.util.Arrays.toString(base.dimSize) + "'.");
        }


        return arrLiteral;
    }
}
