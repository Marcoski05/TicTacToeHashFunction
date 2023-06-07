import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO Make sure you remove all of the TODO comments from this file before turning it in

public class TicTacToeHashCode extends Board {

	boolean[] winners; // True if the hash string that maps to this index is a winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);
		// TODO Instantiate/fill winners array.
		winners = new boolean[(int) Math.pow(3, 9)];
		File file = new File("TicTacToeWinners.txt");
		Scanner sc;
		try { 
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File \"TicTacToeWinners.txt\" not in working directory");
			return;
		}
		while (sc.hasNextLine()) {
			winners[hashCode(sc.nextLine())] = true;
		}
		sc.close();
	}

	// TODO - write the myHashCode function. It must create a unique hashcode for
	// all of the
	// possible values the game board (3 ^ 9) and it MUST use the super.charAt(row,
	// col) function
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

	public boolean isWin() {
		return winners[myHashCode()];
	}

	public boolean isWin(String s) {
		return winners[hashCode(s)];
	}

	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		String s = "";

		File file = new File("TTT_Tests.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
			// TODO Update this line to call your isWin method.
			board.setWinner(board.isWin(s));
			// TicTacToe ttt = new TicTacToe();
			// System.out.println(ttt.isWin(s));
			board.show(s);
			Thread.sleep(4000);
			// board.displayRandomString();
		}
	}
}