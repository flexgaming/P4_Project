package p4project.stubsAndDrivers;

import p4project.context.Scope;
import p4project.context.Symbol;
import p4project.context.SymbolTable;
import p4project.context.TypeSymbol;
import p4project.context.VariableSymbol;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.HashMap;
import java.util.Map;

public class StubSymbolTable extends SymbolTable {

    private final Map<String, Symbol> fakeSymbols = new HashMap<>();

    public StubSymbolTable() {
        // We use pushScope to add a scope, since we cannot access the private field `currentScope`
        pushScope(null);
    }

    public void defineFake(String name, String typeName) {
        VariableSymbol symbol = new VariableSymbol(name, TypeSymbol.fromString(typeName));
        fakeSymbols.put(name, symbol);
        define(symbol);
    }

    @Override
    public boolean define(Symbol s) {
        if (s != null) {
            fakeSymbols.put(s.ID, s);
        }
        return super.define(s);
    }

    @Override
    public Symbol resolve(String name) {
        Symbol stub = fakeSymbols.get(name);
        if (stub != null) return stub;
        return super.resolve(name);
    }

    @Override
    public Symbol resolveLocal(String name) {
        Symbol stub = fakeSymbols.get(name);
        if (stub != null) return stub;
        return super.resolveLocal(name);
    }
}