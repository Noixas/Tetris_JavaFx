import java.util.List;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class Board extends GameObject {
  private int _width;
  private int _height;
  private Pentomino current;
  private Pentomino[][] _board ;
  private List<Pentomino> _pentominoes = new LinkedList<Pentomino>();
  private Score _score = new Score();
  private GraphicsContext _gc;
  private float _speed;
  public Board(int pWidth, int pHeight, GraphicsContext pGc) {
    _speed = 1;
    _width = pWidth;
    _height = pHeight;
    _gc = pGc; //The context where we will draw the board
    _board = new Pentomino[_height][_width];
    Pentomino p = new Pentomino(0,0,this);
		InputPentomino inputP = new InputPentomino(p);
    GraphicsComponent graphP = new GraphicsComponent(p,_gc);
    PhysicsPentomino phyP = new PhysicsPentomino(p,_speed);
    addPentomino(p,0,0);
    addChild(p);
    updatePentomino(p);

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
  public void updatePentomino(Pentomino pPent)
  {
      char[][] pos = pPent.getPentArray();
      Vector2D pivot = pPent.getPivot();
      System.out.println(pivot.toString());
        //pPent.getPivot();
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
      addPentomino(pPent,pivot);
      System.out.println(pivot.toString());

  }
  //0 is down
  public boolean tryMove(Pentomino pPiece, int pDir)
  {
    for(int i = 0; i < _board.length; i++)
    {
      for(int j = 0; j < _board[0].length; j++)
      {
        if(_board[i][j] == pPiece)
        {
          if(pDir == -1 && ((j + pDir < 0) || //LEFT, boundary limits
          (_board[i][j + pDir] != pPiece && _board[i][j + pDir] != null)  ))
          {
              System.out.println("left"+ j);

            return false;
          }
          else if(pDir == 0 && ((i + 1 >= _board.length) ||
           (_board[i + 1][j] != pPiece && _board[i+1][j] != null)))//DOWN
          {
            System.out.println("down");
            return false;
          }
          else if(pDir == 1 &&  ( j + pDir < _board[0].length ) &&//RIGHT, boundary limits
           (_board[i][j + pDir] != pPiece && _board[i][j + pDir] != null))
          {

              System.out.println("right");
            return false;
          }
        }
      }
    }
    for(int i = 0; i < _board.length; i++)
    {
      for(int j = 0; j < _board[0].length; j++)
      {
        if(_board[i][j] == pPiece){
          if(pDir == -1 && j+pDir >= 0 ){
            _board[i][j + pDir] = pPiece;
            _board[i][j] = null;
          }
          else if(pDir == 0 && i+1 < _board.length)
          {
            _board[i+1][j ] = pPiece;
            //_board[i][j] = null;
            return true;
          }
          else if(pDir == 1 && j+ pDir < _board[0].length) {//when going to the right
            _board[i][j + pDir] = pPiece;
            _board[i][j] = null;
          }
      }
      }
    }
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
  public void Update()
  {
    String s = "";
    if(Input.keyPressed("SPACE"))
    {
      for(int i = 0; i < _board.length; i++)
      {
        for(int j = 0; j < _board[0].length; j++)
        {
          s += _board[i][j] + "  ";
        }
        s += "\n";
      }
      System.out.println(s);
    //  System.exit(0);
    }
  }
  public void Init()
  {
      //Start Time
      //Spawn first Pentomino
      //Preview next 2 pentominoes
      //Start Score
  }

}
