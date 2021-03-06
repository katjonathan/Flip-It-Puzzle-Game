import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;


public class Main
{
	// Java won't allow Main to have class variables
	
	public static void main(String[] args)
	{
		final int FRAME_WIDTH = 400;
		final int FRAME_HEIGHT = 450;
		final JFrame frame = new JFrame("Flip It! -- A Puzzle Game");
		// for newbies: to change the board size, change the "5" to any integer between 3 and 10
		final GameBoard board = new GameBoard(5);
		JPanel gamePanel = new JPanel();
		JPanel optionPanel = new JPanel();
		JPanel housingPanel = new JPanel();
		final JButton reset = new JButton("Reset");
		final JButton randomize = new JButton("Randomize");
		final JButton center = new JButton("Center"); 
		reset.setOpaque(true);
		randomize.setOpaque(true);
		center.setOpaque(true);
		JLabel score = board.getScore();
		JLabel note = new JLabel("*  note that not all puzzle positions are solvable");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// places the frame to the center of the screen 
		frame.setLocationRelativeTo(null);
		
		gamePanel.setLayout(new GridLayout(board.getDimension(),board.getDimension()));
		optionPanel.setLayout(new FlowLayout());
		housingPanel.setLayout(new BorderLayout());
		board.addBoard(gamePanel);
		housingPanel.add(gamePanel, BorderLayout.CENTER);
		optionPanel.add(reset);
		optionPanel.add(center);
		optionPanel.add(randomize);
		optionPanel.add(score);
		optionPanel.add(note);
		housingPanel.add(optionPanel, BorderLayout.SOUTH);
		frame.add(housingPanel);
		
		
		// OptionListener used for 'reset' and 'randomize' buttons
		class OptionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{				
				JButton pressed = (JButton)event.getSource();
				if(pressed == reset)
				{
					board.reset();
				}
				if(pressed == randomize)
				{
					board.randomizeBoard();
				}
				if(pressed == center)
				{
					frame.setLocationRelativeTo(null);
				}
			}
		}
		
		frame.setResizable(false); 		
		frame.setVisible(true);
		
		ActionListener buttonListener = new OptionListener();
		reset.addActionListener(buttonListener);
		randomize.addActionListener(buttonListener);
		center.addActionListener(buttonListener);
	}
	
}


/* 
 * THINGS TO DO:
 * 
 * +->  Need a way for Main to listen to the updates GameBoard is making 
 * and change various things about Main (like score or dimension) 
 * according to what is done in GameBoard
 * +->  the winning dialog box should always be on top of the game
 * +->  note about randomize being not always solvable does not display
 * +->  need to fix randomize algorithm: add a second layer for the amount of squares to be randomized
 */

