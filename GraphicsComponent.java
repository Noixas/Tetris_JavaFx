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
    private Color _color;
  public GraphicsComponent(GameObject pOwner, GraphicsContext pGc) {
    _owner = pOwner;
    _pentomino = (Pentomino) _owner;
    _owner.addComponent(this);
    _pent = _pentomino.getPentArray();
    _gc = pGc;
    _color = selectcolor(_pentomino);
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
            gc.setFill(_color);
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

    public Color selectcolor(Pentomino p){
      switch (p.getType()){
        case 0: return Color.PURPLE;
        case 1: return Color.WHITE;
        case 2: return Color.BLUE;
        case 3: return Color.RED;
        case 4: return Color.PINK;
        case 5: return Color.YELLOW;
        case 6: return Color.GREEN;
        case 7: return Color.ORANGE;
        case 8: return Color.LIGHTBLUE;
        case 9: return Color.DARKBLUE;
        case 10: return Color.GREY;
        case 11: return Color.GOLD;
        case 12: return Color.KHAKI;
        case 13: return Color.LIGHTSALMON;
        case 14: return Color.LIMEGREEN;
        case 15: return Color.MAGENTA;
        case 16: return Color.SILVER; 
        case 17: return Color.TAN;
        default: return Color.BROWN;
      }
    }
}
