package game;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Vertex {
	
	private int myX;
	private int myY;
	private int myHeight;
	private int myWidth;
	private Color myColor;
	private Rectangle myVertex;
//	private ImageView myImageView;
	
	public Vertex (int x, int y, int height, int width, Color c) {
		setMyX(x);
		setMyY(y);
		myHeight = height;
		myWidth = width;
		myColor = c;
//		myImageView = imageView;
		
		myVertex = new Rectangle(x, y, height, width);
		myVertex.setFill(c);
	}
	
	public Node getNode() {
		return myVertex;
	}

	public int getMyX() {
		return myX;
	}

	public void setMyX(int myX) {
		this.myX = myX;
	}

	public int getMyY() {
		return myY;
	}

	public void setMyY(int myY) {
		this.myY = myY;
	}
}
