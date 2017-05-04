grammar SELECT;

options {
  language = Java;
}

@header{
	package select;
	import helper.*;
}

@lexer::header{
	package select;
}

@members{
ReadTableHelper rth = new ReadTableHelper();
}

start

@init{}
	:
	selectLine=SELECT 
	fields=fieldList {rth.addFields(fields);} 
	FROM 
	tableName=ID {rth.addTable(tableName.getText());}
	(
	WHERE 
	targets=targetList {rth.addTargets(targets);}
	)?
	END_QUERY
	{
		rth.printName();
		rth.readTable();
	}
	;

fieldList returns[String list]
		:
		{String fieldName; String returnString="";}
		(fn=ALL_STAR {returnString=fn.getText();}
		|
		fn=ID {fieldName=fn.getText()+'-';}
		COMMA* {returnString+=fieldName;}//gestione virgole mutliple
		)+
		{list=returnString;}
		;

targetList returns [String list]
		:
		{String fieldName; String compareValue; String valueName = ""; String returnString = "";}
		(
		fn=ID {fieldName=fn.getText()+'-';}
		cmp=COMPARE {compareValue=cmp.getText()+']';}
		(vn=(VARCHAR | NUMBER) {valueName=vn.getText();})
		moreRel=MORE_RELATION* {
								if(moreRel!=null){
									returnString+=fieldName+compareValue+valueName+'['+moreRel.getText()+'|';
									moreRel=null;
								}
								else
	                                returnString+=fieldName+compareValue+valueName+'|';
                                }//gestione virgole multiple
		)*
		{list=returnString;}
		;

//LEXER RULE
fragment LETTER: ('A'..'Z')|('a'..'z');

fragment DIGIT: ('0'..'9');

WS : (' '|'\t'|'\n'|'\r'|'\f')+ {$channel=HIDDEN;};

SELECT : 'SELECT' ;

FROM : 'FROM' ;

WHERE : 'WHERE' ;

COMMA: ',';

APEX : '\'' ;

UNDERSCORE : '_' ;

ALL_STAR : '`*`' ;

COMPARE: '=' | '<' | '>';

fragment AND : 'AND' ;

fragment OR : 'OR' ;

MORE_RELATION: (AND | OR)*;

LP : '(' ;

RP : ')' ;

END_QUERY : ';' ;

NUMBER : DIGIT+ ;

VARCHAR : APEX (LETTER | DIGIT | UNDERSCORE | ' ')+ APEX ;

ID: '`' LETTER (LETTER | DIGIT | UNDERSCORE)* '`';
