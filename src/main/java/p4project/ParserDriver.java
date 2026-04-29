package p4project;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import p4project.context.CompilationContext;
import p4project.visitors.CodeGenVisitor;

public class ParserDriver {
    public static void main(String[] args) {
        String input = args.length > 0 ? String.join(" ", args) : "int x; x = 1 + 2;";

        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);

        ParseTree tree = parser.program();
        System.out.println("--- Parse Tree ---");
        System.out.println(tree.toStringTree(parser));
        System.out.println("------------------\n");
 
        // 1. Initialize Shared Context
        CompilationContext ctx = new CompilationContext();
        
        // 3. Generate and Print Java Code
        String javaCode = new CodeGenVisitor().visit(tree);
        System.out.println("--- Generated Java Code ---");
        System.out.println(javaCode);
    }
}
