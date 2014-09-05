package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Enemy extends Circle {
	
	private Color myColor;
	private double myEnemySpeed;

	public Enemy (double x, double y, double radius, Color c) {
		myColor = c;
	}

	public Color getMyColor () {
		return myColor;
	}

	public void setMyColor (Color myColor) {
		this.myColor = myColor;
	}
	
	public double getMyEnemySpeed () {
		return myEnemySpeed;
	}
	
	public void setMyEnemySpeed (double myEnemySpeed) {
		this.myEnemySpeed = myEnemySpeed;
	}
}
