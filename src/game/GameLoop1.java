// This entire file is part of my masterpiece.
// Scotty Shaw

package game;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

class GameLoop1 {
	protected Group root;
	protected Group ammoGroup = new Group();
	protected Group enemyGroup = new Group();
	protected Circle balla, weapon, baddy;
	protected Player player;
	protected Ammo ammo;
	protected Enemy enemy;
	protected boolean movePlayerUp, movePlayerDown, movePlayerLeft, movePlayerRight;
	protected boolean shoot, shiftReleased, machineGunCheat, gameOver;
	protected Scene scene;
	protected Stage stage;
	protected Random r;
	protected double numFrame;
	protected int score;
	protected Button backButton = new Button();
	protected double playerSpeed;
	protected double enemySpeed = 4;
	protected double ammoSpeed;

	public Scene init (Stage s, int width, int height, Button back) {
		playerSpeed = 5;
		ammoSpeed = 15;
		root = new Group();
		scene = new Scene(root, width, height, Color.BEIGE);
		setStage(s);
		shiftReleased = true;

		player = new Player();
		balla = player.myPlayer(scene.getWidth() / 4, 3 * scene.getHeight() / 4, 20);
		balla.setFill(Color.BLACK);
		backButton = back;

		root.getChildren().addAll(balla, backButton, ammoGroup, enemyGroup);

		return scene;
	}

	public KeyFrame start () {
		return new KeyFrame(Duration.millis(1000/60), oneFrame);
	}

	public EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			updateSprites();
		}
	};

	protected void updateSprites () {
		numFrame++;
		keyPressed(scene);
		keyReleased(scene);
		updatePlayer();
		if (!gameOver) {
			attack();
			spawnEnemy();
		}
		moveAmmo();
		moveEnemy();
		checkCollision();
	}

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
					if (shiftReleased) {
						shoot = true;
						if (!machineGunCheat) {
							shiftReleased = false;
						}
					}
					return;
				case M:
					machineGunCheat = true;
				default:
					return;
				}
			}
		});
	}

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
				case SHIFT:
					shoot = false;
					shiftReleased = true;
					return;
				default:
					return;
				}
			}
		});
	}

	protected void updatePlayer () {
		if (movePlayerUp) {
			if (balla.getCenterY() > balla.getRadius()) {
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

		if (shoot) {
			Circle ammo = new Circle(balla.getCenterX(), balla.getCenterY(), 5, Color.RED);
			ammoGroup.getChildren().add(ammo);
			ammo.setCenterX(balla.getCenterX());
			ammo.setCenterY(balla.getCenterY());
			if (!machineGunCheat) {
				shoot = false;
			}
		}
	}

	protected void attack () {
		if (shoot) {
			ammo = new Ammo();
			weapon = ammo.myAmmo(balla.getCenterX(), balla.getCenterY(), 5);
			weapon.setFill(Color.RED);
			root.getChildren().add(weapon);
			ammoGroup.getChildren().add(weapon);
			if (!machineGunCheat) {
				shoot = false;
			}
		}
	}

	protected void moveAmmo () {
		for (Node a : ammoGroup.getChildren()) {
			Circle aCircle = (Circle) a;
			if (aCircle.getCenterX() < scene.getWidth() + aCircle.getRadius()) {
				aCircle.setCenterX(aCircle.getCenterX() + ammoSpeed);
			}
			else {
				ammoGroup.getChildren().remove(a);
				root.getChildren().remove(a);
				return;
			}
		}
	}

	protected void spawnEnemy() {
		if (numFrame % 60 == 20) {
			r = new Random();
			enemy = new Enemy();
			baddy = enemy.myEnemy(scene.getWidth() * 1.1, (double) r.nextInt((int) scene.getHeight()), r.nextInt(15) + 15);
			baddy.setFill(Color.GREEN);
			root.getChildren().add(baddy);
			enemyGroup.getChildren().add(baddy);
		}
	}

	protected void moveEnemy () {
		for (Node e : enemyGroup.getChildren()) {
			Circle eCircle = (Circle) e;
			if (eCircle.getCenterX() > -eCircle.getRadius()) {
				eCircle.setCenterX(eCircle.getCenterX() - enemySpeed);
			}
			else {
				enemyGroup.getChildren().remove(e);
				root.getChildren().remove(e);
				return;
			}
		}
	}

	protected boolean checkCollision() {
		for (Node e : enemyGroup.getChildren()) {
			Circle eCircle = (Circle) e;
			for (Node a : ammoGroup.getChildren()) {
				Circle aCircle = (Circle) a;
				if (intersect(eCircle, aCircle)) {
					ammoGroup.getChildren().remove(aCircle);
					root.getChildren().remove(aCircle);
					enemyGroup.getChildren().remove(eCircle);
					root.getChildren().remove(eCircle);
					score++;
					if (score >= 10) {
						System.out.println("YOU WIN!!!");
						gameOver = true;
					}
					return true;
				}
			}
			if (intersect(eCircle, balla)) {
				root.getChildren().remove(balla);
				enemyGroup.getChildren().remove(eCircle);
				root.getChildren().remove(eCircle);
				System.out.println("YOU LOSE...");
				gameOver = true;
				return true;
			}
		}
		return false;
	}

	protected boolean intersect(Circle e, Circle a) {
		Path intersection = (Path) Shape.intersect(e, a);
		if (!intersection.getElements().isEmpty()) {
			return true;
		}
		return false;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}