//package Pentominoes;
import java.util.Arrays;
import java.util.Scanner;
import algorithm.*;
public class Algorithm_Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean validBoard = false;
		int x = -1;
		int y = -1;//Init

		while (!validBoard){ //Until is a board we can solve
			System.out.println("Give height of the board.");
			x = in.nextInt();

			System.out.println("Give width of the board.");
			y = in.nextInt();

			if (x==2&&y==5 || x==5&&y==2 || x*y>60) //If any of these conditions is true, none solutions possibe
				System.out.println("This board is not going to give any solutions.");
			else
				if  (x*y%5==0) //If this is false then theres no solutions possible
					validBoard = true;
				else
					System.out.println("This board is not going to give any solutions.");
		}

		System.out.println("p = 0");
		System.out.println("x = 1");
		System.out.println("f = 2");
		System.out.println("v = 3");
		System.out.println("w = 4");
		System.out.println("y = 5");
		System.out.println("i = 6");
		System.out.println("t = 7");
		System.out.println("z = 8");
		System.out.println("u = 9");
		System.out.println("n = 10");
		System.out.println("l = 11");
		System.out.println("ALL = 12"); //copy to code 13/10 10:50

		validBoard = false;
		int [] t = new int [0];
		int q;
		

		System.out.println("Put in the numbers of the pentominoes u want to use with spaces inbetween, end with a Q.");

		while (in.hasNextInt()){
			q = in.nextInt();
			t = inserter(t, q);}

		//for (int i=0; i<t.length; i++){ //Debug purposes
			//System.out.print(t[i] + " ");}
		System.out.println();
		boolean continueTry = true;
		int [] temp = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};		//copy this; 11.00 10/13
		if (t.length*5>=x*y)
			validBoard = true;
		else if (t[0] == 12)										//copy this; 11.00 10/13
			t = temp;
		else{
				System.out.println("This set is not going to give any solutions, there need to be more pentominoes.");
				continueTry = false;
		}

		if(continueTry){//If valid board try to solve it
		Algorithm_A testa = new Algorithm_A(x,y, t);//Initialize the algorithm with x properties
		long startTime = System.nanoTime(); 
		while(!testa.Finished())//While the algorithm hasnt being stopped keep trying
		{
			testa.FindSolutions();//Try to find answer
		}
		float estimatedTime = (System.nanoTime() - startTime) / 1000000000.0f;
		System.out.println(testa.ReturnSolutions());
		System.out.println("Time it took: " + estimatedTime+ " seconds");
		}
		else
		{
			System.out.println("Exiting...");
				
		}
	}

		public static int [] inserter (int [] t, int x){
		int [] newpentominoe = new int[t.length+1];
		for (int i=0; i<t.length; i++)
			newpentominoe[i]=t[i];
		t = newpentominoe;
		t[t.length-1]= x;

		
        return t;
	}
	
}
