public class MoveLog {
  public double score;
  public int moves;
  public int rotation;

  public MoveLog(double _score, int _moves, int _rotation) {
    this.score = _score;
    this.moves = _moves;
    this.rotation = _rotation;
  }

  public double getScore() {
    return score;
  }

  public int getMoves() {
    return moves;
  }

  public int getRotation() {
    return rotation;
  }

  public void setScore(double _score) {
    this.score = _score;
  }

  public void setMoves(int _moves) {
    this.moves = _moves;
  }

  public void setRotation(int _rotation) {
    this.rotation = _rotation;
  }

}
