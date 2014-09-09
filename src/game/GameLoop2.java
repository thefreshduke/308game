// This entire file is part of my masterpiece.
// Scotty Shaw

package game;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

class GameLoop2 extends GameLoop1 {
	protected int enemyCount;
	protected boolean zigzag;

	public Scene init (Stage s, int width, int height, Button back) {
		enemySpeed = 20;
		return super.init(s, width, height, back);
	}

	protected void updateSprites () {
		super.updateSprites();
		checkZigZag();
	}

	@Override
	public void keyPressed (Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					movePlayerUp = true;
					return;
				case DOWN:
					movePlayerDown = true;
					return;
				case LEFT:
					movePlayerLeft = true;
					return;
				case RIGHT:
					movePlayerRight = true;
					return;
				case SHIFT:
					playerSpeed = 10;
					enemySpeed = 5;
					return;
				default:
					return;
				}
			}
		});
	}

	@Override
	public void keyReleased (Scene scene) {
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					movePlayerUp = false;
					return;
				case DOWN:
					movePlayerDown = false;
					return;
				case LEFT:
					movePlayerLeft = false;
					return;
				case RIGHT:
					movePlayerRight = false;
					return;
				default:
					return;
				}
			}
		});
	}

	@Override
	protected void updatePlayer () {
		if (movePlayerUp) {
			if (balla.getCenterY() >= balla.getRadius()) {
				balla.setCenterY(balla.getCenterY() - playerSpeed);
			}
		}

		if (movePlayerDown) {
			if (balla.getCenterY() < scene.getHeight() - balla.getRadius()) {
				balla.setCenterY(balla.getCenterY() + playerSpeed);
			}
		}

		if (movePlayerRight) {
			if (balla.getCenterX() < scene.getWidth() - balla.getRadius()) {
				balla.setCenterX(balla.getCenterX() + playerSpeed);
			}
		}

		if (movePlayerLeft) {
			if (balla.getCenterX() > balla.getRadius()) {
				balla.setCenterX(balla.getCenterX() - playerSpeed);
			}
		}
	}

	@Override
	protected void spawnEnemy() {
		if (enemyGroup.getChildren().size() < 1) {
			enemyCount++;
			r = new Random();
			enemy = new Enemy();
			baddy = enemy.myEnemy(scene.getWidth() * 1.1, (double) r.nextInt((int) scene.getHeight()), r.nextInt(15) + 15);
			baddy.setFill(Color.GREEN);
			root.getChildren().add(baddy);
			enemyGroup.getChildren().add(baddy);
		}
	}

	//check to see if player has touched the top or bottom, depending on which one was touched last
	protected void checkZigZag () {
		if (!zigzag) {
			if (balla.getCenterY() < 50) {
				System.out.println(score);
				zigzag = true;
				score++;
			}
		}
		else {
			if (balla.getCenterY() > scene.getHeight() - 50) {
				System.out.println(score);
				zigzag = false;
				score++;
			}
		}
		if (score >= 10) {
			gameOver = true;
			System.out.println("YOU WIN!!!");
		}
	}
}