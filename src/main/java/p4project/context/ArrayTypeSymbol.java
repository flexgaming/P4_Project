package p4project.context;

/**
 * An extention to the TypeSymbol class that allows for
 * multi-dimensional arrays to be declared.
 */
public class ArrayTypeSymbol extends TypeSymbol {
    public final TypeSymbol elementType;
    public int dimensions = 0; // int[][] → 2
    public String[] dimSize; //int[10][20] → dimSize[10,20]

    //Constructor taking a TypeSymbol as input and the dimensions.
    public ArrayTypeSymbol(TypeSymbol elementType, String[] dimensions) {
        super(elementType.type, elementType.name + "[]");
        this.elementType = elementType;
        this.dimSize = dimensions;
        this.dimensions = dimensions.length;
    }

    //Outputs in the format "int[dimSize[0]]...[dimSize[n]]" for n dimensions.
    @Override public String toString() {
        String returnString = elementType.toString();
        for(int i = 0; i < dimensions; i++) {
            returnString += "[" + dimSize[i] + "]";
        }
        return returnString;
    }
}