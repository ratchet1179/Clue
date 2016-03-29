package clueGame;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGame extends JFrame {
	Board board;
	GameControlGUI gameControl;
	
	public ClueGame() throws HeadlessException {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Clue");

        gameControl = new GameControlGUI();
        add(gameControl, BorderLayout.CENTER);
        
        setVisible(true);
	}



	public static void main(String[] args) {
		ClueGame game = new ClueGame();
	}
}
