/*public class Bot extends GameObject {
  private Pentomino[][] _boardArray;
  private Pentomino _activePentomino;
  private Board _activeBoard;
  private Pentomino _candidatePentomino;
  private Board _candidateBoard;
  private int boardHeight = _boardArray.length;
  private int boardWidth = _boardArray[0].length;
  private double holesWeight;
  private double bumpinessWeight;
  private double heightWeight;
  private double rowWeight;
  private boolean startNewTrial = true;
  private int moves = 0;
  private int rotation = 0;
  private MoveLog logBest;
  private MoveLog logNew;


  private Bot(Pentomino[][] activeBoard, Board board) {
    this._boardArray = activeBoard;
    this._activeBoard = board;
  }

  public void Update() {
    this._activePentomino = _activeBoard.getActivePentomino();
    if(startNewTrial) {
      startNewTrial = false;
    }
  }



  private void newTrial() {
    moves = 0;
    rotation = 0;
    while(!startNewTrial) {
      newAttempt();
      if(moves==0) {
        logBest = logNew;
      } else {
        selectBestScore();
      }
    }
    _candidatePentomino.rotate(logBest.getRotation());
    for(int i=0;i<=logBest.getMoves();i++) {
      _candidatePentomino.move(1);
    }
  }

  private void newAttempt() {
    Board _candidateBoard = (Board)((Board)_activeBoard).clone();
    Pentomino _candidatePentomino = (Pentomino)((Pentomino)_activePentomino).clone();
    moveAllToLeft();
    tryPentominoPosition();
    logNew = new MoveLog(heuristicsScorer(), moves, rotation);
  }

  private void tryPentominoPosition() {
    for(int i=0;i<=moves;i++) {
      _candidatePentomino.move(1);
    }
    if(!_candidateBoard.tryMove(_candidatePentomino, 1)) {
      startNewTrial = true;
    }
    _candidatePentomino.fallAllTheWay();
  }

  private void moveAllToLeft() {
    while(_activeBoard.tryMove(_candidatePentomino, -1)) {
      _candidatePentomino.move(-1);
    }
    _candidatePentomino.fallAllTheWay();
  }

  private void selectBestScore() {
    if(logBest.getScore() < logNew.getScore()) {
      logBest = logNew;
    } else if(logBest.getScore() == logNew.getScore()) {
      double rnd = Math.random();
      if(Math.random()<0.5) {
        logBest = logNew;
      }
    }
  }

  private double heuristicsScorer() {
    return (double)holesWeight*holesCount() + (double)bumpinessWeight*bumpinessCount() + (double)heightWeight*heightCount() + (double)rowWeight*completeRowCount();
  }

//Heuristics
  private int holesCount() { //Counts how many "holes" there are

  }

  private int bumpinessCount() { //Counts how bumpy the "mass of pentominoes" is
    int totalBumpiness = 0;
    for(int i=0;i<_boardArray[0].length - 1;i++) {
      totalBumpiness += Math.abs(columnHeight(i) - columnHeight(i+1));
    }
    return totalBumpiness;
  }

  private int heightCount() { //Counts the cumulative height of all the columns
    int totalHeight = 0;
    for(int i=0;i<_boardArray.length;i++) {
      totalHeight += columnHeight(i);
    }
    return totalHeight;
  }

  private double completeRowCount() { //Counts the complete lines made

  }

//Helper
  private int columnHeight(int c) {
    boolean pieceFound = false;
    int colHeight = 0;
    int i = 0;
    while(i<_boardArray[0].length && !pieceFound) {
      if(_boardArray[c][i] == '0') {
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
