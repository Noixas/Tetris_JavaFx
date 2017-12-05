import java.util.Stack;
import java.util.Random;

public class PentominoesPool {
  public int[] pentArray = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
  public Stack<Integer> pentPool = new Stack<Integer> ();
  public PentominoesPool()
  {
    newPentPool();
  }
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
  public Stack<Integer> getPentPool() {
    return pentPool;
  }
}
