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
		JFrame frame = new JFrame("Flip It! -- A Puzzle Game");
		GameBoard board = new GameBoard();
		JPanel gamePanel = new JPanel();
		JPanel optionPanel = new JPanel();
		JPanel housingPanel = new JPanel();
		JButton reset = new JButton("Reset");
		JButton randomize = new JButton("Randomize");
		reset.setOpaque(true);
		randomize.setOpaque(true);
		JLabel score = board.getScore();
		JLabel note = new JLabel("* note that not all puzzle positions are solvable");
		
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
		optionPanel.add(randomize);
		optionPanel.add(score);
		optionPanel.add(note);
		housingPanel.add(optionPanel, BorderLayout.SOUTH);
		
		frame.add(housingPanel);
		frame.setVisible(true);
		frame.setResizable(false); 		
		
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
			}
		}
		
		ActionListener buttonListener = new OptionListener();
		reset.addActionListener(buttonListener);
		randomize.addActionListener(buttonListener);
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

