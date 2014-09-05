package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Caesar extends Application {
	private GameLoop myGame;
	public static int height = 500;
	public static int width = 800;
	
	@Override
	public void start (Stage s) {
		s.setTitle("ALL HAIL");
		// create your own game here
		myGame = new GameLoop();
		// attach game to the stage and display it
		Scene scene = myGame.init(s, width, height);
		s.setScene(scene);
		s.show();
		
		// sets the game's loop 
		KeyFrame frame = myGame.start();
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public static void main (String[] args) {
		launch(args);
	}
}
