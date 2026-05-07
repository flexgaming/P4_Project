grammar OurGrammar;

program
    : statement* EOF
    ;

statement
    : statementPrime ';'
    | assignment
    | threadAssignment
    | ifStatement
    | forStatement
    | whileStatement
    | criticalSection
    | block
    ;

statementPrime
    : arrayIndex '=' expr
    | reassignment
    | declaration 
    | awaitStatement 
    | continueStatement 
    | breakStatement 
    | returnStatement 
    | printStatement
    | functionCall
    ;

assignment
    : PREFIX? typeRef ID (assVar ';' | assFunc)
    ;

assVar
    : '=' expr
    ;

assFunc
    : '(' (PREFIX? typeRef ID (',' PREFIX? typeRef ID)*)? ')' block
    ;

reassignment
    : ID '=' expr
    ;

declaration
    : PREFIX? typeRef ID 
    ;

threadAssignment
    : typeRef ID '=>' block
    ;

awaitStatement
    : 'await' '(' ID (',' ID)* ')'
    | 'awaitAny' '(' ID (',' ID)* ')'
    ;

castExpression
    : 'cast' '(' TYPE ')' expr 
    ;

typeRef
    : TYPE ('[' (ID | INT)? ']')*
    ;

ifStatement
    :   'if' '(' expr ')' statement 
        ('else if' '(' expr ')' statement)* 
        ('else' statement)?
    ;

forStatement
    : 'for' '(' forVar ';' expr ';' reassignment ')' statement
    ;

forVar
    : PREFIX? typeRef ID (assVar | assFunc)
    | reassignment
    | ID
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
    : 'critical' '(' ID (',' ID)* ')' block
    ;

printStatement
    : 'print' '(' expr (',' expr)* ')'
    ;

read
    : 'read' '(' TYPE ')' 
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
    : ('[' expr (',' expr)* ']')+
    ;

arrayIndex
    : ID '[' expr ']' ('[' expr ']')*
    ;

factor
    : NEGATIVE factor
    | functionCall
    | arrayIndex
    | arrayLiteral
    | ID
    | INT
    | FLOAT
    | BOOL
    | CHAR
    | STRING
    | THREAD
    | '(' expr ')'
    | castExpression
    | read
    ;

functionCall
    : ID '(' (expr (',' expr)*)? ')'
    ;

NEGATIVE : '-';
INT : [0-9]+ ;
FLOAT : [0-9]+ ('.' [0-9]+?)? ;
BOOL : 'true' | 'false' ;
CHAR : '\'' . '\'' ;
STRING : '"' .*? '"' ;
THREAD : BOOL ;
PREFIX : 'shared' 'const'? 'static'? | 'shared'? 'const' 'static'? | 'shared'? 'const'? 'static' ;
TYPE : 'int' | 'float' | 'bool' | 'char' | 'string' | 'void' | 'thread' ;
ID : [a-zA-Z_][a-zA-Z0-9_]* ;

LINE_COMMENT : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;
WS  : [ \t\r\n]+ -> skip ;