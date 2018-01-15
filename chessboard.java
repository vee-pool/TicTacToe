package tictactoe;
import java.util.Scanner;

/*instance of this class will be 3*3 board, similar to chessboard*/

public  class chessboard
{
	char board[][] = new char [3][3];

	public  chessboard ()
	{
		int i,j;

		for (i=0;i<3;i++)
			for (j=0;j<3;j++)
				board[i][j]='_';

	}

	public void reset()
	{
		int i,j;

		for (i=0;i<3;i++)
			for (j=0;j<3;j++)
				board[i][j]='_';		
	}

	/*this method will update play board , when input is accepted from players*/
	/*returns 0 if exception occurs, else returns 1*/

	public int fillBoard (char ch, int position) throws ArrayIndexOutOfBoundsException
	{
		int row, col , i,j;

		try
		{
			if ( (position%3)==0 ) 
			{
				row = 3-(position/3);
				col=2;
			}
			else 
			{
				row = 2-(position/3);
				col = (position%3)-1;
			}

			if ( board[row][col] =='X' || board[row][col] =='O' )
			{
				return 0;
			}

			board[row][col] = ch ;
		}

		catch (ArrayIndexOutOfBoundsException e) 
		{
			return 0;
		}

		return 1;
	}

	public char checkForWinner()
	{
		int i,j,k;
		char result='_';

		/*first checking middle element*/
		
		if (board[1][1]!='_') /*if bit is empty*/
		{
			if (board[1][1]==board[2][2])
				if (board[1][1]==board[0][0])
					return board[1][1];

			if (board[1][1]==board[0][2])
				if (board[1][1]==board[2][0])
					return board[1][1];

			if (board[1][1]==board[0][1])
				if (board[1][1]==board[2][1])
					return board[1][1];

			if (board[1][1]==board[1][0])
				if (board[1][1]==board[1][2])
					return board[1][1];
		}

		/*checking with left top bit*/
		if (board[0][0]!='_')
		{
			if (board[0][0]==board[0][1])
				if (board[0][0]==board[0][2])
				return board[0][0];

			if (board[0][0]==board[1][0])
				if (board[0][0]==board[2][0])
					return board[0][0];
		}	

		/*checking with right bottom bit*/
		if (board[2][2]!='_')
		{
			if (board[2][2]==board[2][1])
				if (board[2][2]==board[2][0])
					return board[2][2];

			if (board[2][2]==board[1][2])
				if (board[2][2]==board[0][2])
					return board[2][2];
		}

		return result;	
	}

}