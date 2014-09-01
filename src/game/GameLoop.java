package game;

import java.util.HashMap;

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
	private Vertex[][] myBoard; //length 9, height 5
	private Circle myBall;
	private ImageView imageView;
	
	HashMap <Vertex, Piece> verticesAndPiecesMap = new HashMap <Vertex, Piece>();
	
	private Vertex vertex00;
	private Rectangle vertex10;
	private Rectangle vertex20;
	private Rectangle vertex30;
	private Rectangle vertex40;
	private Rectangle vertex50;
	private Rectangle vertex60;
	private Rectangle vertex70;
	private Rectangle vertex80;
	private Rectangle vertex01;
	private Rectangle vertex11;
	private Rectangle vertex21;
	private Rectangle vertex31;
	private Rectangle vertex41;
	private Rectangle vertex51;
	private Rectangle vertex61;
	private Rectangle vertex71;
	private Rectangle vertex81;
	private Rectangle vertex02;
	private Rectangle vertex12;
	private Rectangle vertex22;
	private Rectangle vertex32;
	private Rectangle vertex42;
	private Rectangle vertex52;
	private Rectangle vertex62;
	private Rectangle vertex72;
	private Rectangle vertex82;
	private Rectangle vertex03;
	private Rectangle vertex13;
	private Rectangle vertex23;
	private Rectangle vertex33;
	private Rectangle vertex43;
	private Rectangle vertex53;
	private Rectangle vertex63;
	private Rectangle vertex73;
	private Rectangle vertex83;
	private Rectangle vertex04;
	private Rectangle vertex14;
	private Rectangle vertex24;
	private Rectangle vertex34;
	private Rectangle vertex44;
	private Rectangle vertex54;
	private Rectangle vertex64;
	private Rectangle vertex74;
	private Rectangle vertex84;

	/**
	 *  Function to do each game frame
	 */
    private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			updateSprites();
		}
	};
	
//	private EventHandler<MouseEvent> oneFrame = new EventHandler<MouseEvent>() {
//		@Override
//		public void handle(MouseEvent evt) {
//			updateSprites();
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
		
		// remember shapes for viewing later
		root.getChildren().add(imageView);
		root.getChildren().add(myBall);

		for (int col = 0; col < 9; col++) {
			if (col != 4) {
				for (int row = 0; row < 5; row++) {
					if (row != 2) {
						Vertex v = new Vertex(80 + (col * 100), 485 - (row * 100), 60, 60, Color.BLACK);
						root.getChildren().add(v.getNode());
					}
				}
			}
		}

		//		vertex00 = new Vertex(80, 485, 60, 60, Color.BLACK);
		//		vertex10 = new Rectangle(180, 485, 60, 60);
//		vertex10.setFill(Color.YELLOW);
//		vertex20 = new Rectangle(280, 485, 60, 60);
//		vertex20.setFill(Color.YELLOW);
//		vertex30 = new Rectangle(380, 485, 60, 60);
//		vertex30.setFill(Color.YELLOW);
//		vertex40 = new Rectangle(480, 485, 60, 60);
//		vertex40.setFill(Color.YELLOW);
//		vertex50 = new Rectangle(580, 485, 60, 60);
//		vertex50.setFill(Color.YELLOW);
//		vertex60 = new Rectangle(680, 485, 60, 60);
//		vertex60.setFill(Color.YELLOW);
//		vertex70 = new Rectangle(780, 485, 60, 60);
//		vertex70.setFill(Color.YELLOW);
//		vertex80 = new Rectangle(880, 485, 60, 60);
//		vertex80.setFill(Color.YELLOW);
//		
//		vertex01 = new Rectangle(80, 385, 60, 60);
//		vertex01.setFill(Color.YELLOW);
//		vertex11 = new Rectangle(180, 385, 60, 60);
//		vertex11.setFill(Color.YELLOW);
//		vertex21 = new Rectangle(280, 385, 60, 60);
//		vertex21.setFill(Color.YELLOW);
//		vertex31 = new Rectangle(380, 385, 60, 60);
//		vertex31.setFill(Color.YELLOW);
//		vertex41 = new Rectangle(480, 385, 60, 60);
//		vertex41.setFill(Color.YELLOW);
//		vertex51 = new Rectangle(580, 385, 60, 60);
//		vertex51.setFill(Color.YELLOW);
//		vertex61 = new Rectangle(680, 385, 60, 60);
//		vertex61.setFill(Color.YELLOW);
//		vertex71 = new Rectangle(780, 385, 60, 60);
//		vertex71.setFill(Color.YELLOW);
//		vertex81 = new Rectangle(880, 385, 60, 60);
//		vertex81.setFill(Color.YELLOW);
//		
//		vertex02 = new Rectangle(80, 285, 60, 60);
//		vertex02.setFill(Color.YELLOW);
//		vertex12 = new Rectangle(180, 285, 60, 60);
//		vertex12.setFill(Color.YELLOW);
//		vertex22 = new Rectangle(280, 285, 60, 60);
//		vertex22.setFill(Color.YELLOW);
//		vertex32 = new Rectangle(380, 285, 60, 60);
//		vertex32.setFill(Color.YELLOW);
//		vertex42 = new Rectangle(480, 285, 60, 60);
//		vertex42.setFill(Color.YELLOW);
//		vertex52 = new Rectangle(580, 285, 60, 60);
//		vertex52.setFill(Color.YELLOW);
//		vertex62 = new Rectangle(680, 285, 60, 60);
//		vertex62.setFill(Color.YELLOW);
//		vertex72 = new Rectangle(780, 285, 60, 60);
//		vertex72.setFill(Color.YELLOW);
//		vertex82 = new Rectangle(880, 285, 60, 60);
//		vertex82.setFill(Color.YELLOW);
//		
//		vertex03 = new Rectangle(80, 185, 60, 60);
//		vertex03.setFill(Color.YELLOW);
//		vertex13 = new Rectangle(180, 185, 60, 60);
//		vertex13.setFill(Color.YELLOW);
//		vertex23 = new Rectangle(280, 185, 60, 60);
//		vertex23.setFill(Color.YELLOW);
//		vertex33 = new Rectangle(380, 185, 60, 60);
//		vertex33.setFill(Color.YELLOW);
//		vertex43 = new Rectangle(480, 185, 60, 60);
//		vertex43.setFill(Color.YELLOW);
//		vertex53 = new Rectangle(580, 185, 60, 60);
//		vertex53.setFill(Color.YELLOW);
//		vertex63 = new Rectangle(680, 185, 60, 60);
//		vertex63.setFill(Color.YELLOW);
//		vertex73 = new Rectangle(780, 185, 60, 60);
//		vertex73.setFill(Color.YELLOW);
//		vertex83 = new Rectangle(880, 185, 60, 60);
//		vertex83.setFill(Color.YELLOW);
//		
//		vertex04 = new Rectangle(80, 85, 60, 60);
//		vertex04.setFill(Color.YELLOW);
//		vertex14 = new Rectangle(180, 85, 60, 60);
//		vertex14.setFill(Color.YELLOW);
//		vertex24 = new Rectangle(280, 85, 60, 60);
//		vertex24.setFill(Color.YELLOW);
//		vertex34 = new Rectangle(380, 85, 60, 60);
//		vertex34.setFill(Color.YELLOW);
//		vertex44 = new Rectangle(480, 85, 60, 60);
//		vertex44.setFill(Color.YELLOW);
//		vertex54 = new Rectangle(580, 85, 60, 60);
//		vertex54.setFill(Color.YELLOW);
//		vertex64 = new Rectangle(680, 85, 60, 60);
//		vertex64.setFill(Color.YELLOW);
//		vertex74 = new Rectangle(780, 85, 60, 60);
//		vertex74.setFill(Color.YELLOW);
//		vertex84 = new Rectangle(880, 85, 60, 60);
//		vertex84.setFill(Color.YELLOW);
		
//		root.getChildren().add(vertex00.getRectangle());
//		root.getChildren().add(vertex10);
//		root.getChildren().add(vertex20);
//		root.getChildren().add(vertex30);
//		root.getChildren().add(vertex40);
//		root.getChildren().add(vertex50);
//		root.getChildren().add(vertex60);
//		root.getChildren().add(vertex70);
//		root.getChildren().add(vertex80);
//		root.getChildren().add(vertex01);
//		root.getChildren().add(vertex11);
//		root.getChildren().add(vertex21);
//		root.getChildren().add(vertex31);
//		root.getChildren().add(vertex41);
//		root.getChildren().add(vertex51);
//		root.getChildren().add(vertex61);
//		root.getChildren().add(vertex71);
//		root.getChildren().add(vertex81);
//		root.getChildren().add(vertex02);
//		root.getChildren().add(vertex12);
//		root.getChildren().add(vertex22);
//		root.getChildren().add(vertex32);
//		root.getChildren().add(vertex42);
//		root.getChildren().add(vertex52);
//		root.getChildren().add(vertex62);
//		root.getChildren().add(vertex72);
//		root.getChildren().add(vertex82);
//		root.getChildren().add(vertex03);
//		root.getChildren().add(vertex13);
//		root.getChildren().add(vertex23);
//		root.getChildren().add(vertex33);
//		root.getChildren().add(vertex43);
//		root.getChildren().add(vertex53);
//		root.getChildren().add(vertex63);
//		root.getChildren().add(vertex73);
//		root.getChildren().add(vertex83);
//		root.getChildren().add(vertex04);
//		root.getChildren().add(vertex14);
//		root.getChildren().add(vertex24);
//		root.getChildren().add(vertex34);
//		root.getChildren().add(vertex44);
//		root.getChildren().add(vertex54);
//		root.getChildren().add(vertex64);
//		root.getChildren().add(vertex74);
//		root.getChildren().add(vertex84);
		return scene;
	}
	
	private void populateVerticesAndPiecesMap () {
//		for (int y = 0; y < 5; y++) {
//			String s = "vertex" + y.toString;
//			for (int x = 0; x < 9; x++) {
//				s + x.toString;
//				//how to create individual object vertices?
//				//remember that some vertices are empty
//				map.put(vertex, Piece); //how to create individual pieces to put into the map?
//				//relate click to vertex to retrieve piece, if it exists
//			}
//		}
	}

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
