import java.util.List;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;
public class Board extends GameObject {
  private int _width;
  private int _height;
  private int _tileSize;
  private boolean _done = false;
  private Pentomino current;
  private Pentomino p;
  private Pentomino[][] _board ;
  private List<Pentomino> _pentominoes = new LinkedList<Pentomino>();
  private Score _score = new Score();
  private GraphicsContext _gc;
  private float _speed;
  private Random _rnd;
  private int _rowCombo = 1;
  public Board(int pWidth, int pHeight, int pTileSize, GraphicsContext pGc) {
    _speed = 5;
    _rnd = new Random();
    _width = pWidth;
    _height = pHeight;
    _tileSize = pTileSize;
    _gc = pGc; //The context where we will draw the board
    _board = new Pentomino[_height][_width];
    Pentomino p = new Pentomino((_width*_tileSize)/2,0,0,_tileSize,this);
		InputPentomino inputP = new InputPentomino(p);
    GraphicsComponent graphP = new GraphicsComponent(p,_gc);
    PhysicsPentomino phyP = new PhysicsPentomino(p,_speed);
    current = p;
    addPentominoToBoard(p,new Vector2D(_width/2,0));
    addChild(p);
    updatePentominoAtBoard(p);



  }
  public void addPentominoToBoard(Pentomino pPent, Vector2D pVec)
  {
      char[][] pos = pPent.getPentArray();

      for(int i = 0; i < pos.length; i++)
      {
        for(int j = 0; j < pos[0].length; j++)
        {
          if(pos[i][j] != '0')
          {

              if((int)pVec.x+j >= _board[0].length)//x is bigger than the Board
              {
                pVec.x = _board[0].length - pos[0].length;
                updatePentominoAtBoard(pPent,pVec);
                    pPent.xPos = pVec.x * 50;
                i = pos.length;
                j = pos[0].length;
              }
              if(pVec.y+i >= _board.length)
              setGameDone(true);
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
      addPentominoToBoard(pPent, pPent.getPivot()); // Add the new rotation of the pentomino
      pPent.yPos = pPent.getPivot().y * 50;
  }
    //Update pentomino in a new pivot point
  public void updatePentominoAtBoard(Pentomino pPent, Vector2D pPivot)
  {
      erasePentomino(pPent);//Erase the old positions of the pentomino
      addPentominoToBoard(pPent,pPivot); // Add the new rotation of the pentomino
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
  public void eraseRow(int pPos)
  {
    for(int j = 0; j < _board[pPos].length; j++)
    {
      _board[pPos][j].eraseBlock(new Vector2D(j,pPos));
      updatePentominoAtBoard(_board[pPos][j]);
      _board[pPos][j] = null;
    }
    _score.addScore(100 * _rowCombo);
    _rowCombo++;
    System.out.println("ROOOOOWWW COMBOOO"+_rowCombo);
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
              //System.out.println("left"+ j);
            return false;
          }
          else if(pDir == 0 && ((i + 1 >= _board.length) ||  //0 is down
           (_board[i + 1][j] != pPiece && _board[i+1][j] != null)))//DOWN
          {
           //System.out.println("down");
            return false;
          }
          else if(pDir == 1 &&  (( j + pDir >= _board[0].length ) ||//RIGHT, boundary limits
           (_board[i][j + pDir] != pPiece && _board[i][j + pDir] != null)|| (_board[i+1][j + pDir] != pPiece && _board[i+1][j + pDir] != null) ))
          {
             // System.out.println("right");
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

  public void checkRow()
  {
    int counter = 1;
    for(int i = 0; i < _board.length; i++)
    {
      for(int j = 0; j < _board[0].length; j++)
      {
        if(_board[i][j] == null)
        {
          j = _board[0].length;
          counter=0;
        }
        else{
          counter++;
        }
        if(counter == _board[0].length){
          eraseRow(i);
          counter = 0;
        }
      }
    }
  }
  public void Update()
  {
    _rowCombo = 0;
    if(_done == false){
    if(Input.keyPressed("SPACE"))
    {
      System.out.println(this.toString());
    }
    if(Input.keyPressed("Q"))
    {
      Pentomino p = new Pentomino((_width*_tileSize)/2,0,0,_tileSize,this);
      InputPentomino inputP = new InputPentomino(p);
      GraphicsComponent graphP = new GraphicsComponent(p,_gc);
      PhysicsPentomino phyP = new PhysicsPentomino(p,_speed);
      current = p;
      addPentominoToBoard(p,new Vector2D(_width/2,0));
      addChild(p);
      updatePentominoAtBoard(p);
    }
    if(Input.keyPressed("W"))
    {
      _speed++;
    }
     else if(Input.keyPressed("S"))
    {
      if(_speed > 0)
      _speed--;
    }
  }
    GameDone();
  }
  public void pentominoDone()
  {
      checkRow();
      SpawnPentomino();
      _score.addScore(20);
  }
  private void SpawnPentomino()
  {
    if(_done ==false){
    /*int nxtRnd = _rnd.nextInt(Piece.getPiecesQuantity());
    nxtRnd = 0;*/
    PentominoesPool pp= new PentominoesPool();
    pp.newPentPool();
    if(!pp.getPentPool().empty()) {
      p = new Pentomino((_width*_tileSize)/2,0,pp.getPentPool().peek(),_tileSize,this);
      pp.getPentPool().pop();
      InputPentomino inputP = new InputPentomino(p);
      GraphicsComponent graphP = new GraphicsComponent(p,_gc);
      PhysicsPentomino phyP = new PhysicsPentomino(p,_speed);
      current = p;
      addPentominoToBoard(p,new Vector2D(_width/2,0));
    }


    if(checkLose(p))
    {
      setGameDone(true);
      //TODO: Destroy this pentomino
    }
    else{
      addChild(p);
      updatePentominoAtBoard(p);
    }
  }
  }
  public void setGameDone(boolean pBool)
  {
      _done = pBool;
  }
  public boolean GameDone()
  {
    if(_done)
    _score.Done();
    return _done;
    /*if(current.yPos == current._position.y && current.isDone())
    {
      for(int i = 0; i<5000;i++)
      System.out.println("DOOOOOONE");
      System.exit(0);
      return true;
    }
    else return false;
*/  }
  public String toString()
  {
    String s = "";
    for(int i = 0; i < _board.length; i++)
    {
      for(int j = 0; j < _board[0].length; j++)
      {
        s += _board[i][j]+ " ";// + "Y: "+i+ "  ";
      }
      s += "\n";
    }
    return s;
  }
  public boolean checkLose(Pentomino p)
  {
    if(tryMove(p,0) == false){
      System.out.println("YOU LOST");
      return true;
    }
    else return false;

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
  public void Init()
  {
      //Start Time
      //Spawn first Pentomino
      //Preview next 2 pentominoes
      //Start Score
  }

}
