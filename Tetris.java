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
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.util.List;
import java.util.LinkedList;
//import java.util.Scanner;
//TODO clean this class and just have relevant information on it
//TODO Just keep the JAVAFX and a world update
public class Tetris extends Application {
	long time = 0;

	public static void main(String[] args) {
		Time.StartTime();
	//	Scanner in = new Scanner(System.in);
		//int n1 = in.nextInt();

		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tetris!");
		int tile_size = 50;
		Pane left = new Pane();
		left.setStyle("-fx-background-color: gray;");
		left.setPrefSize(390,750);
		Pane right = new Pane();
		right.setStyle("-fx-background-color: gray;");
		right.setPrefSize(390,750);
		Pane world = new Pane();
		world.setStyle("-fx-background-color: black;");
		world.setPrefSize(tile_size*10,tile_size*15);
		GridPane root = new GridPane();


		Canvas canvas = new Canvas(500,750);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.add(left, 0, 0, 1, 1);
		root.add(world, 1, 0, 1, 1);
		root.add(right, 2, 0, 1, 1);
		world.getChildren().addAll(canvas);
		Scene scene = new Scene(root, 1280, 750);
		primaryStage.setScene(scene);

		World worldGame = new World(canvas);
		//TODO make it so whenever a pentomino stops, a new one is spawn
		//TODO spawn pentominoes when off limits
		//TODO Add color property to pentomino

		Label gameTimer = new Label();
		gameTimer.relocate(90, 20);
		gameTimer.setStyle("-fx-font: 30 arial;");
		gameTimer.setTextFill(Color.web("#FF00BC"));
		//Style of the gameTimer

		Rectangle timerBox = new Rectangle(250, 40);
		timerBox.setArcWidth(30.0);
		timerBox.setArcHeight(30.0);
		timerBox.setFill(Color.rgb(0, 0, 0, .99));
		timerBox.relocate(82, 18);
		//Style for the gameTimer box

		Label scoreCounter = new Label();
		scoreCounter.setStyle("-fx-font: 30 arial;");
		scoreCounter.setTextFill(Color.web("#FF00BC"));
		scoreCounter.relocate(20, 200);


		right.getChildren().add(timerBox);
		right.getChildren().add(gameTimer);
		right.getChildren().add(scoreCounter);
		//adding gameTimer and timerBox to right pane




		Input.setScene(scene);
		//Loop of the game
		AnimationTimer timer = new AnimationTimer() {
			                       @Override
			                       public void handle(long now) {
				                       time += now;
															 World.world.Update();
															 Time.updateGameTime();
															 gameTimer.setText("Time: " + Time.getGameTime());
															 scoreCounter.setText("Score: " + Score.getScore());
			                       }
		                       };
		timer.start();
		primaryStage.show();
	}
}
