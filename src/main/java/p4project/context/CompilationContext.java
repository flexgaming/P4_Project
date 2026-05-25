package p4project.context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

public class CompilationContext {
    // Phase 1: the symbol table, which supports scopes (stack of hashmaps)
    public final SymbolTable symbolTable = new SymbolTable();

    // Phase 2: every identifier-use node → the Symbol it refers to
    // Use LinkedHashMap to preserve insertion order (deterministic across runs)
    public final Map<ParseTree, Symbol> resolvedSymbols = new LinkedHashMap<>();

    // Phase 3: every expr/factor node → its resolved TypeSymbol
    public final Map<ParseTree, TypeSymbol> nodeTypes = new LinkedHashMap<>();

    // Phase 4: function name → FunctionSymbol (the ftable)
    // Use LinkedHashMap to keep a stable order when iterating over functions.
    public final Map<String, FunctionSymbol> ftable = new LinkedHashMap<>();

    // Concurrency: track which variables are declared 'shared' — Phase 3 uses this
    // to enforce that shared vars are only mutated inside critical sections
    public final List<String> sharedVariables = new ArrayList<>();
}
