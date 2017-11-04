import javafx.application.Application;
import javafx.animation.AnimationTimer;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
public class Tetris extends Application {
	private static final int STAR_COUNT = 500;
 private final Random random = new Random();

	private final Rectangle[] nodes = new Rectangle[STAR_COUNT];
	private final double[] angles = new double[STAR_COUNT];
	private final long[] start = new long[STAR_COUNT];
	public static void main(String[] args) {
		Time.StartTime();
		World world = new World();
		for(int i=0; i<3; i++) {
			Pentomino pent = new Pentomino(5, 10);
			world.addChild(pent);
			pent.xPos = i*100;
		}
		for(int i=0; i<1; i++) {
			Time.updateDeltaTime();
			world.Update();


		}
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tetris!");
		//Input.updateInput();
		for (int i=0; i<500; i++) {
			nodes[i] = new Rectangle(1, 1, Color.WHITE);
			angles[i] = 2.0 * Math.PI * random.nextDouble();
			start[i] = random.nextInt(2000000000);
		}
		
		Group root = new Group(nodes);
		final Scene scene = new Scene(root, 800, 600, Color.PURPLE);
        primaryStage.setScene(scene);
        Rectangle r = new Rectangle(5,5,50,50);
        r.setFill(Color.BLUE);
        
		root.getChildren().add(r);
		if(Input.keyPressed("SPACE"))
			System.out.println("SPACE");
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				final double width = 0.5 * primaryStage.getWidth();
				final double height = 0.5 * primaryStage.getHeight();
				final double radius = Math.sqrt(2) * Math.max(width, height);
				r.setTranslateY(r.getTranslateY() + 0.5f);
				for (int i=0; i<STAR_COUNT; i++) {
					final Node node = nodes[i];
					final double angle = angles[i];
					final long t = (now - start[i]) % 2000000000;
					final double d = t * radius / 2000000000.0;
					node.setTranslateX(Math.cos(angle) * d + width);
					node.setTranslateY(Math.sin(angle) * d + height);
				}
			}
		}.start();
		primaryStage.show();
	}
}