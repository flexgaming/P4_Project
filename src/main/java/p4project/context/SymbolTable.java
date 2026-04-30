package p4project.context;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * A class for handling all declared symbols and their scope.
 * Each symbol is linked to an ID and a scope.
 */
public class SymbolTable {
    //By putting the hashmaps in a deque, stacking multiple scopes becomes possible
    private final Deque<HashMap<String, Symbol>> scopes = new ArrayDeque<>();
    private int depth = 0;

    public void pushScope() { scopes.push(new HashMap<>()); depth++; }
    public void popScope()  { scopes.pop(); depth--; }
    public int  depth()     { return depth; }

    /**
     * Define in the current (innermost) scope.
     * Reports duplicate if already defined at this level.
     */
    public boolean define(Symbol s) {
        var current = scopes.peek();
        if (current.containsKey(s.ID)) return false;
        current.put(s.ID, s);
        return true;
    }

    //Goes through every scope, deepest first, to match the ID to a Symbol.
    public Symbol resolve(String ID) {
        for (var scope : scopes) {
            if (scope.containsKey(ID)) return scope.get(ID);
        }
        return null;
    }

    //Look to see if a function exists in the local scope, for duplicate detection
    public Symbol resolveLocal(String name){
        return scopes.peek().get(name);
    }

    // For debugging purposes, to print the symbol table after phase 1
    public Void printSymbolTable() {
        for (var scope : scopes) {
            System.out.println(scope);
        }
        return null;
    }

    // For debugging purposes, to print the symbol table after phase 1
    public int Length() {
        return scopes.size();
    }


}
