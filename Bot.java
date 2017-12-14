public class Bot extends GameObject {
  private Pentomino[][] _boardArray;
  private Pentomino _activePentomino;
  private Board _activeBoard;
  private Pentomino[][] _candidateBoardArray;
  private Pentomino _candidatePentomino;
  //private Board _candidateBoard;
  private int boardHeight = _boardArray.length;
  private int boardWidth = _boardArray[0].length;
  private int countx = 0;
  private int county = 0;
  private double holesWeight = 1;
  private double bumpinessWeight = 1;
  private double heightWeight = 1;
  private double rowWeight = 1;
  private boolean newPentomino = true;
  private int moves = 0;
  private int rotation = 0;
  private MoveLog logBest;
  private MoveLog logNew;
  private int rowCombo;

  private Bot(Pentomino[][] activeBoard, Board board) {
    this._boardArray = activeBoard;
    this._activeBoard = board;
  }

  public void Update() {
    this._activePentomino = _activeBoard.getActivePentomino();
    if(newPentomino) {
      newPentomino = false;
      newTrial();
    }
  }

  private boolean makeMove() {
    
  }

  private void newTrial() {
    moves = 0;
    rotation = 0;
    while(!newPentomino) {
      newAttempt();
      if(logBest == null) {
        copyMoveLog();
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
    Pentomino _candidatePentomino = _activePentomino;
    Vector2D newPos = _candidatePentomino.getPivot();
    Vector2D SpawnPos = new Vector2D(newPos.x,newPos.y);
    moveAllToLeft();
    tryPentominoPosition();
    copyPentominoBoard();

    //newPos.x += countx;
    newPos.y += county;
    addPentominoToBoard(_candidatePentomino, newPos);
    //update pent at board
    logNew = new MoveLog(heuristicsScorer(), moves, rotation);
    _candidatePentomino.restartArray();
    _activeBoard.updatePentominoAtBoard(_candidatePentomino,SpawnPos);//reset to spawn pos
  }

  private void tryPentominoPosition() {
    for(int i=0;i<moves;i++) {
      _candidatePentomino.move(1);
    }
    if(!_activeBoard.tryMove(_candidatePentomino, 1)) {
      newPentomino = true;
    }
    moves++;
    //_candidatePentomino.fallAllTheWay();

    while(_activeBoard.tryMove(_candidatePentomino, 0))
    county++;
  }

  private void moveAllToLeft() {
    while(_activeBoard.tryMove(_candidatePentomino, -1)) {
      _candidatePentomino.move(-1);
    }
  }

  private void selectBestScore() {
    if(logBest.getScore() < logNew.getScore()) {
      copyMoveLog();
    } else if(logBest.getScore() == logNew.getScore()) {
      if(Math.random()<0.5) {
        copyMoveLog();
      }
    }
  }

  private double heuristicsScorer() {
    return -(double)holesWeight*holesCount() - (double)bumpinessWeight*bumpinessCount() - (double)heightWeight*heightCount() + (double)rowWeight*rowCombo;
  }

//Heuristics
  private int holesCount() { //Counts how many "holes" there are
    return 0;
  }

  /*public boolean emptySpaceIsolated(char[][] pBoard, int x, int y)
	{
		//char[][] trackEmpty = new char[pBoard.length][pBoard[0].length];
		if(y > 0 && pBoard[x][y-1] == '0')//left
		{
			return false;
		}
		else if(x > 0 && pBoard[x-1][y] == '0')//top
			return false;
		else if(x < pBoard.length-1 && pBoard[x+1][y] == '0')//bottom
			return false;
		else if(y < pBoard[x].length-1 && pBoard[x][y+1] == '0')//right
			return false;
		else
			return true;//backtrack
	}*/

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

//Helper

  private int columnHeight(int c) {
    boolean pieceFound = false;
    int colHeight = 0;
    int i = 0;
    while(i<_boardArray[0].length && !pieceFound) {
      if(_boardArray[c][i] == null) {
        colHeight++;
        i++;
      } else {
        pieceFound = true;
      }
    }
    return colHeight-rowCombo;
  }

  private Pentomino[][] copyPentominoBoard() { //Deep copy of the board representing the pentominoes
    _candidateBoardArray = new Pentomino[boardHeight][boardWidth];
    for(int i=0;i<boardHeight;i++) {
      for(int j=0;i<boardWidth;j++) {
        _candidateBoardArray[i][j] = _boardArray[i][j];
      }
    }
    return _candidateBoardArray;
  }

  private void copyMoveLog() { //Deep copy of moves log object
    double score = logNew.getScore();
    int moves = logNew.getMoves();
    int rotation = logNew.getRotation();
    logBest = new MoveLog(score, moves, rotation);
  }
  public void addPentominoToBoard(Pentomino pPent, Vector2D pVec)
  {
      char[][] pos = pPent.getPentArray();
      for(int i = 0; i < pos.length; i++)
        for(int j = 0; j < pos[0].length; j++)
          if(pos[i][j] != '0')
          {
            _candidateBoardArray[(int)pVec.y+i][(int)pVec.x+j] = pPent;
          }
      pPent.setPivot(pVec);
  }
  public int checkRow()//APROVED
  {
    int counter = 1;
    rowCombo = 0;
    for(int i = 0; i < _candidateBoardArray.length; i++)
      for(int j = 0; j < _candidateBoardArray[0].length; j++)
      {
        if(_candidateBoardArray[i][j] == null)
        {
          j = _candidateBoardArray[0].length;
          counter = 1;
        }
        else counter++;

        if(counter == _candidateBoardArray[0].length){
          eraseRow(i);
          counter = 1;
          rowCombo++;
        }
      }
      return rowCombo;
    }

    public void eraseRow(int pPos)//Aproved
    {
      for(int j = 0; j < _candidateBoardArray[pPos].length; j++)
      {
        _candidateBoardArray[pPos][j].eraseBlock(new Vector2D(j,pPos));//x,y
        _activeBoard.updatePentominoAtBoard(_candidateBoardArray[pPos][j],_candidateBoardArray[pPos][j].getPivot());
        _candidateBoardArray[pPos][j] = null;
      }
    }
}
