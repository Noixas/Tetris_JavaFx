public class Vector2D{
  public float x;
  public float y;
  public Vector2D(int pX, int pY)
  {
    x = (int)pX;
    y = (int)pY;
  }
  public Vector2D(float pX, float pY)
  {
    x = pX;
    y = pY;
  }
  public void setXY(float pX, float pY)
  {
    x = pX;
    y = pY;
  }
  public boolean equals(Vector2D other)
  {
    if(x == other.x && y == other.y)
    return true;
    else return false;
  }
  public String toString() {
      return "Vector2D(" + x + ", " + y + ")";
   }

}