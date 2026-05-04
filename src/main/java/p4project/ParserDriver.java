package p4project;

import java.io.Console;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import p4project.context.CompilationContext;
import p4project.visitors.AssDecVisitor;
import p4project.visitors.CodeGenVisitor;
import p4project.visitors.RefLinkingVisitor;
import p4project.visitors.TypeCheckingVisitor;
import p4project.visitors.FtableGenVisitor;

public class ParserDriver {
    public static void main(String[] args) {
        String input = "float bah(float a) { return a + 1.0; } void main() { int x = 5; int y = 10; if (x > y) { float lol = 2.0; lol = bah(lol); } else if (x == 0) { x = 10; } else { x = x + 1; } }";


        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);

        ParseTree tree = parser.program();
        System.out.println("--- Parse Tree ---");
        System.out.println(tree.toStringTree(parser));
        System.out.println("------------------\n");
        CompilationContext ctx = new CompilationContext();
        ctx.symbolTable.pushScope(tree); // global scope

        // 1. Symbol assignments and declerations
            AssDecVisitor assDecVisitor = new AssDecVisitor(ctx);
            assDecVisitor.visit(tree);
            // print the symbol table after phase 1:
            System.out.println("--- Symbol Table After Phase 1 ---");
            // ctx.symbolTable.printSymbolTable(); //No longer exists lol
            System.out.println("----------------------------------\n");

        // 2. Reference linking
            RefLinkingVisitor refLinkingVisitor = new RefLinkingVisitor(ctx);
            refLinkingVisitor.visit(tree);
            // print the resolved symbols after phase 2:
            System.out.println("--- Resolved Symbols After Phase 2 ---");
            ctx.resolvedSymbols.forEach((node, sym) -> {
                System.out.println(node.getText() + " -> " + sym);
            });
            System.out.println("--------------------------------------\n");

        // 3. Type checking
            TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor(ctx);
            typeCheckingVisitor.visit(tree);
            System.out.println("--- Type Checking Passed Phase 3 ---\n");

        // 4. ftable generation
            FtableGenVisitor ftableGenVisitor = new FtableGenVisitor(ctx);
            ftableGenVisitor.visit(tree);
            System.out.println("--- Function Table After Phase 4 ---");
            ctx.ftable.forEach((name, func) -> {
                System.out.println(name + " -> " + func);
            });
            System.out.println("-----------------------------------\n");


        // 5. Java Code Gen
            CodeGenVisitor codeGenVisitor = new CodeGenVisitor(ctx);
            String javaCode = codeGenVisitor.visit(tree);
            System.out.println("--- Generated Java Code Phase 5---");
            System.out.println(javaCode);

    }
}
