grammar OurGrammar;

@header {
package p4project;
}

expr
    : equal ('&&' | '||') expr
    | equal
    ;

equal
    : comp ('==' | '!=') equal
    | comp
    ;

comp
    : additive ('<' | '>') comp
    | additive
    ;

additive
    : mult ('+' | '-') additive
    | mult
    ;

mult
    : power ('*' | '/' | '%') mult
    | power
    ;

power
    : factor '^' power
    | factor
    ;

factor
    : INT
    | '(' expr ')'
    ;

INT : [0-9]+ ;
WS  : [ \t\r\n]+ -> skip ;
