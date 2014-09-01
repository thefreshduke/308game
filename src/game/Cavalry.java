package game;

public class Cavalry extends Piece {
	
	public static int faction;
	public static int rank = 3;
	
	// overrides calculateLegalMoves in Piece.java
    public void calculateLegalMoves() {
        if (active) {
        	
        }
    }
    
    public static void main(String[] args) {
        Cavalry c = new Cavalry();
    }
}
