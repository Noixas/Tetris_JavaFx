import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;
import java.lang.Math;
import java.util.LinkedList;

/**
 *Main container class of the pentomino information
 */
public class Pentomino extends GameObject {
	private List<char[][]> 		_pentominoes = new LinkedList<char[][]>();
  private char[][] 					_pentomino;
  private int 							_rotation = 0;
	private int 							_type;
	private int 							_tileSize;
	private Board 						_board;
  private Vector2D 					_pivot;
	private boolean 					_done = false;
	private boolean 					_rotated = false;

	public Pentomino(Vector2D pPos, int pType,int pTileSize, Board pBoard) {
		super((int)pPos.x, (int)pPos.y);
		_tileSize = pTileSize;
		_pivot =  pPos;
		_board = pBoard;
		_type= pType;
    _pentominoes = Piece.getPieces(_type);//Get all posible ways of the pentomino
    _pentomino = _pentominoes.get(_rotation);
		for(int i = 0; i < _pentomino.length; i++)
			for(int j = 0; j < _pentomino[0].length; j++)
					System.out.println(_pentomino[i][j]);
	}
		public Pentomino(Vector2D pPos, char[][] pPent,int pTileSize, Board pBoard) {
			super((int)pPos.x, (int)pPos.y);
			_tileSize = pTileSize;
			_pivot =  pPos;
			_board = pBoard;
	    _pentominoes = Piece.getPieces(0);//Get all posible ways of the pentomino
	    _pentomino = pPent;
		}
  public void rotate(int pDir)
  {
		if(!_done){
			_rotation += pDir;
	    if(_rotation < 0) _rotation = 3;
	    else if(_rotation > 3) _rotation = 0;

			Vector2D newPivot = getPivot();
			//Return Vector.Zero when the rotation is possible, otherwise return a pivot sugestion
			newPivot = _board.tryRotation(this,_pentominoes.get(_rotation), newPivot);
			if(newPivot.equals(Vector2D.Zero))
			{
				_pentomino = _pentominoes.get(_rotation);
				_board.updatePentominoAtBoard(this,getPivot());
				xPos = _pivot.x *_tileSize;//Update world/graphics pos
				yPos = _pivot.y * _tileSize;
			}
			else{
				while(!newPivot.equals(Vector2D.Zero))
				{
					_pivot = newPivot;//possible solution
					newPivot = _board.tryRotation(this,_pentominoes.get(_rotation), _pivot);//Try possible solution
				}
				_pentomino = _pentominoes.get(_rotation);
				_board.updatePentominoAtBoard(this,_pivot);
				xPos = _pivot.x *_tileSize;//Update world/graphics pos
				yPos = _pivot.y * _tileSize;
			}
			_rotated = true;
		}
  }
	public void move(int pDir) {//APROVED
		if(!_done)//If not done then keep moving
			for(int i = 0; i<_components.size();i++)
				if(_components.get(i) instanceof PhysicsPentomino)
				{
					PhysicsPentomino physicsComp = (PhysicsPentomino) _components.get(i);
					physicsComp.move(pDir);
				}
	}
	public void settleDone()//APROVED
	{
		_done = true;
		for(int i = 0; i<_components.size();i++)//remove input component
			{
				if(_components.get(i) instanceof InputPentomino ){
					_components.remove(i);
				}
				if(_components.get(i) instanceof GraphicsComponent ){
					GraphicsComponent GraphComp = (GraphicsComponent) _components.get(i);
					GraphComp.DisableBlinking();
				}
			}
	}
	public void eraseBlock(Vector2D pVec)
	{
		pVec.x = Math.abs(pVec.x-_pivot.x);
		pVec.y = Math.abs(pVec.y-_pivot.y);
		_pentomino[(int)pVec.y][(int)pVec.x] = '0';
		//checkIfSplitPentomino();
	}
	private void checkIfSplitPentomino()
	{
		int counter = _pentomino[0].length - 1;
		for(int i = 0; i < _pentomino.length; i++)
			for(int j = 0; j < _pentomino[0].length; j++){
				if(_pentomino[i][j] == '0')	counter--;
				if(counter == 0)	SplitPentomino(i);
		}
		counter = _pentomino[0].length - 1;
	}
	/**
	 * Split the pentomino if there is an empty row in between
	 * @param int pRow [The row to be deleted]
	 */
	private void SplitPentomino(int pRow)
	{
		if(pRow != 0 && pRow != getPentominoHeight()-1){
		char[][] above = new char[pRow-1][_pentomino[0].length];
		char[][] below = new char[_pentomino.length - pRow][_pentomino[0].length];
		System.out.println("ABOVE \n"+above);
		System.out.println("BELOW \n"+below);
		Pentomino a = new Pentomino(new Vector2D(xPos, yPos),above,_tileSize,_board);
		Pentomino b = new Pentomino(new Vector2D(xPos, yPos+(pRow*_tileSize)),below,_tileSize,_board);
		Vector2D aPivot = a.getPivot();
		aPivot.x = aPivot.x/_tileSize;
		aPivot.y = aPivot.y/_tileSize;
		a.setPivot(aPivot);
		Vector2D bPivot = b.getPivot();
		bPivot.x = bPivot.x/_tileSize;
		bPivot.y = bPivot.y/_tileSize;
		b.setPivot(bPivot);
		Pentomino[] newP = new Pentomino[2];
		newP[0] = a;
		newP[1] = b;
		_board.addSplitPentomino(this,newP);
	}
	}
	public void fallAllTheWay()//APROVED
	{
		if(!_done){//If not done then keep moving
			for(int i = 0; i<_components.size();i++)
				if(_components.get(i) instanceof PhysicsPentomino)
				{
					PhysicsPentomino physicsComp = (PhysicsPentomino) _components.get(i);
					while(_board.tryMove(this, 0)) physicsComp.fallStepBoard();
					setDone();
				}
		}
	}
	public void startBlinking()
	{
		for(int i = 0; i<_components.size();i++)
		{
			if(_components.get(i) instanceof GraphicsComponent)
			{
				GraphicsComponent GraphComp = (GraphicsComponent) _components.get(i);
				GraphComp.EnableBlinking();
			}
		}
	}
	public void restartArray()
	{
		_pentomino = _pentominoes.get(_rotation);
	}
	public void setPivot(Vector2D pPivot)
	{
		_pivot = pPivot;
	}
	public void setDone()
	{
		if(!_done) _board.pentominoDone();
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
	public int getPentominoHeight()
	{
		return _pentomino.length * getTileSize();
	}
	public boolean getRotated()
	{
		return _rotated;
	}
	public void rotationUsed()
	{
		_rotated = false;
	}
	public Vector2D getPivot()
	{
		return _pivot;
	}
	public int getType ()
	{
		return _type;
	}
	public String toString()
	{
		return "PENT";
	}
}
