package p4project.context;

/**
 * An internal class to store the type of a symbol.
 * Each type is final and are able to be read out as a string.
 */
public class TypeSymbol {
    //All variable types defined by our language
    public enum Type { INT, FLOAT, BOOL, CHAR, STRING, VOID, THREAD }

    //This instance of TypeSymbols' type
    public final Type type;

    // Name of the type
    public final String name;

    //Creating the final singular instance of every TypeSymbol
    public static final TypeSymbol INT    = new TypeSymbol(Type.INT, "int");
    public static final TypeSymbol FLOAT  = new TypeSymbol(Type.FLOAT, "float");
    public static final TypeSymbol BOOL   = new TypeSymbol(Type.BOOL, "bool");
    public static final TypeSymbol CHAR   = new TypeSymbol(Type.CHAR, "char");
    public static final TypeSymbol STRING = new TypeSymbol(Type.STRING, "string");
    public static final TypeSymbol VOID   = new TypeSymbol(Type.VOID, "void");
    public static final TypeSymbol THREAD = new TypeSymbol(Type.THREAD, "thread");
    public static final TypeSymbol UNKNOWN = new TypeSymbol(null, "unknown"); // error recovery

    //Constructor, only meant to be used by all subclasses.
    protected TypeSymbol(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    //Passes input in format of our Context Free Grammar "TYPE".
    public static TypeSymbol fromString(String s) {
        return switch (s) {
            case "int"    -> INT;
            case "float"  -> FLOAT;
            case "bool"   -> BOOL;
            case "char"   -> CHAR;
            case "string" -> STRING;
            case "void"   -> VOID;
            case "thread" -> THREAD;
            default       -> UNKNOWN;
        };
    }

    public String toString() {
        return this.type != null ? this.type.name().toLowerCase() : "unknown";
    }
}

