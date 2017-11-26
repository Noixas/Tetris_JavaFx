import java.io.Serializable;

public class HScore implements Serializable {

  private int hScore;

  public int getHScore() {
    return hScore;
  }

  public HScore(int hScore) {
    this.hScore = hScore;
  }
}
//This class makes it possible to have a highscorelist
