public class Time {
	public static float startTime;
	public static float deltaTime;
	public static float gameTime;
	public static void updateDeltaTime() {

		deltaTime = System.nanoTime() - startTime;
		deltaTime = deltaTime / 1000000000;
	}

	public static void StartTime() {
		startTime = System.nanoTime();
		System.out.println("This is startTime: " + startTime);
	}

	public static void updateGameTime() {
		gameTime = System.nanoTime() - startTime;
	}

	public static float getGameTime() {
		return gameTime / 1000000000;
	}

}
