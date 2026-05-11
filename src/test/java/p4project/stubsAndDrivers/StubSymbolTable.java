package p4project.stubsAndDrivers;

import java.util.HashMap;
import java.util.Map;

import p4project.context.Symbol;
import p4project.context.SymbolTable;
import p4project.context.TypeSymbol;
import p4project.context.VariableSymbol;

public class StubSymbolTable extends SymbolTable {

    private final Map<String, Symbol> fakeSymbols = new HashMap<>();

    public void defineFake(String name, String typeName) {
        VariableSymbol symbol = new VariableSymbol(name, TypeSymbol.fromString(typeName));
        fakeSymbols.put(name, symbol);
    }

    @Override
    public boolean define(Symbol s) {
        if (s != null) {
            fakeSymbols.put(s.ID, s);
        }
        return true;
    }

    @Override
    public Symbol resolve(String name) {
        return fakeSymbols.get(name);
    }

    @Override
    public Symbol resolveLocal(String name) {
        return fakeSymbols.get(name);
    }
}