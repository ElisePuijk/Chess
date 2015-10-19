package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class View extends JFrame implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	JFrame f = new JFrame("Chess");
	private Image[][] chessPieceImages = new Image[2][6];
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xCoordinate;
	int yCoordinate;
	String pieceType;
	int targetX, targetY, startX, startY, targetColumn, targetRow, startColumn, startRow;
	Knight knight = new Knight();
	Rook rook = new Rook();
	Bishop bishop = new Bishop();
	Pawn pawn = new Pawn();
	Queen queen = new Queen();
	King king = new King();
	
	public static final char PLAYER_W = 'w';
    public static final char PLAYER_Z = 'z';
    private char player = PLAYER_W;
	
	boolean chessPieceBlock;
	boolean canPieceMove;
	boolean colorTest;

	String[][] piecePlaces = new String[8][8];
	String chessPieceName;
	
	int[] startPositionBlackPieces = { 2, 3, 4, 1, 0, 4, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, };
	String[] blackPieces = { "BlackRook", "BlackKnight", "BlackBishop", "BlackQueen", "BlackKing", "BlackBishop",
			"BlackKnight", "BlackRook", "BlackPawn", "BlackPawn", "BlackPawn", "BlackPawn", "BlackPawn",
			"BlackPawn", "BlackPawn", "BlackPawn" };
	int[] startPositionWhitePieces = { 5, 5, 5, 5, 5, 5, 5, 5, 2, 3, 4, 1, 0, 4, 3, 2 };

	String[] whitePieces = { "WhitePawn", "WhitePawn", "WhitePawn", "WhitePawn", "WhitePawn", "WhitePawn",
			"WhitePawn", "WhitePawn", "WhiteRook", "WhiteKnight", "WhiteBishop", "WhiteQueen", "WhiteKing",
			"WhiteBishop", "WhiteKnight", "WhiteRook" };
	int[] k = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63 };
	int number = 8;
	
    public char getPlayer (){
        return player;
    }
 
    private void setNextPlayer(){
        if (player == PLAYER_Z){
            player = PLAYER_W;
            System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
            System.out.println("Wit is aan de beurt");
        } else if (player == PLAYER_W){
            player = PLAYER_Z;
            System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
            System.out.println("Zwart is aan de beurt");
        }
    }
    	
	public View() throws MalformedURLException {
		Dimension boardSize = new Dimension(640, 640);

		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		
		// Create Chessboard
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		// create array
		for (int column = 0; column < piecePlaces.length; column++) {
			for (int row = 0; row < 1; row++) {
				piecePlaces[row][column] = blackPieces[column];
			}
			for (int row = 1; row < 2; row++){
				piecePlaces[row][column] = "BlackPawn";
			}
			for (int row = 6; row < 7; row++){
				piecePlaces[row][column] = "WhitePawn";
			}
			for (int row = 7; row < 8; row++) {
				
				piecePlaces[row][column] = whitePieces[number];
				number++;
				}
				
			}
				
		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground(i % 2 == 0 ? new Color(238, 221, 187) : new Color(204, 136, 68));
			else
				square.setBackground(i % 2 == 0 ? new Color(204, 136, 68) : new Color(238, 221, 187));
		}

		// Pieces into chessPiecesImages
		try {
			URL url = new URL("http://i.stack.imgur.com/memI0.png");
			BufferedImage bi = ImageIO.read(url);
			for (int ii = 0; ii < 2; ii++) {
				for (int jj = 0; jj < 6; jj++) {
					chessPieceImages[ii][jj] = bi.getSubimage(jj * 64, ii * 64, 64, 64);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Black pieces on the board
		for (int j = 0; j < 16; j++) {
			JLabel piece = new JLabel(new ImageIcon(chessPieceImages[1][startPositionBlackPieces[j]]));
			piece.setName(blackPieces[j]);
			JPanel panel = (JPanel) chessBoard.getComponent(j);
			panel.add(piece);
		}
		// White pieces on the board
		for (int j = 0; j < 16; j++) {
			JLabel piece1 = new JLabel(new ImageIcon(chessPieceImages[0][startPositionWhitePieces[j]]));
			piece1.setName(whitePieces[j]);
			JPanel panel1 = (JPanel) chessBoard.getComponent(k[j]);
			panel1.add(piece1);
		}
	}

	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		xCoordinate = parentLocation.x - e.getX();
		yCoordinate = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		startX = parentLocation.x;
		startY = parentLocation.y;
		startColumn = startX/80;
		startRow = startY/80;
		chessPieceName = chessPiece.getName();
		pieceType = chessPiece.getName();

		chessPiece.setLocation(e.getX() + xCoordinate, e.getY() + yCoordinate);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	}

	// move pieces
	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null)
			return;

		chessPiece.setLocation(me.getX() + xCoordinate, me.getY() + yCoordinate);
	}

	// drop a piece when mouse is released
	public void mouseReleased(MouseEvent e) {
		
		if (chessPiece == null)
			return;
		double dtargetX = (double) chessPiece.getLocation().x;
		double dtargetY = (double) chessPiece.getLocation().y;
		targetRow = (int) Math.round(dtargetY/80);
		targetColumn = (int) Math.round(dtargetX/80);

		switch (pieceType) {
		case "WhitePawn":
			canPieceMove = pawn.canPawnMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "WhiteKnight":
			canPieceMove = knight.canKnightMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "WhiteBishop":
			canPieceMove = bishop.canBishopMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "WhiteQueen":
			canPieceMove = queen.canQueenMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "WhiteKing":
			canPieceMove = king.canKingMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "WhiteRook":
			canPieceMove = rook.canRookMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "BlackPawn":
			canPieceMove = pawn.canPawnMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "BlackKnight":
			canPieceMove = knight.canKnightMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "BlackBishop":
			canPieceMove = bishop.canBishopMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "BlackQueen":
			canPieceMove = queen.canQueenMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "BlackKing":
			canPieceMove = king.canKingMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;
		case "BlackRook":
			canPieceMove = rook.canRookMove(targetColumn, targetRow, startColumn, startRow, pieceType, player);
			//System.out.println(canPieceMove);
			break;

		default:
			//System.out.println("Fout");
			break;
		}
		chessPiece.setVisible(false);
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		if (canPieceMove == false) {
			//System.out.println("canPieceMove: "+ canPieceMove);
			c = chessBoard.findComponentAt(startX, startY);
		}
//		if (chessPieceBlock == false){
//			System.out.println("Chesspieceblok: " + chessPieceBlock);
//			c = chessBoard.findComponentAt(startX, startY);
//		}

		if (c instanceof JLabel) {
			Container parent = c.getParent();
			parent.remove(0);
			parent.add(chessPiece);
		} else {
			Container parent = (Container) c;
			parent.add(chessPiece);
		}
		
		
if(canPieceMove == true){
	//&& chessPieceBlock == true
	
	piecePlaces[targetRow][targetColumn] = piecePlaces[startRow][startColumn];
	piecePlaces[startRow][startColumn] = null;
	setNextPlayer();
	}
		chessPiece.setVisible(true);
		
		// print the String[][]
//		for (int row = 0; row < piecePlaces.length; row++) {
//			for (int column = 0; column < piecePlaces[row].length; column++) {
//			//System.out.print(piecePlaces[row][column] + " ");
//			}
//			//System.out.println();
//			}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) throws MalformedURLException {
		JFrame frame = new View();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}



}