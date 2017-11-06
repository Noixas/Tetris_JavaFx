import java.util.List;
import java.util.LinkedList;
public class Board extends GameObject {
  private int _width;
  private int _height;
  private  Pentomino current;
  private List<Pentomino> _pentominoes = new LinkedList<Pentomino>();
  private Score _score = new Score();
  public Board(int pWidth, int pHeight) {
    _width = pWidth;
    _height = pHeight;
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
