package p4project.context;

import java.util.IdentityHashMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTree;

public class CompilationContext {
    // Phase 1: the symbol table, which supports scopes (stack of hashmaps)
    public final SymbolTable symbolTable = new SymbolTable();

    // Phase 2: every identifier-use node → the Symbol it refers to
    public final Map<ParseTree, Symbol> resolvedSymbols = new IdentityHashMap<>();

    // Phase 3: every expr/factor node → its resolved TypeSymbol
    public final Map<ParseTree, TypeSymbol> nodeTypes = new IdentityHashMap<>();

    // Phase 4: function name → FunctionSymbol (the ftable)
    public final Map<String, FunctionSymbol> ftable = new HashMap<>();

    // Concurrency: track which variables are declared 'shared' — Phase 3 uses this
    // to enforce that shared vars are only mutated inside critical sections
    public final Set<String> sharedVariables = new HashSet<>();

    // The function we're currently inside — Phase 3 needs this for return type checking
    public FunctionSymbol currentFunction = null;


}
