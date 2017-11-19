import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.LinkedList;
public class Pentomino extends GameObject {
	public static final int TILE_SIZE = 50;
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 15;
	//Type of tiles
	private List<char[][]> pentominoes = new LinkedList<char[][]>();
  private char[][] pentomino;
  private int _rotation = 0;
	private Board _board;
  private Vector2D _pivot;
	private boolean _done = false;
	public boolean _rotated = false;
	public Pentomino(int pX, int pY, Board pBoard) {
		super(pX, pY);
		_pivot =  new Vector2D(pX,pY);
		speed = 2f;
		System.out.println("A new pentomino has been created!");
    pentominoes = Piece.getPieces(0);
    pentomino = pentominoes.get(0);
		_board = pBoard;
	}
	public void Update() {
	}
	public void move(int pDir) {
		if(!_done){
			for(int i = 0; i<_components.size();i++)
				{
					if(_components.get(i) instanceof PhysicsPentomino)
					{
						PhysicsPentomino ph = (PhysicsPentomino) _components.get(i);
						ph.move(pDir);
					}
				}
			}
	}
	public Vector2D getPivot()
	{
		return _pivot;
	}
	public void setPivot(Vector2D pPivot)
	{
		_pivot = pPivot;
	}
	public void setDone()
	{
		_done = true;
		for(int i = 0; i<_components.size();i++)
			{
				if(_components.get(i) instanceof InputPentomino ){
					_components.remove(i);
				}
			}
	}
  public void rotate(int pDir)
  {
		if(!_done){
			_rotation += pDir;
	    if(_rotation < 0) _rotation = 3;
	    else if(_rotation > 3) _rotation = 0;
	    pentomino = pentominoes.get(_rotation);

			_board.updatePentomino(this);
			_board.tryMove(this, 0);
			_rotated = true;
		}
  }

	public int getTileSize()
	{
		return TILE_SIZE;
	}
	public char[][] getPentArray()
	{
		return pentomino;
	}
	public Board getBoard()
	{
		return _board;
	}
	public int getHeight()
	{
		return pentomino.length * TILE_SIZE;
	}
	public String toString()
	{
		return "NULL";
	}

}
