package tictactoe;
import java.util.Scanner;

public class user extends player
{
	public int Move ()
	{
		int position;
	
		Scanner in = new Scanner (System.in);

		System.out.print("Enter your marking position: ");

		position = in.nextInt();

		return position;
	}	
}