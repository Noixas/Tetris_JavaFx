import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class GraphicsComponent extends Component{
  private Pentomino _pentomino;
    private char[][] _pent;
    private GraphicsContext _gc;
    private float _blinking =  .15f;
    private float _timePased = 0;
    private boolean _blink = false;
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
    _pent = _pentomino.getPentArray();//get the newest array(in case we rotated)
    if(!_blink)
    Render(_gc);
    else
    Blinking();

  }
  private void Blinking()
  {
    _timePased += Time.deltaTime;
    if(_timePased < 0){
      Render(_gc);
    }
    else if(_blinking < _timePased)
    {
      _timePased = -_blinking*2;
      Render(_gc);
    }
    // Else Do Nothing
  }
  private void Render(GraphicsContext gc)
  {
    if(_pent.length>0)
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
    public void EnableBlinking()
    {
      _blink = true;
    }
    public void DisableBlinking()
    {
      _blink = false;
    }
}
