package p4project;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import p4project.context.CompilationContext;

public class ParserDriver {
    public static void main(String[] args) {
        String input = args.length > 0 ? String.join(" ", args) : "1+2*3";

        CharStream charStream = CharStreams.fromString(input);
        OurGrammarLexer lexer = new OurGrammarLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OurGrammarParser parser = new OurGrammarParser(tokens);

        ParseTree tree = parser.program();
        System.out.println(tree.toStringTree(parser));

        CompilationContext ctx = new CompilationContext();
    }
}
