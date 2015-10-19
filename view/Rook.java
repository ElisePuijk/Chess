package view;

public class Rook {
	public Rook() {

	}

	public boolean canRookMove(int targetX, int targetY, int startX, int startY, String piece, char player) {
		if ((targetX == startX || targetY == startY)){
			if (trueColor(piece, player)){
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
	public boolean notBlocked(int targetX, int targetY, int startX, int startY, String[][] piecePlaces,
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
				if (piecePlaces[x][y] != null) {
					//System.out.println("1 " + chessPieceName.substring(0, 5));
					//System.out.println(piecePlaces[targetY][targetX]);
					// chessPiece.getLocation(startX+i*targetX,startY+i*targetY)
					// != null) {
					if (piecePlaces[targetY][targetX] == null) {
						//System.out.println("if boven else if " + piecePlaces[targetY][targetX] == null);
						return true;
					} else if (chessPieceName.substring(0, 5).equals(piecePlaces[targetX][targetY].substring(0, 5))) {
						//System.out.println("chesspiecename substring: " + chessPieceName.substring(0, 5));
						//System.out.println("pieceplaces substring: " + chessPieceName.substring(0, 5) );
						//System.out.println(chessPieceName.substring(0, 5).equals(piecePlaces[targetX][targetY].substring(0, 5)));
						return false;
						
					}
				}
				return false;
			}
		}
		return true;
	}

}