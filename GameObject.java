import java.util.List;
import java.util.LinkedList;
public class GameObject {
  protected GameObject _parent;
  protected List<GameObject> _children = new LinkedList<GameObject>();
  public GameObject() {
    System.out.println("A new game object has been created!");
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
