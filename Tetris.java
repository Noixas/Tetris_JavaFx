

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
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.LinkedList;
import javafx.event.*;
//import java.util.Scanner;
//TODO clean this class and just have relevant information on it
//TODO Just keep the JAVAFX and a world update
/**
 * Main class which calls all the graphical and logical aspects of the game.
 */
public class Tetris extends Application {
	long time = 0;

	/**
	 * Main method which starts the timer
	 */
	public static void main(String[] args) {
		Time.StartTime();
		HighscoreManager.init();
	//	Scanner in = new Scanner(System.in);
		//int n1 = in.nextInt();
		launch(args);
	}

	@Override
	/**
	 * 	Method that creates the window.
	 *	@param primaryStage main stage
	 */
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tetris!");
		GridPane root = new GridPane();
		Scene scene = new Scene(root, 1030, 750);
		primaryStage.setScene(scene);
		World worldGame = new World(root);
		Input.setScene(scene);

	//Loop of the game
		AnimationTimer timer = new AnimationTimer() {
			                       @Override
			                       public void handle(long now) {
				                       time += now;
															 World.world.Update();
															 Time.updateGameTime();
															// System.out.println(Time.getGameTime());
			                       }
		                       };
		timer.start();
		primaryStage.show();
	}
}
