import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tetris extends Application {
	public static void main(String[] args) {
		Time.StartTime();
		World world = new World();
		for(int i=0; i<3; i++) {
			Pentomino pent = new Pentomino(5, 10);
			world.addChild(pent);
			pent.xPos = i*100;
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
		Input.updateInput();
	  if(Input.keyPressed("SPACE"))
	  	  System.out.println("SPACE");
	  
		primaryStage.show();
	}
}