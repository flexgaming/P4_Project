package p4project.context;

public class VariableSymbol extends Symbol {
    public boolean isThread;

    public VariableSymbol(String name, TypeSymbol type) {
        super(name, type);
    }
    public VariableSymbol(String name, TypeSymbol type, String[] dimensions) {
        super(name, type, dimensions);
        this.isThread = false;
    }
}
