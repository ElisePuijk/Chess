package view;

import java.net.MalformedURLException;

public class Knight {

	public Knight(){
		
	}


	public boolean canKnightMove(int targetX, int targetY, int startX, int startY, String piece, char player) {

		int diffX = Math.abs(targetX - startX);
		int diffY = Math.abs(targetY - startY);

		if ((diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2)){
			if(trueColor(piece, player) == true){
				
		
			return true;}
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
