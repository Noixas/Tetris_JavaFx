/*public class Bot extends GameObject {
  private Pentomino[][] _currentBoard;
  private Pentomino _activePentomino;
  private Board _board;
  private boardHeight = _currentBoard.length;
  private boardWidth = _currentBoard[0].length;


  public void startBot() {
    int botInt = 0;
    while(botInt < 10) {
      Input.keyPressed("UP");
      botInt++;
    }
  }

  private Bot(Pentomino[][] currentBoard, Board board) {
    this._currentBoard = currentBoard;
    this._board = board;
  }

  private void Update() {
    this._activePentomino = _board.getActivePentomino();
    addChild();
  }

  private void checkMoveOutcome() {
    for() {

    }
  }

//Heuristics
  private int holesCount() {//Counts how many "holes" there are

  }

  private int bumpinessCount() {//Counts how bumpy the "mass of pentominoes" is
  for(int i=0;i<currentBoard[0].length;i++) {

  }
    //How many sides are open?
    //The difference in height between each column?

  }

  private int heightCount() { //Counts the cumulative height of all the columns
    int totalHeight = 0;
    for(int i=0;i<currentBoard.length;i++) {
      totalHeight += candidateBoard.columnHeight(i);
    }
    return totalHeight;
  }

  private int completeRowCount() {//Counts the complete lines made

  }

  private int heuristicsScorer() {
    return holesCount() + bumpinessCount() + heightCount() + completeRowCount();
  }

//Helper
  private int columnHeight(int c) {
    boolean pieceFound = false;
    int colHeight = 0;
    int i = 0;
    while(i<currentBoard[0][i].length && !pieceFound) {
      if(candidateBoard[c][i] = '0') {
        colHeight++;
        i++;
      } else {
        pieceFound = true;
      }
    }
    return colHeight;
  }
}
*/
