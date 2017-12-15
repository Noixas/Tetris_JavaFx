import java.util.List;
import java.util.LinkedList;

/**
 * The GameObject class is a parent to most of the other objects interact with the player.
 */
public class GameObject {
  protected GameObject _parent;
  protected List<GameObject> _children = new LinkedList<GameObject>();
  protected List<Component> _components = new LinkedList<Component>();
  protected float xPos;
	protected float yPos;
	protected float speed;
  protected Vector2D _position;
	protected int direct = -1;
  //TODO add list of components and create its addMEthod and a remove method
  //NOTE: All remove methods should be overwritten so we can remove stuff by either index, type or if the objects are equals()
  //TODO removeChild()

  public GameObject() {
    System.out.println("A new game object has been created!");
  }

  /**
   * Constructor which places the object onto the board and applies that to the Vector2D object.
   * @param pX x position of the object.
   * @param pY y position of the object.
   */
  public GameObject(int pX, int pY) {
    xPos = pX;
    yPos = pY;
    _position = new Vector2D(xPos,yPos);
  }

  /**
   * Reference to the parent of this object in the hierarchy
   * @param parent parent
   */
  public void addParent(GameObject parent){
    _parent = parent;
  }

  public void Update() {

  }

  /**
   * Add the child to our children list and inform it who is their father
   * @param child child
   */
  public void addChild(GameObject child) {
    _children.add(child);
    child.addParent(this);
  }

  /**
   * Remove the child to our children list and remove reference to its father
   * @param child child
   */
  public void removeChild(GameObject child)
  {
    child.clearParent();
    _children.remove(child);
  }

  /**
   * Clears the reference to the parent.
   */
  public void clearParent()
  {
    _parent = null;
  }

  public List<GameObject> getAllChildren(List<GameObject> pList)
	{
		for(int i = 0; i < _children.size(); i++)
		{
			_children.get(i).getAllChildren(pList);
			pList.add(_children.get(i));
		}
		return pList;
	}

  public List<GameObject> getChildren()
	{
		return _children;
	}
  public void translate(float pxPos, float pyPos, float pDistance)
  {
    xPos = pxPos * pDistance;
    yPos = pyPos * pDistance;
    _position.setXY(xPos,yPos);
  }
  public void worldMove(float xDir, float yDir, float pDistance)
  {
    xPos += xDir * pDistance;
    yPos += yDir * pDistance;
      _position.setXY(xPos,yPos);
  }
  public void addComponent(Component pComponent)
  {
    _components.add(pComponent);
  }
  public boolean removeComponent(Component pComponent)
  {
    return _components.remove(pComponent);
  }
  public void removeComponent(int pIndex)
  {
     _components.remove(pIndex);
  }
  public List<Component> getComponents()
  {
    return _components;
  }
  public float getX()
	{
		return xPos;
	}
	public float getY()
	{
		return yPos;
	}
  public Vector2D getPosition(){
    return _position;
  }
}
