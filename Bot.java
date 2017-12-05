public class Bot {
  public static void startBot() {
    int botInt = 0;
    while(botInt < 10) {
      Input.keyPressed("UP");
      botInt++;
    }
  }
}
