package p4project.stubsAndDrivers;

import p4project.context.CompilationContext;

public class StubCompilationContext extends CompilationContext {

    public StubCompilationContext() {
        // Inject the isolated StubSymbolTable up to the base class
        super(new StubSymbolTable());
    }
    
    public void defineFake(String name, String typeName) {
        ((StubSymbolTable)this.symbolTable).defineFake(name, typeName);
    }
}