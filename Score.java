public class Score {
  private static int _score = 0;
  private static int points = 0;

  public Score() {
    /*if(Pentomino.setDone() == true) {
      points = 5;
    }
    */

  }
  public void Update() {

  }

  public void addScore(int points) {
    _score += points;
  }

  public static int getScore() {
    return _score;
  }
}
