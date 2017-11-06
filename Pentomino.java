import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.LinkedList;
public class Pentomino extends Sprite {
	public static final int TILE_SIZE = 25;
	public static final int GRID_WIDTH = 15;
	public static final int GRID_HEIGHT = 20;
	//Type of tiles
	private List<char[][]> pentominoes = new LinkedList<char[][]>();
  private char[][] pentomino;
  private int _rotation = 0;
	public Pentomino(int pX, int pY) {
		super(pX, pY);
		speed = 2f;
		System.out.println("A new pentomino has been created!");
    pentominoes = Piece.getPieces(0);
    pentomino = pentominoes.get(0);
	}
	public void Update() {
		move();
		System.out.println("My new position is " + yPos);

	}
	public void move(int pDir) {
		xPos = xPos + pDir*TILE_SIZE ;
	}
  public void move() {
		System.out.println(Time.deltaTime);
		yPos = yPos + speed ;
	}
  public void rotate(int pDir)
  {
    _rotation += pDir;
    if(_rotation < 0) _rotation = 3;
    else if(_rotation > 3) _rotation = 0;
    pentomino = pentominoes.get(_rotation);
  }
	public void Render(GraphicsContext gc)
	{
    gc.clearRect(0, 0, GRID_WIDTH * TILE_SIZE, GRID_HEIGHT * TILE_SIZE);

		gc.setFill(Color.BLUE);
		for(int i = 0; i < pentomino[0].length; i++)
		{
			for(int j = 0; j < pentomino.length; j++)
			{
        if(pentomino[j][i] != '0')
				gc.fillRect((xPos) +(i* TILE_SIZE),yPos + (j* TILE_SIZE), TILE_SIZE, TILE_SIZE);
			}
		}
	}
}
