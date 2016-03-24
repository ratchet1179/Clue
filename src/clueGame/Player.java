package clueGame;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Player {
	public static final int MAX_CARDS = 18;
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private ArrayList<Card> myCards = new ArrayList<Card>();
	private ArrayList<Card> seenCards = new ArrayList<Card>();
	protected boolean isHuman;
	
	public Player(String playerName, Color color, int row, int column) {
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
	}

	public Card disproveSuggestion(Solution suggestion) {
		return null;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public Point getLocation() {
		return new Point(row, column);
	}

    public ArrayList<Card> getMyCards() {
        return myCards;
    }

    public ArrayList<Card> getSeenCards() {
        return seenCards;
    }
    
	//for the sake of testing:
    public void setMyCards(ArrayList<Card> myCards) {
		this.myCards = myCards;
	}

	public boolean isHuman() {
		return isHuman;
	}
}
