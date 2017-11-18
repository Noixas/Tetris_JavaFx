import java.util.List;
import java.util.LinkedList;
public class Board extends GameObject {
  private int _width;
  private int _height;
  private Pentomino current;
  private Pentomino[][] _board ;
  private List<Pentomino> _pentominoes = new LinkedList<Pentomino>();
  private Score _score = new Score();
  public Board(int pWidth, int pHeight) {
    _width = pWidth;
    _height = pHeight;
    _board = new Pentomino[_height][_width];
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
          if(pDir == -1 && ( j + pDir >= 0 ) &&//LEFT, boundary limits
           (_board[i][j + pDir] != pPiece && _board[i][j + pDir] != null))
          {
            return false;
          }
          else if(pDir == 0 && (j - pDir < _board.length) &&
           (_board[i + 1][j] != pPiece && _board[i+1][j] != null))//DOWN
          {
            return false;
          }
          else if(pDir == 1 &&  ( j + pDir < _board[0].length ) &&//RIGHT, boundary limits
           (_board[i][j + pDir] != pPiece && _board[i][j + pDir] != null))
          {
            return false;
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
  //TODO add update method
  public void Init()
  {
      //Start Time
      //Spawn first Pentomino
      //Preview next 2 pentominoes
      //Start Score
  }

}
