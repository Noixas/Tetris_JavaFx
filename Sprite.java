public class Sprite extends GameObject {
	public float xPos;
	public float yPos;
	public int distance;
	protected float speed;
	protected int direct = -1;
	
	public Sprite(int pX, int pY) {
		xPos = pX;
		yPos = pY;
		System.out.println("xPosition " + pX);
	}	
}