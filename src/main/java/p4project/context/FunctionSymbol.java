package p4project.context;

import java.util.ArrayList;
import java.util.List;

import p4project.OurGrammarParser;

public class FunctionSymbol extends Symbol {
    public final List<VariableSymbol> parameters = new ArrayList<>();
    public int declaredAtDepth;

    public boolean containsCriticalSection = false;
    public OurGrammarParser.AssignmentContext context;

    public FunctionSymbol(String name, TypeSymbol type) {
        super(name, type);
    }
}
