package view;


public class King{

	public King(){
		
	}

	public boolean canKingMove(int targetX, int targetY, int startX, int startY, String piece, char player) {
		int diffRow = Math.abs(targetY - startY);
		int diffCol = Math.abs(targetX - startX);

		if ((diffRow == 1 && diffCol == 1) || (diffRow ==1 || diffCol == 1)){
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
}
