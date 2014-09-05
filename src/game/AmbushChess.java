package game;

import java.util.HashMap;
import java.util.HashSet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * This is the main program, it is basically boilerplate to create
 * an animated scene.
 * 
 * @author Robert C. Duvall
 */
public class AmbushChess extends Application
{
	private GameLoop myGame;
	HashMap <Vertex, Piece> verticesAndPiecesMap = new HashMap <Vertex, Piece>();

	/**
	 * Set things up at the beginning.
	 */
	@Override
	public void start (Stage s)
	{
		s.setTitle("Ambush Chess!");
		Button twoPlayerGameButton = new Button("2-Player Game");
		Button threePlayerGameButton = new Button("3-Player Game");
		
		twoPlayerGameButton.setOnAction(new NewGameHandler(s));
		
		// create your own game here
		myGame = new GameLoop();
		// attach game to the stage and display it
		Scene scene = myGame.init(s, 1000, 700);
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            myGame.selectPiece(event);
	        }
	    });
		s.setScene(scene);
		s.show();
		
		// sets the game's loop 
		KeyFrame frame = myGame.start();
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	//button stuff?
//	Button button = new Button("click here");
//    root.getChildren().add(button);
	private class NewGameHandler implements EventHandler<ActionEvent> {
		Stage current;
		public NewGameHandler(Stage s) {
			current = s;
			myGame = new GameLoop();
		}
		
		@Override
		public void handle(ActionEvent event) {
			Scene test = myGame.init(current, 1000, 700);
			current.setScene(test);
			current.show();
		}
	}
	
	/**
	 * Start the program.
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
