public class World extends GameObject{

	public static World world;
	public World() {
		world = this;
	}
	public void Update() {
		for(int i=0; i<_children.size(); i++) {
			_children.get(i).Update();
		}
	}
	private void Init()
	{

	}

}
