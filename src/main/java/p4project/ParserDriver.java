package p4project;

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
        // While loop example with break and continue:
        String input = "void main() { int x; x = 5; x = read(int); while (x > 0) { if (x == 3) { break; } else if (x == 4) { continue; } x = x - 1; } }";

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
                System.out.println(node.getText() + " -> " + sym + " (type: " + sym.type.name + ")");
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
            StringBuilder javaCode = new StringBuilder();
            javaCode.append("import java.util.Scanner;\n");
            javaCode.append("import java.util.concurrent.*;\n");
            javaCode.append("import java.util.concurrent.atomic.*;\n\n");
            if (!ctx.ftable.containsKey("main")) {
                javaCode.append("public class Main {\n");
                javaCode.append("    public static void main(String[] args) {\n");
                javaCode.append("    Scanner scanner = new Scanner(System.in);\n");
                javaCode.append("    ExecutorService executor = Executors.newCachedThreadPool();\n");
                javaCode.append(codeGenVisitor.visit(tree));
                javaCode.append("    executor.shutdown();\n");
                javaCode.append("    scanner.close();\n");
                javaCode.append("    }\n");
                javaCode.append("}\n");
            } else {
                javaCode.append(codeGenVisitor.visit(tree));
            }
            System.out.println("--- Generated Java Code Phase 5---");
            System.out.println(javaCode);

    }
}
