import java.util.Arrays;

/**
 * @author Terri Kelly
 * represents a tictactoe board and can manipulate it, both as a board and as a String. It can also check the win status of the board.
 */
public class TicTacToe {
	/**
	 * the number of rows in a tictactoe board
	 */
	public final static int ROWS = 3;
	/**
	 * the number of columns in a tictactoe board
	 */
	public final static int COLS = 3;
	/**
	 * the number of possible board combinations
	 */
	public final static int POSSIBILITIES = (int) Math.pow(3,9);
	/**
	 * the number of possible characters to appear in a space
	 */
	public final static int CHAR_POSSIBILITIES = 3; // x, o or space

	/**
	 * @param b the board being searched
	 * @param ch the character being counted in the board
	 * @return the number of the specified character in the board
	 * searches the board and returns the amount of times the specified character appears
	 */
	private static int numChars(char[][] b, char ch) {
		int total = 0;
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS; c++)
				if (ch == b[r][c]) 
					total++;
		return total;
	}

	/**
	 * @param board the board to be checked
	 * @return a boolean for if the board is valid or not
	 * returns whether or not the current board could actually exist in a game of tictactoe
	 */
	public static boolean valid(char[][] board) {

		// Ensure there are at least 3 xs and 2 os, or 3 os and 2 xs
		// Make sure there are at least one more x or one more o
		int numX = numChars(board, 'x');
		int numO = numChars(board, 'o');
		if (! (numX > 2 || numO > 2)) 
			return false;
		if ((numX == numO + 1) || (numO == numX + 1)) 
			return true;
		return false;
	}

	/**
	 * @param b the board to be converted
	 * @return the String representation of the board
	 * returns a String representation of the board, constructed by going in order through each row, left to right, top to bottom
	 */
	public static String boardToString(char[][] b) {
		String result = "";
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) 
				result += b[r][c];
			//     result += "\n";
		}
		return result;
	}

	/**
	 * @param board the String to be converted
	 * @return the board representation of the String
	 * returns a board representation of the String, constructed by going in order through each row, left to right, top to bottom
	 */
	public static char[][] stringToBoard(String board) {
		char[][] b = new char[ROWS][COLS];
		int index = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) 
				b[r][c] = whichLetter(board.charAt(index++));
		}
		return b;
	}


	/**
	 * @param ch the number as a character that is being checked
	 * @return which letter the number represents
	 * returns which tictactoe letter the given number represents
	 */
	public static char whichLetter(char ch) {
		switch (ch) {
		case '1' : 
			return 'x';
		case '2' : 
			return 'o';
		case '0'  : 
			return ' ';
		default: 
			return ch;
		}
	}

	/**
	 * @param s the String to create the board from
	 * @return the newly made board
	 * creates and returns a board from the given String representation
	 */
	public static char[][] makeBoard(String s) {
		char[][] b = new char[ROWS][COLS];
		int ch = 0;
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS; c++){         
				b[r][c] = whichLetter(s.charAt(ch));
				ch++;
			}
		return b;
	}

	/**
	 * @param s the number String representation of a board
	 * @return the new incremented String
	 * increments the base 3 number given to represent a board
	 */
	private static String addOne(String s) {
		// s is a 9 character string, composed of 0s, 1s, and 2s.  Add 1 to the last char, adjusting
		// all the rest of the characters as necessary.
		boolean carry = false;
		char ch[] = s.toCharArray();
		ch[ch.length-1] =  (char)((int)(ch[ch.length-1])+1);
		for (int n = ch.length-1; n >=0; n--) {
			if (carry) ch[n] = (char)((int)(ch[n])+1);
			if (ch[n] == '3') {
				carry = true;
				ch[n] = '0';
			}
			else
				carry = false;      
		}
		return new String(ch);
	}

	/**
	 * @return the filled values String array
	 * creates and returns a String array of all possible board integer Strings
	 */
	public static String[] fillValues() {
		String strBoard = "000000000";
		String[] values = new String[POSSIBILITIES];
		int index = 0;
		values[index++] = strBoard;
		while (!strBoard.equals("222222222") ) {
			strBoard = addOne(strBoard);
			values[index++] = strBoard;
		}
		return values;
	}

	/**
	 * @param board the board to be checked
	 * @return a boolean for if there is a diagonal win
	 * checks if there is a diagonal line of x's or o's signifying a win
	 */
	public static boolean diagonalWin(char[][] board) {
		int wins = 0;
		if ((board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x') || 
				(board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')) 
			wins++;


		if ((board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x') ||
				(board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o')) 
			wins++;

		return wins == 1;
	}

	/**
	 * @param board the board to be checked
	 * @return a boolean for if there is a horizontal win
	 * checks if there is a horizontal line of x's or o's signifying a win
	 */
	public static boolean rowWin(char[][] board) {
		char ch;
		int wins = 0;
		boolean win = true;
		for (int r = 0; r < ROWS; r++) {
			win = true;
			ch = board[r][0];
			for (int c = 0; c < COLS; c++) 
				if (ch != board[r][c]) {
					win = false;
					break;
				}
			if (win && ch != ' ')
				wins++;
		} 
		return wins == 1;
	} 
	
	/**
	 * @param board the board to be checked
	 * @return a boolean for if there is a horizontal win
	 * checks if there is a vertical line of x's or o's signifying a win
	 */
	public static boolean colWin(char[][] board) {
		char ch;
		int wins = 0;
		boolean win = true;
		for (int c = 0; c < COLS; c++) {
			win = true;
			ch = board[0][c];
			for (int r = 0; r < ROWS; r++) 
				if (ch != board[r][c]) {
					win = false;
					break;
				}
			if (win && ch != ' ') 
				wins++;
		} 
		return wins == 1;
	} 

	/**
	 * @param b the board to be checked
	 * @return boolean for if board is a win
	 * returns whether the given board is a win
	 */
	public static boolean isWin(char[][]b) {
		int wins = 0;
		if (valid(b)) {
			if (rowWin(b)) wins++;
			if (colWin(b)) wins++;
			if (diagonalWin(b)) wins++;
		}
		return wins == 1;
		//     return valid(b) && (rowWin(b) ^ colWin(b) ^ diagonalWin(b));
	}


	/**
	 * @param b board to be checked
	 * @return a String saying whether the board is a win or loss, and if the win came from row, column, or diagonal
	 * returns if the board is a win, and what type
	 */
	public static String isWinString(char[][]b) {
		boolean v = valid(b);
		if (v) {
			boolean r = rowWin(b);
			boolean c = colWin(b);
			boolean d = diagonalWin(b);
			int wins = 0;
			if (r) wins++;
			if (c) wins++;
			if (d) wins++;
			if (wins == 1) {
				if (r) 
					return "Row";
				if (c) 
					return "Col";
				if (d) 
					return "Dia";
				return "winner";

			}
		}   
		return "loser";
	}

	/**
	 * @param s String board to be checked
	 * @return whether the given String represents a winning board
	 * checks whether the given String is a win
	 */
	public static boolean isWin(String s) {
		char[][] b = stringToBoard(s);
		return isWin(b);
	}

	/**
	 * @param s String board to be checked
	 * @return whether the given String represents a winning board
	 * checks whether the given String is a win
	 */
	public static String isWinString(String s) {
		char[][] b = stringToBoard(s);
		return isWinString(b);
	}

	/**
	 * @param args unused
	 * tests various class functions
	 */
	public static void main(String[] args) {
		String s = "ooooxxxox";
		char[][] b = stringToBoard(s);
		System.out.println("Valid:   " + valid(b));
		System.out.println("Row Win: " + rowWin(b));
		System.out.println("Col Win: " + colWin(b));
		System.out.println("Dia Win: " + diagonalWin(b));
		System.out.println(isWin(b));

	}    
}