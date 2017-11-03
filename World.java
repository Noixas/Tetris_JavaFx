public class World extends GameObject{
  public World() {

  }
  public void Update() {
    for(int i=0; i<_children.size(); i++) {
      _children.get(i).Update();
    }
  }
}
