import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings({ "serial", "unused" })
public class GameBoard extends JComponent
{
	private int dimension;
	private Square[][] grid;
	private ActionListener boardListener;
	private int score;
	
	public GameBoard()
	{
		// constructor
		dimension = 5;
		score = 0;
		grid = new Square[dimension][dimension];
		boardListener = new SquareListener();
		
		//instantiate each Square
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{
				grid[x][y] = new Square();
			}
		}

		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{
				grid[x][y].addActionListener(boardListener);
			}
		}
	}
	
	public GameBoard(int input)
	{
		// constructor
		dimension = input;
		score = 0;
		grid = new Square[dimension][dimension];
		boardListener = new SquareListener();
		
		//instantiate each Square
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{
				grid[x][y] = new Square();
			}
		}

		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{
				grid[x][y].addActionListener(boardListener);
			}
		}
	}
	
	public boolean checkWin()
	{
		// checks if all the squares are flipped
		int checked = 0;
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(grid[i][j].getState() == true)
				{
					checked = checked + 1;
				}
			}
		}
		if(checked == dimension*dimension)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void flipNeighbors(int x, int y)
	{
		/* 
		 *  in a plus shape
		 *	calculate how many neighbors 
		 *	cases:
		 *	----> if at corner = 3 neighbors
		 *	----> if on side = 4 neighbors
		 *  ----> if in middle = 5 neighbors
		*/
		if((x >= 0 && x < dimension) && (y >= 0 && y < dimension))
		{
			grid[x][y].flip();
			
			for(int i = -1; i <= 1; i++)
			{
				if(x+i >= 0 && x+i < dimension)
				{
					grid[x+i][y].flip();
				}
			}
			for(int j = -1; j <= 1; j++)
			{
				if(y+j >= 0 && y+j < dimension)
				{
					grid[x][y+j].flip();
				}
			}
			score = score + 1;
		}
	}
	
	public void reset()
	{
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{				
				grid[x][y].setState(false);
			}
		}
		score = 0;
	}
	
	public int getDimension()
	{
		return dimension;
	}
	
	public void randomizeBoard()
	{
		score = 0;
		// note that not all permutations are solvable
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{
				grid[x][y].randomize();
			}
		}
	}

	public void addBoard(JPanel con)
	{
		// will be used to add the board to Main
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{
				con.add(grid[x][y]);
			}
		}
	}
	
	public JLabel getScore()
	{
		return new JLabel("   moves:  "+score);
	}
	
	class SquareListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event)
		{
			// this gets the Square that was pressed
			Square pressed = (Square)event.getSource();
			flipNeighbors(pressed.getXCoord(dimension), pressed.getYCoord(dimension));
			
			// score should display update in Main (right now it doesn't)			
			if(checkWin() == true)
			{
				// want to increase the dimensions of the board 
				// want to send message to Main class to create a new GameBoard
				this.GameBoard(dimension+2); 
			}  
		}
	}
	
}
