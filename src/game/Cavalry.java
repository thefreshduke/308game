package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cavalry extends Piece {
	
	protected boolean isActive;
	private int myFaction;
	private int myRank;
	private ImageView myImageView;
	
	public Cavalry (int faction, int rank, ImageView imageView) {
//		isActive = active;
		setMyFaction(faction);
		setMyRank(rank);
		myImageView = imageView;
	}
	
	public void checkPieceStatus() {
		super.checkPieceStatus();
	}
	
//    public void calculateLegalMoves() {
//        int[] deltaX = {1, -1, 0, 0};
//        int[] deltaY = {0, 0, -1, 1};
//        Vertex.x = 0;
//    }
    
    public void activatePiece() {
    	super.activatePiece();
    	Image image = new Image(getClass().getResourceAsStream("images/cavalry.jpg"));
        myImageView = new ImageView();
        myImageView.setImage(image);
    }

	public int getMyFaction() {
		return myFaction;
	}

	public void setMyFaction(int myFaction) {
		this.myFaction = myFaction;
	}

	public int getMyRank() {
		return myRank;
	}

	public void setMyRank(int myRank) {
		this.myRank = myRank;
	}
    
//    public static void main(String[] args) {
//        Cavalry c = new Cavalry();
//    }
}
