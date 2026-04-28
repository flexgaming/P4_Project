package p4project.visitors;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.tree.TerminalNode;

import p4project.OurGrammarBaseVisitor;
import p4project.context.CompilationContext;
import p4project.context.Symbol;

/*
    -> Phase 1: Symbol assignments and declerations
    Phase 2: Reference linking
    Phase 3: Type checking
    Phase 4: vtable and ftable generation
    Phase 5: Java Code Gen
*/

public class AssDecVisitor extends OurGrammarBaseVisitor<Void> {
    private final CompilationContext ctx;

    public AssDecVisitor(CompilationContext ctx) {
        this.ctx = ctx;
        ctx.symbolTable.pushScope(); // global scope
    }

    private Set<Symbol.Prefix> parsePrefixes(List<TerminalNode> prefixTokens) {
        Set<Symbol.Prefix> result = EnumSet.noneOf(Symbol.Prefix.class);
        for (var t : prefixTokens) {
            String text = t.getText();
            if (text.contains("shared")) result.add(Symbol.Prefix.SHARED);
            if (text.contains("const"))  result.add(Symbol.Prefix.CONST);
            if (text.contains("static")) result.add(Symbol.Prefix.STATIC);
        }
        return result;
    }


}
