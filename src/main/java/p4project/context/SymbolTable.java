package p4project.context;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.Token;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class for handling all declared symbols and their scope.
 * Each symbol is linked to an ID and a scope.
 */
public class SymbolTable {
    //By putting the hashmaps in a deque, stacking multiple scopes becomes possible
    private Scope currentScope = null;
    private final Map<ParseTree, Scope> nodeScopes = new HashMap<>();
    private int depth = -1; // global scope starts at 0, so -1 means no scopes yet

    // Get the current scope's associated parse tree node.
    public ParseTree getNodeScope() {
        // get parsetree node key in the hashmap corresponding to current scope
        for (Map.Entry<ParseTree, Scope> entry : nodeScopes.entrySet()) {
            if (entry.getValue() == currentScope) {
                return entry.getKey();
            }
        }
        return null; // should never happen if scopes are managed correctly
    }

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

    // For testing: Get a string representation of the symbol table.
    public String getText() {
        StringBuilder sb = new StringBuilder();
        List<ParseTree> nodes = new ArrayList<>(nodeScopes.keySet());
        // Sort deterministically by source position when available, fallback to text hash.
        nodes.sort(Comparator.comparingInt(node -> {
            if (node instanceof ParserRuleContext) {
                Token t = ((ParserRuleContext) node).getStart();
                return (t != null) ? t.getStartIndex() : Integer.MAX_VALUE;
            } else if (node instanceof TerminalNode) {
                Token t = ((TerminalNode) node).getSymbol();
                return (t != null) ? t.getStartIndex() : Integer.MAX_VALUE;
            } else {
                return node.getText().hashCode();
            }
        }));

        for (ParseTree node : nodes) {
            sb.append("Node: ").append(node.getText()).append("\n");
        }
        return sb.toString();
    }
}