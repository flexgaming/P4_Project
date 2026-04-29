package p4project.context;

import java.util.EnumSet;
import java.util.Set;

public class Symbol {
    public enum Prefix { SHARED, CONST, STATIC }

    public final String ID;
    public final Set<Prefix> prefixes = EnumSet.noneOf(Prefix.class);
    public TypeSymbol type;
    public ArrayTypeSymbol arrType;

    public boolean isShared() { return prefixes.contains(Prefix.SHARED); }
    public boolean isConst()  { return prefixes.contains(Prefix.CONST); }
    public boolean isStatic() { return prefixes.contains(Prefix.STATIC); }

    public Symbol(String name, TypeSymbol type) {
        this.ID = name;
        this.type = new TypeSymbol(type.type, type.name);
    }

    public Symbol(String name, TypeSymbol type, int[] dimensions) {
        this.ID = name;
        this.arrType = new ArrayTypeSymbol(type, dimensions);
        this.type = this.arrType;
    }
    
}
