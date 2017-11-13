import java.util.List;
import java.util.LinkedList;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;


public class Input {
	private static List<String> input = new LinkedList<String>();
	private static Scene scene;
	private static List<String> pressed = new LinkedList<String>();
	public static void setScene(Scene pScene) {
		scene = pScene;
		scene.setOnKeyPressed(
			new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code = e.getCode().toString();

					if(!pressed.contains(code)) {
						input.add(code);
						System.out.println(code);
						System.out.println(input.size());
					}
				}
			});
		scene.setOnKeyReleased(
			new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code = e.getCode().toString();
					if(pressed.contains(code)) {
						pressed.remove(code);
						System.out.println("release");
					}
				}
			});
	}
	public static boolean keyPressed(String pKey) {
		if(input.contains(pKey)) {
			input.remove(pKey);
			System.out.println(true);
			return true;
		}
		else {
			return false;
		}
	}
	public static void clearInput() {
		for(int i=0; i<input.size(); i++) {
			String news = input.get(i);
			pressed.add(news);
		}
		input.clear();


	}
}
