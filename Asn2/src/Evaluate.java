// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Oct 18th, 2020

// This class implements some methods needed for the game
public class Evaluate {
	
	// constants and variables
	private final int DICTIONARY_SIZE = 9887;
	private char[][] gameBoard;
	private int rows, cols, tilesWin;
	
	// Constructs the game board using rows and columns, initializes variables
	public Evaluate (int boardRows, int boardColumns, int tilesNeeded, int maxLevels) {
		
		// sets variables to their appropriate values
		gameBoard = new char[boardRows][boardColumns];
		rows = boardRows;
		cols = boardColumns;
		tilesWin = tilesNeeded;
		
		// fills the board with empty spaces
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				gameBoard[i][j] = 'g';
		
	}
	
	// creates the dictionary that will store the data objects used by the game
	public Dictionary createDictionary() {
		return new Dictionary(DICTIONARY_SIZE);
	}
	
	// Turns the contents of the board array into a string and checks if it's in the dictionary
	public Data repeatedConfig(Dictionary dict) {
		
		// variable for the string
		String board = "";
		
		// goes through the array and adds the chars onto the string variable
		for (int i = 0; i < rows; i++) 
			for (int j = 0; j < cols; j++)
				board += gameBoard[i][j];
		
		// returns the data with the string key (or null)
		return dict.get(board);
		
	}
	
	// represents the board as a string then stores this data item in the dictionary
	public void insertConfig(Dictionary dict, int score, int level) {
		
		// variable for the string
		String board = "";
		
		// goes through the array and adds the chars onto the string variable
		for (int i = 0; i < rows; i++) 
			for (int j = 0; j < cols; j++)
				board += gameBoard[i][j];
		
		// puts the data into the dictionary
		dict.put(new Data(board, score, level));
	}
	
	// stores the specified symbol in the board square
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	// returns true if the specified square is empty
	public boolean squareIsEmpty(int row, int col) {
		return gameBoard[row][col] == 'g';
	}
	
	// returns true if the specified square is a computer tile
	public boolean tileOfComputer(int row, int col) {
		return gameBoard[row][col] == 'o';		
	}
	
	// returns true if the specified square is a human tile
	public boolean tileOfHuman(int row, int col) {
		return gameBoard[row][col] == 'b';		
	}
	
	// checks if the specified symbol is located in enough adjacent squares to win
	public boolean wins(char symbol) {
		
		// variable for the amount of adjacent tiles with the specified symbol
		int run = 0;
		
		// checks horizontally
		for (int i = 0; i < rows; i++) {
			run = 0; // resets run for every row
			for (int j = 0; j < cols; j++) {
				
				// if the symbol matches, increases the run, otherwise sets it to 0
				if (gameBoard[i][j] == symbol)
					run ++;
				else
					run = 0;
				
				// if the run reaches the amount needed to win return true
				if (run == tilesWin)
					return true;
			}
		}
		
		// checks vertically
		for (int j = 0; j < cols; j++) {
			run = 0; // resets run for every column
			for (int i = 0; i < rows; i++) {
				
				// if the symbol matches, increases the run, otherwise sets it to 0
				if (gameBoard[i][j] == symbol)
					run ++;
				else
					run = 0;
				
				// if the run reaches the amount needed to win return true
				if (run == tilesWin)
					return true;
			}
		}
		
		// checks diagonally
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				
				// checks diagonally down+right (only needs to check starting from the first row and column)
				if (i == 0 || j == 0) {
					run = 0; // resets the run
					int r = i;
					int c = j;
					
					// goes through the diagonal
					while (r < rows && c < cols) {
						
						// if the symbol matches, increases the run, otherwise sets it to 0
						if (gameBoard[r][c] == symbol)
							run ++;
						else
							run = 0;
						
						// if the run reaches the amount needed to win return true
						if (run == tilesWin)
							return true;
						
						// moves diagonally
						r ++;
						c ++;
					}
				}
				
				// checks diagonally down+left (only need to check the first row and the last column)
				if (i == 0 || j == gameBoard.length - 1) {
					run = 0; // resets the run
					int r = i;
					int c = j;
					
					// goes through the diagonal
					while (r < rows && c >= 0) {
						
						// if the symbol matches, increases the run, otherwise sets it to 0
						if (gameBoard[r][c] == symbol)
							run ++;
						else
							run = 0;
						
						// if the run reaches the amount needed to win return true
						if (run == tilesWin)
							return true;
						
						// moves diagonally
						r ++;
						c --;
					}
				}
				
			}
		}
		
		// if it has not been found returns false
		return false;
		
	}
	
	// returns true if there are no empty positions left in the board
	public boolean isDraw() {

		// goes through the board checking if there are empty space
		for (int i = 0; i < rows; i++) 
			for (int j = 0; j < cols; j++)
				if (squareIsEmpty(i, j))
					return false;
		
		// if no empty spaces were found, returns true
		return true;
		
	}
	
	// returns a value based on the board
	public int evalBoard() {
		
		// checks whether someone won, its a tie or unfinished game
		if (wins('o'))
			return 3;
		else if (wins('b'))
			return 0;
		else if (isDraw())
			return 2;
		else
			return 1;
	}

}
