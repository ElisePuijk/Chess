package view;


public class Pawn {
	public Pawn() {

	}

	public boolean canPawnMove(int targetX, int targetY, int startX, int startY, String piece, char player) {
		int diffRow = Math.abs(targetY - startY);

		if ((targetX == startX) && (diffRow == 1)) {
			if (trueColor(piece, player)){
			return true;
			}
		}
		if (((targetX == startX) && diffRow == 2) && ((startY == 1) || (startY == 6))) {
			if(trueColor(piece,player)){
			return true;
			}
			
		}

		return false;
	}

	public boolean trueColor(String piece, char player){
		if (piece.substring(0, 5).equals("Black") && (player == 'z')) {
			//System.out.println(player);
			return true;
		} else if (piece.substring(0, 5).equals("White") && (player == 'w')){
			//System.out.println(player);
			return true;
		} else 
			return false;
	}
	@SuppressWarnings("unused")
	public boolean notBlocked(int startX, int startY, int targetX, int targetY, String[][] piecePlaces,
			String chessPieceName) {
		// Returns true if the entire path from the origin to the destination is
		// clear.
		// (This excepts knights, which can move over teammates and enemies.)

		// dit moeten we aanroepen in de switch bij elk stuk waar naar moet
		// worden gekeken

		// Determine the direction (if any) of x and y movement
		int dx = (startX < targetX) ? 1 : ((startX == targetX) ? 0 : -1);
		int dy = (startY < targetY) ? 1 : ((startY == targetY) ? 0 : -1);

		// Determine the number of times we must iterate
		int steps = Math.max(Math.abs(startX - targetX), Math.abs(startY - targetY));

		if (startX == targetX || startY == targetY || Math.abs(startX - targetX) == Math.abs(startY - targetY)) {
			for (int i = 1; i < steps; i++) {
				int x = startX + i * dx;
				int y = startY + i * dy;
				if (piecePlaces[x][y] != null){

					// chessPiece.getLocation(startX+i*targetX,startY+i*targetY)
					// != null) {
					if (chessPieceName.substring(0, 4).equals(piecePlaces[targetX][targetY].substring(0, 4))){
						return false;
					}
				// als er een stuk op staat
				return false;
				}
			}
		}
		return true;
	}
}
