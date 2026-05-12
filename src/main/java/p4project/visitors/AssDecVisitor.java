package p4project.visitors;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
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
    Phase 5: Mutex and deep nested critical section checking
    Phase 6: Java Code Gen
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
        String contexStr = context.getText();
    
        int dims = (int) contexStr.chars().filter(ch -> ch == '[').count(); // Get the number of dimensions.
        VariableSymbol symbol;
        
        if (dims > 0) {
            int[] dimensions = new int[dims];
            Arrays.fill(dimensions, -1);
            for (int i = 0; i < dims; i++) {
                if (contexStr.contains("[")) { // Get the size of the dimension from the brackets after the variable name if it is defined.
                    if (contexStr.matches(".*[1-9].*")) {
                        int sqBracket1 = contexStr.indexOf("[");
                        int sqBracket2 = contexStr.indexOf("]");
                        dimensions[i] = Integer.parseInt(contexStr.substring(sqBracket1 + 1, sqBracket2));
                        contexStr = contexStr.substring(contexStr.indexOf("]") + 1); // Move to the next dimension by removing the processed part.
                    }
                } 
            }
            symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr), dimensions);
        } else {
            symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr));
        }
        
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
    public Void visitForStatement(OurGrammarParser.ForStatementContext context) {
        this.ctx.symbolTable.pushScope(context);
        visitChildren(context);
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
        String contexStr = context.getText();

        int eqIndex = contexStr.indexOf("=");
        String beforeEquals = contexStr.substring(0, eqIndex);
        String afterEquals = contexStr.substring(eqIndex + 1);
        
        int dims = (int) afterEquals.chars().filter(ch -> ch == '{').count(); // Get the number of dimensions.
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

            VariableSymbol symbol;

            if (dims > 0) {
                int[] dimensions = new int[dims];
                Arrays.fill(dimensions, -1);
                for (int i = 0; i < dims; i++) {
                    if (beforeEquals.contains("[")) { // Get the size of the dimension from the brackets after the variable name if it is defined.
                        if (beforeEquals.matches(".*[1-9].*")) {
                            int sqBracket1 = contexStr.indexOf("[");
                            int sqBracket2 = contexStr.indexOf("]");
                            dimensions[i] = Integer.parseInt(beforeEquals.substring(sqBracket1 + 1, sqBracket2));
                            beforeEquals = beforeEquals.substring(beforeEquals.indexOf("]") + 1); // Move to the next dimension by removing the processed part.
                        }
                    } 
                    if (dimensions[i] == -1) { // If the size of the dimension is not defined in the brackets, get it from the number of elements in the initialization list.
                        int commaNum = (int) afterEquals.substring(afterEquals.indexOf("{"), afterEquals.indexOf("}")).chars().filter(ch -> ch == ',').count();
                        dimensions[i] = commaNum + 1; // Number of commas + 1 gives the number of elements in that dimension
                        afterEquals = afterEquals.substring(afterEquals.indexOf("}") + 1); // Move to the next dimension by cutting off the part of the string we've already processed
                    }
                }
                symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr), dimensions);
            } else {
                symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr));
            }

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
            String contexStr = context.getText();
            
            int eqIndex = contexStr.indexOf("=");
            String beforeEquals = contexStr.substring(0, eqIndex);
            String afterEquals = contexStr.substring(eqIndex + 1);
            
            int dims = (int) afterEquals.chars().filter(ch -> ch == '{').count(); // Get the number of dimensions.
            
            VariableSymbol symbol;

            // Get the correct number of dimensions for array declarations by counting the brackets after the equals sign, not in the type reference
            if (dims > 0) {
                int[] dimensions = new int[dims];
                Arrays.fill(dimensions, -1);
                for (int i = 0; i < dims; i++) {
                    if (beforeEquals.contains("[")) { // Get the size of the dimension from the brackets after the variable name if it is defined.
                        if (beforeEquals.matches(".*[1-9].*")) {
                            int sqBracket1 = contexStr.indexOf("[");
                            int sqBracket2 = contexStr.indexOf("]");
                            dimensions[i] = Integer.parseInt(beforeEquals.substring(sqBracket1 + 1, sqBracket2));
                            beforeEquals = beforeEquals.substring(beforeEquals.indexOf("]") + 1); // Move to the next dimension by removing the processed part.
                        }
                    } 
                    if (dimensions[i] == -1) { // If the size of the dimension is not defined in the brackets, get it from the number of elements in the initialization list.
                        int commaNum = (int) afterEquals.substring(afterEquals.indexOf("{"), afterEquals.indexOf("}")).chars().filter(ch -> ch == ',').count();
                        dimensions[i] = commaNum + 1; // Number of commas + 1 gives the number of elements in that dimension
                        afterEquals = afterEquals.substring(afterEquals.indexOf("}") + 1); // Move to the next dimension by cutting off the part of the string we've already processed
                    } 
                }
                symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr), dimensions);
            } else {
                symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr));
            }

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
    public Void visitThreadAssignment(OurGrammarParser.ThreadAssignmentContext context) {
        String id = context.ID().getText();
        String typeStr = context.typeRef().TYPE().getText();
        
        VariableSymbol symbol = new VariableSymbol(id, TypeSymbol.fromString(typeStr));
        
        if (!this.ctx.symbolTable.define(symbol)) {
            throw new RuntimeException("Duplicate declaration: '" + id + "'");
        }

        return visitChildren(context);
    }

    @Override
    public Void visitWhileStatement(OurGrammarParser.WhileStatementContext context) {
        this.ctx.symbolTable.pushScope(context);
        visitChildren(context);
        this.ctx.symbolTable.popScope();
        return null;
    }
}