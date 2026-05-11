package p4project.stubsAndDrivers;

import java.util.HashMap;
import java.util.Map;

import p4project.context.Symbol;
import p4project.context.SymbolTable;
import p4project.context.TypeSymbol;
import p4project.context.VariableSymbol;

public class StubSymbolTable extends SymbolTable {

    private final Map<String, Symbol> fakeSymbols = new HashMap<>();

    /**
     * Constructor - creates a root scope so currentScope is never null
     */
    public StubSymbolTable() {
        pushScope(null);   // This creates the first root Scope
    }

    public void defineFake(String name, String typeName) {
        VariableSymbol symbol = new VariableSymbol(name, TypeSymbol.fromString(typeName));
        fakeSymbols.put(name, symbol);
        define(symbol);                    // also registers it in the real scope
    }

    @Override
    public boolean define(Symbol s) {
        if (s != null) {
            fakeSymbols.put(s.ID, s);
        }
        return super.define(s);   // let real Scope do its job
    }

    @Override
    public Symbol resolve(String name) {
        Symbol stub = fakeSymbols.get(name);
        return (stub != null) ? stub : super.resolve(name);
    }

    @Override
    public Symbol resolveLocal(String name) {
        Symbol stub = fakeSymbols.get(name);
        return (stub != null) ? stub : super.resolveLocal(name);
    }
}