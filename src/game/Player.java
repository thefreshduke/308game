package game;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {

	private double myX;
	private double myY;
	private double myRadius;
	private Color myColor;
	private Circle myPlayer;
	
	private double playerSpeed = 2;

	public Player (double x, double y, double radius, Color c) {
		myX = x;
		myY = y;
		myRadius = radius;
		myColor = c;

		myPlayer = new Circle(x, y, radius);
		myPlayer.setFill(c);
	}

	public void moveUp (Scene scene) {
		if (myPlayer.getCenterY() > myPlayer.getRadius()) {
			myPlayer.setCenterY(myPlayer.getCenterY() - playerSpeed);
			myY = myPlayer.getCenterY();
		}
	}

	public void moveDown (Scene scene) {
		if (myPlayer.getCenterY() < scene.getHeight() - myPlayer.getRadius()) {
			myPlayer.setCenterY(myPlayer.getCenterY() + playerSpeed);
			myY = myPlayer.getCenterY();
		}
	}

	public void moveRight (Scene scene) {
		if (myPlayer.getCenterX() < scene.getWidth() - myPlayer.getRadius()) {
			myPlayer.setCenterX(myPlayer.getCenterX() + playerSpeed);
			myX = myPlayer.getCenterX();
		}
	}

	public void moveLeft (Scene scene) {
		if (myPlayer.getCenterX() > myPlayer.getRadius()) {
			myPlayer.setCenterX(myPlayer.getCenterX() - playerSpeed);
			myX = myPlayer.getCenterX();
		}
	}

	public void shootUp (Scene scene) {
		
	}

	public void shootDown (Scene scene) {
		
	}
	
	public void shootLeft (Scene scene) {
		
	}

	public void shootRight (Scene scene) {
		
	}

	public Node getNode() {
		return myPlayer;
	}

	public double getCenterX() {
		return myX;
	}

	public void setCenterX (double myX) {
		this.myX = myX;
	}

	public double getCenterY() {
		return myY;
	}

	public void setCenterY (double myY) {
		this.myY = myY;
	}

	public double getMyRadius() {
		return myRadius;
	}

	public void setMyRadius (double myRadius) {
		this.myRadius = myRadius;
	}

	public Color getMyColor() {
		return myColor;
	}

	public void setMyColor (Color myColor) {
		this.myColor = myColor;
	}
}
