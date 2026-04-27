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

    //Creating the final singular instance of every TypeSymbol
    public static final TypeSymbol INT    = new TypeSymbol(Type.INT);
    public static final TypeSymbol FLOAT  = new TypeSymbol(Type.FLOAT);
    public static final TypeSymbol BOOL   = new TypeSymbol(Type.BOOL);
    public static final TypeSymbol CHAR   = new TypeSymbol(Type.CHAR);
    public static final TypeSymbol STRING = new TypeSymbol(Type.STRING);
    public static final TypeSymbol VOID   = new TypeSymbol(Type.VOID);
    public static final TypeSymbol THREAD = new TypeSymbol(Type.THREAD);
    public static final TypeSymbol UNKNOWN = new TypeSymbol(null); // error recovery

    //Constructor, only meant to be used by all subclasses.
    protected TypeSymbol(Type type) {
        this.type = type;
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

