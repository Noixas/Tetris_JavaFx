public class Pentomino extends Sprite {
	public Pentomino(int pX, int pY) {
		super(pX, pY);
		speed = 5.0f;
		System.out.println("A new pentomino has been created!");
	}
	public void Update() {
		move();		
		System.out.println("My new position is " + yPos);
		
    }
	public void move() {
		System.out.println(Time.deltaTime);
		yPos = yPos + speed * Time.deltaTime;
	}
}
