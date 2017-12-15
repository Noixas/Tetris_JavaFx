import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;

public class SmartOrderer extends GameObject{
  private List<MoveInfo>   _pentAndMoves = new LinkedList<MoveInfo>();
  private int             _aim = 5;
  private char[][]        _virtualBoard;
  private int             _IshapeIndex;
  private int             _LshapeIndex;
  //public List<Integer>    pentominoesList ; //Starting pool of pentominoes
  private int            _pentominoIndex = 1;
  private int             _pentRotation = 0;
  private int             _pentMoves = 0;
  private List<char[][]> _pentominoesList = new ArrayList<char[][]>();

  public SmartOrderer(){
    super(0,0);
    //pentominoesList = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11));
    _IshapeIndex = 6;
    _LshapeIndex = 0;
    //pentominoesList.remove(_IshapeIndex);
    List<char[][]> _bufferPentominoesList = new ArrayList<char[][]>();

//Dont flip t, v, i, u/*
for(int i = 0; i < 12/*size*/; i++)
{
  _bufferPentominoesList.add(Piece.getPiece(i));
  if(i != 1 ){
    _bufferPentominoesList.add(Piece.rotate(Piece.getPiece(i),90));//Rotated
    _bufferPentominoesList.add(Piece.rotate(Piece.getPiece(i),180));
    _bufferPentominoesList.add(Piece.rotate(Piece.getPiece(i),270));
  }
}
_pentominoesList = _bufferPentominoesList;
    startSimulation();

  }
  private void startSimulation()
  {
    _virtualBoard = new char[_aim][5];
    for(int i = 0; i < _virtualBoard.length; i++)
      for(int j = 0; j < _virtualBoard[0].length; j++){
          _virtualBoard[i][j] = '0';
          System.out.println(  _virtualBoard[i][j] );
      }
      findSolution(_virtualBoard);
  }
    private void findSolution(char[][] pBoard)
    {
      if(_aim == 5){//pre fill shape I
        for (int i = 0; i < pBoard.length; i++)
            pBoard[i][pBoard[0].length-1] = 'i';
      }
      int[] coordinates = FindNextMove(pBoard);//Find next move to check
      int height = coordinates[0];
      int length = coordinates[1];
      while(_pentAndMoves.size() < _aim-1)//-1 because I shape is predefined
      {
        if(TryMove(pBoard, height, length))//IF WORKS; find next empty space
      			{
      				Piece.PrintBoard(pBoard);  //comment out if you dont want to see the steps
              _pentAndMoves.add(new MoveInfo(_pentominoIndex,_pentRotation,_pentMoves, _pentominoesList));
      				//spentominoesList.remove(_pentominoIndex); //THIS WORKS :)
      				_pentominoIndex = 0;
      				EmptySpaceAtWorld(pBoard,  height, length); //This line increases the performance 50%
      			}
      				coordinates = FindNextMove(pBoard);//Find next move to check
      				height = coordinates[0];
      				length = coordinates[1];
            //  System.out.println(_pentAndMoves.size());

  		}

      _pentAndMoves.add(new MoveInfo(_IshapeIndex,1,4, _pentominoesList));
  //    for(int i = 0; i < _pentAndMoves.size();i++)
  //    System.out.println("Pents "+_pentAndMoves.get(i));
  //    Piece.PrintBoard(pBoard);
    }
    private int[] FindNextMove(char[][] pBoard)
    {
      //here try from leftmost till right and down

      return FindNextEmptySquare(pBoard);
    }
    public int[] FindNextEmptySquare(char[][] pBoard)
	{
		int[] result = new int[2];

		for(int i = 0; i < pBoard.length; i++)
			{
				for(int j = 0; j < pBoard[i].length; j++)
				{
					if(pBoard[i][j] == '0')
					{
						result[0] = i;
						result[1] = j;
						if(!emptySpaceIsolated(pBoard,i,j))//IF its not alone
						{

							//Quit loop
							i = pBoard.length - 1;
							j = pBoard[i].length -1;
						}
						else//Write backtracking code here, create erase last move method
						{
							BackOneMove(pBoard);//Go back one move,

							i = pBoard.length - 1;
							j = pBoard[i].length -1;
							result = FindNextEmptySquare(pBoard);//Find again an empty spot
						}
					}
				}
			}
			return result;
	}
    public boolean TryMove(char[][]pBoard, int height, int length)
  	{

      if(_pentominoIndex < _pentominoesList.size())//Here is the only place we check if we already tryied all pentominoes
  return  PentominoAtBoard(pBoard, _pentominoesList.get(_pentominoIndex), height, length);
  else
  {

  			//If we already check all prev moves then dont move forward
  			//Go one move back
  			BackOneMove(pBoard);
  			return false;
  		}
  	}
    public boolean PentominoAtBoard(char[][] pBoard, char[][] pPent, int height, int length)
{
  // Method that checks if we already used a version of the same pentomino but rotated
  if(checkIfExists(pBoard, pPent) == false)//So if it doesnt exist yet
  {

    if(pPent[0][0] == '0' && length >0)//If the first element is empty space then we move the whole pentomino to the left
      {
           length -= 1;
      }
    if(((height +pPent.length-1) < pBoard.length && (length+pPent[0].length -1) < pBoard[height].length)  )		//If in boundaries
    {
      for(int i = 0; i< pPent.length; i++)
      {
        for(int j = 0; j < pPent[i].length; j++)
        {

          //In boundaries and (no overlap or the char is empty) //TODO: check the whole boundaries just once

          if((pBoard[height+i][length+j] == '0' ))
          {
            //Move
            //if(pPent[i][j] != '0') //we dont want to write a 0 over another piece
            pBoard[height+i][length+j] = pPent[i][j];
          }
          else if(pPent[i][j] != '0')
          {
            EraseCurrentMove(pBoard, pPent);
            //CURRENT MOVE INVALID
            //Delete current move so far and increase index to go to the next pentomino
            return false;
          }

        }
      }
    }
    else
      {
        EraseCurrentMove(pBoard, pPent);
        //CURRENT MOVE INVALID
        //Delete current move so far and increase index to go to the next pentomino
        return false;
      }

    return true;
  }
  else {
    if(_pentRotation == 3){
    _pentominoIndex++;
    _pentRotation = 0;
  }
    return false;
  }
}
  public boolean checkIfExists(char[][] pBoard, char[][] pPent)
	{
		char pent = '0';
		for(int i = 0; i< pPent.length; i++)
			for(int j = 0; j< pPent[i].length; j++)
			{
				if(pPent[i][j]!= '0'){//Find the char we will compare with
					pent = pPent[i][j];
					i = pPent.length -1;
					j = pPent[i].length -1;
				}
			}
		for(int i = 0; i< pBoard.length; i++)
			for(int j = 0; j< pBoard[i].length; j++)
			{
				if(pBoard[i][j] == pent)//If the board has the pentomino that we try add, tell that yeah it exists before
					return true;
			}
		return false;
	}
    public boolean tryMove(char[][] pBoard, char pPiece, int pDir,int a)
    {
      for(int i = 0; i < pBoard.length; i++)
      {
        for(int j = 0; j < pBoard[0].length; j++)
        {
          if(pBoard[i][j] == pPiece)
          {
            if(pDir == -1 && ((j + pDir < 0) || //LEFT, boundary limits
            (pBoard[i][j + pDir] != pPiece && pBoard[i][j + pDir] != '0') || (pBoard[i+1][j + pDir] != pPiece && pBoard[i+1][j + pDir] != '0')  ))
            {
                //System.out.println("left"+ j);
              return false;
            }
            else if(pDir == 0 && ((i + 1 >= pBoard.length) ||  //0 is down
             (pBoard[i + 1][j] != pPiece && pBoard[i+1][j] != '0')))//DOWN
            {
             //System.out.println("down");
              return false;
            }
            else if(pDir == 1 &&  (( j + pDir >= pBoard[0].length ) ||//RIGHT, boundary limits
             (pBoard[i][j + pDir] != pPiece && pBoard[i][j + pDir] != '0')|| (pBoard[i+1][j + pDir] != pPiece && pBoard[i+1][j + pDir] != '0') ))
            {
               // System.out.println("right");
              return false;
            }
          }
        }
      }
      return true;
    }
public void BackOneMove (char[][] pBoard)
	{

		if(_pentAndMoves.isEmpty() == false){
		MoveInfo p = _pentAndMoves.get(_pentAndMoves.size() -1);
		_pentominoIndex = p.pentIndex;
		EraseMove(pBoard,Piece.getPiece(p.pentIndex));
		_pentominoesList = _pentAndMoves.remove(_pentAndMoves.size() -1).pentominoesListState;

		_pentominoIndex++;

		}
		else {
			_pentominoIndex = 12;
			//isDone = true;
		}

	}
  public boolean EraseMove(char[][] pBoard, char[][] pPiece)
  	{
      char[][] pent = pPiece;
  		char pentLetter = '0';
  		for(int i = 0; i< pent.length; i++)
  			for(int j = 0; j< pent[i].length; j++)
  			{
  				if(pent[i][j]!= '0'){//Find the char we will delete
  					pentLetter = pent[i][j];
  					i = pent.length -1;
  					j = pent[i].length -1;
  				}
  			}
  		for(int i = 0; i< pBoard.length; i++)
  			for(int j = 0; j< pBoard[i].length; j++)
  			{
  				if(pBoard[i][j] == pentLetter)//If the board has the pentomino that we try to delete, set it back to 0
  					pBoard[i][j] = '0';
  			}
  		return true;
  	}
    public void EraseCurrentMove (char[][] pBoard, char[][] pPent)
{
  EraseMove(pBoard, pPent);
  if(_pentRotation == 3){
  _pentominoIndex++;
  _pentRotation = 0;
  }
}
    //Check if there is any isolated block in the whole board
public boolean EmptySpaceAtWorld(char[][]pBoard, int height, int length)
{

  for(int i = 0; i< pBoard.length; i++)
    for(int j = 0; j < pBoard[i].length; j++)
    {
      if(pBoard[i][j]=='0'&& emptySpaceIsolated(pBoard,i,j))
      {
        BackOneMove(pBoard);//Go back one move
        return true;
      }
    }
  return false;
}

public boolean emptySpaceIsolated(char[][] pBoard, int x, int y)
{
  //char[][] trackEmpty = new char[pBoard.length][pBoard[0].length];
  if(y > 0 && pBoard[x][y-1] == '0')//left
  {
    return false;
  }
  else if(x > 0 && pBoard[x-1][y] == '0')//top
    return false;
  else if(x < pBoard.length-1 && pBoard[x+1][y] == '0')//bottom
    return false;
  else if(y < pBoard[x].length-1 && pBoard[x][y+1] == '0')//right
    return false;
  else
    return true;//backtrack
}
}

class MoveInfo{
	int pentIndex;
	int rotation;
  int moves;
   List<char[][]>  pentominoesListState = new ArrayList<char[][]>();

	public MoveInfo(int pPentIndex, int pRot, int pMoves,  List<char[][]>  pPentominoesListState  )
	{
		pentIndex = pPentIndex;
		rotation = pRot;
		moves = pMoves;
    pentominoesListState = pPentominoesListState;

	}
  public String toString()
  {
    String out = "";
    out = "Index " + pentIndex + "\n";
    out += "Rotation " + rotation + "\n";
    out += "Moves " + moves + "\n";


    return out;
  }
}
