import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.event.*;

public class MenuUI {
  private static Button startGame;
  private static Button quitGame;

  public MenuUI(Pane mainPane) {
    createStartButton(mainPane);
    createLogo(mainPane);
    createQuitButton(mainPane);
  }

  private void createStartButton(Pane mainPane) {
    startGame = new Button();
    startGame.setText("Start Game");
    startGame.setPrefSize(200, 50);
    startGame.relocate(100, 150);
    startGame.setVisible(false);
    mainPane.getChildren().add(startGame);
  }

  private void createQuitButton(Pane mainPane) {
    Button quitGame = new Button();
    quitGame.setText("Quit");
    quitGame.setPrefSize(200, 50);
    quitGame.relocate(100, 250);
    mainPane.getChildren().add(quitGame);
  }

  private void createLogo(Pane mainPane) {
    Label logo = new Label();
    logo.setText("Tetris");
    logo.setStyle("-fx-font: 80 arial;");
    logo.setTextFill(Color.rgb(255, 0, 0, .99));
    logo.relocate(100, 30);


    mainPane.getChildren().add(logo);
  }

  public static void init() {
    startGame = new Button();
    quitGame = new Button();
  }
}
