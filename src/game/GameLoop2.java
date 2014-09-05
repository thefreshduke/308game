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

class GameLoop2 {
	private Group root;
	private Group ammoGroup = new Group();
	private Group enemyGroup = new Group();
	private Circle balla, baddy;
	private Player player;
	private Enemy enemy;
	private boolean movePlayerUp, movePlayerDown, movePlayerLeft, movePlayerRight;
	private boolean shiftReleased, gameOver;
	private Scene scene;
	private Stage stage;
	private Random r;
	private double numFrame;
	private int score, enemyCount;
	private Button backButton = new Button();
	private double playerSpeed;
	private double enemySpeed;
	private boolean zigzag;

	public Scene init (Stage s, int width, int height, Button back) {
		root = new Group();
		scene = new Scene(root, width, height, Color.BEIGE);
		setStage(s);
		setShiftReleased(true);
		gameOver = false;
		score = 0;
		setEnemyCount(0);
		playerSpeed = 5;
		enemySpeed = 20;

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

	private void updateSprites () {
		setNumFrame(getNumFrame() + 1);
		keyPressed(scene);
		keyReleased(scene);
		updatePlayer();
		if (!gameOver) {
			spawnEnemy();
		}
		moveEnemy();
		checkCollision();
		checkZigZag();
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
					playerSpeed = 10;
					enemySpeed = 5;
					return;
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
				default:
					return;
				}
			}
		});
	}

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

	private void spawnEnemy() {
		if (enemyGroup.getChildren().size() < 1) {
			setEnemyCount(getEnemyCount() + 1);
			r = new Random();
			enemy = new Enemy();
			baddy = enemy.myEnemy(scene.getWidth() * 1.1, (double) r.nextInt((int) scene.getHeight()), r.nextInt(15) + 15);
			baddy.setFill(Color.GREEN);
			root.getChildren().add(baddy);
			enemyGroup.getChildren().add(baddy);
		}
	}

	private void moveEnemy () {
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

	private boolean checkCollision() {
		for (Node e : enemyGroup.getChildren()) {
			Circle eCircle = (Circle) e;
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

	private boolean intersect(Circle e, Circle b) {
		Path intersection = (Path) Shape.intersect(e, b);
		if (!intersection.getElements().isEmpty()) {
			return true;
		}
		return false;
	}

	private void checkZigZag () {
		if (!isZigzag()) {
			if (balla.getCenterY() < 50) {
				System.out.println(score);
				setZigzag(true);
				score++;
			}
		}
		else {
			if (balla.getCenterY() > scene.getHeight() - 50) {
				System.out.println(score);
				setZigzag(false);
				score++;
			}
		}
		if (score >= 10) {
			gameOver = true;
			System.out.println("YOU WIN!!!");
		}
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public boolean isZigzag() {
		return zigzag;
	}

	public void setZigzag(boolean zigzag) {
		this.zigzag = zigzag;
	}

	public double getNumFrame() {
		return numFrame;
	}

	public void setNumFrame(double numFrame) {
		this.numFrame = numFrame;
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public boolean isShiftReleased() {
		return shiftReleased;
	}

	public void setShiftReleased(boolean shiftReleased) {
		this.shiftReleased = shiftReleased;
	}
}
