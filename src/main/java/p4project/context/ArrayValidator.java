package p4project.context;

import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;

public class ArrayValidator {

    public ArrayValidator(CompilationContext ctx) {
    }

    // -------------------------------------------------------
    // Phase 2 — infer dimensions from a literal and update
    // the symbol's type accordingly
    // -------------------------------------------------------

    public void inferDimensions(String literal, Symbol sym) {
        if (sym.arrType == null) {
            throw new RuntimeException("Cannot infer dimensions for variable '" + sym.ID + "' because it is not declared as an array.");
        }
        ArrayTypeSymbol base = sym.arrType;

        int[] dims = inferDimensionsRecursive(literal.trim());
        if (dims != null) { 
            base.dimSize = dims;
            base.dimensions = dims.length;
        }
    }

    // Recursive helper to infer dimensions from a nested array literal
    private int[] inferDimensionsRecursive(String input) {
        if (!input.startsWith("{") || !input.endsWith("}")) {
            throw new RuntimeException("Expected '{...}' but got: " + input);
        }
        String inner = input.substring(1, input.length() - 1).trim();
        List<String> elements = splitTopLevel(inner);
        int size = elements.size();

        // Check if the elements are themselves nested arrays
        boolean nested = elements.stream().anyMatch(e -> e.trim().startsWith("{"));
        if (nested) {
            // Recurse into the first element to get inner dimensions
            int[] innerDims = inferDimensionsRecursive(elements.get(0).trim());
            if (innerDims == null) return null;

            // Validate all elements have the same inner dimensions
            for (int i = 1; i < elements.size(); i++) {
                int[] otherDims = inferDimensionsRecursive(elements.get(i).trim());
                if (otherDims == null) return null;
                if (!Arrays.equals(innerDims, otherDims))
                    throw new RuntimeException("Inconsistent dimensions in array literal at element " + i);
            }
            // Prepend this dimension to the inner ones: [size, innerDims...]
            int[] result = new int[1 + innerDims.length];
            result[0] = size;
            System.arraycopy(innerDims, 0, result, 1, innerDims.length);
            return result;
        }

        // Base case — flat list of values, single dimension
        return new int[]{ size };
    }

    // -------------------------------------------------------
    // Phase 3 — validate a literal matches an already-typed
    // array symbol in both dimensions and element types
    // -------------------------------------------------------

    public void validateLiteral(String literal, ArrayTypeSymbol type) {
        validateDimension(literal.trim(), type, 0);
    }

    private void validateDimension(String input, ArrayTypeSymbol type, int depth) {
        int expectedSize = type.dimSize[depth];

        if (!input.startsWith("{") || !input.endsWith("}")) {
            throw new RuntimeException("Expected '{...}' at dimension " + depth + " but got: " + input);
        }
        String inner = input.substring(1, input.length() - 1).trim();
        List<String> elements = splitTopLevel(inner);

        if (elements.size() != expectedSize) {
            throw new RuntimeException("Dimension " + depth + " expects " + expectedSize + " elements but got " + elements.size());
        }

        if (depth < type.dimSize.length - 1) {
            for (String element : elements) validateDimension(element.trim(), type, depth + 1);
        } else {
            //for (String element : elements) validateBaseType(element.trim(), type.elementType);
        }
    }

    // -------------------------------------------------------
    // Shared utility
    // -------------------------------------------------------

    private List<String> splitTopLevel(String input) {
        List<String> result = new ArrayList<>();
        int depth = 0;
        int start = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if      (c == '{') depth++;
            else if (c == '}') depth--;
            else if (c == ',' && depth == 0) {
                result.add(input.substring(start, i).trim());
                start = i + 1;
            }
        }
        if (!input.isBlank()) result.add(input.substring(start).trim());
        return result;
    }
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
