package p4project.visitors;

import java.util.HashMap;
import java.util.Map;

import p4project.OurGrammarBaseVisitor;
import p4project.OurGrammarParser;

/*
    -> Phase 1: Symbol assignments and declerations
    Phase 2: Reference linking
    Phase 3: Type checking
    Phase 4: vtable and ftable generation
    Phase 5: Java Code Gen
*/

public class AssDecVisitor extends OurGrammarBaseVisitor<Integer> {

    Map<String, Integer> memory = new HashMap<>();

    /**
     * assignment
     *     : PREFIX? typeRef ID ( assVar ';' | assFunc )
     *     ;
     */
    @Override
    public Integer visitAssignment(OurGrammarParser.AssignmentContext ctx) {
        // You can check if PREFIX exists:
        // boolean hasPrefix = ctx.PREFIX() != null;
        // String typeRef = ctx.typeRef().getText();
        // String id = ctx.ID().getText();
        if (ctx.assVar() != null) {
            // It's a variable assignment
            // return visit(ctx.assVar());
        } else if (ctx.assFunc() != null) {
            // It's a function assignment
            // return visit(ctx.assFunc());
        }
        // Default behavior: visit children
        return visitChildren(ctx);
    }
}
