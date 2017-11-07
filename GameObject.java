import java.util.List;
import java.util.LinkedList;
public class GameObject {
  protected GameObject _parent;
  protected List<GameObject> _children = new LinkedList<GameObject>();
  protected float xPos;
	protected float yPos;
	protected float speed;
	protected int direct = -1;
  public GameObject() {
    System.out.println("A new game object has been created!");
  }
  public GameObject(int pX, int pY) {
    xPos = pX;
    yPos = pY;
  }
  public void addParent(GameObject parent){
    _parent = parent;
  }
  public void Update() {

  }
  public void addChild(GameObject child) {
    _children.add(child);
    child.addParent(this);
  }
}
