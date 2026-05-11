package p4project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

// TODO - acceptance testing with sample programs and expected outputs

public class ParserDriver {
    public static void main(String[] args) {
        String input = """
void main() { 
    shared int x; 
    print(x, "Hello World"); 
    x = read(int); 
    thread t1 => { 
        print("In thread, x = ", x); 
    }
    thread t2 => { 
        print("In thread, x = ", x); 
    }
    awaitAll(t1, t2);
    while (x > 0) { 
        if (x == 3) { 
            break; 
        } else if (x == 4) { 
            continue;
        } 
        x = x - 1; 
    } 
}
        """;;

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
                System.out.println(node.getText() + " -> " + sym + " (type: " + sym.type.name + ")" + (sym.prefixes.isEmpty() ? "" : ", prefixes: " + sym.prefixes));
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
            javaCode.append("import java.util.concurrent.atomic.*;\n");
            javaCode.append("import java.util.concurrent.CompletableFuture<T>;\n\n");
            if (!ctx.ftable.containsKey("main")) {
                javaCode.append("public class Main {\n");
                javaCode.append("    public static void main(String[] args) {\n");
                javaCode.append("        Scanner scanner = new Scanner(System.in);\n");
                javaCode.append("        ExecutorService executor = Executors.newCachedThreadPool();\n");
                javaCode.append(codeGenVisitor.visit(tree));
                javaCode.append("        executor.shutdown();\n");
                javaCode.append("        scanner.close();\n");
                javaCode.append("    }\n");
                javaCode.append("}\n");
            } else {
                javaCode.append(codeGenVisitor.visit(tree));
            }
            System.out.println("--- Generated Java Code Phase 5---");
            System.out.println(javaCode);

    }

    /**
     * Runs only the Lexer and Parser, returning the Parse Tree as a string.
     * Throws if parsing fails.
     */
    public static String runLexerParserPipeline(String input) {
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new org.antlr.v4.runtime.BaseErrorListener() {
            @Override
            public void syntaxError(org.antlr.v4.runtime.Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, org.antlr.v4.runtime.RecognitionException e) {
                throw new RuntimeException("Syntax error at line " + line + ":" + charPositionInLine + " - " + msg);
            }
        });

        ParseTree tree = parser.program();
        return tree.toStringTree(parser);
    }

    /**
     * Runs Lexer, Parser, and Semantic Analysis (Symbol AssDec, RefLinking, TypeChecking).
     * Returns a simple success message if no exceptions occur.
     */
    public static String runParserSemanticPipeline(String input) {
        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new org.antlr.v4.runtime.BaseErrorListener() {
            @Override
            public void syntaxError(org.antlr.v4.runtime.Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, org.antlr.v4.runtime.RecognitionException e) {
                throw new RuntimeException("Syntax error at line " + line + ":" + charPositionInLine + " - " + msg);
            }
        });

        ParseTree tree = parser.program();

        CompilationContext ctx = new CompilationContext();
        ctx.symbolTable.pushScope(tree);

        // Phase 1: Symbol assignments and declarations
        AssDecVisitor assDecVisitor = new AssDecVisitor(ctx);
        assDecVisitor.visit(tree);

        // Phase 2: Reference linking
        RefLinkingVisitor refLinkingVisitor = new RefLinkingVisitor(ctx);
        refLinkingVisitor.visit(tree);

        // Phase 3: Type checking
        TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor(ctx);
        typeCheckingVisitor.visit(tree);

        return "success";
    }

    /**
     * Runs the full compilation pipeline on the given input string.
     * Returns the complete console-style output as a String.
     * Throws RuntimeException if any phase fails (we will catch it in tests).
     */
    public static String runFullPipeline(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        try {
            CharStream charStream = CharStreams.fromString(input);
            OurGrammarLexer lexer = new OurGrammarLexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            OurGrammarParser parser = new OurGrammarParser(tokens);

            ParseTree tree = parser.program();

            CompilationContext ctx = new CompilationContext();
            ctx.symbolTable.pushScope(tree); // global scope

            // Phase 1: Symbol assignments and declarations
            AssDecVisitor assDecVisitor = new AssDecVisitor(ctx);
            assDecVisitor.visit(tree);

            // Phase 2: Reference linking
            RefLinkingVisitor refLinkingVisitor = new RefLinkingVisitor(ctx);
            refLinkingVisitor.visit(tree);

            // Phase 3: Type checking
            TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor(ctx);
            typeCheckingVisitor.visit(tree);

            // Phase 4: ftable generation
            FtableGenVisitor ftableGenVisitor = new FtableGenVisitor(ctx);
            ftableGenVisitor.visit(tree);

            // Phase 5: Java Code Gen
            CodeGenVisitor codeGenVisitor = new CodeGenVisitor(ctx);
            StringBuilder javaCode = new StringBuilder();
            javaCode.append("import java.util.Scanner;\n");
            javaCode.append("import java.util.concurrent.*;\n");
            javaCode.append("import java.util.concurrent.atomic.*;\n");
            javaCode.append("import java.util.concurrent.CompletableFuture<T>;\n\n");

            if (!ctx.ftable.containsKey("main")) {
                javaCode.append("public class Main {\n");
                javaCode.append("    public static void main(String[] args) {\n");
                javaCode.append("        Scanner scanner = new Scanner(System.in);\n");
                javaCode.append("        ExecutorService executor = Executors.newCachedThreadPool();\n");
                javaCode.append(codeGenVisitor.visit(tree));
                javaCode.append("        executor.shutdown();\n");
                javaCode.append("        scanner.close();\n");
                javaCode.append("    }\n");
                javaCode.append("}\n");
            } else {
                javaCode.append(codeGenVisitor.visit(tree));
            }

            return javaCode.toString();

        } finally {
            System.setOut(originalOut);   // always restore console
        }
    }
}
