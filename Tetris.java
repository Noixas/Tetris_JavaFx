import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tetris extends Application {
	public static void main(String[] args) {
		Time.StartTime();
		World world = new World();
		for(int i=0; i<10; i++) {
			Pentomino pent = new Pentomino(5, 10);
			world.addChild(pent);
		}
		for(int i=0; i<100; i++) {
			Time.updateDeltaTime();
			world.Update();
		}
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tetris!");
	  
		primaryStage.show();
	}
}