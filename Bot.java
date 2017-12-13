/*public class Bot extends GameObject {
  private Pentomino[][] _currentBoard;
  private Pentomino _activePentomino;
  private Board _board;
  private int boardHeight = _currentBoard.length;
  private int boardWidth = _currentBoard[0].length;
  private double holesWeight;
  private double bumpinessWeight;
  private double heightWeight;
  private double rowWeight;
  private int moves = 0;
  private int rotation = 0;

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


  private void newAttempt() {
    Board _candidateBoard = _currentBoard.clone();
    Pentomino[][] _candidatePentomino = _activePentomino.clone();
    moveAllToLeft();
    tryPentominoPosition();
  }

  private void newTrial() {
    moves = 0;
    rotation = 0;
    newAttempt();
  }

  private void bestMove() {

  }

  private void moveAllToLeft() {
    while(_board.tryMove(_candidatePentomino, -1)) {
      _activePentomino.move(-1);
    }
    _activePentomino.fallAllTheWay();
  }

  private void tryPentominoPosition() {
    if(board.tryMove(_candidatePentomino, 1)) {
      for(int i=0;i<=moves;i++) {
        _candidatePentomino.move(1);
      }
    }
  }

//Heuristics
  private int holesCount() { //Counts how many "holes" there are

  }

  private int bumpinessCount() { //Counts how bumpy the "mass of pentominoes" is
    int totalBumpiness = 0;
    for(int i=0;i<_candidateBoard[0].length - 1;i++) {
      totalBumpiness += abs(columnHeight(i) - columnHeight(i+1));
    }
    return totalBumpiness;
  }

  private int heightCount() { //Counts the cumulative height of all the columns
    int totalHeight = 0;
    for(int i=0;i<_candidateBoard.length;i++) {
      totalHeight += _candidateBoard.columnHeight(i);
    }
    return totalHeight;
  }

  private int completeRowCount() { //Counts the complete lines made

  }

  private int heuristicsScorer() {
    return holesWeight*_candidateBoard.holesCount() + bumpinessWeight*_candidateBoard.bumpinessCount() + heightWeight*_candidateBoard.heightCount() + rowWeight*_candidateBoard.completeRowCount();
  }

//Helper
  private int columnHeight(int c) {
    boolean pieceFound = false;
    int colHeight = 0;
    int i = 0;
    while(i<_candidateBoard[0][i].length && !pieceFound) {
      if(_candidateBoard[c][i] = '0') {
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
