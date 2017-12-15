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
  private int               _rowCombo = 1;
  private PentominoesPool   _pp = new PentominoesPool();
  private int               _gameMode;

  public Board(int pWidth, int pHeight, int pTileSize, GraphicsContext pGc, int pGameMode) {
    _gameMode = pGameMode;
    _width = pWidth;
    _height = pHeight;
    _tileSize = pTileSize;
    _gc = pGc; //The context where we will draw the board
    _board = new Pentomino[_height][_width];
    SpawnPentomino();///Spawn the first pentomino
    if(_gameMode == 2) {
      Bot bot = new Bot(_board, this);
      addChild(bot);
    }
  }//APROVED
  public void addPentominoToBoard(Pentomino pPent, Vector2D pVec)
  {
      char[][] pos = pPent.getPentArray();
      for(int i = 0; i < pos.length; i++)
        for(int j = 0; j < pos[0].length; j++)
          if(pos[i][j] != '0')
          {
            _board[(int)pVec.y+i][(int)pVec.x+j] = pPent;
          }
      pPent.setPivot(pVec);
  }
  public void addSplitPentomino(Pentomino old, Pentomino[] newP)
  {
    erasePentomino(old);
    for(int i = 0; i < newP.length; i++)
    {
      GraphicsComponent graphP = new GraphicsComponent(newP[i],_gc);
      PhysicsPentomino phyP = new PhysicsPentomino(newP[i],_speed);
      addPentominoToBoard(newP[i],newP[i].getPivot());
      addChild(newP[i]);
      newP[i].toString();
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
    return true;
  }
  public Vector2D tryRotation(Pentomino oldRot, char[][] newRot, Vector2D pivot)//Aproved
  {
        int x = (int)pivot.x;
        int y = (int)pivot.y;
        for(int i = 0; i < newRot.length; i++)
          for(int j = 0; j < newRot[0].length; j++)
          {
            if(y+i >= _board.length)
            return new Vector2D(pivot.x, pivot.y - (newRot.length-i));
            else if(x+j >= _board[0].length)
            return new Vector2D(pivot.x - (newRot[0].length-j), pivot.y);
            else if(_board[y+i][x+j] != oldRot &&  _board[y+i][x+j] != null)
            {
              if(i >= j){
              Vector2D n = new Vector2D(pivot.x, pivot.y - (newRot.length-i));
              n.toString();
                return n;
              }
              else{
              Vector2D n = new Vector2D(pivot.x - (newRot[0].length-j), pivot.y);
              n.toString();

                return n;
              }
            }
          }

    return Vector2D.Zero;
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
    _rowCombo = 1;
    if(_done == false){
      if(Input.keyPressed("W"))_speed += .1f;
      else if(Input.keyPressed("S") && _speed > 0) _speed-= .1f;;
    }
    tryDonePentomino();
    checkIfGameDone();
  }
  public void eraseRow(int pPos)//Aproved
  {
    for(int j = 0; j < _board[pPos].length; j++)
    {
      _board[pPos][j].eraseBlock(new Vector2D(j,pPos));//x,y
      updatePentominoAtBoard(_board[pPos][j],_board[pPos][j].getPivot());
      _board[pPos][j] = null;
    }
    _score.addScore(100 * _rowCombo);
    _rowCombo++;
    System.out.println("ROOOOOWWW COMBOOO"+_rowCombo);
  }
  //Update pentomino in a new pivot point
  public void updatePentominoAtBoard(Pentomino pPent, Vector2D pPivot)//Aproved
  {
      erasePentomino(pPent);//Erase the old positions of the pentomino
      addPentominoToBoard(pPent,pPivot); // Add the new rotation of the pentomino
  }
  private void erasePentomino(Pentomino pPent)//APROVED
  {
    for(int i = 0; i < _board.length; i++)
      for(int j = 0; j < _board[0].length; j++)
        if(_board[i][j] == pPent)  _board[i][j] = null;
  }
  public void checkRow()//APROVED
  {
    int counter = 1;
    for(int i = 0; i < _board.length; i++)
      for(int j = 0; j < _board[0].length; j++)
      {
        if(_board[i][j] == null)
        {
          j = _board[0].length;
          counter = 1;
        }
        else counter++;

        if(counter == _board[0].length){
          eraseRow(i);
          counter = 1;
        }
      }
    }

  private void newPentomino()
  {
    int startPos = (_width - (int)(_width/2));

   Pentomino p = new Pentomino(new Vector2D(startPos*_tileSize,0),_pp.getPentPool().pop(),_tileSize,this);
//Erase line below, only for debug purposes
    // p = new Pentomino(new Vector2D((_width*_tileSize)/2,0),0,_tileSize,this);

    InputPentomino inputP = new InputPentomino(p);
    GraphicsComponent graphP = new GraphicsComponent(p,_gc);
    PhysicsPentomino phyP = new PhysicsPentomino(p,_speed);
    _activePentomino = p;
    System.out.println((_width - (int)(_width/2)));
    int pentWidth = p.getPentArray()[0].length;
      startPos = (5-pentWidth);
    p.translate(startPos*_tileSize, 0, 1);
    p.setPivot(new Vector2D(startPos,0));
    addPentominoToBoard(p,new Vector2D(startPos,0));
    addChild(p);
    if(_pp.getPentPool().empty()) _pp.newPentPool();
  }

  public int getPreviewInt() {
    int previewInt = _pp.getPentPool().peek();
    return previewInt;
  }
  private void SpawnPentomino()//APROVED
  {
    if(_done) return;//If we are done, stop spawning pentominoes
    newPentomino();

    if(checkLose(_activePentomino))//check if spawning this pentomino will make the game lose
    {
      setGameDone(true);
      //TODO: Destroy this pentomino
    }
  }
  public void setGameDone(boolean pBool)//APROVED
  {
      _done = pBool;
  }
  public boolean checkIfGameDone()//APROVED
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
  public Pentomino[][] getBoardArray()
  {
    return _board;
  }

  public Pentomino getActivePentomino() {
    return _activePentomino;
  }
}
