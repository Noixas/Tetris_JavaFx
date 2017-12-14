import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.util.List;
import java.util.LinkedList;
public class World extends GameObject{
	Canvas _canvas;
	Canvas _previewCanvas;
	public static final int TILE_SIZE = 50;
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 15;
	public static World world;
	private Board _board;
	private BoardUI constructUI;
	public World(GridPane root) {

		int tile_size = 50;
		Pane left = new Pane();
		left.setStyle("-fx-background-color: #357dff");
		left.setPrefSize(390,750);
		Pane right = new Pane();
		right.setStyle("-fx-background-color: #357dff");
		right.setPrefSize(390,750);
		Pane worldPane = new Pane();
		worldPane.setStyle("-fx-background-color: black;");
		worldPane.setPrefSize(tile_size*10,tile_size*15);


		Canvas canvas = new Canvas(500,750);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.add(left, 0, 0, 1, 1);
		root.add(worldPane, 1, 0, 1, 1);
		root.add(right, 2, 0, 1, 1);
		worldPane.getChildren().addAll(canvas);




		world = this;
		_canvas = canvas;
		_board = new Board(GRID_WIDTH,GRID_HEIGHT,TILE_SIZE, _canvas.getGraphicsContext2D(),1);
		addChild(_board);

		 constructUI = new BoardUI(right, left, _board);
		addChild(constructUI);

	}
	public void Update() {
		if(Input.keyPressed("ESCAPE")) {
				 System.out.println("Terminating Game...");
				_board.setGameDone(true);
				_board.checkIfGameDone();
				 System.exit(0);
		 }
		 if(Input.keyPressed("R")) {
 				RestartGame();
 		 }
		_canvas.getGraphicsContext2D().clearRect(0, 0, GRID_WIDTH * TILE_SIZE, GRID_HEIGHT * TILE_SIZE);

		List<GameObject> childs = getAllChildren(new LinkedList<GameObject>());
		for(int i=0; i<childs.size(); i++) {
			List<Component> comps = childs.get(i).getComponents();
			for(int j = 0; j < comps.size(); j++)
			{
					Component comp = comps.get(j);
		 		 if(comp instanceof InputPentomino)
					{
						comp.Update();//Update Input
				  }
			}
			childs.get(i).Update();//Update objects after input
			for(int j = 0; j< comps.size(); j++)
			{
				Component comp = comps.get(j);
		  	if(comp instanceof PhysicsPentomino)
				{
					comp.Update();//Update physics
				}
				else if(comp instanceof GraphicsComponent)
				{
		  		comp.Update();//Render screen
				}
			}
		}
		Input.clearInput();
	}
	public void RestartGame()
	{
		System.out.println("Restarting Game...");
		Score.Restart();
	  Time.StartTime();
	  removeChild(_board);
	  _board = new Board(GRID_WIDTH,GRID_HEIGHT,TILE_SIZE, _canvas.getGraphicsContext2D());
	 	addChild(_board);
		constructUI.updateBoard(_board);
	}
}
