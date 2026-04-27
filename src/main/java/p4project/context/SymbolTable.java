package p4project.context;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;

public class SymbolTable {
    private final Deque<LinkedHashMap<String, Symbol>> scopes = new ArrayDeque<>();
    private int depth = 0;

    public void pushScope() { scopes.push(new LinkedHashMap<>()); depth++; }
    public void popScope()  { scopes.pop(); depth--; }
    public int  depth()     { return depth; }

    
}
