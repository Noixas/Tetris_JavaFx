import java.util.Stack;
import java.util.Random;
/**
 * @author      Giacomo Anerdi
 *  This class creates and object which handles the randomization of the pentomino pool.
 */
/**
 *  This class creates and object which handles the randomization of the pentomino pool.
 */
public class PentominoesPool {
  public int[] pentArray = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
  public Stack<Integer> pentPool = new Stack<Integer> ();

  /**
   *  Calls method newPentPool() as soon as the object is created.
   */
  public PentominoesPool()
  {
    newPentPool();
  }

  /**
   *  A new pool of pentominoes is made randomly using the Fisher-Yates Shuffle.
   */
  public void newPentPool() {
    Random rand = new Random();
    for (int i = pentArray.length - 1; i > 0; i--) {
      int index = rand.nextInt(i+1);
      int a = pentArray[index];
      pentArray[index] = pentArray[i];
      pentArray[i] = a;
      pentPool.push(a);
    }
  }

  /**
   *  Getter for the pool of pentominoes used in the Board class.
   *  @return pentPool returns the pool of pentominoes.
   */
  public Stack<Integer> getPentPool() { //Getter for the pentomino pool
    return pentPool;
  }
}
