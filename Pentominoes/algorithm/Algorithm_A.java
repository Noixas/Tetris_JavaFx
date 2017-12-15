package algorithm;
import java.util.*;
//Get the Width, Height of the board and an array of pentomino letter (minus)
//that can be used to quickly find the pentomino represenration

//We extend this class from the abstract algorithm class
public class Algorithm_A extends abstract_Algorithm {

	int _pentominoIndex = 0;
	public Algorithm_A(int pWidth, int pHeight, int[] pPentominoes) {
		//Save the properties of the board in the abstract algorithm class
		_width = pWidth;
		_height = pHeight;
		minPentominoes = (_width*_height)/5;
		board = new char[_width][_height];
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
			board[i][j] = '0';

		//_pentominoes = pPentominoes;
		_pentominoesList.add(p);//1
		_pentominoesList.add(x);//2
		_pentominoesList.add(f);//3
		_pentominoesList.add(v);//4
		_pentominoesList.add(w);//5
		_pentominoesList.add(y);//6
		_pentominoesList.add(i);//7
		_pentominoesList.add(t);//8
		_pentominoesList.add(z);//9
		_pentominoesList.add(u);//10
		_pentominoesList.add(n);//11
		_pentominoesList.add(l);//12

		_penominoesTotal = pPentominoes.length;//Total pentominoes we will use
		List<char[][]> _bufferPentominoesList = new ArrayList<char[][]>();

		//Dont flip t, v, i, u/*
		for(int i = 0; i < 12/*size*/; i++)
		{
			_bufferPentominoesList.add(_pentominoesList.get(i));
			if(i != 1 ){//Avoid x
				/*if(i != 3 && i != 6 && i != 7 && i != 9){ //This improves A LOT the performance
					_bufferPentominoesList.add(flip(_pentominoesList.get(i)));//Normal flip
					_bufferPentominoesList.add(flip(rotate(_pentominoesList.get(i),90)));//Rotated and flipped
					_bufferPentominoesList.add(flip(rotate(_pentominoesList.get(i),180)));
					_bufferPentominoesList.add(flip(rotate(_pentominoesList.get(i),270)));
				}*/
				_bufferPentominoesList.add(rotate(_pentominoesList.get(i),90));//Rotated
				_bufferPentominoesList.add(rotate(_pentominoesList.get(i),180));
				_bufferPentominoesList.add(rotate(_pentominoesList.get(i),270));
			}
		}
		_pentominoesList = _bufferPentominoesList;
		//Erase pentominoes that are not required
		char [] allPentominoes = {'p', 'x', 'f', 'v', 'w', 'y', 'i', 't', 'z', 'u', 'n', 'l'};
		char []	notUsedPentominoes = new char [allPentominoes.length - pPentominoes.length];
		for (int i = 0; i<pPentominoes.length; i++) {
				allPentominoes [pPentominoes[i]] = '-';}

		int j = 0;
		for (int i = 0; i<allPentominoes.length; i++) {
			if (allPentominoes[i] != '-'){
				notUsedPentominoes [j] = allPentominoes [i];
				j++;}
		}

		for (int i = 0; i<notUsedPentominoes.length; i++){
			for (int counter = _bufferPentominoesList.size() - 1; counter>=0; counter--){
			char [][] temp = _bufferPentominoesList.get(counter);
			if (temp[0][0] == notUsedPentominoes[i] ||
				temp.length>1 && temp[1][0] == notUsedPentominoes[i] ||
				temp.length>2 && temp[2][0] == notUsedPentominoes[i] ||
				temp[0].length>1 && temp[0][1] == notUsedPentominoes[i] ||
				temp.length>1 && temp[1].length>1 && temp[1][1] == notUsedPentominoes[i]
				)
				_bufferPentominoesList.remove(counter);

			}}

		_pentominoesList = _bufferPentominoesList; //Final pentominoes we will use


	}

	//Loop to find solution
	public boolean ComputeSolution(char[][] pBoard, int height, int length)
	{
		while(checkBoard(pBoard) == false){ //while no solution
			if(TryMove(pBoard, height, length))//IF WORKS; find next empty space
			{
				//PrintBoard(pBoard);  //comment out if you dont want to see the steps
				_usedPentominoes.add(new Pentomino(_pentominoesList.get(_pentominoIndex), _pentominoIndex,new ArrayList<char[][]>( _pentominoesList)));
				_pentominoesList.remove(_pentominoIndex); //THIS WORKS :)
				_pentominoIndex = 0;
				EmptySpaceAtWorld(pBoard,  height, length); //This line increases the performance 50%
			}
				int[] coordinates = FindNextEmptySquare(pBoard);
				height = coordinates[0];
				length = coordinates[1];
		}
			//Your conditionals to check if the board is complete and we should quit the project
			solutions.add(pBoard);//Add your solutions to the list so in the future we can display them
			return true;//NOTE: Returning true after just one solution will exit the program, modify if you want to find more solutions


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

	//We try the move if there are still moves to check
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
	//Done: Check if they overlap
 	//Done: check if this piece would leave an empty square alone and if so we delete it and go back
 	//Set the pentomino in the board
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
			_pentominoIndex++;
			return false;
		}
	}
	//Check f that pentomino was already in the board
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
	public void EraseCurrentMove (char[][] pBoard, char[][] pPent)
	{
		EraseMove(pBoard, pPent);
		_pentominoIndex++;

	}
	public void BackOneMove (char[][] pBoard)
	{

		if(_usedPentominoes.isEmpty() == false){
		Pentomino p = _usedPentominoes.get(_usedPentominoes.size() -1);
		_pentominoIndex = p.lastIndexUsed ;
		EraseMove(pBoard,p.pentomino);
		_pentominoesList = _usedPentominoes.remove(_usedPentominoes.size() -1).pentominoesListState;

		_pentominoIndex++;

		}
		else {
			_pentominoIndex = _penominoesTotal;
			isDone = true;
		}

	}

	//return the coordinates in a non smart array
	//This is probably optimizable
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
	////////////////JUST NICE METHODS DOWN HERE///////////////////////
	////////////////JUST NICE METHODS DOWN HERE///////////////////////
	////////////////JUST NICE METHODS DOWN HERE///////////////////////
	////////////////JUST NICE METHODS DOWN HERE///////////////////////
	////////////////JUST NICE METHODS DOWN HERE///////////////////////

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
      return null;
      /*This should never be triggered. It is needed because if not java doesn't
      compile because it sees the possibility that the method will not return
      anything. Another way to solve this problem is to use else instead
      of else if for the 270/-90.*/
    }
    return newPentomino;
  }

	//Not the best method but it works
	public boolean EraseMove(char[][] pBoard, char[][] pPent)
	{
		char pent = '0';
		for(int i = 0; i< pPent.length; i++)
			for(int j = 0; j< pPent[i].length; j++)
			{
				if(pPent[i][j]!= '0'){//Find the char we will delete
					pent = pPent[i][j];
					i = pPent.length -1;
					j = pPent[i].length -1;
				}
			}
		for(int i = 0; i< pBoard.length; i++)
			for(int j = 0; j< pBoard[i].length; j++)
			{
				if(pBoard[i][j] == pent)//If the board has the pentomino that we try to delete, set it back to 0
					pBoard[i][j] = '0';
			}
		return true;
	}

	//Write your own method to check the board
	public boolean checkBoard(char[][] pBoard)//Improve by checking from the end
	{

		if(_usedPentominoes.size() >= minPentominoes)
			return true;
		else
			return false;
	}
	//check if there are more spaces around
	///TODO: Find if there are squares empty and isolated in non multiple of 5
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
	//Method call every frame from the main class to check the solutions
	public void FindSolutions()
	{
		isDone = ComputeSolution(board,0,0);//we set the isDone variable to true when we find the solution
	}
	//Return the solution you found
	public String ReturnSolutions()
	{
		PrintBoard(solutions.get(0));//Here print the solution you found, p is just a placeholder
		return "First found solution";
	}
	//Return the List with all the solutions we found, will be use with the graphical interface
	public List<char[][]> ReturnListSolutions(){
		return solutions;
	}

}
class Pentomino{
	char[][] pentomino;
	int lastIndexUsed;
	List<char[][]> pentominoesListState = new ArrayList<char[][]>();

	public Pentomino(char[][] pPentomino, int pLastIndexUsed,List<char[][]> pPentominoesListState )
	{
		pentomino = pPentomino;
		lastIndexUsed = pLastIndexUsed;
		pentominoesListState = pPentominoesListState;
	}
}
