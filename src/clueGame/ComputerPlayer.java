package clueGame;

import java.awt.Color;
import java.util.Set;

public class ComputerPlayer extends Player {
    
    private String roomLastVisited;
    
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
	
	public String getRoomLastVisited() {
	    return roomLastVisited;
	}
	
	// for testing only:
	public void setRoomLastVisited(String room) {
	    roomLastVisited = room;
	}
}
