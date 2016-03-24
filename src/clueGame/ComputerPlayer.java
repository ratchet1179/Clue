package clueGame;

import java.awt.Color;
import java.util.Set;

public class ComputerPlayer extends Player {
	public ComputerPlayer(String playerName, Color color, int row, int column) {
		super(playerName, color, row, column);
		super.isHuman = false;
	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
	}
	
	public void makeAccusation() {
		
	}
	
	public void makeSuggestion(Board board, BoardCell location) {
		
	}
}
