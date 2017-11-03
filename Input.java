import java.util.List;
import java.util.LinkedList;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;


public class Input {
	private static List<String> input = new LinkedList<String>();
	private static Scene scene;
	public static void setScene(Scene pScene) {
		scene = pScene;
	}
	public static boolean keyPressed(String pKey) {
		if(input.contains(pKey)) {
			input.remove(pKey);
			return true;
		}
		else {
			return false;
		}
	}
	public static void updateInput() {
		scene.setOnKeyPressed(
			new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code = e.getCode().toString();
					
					if(!input.contains(code)) {
						input.add(code);
					}
				}
			});
		scene.setOnKeyReleased(
			new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code = e.getCode().toString();
					if(input.contains(code)) {
						input.remove(code);
					}
				}
			});
		
	}
}