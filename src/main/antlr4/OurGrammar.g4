grammar OurGrammar;

@header {
package p4project;
}

program
    : statement* EOF
    ;
    
statement
    : expr ';'
    | arrayIndex '=' expr ';'
    | assignment
    | ifStatement
    | forStatement
    | block
    ;

assignment
    : PREFIX* typeRef ID ('=' expr ';' | '(' (typeRef ID (',' typeRef ID)*)? ')' block)
    ;

typeRef
    : TYPE ('[' ']')*
    ;

ifStatement
    : 'if' '(' expr ')' statement ('else if' statement)* ('else' statement)?
    ;

forStatement
    : 'for' '(' expr ';' expr ';' additive ')' statement
    ;

block
    : '{' statement* '}'
    ;

expr
    : equal ('&&' | '||') expr
    | equal
    ;

equal
    : comp ('==' | '!=') equal
    | comp
    ;

comp
    : additive ('<' | '>' | '<=' | '>=') comp
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

arrayLiteral
    : '[' expr (',' expr)* ']'
    ;

arrayIndex
    : ID '[' expr ']'
    ;

factor
    : functionCall
    | ID ('[' expr ']')*
    | arrayLiteral
    | INT
    | FLOAT
    | BOOL
    | CHAR
    | STRING
    | '(' expr ')'
    ;

functionCall
    : ID '(' (expr (',' expr)*)? ')' 
    ;

INT : [0-9]+ ;
FLOAT : [0-9]+ '.' [0-9]+ ;
BOOL : 'true' | 'false' ;
CHAR : '\'' . '\'' ;
STRING : '"' .*? '"' ;
PREFIX : 'shared' | 'const' | 'static' ;
TYPE : 'int' | 'float' | 'bool' | 'char' | 'string' | 'void' ;
ID : [a-zA-Z_][a-zA-Z0-9_]* ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;
WS  : [ \t\r\n]+ -> skip ;
