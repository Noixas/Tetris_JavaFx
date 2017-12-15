/**
 *  This class handels the inputs of the player and applies them to the pentomino.
 */
public class InputPentomino extends Component {
  private Pentomino _pentomino;

  
  /**
   *  Constructor which adds the current active pentomino to the player input.
   *  @param pOwner pOwner the current pentomino.
   */
  public InputPentomino(GameObject pOwner) {
    _owner = pOwner;
    _pentomino = (Pentomino) _owner;
    _owner.addComponent(this);
  }
  @Override

  /**
   *  Update method which checks what button has been Pressed.
   */
  public void Update() {
    if(Input.keyPressed("UP")) {
       _pentomino.rotate(1);
    }
    else if(Input.keyPressed("DOWN")) {
       _pentomino.rotate(-1);
    }
    if(Input.keyPressed("LEFT")) {
       _pentomino.move(-1);
    }
    else if(Input.keyPressed("RIGHT")) {
       _pentomino.move(1);
    }
    if(Input.keyPressed("SPACE"))
    {
      _pentomino.fallAllTheWay();
    }
  }
}
