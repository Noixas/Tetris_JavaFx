import java.util.List;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class Board extends GameObject {
  private int _width;
  private int _height;
  private int _tileSize;
  private Pentomino current;
  private Pentomino[][] _board ;
  private List<Pentomino> _pentominoes = new LinkedList<Pentomino>();
  private Score _score = new Score();
  private GraphicsContext _gc;
  private float _speed;
  public Board(int pWidth, int pHeight, int pTileSize, GraphicsContext pGc) {
    _speed = 5;
    _width = pWidth;
    _height = pHeight;
    _tileSize = pTileSize;
    _gc = pGc; //The context where we will draw the board
    _board = new Pentomino[_height][_width];
    Pentomino p = new Pentomino(0,0,0,_tileSize,this);
		InputPentomino inputP = new InputPentomino(p);
    GraphicsComponent graphP = new GraphicsComponent(p,_gc);
    PhysicsPentomino phyP = new PhysicsPentomino(p,_speed);
    addPentomino(p,0,0);
    addChild(p);
    updatePentominoAtBoard(p);

  }
  public void addPentomino(Pentomino pPent, int x, int y)
  {
      char[][] pos = pPent.getPentArray();
      for(int i = 0; i < pos.length; i++)
      {
        for(int j = 0; j < pos[0].length; j++)
        {
          if(pos[i][j] != '0')
          {
            _board[y+i][x+j] = pPent;
          }
        }
      }
  }
  public void addPentomino(Pentomino pPent, Vector2D pVec)
  {
      char[][] pos = pPent.getPentArray();
      for(int i = 0; i < pos.length; i++)
      {
        for(int j = 0; j < pos[0].length; j++)
        {
          if(pos[i][j] != '0')
          {
            _board[(int)pVec.y+i][(int)pVec.x+j] = pPent;
          }
        }
      }
      pPent.setPivot(pVec);
  }
  //Update pentomino in the same pivot point
  public void updatePentominoAtBoard(Pentomino pPent)
  {
      erasePentomino(pPent);//Erase the old positions of the pentomino
      addPentomino(pPent, pPent.getPivot()); // Add the new rotation of the pentomino
      pPent.yPos = pPent.getPivot().y * 50;
  }
    //Update pentomino in a new pivot point
  public void updatePentominoAtBoard(Pentomino pPent, Vector2D pPivot)
  {
      erasePentomino(pPent);//Erase the old positions of the pentomino
      addPentomino(pPent,pPivot); // Add the new rotation of the pentomino
  }
  private void erasePentomino(Pentomino pPent)
  {
    for(int i = 0; i < _board.length; i++)
    {
      for(int j = 0; j < _board[0].length; j++)
      {
        if(_board[i][j] == pPent)
        {
          _board[i][j] = null;
        }
      }
    }
  }

  public boolean tryMove(Pentomino pPiece, int pDir)
  {
    for(int i = 0; i < _board.length; i++)
    {
      for(int j = 0; j < _board[0].length; j++)
      {
        if(_board[i][j] == pPiece)
        {
          if(pDir == -1 && ((j + pDir < 0) || //LEFT, boundary limits
          (_board[i][j + pDir] != pPiece && _board[i][j + pDir] != null) || (_board[i+1][j + pDir] != pPiece && _board[i+1][j + pDir] != null)  ))
          {
            //  System.out.println("left"+ j);
            return false;
          }
          else if(pDir == 0 && ((i + 1 >= _board.length) ||  //0 is down
           (_board[i + 1][j] != pPiece && _board[i+1][j] != null)))//DOWN
          {
          //  System.out.println("down");
            return false;
          }
          else if(pDir == 1 &&  (( j + pDir >= _board[0].length ) ||//RIGHT, boundary limits
           (_board[i][j + pDir] != pPiece && _board[i][j + pDir] != null)|| (_board[i+1][j + pDir] != pPiece && _board[i+1][j + pDir] != null) ))
          {
              //System.out.println("right");
            return false;
          }
        }
      }
    }
    //Update pivot point
    Vector2D newPivot = pPiece.getPivot();
    newPivot.x += pDir;
    updatePentominoAtBoard(pPiece, newPivot);

    return true;
  }
  public int getWidth() {
    return _width;
  }
  public int getHeight() {
    return _height;
  }
  public Score getScore() {
    return _score;
  }
  public void eraseRow(int pPos)
  {
    for(int j = 0; j < _board[pPos].length; j++)
    {
      _board[pPos][j].eraseBlock(new Vector2D(j,pPos));
      _board[pPos][j] = null;
    }
  }
  public void checkRow()
  {
    int counter = 0;
    for(int i = 0; i < _board.length; i++)
    {
      for(int j = 0; j < _board[0].length; j++)
      {
        if(counter == _board[0].length-1){
          eraseRow(i);
          counter = 0;
        }
        if(_board[i][j] == null)
        {
          j = _board[0].length;
          counter=0;
        }
        else{
          counter++;
        }

      }
    }
  }
  public void Update()
  {
    checkRow();
    if(Input.keyPressed("SPACE"))
    {
      System.out.println(this.toString());
    //  System.exit(0);
    }
    if(Input.keyPressed("Q"))
    {
      Pentomino p = new Pentomino(0,0,0,_tileSize,this);
      InputPentomino inputP = new InputPentomino(p);
      GraphicsComponent graphP = new GraphicsComponent(p,_gc);
      PhysicsPentomino phyP = new PhysicsPentomino(p,_speed);
      addPentomino(p,0,0);
      addChild(p);
      updatePentominoAtBoard(p);
    }
    if(Input.keyPressed("S"))
    {
      List<GameObject> check = getChildren();
      for(int i = 0; i<check.size();i++)
      {
          System.out.println(check.get(i).getPosition());
      }
    }
  }
  public String toString()
  {
    String s = "";
    for(int i = 0; i < _board.length; i++)
    {
      for(int j = 0; j < _board[0].length; j++)
      {
        s += _board[i][j] + "  ";
      }
      s += "\n";
    }
    return s;
  }
  public void Init()
  {
      //Start Time
      //Spawn first Pentomino
      //Preview next 2 pentominoes
      //Start Score
  }

}
