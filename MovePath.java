import java.util.List;

public class MovePath {
	public static String movePath(List<int[]> prevMoves) {
		return movePath(prevMoves, 0);
	}
	
	public static String movePath(List<int[]> prevMoves, int inc) {
		String path = "";
		if (prevMoves.size() > 0) {
			for (int[] move : prevMoves) {
				String moveString =  moveToString(TurnBoard.moveTurn(move, inc));
				if (path.length() == 0) {
					path = moveString;
				}
				else {
					path += ("-" + moveString);
				}
			}
		}
		return path;
	}
	public static String  moveToString(int[] move) {
		return  "(" + (move[0]+1) + "," + (move[1]+1) + ")";
	}
}
