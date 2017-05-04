// $ANTLR 3.4 D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g 2017-03-08 09:36:17

	package select;
	import helper.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SELECTParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALL_STAR", "AND", "APEX", "COMMA", "COMPARE", "DIGIT", "END_QUERY", "FROM", "ID", "LETTER", "LP", "MORE_RELATION", "NUMBER", "OR", "RP", "SELECT", "UNDERSCORE", "VARCHAR", "WHERE", "WS"
    };

    public static final int EOF=-1;
    public static final int ALL_STAR=4;
    public static final int AND=5;
    public static final int APEX=6;
    public static final int COMMA=7;
    public static final int COMPARE=8;
    public static final int DIGIT=9;
    public static final int END_QUERY=10;
    public static final int FROM=11;
    public static final int ID=12;
    public static final int LETTER=13;
    public static final int LP=14;
    public static final int MORE_RELATION=15;
    public static final int NUMBER=16;
    public static final int OR=17;
    public static final int RP=18;
    public static final int SELECT=19;
    public static final int UNDERSCORE=20;
    public static final int VARCHAR=21;
    public static final int WHERE=22;
    public static final int WS=23;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public SELECTParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public SELECTParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return SELECTParser.tokenNames; }
    public String getGrammarFileName() { return "D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g"; }


    ReadTableHelper rth = new ReadTableHelper();



    // $ANTLR start "start"
    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:20:1: start : selectLine= SELECT fields= fieldList FROM tableName= ID ( WHERE targets= targetList )? END_QUERY ;
    public final void start() throws RecognitionException {
        Token selectLine=null;
        Token tableName=null;
        String fields =null;

        String targets =null;


        try {
            // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:23:2: (selectLine= SELECT fields= fieldList FROM tableName= ID ( WHERE targets= targetList )? END_QUERY )
            // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:24:2: selectLine= SELECT fields= fieldList FROM tableName= ID ( WHERE targets= targetList )? END_QUERY
            {
            selectLine=(Token)match(input,SELECT,FOLLOW_SELECT_in_start51); 

            pushFollow(FOLLOW_fieldList_in_start57);
            fields=fieldList();

            state._fsp--;


            rth.addFields(fields);

            match(input,FROM,FOLLOW_FROM_in_start63); 

            tableName=(Token)match(input,ID,FOLLOW_ID_in_start69); 

            rth.addTable(tableName.getText());

            // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:28:2: ( WHERE targets= targetList )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==WHERE) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:29:2: WHERE targets= targetList
                    {
                    match(input,WHERE,FOLLOW_WHERE_in_start77); 

                    pushFollow(FOLLOW_targetList_in_start83);
                    targets=targetList();

                    state._fsp--;


                    rth.addTargets(targets);

                    }
                    break;

            }


            match(input,END_QUERY,FOLLOW_END_QUERY_in_start92); 


            		rth.printName();
            		rth.readTable();
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "start"



    // $ANTLR start "fieldList"
    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:39:1: fieldList returns [String list] : (fn= ALL_STAR |fn= ID ( COMMA )* )+ ;
    public final String fieldList() throws RecognitionException {
        String list = null;


        Token fn=null;

        try {
            // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:40:3: ( (fn= ALL_STAR |fn= ID ( COMMA )* )+ )
            // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:41:3: (fn= ALL_STAR |fn= ID ( COMMA )* )+
            {
            String fieldName; String returnString="";

            // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:42:3: (fn= ALL_STAR |fn= ID ( COMMA )* )+
            int cnt3=0;
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ALL_STAR) ) {
                    alt3=1;
                }
                else if ( (LA3_0==ID) ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:42:4: fn= ALL_STAR
            	    {
            	    fn=(Token)match(input,ALL_STAR,FOLLOW_ALL_STAR_in_fieldList119); 

            	    returnString=fn.getText();

            	    }
            	    break;
            	case 2 :
            	    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:44:3: fn= ID ( COMMA )*
            	    {
            	    fn=(Token)match(input,ID,FOLLOW_ID_in_fieldList131); 

            	    fieldName=fn.getText()+'-';

            	    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:45:3: ( COMMA )*
            	    loop2:
            	    do {
            	        int alt2=2;
            	        int LA2_0 = input.LA(1);

            	        if ( (LA2_0==COMMA) ) {
            	            alt2=1;
            	        }


            	        switch (alt2) {
            	    	case 1 :
            	    	    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:45:3: COMMA
            	    	    {
            	    	    match(input,COMMA,FOLLOW_COMMA_in_fieldList137); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop2;
            	        }
            	    } while (true);


            	    returnString+=fieldName;

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            list=returnString;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return list;
    }
    // $ANTLR end "fieldList"



    // $ANTLR start "targetList"
    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:50:1: targetList returns [String list] : (fn= ID cmp= COMPARE (vn= ( VARCHAR | NUMBER ) ) (moreRel= MORE_RELATION )* )* ;
    public final String targetList() throws RecognitionException {
        String list = null;


        Token fn=null;
        Token cmp=null;
        Token vn=null;
        Token moreRel=null;

        try {
            // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:51:3: ( (fn= ID cmp= COMPARE (vn= ( VARCHAR | NUMBER ) ) (moreRel= MORE_RELATION )* )* )
            // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:52:3: (fn= ID cmp= COMPARE (vn= ( VARCHAR | NUMBER ) ) (moreRel= MORE_RELATION )* )*
            {
            String fieldName; String compareValue; String valueName = ""; String returnString = "";

            // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:53:3: (fn= ID cmp= COMPARE (vn= ( VARCHAR | NUMBER ) ) (moreRel= MORE_RELATION )* )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==ID) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:54:3: fn= ID cmp= COMPARE (vn= ( VARCHAR | NUMBER ) ) (moreRel= MORE_RELATION )*
            	    {
            	    fn=(Token)match(input,ID,FOLLOW_ID_in_targetList178); 

            	    fieldName=fn.getText()+'-';

            	    cmp=(Token)match(input,COMPARE,FOLLOW_COMPARE_in_targetList186); 

            	    compareValue=cmp.getText()+']';

            	    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:56:3: (vn= ( VARCHAR | NUMBER ) )
            	    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:56:4: vn= ( VARCHAR | NUMBER )
            	    {
            	    vn=(Token)input.LT(1);

            	    if ( input.LA(1)==NUMBER||input.LA(1)==VARCHAR ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    valueName=vn.getText();

            	    }


            	    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:57:10: (moreRel= MORE_RELATION )*
            	    loop4:
            	    do {
            	        int alt4=2;
            	        int LA4_0 = input.LA(1);

            	        if ( (LA4_0==MORE_RELATION) ) {
            	            alt4=1;
            	        }


            	        switch (alt4) {
            	    	case 1 :
            	    	    // D:\\Programming\\Eclipse_workspace_ANTLR\\SELECT_ANTLR\\src\\SELECT.g:57:10: moreRel= MORE_RELATION
            	    	    {
            	    	    moreRel=(Token)match(input,MORE_RELATION,FOLLOW_MORE_RELATION_in_targetList210); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop4;
            	        }
            	    } while (true);



            	    								if(moreRel!=null){
            	    									returnString+=fieldName+compareValue+valueName+'['+moreRel.getText()+'|';
            	    									moreRel=null;
            	    								}
            	    								else
            	    	                                returnString+=fieldName+compareValue+valueName+'|';
            	                                    

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            list=returnString;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return list;
    }
    // $ANTLR end "targetList"

    // Delegated rules


 

    public static final BitSet FOLLOW_SELECT_in_start51 = new BitSet(new long[]{0x0000000000001010L});
    public static final BitSet FOLLOW_fieldList_in_start57 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_FROM_in_start63 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_start69 = new BitSet(new long[]{0x0000000000400400L});
    public static final BitSet FOLLOW_WHERE_in_start77 = new BitSet(new long[]{0x0000000000001400L});
    public static final BitSet FOLLOW_targetList_in_start83 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_END_QUERY_in_start92 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALL_STAR_in_fieldList119 = new BitSet(new long[]{0x0000000000001012L});
    public static final BitSet FOLLOW_ID_in_fieldList131 = new BitSet(new long[]{0x0000000000001092L});
    public static final BitSet FOLLOW_COMMA_in_fieldList137 = new BitSet(new long[]{0x0000000000001092L});
    public static final BitSet FOLLOW_ID_in_targetList178 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMPARE_in_targetList186 = new BitSet(new long[]{0x0000000000210000L});
    public static final BitSet FOLLOW_set_in_targetList195 = new BitSet(new long[]{0x0000000000009002L});
    public static final BitSet FOLLOW_MORE_RELATION_in_targetList210 = new BitSet(new long[]{0x0000000000009002L});

}