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
    public Void visitBlock(OurGrammarParser.BlockContext context) {
        this.ctx.symbolTable.pushScope(context);
        Void result = visitChildren(context);
        this.ctx.symbolTable.popScope();
        return result;
    }

    @Override
    public Void visitForStatement(OurGrammarParser.ForStatementContext ctx) {
        this.ctx.symbolTable.pushScope(ctx);
        visitChildren(ctx);
        this.ctx.symbolTable.popScope();
        return null;
    }

    @Override
    public Void visitForVar(OurGrammarParser.ForVarContext context) {
        Set<Symbol.Prefix> prefixes = EnumSet.noneOf(Symbol.Prefix.class);
        if (context.PREFIX() != null) {
            prefixes = parsePrefixes(List.of(context.PREFIX()));
            if (prefixes.contains(Symbol.Prefix.CONST) && prefixes.contains(Symbol.Prefix.SHARED)) {
                throw new RuntimeException("Variable cannot be both 'const' and 'shared'.");
            }
        }

        String id = context.ID().getText();
        String typeStr = context.typeRef().TYPE().getText();

        // Function declaration with parameters
        if (context.assFunc() != null) {
            FunctionSymbol f = new FunctionSymbol(id, TypeSymbol.fromString(typeStr));
            f.prefixes.addAll(prefixes);

            if (context.assFunc().typeRef() != null) {
                var paramTypes = context.assFunc().typeRef();
                var paramNames = context.assFunc().ID();

                for (int i = 0; i < paramNames.size(); i++) {
                    // Skip the function name itself if it's included
                    if (i == 0 && paramNames.get(i).getText().equals(id)) continue;

                    String paramName = paramNames.get(i).getText();
                    String paramTypeStr = paramTypes.get(i).TYPE().getText();

                    VariableSymbol param = new VariableSymbol(paramName, TypeSymbol.fromString(paramTypeStr));
                    if (!this.ctx.symbolTable.define(param)) {
                        throw new RuntimeException("Duplicate parameter name: '" + paramName + "'");
                    }
                }
            }

            if (!this.ctx.symbolTable.define(f)) {
                throw new RuntimeException("Duplicate function declaration: '" + id + "'");
            }

            return visitChildren(context);
        } 
        // Variable declaration with initialization
        else if (context.assVar() != null) { 
            VariableSymbol symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr));
            symbol.prefixes.addAll(prefixes);
            if (!this.ctx.symbolTable.define(symbol)) {
                throw new RuntimeException("Duplicate declaration: '" + id + "'");
            }

            if (symbol.isShared()) this.ctx.sharedVariables.add(id);

            return visitChildren(context);
        }

        return visitChildren(context);
    }

    @Override
    public Void visitAssignment(OurGrammarParser.AssignmentContext context) {
        Set<Symbol.Prefix> prefixes = EnumSet.noneOf(Symbol.Prefix.class);
        if (context.PREFIX() != null) {
            prefixes = parsePrefixes(List.of(context.PREFIX()));
            if (prefixes.contains(Symbol.Prefix.CONST) && prefixes.contains(Symbol.Prefix.SHARED)) {
                throw new RuntimeException("Variable cannot be both 'const' and 'shared'.");
            }
        }

        String id = context.ID().getText();
        String typeStr = context.typeRef().TYPE().getText();

        // Function declaration with parameters
        if (context.assFunc() != null) {
            FunctionSymbol f = new FunctionSymbol(id, TypeSymbol.fromString(typeStr));
            f.prefixes.addAll(prefixes);

            if (context.assFunc().typeRef() != null) {
                var paramTypes = context.assFunc().typeRef();
                var paramNames = context.assFunc().ID();

                for (int i = 0; i < paramNames.size(); i++) {
                    // Skip the function name itself if it's included
                    if (i == 0 && paramNames.get(i).getText().equals(id)) continue;

                    String paramName = paramNames.get(i).getText();
                    String paramTypeStr = paramTypes.get(i).TYPE().getText();

                    VariableSymbol param = new VariableSymbol(paramName, TypeSymbol.fromString(paramTypeStr));
                    if (!this.ctx.symbolTable.define(param)) {
                        throw new RuntimeException("Duplicate parameter name: '" + paramName + "'");
                    }
                }
            }

            if (!this.ctx.symbolTable.define(f)) {
                throw new RuntimeException("Duplicate function declaration: '" + id + "'");
            }

            return visitChildren(context);
        } 
        // Variable declaration with initialization
        else if (context.assVar() != null) { 
            VariableSymbol symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr));
            symbol.prefixes.addAll(prefixes);
            if (!this.ctx.symbolTable.define(symbol)) {
                throw new RuntimeException("Duplicate declaration: '" + id + "'");
            }

            if (symbol.isShared()) this.ctx.sharedVariables.add(id);

            return visitChildren(context);
        }

        return visitChildren(context);
    }
}