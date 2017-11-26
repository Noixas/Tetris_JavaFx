import java.util.Comparator;

public class HScoreComparator implements Comparator<HScore> {
  public int compare(HScore hScore1, HScore hScore2) {

    int hs1 = hScore1.getHScore();
    int hs2 = hScore2.getHScore();

    if(hs1 > hs2) {
      return -1;
    }
    else if(hs1 < hs2) {
      return +1;
    }
    else {
      return 0;
    }
  }
}
//This class is used to compare scores so a higher score will stand higher on the highscorelist
