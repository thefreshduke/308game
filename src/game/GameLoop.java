package game;

import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

class GameLoop {
	public Group root;
	public Circle myBall;
	public ImageView imageView;
	public Player myPlayer;
	public Ammo myAmmo;
	public List<Ammo> ammoArrayList = new ArrayList<Ammo>();
	public boolean movePlayerUp, movePlayerDown, movePlayerLeft, movePlayerRight;
	public boolean moveAmmo, shoot;
	public boolean spaceReleased;
	public boolean machineGun;
	private Scene scene;

	public Scene init (Stage s, int width, int height) {
		root = new Group();
		scene = new Scene(root, width, height, Color.WHITE);
		spaceReleased = true;
		myPlayer = new Player(scene.getWidth() / 4, 3 * scene.getHeight() / 4, 20, Color.BLACK);
		root.getChildren().add(myPlayer.getNode());
		return scene;
	}
	
	public EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			updateSprites();
		}
	};

	private void updateSprites () {
		keyPressed(scene);
		keyReleased(scene);
		movePlayer();
		attack();
		moveAmmo();
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
				case SPACE:
					if (spaceReleased) {
						shoot = true;
						if (!machineGun) {
							spaceReleased = false;
						}
					}
					return;
				case M:
					machineGun = true;
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
				case SPACE:
					shoot = false;
					spaceReleased = true;
					return;
				default:
					return;
				}
			}
		});
	}

	public KeyFrame start () {
		return new KeyFrame(Duration.millis(1000/60), oneFrame);
	}

	private void movePlayer () {
		if (movePlayerUp) {
			myPlayer.moveUp(scene);
		}
		if (movePlayerDown) {
			myPlayer.moveDown(scene);
		}
		if (movePlayerLeft) {
			myPlayer.moveLeft(scene);
		}
		if (movePlayerRight) {
			myPlayer.moveRight(scene);
		}
	}

	private void attack () {
		if (shoot) {
			myAmmo = new Ammo(myPlayer.getCenterX(), myPlayer.getCenterY(), 5, Color.RED);
			root.getChildren().add(myAmmo.getNode());
			ammoArrayList.add(0, myAmmo);
			if (!machineGun) {
				shoot = false;
			}
		}
	}

	private void moveAmmo () {
		for (Ammo a : ammoArrayList) {
			if (a.getCenterX() < scene.getWidth() + a.getMyRadius()) {
				a.move(scene);
			}
			else {
				ammoArrayList.remove(a);
				root.getChildren().remove(a);
			}
		}
	}
}
