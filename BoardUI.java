import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class BoardUI {

  Label gameTimer;
  Label scoreCounter;

  public BoardUI(Pane rightPane) {
    createPreview(rightPane);
    createTimer(rightPane);
    createCounter(rightPane);
    createHighscore(rightPane);
  }

  private void createPreview(Pane rightPane) {
    Rectangle preview = new Rectangle(250, 150);
		preview.setArcWidth(25.0);
		preview.setArcHeight(25.0);
		preview.setFill(Color.rgb(0, 0, 0, .99));
		preview.relocate(70, 20);
		preview.setStroke(Color.rgb(255, 0, 0, .99));
		preview.setStrokeWidth(3);
    rightPane.getChildren().add(preview);
  }


  private void createTimer(Pane rightPane) {
    Rectangle timerBox = new Rectangle(250, 40);
		timerBox.setArcWidth(25.0);
		timerBox.setArcHeight(25.0);
		timerBox.setFill(Color.rgb(0, 0, 0, .99));
		timerBox.relocate(70, 190);
		timerBox.setStroke(Color.rgb(255, 0, 0, .99));
		timerBox.setStrokeWidth(3);

    gameTimer = new Label();
		gameTimer.setStyle("-fx-font: 30 arial;");
		gameTimer.setTextFill(Color.web("#FF0000"));
		gameTimer.relocate(75, 192);
    rightPane.getChildren().addAll(timerBox, gameTimer);
  }

  public void updateTime() {
    gameTimer.setText("Time: " + Time.getGameTime());
  }


  private void createCounter(Pane rightPane) {
    Rectangle scoreBox = new Rectangle(250, 40);
    scoreBox.setArcWidth(25.0);
    scoreBox.setArcHeight(25.0);
    scoreBox.setFill(Color.rgb(0, 0, 0, .99));
    scoreBox.relocate(70, 250);
    scoreBox.setStroke(Color.rgb(255, 0, 0, .99));
    scoreBox.setStrokeWidth(3);

    scoreCounter = new Label();
    scoreCounter.setStyle("-fx-font: 30 arial;");
    scoreCounter.setTextFill(Color.web("#ff0000"));
    scoreCounter.relocate(75, 252);
    rightPane.getChildren().addAll(scoreBox, scoreCounter);
  }

  public void updateCounter() {
    scoreCounter.setText("Score: " + Score.getScore());
  }


  private void createHighscore(Pane rightPane) {
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

		highscores.setText(HighscoreManager.getHScoreString());
		highscores.setStyle("-fx-font: 30 arial;");
		highscores.setTextFill(Color.rgb(255, 0, 0, .60));
		highscores.relocate(75, 354);

    rightPane.getChildren().addAll(highscore, highscoreLabel, highscoreLine, highscores);
  }
}
