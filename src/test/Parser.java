package test;
public class Parser {
	/*
	 * Questo � una sorta di MAIN da cui faccio partire l'esecuzione del programma
	 */
	public static void main(String[] args) {
		String fileIn = ".\\resources\\input.txt";
		//FileWriter fOut;
		/*
		 * Non scrivo qui su foglio ma faccio solo il parsing
		 * Ci penser� poi la classe ReadTableHelper richiamata
		 * dal grammar a scrivere
		 */
		
		helper.ParserTester.startParsing(fileIn);
	}
}
