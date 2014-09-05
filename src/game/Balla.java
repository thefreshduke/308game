package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Balla extends Application {
	private GameLoop1 myGame1;
	private GameLoop2 myGame2;
	private Group root;
	private Button button1 = new Button();
	private Button button2 = new Button();
	private Scene startScene;
	private Stage s = new Stage();
	private static int height = 500;
	private static int width = 800;

	@Override
	public void start (Stage s) {

		s.setTitle("BALLA");
		myGame1 = new GameLoop1();
		myGame2 = new GameLoop2();

		button1.setText("Play Game 1");
		button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				goToGame1();
			}
		});

		button1.setLayoutX(width / 2);
		button1.setLayoutY(height / 2);

		button2.setText("Play Game 2");
		button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				goToGame2();
			}
		});

		button2.setLayoutX(width / 2);
		button2.setLayoutY(2 * height / 3);
		showSplashScreen();
	}

	private void showSplashScreen () {
		root = new Group();
		Text rules1 = new Text(50, 50, "Win Game 1 by killing 10 enemies.");
		Text cheats1 = new Text(50, 75, "Game 1 cheat: Push 'M' for rapid fire.");
		Text rules2 = new Text(50, 125, "Win Game 2 by crossing up and down 10 times.");
		Text cheats2 = new Text(50, 150, "Game 2 cheat: Push 'SHIFT' for faster speed.");
		Text rules3 = new Text(50, 200, "Don't let the green enemies hit you!");
		Text credits = new Text(50, 300, "Created by THE FRESH DUKE SCOTTY SHAW");
		root.getChildren().addAll(button1, button2, rules1, cheats1, rules2, cheats2, rules3, credits);
		startScene = new Scene(root, width, height, Color.ANTIQUEWHITE);
		s.setScene(startScene);
		s.show();
	}

	private void goToGame1() {
		Button back = new Button();
		back.setText("Back to main menu");
		root.getChildren().add(back);
		Scene scene = myGame1.init(s, width, height, back);
		s.setScene(scene);
		s.show();

		back.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				showSplashScreen();
			}
		});

		KeyFrame oneFrame = myGame1.start();
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(oneFrame);
		animation.play();
	}

	protected void goToGame2() {
		root = new Group();
		Button back = new Button();
		back.setText("Back to main menu");
		root.getChildren().add(back);
		Scene scene = myGame2.init(s, width, height, back);
		s.setScene(scene);
		s.show();

		back.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				showSplashScreen();
			}
		});

		KeyFrame oneFrame = myGame2.start();
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(oneFrame);
		animation.play();
	}

	public static void main (String[] args) {
		launch(args);
	}
}
