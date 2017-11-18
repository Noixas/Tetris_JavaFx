public class PhysicsPentomino extends Component {
  private Pentomino _pentomino;
	protected float _speed;
  public PhysicsPentomino(GameObject pOwner, float pSpeed) {
    _owner = pOwner;
    _speed = pSpeed;
    _pentomino = (Pentomino) _owner;
    _owner.addComponent(this);
  }
  public void fall()
  {
    _pentomino.move(0, -1, _speed);
  }
  public void move(int pDir)
  {
    _pentomino.move(pDir, 0, _pentomino.getTileSize());
  }
  @Override
  public void Update() {
      fall();
  }
}
