import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;
import java.lang.Math;
import java.util.LinkedList;
public class Pentomino extends GameObject {
	public static final int TILE_SIZE = 50;
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 15;
	//Type of tiles
	private List<char[][]> pentominoes = new LinkedList<char[][]>();
  private char[][] pentomino;
  private int _rotation = 0;
	private int _type;
	private Board _board;
	private int _tileSize;
  private Vector2D _pivot;
	private boolean _done = false;
	public boolean _rotated = false;
	public Pentomino(int pX, int pY, int pType,int pTileSize, Board pBoard) {
		super(pX, pY);
		_tileSize = pTileSize;
		_pivot =  new Vector2D(pX,pY);
		_board = pBoard;
		_type= pType;
    pentominoes = Piece.getPieces(_type);//Get all posible ways of the pentomino
    pentomino = pentominoes.get(_rotation);
	}
	public void move(int pDir) {
		if(!_done){
			for(int i = 0; i<_components.size();i++)
				{
					if(_components.get(i) instanceof PhysicsPentomino)
					{
						PhysicsPentomino physicsComp = (PhysicsPentomino) _components.get(i);
						physicsComp.move(pDir);
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
		for(int i = 0; i<_components.size();i++)//remove input component
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
			_board.updatePentominoAtBoard(this);
			_board.tryMove(this, 0);
			_rotated = true;
		}
  }
	public void eraseBlock(Vector2D pVec)
	{
		pVec.x = Math.abs(pVec.x-_pivot.x);
		pVec.y = Math.abs(pVec.y-_pivot.y);
		pentomino[(int)pVec.y][(int)pVec.x] = '0';

	}
	public void resizePentomino()
	{
    int counter = 0;
		for(int i = 0; i < pentomino.length; i++)
    {
      for(int j = 0; j < pentomino[0].length; j++)
      {
				if(counter == pentomino[0].length-1){
					//eraseRow(i);
					counter = 0;
				}
				if(pentomino[i][j] == '0')
				{
					j = pentomino[0].length;
					counter=0;
				}
				else{
					counter++;
				}
			}
		}
	}
	public int getTileSize()
	{
		return _tileSize;
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
		return pentomino.length * getTileSize();
	}
	public String toString()
	{
		return "PENT";
	}

}
