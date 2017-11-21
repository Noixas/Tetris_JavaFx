public class Score {
  private int _score = 0;
  private int points = 0;

  public Score() {
    if(_done == true) {
      points = 5;
    }


  }
  public void Update() {

  }
  public int getScore() {
    return _score;
  }
  public void addScore(int points) {
    _score += points;
  }
}
