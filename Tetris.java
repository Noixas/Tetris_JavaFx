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
//TODO clean this class and just have relevant information on it
//TODO Just keep the JAVAFX and a world update
public class Tetris extends Application {
	long time = 0;
	public static void main(String[] args) {
		Time.StartTime();
		World world = new World();
		for(int i=0; i<3; i++) {
			Pentomino pent = new Pentomino(5, 10);
			world.addChild(pent);
			pent.xPos = i*100;
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
		GridPane root = new GridPane();

		Pentomino p = new Pentomino(0,0);
		Canvas canvas = new Canvas(500,720);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.add(left, 0, 0, 1, 1);
		root.add(world, 1, 0, 1, 1);

		world.getChildren().addAll(canvas);
		Scene scene = new Scene(root, 1280, 720);
		primaryStage.setScene(scene);
		//TODO make it so whenever a pentomino stops, a new one is spawn
		//TODO spawn pentominoes when off limits
		//TODO Add color property to pentomino
Input.setScene(scene);
			/*scene.setOnKeyPressed(e -> {
			            if (e.getCode() == KeyCode.SPACE) {
			                p.rotate(1);
											System.out.println(time);
			            }
								 else if (e.getCode() == KeyCode.LEFT) {
									p.move(-1);
								} else if (e.getCode() == KeyCode.RIGHT) {
									p.move(1);
								}
							});*/
		//Loop of the game
		AnimationTimer timer = new AnimationTimer() {
			                       @Override
			                       public void handle(long now) {
				                       time += now;
				                       //  if (time >= 0.5) {
															 if(Input.keyPressed("SPACE")) {
																 	p.rotate(1);
																	System.out.println("It works!!!!!!");
															 }
															 World.world.Update();
															 p.Update();
															 p.Render(gc);

				                       //time = 0;
				                       //  }

			                       }
		                       };
		timer.start();
		primaryStage.show();
	}
}
