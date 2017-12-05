import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import java.util.List;
import java.util.LinkedList;
public class World extends GameObject{
	Canvas _canvas;
	public static final int TILE_SIZE = 50;
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 15;
	public static World world;
	private Board _board;
	public World(Canvas pCanvas) {
		world = this;
		_canvas = pCanvas;
		_board = new Board(GRID_WIDTH,GRID_HEIGHT,TILE_SIZE, _canvas.getGraphicsContext2D());
		addChild(_board);

	}
	public void addCanvas(Canvas pCanvas)
	{
		_canvas = pCanvas;
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
	}
}
