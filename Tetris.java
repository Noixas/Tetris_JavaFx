import javafx.application.Application;
import javafx.animation.AnimationTimer;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.canvas.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
public class Tetris extends Application {
	private static final int STAR_COUNT = 500;
	private final Random random = new Random();
	public static final int TILE_SIZE = 40;
	public static final int GRID_WIDTH = 15;
	public static final int GRID_HEIGHT = 20;
	private final Rectangle[] nodes = new Rectangle[STAR_COUNT];
	private final double[] angles = new double[STAR_COUNT];
	private final long[] start = new long[STAR_COUNT];
	private double time;
	public static void main(String[] args) {
		Time.StartTime();
		//testing gits
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
		Pane left = new Pane();
		left.setStyle("-fx-background-color: gray;");
		left.setPrefSize(390,720);
		Pane world = new Pane();
		world.setStyle("-fx-background-color: black;");
		world.setPrefSize(500,720);
		Button button2 = new Button("Button 2");
		GridPane root = new GridPane();

		Pentomino p = new Pentomino(0,0);
		Canvas canvas = new Canvas(500,720);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.add(left, 0, 0, 1, 1);
		root.add(world, 1, 0, 1, 1);

		world.getChildren().addAll(canvas);
		Scene scene = new Scene(root, 1280, 720);
		primaryStage.setScene(scene);
		Rectangle r = new Rectangle(5,5,25,25);
		r.setFill(Color.ORANGE);
		//TODO make it so whenever a pentomino stops, a new one is spawn
		//TODO spawn pentominoes when off limits
		//TODO Add color property to pentomino
		root.getChildren().add(r);
		if(Input.keyPressed("SPACE"))
			System.out.println("SPACE");
			scene.setOnKeyPressed(e -> {
			            if (e.getCode() == KeyCode.SPACE) {
			                p.rotate(1);
			            }
								 else if (e.getCode() == KeyCode.LEFT) {
									p.move(-1);
								} else if (e.getCode() == KeyCode.RIGHT) {
									p.move(1);
								}
								});
		AnimationTimer timer = new AnimationTimer() {
			                       @Override
			                       public void handle(long now) {
				                       time += 0.017;

				                       //  if (time >= 0.5) {
				                       update(r);
				                       render(gc, r);
															 p.Update();
															 p.Render(gc);
				                       time = 0;
				                       //  }
			                       }
		                       };
		timer.start();
		primaryStage.show();
	}
	private void update(Rectangle r)
	{
		r.setTranslateY(r.getTranslateY() + 1);
		//Try move down
	}
	private void render(GraphicsContext gc, Rectangle r)
	{
		gc.clearRect(0, 0, GRID_WIDTH * TILE_SIZE, GRID_HEIGHT * TILE_SIZE);
		gc.fillRect(r.getTranslateX(),
		            r.getTranslateY(),
		            r.getWidth(),
		            r.getHeight());
		System.out.println(r.getTranslateX());
		gc.setFill(Color.PURPLE);
		//	gc.setStroke(Color.BLUE);
	}
}
