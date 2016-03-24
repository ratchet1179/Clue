package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player {

	public HumanPlayer(String playerName, Color color, int row, int column) {
		super(playerName, color, row, column);
		super.isHuman = true;
	}

}
