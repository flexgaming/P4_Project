package p4project.visitors;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.tree.TerminalNode;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;
import p4project.context.CompilationContext;
import p4project.context.Symbol;
import p4project.context.VariableSymbol;
import p4project.context.FunctionSymbol;
import p4project.context.TypeSymbol;

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

    @Override
    public Void visitDeclaration(OurGrammarParser.DeclarationContext context) {
        Set<Symbol.Prefix> prefixes = EnumSet.noneOf(Symbol.Prefix.class);
        if (context.PREFIX() != null) {
            prefixes = parsePrefixes(List.of(context.PREFIX()));
            if (prefixes.contains(Symbol.Prefix.CONST) && prefixes.contains(Symbol.Prefix.SHARED)) {
                throw new RuntimeException("Variable cannot be both 'const' and 'shared'.");
            }
        }

        String id = context.ID().getText();
        String typeStr = context.typeRef().TYPE().getText();

        VariableSymbol symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr));
        symbol.prefixes.addAll(prefixes);
        if (!this.ctx.symbolTable.define(symbol)) {
            throw new RuntimeException("Duplicate declaration: '" + id + "'");
        }

        if (symbol.isShared()) this.ctx.sharedVariables.add(id);

        return visitChildren(context);
    }

    @Override
    public Void visitAssignment(OurGrammarParser.AssignmentContext context) {
        // This rule covers declarations with initializers and function definitions.
        Set<Symbol.Prefix> prefixes = EnumSet.noneOf(Symbol.Prefix.class);
        if (context.PREFIX() != null) {
            prefixes = parsePrefixes(List.of(context.PREFIX()));
            if (prefixes.contains(Symbol.Prefix.CONST) && prefixes.contains(Symbol.Prefix.SHARED)) {
                throw new RuntimeException("Variable cannot be both 'const' and 'shared'.");
            }
        }

        String id = context.ID().getText();
        String typeStr = context.typeRef().TYPE().getText();

        // Function declaration (assFunc present)
        if (context.assFunc() != null) {
            FunctionSymbol f = new FunctionSymbol(id, TypeSymbol.fromString(typeStr));
            f.prefixes.addAll(prefixes);
            if (!this.ctx.symbolTable.define(f)) {
                throw new RuntimeException("Duplicate function declaration: '" + id + "'");
            }
            return visitChildren(context);
        }

        // Variable declaration with initializer
        String typeRefText = context.typeRef().getText();
        int bracketCount = 0;
        for (int i = 0; i < typeRefText.length(); i++) if (typeRefText.charAt(i) == '[') bracketCount++;

        if (bracketCount == 0) {
            VariableSymbol v = new VariableSymbol(id, TypeSymbol.fromString(typeStr));
            v.prefixes.addAll(prefixes);
            if (!this.ctx.symbolTable.define(v)) {
                throw new RuntimeException("Duplicate declaration: '" + id + "'");
            }
            if (v.isShared()) this.ctx.sharedVariables.add(id);
        } else {
            // parse dimensions (numbers or leave -1 for unspecified)
            Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(typeRefText);
            java.util.List<Integer> dims = new java.util.ArrayList<>();
            while (m.find()) {
                String inside = m.group(1);
                if (inside == null || inside.isEmpty()) dims.add(-1);
                else {
                    try { dims.add(Integer.parseInt(inside)); }
                    catch (NumberFormatException ex) { dims.add(-1); }
                }
            }
            int[] dimArr = dims.stream().mapToInt(Integer::intValue).toArray();
            Symbol arr = new Symbol(id, TypeSymbol.fromString(typeStr), dimArr);
            arr.prefixes.addAll(prefixes);
            if (!this.ctx.symbolTable.define(arr)) {
                throw new RuntimeException("Duplicate declaration: '" + id + "'");
            }
            if (arr.isShared()) this.ctx.sharedVariables.add(id);
        }

        return visitChildren(context);
    }
}