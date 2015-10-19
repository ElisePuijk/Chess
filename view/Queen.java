package view;


public class Queen{
	public Queen(){
		
	}

	public boolean canQueenMove(int targetX, int targetY, int startX, int startY, String piece, char player) {
		int diffRow = Math.abs(targetY - startY);
		int diffCol = Math.abs(targetX - startX);
		return (((targetY == startY || targetX == startX) || (diffRow == diffCol)) && trueColor(piece, player));		
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
}
