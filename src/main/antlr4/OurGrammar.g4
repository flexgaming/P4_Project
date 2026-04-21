grammar OurGrammar;

@header {
package p4project;
}

program
    : statement* EOF // EOF = End Of File
    ;
    
statement
    : expr ';'
    | arrayIndex '=' expr ';'
    | assignment
    | reassignment ';'
    | declaration ';'
    | threadAssignment
    | awaitStatement ';'
    | ifStatement
    | forStatement
    | whileStatement
    | continueStatement ';'
    | breakStatement ';'
    | block
    | returnStatement ';'
    | criticalSection
    | printStatement ';'
    | readStatement ';'
    ;

assignment
    : PREFIX* typeRef ID ('=' expr ';' | '(' (PREFIX? typeRef ID (',' PREFIX? typeRef ID)*)? ')' block)
    // | ID ('+=' | '-=') expr ';' // Maybe add this after MVP "| '*=' | '/=' | '%='"
    ;

reassignment
    : ID '=' expr
    ;

declaration
    : PREFIX* typeRef ID
    ;

threadAssignment
    : typeRef ID '=>' block // Functions like a lambda expression
    ;

awaitStatement
    : 'await' '(' expr (',' expr)* ')'
    | 'awaitAny' '(' expr (',' expr)* ')'
    ;

castExpression
    : 'cast' '(' TYPE ')' expr
    ;

typeRef
    : TYPE ('[' (ID | INT)? ']')*
    ;

ifStatement
    : 'if' '(' expr ')' statement ('else if' statement)* ('else' statement)?
    ;

forStatement
    : 'for' '(' (assignment | ID ';') expr ';' reassignment ')' statement
    ;

whileStatement
    : 'while' '(' expr ')' statement
    ;

continueStatement
    : 'continue'
    ;

breakStatement
    : 'break'
    ;

block
    : '{' statement* '}'
    ;

returnStatement
    : 'return' expr?
    ;

criticalSection
    : 'critical''(' ID (',' ID)* ')' block
    ;

printStatement
    : 'print' '(' expr (',' expr)* ')'
    ;

readStatement
    : 'read' '(' ID ')'
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
    : ID '[' expr ']' ('[' expr ']')*
    ;

factor
    : '-' factor
    | functionCall
    | ID ('[' expr ']')*
    | arrayLiteral
    | INT
    | FLOAT
    | BOOL
    | CHAR
    | STRING
    | THREAD
    | '(' expr ')'
    | castExpression
    ;

functionCall
    : ID '(' (expr (',' expr)*)? ')' 
    ;

INT : [0-9]+ ;
FLOAT : [0-9]+ ('.' [0-9]+?)? ;
BOOL : 'true' | 'false' ;
CHAR : '\'' . '\'' ;
STRING : '"' .*? '"' ;
THREAD : BOOL ; // starts with false and is true when task is done.
PREFIX : 'shared' | 'const' | 'static' ;
TYPE : 'int' | 'float' | 'bool' | 'char' | 'string' | 'void' | 'thread' ;
ID : [a-zA-Z_][a-zA-Z0-9_]* ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;
WS  : [ \t\r\n]+ -> skip ;
