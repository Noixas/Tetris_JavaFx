 import java.util.List;
import java.util.LinkedList;
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
  public GameObject(int pX, int pY) {
    xPos = pX;
    yPos = pY;
    _position = new Vector2D(xPos,yPos);
  }
  public void addParent(GameObject parent){
    _parent = parent;
  }
  public void Update() {
    //TODO  Update all of my children first

  }
  public void addChild(GameObject child) {
    _children.add(child);
    child.addParent(this);
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
  public void translate(float xPos, float yPos, float pDistance)
  {
    xPos = xPos * pDistance;
    yPos = yPos * pDistance;
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
