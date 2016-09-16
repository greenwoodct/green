import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
	public static final int EMPTY = 0;
	public static final int COMPUTER = 10;
	public static final int PLAYER = 20;
	public static final int ROWS = 3, COLS = 3; // number of rows and columns
	int[][] board = 
		{{0,0,0},
		 {0,0,0},
		 {0,0,0}};	

	List<int[]> moves = new ArrayList<int[]>();
	//--------------------------------------------------------------------------
	// Print game board
	//--------------------------------------------------------------------------
	public void printBoard() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				printCell(board[row][col]); // print each of the cells
				if (col != COLS - 1) {
					System.out.print("|"); // print vertical partition
				}
			}
			System.out.println();
			if (row != ROWS - 1) {
				System.out.println("-----------"); // print horizontal partition
			}
		}
		System.out.println();
	}

	/** Print a cell with the specified "content" */
	public void printCell(int content) {
		switch (content) {
		case EMPTY:
			System.out.print("   ");
			break;
		case PLAYER:
			System.out.print(" O ");
			break;
		case COMPUTER:
			System.out.print(" X ");
			break;
		}
	}
	
	//--------------------------------------------------------------------------
	// Make a move on the game board
	//--------------------------------------------------------------------------
	public boolean makeMove(int[] move, int player) {
		return makeMove(move[0], move[1], player);
	}
	
	public boolean makeMove(int row, int col, int player) {
		int[] move = new int[2];
		move[0] = row;
		move[1] = col;
		moves.add(move);
		board[row][col] = player;
		return hasWon(player, row, col);
	}
	public static int[] nextRandomSpot(int[][] boardIn, int selector) {
		int count = openSpotCount(boardIn, selector);
		
		if (count > 0) {
			Random generator = new Random(count);
			int index = Math.abs(generator.nextInt())%count;
			int[] spot = new int[2];
			for (int row = 0; row < ROWS; ++row) {
				for (int col = 0; col < COLS; ++col) {
					if (boardIn[row][col] < selector) {						
						if (index == 0) {
							spot[0] = row;
							spot[1] = col;							
							return spot;
						}
						index--;
					}
				}
			}
		}
		return null;
	}
	public boolean hasWon(int player, int currentRow, int currentCol) {
	   return (board[currentRow][0] == player         // 3-in-the-row
	                && board[currentRow][1] == player
	                && board[currentRow][2] == player
	           || board[0][currentCol] == player      // 3-in-the-column
	                && board[1][currentCol] == player
	                && board[2][currentCol] == player
	           || currentRow == currentCol            // 3-in-the-diagonal
	                && board[0][0] == player
	                && board[1][1] == player
	                && board[2][2] == player
	           || currentRow + currentCol == 2  // 3-in-the-opposite-diagonal
	                && board[0][2] == player
	                && board[1][1] == player
	                && board[2][0] == player);
	}
	
	public boolean isOpen(int row, int col) {
		return row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == 0;
	}
	
	public static int openSpotCount(int[][] boardIn, int selector) {
		int count = 0;
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				if (boardIn[row][col] < selector) {
					count++;
				}
			}
		}
		return count;
	}
	public int openSpotCount() {
		return openSpotCount(board, COMPUTER);
	}

	public static boolean isDraw(int[][] boardIn, int selector) {		
		return openSpotCount(boardIn, selector) == 0;
	}
	public int[][] cloneBoard() {
		int[][] newBoard = new int[ROWS][COLS];
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				newBoard[row][col] = board[row][col];
			}
		}
		return newBoard;
	}
	
	//--------------------------------------------------------------------------
	// Move history in string. i.e.: (1,2)-(2,2)-(3,1)-(1,1)-(1,3)
	//--------------------------------------------------------------------------
	public String movePath(int[] nextMove) {
		String path = movePath();
		if (path.length() > 0) {
			path += ("-" +  MovePath.moveToString(nextMove));
		}
		else {
			path = MovePath.moveToString(nextMove);
			
		}
		return path;
	}
	public String movePath() {
		return MovePath.movePath(moves);	
	}
	public String prevMovePath() {
		return prevMovePath(0);			
	}
	
	public String prevMovePath(int inc) {
		List<int[]> prevMoves = new ArrayList<int[]>();
		prevMoves.addAll(moves);
		if (moves.size() > 1) {
			prevMoves.remove(moves.size() -1);
		}
		return MovePath.movePath(prevMoves, inc);			
	}
}
