// This entire file is part of my masterpiece.
// Scotty Shaw

package game;

import javafx.scene.shape.Circle;

public class Enemy extends Circle {
	public Circle myEnemy(double x, double y, double radius) {
		Circle enemy = new Circle(x, y, radius);
		return enemy;
	}
}
