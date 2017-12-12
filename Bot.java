public class Bot {
  List<Pentomino> boardPreviews = new ArrayList<Pentomino> ();
  Board currentBoard = getBoard();
  public static void startBot() {
    int botInt = 0;
    while(botInt < 10) {
      Input.keyPressed("UP");
      botInt++;
    }
  }


  public static void makeBoardPreview() {
    boardPreviews.add(candidateBoard);

  }

  public static void checkMoveOutcome() {

  }

//Heuristics
  public static int holesCount() {//Counts how many "holes" there are

  }

  public static int bumpinessCount() {//Counts how bumpy the "mass of pentoinoes is"
  for(int i=0;i<currentBoard[0].length;i++) {
    
  }
    //How many sides are open?
    //The difference in height between each column?

  }

  public static int heightCount() { //Counts the cumulative height of all the columns
    int totalHeight = 0;
    for(int i=0;i<currentBoard.length;i++) {
      totalHeight += candidateBoard.columnHeight(i);
    }
    return totalHeight;
  }

  public static int completeLinesCount() {//Counts the complete lines made

  }

//Helper
  public static int columnHeight(int c) {
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
