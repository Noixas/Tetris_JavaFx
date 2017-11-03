public class Sprite extends GameObject {
	protected float xPos;
	protected float yPos;
	protected float speed;
	protected int direct = -1;
	
	public Sprite(int pX, int pY) {
		xPos = pX;
		yPos = pY;
		System.out.println("xPosition " + pX);
	}	
}