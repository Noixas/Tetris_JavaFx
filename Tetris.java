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
		left.setStyle("-fx-background-color: #357dff");
		left.setPrefSize(390,750);
		Pane right = new Pane();
		right.setStyle("-fx-background-color: #357dff");
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
		gameTimer.setStyle("-fx-font: 30 arial;");
		gameTimer.setTextFill(Color.web("#FF0000"));
		gameTimer.relocate(75, 192);
		//Style of the gameTimer

		Rectangle timerBox = new Rectangle(250, 40);
		timerBox.setArcWidth(25.0);
		timerBox.setArcHeight(25.0);
		timerBox.setFill(Color.rgb(0, 0, 0, .99));
		timerBox.relocate(70, 190);
		timerBox.setStroke(Color.rgb(255, 0, 0, .99));
		timerBox.setStrokeWidth(3);
		//Style for the gameTimer box

		Label scoreCounter = new Label();
		scoreCounter.setStyle("-fx-font: 30 arial;");
		scoreCounter.setTextFill(Color.web("#ff0000"));
		scoreCounter.relocate(75, 252);
		//Style of scoreCounter

		Rectangle scoreBox = new Rectangle(250, 40);
		scoreBox.setArcWidth(25.0);
		scoreBox.setArcHeight(25.0);
		scoreBox.setFill(Color.rgb(0, 0, 0, .99));
		scoreBox.relocate(70, 250);
		scoreBox.setStroke(Color.rgb(255, 0, 0, .99));
		scoreBox.setStrokeWidth(3);
		//Style for the scoreCounter scoreBox

		Rectangle preview = new Rectangle(250, 150);
		preview.setArcWidth(25.0);
		preview.setArcHeight(25.0);
		preview.setFill(Color.rgb(0, 0, 0, .99));
		preview.relocate(70, 20);
		preview.setStroke(Color.rgb(255, 0, 0, .99));
		preview.setStrokeWidth(3);
		//Style for the pentominopreviewer

		Rectangle highscore = new Rectangle(250, 420);
		highscore.setArcWidth(25.0);
		highscore.setArcHeight(25.0);
		highscore.setFill(Color.rgb(0, 0, 0, .99));
		highscore.relocate(70, 310);
		highscore.setStroke(Color.rgb(255, 0, 0, .99));
		highscore.setStrokeWidth(3);
		Label highscoreLabel = new Label();
		highscoreLabel.setText("Highscores");
		highscoreLabel.setStyle("-fx-font: 30 arial;");
		highscoreLabel.setTextFill(Color.rgb(255, 0, 0, .99));
		highscoreLabel.relocate(75, 312);
		Rectangle highscoreLine = new Rectangle(250, 3);
		highscoreLine.setFill(Color.rgb(255, 0, 0, .40));
		highscoreLine.relocate(70, 350);
		Label highscores = new Label();
		highscores.setText(
			" 1. \n" +
			" 2. \n" +
			" 3. \n" +
			" 4. \n" +
			" 5. \n" +
			" 6. \n" +
			" 7. \n" +
			" 8. \n" +
			" 9. \n" +
			"10. \n");
		highscores.setStyle("-fx-font: 30 arial;");
		highscores.setTextFill(Color.rgb(255, 0, 0, .60));
		highscores.relocate(75, 354);


		right.getChildren().addAll(scoreBox, timerBox, gameTimer, scoreCounter, preview, highscore, highscoreLabel, highscoreLine, highscores);


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
