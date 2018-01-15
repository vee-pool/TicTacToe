import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import tictactoe.*;
/*
<applet code="TicTacToe" width=300 height=300>
</applet>
*/
public class TicTacToe extends Applet implements MouseListener, ItemListener, ActionListener
{

	int mouseX, mouseY, stepCount, upper[], left[];
	char symbol[], result;

		chessboard PlayBoard = new chessboard();
		user Player1 = new user();
		user Player2 = new user();
		computer Computer = new computer();

	Choice gameMode, gameLevel;
	Button reset;


	public void init() 
	{

		setSize(300, 400);
		addMouseListener(this);	
		upper = new int [3];
		left = new int [3];
		symbol = new char [10];

		int i,j;

		for (i=0; i<300; i=i+100) 
			upper[i/100]=i;	

		for (j=100; j<400; j=j+100)	
			left[j/100-1]=j;

		for (i=0; i<10; i++) 
		{
			symbol[i]='N';	
		}

		gameMode = new Choice();
		gameLevel = new Choice();

		gameLevel.add("Nil");
		gameLevel.add("Easy");
		gameLevel.add("Medium");
		gameLevel.add("Unbeatable");

		gameMode.add("Play with Friend");
		gameMode.add("Play with Computer");

		add(gameLevel);
		add(gameMode);

		gameMode.addItemListener(this);
		gameLevel.addItemListener(this);

		reset = new Button ("Reset game");
		add(reset);
		reset.addActionListener(this);

	}

	public void itemStateChanged(ItemEvent ie) 
	{
		int i;
		for (i=0; i<10; i++) 
		{
			symbol[i]='N';	
		}

		PlayBoard.reset();
		Computer.reset();
		result='-';
		stepCount=0;

		repaint();
	}

	public void actionPerformed(ActionEvent ae)
	{
		int i;
		for (i=0; i<10; i++) 
		{
			symbol[i]='N';	
		}

		PlayBoard.reset();
		Computer.reset();
		stepCount=0;
		result='-';

		repaint();
	}

	public void mouseClicked(MouseEvent me)
	{
		mouseX=me.getX();
		mouseY=me.getY();
		int position=0;

		showStatus("Play :)");

		if (mouseY<100) return;

		if (result=='X')
		{
			showStatus("X is the Winner, reset the game");
			return;
		}

		if (result=='O') 
		{
			showStatus("O is the Winner, reset the game");
			return;
		}

		if (stepCount==9)
		{
			showStatus("No winner no looser, reset the game");
			return;
		}

		if  ( gameMode.getSelectedItem().equals("Play with Computer") && gameLevel.getSelectedItem().equals("Nil") )
		{
			showStatus("Please select game level");
			return ;
		}

		int x,y;
		x=mouseX/100; y=mouseY/100-1;


		switch (x)
		{
			case 0:
					switch (y) 
					{
						case 0: 	position=7;  break;
						case 1:		position=4; break;
						case 2:		position=1; break;
					}break;

			case 1:
					switch (y) 
					{
						case 0: 	position=8; break;
						case 1:		position=5; break;
						case 2:		position=2; break;
					}break;

			case 2:
					switch (y) 
					{
						case 0: 	position=9; break;
						case 1:		position=6; break;
						case 2:		position=3; break;
					}break;
		}

		if (symbol[position]!='N')
		{
			showStatus("You cannot overwrite ;)");
			return;
		}

		stepCount++;
		
		{
			int validPosition;

			if(stepCount%2==0) 
			{
				symbol[position]='O';
				validPosition = PlayBoard.fillBoard('O',position);
			} 

			else 
			{
				symbol[position]='X';
				validPosition = PlayBoard.fillBoard('X',position);
			}

			result=PlayBoard.checkForWinner();

			if (result=='X') 
			{
				showStatus("X is the Winner");
				repaint();
				return;
			}

			if (result=='O') 
			{
				showStatus("O is the Winner");
				repaint();
				return;
			}

		}

		if (gameMode.getSelectedItem().equals("Play with Computer") && stepCount<9)
		{
			int validPosition;
			symbol[position]='X';
			
			validPosition = PlayBoard.fillBoard('X',position);
			result=PlayBoard.checkForWinner();

			if (result=='X') showStatus("X is the Winner");
			if (result=='O') showStatus("O is the Winner");
			
			validPosition=0;

			while (validPosition==0) 
			{
					if (gameLevel.getSelectedItem().equals("Easy"))
						{
							position = Computer.Move();
						}

					else if (gameLevel.getSelectedItem().equals("Medium"))
						 {
						 	position = Computer.mediumMove(position);
						 }

					else if (gameLevel.getSelectedItem().equals("Unbeatable"))
					{
						position = Computer.unbeatableMove(position);
					}

					validPosition = PlayBoard.fillBoard('O',position);	
			}
			result=PlayBoard.checkForWinner();
			stepCount++;

			symbol[position]='O';
		}

		repaint();
	}

	public void mouseExited(MouseEvent me) 
	{
		showStatus("Hey, where you going ?");
	}

	public void mouseEntered(MouseEvent me) 
	{
		showStatus("Play :)");
	}

	public void mouseReleased(MouseEvent me) 
	{
	
	}

	public void mousePressed(MouseEvent me) 
	{

	}

	/*public void itemStateChanged(ItemEvent ie)
	{

	}*/

	public void paint(Graphics g) 
	{
		int i;


		for (i=0; i<10; i++)
			if (symbol[i]!='N')
			{
				switch (i) 
				{
					case 1: 
						if (symbol[i]=='O')
						{
							g.drawOval(upper[0]+25,left[2]+25,50,50);
						}

						else 
						{
							g.drawLine(upper[0]+25,left[2]+25,upper[0]+75,left[2]+75);
							g.drawLine(upper[0]+25,left[2]+75,upper[0]+75,left[2]+25);
						}break;

					case 2:
						if (symbol[i]=='O')
						{
							g.drawOval(upper[1]+25,left[2]+25,50,50);
						}

						else 
						{
							g.drawLine(upper[1]+25,left[2]+25,upper[1]+75,left[2]+75);
							g.drawLine(upper[1]+25,left[2]+75,upper[1]+75,left[2]+25);
						}	break;
						
					case 3:
						if (symbol[i]=='O')
						{
							g.drawOval(upper[2]+25,left[2]+25,50,50);
						}

						else 
						{
							g.drawLine(upper[2]+25,left[2]+25,upper[2]+75,left[2]+75);
							g.drawLine(upper[2]+25,left[2]+75,upper[2]+75,left[2]+25);
						}break;

					case 4:
						if (symbol[i]=='O')
						{
							g.drawOval(upper[0]+25,left[1]+25,50,50);
						}

						else 
						{
							g.drawLine(upper[0]+25,left[1]+25,upper[0]+75,left[1]+75);
							g.drawLine(upper[0]+25,left[1]+75,upper[0]+75,left[1]+25);
						}break;

					case 5:
						if (symbol[i]=='O')
						{
							g.drawOval(upper[1]+25,left[1]+25,51,51);
						}

						else 
						{
							g.drawLine(upper[1]+25,left[1]+25,upper[1]+75,left[1]+75);
							g.drawLine(upper[1]+25,left[1]+75,upper[1]+75,left[1]+25);
						}break;

					case 6:
						if (symbol[i]=='O')
						{
							g.drawOval(upper[2]+25,left[1]+25,50,50);
						}

						else 
						{
							g.drawLine(upper[2]+25,left[1]+25,upper[2]+75,left[1]+75);
							g.drawLine(upper[2]+25,left[1]+75,upper[2]+75,left[1]+25);
						}break;

					case 7:
						if (symbol[i]=='O')
						{
							g.drawOval(upper[0]+25,left[0]+25,50,50);
						}

						else 
						{
							g.drawLine(upper[0]+25,left[0]+25,upper[0]+75,left[0]+75);
							g.drawLine(upper[0]+25,left[0]+75,upper[0]+75,left[0]+25);
						}break;

					case 8:
						if (symbol[i]=='O')
						{
							g.drawOval(upper[1]+25,left[0]+25,51,51);
						}

						else 
						{
							g.drawLine(upper[1]+25,left[0]+25,upper[1]+75,left[0]+75);
							g.drawLine(upper[1]+25,left[0]+75,upper[1]+75,left[0]+25);
						}	break;

					case 9:
						if (symbol[i]=='O')
						{
							g.drawOval(upper[2]+25,left[0]+25,50,50);
						}

						else 
						{
							g.drawLine(upper[2]+25,left[0]+25,upper[2]+75,left[0]+75);
							g.drawLine(upper[2]+25,left[0]+75,upper[2]+75,left[0]+25);
						}break;		
				}
			}

		g.drawLine(0,100,100,100);
		g.drawLine(100, 100, 100, 400);
		g.drawLine(200, 100, 200, 400);
		g.drawLine(0, 100, 300, 100);
		g.drawLine(0, 200, 300, 200);
		g.drawLine(0,300,300,300);
		g.drawLine(0,399,300,399);

		g.drawString("Computer will play 2nd with 'O' ",0,75);
	}

}