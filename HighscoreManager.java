import java.io.*;
import java.util.*;

public class HighscoreManager {
  private static ArrayList<HScore> hScores;

  private static final String HIGHSCORE_FILE = "hScores.dat";
  //Creation of the highscores file, where the highscores are stored

  static ObjectOutputStream scoreOutputStream = null;
  static ObjectInputStream scoreInputStream = null;

  public HighscoreManager() {
    hScores = new ArrayList<HScore>();
  }

  public static ArrayList<HScore> getHScores() {
    loadScoreFile();
    sort();
    return hScores;
  }
  //Method that returns a sorted highscore arraylist

  private static void sort() {
    HScoreComparator comparator = new HScoreComparator();
    Collections.sort(hScores, comparator);
  }
  //Method for sorting the ArrayList

  public static void addHScore(int hScore) {
    loadScoreFile();
    hScores.add(new HScore(hScore));
    updateScoreFile();
  }
  //adding a score to the hScores.dat file

  public static void loadScoreFile() {
    try {
      scoreInputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
      hScores = (ArrayList<HScore>) scoreInputStream.readObject();
    }
    catch (FileNotFoundException e) {
      System.out.println("[LOAD] FNF error: " + e.getMessage());
    }
    catch (IOException e) {
      System.out.println("[LOAD] IO error: " + e.getMessage());
    }
    catch (ClassNotFoundException e) {
      System.out.println("[LOAD] CNF error: " + e.getMessage());
    }
    finally {
      try {
        if (scoreOutputStream != null) {
          scoreOutputStream.flush();
          scoreOutputStream.close();
        }
      }
      catch (IOException e) {
        System.out.println("[LOAD] IO error: " + e.getMessage());
      }
    }
  }
  //this function loads the arraylist in the file and puts it in the hScores-arraylist

  public static void updateScoreFile() {
    try {
      scoreOutputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
      scoreOutputStream.writeObject(hScores);
    }
    catch (FileNotFoundException e) {
      System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
    }
    catch (IOException e) {
      System.out.println("[Update] IO Error: " + e.getMessage());
    }
    finally {
      try {
        if (scoreOutputStream != null) {
          scoreOutputStream.flush();
          scoreOutputStream.close();
        }
      }
      catch (IOException e) {
        System.out.println("[Update] Error: " + e.getMessage());
      }
    }
  }
  //this funcion is like the one above, it is just the other way around, it writes the hScores-arraylist to the file

  public static String getHScoreString() {
    String hScoreString = "";
    int max = 10;

    ArrayList<HScore> hScores;
    hScores = getHScores();

    int i = 0;
    int x = hScores.size();
    if (x > max) {
      x = max;
    }
    while (i < x) {
      hScoreString += (i + 1) + ".\t" + hScores.get(i).getHScore() + "\n";
      i++;
    }
    return hScoreString;
  }
  //method that returns all the highscores in the hScores-arraylist
  public static void init() {
    hScores = new ArrayList<HScore>();
  }

  public static void clearArray() {
    hScores = new ArrayList<HScore>();
  }
}
