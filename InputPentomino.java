public class InputPentomino extends Component {
  private Pentomino _pentomino;
  public InputPentomino(GameObject pOwner) {
    _owner = pOwner;
    _pentomino = (Pentomino) _owner;
    _owner.addComponent(this);
  }
  @Override
  public void Update() {
    if(Input.keyPressed("UP")) {
       _pentomino.rotate(1);
    }
    if(Input.keyPressed("DOWN")) {
       _pentomino.rotate(-1);
    }
    if(Input.keyPressed("LEFT")) {
       _pentomino.move(-1);
    }
    else if(Input.keyPressed("RIGHT")) {
       _pentomino.move(1);
       System.out.println(_owner);
       //System.exit(0);
    }
    if(Input.keyPressed("SPACE"))
    {
      _pentomino.fallAllTheWay();
    }
  }
}
