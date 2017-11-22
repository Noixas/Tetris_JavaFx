import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class GraphicsComponent extends Component{
  private Pentomino _pentomino;
    private char[][] _pent;
    private GraphicsContext _gc;
    public static final int TILE_SIZE = 50;
    public static final int GRID_WIDTH = 10;
    public static final int GRID_HEIGHT = 15;
  public GraphicsComponent(GameObject pOwner, GraphicsContext pGc) {
    _owner = pOwner;
    _pentomino = (Pentomino) _owner;
    _owner.addComponent(this);
    _pent = _pentomino.getPentArray();
    _gc = pGc;
  }
  @Override
  public void Update()
  {
    _pent = _pentomino.getPentArray();
    Render(_gc);
  }

  public void Render(GraphicsContext gc)
  {
  		for(int i = 0; i < _pent[0].length; i++)
  		{
  			for(int j = 0; j < _pent.length; j++)
  			{
          if(_pent[j][i] != '0'){
            gc.setFill(Color.WHITE);
  				  gc.fillRect(((_pentomino.getX()) +(i* TILE_SIZE)),_pentomino.getY() + (j* TILE_SIZE), TILE_SIZE, TILE_SIZE);
	          gc.setFill(Color.BLUE);
            gc.fillRect((_pentomino.getX()) +(i* TILE_SIZE) +1,_pentomino.getY() + (j* TILE_SIZE) +1, TILE_SIZE-2, TILE_SIZE-2);

    			}
        }
  		}
  	}
}
