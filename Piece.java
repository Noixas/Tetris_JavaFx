import java.util.List;
import java.util.LinkedList;
public class Piece {

	private static List<char[][]> pieces = new LinkedList<char[][]>();

	public static List<char[][]> getPieces(int pIndex)//Todo return all rotations of the piece
	{
  List<char[][]> allRotations = new LinkedList<char[][]>();
    allRotations.add(getPiece(pIndex));
    allRotations.add(rotate(getPiece(pIndex),90));//Rotated
    allRotations.add(rotate(getPiece(pIndex),180));
    allRotations.add(rotate(getPiece(pIndex),270));
		return allRotations;
	}
  public static char[][] flip(char[][] pentomino) { //This flips horizontally(column per column)

    int width = pentomino[0].length;
    int height = pentomino.length;
    char[][] newPentomino = new char[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newPentomino[i][width - j - 1] = pentomino[i][j];
      }
    }
    return newPentomino;
  }
public static char[][] getPiece(int pIndex)
{
	char[][] buffer = pieces.get(pIndex);
	char[][] finalPiece = new char[buffer.length][buffer[0].length];
	for(int i=0; i<buffer.length; i++)
  for(int j=0; j<buffer[i].length; j++)
    finalPiece[i][j]=buffer[i][j];
		return finalPiece;
}
public static int getPiecesQuantity()
{
	return pieces.size();
}

  public static char[][] rotate(char[][] pentomino, int degrees) {
    int width = pentomino[0].length;
    int height = pentomino.length;
    char[][] newPentomino;
    if(degrees==90) { //Clockwise 90°
      newPentomino = new char[width][height];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          newPentomino[j][height - i - 1] = pentomino[i][j];
        }
      }
    } else if(degrees==180) { //Clockwise or CounterClockwise 180°
      newPentomino = new char[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          newPentomino[height - i - 1][width - j - 1] = pentomino[i][j];
        }
      }
    } else if(degrees == 270 || degrees == -90){ //CounterClockwise 90°
      newPentomino = new char[width][height];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          newPentomino[width - j - 1][i] = pentomino[i][j];
        }
      }
    } else {
      return pentomino;
    }
    return newPentomino;
  }
  private static char [][] p = {
    {'p','p'},
    {'p','p'},
    {'p','0'}};
private static char[][] x = {
    {'0','x','0'},
    {'x','x','x'},
    {'0','x','0'}};
   private static 	char[][] f = {
    {'0','f','f'},
    {'f','f','0'},
    {'0','f','0'}};
   private static 	char[][] v = {
    {'v','0','0'},
    {'v','0','0'},
    {'v','v','v'}};

    private static char[][] w = {
    {'w','0','0'},
    {'w','w','0'},
    {'0','w','w'}};

     private static char[][] y = {
    {'0','y'},
    {'y','y'},
    {'0','y'},
    {'0','y'}};

   private static 	char[][] i = {
    {'i'},
    {'i'},
    {'i'},
    {'i'},
    {'i'}};

   private static 	char[][] t = {
    {'t','t','t'},
    {'0','t','0'},
    {'0','t','0'}};

   private static 	char[][] z = {
    {'z','z','0'},
    {'0','z','0'},
    {'0','z','z'}};

   private static 	char[][] u = {
    {'u','0','u'},
    {'u','u','u'}};

   private static 	char[][] n = {
    {'n','n','0','0'},
    {'0','n','n','n'}};

   private static 	char[][] l = {
    {'l','0'},
    {'l','0'},
    {'l','0'},
    {'l','l'}};
    static{
			//TODO: ask teacher if good programming practice
			pieces.add(l);//0
      pieces.add(x);//1
      pieces.add(f);//2
      pieces.add(v);//3
      pieces.add(w);//4
      pieces.add(y);//5
      pieces.add(i);//6
      pieces.add(t);//7
      pieces.add(z);//8
      pieces.add(u);//9
      pieces.add(n);//10
      pieces.add(p);//11
      for(int i = 0; i < 12/*size*/; i++)
      {
        if(i != 1 ){//Avoid x
          if(i != 3 && i != 4 && i != 6 && i != 7 && i != 9){ //This improves A LOT the performance
            pieces.add(flip(pieces.get(i)));
          }
        }
      }

    }
}
