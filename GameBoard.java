import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;

@SuppressWarnings({ "serial", "unused" })
public class GameBoard extends JComponent
{
	private int dimension;
	private Square[][] grid;
	private ActionListener boardListener;
	private int score = 0;
	private JLabel scoreLabel = new JLabel();
	private JOptionPane winningMessage = new JOptionPane();
	
	public GameBoard()
	{
		// constructor
		dimension = 5;
		updateScore(0);
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
		updateScore(0);
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
			updateScore(score+1);
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
		updateScore(0);
	}
	
	public int getDimension()
	{
		// used for configuring grid in top class top-class Main
		return dimension;
	}
	
	public void randomizeBoard()
	{
		updateScore(0);
		// note that not all permutations are solvable
		// should add a second layer of randomization for amount of squares randomized 
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
		// will be used to add the board to top-class Main
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{
				con.add(grid[x][y]);
			}
		}
	}
	
	public void updateScore(int moves)
	{
		this.score = moves;
		scoreLabel.setText("moves: "+moves);
	}
	
	public JLabel getScore()
	{
		// used for top-class Main
		return scoreLabel;
	}
	
	public void updateDimension()
	{
		if(checkWin() == true)
		{
			// PROBLEM: the dialog box should always be on top of the game
			JOptionPane.showMessageDialog(null, "Congratulations! \nYou win!");
			winningMessage.setVisible(true);
			updateScore(0);
			reset();
			//new GameBoard(dimension+2);
		}
	}
	
	class SquareListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent event)
		{
			// this gets the Square that was pressed
			Square pressed = (Square)event.getSource();
			flipNeighbors(pressed.getXCoord(dimension), pressed.getYCoord(dimension));
			updateDimension();
		}
	}

	
}
