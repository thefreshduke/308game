package game;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Vertex {
	
	private int myX;
	private int myY;
	private int myHeight;
	private int myWidth;
	private Color myColor;
	private Rectangle myVertex;
	
	public Vertex (int x, int y, int height, int width, Color c) {
		myX = x;
		myY = y;
		myHeight = height;
		myWidth = width;
		myColor = c;
		
		myVertex = new Rectangle(x, y, height, width);
		myVertex.setFill(c);
	}
	
	public Node getNode() {
		return myVertex;
	}
//		Piece p;
//		boolean occupied;
	
//	method that pulls piece's location to check if the clicked location is occupied
//	public void checkVacancy() {
//		for (Piece p : ) {
//			if (Piece.x == click.x && Piece.y == click.y) {
//				occupied = true;
//			}
//		}
//	}
}
