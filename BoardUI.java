import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
/**
 * Contains the UI elements of board class
 */
public class BoardUI extends GameObject {

  Label gameTimer;
  Label scoreCounter;
  Board _board;
  private static final int TILE_SIZE = 25;
  private char[][] _pent;
  private ArrayList<Rectangle> rectList = new ArrayList<Rectangle>();


  public BoardUI(Pane rightPane, Pane leftPane, Board pBoard) {
    _board = pBoard;
    createPreview(rightPane);
    createTimer(rightPane);
    createCounter(rightPane);
    createHighscore(rightPane);
    leftControl(leftPane);
    rightControl(leftPane);
    upControl(leftPane);
    downControl(leftPane);
    spaceControl(leftPane);
  }

  public void updateBoard(Board pBoard) {
    _board = pBoard;
  }

  public void updatePreview() {
    _pent = Piece.getPiece(_board.getPreviewInt());
    int count =0;
    for(int i = 0; i < _pent[0].length; i++) {
      for(int j = 0; j < _pent.length; j++) {
        if(_pent[j][i] != '0'){

          rectList.get(count).relocate(160 + (TILE_SIZE * i), 40 + (TILE_SIZE * j));
          //rectList.get(j).relocate(170 + (TILE_SIZE * j), 50 + (TILE_SIZE * i));
          count++;
        }
      }
    }
  }

  private void createPreview(Pane rightPane) {
    Rectangle preview = new Rectangle(250, 150);
		preview.setArcWidth(25.0);
		preview.setArcHeight(25.0);
		preview.setFill(Color.rgb(0, 0, 0, .99));
		preview.relocate(70, 20);
		preview.setStroke(Color.rgb(255, 0, 0, .99));
		preview.setStrokeWidth(3);


    for(int i = 0; i < 5; i++) {
      rectList.add(new Rectangle(TILE_SIZE, TILE_SIZE));
      rectList.get(i).setFill(Color.rgb(255, 0, 0, .99));
      rectList.get(i).setStroke(Color.rgb(255, 255, 255, .99));
    }


    rightPane.getChildren().addAll(preview, rectList.get(0), rectList.get(1), rectList.get(2), rectList.get(3), rectList.get(4));
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
  public void Update()
  {
    updateTime();
    updateCounter();
    updatePreview();
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


  private void leftControl(Pane leftPane) {
    Rectangle leftArrowBox = new Rectangle(50, 50);
    leftArrowBox.setArcWidth(12.5);
    leftArrowBox.setArcHeight(12.5);
    leftArrowBox.setFill(Color.rgb(0, 0, 0, .99));
    leftArrowBox.relocate(30, 30);
    leftArrowBox.setStroke(Color.rgb(255, 0, 0, .99));
    leftArrowBox.setStrokeWidth(3);

    Label leftArrow = new Label();
    leftArrow.setText("<");
    leftArrow.setStyle("-fx-font: 48 arial;");
    leftArrow.setTextFill(Color.rgb(255, 0, 0, .80));
    leftArrow.relocate(38, 28);

    Rectangle leftDescBox = new Rectangle(200, 50);
    leftDescBox.setArcWidth(12.5);
    leftDescBox.setArcHeight(12.5);
    leftDescBox.setFill(Color.rgb(0, 0, 0, .99));
    leftDescBox.relocate(110, 30);
    leftDescBox.setStroke(Color.rgb(255, 0, 0, .99));
    leftDescBox.setStrokeWidth(3);

    Label leftDesc = new Label();
    leftDesc.setText("Move left");
    leftDesc.setStyle("-fx-font: 40 arial");
    leftDesc.setTextFill(Color.rgb(255, 0, 0, .80));
    leftDesc.relocate(114, 30);

    leftPane.getChildren().addAll(leftArrowBox, leftArrow, leftDescBox, leftDesc);
  }

  private void rightControl(Pane leftPane) {
    Rectangle rightArrowBox = new Rectangle(50, 50);
    rightArrowBox.setArcWidth(12.5);
    rightArrowBox.setArcHeight(12.5);
    rightArrowBox.setFill(Color.rgb(0, 0, 0, .99));
    rightArrowBox.relocate(30, 90);
    rightArrowBox.setStroke(Color.rgb(255, 0, 0, .99));
    rightArrowBox.setStrokeWidth(3);

    Label rightArrow = new Label();
    rightArrow.setText(">");
    rightArrow.setStyle("-fx-font: 48 arial;");
    rightArrow.setTextFill(Color.rgb(255, 0, 0, .80));
    rightArrow.relocate(38, 88);

    Rectangle rightDescBox = new Rectangle(200, 50);
    rightDescBox.setArcWidth(12.5);
    rightDescBox.setArcHeight(12.5);
    rightDescBox.setFill(Color.rgb(0, 0, 0, .99));
    rightDescBox.relocate(110, 90);
    rightDescBox.setStroke(Color.rgb(255, 0, 0, .99));
    rightDescBox.setStrokeWidth(3);

    Label rightDesc = new Label();
    rightDesc.setText("Move right");
    rightDesc.setStyle("-fx-font: 40 arial");
    rightDesc.setTextFill(Color.rgb(255, 0, 0, .80));
    rightDesc.relocate(114, 90);

    leftPane.getChildren().addAll(rightArrowBox, rightArrow, rightDescBox, rightDesc);
  }

  private void upControl(Pane leftPane) {
    Rectangle upArrowBox = new Rectangle(50, 50);
    upArrowBox.setArcWidth(12.5);
    upArrowBox.setArcHeight(12.5);
    upArrowBox.setFill(Color.rgb(0, 0, 0, .99));
    upArrowBox.relocate(30, 150);
    upArrowBox.setStroke(Color.rgb(255, 0, 0, .99));
    upArrowBox.setStrokeWidth(3);

    Label upArrow = new Label();
    upArrow.setText("^");
    upArrow.setStyle("-fx-font: 48 arial;");
    upArrow.setTextFill(Color.rgb(255, 0, 0, .80));
    upArrow.relocate(42, 155);

    Rectangle upDescBox = new Rectangle(200, 50);
    upDescBox.setArcWidth(12.5);
    upDescBox.setArcHeight(12.5);
    upDescBox.setFill(Color.rgb(0, 0, 0, .99));
    upDescBox.relocate(110, 150);
    upDescBox.setStroke(Color.rgb(255, 0, 0, .99));
    upDescBox.setStrokeWidth(3);

    Label upDesc = new Label();
    upDesc.setText("Rotate");
    upDesc.setStyle("-fx-font: 40 arial");
    upDesc.setTextFill(Color.rgb(255, 0, 0, .80));
    upDesc.relocate(114, 150);

    leftPane.getChildren().addAll(upArrowBox, upArrow, upDescBox, upDesc);
  }

  private void downControl(Pane leftPane) {
    Rectangle downArrowBox = new Rectangle(50, 50);
    downArrowBox.setArcWidth(12.5);
    downArrowBox.setArcHeight(12.5);
    downArrowBox.setFill(Color.rgb(0, 0, 0, .99));
    downArrowBox.relocate(30, 210);
    downArrowBox.setStroke(Color.rgb(255, 0, 0, .99));
    downArrowBox.setStrokeWidth(3);

    Label downArrow = new Label();
    downArrow.setText("v");
    downArrow.setStyle("-fx-font: 40 arial;");
    downArrow.setTextFill(Color.rgb(255, 0, 0, .80));
    downArrow.relocate(44, 210);

    Rectangle downDescBox = new Rectangle(200, 50);
    downDescBox.setArcWidth(12.5);
    downDescBox.setArcHeight(12.5);
    downDescBox.setFill(Color.rgb(0, 0, 0, .99));
    downDescBox.relocate(110, 210);
    downDescBox.setStroke(Color.rgb(255, 0, 0, .99));
    downDescBox.setStrokeWidth(3);

    Label downDesc = new Label();
    downDesc.setText("Rotate");
    downDesc.setStyle("-fx-font: 40 arial");
    downDesc.setTextFill(Color.rgb(255, 0, 0, .80));
    downDesc.relocate(114, 210);

    leftPane.getChildren().addAll(downArrowBox, downArrow, downDescBox, downDesc);
  }



  private void spaceControl(Pane leftPane) {
    Rectangle spaceBox = new Rectangle(50, 50);
    spaceBox.setArcWidth(12.5);
    spaceBox.setArcHeight(12.5);
    spaceBox.setFill(Color.rgb(0, 0, 0, .99));
    spaceBox.relocate(30, 270);
    spaceBox.setStroke(Color.rgb(255, 0, 0, .99));
    spaceBox.setStrokeWidth(3);

    Label space = new Label();
    space.setText("\u2423");
    space.setStyle("-fx-font: 48 arial;");
    space.setTextFill(Color.rgb(255, 0, 0, .80));
    space.relocate(30, 256);

    Rectangle spaceDescBox = new Rectangle(200, 50);
    spaceDescBox.setArcWidth(12.5);
    spaceDescBox.setArcHeight(12.5);
    spaceDescBox.setFill(Color.rgb(0, 0, 0, .99));
    spaceDescBox.relocate(110, 270);
    spaceDescBox.setStroke(Color.rgb(255, 0, 0, .99));
    spaceDescBox.setStrokeWidth(3);

    Label spaceDesc = new Label();
    spaceDesc.setText("Set down");
    spaceDesc.setStyle("-fx-font: 40 arial");
    spaceDesc.setTextFill(Color.rgb(255, 0, 0, .80));
    spaceDesc.relocate(114, 270);

    leftPane.getChildren().addAll(spaceBox, space, spaceDescBox, spaceDesc);
  }
}
