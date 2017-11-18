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
    _lastBlock = 0;
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
   if(_move && _lastBlock + _tileSize >= _pentomino.getY())
    {
      _move = _board.tryMove(_pentomino, 0);
      _lastBlock = _pentomino.getY();
    }

    if(_move)
      fall();
  }
}
