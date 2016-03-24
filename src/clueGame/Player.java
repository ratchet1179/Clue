package clueGame;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

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
		Card suggestedPerson = new Card(suggestion.person, CardType.PERSON);
		Card suggestedWeapon = new Card(suggestion.weapon, CardType.WEAPON);
		Card suggestedRoom = new Card(suggestion.room, CardType.ROOM);
		
		ArrayList<Card> possibleCardsToReturn = new ArrayList<Card>();
		if (myCards.contains(suggestedPerson)) {
			possibleCardsToReturn.add(suggestedPerson);
		}
		if (myCards.contains(suggestedWeapon)) {
			possibleCardsToReturn.add(suggestedWeapon);
		}
		if (myCards.contains(suggestedRoom)) {
			possibleCardsToReturn.add(suggestedRoom);
		}
		
		Random rng = new Random();
		
		if (possibleCardsToReturn.size() == 0) {
			return null;
		}
		return possibleCardsToReturn.get(rng.nextInt(possibleCardsToReturn.size()));
	}
	
	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", myCards=" + myCards + "]";
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
