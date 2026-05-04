package p4project.context;

import org.antlr.v4.runtime.tree.ParseTree;
import java.util.Map;
import java.util.HashMap;

/**
 * A class for handling all declared symbols and their scope.
 * Each symbol is linked to an ID and a scope.
 */
public class SymbolTable {
    //By putting the hashmaps in a deque, stacking multiple scopes becomes possible
    private Scope currentScope = null;
    private final Map<ParseTree, Scope> nodeScopes = new HashMap<>();
    private int depth = -1; // global scope starts at 0, so -1 means no scopes yet

    // Phase 1: Symbol assignments and declarations
    // Create a new scope and link it to the current (Block) node in the parse tree.
    public void pushScope(ParseTree node) { 
        currentScope = new Scope(currentScope);
        nodeScopes.put(node, currentScope);
        depth++;
    }

    // Phases 2-5: Restore the scope associated with the given parse tree node.
    // Restore the scope associated with the given parse tree node.
    public void restoreScope(ParseTree node) {
        Scope stored = nodeScopes.get(node);
        if (stored != null) {
            currentScope = stored;
            depth++;
        }
    }
    
    // Exit the current scope and return to the parent scope.
    public void popScope() { currentScope = currentScope.parent; depth--; }

    // Get the current depth of the scope stack.
    public int getDepth() { return depth; }

    /**
     * Define in the current (innermost) scope.
     * Reports duplicate if already defined at this level.
     */
    public boolean define(Symbol s) {
        return currentScope.define(s);
    }
    
    //Goes through every scope, deepest first, to match the ID to a Symbol.
    public Symbol resolve(String ID) {
        return currentScope.resolve(ID);
    }

    //Look to see if a function exists in the local scope, for duplicate detection
    public Symbol resolveLocal(String name){
        return currentScope.resolveLocal(name);
    }
}