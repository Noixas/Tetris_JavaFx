package algorithm;
import java.util.*;
//Get the Width, Height of the board and an array of pentomino letter (minus) 
//that can be used to quickly find the pentomino represenration

//We extend this class from the abstract algorithm class
public class Algorithm_Test extends abstract_Algorithm {

	int _pentominoIndex = 0;
	public Algorithm_Test(int pWidth, int pHeight, char[] pPentominoes) {
		//Save the properties of the board in the abstract algorithm class
		_width = pWidth;
		_height = pHeight;
		board = new char[_width][_height];
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board[i].length; j++)
			board[i][j] = '0';
		
		_pentominoes = pPentominoes;
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
		//////////////////////////
		/////////// Just reset to 0 the index when you used all pentominoes(?)
		//////////	NO! you should backtrack and the last pentomino you go back should have a refrence to the next index, if you use all then backtrack again
		////////////////////////
				
	}
	
	
	public boolean ComputeSolution(char[][] pBoard, int height, int length)
	{
		if(TryMove(pBoard, height, length))//IF WORKS; find next empty space
		{
			_usedPentominoes.add(new Pentomino(_pentominoesList.get(_pentominoIndex), _pentominoIndex,new ArrayList<char[][]>( _pentominoesList)));						
			_pentominoesList.remove(_pentominoIndex); //THIS WORKS :)			
			_pentominoIndex = 0;
			BackTrack( pBoard);
			
			int[] coordinates = FindNextEmptySquare(pBoard);
			
			height = coordinates[0];
			length = coordinates[1];
		}
		PrintBoard(pBoard);//Print board method in the abstract class
		if(checkBoard(pBoard)){//Your conditionals to check if the board is complete and we should quit the project
			solutions.add(pBoard);//Add your solutions to the list so in the future we can display them
			return true;//NOTE: Returning true after just one solution will exit the program, modify if you want to find more solutions
		}
		else //If is not complete then return false
		{
			if(pBoard[height].length <= length && !(pBoard.length <= height))//Checar si esta bien para ir a la fila de abajo
			{
				length = 0;
				height ++;
				
			}
			
			ComputeSolution(pBoard, height, length);
			return false;
		}
	}
	public boolean TryMove(char[][]pBoard, int height, int length)
	{
		if(pBoard[height][length] == '0')//If this square is empty try to put a pentomino	
		{			
			if(_pentominoIndex >= _pentominoesList.size())
			{
				_pentominoIndex = _usedPentominoes.get(_usedPentominoes.size() -1).lastIndexUsed;
				EraseMove(pBoard,_usedPentominoes.get(_usedPentominoes.size() -1).pentomino);//Because backtrackiong, delete last used pentomino
				_pentominoesList = _usedPentominoes.remove(_usedPentominoes.size() -1).pentominoesListState;
							
			}
			return  PentominoAtBoard(pBoard, _pentominoesList.get(_pentominoIndex), height, length);
		}
		else//Because we find a method that find the next empty square this is redundant
		{
			//System.out.println("TryMove Method NONE EMPTY BLOCK: "+ count);
			for(int a = 0; a < 999999; a++)							
			System.out.println("WRONNNNNG");
			System.out.println("Length "+ length);
			System.out.println("Content "+ pBoard[height][length]);
			return false; //if its not empty return 
		}		
	}
	//TODO: Check if they overlap		
 	//TODO: check if this piece would leave an empty square alone and if so we delete it and go back
	public boolean PentominoAtBoard(char[][] pBoard, char[][] pPent, int height, int length)
	{
		int count = 0;
		for(int i = 0; i< pPent.length; i++)
		{
			for(int j = 0; j < pPent[i].length; j++)
			{
				System.out.println("The board before writing a new pentomino over it");		
				PrintBoard(pBoard);
				System.out.println("I´m that pentomino :)");						
				PrintBoard(pPent);		
					//check boundaries and if is empty
				if((height+i< pBoard.length && length+j < pBoard[height].length)  && 
					(pBoard[height+i][length+j] == '0' || pPent[i][j] == '0'))//GOOD
				{
					count++;				
					System.out.println("Pentomino At board method movecount: "+ count);		
					if(pPent[i][j] != '0') //we dont want to write a 0 over another piece
					pBoard[height+i][length+j] = pPent[i][j];
				}//If out of boundaries or if the block is ocuppied -> this move is not valid, go back
				else if((height+i >= pBoard.length || length+j >= pBoard[height].length )||pBoard[height+i][length+j] != '0')//if the block is occupied by another pentomino
				{					
					if(_pentominoIndex < _pentominoesList.size()-1) //If there are more pentominos we havent tried
						{								
							EraseMove(pBoard, pPent); //Erase this last move
							PrintBoard(pBoard);		//Print the board without this last move		
							_pentominoIndex++;
							System.out.println("NEW PENTOMINO INDEXXXXXX:"+ _pentominoIndex);
							System.out.println("PENTOMINO _pentominoesList.size():"+ _pentominoesList.size());
							
						}
						//If we loop through all the pentominos then we take the last approved moved back and try new pentominoes
						else{//If we reached the last pentomino go back one move	
							
							EraseMove(pBoard,pPent);
							_pentominoIndex = _usedPentominoes.get(_usedPentominoes.size() -1).lastIndexUsed;
							EraseMove(pBoard,_usedPentominoes.get(_usedPentominoes.size() -1).pentomino);//Because backtrackiong, delete last used pentomino
							_pentominoesList = _usedPentominoes.remove(_usedPentominoes.size() -1).pentominoesListState;
							
							if(_pentominoIndex < _pentominoesList.size()) //If there are more pentominos we havent tried
								_pentominoIndex++;
							else //Go back
							{
								BackTrack(pBoard);
							}
							/* //Debug info
							System.out.println("Last USED PENTOMINOES ARE:");
							PrintBoard(pPent);							
							System.out.println("USED PENTOMINOES ARE:");								
							for(int a = 0; a < _usedPentominoes.size(); a++)
								PrintBoard(_usedPentominoes.get(a).pentomino);							
							for(int p = 0; p< 15; p++)
							System.out.println("OLD LIST SIZE "+ _usedPentominoes.size());
						*/
						
							/* //More debug info
							for(int p = 0; p< 15; p++)
							System.out.println("NEW LIST SIZE of used pentominos"+ _usedPentominoes.size());							
							for(int p = 0; p< 15; p++)
							System.out.println("NEW pentominoes index "+ _pentominoIndex);							
							System.out.println("new list of USED PENTOMINOES ARE:");								
							for(int a = 0; a < _usedPentominoes.size(); a++)
								PrintBoard(_usedPentominoes.get(a).pentomino);*/
						}
					return false;
				}
				else{
					for(int p = 0; p< 5000; p++)
								System.out.println("SOMETHING IS WRONG AT OENTOMINOAT BOARD");							
								
					return false;
				}
			}
		}		
					
		return true;
	}
	
	//return the coordinates in a non smart array
	//This is probably optimizable
	public int[] FindNextEmptySquare(char[][] pBoard)
	{
		int height = 0;
		int length = 0;
		int[] result = new int[2];
		
		for(int i = 0; i < pBoard.length; i++)
			{
				for(int j = 0; j < pBoard[i].length; j++)
				{
					if(pBoard[i][j] == '0')
					{
						height = i;
						length = j;
						result[0] = height;
						result[1] = length;
						//for(int k = 0; k < 10; k++)
						//System.out.println("EMPTY SPACE: "+ i +" , "+j);
						
						if(!emptySpaceIsolated(pBoard,i,j))
						{
							//_pentominoIndex = 0;				
							System.out.println("PENTOMINO INDEX from empty square method: "+ _pentominoIndex);
							System.out.println("PENTOMINO LIIIIIIISSSST SIZE from empty square method: "+_pentominoesList.size());
							
							PrintBoard(_pentominoesList.get(_pentominoIndex));
							for(int z = 0; z < _pentominoesList.size();z++)
							{
								System.out.println("PENTOMINO List at: "+ z);
								PrintBoard(_pentominoesList.get(z));
							}
							for(int z = 0; z < _usedPentominoes.size();z++)
							{
								System.out.println("PENTOMINO USEDDDDDDDD List at: "+ z);
								PrintBoard(_usedPentominoes.get(z).pentomino);
							}
							
							//Quit loop
							i = pBoard.length - 1;
							j = pBoard[i].length -1;
						}
						else//Write backtracking code here, create erase last move method	
						{
							System.out.println("PENTOMINO INDEX MULTIPLICAR:"+ _pentominoIndex);
							System.out.println("PENTOMINO LIIIIIIISSSST SIZE:"+_pentominoesList.size());
							
							if(_pentominoIndex < _pentominoesList.size()-1)
							{
								EraseMove(pBoard,_pentominoesList.get(_pentominoIndex));							
								_pentominoIndex = _usedPentominoes.get(_usedPentominoes.size() -1).lastIndexUsed +1;
								EraseMove(pBoard,_usedPentominoes.get(_usedPentominoes.size() - 1).pentomino);							
								_pentominoesList = _usedPentominoes.remove(_usedPentominoes.size() -1).pentominoesListState;
								
								for(int b = 0; b < _pentominoesList.size();b++)
								{
									System.out.println("Printing the previous version of the list");
							
									PrintBoard(_pentominoesList.get(b));
								//HEre print the list state
								}
								System.out.println("PENTOMINO INDEXXXXXX if is true y su papa se la come:"+ _pentominoIndex);
								
							}else//If we loop through all the pentominos then we take the last approved moved back and try new pentominoes
							{	
								System.out.println("NEW CODE WOEKING!!! "+ _pentominoIndex);							
								_pentominoIndex = _usedPentominoes.get(_usedPentominoes.size() -1).lastIndexUsed +1;
								EraseMove(pBoard,_usedPentominoes.get(_usedPentominoes.size() - 1).pentomino);							
								_pentominoesList = _usedPentominoes.remove(_usedPentominoes.size() -1).pentominoesListState;
								
								 BackTrack( pBoard);
	
								
							}
							i = pBoard.length - 1;
							j = pBoard[i].length -1;
							result = FindNextEmptySquare(pBoard);
						}
					}
				}
			}
			return result;
	}
	public void BackTrack(char[][] pBoard)
	{
		if(_pentominoIndex == _pentominoesList.size())//back one, create back method?
			{
				_pentominoIndex = _usedPentominoes.get(_usedPentominoes.size() -1).lastIndexUsed +1;
				EraseMove(pBoard,_usedPentominoes.get(_usedPentominoes.size() - 1).pentomino);							
				_pentominoesList = _usedPentominoes.remove(_usedPentominoes.size() -1).pentominoesListState;
				for(int i = 0; i< 30; i++)
					System.out.println("The index after backtracking is: " + _pentominoIndex +" indx: "+ i);
							BackTrack(pBoard);
			}
	}
	////////////////JUST NICE METHODS DOWN HERE///////////////////////
	////////////////JUST NICE METHODS DOWN HERE///////////////////////
	////////////////JUST NICE METHODS DOWN HERE///////////////////////
	////////////////JUST NICE METHODS DOWN HERE///////////////////////
	////////////////JUST NICE METHODS DOWN HERE///////////////////////
	
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
	public boolean checkBoard(char[][] pBoard)
	{
		for(int i = 0; i< pBoard.length; i++)
		{
			for(int j = 0; j< pBoard[i].length; j++)
			{
				if(pBoard[i][j] == '0')//If there is a space left then is not a solution
				{
					return false;			
				}
			}
		}
		//System.out.println("Checkboard Method true");
		return true;
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
		PrintBoard(p);//Here print the solution you found, p is just a placeholder
		return "There are x possible solutions";
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
