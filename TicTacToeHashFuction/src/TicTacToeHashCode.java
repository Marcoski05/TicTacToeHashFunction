//TODO Make sure you remove all of the TODO comments from this file before turning it in

public class TicTacToeHashCode extends Board {

	boolean[] winners; // True if the hash string that maps to this index is a winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);
		// TODO Instantiate/fill winners array.
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
					case 'o':
						code += Math.pow(3, placeValue);
						break;
					case 'x':
						code += 2 * Math.pow(3, placeValue);
						break;
				}
				placeValue++;
			}
		}
		return code;
	}

	public boolean isWin() {
		return false;
	}

	public boolean isWin(String s) {
		// return the value in the winner array for the hash code of the board string
		// sent in.
		return true;
	}

	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		String s = "";
		java.util.Scanner kb = new java.util.Scanner(System.in);

		while (true) {
			s = kb.nextLine();
			// String currentBoard = board.boardValues[(int)(Math.random()*
			// board.boardValues.length)];
			// board.show(currentBoard);
			// board.setHashCode(board.myHashCode());
			// TODO Update this line to call your isWin method.
			// board.setWinner(TicTacToe.isWin(currentBoard));
			TicTacToe ttt = new TicTacToe();
			System.out.println(ttt.isWin(s));
			board.show(s);
			// Thread.sleep(4000);
			// board.displayRandomString();
		}
	}
}