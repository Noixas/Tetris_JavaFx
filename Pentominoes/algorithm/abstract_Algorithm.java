package algorithm;
import java.util.*;

public abstract class abstract_Algorithm {
	//Properties that all algorithms should have
	protected int _width; 
	protected int _height;
	protected char[][] board;
	protected char[] _pentominoes;
	protected List<Pentomino> _usedPentominoes = new ArrayList<Pentomino>();
	protected List<char[][]> _pentominoesList = new ArrayList<char[][]>();
	protected int minPentominoes = 0;
	protected int _penominoesTotal;
		
	protected boolean isDone = false;
	protected List<char[][]> solutions = new ArrayList<char[][]>();

	//Print all the chars from a board
	public void PrintBoard(char[][] pBoard)
	{
		String board  = "";
		for(int i = 0; i < pBoard.length; i++)
		{
			for(int j = 0; j < pBoard[i].length; j++)
			{
				board += pBoard[i][j];

			}	
			board += "\n";
		}
		System.out.println(board);
	}
	public boolean Finished()
	{
		return isDone;
	}
	public abstract boolean checkBoard(char[][] pBoard);
	public abstract String ReturnSolutions();
	public abstract List<char[][]> ReturnListSolutions();
	
	//Accesible arrays for each pentomino
	char [][] p = {
	{'p','p'},
	{'p','p'},
	{'p','0'}};
	char[][] x = {
	{'0','x','0'},
	{'x','x','x'},
	{'0','x','0'}};
	char[][] f = {
	{'0','f','f'},
	{'f','f','0'},
	{'0','f','0'}};
	char[][] v = {
	{'v','0','0'},
	{'v','0','0'},
	{'v','v','v'}};
	
	char[][] w = {
	{'w','0','0'},
	{'w','w','0'},
	{'0','w','w'}};

	char[][] y = {
	{'0','y'},
	{'y','y'},
	{'0','y'},
	{'0','y'}};
	
	char[][] i = {
	{'i'},
	{'i'},
	{'i'},
	{'i'},
	{'i'}};
	
	char[][] t = {
	{'t','t','t'},
	{'0','t','0'},
	{'0','t','0'}};
	
	char[][] z = {
	{'z','z','0'},
	{'0','z','0'},
	{'0','z','z'}};
	
	char[][] u = {
	{'u','0','u'},
	{'u','u','u'}};

	char[][] n = {
	{'n','n','0','0'},
	{'0','n','n','n'}};
	
	char[][] l = {
	{'l','0'},
	{'l','0'},
	{'l','0'},
	{'l','l'}};
}