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
import p4project.visitors.FtableGenVisitor;
import p4project.visitors.RefLinkingVisitor;
import p4project.visitors.TypeCheckingVisitor;
import p4project.visitors.FtableGenVisitor;
import p4project.visitors.MutexVisitor;

public class ParserDriver {
    public static void main(String[] args) {
        String input = """
int func() {
    return 42;
}
void main() { 
    shared int x; 
    x = read(int); 
    thread t1 => { 
        print("In thread, x = ", x); 
    }
    thread t2 => { 
        print("In thread, x = ", x); 
    }
    awaitAll(t1, t2);
    x = 1;
    int wow = 69;
    int[][] l;
    l = [3][10];
    int[][] m = {{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1}};
    l = m;
    l[1][2] = 1222;
    //l = {{2}, {2}};
    //int[][] n = {{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1}};
    print(cast(string)l[2]);
    shared int y = 2;
    shared float z = 3.0;
    void criticalFunc(shared float a) {
        critical(a) {
            print("In criticalFunc, a = ", a);
            a = a + 1.0;
        }
    }

    critical(x, z) {
        critical(x, y, z) {
            print("In critical section, x = ", x, " y = ", y, " z = ", z); 
            x = x + 1; 
            y = y ^ 1; 
            z = z + 1.0; 
        } 
    }

    criticalFunc(z);

    func();
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
                if(node != null) {
                    System.out.println(node.getText() + " -> " + sym + 
                    " (type: " + sym.type.name + ")" + ((sym.arrType == null) ? "" : " dimSize: " + 
                    java.util.Arrays.toString(sym.arrType.dimSize)) + 
                    (sym.prefixes.isEmpty() ? "" : ", prefixes: " + sym.prefixes));
                } else {
                    System.out.println("\n!-!-!-!- COMPILATION CONTEXT RESOLVED SYMBOL " + node + " = NULL -!-!-!-!\n");
                }
            });
            System.out.println("--------------------------------------\n");

        // 3. Type checking
            TypeCheckingVisitor typeCheckingVisitor = new TypeCheckingVisitor(ctx);
            typeCheckingVisitor.visit(tree);
            System.out.println("--- Type Checking Passed Phase 3 ---\n");
            System.out.println("-----------------------------------\n");

        // 4. ftable generation
            FtableGenVisitor ftableGenVisitor = new FtableGenVisitor(ctx);
            ftableGenVisitor.visit(tree);
            System.out.println("--- Function Table After Phase 4 ---");
            ctx.ftable.forEach((name, func) -> {
                System.out.println(name + " -> " + func.toString() + ", parameters: " + func.parameters.size() + ", containsCriticalSection: " + func.containsCriticalSection);
            });
            System.out.println("-----------------------------------\n");

        // 5. Mutex analysis
            MutexVisitor mutexVisitor = new MutexVisitor(ctx);
            System.out.println("--- Analyzing Mutexes Phase 5 ---");
            mutexVisitor.visit(tree);
            System.out.println("-----------------------------------\n");

        // 6. Java Code Gen
            CodeGenVisitor codeGenVisitor = new CodeGenVisitor(ctx);
            StringBuilder javaCode = new StringBuilder();
            javaCode.append("import java.util.Scanner;\n");
            javaCode.append("import java.util.concurrent.*;\n");
            javaCode.append("import java.util.concurrent.Locks.*;\n");
            javaCode.append("import java.util.concurrent.CompletableFuture;\n\n");
            javaCode.append("public class Main {\n");
            if (!ctx.ftable.containsKey("main")) {
                javaCode.append("    public static void main(String[] args) {\n");
                javaCode.append("        Scanner scanner = new Scanner(System.in);\n");
                javaCode.append("        ExecutorService executor = Executors.newCachedThreadPool();\n");
                
                for (String shared : ctx.sharedVariables) {
                    javaCode.append("        Lock " + "m" + ctx.sharedVariables.indexOf(shared) + " = new ReentrantLock();\n");
                }
                
                javaCode.append(codeGenVisitor.visit(tree));
                javaCode.append("        executor.shutdown();\n");
                javaCode.append("        scanner.close();\n");
                javaCode.append("    }\n");
            } else {
                javaCode.append(codeGenVisitor.visit(tree));
            }
            javaCode.append("}\n");
            System.out.println("--- Generated Java Code Phase 6---\n");
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
            javaCode.append("import java.util.concurrent.CompletableFuture;\n\n");

            if (!ctx.ftable.containsKey("main")) {
                javaCode.append("    public static void main(String[] args) {\n");
                javaCode.append("        Scanner scanner = new Scanner(System.in);\n");
                javaCode.append("        ExecutorService executor = Executors.newCachedThreadPool();\n");
                
                for (String shared : ctx.sharedVariables) {
                    javaCode.append("        Lock " + "m" + ctx.sharedVariables.indexOf(shared) + " = new ReentrantLock();\n");
                }
                
                javaCode.append(codeGenVisitor.visit(tree));
                javaCode.append("        executor.shutdown();\n");
                javaCode.append("        scanner.close();\n");
                javaCode.append("    }\n");
            } else {
                javaCode.append(codeGenVisitor.visit(tree));
            }

            return javaCode.toString();

        } finally {
            System.setOut(originalOut);   // always restore console
        }
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
            javaCode.append("import java.util.concurrent.CompletableFuture;\n\n");

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
