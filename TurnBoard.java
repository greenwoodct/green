public class TurnBoard {
	// --------------------------------------------------------------------------
	// One losing combination can be converted into 4 if you rotate the grid by
	// 90, 180, and 270 degrees
	// --------------------------------------------------------------------------
	public static int[] moveTurn(int[] move, int inc) {
		if (inc <= 0) {
			return move;
		}
		int index = movetoIndex(move);
		if (index > -1) {
			index += inc;
			index = index % 9;
			return indexToMove(index);
		} else {
			return move;
		}
	}

	public static int movetoIndex(int[] move) {
		int index = -1;
		if (move[0] == 0 && move[1] == 0) {
			index = 0;
		} else if (move[0] == 0 && move[1] == 1) {
			index = 1;
		} else if (move[0] == 0 && move[1] == 2) {
			index = 2;
		} else if (move[0] == 1 && move[1] == 2) {
			index = 3;
		} else if (move[0] == 2 && move[1] == 2) {
			index = 4;
		} else if (move[0] == 2 && move[1] == 1) {
			index = 5;
		} else if (move[0] == 2 && move[1] == 0) {
			index = 6;
		} else if (move[0] == 1 && move[1] == 0) {
			index = 7;
		}

		return index;
	}

	public static int[] indexToMove(int index) {
		int[] move = new int[2];
		if (index == 0) {
			move[0] = 0;
			move[1] = 0;
		} else if (index == 1) {
			move[0] = 0;
			move[1] = 1;
		} else if (index == 2) {
			move[0] = 0;
			move[1] = 2;
		} else if (index == 3) {
			move[0] = 1;
			move[1] = 2;
		} else if (index == 4) {
			move[0] = 2;
			move[1] = 2;
		} else if (index == 5) {
			move[0] = 2;
			move[1] = 1;
		} else if (index == 6) {
			move[0] = 2;
			move[1] = 0;
		} else if (index == 7) {
			move[0] = 1;
			move[1] = 0;
		}

		return move;
	}

}
