package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Separate the game code from some of the boilerplate code.
 *  
 * @author Robert C. Duvall
 */
class GameLoop {
	private Circle myBall;
	private ImageView imageView;
	
	Map <Vertex, Piece> verticesAndPiecesMap = new HashMap <Vertex, Piece>();
	List <Piece> allPieces = new ArrayList <Piece>();

	private void pieceCreator () {
		for (int i = 0; i < 10; i++) {
			//			Image hiddenPieceImage = new Image(getClass().getResourceAsStream("images/duke.gif"));
			//			ImageView hiddenPieceImageView = new ImageView();
			//			hiddenPieceImageView.setImage(hiddenPieceImage);
			//			Infantry i = new Infantry(3, 1, hiddenPieceImageView);
			Infantry infantry = new Infantry(3, 0, null);
			allPieces.add(infantry);
		}
		for (int faction = 0; faction < 2; faction++) {
			for (int j = 0; j < 2; j++) {
				Cannon cannon = new Cannon(faction, 1, null);
				allPieces.add(cannon);
				Cavalry cavalry = new Cavalry(faction, 2, null);
				allPieces.add(cavalry);
				Chariot chariot = new Chariot(faction, 3, null);
				allPieces.add(chariot);
				Elephant elephant = new Elephant(faction, 4, null);
				allPieces.add(elephant);
				Guard guard = new Guard(faction, 5, null);
				allPieces.add(guard);
			}
		}
		for (int i = 0; i < 2; i++) {
			General general = new General(3, 6, null);
			allPieces.add(general);
		}
	}

	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			updateSprites();
		}
	};
	
//	private EventHandler<MouseEvent> oneFrame = new EventHandler<MouseEvent>() {
//		@Override
//		public void handle(MouseEvent evt) {
////			updateSprites();
//		}
//	};
	
	/**
	 * Create the game's scene
	 */
	public Scene init (Stage s, int width, int height) {
		// Create a scene graph to organize the scene
		Group root = new Group();
		// Create a place to see the shapes
		Scene scene = new Scene(root, width, height, Color.BROWN);
		// Make some shapes and set their properties
        Image board = new Image(getClass().getResourceAsStream("images/board.png"));
        imageView = new ImageView();
        imageView.setImage(board);
        imageView.setX(100);
        imageView.setY(100);
		myBall = new Circle(scene.getWidth() / 2, scene.getHeight() / 2, 60);
		myBall.setFill(Color.RED);
		
		pieceCreator();
		
		// remember shapes for viewing later
		root.getChildren().add(imageView);
		root.getChildren().add(myBall);
		
		System.out.println(allPieces.size());

		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 5; row++) {
				Vertex v = new Vertex(80 + (col * 100), 485 - (row * 100), 60, 60, Color.YELLOW);
				if (col != 4 && row != 2) {
					root.getChildren().add(v.getNode());
					Random r = new Random();
				    int i = r.nextInt(allPieces.size());
					verticesAndPiecesMap.put(v, allPieces.get(i));
					System.out.println("x: " + (80 + (col * 100)));
					System.out.println("y: " + (485 - (row * 100)));
					System.out.println("rank: " + allPieces.get(i));
					System.out.println();
				    allPieces.remove(i);
				}
				else {
					verticesAndPiecesMap.put(v, null);
				}
			}
		}
		System.out.println(allPieces.size());
		return scene;
	}
	
	//relate click to vertex to retrieve piece, if it exists
	
	/**
	 * Create the game's frame
	 */
	public KeyFrame start () {
		return new KeyFrame(Duration.millis(1000/60), oneFrame);
	}

	/**
	 * Change the sprites properties to animate them
	 * 
	 * Note, there are more sophisticated ways to animate shapes, 
	 * but these simple ways work too.
	 */
	private void updateSprites () {
		myBall.setCenterX(myBall.getCenterX() + 1);
	}
}
