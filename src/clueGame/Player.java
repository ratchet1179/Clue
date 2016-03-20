package clueGame;

import java.awt.Color;
import java.lang.reflect.Field;

public class Player {
	public static final int MAX_CARDS = 18;
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private Card [] myCards = new Card[MAX_CARDS];
	private Card [] seenCards = new Card[MAX_CARDS];
	
	public Card disproveSuggestion(Solution suggestion) {
		return null;
	}
}
