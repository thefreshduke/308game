package game;

import javafx.scene.shape.Circle;

public class Player extends Circle {
	public Circle myPlayer(double x, double y, double radius) {
		Circle player = new Circle(x, y, radius);
		return player;
	}
}
