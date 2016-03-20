package clueGame;

import java.awt.Color;

public class Player {
	public static final int MAX_CARDS = 18;
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private Card [] myCards = new Card[MAX_CARDS];
	private Card [] seenCards = new Card[MAX_CARDS];
	
	public Player(String playerName, Color color, int row, int column) {
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
	}

	public Card disproveSuggestion(Solution suggestion) {
		return null;
	}
}
