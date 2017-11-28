import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;
import java.lang.Math;
import java.util.LinkedList;
public class Pentomino extends GameObject {
	private List<char[][]> _pentominoes = new LinkedList<char[][]>();
  private char[][] _pentomino;
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
    _pentominoes = Piece.getPieces(_type);//Get all posible ways of the pentomino
    _pentomino = _pentominoes.get(_rotation);
	}

	public Pentomino(Vector2D pPos, int pType,int pTileSize, Board pBoard) {
		super((int)pPos.x, (int)pPos.y);
		_tileSize = pTileSize;
		_pivot =  pPos;
		_board = pBoard;
		_type= pType;
    _pentominoes = Piece.getPieces(_type);//Get all posible ways of the pentomino
    _pentomino = _pentominoes.get(_rotation);
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
		if(!_done){
		_board.pentominoDone();
		System.out.println("Pent Done");
	}
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
	    _pentomino = _pentominoes.get(_rotation);

		if(_board.tryMove(this, 0) == false)
		{
			_pivot.x -= 8;
			_board.updatePentominoAtBoard(this,_pivot);
		}
		else
		{
			System.out.println("OTHER");
			_board.updatePentominoAtBoard(this);
		}
		_rotated = true;
		}
  }
	public void eraseBlock(Vector2D pVec)
	{
		pVec.x = Math.abs(pVec.x-_pivot.x);
		pVec.y = Math.abs(pVec.y-_pivot.y);
		_pentomino[(int)pVec.y][(int)pVec.x] = '0';

	}
	public void resizePentomino()
	{
    int counter = 0;
		for(int i = 0; i < _pentomino.length; i++)
    {
      for(int j = 0; j < _pentomino[0].length; j++)
      {
				if(counter == _pentomino[0].length-1){
					//eraseRow(i);
					counter = 0;
				}
				if(_pentomino[i][j] == '0')
				{
					j = _pentomino[0].length;
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
		return _pentomino;
	}
	public Board getBoard()
	{
		return _board;
	}
	public int getHeight()
	{
		return _pentomino.length * getTileSize();
	}
	public String toString()
	{
		return "PENT";
	}
	public boolean isDone()
	{
		return _done;
	}

}
