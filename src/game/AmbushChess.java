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
	            selectPiece(event);
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
	
	public void selectPiece (MouseEvent e) {
		System.out.println("mouse click detected! " + e.getSource());
        double clickX = e.getSceneX();
        System.out.println(clickX);
        double clickY = e.getSceneY();
        System.out.println(clickY);
        for (Vertex v : verticesAndPiecesMap.keySet()) {
        	double xPos = v.x + 30;
        	double yPos = v.y + 30;
        	double d = Math.sqrt(Math.pow(clickX - xPos, 2.0) + Math.pow(clickY - yPos, 2.0));
        	if (d < 45) {
        		if (verticesAndPiecesMap.get(v) != null) {
//        			if (playersAndFactionsMap.get(gameTurn % 3) == piece.faction) {//number of turns mod 3 is player1/2/3...
//        				//if player1/2/3 is assigned the faction that matches the selected piece's faction
//        				//then pieceSelected is true 
//        				pieceSelected boolean = true;
//        				if (active) {
//        					Piece.calculateLegalMoves(); //or calculateLegalMoves for individual pieces...? Infantry.calculateLegalMoves()
//        					movePiece(e);
//        				}
//        				else {
//        					individualPiece.activatePiece();
//        					endTurn();
//        				}
//        			}
//        			else {
//        				//pick a piece again
//        			}
        		}
        	}
        }
	}

//	public void movePiece (MouseEvent e) {
//		if (pieceSelected) { //create pieceSelected 
//			double clickX = e.getSceneX();
//	        double clickY = e.getSceneY();
//	        for (moves in legalMovesSet) {
//	        	double xPos = v.x + 30;
//	        	double yPos = v.y + 30;
//	        	double d = Math.sqrt(Math.pow(clickX - xPos, 2.0) + Math.pow(clickY - yPos, 2.0));
//	        	if (d < 45) {
//	        		if (occupied by enemy) {
//	        			remove enemy - enemy killed
//	        		}
//	        		move piece to new space
//	        		vacate old space
//	        		endTurn();
//	        	}
//	        }
//		}
//	}
//	
//	public void endTurn () {
//		if faction is empty
//			faction lost
//			if both factions empty
//				last faction wins
//				gameOver();
//		gameTurn++; //this determines whose turn it is... where to put it for initializing turns?
//	}
	
	public void gameOver () {
		
	}

	/**
	 * Start the program.
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
