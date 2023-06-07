import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

/**
 * @author Terri Kelly
 * handles the graphics of the tictactoe board and allows for its manipulation
 */
abstract class Board extends JFrame implements ActionListener {

	/**
	 * each position on the tictactoe board
	 */
	private JButton buttons[][];
	/**
	 * the hashCode value of the current board
	 */
	private JLabel lblHashCode;
	/**
	 * whether the current board is a win or loss
	 */
	private JLabel lblWinTitle;

	/**
	 * The String that represents the board
	 */
	private String boardString = "";

	/**
	 * @param title the title of the frame
	 * creates and sets up the JFrame
	 */
	public Board(String title) {
		super(title);
		setupFrame();
	}

	/**
	 * @param hashcode the hashcode to be updated
	 * updates the hashcode display text to what is given
	 */
	public void setHashCodeLabel(int hashcode) {
		lblHashCode.setText("" + hashcode);
	}

	/**
	 * @param result the win text to be updated
	 * updates the win display text to what is given
	 */
	public void setWinner(String result) {
		lblWinTitle.setText(result);
	}
	
	/**
	 * @param result whether the win text should be set to win or loss
	 * updates the win display text based on the given boolean
	 */
	public void setWinner(boolean result) {
		if (result)
			setWinner("Winner");
		else
			setWinner("Loser");
	}
	//  required because of abstract method, but not used   
	@Override
	public void actionPerformed(ActionEvent e) { }

	/**
	 * @return the newly set up panel
	 * sets up the top panel which includes the display text
	 */
	JPanel setupPanelOne() {
		JPanel panel = new JPanel();
		JLabel lblHCTitle = new JLabel("Hash Code");;
		lblHashCode = new JLabel("" + myHashCode());
		lblWinTitle = new JLabel(""); // Will say either Winner or Loser 
		setWinner(TicTacToe.isWin(boardString));
		panel.setLayout(new FlowLayout());    
		panel.add(lblHCTitle);
		panel.add(lblHashCode);  
		panel.add(lblWinTitle);  
		return panel;
	}

	/**
	 * @return the newly set up inner panel
	 * sets up the bottom panel which holds the buttons of the board
	 */
	private JPanel setupPanelTwo() {
		JButton b;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToe.ROWS,TicTacToe.COLS));
		buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];

		int count = 1;

		for (int r = 0; r < TicTacToe.ROWS; r++) 
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();           
				b = new JButton("" + ch);
				boardString += ch;
				b.setActionCommand("" + r + ", " + c);
				b.addActionListener(
						new ActionListener(){  
							public void actionPerformed(ActionEvent e){  
								JButton btn = (JButton) e.getSource();
								btn.setText("" + cycleValue(btn.getText().charAt(0)));
								resetBoardString();
								lblHashCode.setText("" + myHashCode());
							}  
						});              
				panel.add(b);
				buttons[r][c] = b;           
			}

		return panel;
	}

	/**
	 * @param ch the current char
	 * @return the next char in the cycle
	 * returns the next character to show in the button cycle
	 */
	private static char cycleValue(char ch) {
		switch (ch) {
		case 'x' : 
			return 'o';
		case 'o' : 
			return ' ';
		default  : 
			return 'x';
		}
	}

	/**
	 * sets up the overall JFrame
	 */
	private void setupFrame() {
		JPanel panel2 = new JPanel();

		// Setup Frame
		super.setSize(250, 200);  
		super.setLocationRelativeTo(null);  
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		super.setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));  

		// Setup Panels
		panel2 = setupPanelTwo();  //panelOne displays a value that requires panelTwo to be ready
		super.add(setupPanelOne());     
		super.add(panel2);  

		super.setVisible(true);  
	}

	/**
	 * @return a random possible tictactoe char
	 * returns a random choice of the three possible tictactoe characters
	 */
	private char randomXO() {
		int rnd = (int) (Math.random()*TicTacToe.CHAR_POSSIBILITIES);
		switch(rnd) {
		case 1 : 
			return 'x';
		case 2 : 
			return 'o';
		default: 
			return ' ';
		}
	}
	abstract int myHashCode();

	/**
	 * @param row the row to be searched
	 * @param col the col to be searched
	 * @return the char at the given location
	 * returns the character at the given spot on the board
	 */
	public char charAt(int row, int col) {
		String value = buttons[row][col].getText();
		if (value.length() > 0)
			return value.charAt(0);
		else
			return '*';
	}

	/**
	 * @param s the new String/board to be displayed
	 * changes the board being displayed to the given String
	 */
	public void show(String s) {
		int pos = 0;
		String letter;
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++){
				char ch = s.charAt(pos);
				switch (ch) {
				case '1' :
				case 'x' :
					letter = "x"; 
				break;
				case '2' : 
				case 'o' :
					letter = "o"; 
				break;
				case '0'  : 
				case ' ' :
					letter = " "; 
				break;
				default : letter = "" + ch;                                                                                                                                                                                                                                                                                        xx: letter = " ";
				}

				buttons[r][c].setText(letter);
				pos++;
			}
	}
	/**
	 * resets the BoardString to what is beig shown on the board
	 */
	public void resetBoardString() {
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++){
				boardString += buttons[r][c].getText();
			}
	}

	/**
	 * @param s new String
	 * sets the BoardString to the given String
	 */
	public void setBoardString(String s) {
		boardString = s;
		show(s);
	}


}