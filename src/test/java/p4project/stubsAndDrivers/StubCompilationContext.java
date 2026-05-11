package p4project.stubsAndDrivers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTree;

import p4project.context.CompilationContext;
import p4project.context.FunctionSymbol;
import p4project.context.Symbol;
import p4project.context.TypeSymbol;

public class StubCompilationContext extends CompilationContext {

    public final StubSymbolTable symbolTable;

    public final Map<ParseTree, Symbol> resolvedSymbols = new IdentityHashMap<>();
    public final Map<ParseTree, TypeSymbol> nodeTypes = new IdentityHashMap<>();
    public final Map<String, FunctionSymbol> ftable = new HashMap<>();
    public final Set<String> sharedVariables = new HashSet<>();
    public FunctionSymbol currentFunction = null;

    public StubCompilationContext() {
        this.symbolTable = new StubSymbolTable();
    }

    public StubCompilationContext(StubSymbolTable table) {
        this.symbolTable = table;
    }

    // === HELPER METHOD - This solves your error ===
    public void defineFake(String name, String typeName) {
        symbolTable.defineFake(name, typeName);
    }
}