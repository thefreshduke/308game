package game;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ammo {
	
	private double myX;
	private double myY;
	private double myRadius;
	private Color myColor;
	private Circle myAmmo;
	
	private double ammoSpeed = 15;

	public Ammo (double x, double y, double radius, Color c) {
		myX = x;
		myY = y;
		myRadius = radius;
		myColor = c;

		myAmmo = new Circle(x, y, radius);
		myAmmo.setFill(c);
	}

	public void move (Scene scene) {
		if (myAmmo.getCenterX() < scene.getWidth() + myAmmo.getRadius()) {
			myAmmo.setCenterX(myAmmo.getCenterX() + ammoSpeed);
		}
		else {
			removeAmmo();
		}
	}
	
	public void removeAmmo () {
		
	}

	public Node getNode() {
		return myAmmo;
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
