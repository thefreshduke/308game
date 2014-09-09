// This entire file is part of my masterpiece.
// Scotty Shaw

package game;

import javafx.scene.shape.Circle;

public class Ammo extends Circle {
	public Circle myAmmo(double x, double y, double radius) {
		Circle ammo = new Circle(x, y, radius);
		return ammo;
	}
}
