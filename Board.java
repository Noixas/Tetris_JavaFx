import java.util.List;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;
public class Board extends GameObject {
  private int               _width;
  private int               _height;
  private int               _tileSize;
  private float             _delay = 5.0f;
  private boolean           _done = false;
  private float             _startDelayTime = 0;
  private boolean           _startDelay = false;
  private Pentomino         _activePentomino;
  private Pentomino[][]     _board ;
  private List<Pentomino>   _pentominoes = new LinkedList<Pentomino>();
  private Score             _score = new Score();
  private GraphicsContext   _gc;
  private float             _speed = .3f;
  private Random            _random = new Random();
  private int              _rowCombo = 1;
  public Board(int pWidth, int pHeight, int pTileSize, GraphicsContext pGc) {
    _width = pWidth;
    _height = pHeight;
    _tileSize = pTileSize;
    _gc = pGc; //The context where we will draw the board
    _board = new Pentomino[_height][_width];
    SpawnPentomino();///Spawn the first pentomino
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
              else if((int)pVec.x+j <0)
              {
                  pVec.x = 0;
                  updatePentominoAtBoard(pPent,pVec);
                  pPent.xPos = pVec.x * 50;
                  i = pos.length;
                  j = pos[0].length;
              }
              if(pVec.y+i >= _board.length)
              setGameDone(true);
              try{
            _board[(int)pVec.y+i][(int)pVec.x+j] = pPent;
          }catch(Exception e)//Temporary catch while identified bug is being fix, (the falling glitch between world/board))
            {
              System.out.println("Board.java Line: 53");
              System.out.println("Y: "+pVec.y + " X: "+pVec.x );
              System.out.println("I: "+i + " J: "+j);
              System.out.println(e);
              System.exit(0);
            }
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
      _board[pPos][j].eraseBlock(new Vector2D(j,pPos));//x,y
      updatePentominoAtBoard(_board[pPos][j]);
      _board[pPos][j] = null;//TODO:Check this and above line
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
    //Update pivot point,*moved to physics component
  /*  Vector2D newPivot = pPiece.getPivot();
    newPivot.x += pDir;
    updatePentominoAtBoard(pPiece, newPivot);*/
    return true;
  }

  public void pentominoDone()
  {
    if(_startDelay == false)
    {
      _startDelay = true;
      _startDelayTime = Time.getGameTime();
    }
  }
  private void tryDonePentomino()//NON APROVED
  {
    if(_delay < 0){
    _activePentomino.settleDone();
    checkRow();
    SpawnPentomino();
    _score.addScore(20);
    _delay = 50.0f;
    _startDelay = false;
    _startDelayTime = 0;
    }
    else if(_startDelay){
      _delay -= (Time.getGameTime() - _startDelayTime);
      _activePentomino.startBlinking();
    //  System.out.println(_delay);
    }
  }
  public void Update()//APROVED
  {
    _rowCombo = 0;
    if(_done == false){
      if(Input.keyPressed("W"))_speed += .1f;
      else if(Input.keyPressed("S") && _speed > 0) _speed-= .1f;;
    }

    tryDonePentomino();
    GameDone();
  }
  public void checkRow()//APROVED
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
        else counter++;

        if(counter == _board[0].length){
          eraseRow(i);
          counter = 0;
        }
      }
    }
  }
  private void SpawnPentomino()//APROVED
  {
    if(_done) return;//If we are done, stop spawning pentominoes

    int nxtRnd = _random.nextInt(Piece.getPiecesQuantity());
    nxtRnd = 0;
    Pentomino p = new Pentomino(new Vector2D((_width*_tileSize)/2,0),0,_tileSize,this);
    InputPentomino inputP = new InputPentomino(p);
    GraphicsComponent graphP = new GraphicsComponent(p,_gc);
    PhysicsPentomino phyP = new PhysicsPentomino(p,_speed);
    _activePentomino = p;
    addPentominoToBoard(p,new Vector2D(_width/2,0));
    if(checkLose(p))//check if spawning this pentomino will make the game lose
    {
      setGameDone(true);
      //TODO: Destroy this pentomino
    }
    else{
      addChild(p);
      updatePentominoAtBoard(p);
    }
  }
  public void setGameDone(boolean pBool)//APROVED
  {
      _done = pBool;
  }
  public boolean GameDone()//APROVED
  {
    if(_done)
    _score.Done();// Tell score to save it in the file
    return _done;
  }
  public String toString()//APROVED
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
  public boolean checkLose(Pentomino p)//APROVED
  {
    if(tryMove(p,0) == false){
      System.out.println("YOU LOST");
      return true;
    } else return false;

  }
  public void Init()
  { //TODO:
      //Start Time
      //Spawn first Pentomino
      //Preview next pentominoe
      //Start Score
  }

}
