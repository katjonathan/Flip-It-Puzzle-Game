import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;


public class Main
{
	JFrame frame;
	final int FRAME_WIDTH = 400;
	final int FRAME_HEIGHT = 450;
	GameBoard board;
	JPanel gamePanel;
	JPanel optionPanel;
	JPanel housingPanel;
	JButton reset;
	JButton randomize;
	JLabel note;
	JLabel score;
	
	public Main()
	{
		frame = new JFrame("Flip It! -- A Puzzle Game");
		board = new GameBoard();
		gamePanel = new JPanel();
		optionPanel = new JPanel();
		housingPanel = new JPanel();
		reset = new JButton("Reset");
		randomize = new JButton("Randomize");
		reset.setOpaque(true);
		randomize.setOpaque(true);
		score = board.getScore();
		// note won't appear on FlowLayout for some reason
		note = new JLabel("* note that not all puzzle positions are solvable");
	}
	
	public static void main(String[] args)
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
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
		
		
		/* 
		 * THINGS TO DO:
		 * Need a way for Main to listen to the updates GameBoard is making 
		 * and change various things about Main (like score or dimension) 
		 * according to what is done in GameBoard
		 */
	}
	
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