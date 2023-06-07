import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Marco Cecchi-Rivas
 * creates a hashCode for every possible tictactoe board and can check if it is a winner
 */
public class TicTacToeHashCode extends Board {

	/**
	 * boolean array that holds True if the hash string that maps to this index is a winner, false otherwise
	 */
	boolean[] winners; // True if the hash string that maps to this index is a winner, false otherwise

	/**
	 * @param s title of frame
	 * creates the tictactoe board and fills the winners array with all winning boards
	 */
	TicTacToeHashCode(String s) {
		super(s);
		winners = new boolean[(int) Math.pow(3, 9)];
		File file = new File("TicTacToeWinners.txt");
		Scanner sc;
		try { 
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File \"TicTacToeWinners.txt\" not in working directory");
			return;
		}
		while (sc.hasNextLine()) {
			winners[hashCode(sc.nextLine())] = true;
		}
		sc.close();
	}


	/**
	 * @return integer hashCode value
	 * generates the hashCode value from the current board, with a single integer for each possible board combination
	 */
	@Override
	public int myHashCode() {
		int placeValue = 0;
		int code = 0;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				switch (charAt(r, c)) {
				case 'x':
					code += Math.pow(3, placeValue);
					break;
				case 'o':
					code += 2 * Math.pow(3, placeValue);
					break;
				}
				placeValue++;
			}
		}
		return code;
	}

	/**
	 * @param s the string that the hashCode is generated from
	 * @return the hashcode for the given string
	 * generates the hashcode value from the given String,  with a single integer for each possible board/String combination
	 */
	public int hashCode(String s) {
		int placeValue = 0;
		int code = 0;

		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'x':
				code += Math.pow(3, placeValue);
				break;
			case 'o':
				code += 2 * Math.pow(3, placeValue);
				break;
			}
			placeValue++;
		}

		return code;
	}

	/**
	 * @return if the current board is a win
	 * returns if the current board is a win by checking it in the winners array
	 */
	public boolean isWin() {
		return winners[myHashCode()];
	}

	/**
	 * @param s the String that will be checked
	 * @return if the String is a win by checking it in the winners array
	 * returns whether or not the String represents a winning combination in the winners array
	 */
	public boolean isWin(String s) {
		return winners[hashCode(s)];
	}

	/**
	 * @param args unused
	 * @throws InterruptedException for the Thread.sleep() call
	 * tests many tictactoe board combinations from the TTT_Tests.txt file
	 */
	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		String s = "";

		File file = new File("TTT_Tests.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File \"TTT_Tests.txt\" not in working directory");
			return;
		}

		while (true) {
			s = sc.nextLine();
			// String currentBoard = board.boardValues[(int)(Math.random()*
			// board.boardValues.length)];
			// board.show(currentBoard);
			// board.setHashCode(board.myHashCode());
			board.show(s);
			board.setHashCodeLabel(board.myHashCode());
			board.setWinner(board.isWin(s));
			// TicTacToe ttt = new TicTacToe();
			// System.out.println(ttt.isWin(s));
			board.show(s);
			Thread.sleep(4000);
			// board.displayRandomString();
		}
	}
}