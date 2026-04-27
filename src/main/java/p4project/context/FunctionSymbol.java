package p4project.context;

import java.util.ArrayList;
import java.util.List;

public class FunctionSymbol extends Symbol {
    public final List<VariableSymbol> parameters = new ArrayList<>();
    public int declaredAtDepth;

    public FunctionSymbol(String name, TypeSymbol type) {
        super(name, type);
    }
}
