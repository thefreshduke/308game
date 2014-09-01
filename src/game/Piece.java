package game;

import javafx.scene.image.ImageView;

public abstract class Piece {
	
	protected boolean active;
	protected int faction;
	protected int rank;
	private ImageView imageView;
	
	public void checkPieceStatus() {
        if (active) {
        	calculateLegalMoves();
        }
        else {
        	activatePiece();
        }
	}

//	public abstract void calculateLegalMoves();
	public static void calculateLegalMoves() {
		
	}
	
	public void activatePiece() {
		active = true;
	}

	public int getFaction() {
		return faction;
	}

	public void setFaction(int faction) {
		this.faction = faction;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
