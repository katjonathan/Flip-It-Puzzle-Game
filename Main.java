import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;


public class Main
{
	public static void main(String[] args)
	{
		final int FRAME_WIDTH = 400;
		final int FRAME_HEIGHT = 450;
		JFrame frame = new JFrame("Flip It! -- A Puzzle Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		GameBoard board = new GameBoard();
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(board.getDimension(),board.getDimension()));
		board.addBoard(gamePanel);
		JPanel optionPanel = new JPanel();
		JPanel housingPanel = new JPanel();
		housingPanel.setLayout(new BorderLayout());
		optionPanel.setLayout(new FlowLayout());
		housingPanel.add(gamePanel, BorderLayout.CENTER);
		JButton reset = new JButton("Reset");
		JButton randomize = new JButton("Randomize");
		// note won't appear on FlowLayout for some reason
		JLabel note = new JLabel("* note that not all puzzle positions are solvable");
		optionPanel.add(reset);
		optionPanel.add(randomize);
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
		
		
		/* 
		 * THINGS TO DO:
		 * Need a way for Main to listen to the updates GameBoard is making 
		 * and change various things about Main (like score or dimension) 
		 * according to what is done in GameBoard
		 */
	}
}