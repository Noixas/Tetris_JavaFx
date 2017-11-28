public class Score {
  private static int _score = 0;
  private static int points = 0;

  public Score() {
    /*if(Pentomino.setDone() == true) {
      points = 5;
    }
    */
  //  PentominoObserver.addObserver(this);
  }
  public void Update() {

  }
  public void addScore(int points) {
    _score += points;
  }
  public void Done()
  {
    HighscoreManager newManager = new HighscoreManager();
    HighscoreManager.addHScore(this.getScore());
    HighscoreManager.updateScoreFile();
  }

  public static int getScore() {
    return _score;
  }
  public static void Restart()
  {
    _score = 0;
  }
}
