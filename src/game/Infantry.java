package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Infantry extends Piece {
	
	public static int faction;
	public static int rank = 1;
	private ImageView imageView;
	
	public void checkPieceStatus() {
		super.checkPieceStatus();
	}
	
    public void calculateLegalMoves() {
        int[] deltaX = {1, -1, 0, 0};
        int[] deltaY = {0, 0, -1, 1};
        Vertex.x = 0;
    }
    
    public void activatePiece() {
    	super.activatePiece();
    	Image image = new Image(getClass().getResourceAsStream("images/infantry.jpg"));
        imageView = new ImageView();
        imageView.setImage(image);
    }
    
    public static void main(String[] args) {
        Infantry i = new Infantry();
    }
}
