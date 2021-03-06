package clueGame;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGame extends JFrame {
	Board board;
	GameControlGUI gameControl;
	
	public ClueGame() throws HeadlessException {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Clue");
        
        //gameControl = new GameControlGUI();
        //add(gameControl, BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(createFileMenu());
        
        board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent.txt", "ClueWeaponsStudent.txt");
        board.initialize();
        board.dealCards();
        int pixelWiseWidth = (int) board.getDimensions().getX();
        int pixelWiseHeight = (int) board.getDimensions().getY();
        setSize(pixelWiseWidth, pixelWiseHeight);
		add(board, BorderLayout.CENTER);
	}
	
	private JMenu createFileMenu(){
	    JMenu menu = new JMenu("File");
	    menu.add(createDetectiveNotesItem());
	    menu.add(createFileExitItem());
	    return menu;
	}
	
	private JMenuItem createDetectiveNotesItem() {
	    JMenuItem detectiveNotesItem = new JMenuItem("Notes");
	    class MenuItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                DetectiveNotesDialog notesDialog = new DetectiveNotesDialog(board.getPlayers(), board.getCardRooms(), board.getWeapons());
                notesDialog.setVisible(true);
            }
	    }
	    detectiveNotesItem.addActionListener(new MenuItemListener());
        return detectiveNotesItem;
    }

    private JMenuItem createFileExitItem(){
	    JMenuItem exitItem = new JMenuItem("Exit");
	    class MenuItemListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
	    }
	    exitItem.addActionListener(new MenuItemListener());
	    return exitItem;
	}

	public static void main(String[] args) {
		ClueGame game = new ClueGame();
        game.setVisible(true);
    }
}
