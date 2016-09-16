import java.util.Map;

public class Strategy {
	public static final int GAMBLE = 1;
	public static final int LOST_BEFORE = 2;
	public static final String LOST_HISTORY_FILE = "lost-history.txt";
	public static Map<String, Boolean> lostMoves = DataReader.readAll(LOST_HISTORY_FILE); 
	public static void recordLostGame(GameBoard gameBoard) {
		DataWriter.writeLine(LOST_HISTORY_FILE, gameBoard.prevMovePath(0));
		DataWriter.writeLine(LOST_HISTORY_FILE, gameBoard.prevMovePath(2));
		DataWriter.writeLine(LOST_HISTORY_FILE, gameBoard.prevMovePath(4));
		DataWriter.writeLine(LOST_HISTORY_FILE, gameBoard.prevMovePath(6));
	}
	public static int[] nextMove(GameBoard gameBoard) {
		int[][] board = gameBoard.cloneBoard();
		int[] nextMove = null;
		boolean isLostBefore = false;
		
		do {
			nextMove = GameBoard.nextRandomSpot(board, GAMBLE);
			if (nextMove == null) {
				break;
			}
			board[nextMove[0]][nextMove[1]] = GAMBLE;
			isLostBefore = isLostBefore(gameBoard, nextMove);
		}
		while (isLostBefore);
		if (nextMove == null) {
			nextMove =  GameBoard.nextRandomSpot(board, LOST_BEFORE);
		}
		return nextMove;
	}
	
	public static boolean isLostBefore(GameBoard boardGame, int[] nextMove) {
		String movePath =  boardGame.movePath(nextMove);
		return lostMoves.containsKey(movePath);
		
	}
	public static boolean isLostBefore(GameBoard boardGame) {
		String movePath =  boardGame.movePath();
		return lostMoves.containsKey(movePath);
		
	}

}
