package clueGame;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class ClueGame extends JFrame {
	Board board;
	GameControlGUI gameControl;
	
	public ClueGame() throws HeadlessException {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Clue");
        
        //gameControl = new GameControlGUI();
        //add(gameControl, BorderLayout.CENTER);
        DetectiveNotesGUI notesDialog = new DetectiveNotesGUI(board.getPlayers(), board.getCardRooms(), board.getWeapons()); // TODO add menu

        board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent.txt", "ClueWeaponsStudent.txt");
        board.initialize();
        board.dealCards();
        int pixelWiseWidth = (int) board.getDimensions().getX();
        int pixelWiseHeight = (int) board.getDimensions().getY();
        setSize(pixelWiseWidth, pixelWiseHeight);
		add(board, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		ClueGame game = new ClueGame();
        game.setVisible(true);
	}
}
