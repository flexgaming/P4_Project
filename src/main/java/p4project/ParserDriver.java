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
import p4project.visitors.MutexVisitor;

// TODO - add arrayLiteral
// TODO - add arrayIndex
// TODO - floats have to have f in java. DONE
// TODO - initialize every variable to be 0 or null.
// TODO - mutex show m-1 instead of starting with 0. DONE
// TODO - make functions be generated outside main. DONE
// TODO - enable nested critical sections. DONE
// TODO - add mutex to function calls. DONE

public class ParserDriver {
    public static void main(String[] args) {
        // While loop example with break and continue:
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
    int[4][5] m;
    l = m;
    l[1][2] = 1222;
    l = {{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1}};
    int[][] n = {{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1}};
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
            y = y + 1; 
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
                    System.out.println(node.getText() + " -> " + sym + " (type: " + sym.type.name + ")" + (sym.prefixes.isEmpty() ? "" : ", prefixes: " + sym.prefixes));
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
}
