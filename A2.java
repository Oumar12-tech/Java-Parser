

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class A2 {

	
	public static int charClass;
	public static String nextLine;
	public static char lexeme;
	public static char nextChar;
	public static int lexLen;
	public static int nextToken;
	public static boolean floatFl = false;
	public static String lexemeTem = "";
	public static int i = 0;
	public static boolean endLineFl;
	public static String numLexemeTem = "";
	public static final int F = 9;
	public static final int I = 10;
	public static final int P = 11;
	public static final int LETTER = 12;
	public static final int DIGIT = 13;
	public static final int DOT = 14;
	public static final int UNKNOWN = 99;
	public static final int FLOATDCL = 0;
	public static final int INTDCL = 1;
	public static final int PRINT = 2;
	public static final int ID = 3;
	public static final int ASSIGN = 4;
	public static final int PLUS = 5;
	public static final int MINUS = 6;
	public static final int INUM = 7;
	public static final int FNUM = 8;

	public static void main(String[] args) {
		try {
			
			File file = new File(args[0]);
			Scanner sc = new Scanner(file); 

			
			getCharSh(sc);
			lex();
			prog();

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR - cannot open front.in \n");
		}
	}

	

	// Parses the program.
	static void prog() {
		System.out.println("Enter <Prog>");
		if (nextToken == FLOATDCL || nextToken == INTDCL) {
			dcls();
		}
		if (nextToken == ID || nextToken == PRINT) {
			stmts();
		}
		System.out.println("Exit <Prog>");
	}

	// Parses declarations.
	static void dcls() {
		System.out.println("Enter <Dcls>");
		dcl();
		while (nextToken == FLOATDCL || nextToken == INTDCL) {
			dcls();
		}
		System.out.println("Exit <Dcls>");
	}

	// Parses a declaration.
	static void dcl() {
		System.out.println("Enter <Dcl>");
		if (nextToken == FLOATDCL || nextToken == INTDCL) {
			lex();
			lex();
		}
		System.out.println("Exit <Dcl>");
	}

	// Parses statements.
	static void stmts() {
		System.out.println("Enter <Stmts>");
		stmt();
		while (nextToken == ID || nextToken == PRINT) {
			stmts();
		}
		System.out.println("Exit <Stmts>");
	}

	// Parses a statement.
	static void stmt() {
		System.out.println("Enter <Stmt>");
		if (nextToken == ID) {
			lex();
			lex();
			val();
			if (nextToken == PLUS || nextToken == MINUS) {
				expr();
			}
		} else if (nextToken == PRINT) {
			lex();
			lex();
		}
		System.out.println("Leave <Stmt>");
	}

	// Parses an expression.
	static void expr() {
		System.out.println("Enter <Expr>");
		lex();
		val();
		if (nextToken == PLUS || nextToken == MINUS) {
			expr();
		}
		System.out.println("Leave <Expr>");
	}

	// Parses a value.
	static void val() {
		System.out.println("Enter <Val>");
		lex();
		System.out.println("Leave <Val>");
	}

	// Gets the characters for tokenization.
	static void getCharSh(Scanner sc) {
		if (sc.hasNextLine()) {
			nextLine = sc.nextLine();
			if (nextLine.length() > 0) {
				getChar(nextLine);
			}
		}
	}

	// Gets the next character for tokenization.
	static void getChar(String nextLine) {
		nextChar = nextLine.charAt(i);
		if (nextLine.length() - 1 > i) {
			i++;
		} else if (nextLine.length() > i && !endLineFl) {
			endLineFl = true;
		} else {
			nextChar = '$';
		}
		if (nextChar == 'a' || nextChar == 'b' || nextChar == 'c' || nextChar == 'd' || nextChar == 'e'
				|| nextChar == 'g' || nextChar == 'h' || nextChar == 'j' || nextChar == 'k'
				|| nextChar == 'l' || nextChar == 'm' || nextChar == 'n' || nextChar == 'o'
				|| nextChar == 'q' || nextChar == 'r' || nextChar == 's' || nextChar == 't'
				|| nextChar == 'u' || nextChar == 'v' || nextChar == 'w' || nextChar == 'x'
				|| nextChar == 'y' || nextChar == 'z') {
			charClass = LETTER;
		} else if (nextChar == 'f') {
			charClass = F;
		} else if (nextChar == 'i') {
			charClass = I;
		} else if (nextChar == 'p') {
			charClass = P;
		} else if (nextChar == '0' || nextChar == '1' || nextChar == '2' || nextChar == '3' ||
				nextChar == '4' || nextChar == '5' || nextChar == '6' || nextChar == '7' ||
				nextChar == '8' || nextChar == '9') {
			charClass = DIGIT;
		} else if (nextChar == '.') {
			charClass = DOT;
		} else if (Character.isWhitespace(nextChar)) {
		} else {
			charClass = UNKNOWN;
		}
	}

	// Tokenizes the input.
	static int lex() {
		lexLen = 0;
		getWhiteSpace();
		switch (charClass) {
			case F:
				addChar();
				getChar(nextLine);
				nextToken = FLOATDCL;
				break;
			case I:
				addChar();
				getChar(nextLine);
				nextToken = INTDCL;
				break;
			case P:
				addChar();
				getChar(nextLine);
				nextToken = PRINT;
				break;
			case LETTER:
				addChar();
				getChar(nextLine);
				nextToken = ID;
				break;
			case DIGIT:
				addChar();
				getChar(nextLine);
				while (charClass == DIGIT) {
					addChar();
					getChar(nextLine);
					floatFl = false;
				}
				if (charClass == DOT && !floatFl) {
					addChar();
					getChar(nextLine);
					floatFl = true;
					while (charClass == DIGIT) {
						addChar();
						getChar(nextLine);
					}
					if (floatFl) {
						nextToken = FNUM;
					}
				}
				if (!floatFl) {
					nextToken = INUM;
				}
				break;
			case UNKNOWN:
				tokenLookup(nextChar);
				getChar(nextLine);
				break;
			default:
				nextToken = -1;
				break;
		}
		if (nextToken != -1 && numLexemeTem != "") {
			numLexemeTem = numLexemeTem.replaceAll("\u0000", "");
			System.out.println("Next token is: " + nextToken + ", Next lexeme is " + numLexemeTem);
			numLexemeTem = "";
		} else if (nextToken != -1) {
			lexemeTem = lexemeTem.replaceAll("\u0000", "");
			System.out.println("Next token is: " + nextToken + ", Next lexeme is " + lexemeTem);
		}
		return nextToken;
	}

	// Skips white spaces to get the next character.
	static void getWhiteSpace() {
		while (Character.isWhitespace(nextChar)) {
			getChar(nextLine);
		}
	}

	// Appends the current character to the lexeme.
	static void addChar() {
		String nextCharTemp = "";
		nextCharTemp = nextChar + "";
		lexemeTem = lexeme + "";
		lexemeTem += nextCharTemp;
		if (charClass == 13 || charClass == 14) {
			if (!Character.isWhitespace(lexemeTem.charAt(lexemeTem.length() - 1))) {
				numLexemeTem += lexemeTem;
			}
		}
	}

	// Looks up the token based on the given character.
	static int tokenLookup(char ch) {
		switch (ch) {
			case '=':
				addChar();
				nextToken = ASSIGN;
				break;
			case '+':
				addChar();
				nextToken = PLUS;
				break;
			case '-':
				addChar();
				nextToken = MINUS;
				break;
			default:
				addChar();
				nextToken = -1;
				break;
		}
		return nextToken;
	}
}
