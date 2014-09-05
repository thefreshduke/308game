package game;

import javafx.scene.image.ImageView;

public abstract class Piece {
	
	protected boolean active;
	protected int myFaction;
	protected int myRank;
	private ImageView myImageView;
	
//	public Piece (int faction, int rank, ImageView imageView) {
//		myFaction = faction;
//		myRank = rank;
//		myImageView = imageView;
//	}
	
	public void checkPieceStatus() {
        if (active) {
        	calculateLegalMoves();
        }
        else {
        	activatePiece();
        }
	}

//	public abstract void calculateLegalMoves();
	public void calculateLegalMoves() {
		
	}
	
	public void activatePiece() {
		active = true;
	}

	public int getMyFaction() {
		return myFaction;
	}

	public void setMyFaction(int faction) {
		this.myFaction = faction;
	}

	public int getMyRank() {
		return myRank;
	}

	public void setMyRank(int rank) {
		this.myRank = rank;
	}
}
