public class GameLoop extends AnimationTimer {
	public void handle(long now) {
		final double width = 0.5 * primaryStage.getWidth();
		final double height = 0.5 * primaryStage.getHeight();
		final double radius = Math.sqrt(2) * Math.max(width, height);
		for (int i=0; i<STAR_COUNT; i++) {
			final Node node = nodes[i];
			final double angle = angles[i];
			final long t = (now - start[i]) % 2000000000;
			final double d = t * radius / 2000000000.0;
			node.setTranslateX(Math.cos(angle) * d + width);
			node.setTranslateY(Math.sin(angle) * d + height);
		}
	}
}