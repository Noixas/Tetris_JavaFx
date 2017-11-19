public class PhysicsPentomino extends Component {
  private Pentomino _pentomino;
	protected float _speed;
  private Board _board;
  private int _tileSize;
  private float _lastBlock;
  private boolean _move;
  public PhysicsPentomino(GameObject pOwner, float pSpeed) {
    _owner = pOwner;
    _speed = pSpeed;
    _pentomino = (Pentomino) _owner;
    _owner.addComponent(this);
    _board = _pentomino.getBoard();
    _tileSize = _pentomino.getTileSize();
    _lastBlock = _pentomino.getY() + _pentomino.getHeight();
    _move = true;
  }
  public void fall()
  {
    _pentomino.move(0, 1, _speed);
  }
  public void move(int pDir)
  {
    if(_board.tryMove(_pentomino, pDir))
      _pentomino.move(pDir, 0, _pentomino.getTileSize());
  }
  @Override
  public void Update() {
//TODO: when rotate update the _lastBlock position
   if(_move && (_lastBlock + _tileSize) <= _pentomino.getY() + _pentomino.getHeight())
    {
        System.out.println("_lastBLOCK"+_lastBlock+" GET Y "+ (_pentomino.getY()+ _pentomino.getHeight())+ "Tile SIZE  " + _tileSize);
        _move = _board.tryMove(_pentomino, 0);


      if(_move){
        Vector2D v = _pentomino.getPivot();
        v.y += 1;
        _board.updatePentomino(_pentomino, v);
        _lastBlock = _pentomino.getY()+ _pentomino.getHeight();

      }
      else
      _pentomino.setDone();
        System.out.println(_board.toString());
    }

    if(checkMove())
      fall();
      else
        _pentomino.setDone();
  }
  private boolean checkMove()
  {
    _move=  _board.tryMove(_pentomino, 0);

    return _move;
  }
}
