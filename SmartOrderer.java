import java.util.List;
import java.util.LinkedList;

public class SmartOrderer extends GameObject{
  private List<int[][]> _pentAndMoves = new LinkedList<int[][]>();
  private int _aim = 5;
  private char[][] _virtualBoard;
  public SmartOrderer(){
    super(0,0);
    startSimulation();
  }
  private void startSimulation()
  {
    _virtualBoard = new char[_aim][5];
    for(int i = 0; i < _virtualBoard.length; i++)
      for(int j = 0; j < _virtualBoard[0].length; j++){
          _virtualBoard[i][j] = '0';
          System.out.println(  _virtualBoard[i][j] );
      }
    }
}
