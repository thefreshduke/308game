package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class General extends Piece {
	
	protected boolean active;
	private int myFaction;
	private int myRank;
	private ImageView myImageView;
	
	public General (int faction, int rank, ImageView imageView) {
		myFaction = faction;
		myRank = rank;
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
    	Image image = new Image(getClass().getResourceAsStream("images/general.jpg"));
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
//        Infantry i = new Infantry();
//    }
}
