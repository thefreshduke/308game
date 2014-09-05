package game;

<<<<<<< HEAD
import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
>>>>>>> aed0138cf827d3dd9f5af766a943fceb252f9548

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
<<<<<<< HEAD
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
=======
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
	private int losingFactionCount;
	private int gameTurn;
	private boolean pieceSelected;

	Map <Vertex, Piece> verticesAndPiecesMap = new HashMap <Vertex, Piece>();
	Map <Integer, List <Piece>> factionsAndPiecesMap = new HashMap <Integer, List <Piece>>();
	Map <Integer, Integer> playersAndFactionsMap = new HashMap <Integer, Integer>();
	List <Integer> factionsList = new ArrayList <Integer>();
	List <Piece> allPieces = new ArrayList <Piece>();
	Set <Vertex> legalMovesSet = new HashSet <Vertex>();

	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
>>>>>>> aed0138cf827d3dd9f5af766a943fceb252f9548
		@Override
		public void handle(ActionEvent evt) {
			updateSprites();
		}
	};

<<<<<<< HEAD
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
=======
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
		root.getChildren().add(imageView);

		myBall = new Circle(scene.getWidth() / 2, scene.getHeight() / 2, 60);
		myBall.setFill(Color.RED);
		root.getChildren().add(myBall);

		gameTurn = 0;
//		for (int i = 0; i < numPlayers; i++) {
//		for (int numPlayers = 0; numPlayers < 3; numPlayers++) {
//			factionsSet.add(numPlayers);
//		}

		pieceCreator();
		createVerticesAndPiecesMap(root);

		return scene;
	}

	private void pieceCreator () {
		List <Piece> piecesInEachFaction = new ArrayList <Piece>();
		for (int numFactions = 0; numFactions < 3; numFactions++) {
			factionsAndPiecesMap.put(numFactions, new ArrayList <Piece>());
			factionsList.add(numFactions);
		}
		for (int i = 0; i < 10; i++) {
			//			Image hiddenPieceImage = new Image(getClass().getResourceAsStream("images/duke.gif"));
			//			ImageView hiddenPieceImageView = new ImageView();
			//			hiddenPieceImageView.setImage(hiddenPieceImage);
			//			Infantry i = new Infantry(3, 1, hiddenPieceImageView);
			Piece infantry = new Infantry(2, 0, null); //set isActive boolean or not?
			allPieces.add(infantry);
			piecesInEachFaction = factionsAndPiecesMap.get(2);
			piecesInEachFaction.add(infantry);
			factionsAndPiecesMap.put(2, piecesInEachFaction);
		}
		for (int faction = 0; faction < 2; faction++) {
			for (int j = 0; j < 2; j++) {
				Piece cannon = new Cannon(faction, 1, null);
				allPieces.add(cannon);
				piecesInEachFaction = factionsAndPiecesMap.get(faction);
				piecesInEachFaction.add(cannon);
				factionsAndPiecesMap.put(faction, piecesInEachFaction);
				Piece cavalry = new Cavalry(faction, 2, null);
				allPieces.add(cavalry);
				piecesInEachFaction = factionsAndPiecesMap.get(faction);
				piecesInEachFaction.add(cavalry);
				factionsAndPiecesMap.put(faction, piecesInEachFaction);
				Piece chariot = new Chariot(faction, 3, null);
				allPieces.add(chariot);
				piecesInEachFaction = factionsAndPiecesMap.get(faction);
				piecesInEachFaction.add(chariot);
				factionsAndPiecesMap.put(faction, piecesInEachFaction);
				Piece elephant = new Elephant(faction, 4, null);
				allPieces.add(elephant);
				piecesInEachFaction = factionsAndPiecesMap.get(faction);
				piecesInEachFaction.add(elephant);
				factionsAndPiecesMap.put(faction, piecesInEachFaction);
				Piece guard = new Guard(faction, 5, null);
				allPieces.add(guard);
				piecesInEachFaction = factionsAndPiecesMap.get(faction);
				piecesInEachFaction.add(guard);
				factionsAndPiecesMap.put(faction, piecesInEachFaction);
			}
		}
		for (int i = 0; i < 2; i++) {
			Piece general = new General(2, 6, null);
			allPieces.add(general);
			piecesInEachFaction = factionsAndPiecesMap.get(2);
			piecesInEachFaction.add(general);
			factionsAndPiecesMap.put(2, piecesInEachFaction);
		}
		//		for (int i : factionsAndPiecesMap.keySet()) {
		//			System.out.println(factionsAndPiecesMap.get(i).toString());
		//		}
		//		System.out.println(factionsAndPiecesMap.toString());
		//		for (int i = 0; i < factionsAndPiecesMap.keySet().size(); i++) {
		//			for (int j = 0; j < factionsAndPiecesMap.get(i).size(); j++) {
		//				System.out.println(factionsAndPiecesMap.get(i).get(j).getMyRank());
		//			}
		//		}
	}

	private void createVerticesAndPiecesMap (Group root) {
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 5; row++) {
				Vertex v;
				//if/else to create map
				if (col != 4 && row != 2) {
					v = new Vertex(80 + (col * 100), 485 - (row * 100), 60, 60, Color.BLACK);
					Random r = new Random();
					int i = r.nextInt(allPieces.size());
					Piece p = allPieces.get(i);
					verticesAndPiecesMap.put(v, p);
					//add pieces to root? try to do this first, get images to show up second... more later
//					root.getChildren().add(allPieces.get(i).getNode()); //wat need node for pieces wahhhh?
					allPieces.remove(i);
				}
				else {
					v = new Vertex(80 + (col * 100), 485 - (row * 100), 60, 60, Color.GREEN);
					verticesAndPiecesMap.put(v, null);
				}
				root.getChildren().add(v.getNode());
			}
		}
		//		System.out.println(verticesAndPiecesMap.toString());
	}

	public void selectPiece (MouseEvent e) {
		System.out.println("mouse click detected! " + e.getSource());
		double clickX = e.getSceneX();
		double clickY = e.getSceneY();
		System.out.println("X: " + clickX + ", Y: " + clickY);
		System.out.println();
		for (Vertex v : verticesAndPiecesMap.keySet()) {
			double xPos = v.getMyX() + 30;
			double yPos = v.getMyY() + 30;
			double d = Math.sqrt(Math.pow(clickX - xPos, 2.0) + Math.pow(clickY - yPos, 2.0));
			if (d < 45) {
				System.out.println("xPos: " + xPos + ", yPos: " + yPos);
				System.out.println("distance: " + d);
				System.out.println();
				Piece p = verticesAndPiecesMap.get(v);
				if (p != null) {
					if (!playersAndFactionsMap.containsKey(gameTurn % 3)) {
						System.out.println("ASSWIPE");
						playersAndFactionsMap.put(gameTurn % 3, null);
					}
					System.out.println("game turn: " + gameTurn);
					System.out.println("player's number: " + gameTurn % 3);
					System.out.println("player's faction: " + playersAndFactionsMap.get(gameTurn % 3));
					System.out.println("piece's faction: " + p.getMyFaction());
					if (verticesAndPiecesMap.get(v).active) {
						System.out.println("ACTIVE");
						if (playersAndFactionsMap.get(gameTurn % 3) == p.getMyFaction()) { //currently means null == p.getMyFaction() until players are assigned factions?
							System.out.println("MATCH");
							//if player1/2/3 is assigned the faction that matches the selected piece's faction
							//then pieceSelected is true 
							pieceSelected = true;
							p.calculateLegalMoves(); //or calculateLegalMoves for individual pieces...? Infantry.calculateLegalMoves()?
							if (legalMovesSet.size() != 0) {
								movePiece(e, v, p);
							}
						}
						else {
							//pick a different piece
							//not your piece
							//how to do this?
						}
					}
					else {
						System.out.println("activate piece");
						p.activatePiece(); //images don't show up?
						assignFactionToPlayer(p);
						endTurn();
					}
				}
			}
		}
	}

	private void assignFactionToPlayer(Piece p) {
		if (playersAndFactionsMap.get(gameTurn % 3) == null) {
			if (!playersAndFactionsMap.containsValue(p.getMyFaction())) {
				playersAndFactionsMap.put(gameTurn % 3, p.getMyFaction());
				if (factionsList.contains(p.getMyFaction())) {
					factionsList.remove(p.getMyFaction());
				}
				if (factionsList.size() == 1) {
					for (int i : playersAndFactionsMap.keySet()) {
						if (playersAndFactionsMap.get(i) == null) {
							playersAndFactionsMap.put(i, factionsList.get(0));
							factionsList.remove(p.getMyFaction());
						}
					}
				}
			}
		}
	}

	public void movePiece (MouseEvent e, Vertex originVertex, Piece p) {
		if (pieceSelected) { 
			double clickX = e.getSceneX();
			double clickY = e.getSceneY();
			for (Vertex destinationVertex : legalMovesSet) { //calculate legalMoves
				double xPos = destinationVertex.getMyX() + 30;
				double yPos = destinationVertex.getMyY() + 30;
				double d = Math.sqrt(Math.pow(clickX - xPos, 2.0) + Math.pow(clickY - yPos, 2.0));
				if (d < 45) {
					Piece otherPiece = verticesAndPiecesMap.get(destinationVertex);
					if (otherPiece != null && otherPiece.myFaction != p.myFaction) {
						verticesAndPiecesMap.put(destinationVertex, null); //remove enemy from vertex
						List <Piece> piecesInEachFaction = factionsAndPiecesMap.get(otherPiece.myFaction);
						piecesInEachFaction.remove(otherPiece); //remove enemy from faction
						//	        			remove enemy from faction - enemy killed
					}
					verticesAndPiecesMap.put(destinationVertex, p); //move piece to new space
					verticesAndPiecesMap.put(originVertex, null);//vacate old space
					endTurn();
				}
>>>>>>> aed0138cf827d3dd9f5af766a943fceb252f9548
			}
		}
	}

<<<<<<< HEAD
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
=======
	public void endTurn () {
		for (int losingFaction : factionsAndPiecesMap.keySet()) {
			if (factionsAndPiecesMap.get(losingFaction).size() == 0) {
				losingFactionCount++;
				factionsAndPiecesMap.remove(losingFaction);
			}
		}
		if (losingFactionCount == 2) {
			gameOver();
		}
		gameTurn++; //this determines whose turn it is... where to put it for initializing turns?
		//game turn doesn't increase if i click on the same piece over and over... maybe good, need to check?
	}

	public void gameOver () {
		System.out.println("Player " + gameTurn % 3 + "wins!");
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
>>>>>>> aed0138cf827d3dd9f5af766a943fceb252f9548
	}
}
