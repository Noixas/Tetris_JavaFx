public class PhysicsPentomino extends Component {
  private Pentomino _pentomino;
	protected float _speed;
  private Board _board;
  private int _tileSize;
  private float _lastBlock;
  private boolean _sideMove;
  private boolean _fallMove;
  private float _moveStep = 1f;
  public PhysicsPentomino(GameObject pOwner, float pSpeed) {
    _owner = pOwner;
    _speed = pSpeed;
    //_speed = 1;
    _pentomino = (Pentomino) _owner;
    _owner.addComponent(this);
    _board = _pentomino.getBoard();
    _tileSize = _pentomino.getTileSize();
    _lastBlock = _pentomino.getY() + _pentomino.getPentominoHeight();
    _sideMove = true;
    _fallMove= true;
  }
  public void fallWorld()
  {
    _owner.worldMove(0, 1, _speed);//Fall in world
  }
  public void move(int pDir)
  {
    _sideMove = _board.tryMove(_pentomino, pDir);
    if(_sideMove){
      Vector2D v = _pentomino.getPivot();
      v.x += pDir; //increase pivot point
      _owner.worldMove(pDir, 0, _pentomino.getTileSize());//move world positiong (For the graphics)
      _board.updatePentominoAtBoard(_pentomino, v);//Update the position in the board
    }
  }
  @Override
  public void Update() {
    if(_pentomino.getRotated()){
      //Do the rotation in here
    //  _lastBlock =  _pentomino.getY()+ _pentomino.getPentominoHeight() ;
      _pentomino.rotationUsed();
    }
    if(checkFallMove())
    {
      _moveStep -= Time.DeltaTime();//Substract the amount of time last frame took
      //fallWorld(); //For smooth falling
      //System.out.println(_moveStep);
      if(_moveStep <=0)
      {
        _owner.worldMove(0, 1, _pentomino.getTileSize());
        _moveStep = _speed;
        Vector2D v = _pentomino.getPivot();
        v.y += 1; //increase pivot point
        _board.updatePentominoAtBoard(_pentomino, v);//Update the position in the board
        _lastBlock = _pentomino.getY() +  _pentomino.getPentominoHeight();//save in which block we are

        System.out.println(_board.toString());
      }
    }else
        _pentomino.setDone();
  }
  private boolean checkFallMove()
  {
    _fallMove =  _board.tryMove(_pentomino, 0);
    return _fallMove;
  }
}
