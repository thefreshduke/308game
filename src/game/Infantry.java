package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Infantry extends Piece {
	
	protected boolean isActive;
	private int myFaction;
	private int myRank;
	private ImageView myImageView;
	
	public Infantry (int faction, int rank, ImageView imageView) {
//		isActive = active;
		myFaction = faction;
		myRank = rank;
		myImageView = imageView;
//		myVertex = new Rectangle(x, y, height, width);
//		myVertex.setFill(c);
	}
	
//	public Node getNode() {
//		return myVertex;
//	}
	
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
    	Image image = new Image(getClass().getResourceAsStream("images/infantry.jpg"));
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

//    public int getMyFaction() {
//		return super.getMyFaction();
//	}
//
//    public void setMyFaction(int faction) {
//		super.setMyFaction(myFaction);
//	}
//
//    public int getMyRank() {
//		return super.getMyRank();
//	}
//
//    public void setMyRank(int rank) {
//		super.setMyRank(myRank);
//	}
    
//    public static void main(String[] args) {
//        Infantry i = new Infantry();
//    }
}
