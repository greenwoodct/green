import java.util.Scanner;

public class TicTacToe {
	public static final int PLAYING = 0;
	public static final int DRAW = 1;
	public static final int COMPUTER_WON = 2;
	public static final int PLAYER_WON = 3;
	
	public static final int COMPUTER = GameBoard.COMPUTER;
	public static final int PLAYER = GameBoard.PLAYER;
	
	public static int turn = COMPUTER;
	
	public static Scanner in = new Scanner(System.in); // the input Scanner

	public static void main(String[] args) {
		int status = PLAYING;
		GameBoard gameBoard = new GameBoard();
		do {
			status = move(gameBoard);
			gameBoard.printBoard();

		} while (status == PLAYING);
		// Print results
		if (status == COMPUTER_WON) {
			System.out.println("Computer won! Bye!");
		} else if (status == PLAYER_WON) {
			if (!Strategy.isLostBefore(gameBoard)) {
				Strategy.recordLostGame(gameBoard);
			}
			System.out.println("You won! Bye!");
		} else if (status == DRAW) {
			System.out.println("It's a Draw! Bye!");
		}

	}
	
	private static int move(GameBoard gameBoard) {
		if (turn == COMPUTER) {
			turn = PLAYER;
			return computerMove(gameBoard);
		}
		else {
			turn = COMPUTER;
			return playerMove(gameBoard);
		}
	}

	private static int playerMove(GameBoard gameBoard) {
		 boolean validInput = false;  // for input validation
		 int status = PLAYING;
	      do {
	         System.out.print("Enter your move (row[1-3] column[1-3]): ");
	         int row = in.nextInt() - 1;  // array index starts at 0 instead of 1
	         int col = in.nextInt() - 1;
	         if (gameBoard.isOpen(row, col)) {
	        	 if (gameBoard.makeMove(row, col, PLAYER)) {
	        		 status = PLAYER_WON;
	        	 }
	            validInput = true;
	         } else {
	        	
	            System.out.println("This move at (" + (row + 1) + "," + (col + 1)
	                  + ") is not valid. Try again...");
	         }
	      } while (!validInput);  // repeat until input is valid
	      return status;
	}

	private static int computerMove(GameBoard gameBoard) {
		int[] nextMove = Strategy.nextMove(gameBoard);
		if (nextMove == null) {
			return DRAW;
		}
		if (gameBoard.makeMove(nextMove, COMPUTER)) {
			return COMPUTER_WON;
		}
		else {
			if (gameBoard.openSpotCount() > 0) {
				return PLAYING;
			}
			else {
				return DRAW;
			}
		}
	}

}
