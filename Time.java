public class Time {
	public static float startTime;
	public static float deltaTime;
	public static void updateDeltaTime() {
		
		deltaTime = System.nanoTime() - startTime;
		deltaTime = deltaTime / 1000000000;
	}
	
	public static void StartTime() {
		startTime = System.nanoTime();
	}
}