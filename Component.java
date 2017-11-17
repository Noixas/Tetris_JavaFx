//TODO:Make component an interface
//Create Physics, input and Graphics component
public abstract class Component
{
  protected GameObject _owner;
  public void setOwner(GameObject pOwner)
  {
    _owner = pOwner;
  }
  public abstract void Update();

};
