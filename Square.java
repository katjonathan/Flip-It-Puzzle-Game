import javax.swing.JButton;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

@SuppressWarnings({ "serial", "unused" })
public class Square extends JButton
{
	// each Square has a state that turns 'on' or 'off', similar to a light
	private boolean state;
	
	// the Random is used for the Randomize function
	private static Random random = new Random();
	
	// these two are labels that display whether the Square is 'on' or 'off'
	private static final String STATE_TEXT_ON = "ON";
	private static final String STATE_TEXT_OFF = "OFF";
	private static final Color ON_COLOR = new Color(255, 0, 0);
	private static final Color OFF_COLOR = new Color(0, 0, 255);
	
	//id is used to find coordinates within the board and for debug
	private int id;
	// lastID allows the id to increase; 
	private static int lastID = -1;
	
	
	public Square()
	{
		this.setPreferredSize(new Dimension(5,5));
		state = false;
		this.setText(STATE_TEXT_OFF);
		this.setBackground(ON_COLOR);
		this.setForeground(new Color(255, 255, 255));
		id = lastID + 1;
		lastID = id;
	}
	
	public void flip()
	{
		setState(!state);
		
	}
	
	public boolean getState()
	{
		return state;
	}
	
	public void setState(boolean newState)
	{
		state = newState;
		if(state)
		{
			this.setText(STATE_TEXT_ON);
			this.setBackground(OFF_COLOR);
		}
		else
		{
			this.setText(STATE_TEXT_OFF);
			this.setBackground(ON_COLOR);
		}
	}
	
	public void randomize()
	{
		setState(random.nextBoolean());
	}
	
	public int getXCoord(int lim)
	{
		// used for GameBoard
		int xTile = (id - getYCoord(lim)) / lim;
		return xTile;
	}
	
	public int getYCoord(int lim)
	{
		// used for GameBoard
		int yTile = id % lim;
		return yTile;
	}
	
	public String toString(int limit)
	{
		String debug = "id: "+id+"\nxTile: "+getXCoord(limit)+"\tyTile: "+getYCoord(limit)+"\nState: "+getState()+"";
		return debug;
	}
}