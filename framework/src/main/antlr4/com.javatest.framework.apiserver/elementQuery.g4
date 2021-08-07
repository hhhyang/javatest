
grammar ElementQuery ;

// parser

query : query queryAnd query     # andQuery
    | query queryOr query        # orQuery
    | queryNot query             # notQuery
    | '(' query ')'         # subQuery
    | ID ':' predicate      # predicateQuery
    | ID ':' VALUE          # valueQuery
    | ID ':' VALUE_LIST     # valueListQuery
    ;

queryAnd : AND ;
queryOr : OR ;
queryNot : NOT ;

predicate : predicate '.' AND '(' predicate ')'
    | predicate '.' OR '(' predicate ')'
    | NOT '(' predicate ')'
    | eqPredicate
    | neqPredicate
    | ltPredicate
    | ltePredicate
    | gtPredicate
    | gtePredicate
    | insidePredicate
    | outsidePredicate
    | betweenPredicate
    | withinPredicate
    | withoutPredicate
    | startingWithPredicate
    | endingWithPredicate
    | containingPredicate
    | notStartwithPredicate
    | notEndingWithPredicate
    | notContainingPredicate
    | textPrefixPredicate
    | textRegexPredicate
    | textFuzzyPredicate
    ;


eqPredicate : 'eq(' VALUE ')' ;
neqPredicate : 'neq(' VALUE ')' ;

ltPredicate : 'lt(' NUMBER ')' ;
ltePredicate : 'lte(' NUMBER ')' ;
gtPredicate : 'gt(' NUMBER ')' ;
gtePredicate : 'gte(' NUMBER ')' ;
insidePredicate : 'inside(' NUMBER '.' NUMBER ')' ;
outsidePredicate : 'outside(' NUMBER '.' NUMBER ')' ;
betweenPredicate : 'between(' NUMBER '.' NUMBER ')' ;

withinPredicate : 'within(' VALUE_LIST ')' ;
withoutPredicate : 'without(' VALUE_LIST ')' ;

startingWithPredicate : 'startingWith(' STRING ')' ;
endingWithPredicate : 'endingWith(' STRING ')' ;
containingPredicate : 'containing(' STRING ')' ;
notStartwithPredicate : 'notStartingWith(' STRING ')' ;
notEndingWithPredicate : 'notEndingWith(' STRING ')' ;
notContainingPredicate : 'notContaining(' STRING ')' ;

textPrefixPredicate : 'textPrefix(' STRING ')' ;
textRegexPredicate : 'textRegex(' STRING ')' ;
textFuzzyPredicate : 'textFuzzy(' STRING ')' ;


// lexer
AND : [aA][nN][dD] ;
OR : [oO][rR] ;
NOT : [nN][oO][tT] ;

ID : [a-zA-Z] ([a-zA-Z] | [0-9] | '_' | '-')* ;

VALUE : STRING | NUMBER ;
VALUE_LIST : (STRING (',' STRING)*) | (NUMBER (',' NUMBER)*) ;
STRING : ('\'' .*? '\'') | ('"' .*? '"');
NUMBER
   : '-'? INT ('.' [0-9] +)? EXP?
   ;

fragment INT
   : '0' | [1-9] [0-9]*
   ;

// no leading zeros

fragment EXP
   : [Ee] [+\-]? INT
   ;

// \- since - means "range" inside [...]

WS
   : [ \t\n\r] + -> skip
   ;
