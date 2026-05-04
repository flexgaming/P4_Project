package p4project.context;

import java.util.HashMap;
import java.util.Map;

/**
 * A class representing a scope in the symbol table.
 * Each scope has a reference to its parent scope and a map of symbols defined in that scope.
 * The structure resembles a tree, where each scope can have multiple child scopes -
 * (e.g., nested blocks), but only one parent scope.
 */
public class Scope {
    public final Scope parent;
    // A map of symbol names to their corresponding Symbol objects defined in this scope.
    public final Map<String, Symbol> symbols = new HashMap<>();

    public Scope(Scope parent) {
        this.parent = parent;
    }

    // Define a symbol in the current (innermost) scope.
    // Returns false if a symbol with the same name already exists in this scope, true otherwise
    public boolean define(Symbol s) {
        if (symbols.containsKey(s.ID)) return false;
        symbols.put(s.ID, s);
        return true;
    }

    /**
     * The resolve method checks for a symbol in the current scope and, if not found, recursively
     * checks in the parent scopes until it either finds the symbol or reaches the global scope.
     */
    public Symbol resolve(String ID) {
        if (symbols.containsKey(ID)) {
            return symbols.get(ID);
        } else if (parent != null) {
            return parent.resolve(ID);
        } else {
            return null;
        }
    }

    // Resolve a symbol only in the current scope, without checking parent scopes.
    public Symbol resolveLocal(String ID) {
        return symbols.get(ID);
    }
}