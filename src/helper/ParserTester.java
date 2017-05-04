package helper;

import java.io.FileReader;
import java.io.IOException;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import select.*;

public class ParserTester {
	
	static SELECTParser parser;
	
	public static void startParsing(String path) {
		CommonTokenStream tokens;
        String fileIn = path;
	        try {
	            SELECTLexer lexer = new SELECTLexer(new ANTLRReaderStream(new FileReader(fileIn))); 
		        tokens = new CommonTokenStream(lexer);
		        parse (tokens);
	        } catch (Exception e) {
	            System.out.println("ParserTester ERROR\n\n");
	            e.printStackTrace();
	        }
    }
	
	static void parse (CommonTokenStream tokens) throws IOException, RecognitionException {
	    parser = new SELECTParser(tokens);
	    parser.start();
	  }
}
