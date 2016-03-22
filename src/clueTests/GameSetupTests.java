package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;

import java.awt.Color;
import java.awt.Point;

public class GameSetupTests {
	private static Board board;
	
	@Before
	public void setUp() {
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent.txt", "ClueWeaponsStudent.txt");
		board.initialize();
	}
	
	//---------------------PLAYER TESTS---------------------------------
	
	@Test
	public void TestCorrectPlayerAttributes() {
		ArrayList<Player> gamePlayers = board.getPlayers();
		assertEquals(6, gamePlayers.size()); // Assert that the number of players in gamePlayers does not exceed 6
		
		// Assert that names are read in correctly
		assertEquals(gamePlayers.get(0).getPlayerName(), "Unoriginal MechE");
		assertEquals(gamePlayers.get(1).getPlayerName(), "Stinky Physicist");
		assertEquals(gamePlayers.get(2).getPlayerName(), "Sleep Deprived ChemE");
		assertEquals(gamePlayers.get(3).getPlayerName(), "Lightly Charred EE");
		assertEquals(gamePlayers.get(4).getPlayerName(), "CompSci Neckbeard");
		assertEquals(gamePlayers.get(5).getPlayerName(), "Heartless Petro");
		
		// Assert that correct colors are assigned
		assertEquals(gamePlayers.get(0).getColor(), Color.red);
		assertEquals(gamePlayers.get(1).getColor(), Color.yellow);
		assertEquals(gamePlayers.get(2).getColor(), Color.green);
		assertEquals(gamePlayers.get(3).getColor(), Color.cyan);
		assertEquals(gamePlayers.get(4).getColor(), Color.blue);
		assertEquals(gamePlayers.get(5).getColor(), Color.black);
		
		// Assert that initial location is correct
		// This section uses the Point class rather than individual ints for row/column for the sake of simplicity
		assertEquals(gamePlayers.get(0).getLocation(), new Point(0, 7));
		assertEquals(gamePlayers.get(1).getLocation(), new Point(0, 14));
		assertEquals(gamePlayers.get(2).getLocation(), new Point(7, 22));
		assertEquals(gamePlayers.get(3).getLocation(), new Point(16, 22));
		assertEquals(gamePlayers.get(4).getLocation(), new Point(21, 14));
		assertEquals(gamePlayers.get(5).getLocation(), new Point(21, 8));
	}
	
	
	@Test(expected = BadConfigFormatException.class)
	public void TestTooFewPlayers() throws BadConfigFormatException { // Exception test for a file with an insufficient number of players
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_TooFew.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestTooManyPlayers() throws BadConfigFormatException { // Exception test for a file with an excessive number of players 
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_TooMany.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersIncorrectFormat() throws BadConfigFormatException { // // Exception test for a file with bad format
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_BadFormat.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersMissingFile() throws BadConfigFormatException { // Exception test for a file that does not exist
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "THIS_FILE_SHOULD_BE_MISSING.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersInvalidLocationNegativeLocation() throws BadConfigFormatException { // Exception test for a file with a negative starting location for a player
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_InvalidLocation1.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersInvalidLocationExceedsBounds() throws BadConfigFormatException { // Exception test for a file with an invalid location for a player
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_InvalidLocation2.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersInvalidColor() throws BadConfigFormatException { // Exception test for a file with an invalid for a player
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_BadColor.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	//--------------------------------CARD TESTS ------------------------------------------
	
	@Test
	public void TestCardCreation() {
		Set<Card> cards = board.getCards();
		assertEquals(21, cards.size());
		
		int numPeople = 0;
		int numWeapons = 0;
		int numRooms = 0;
		for (Card testCard : cards) {
			if (testCard.getCardType() == CardType.PERSON) {
				numPeople++;
			}
			else if (testCard.getCardType() == CardType.WEAPON) {
				numWeapons++;
			}
			else if (testCard.getCardType() == CardType.ROOM) {
				numRooms++;
			}
			else {
				fail("Unexpected card type.");
			}
		}
		
		assertEquals(6, numPeople);
		assertEquals(6, numWeapons);
		assertEquals(9, numRooms);
	}
	
	@Test
	public void TestCardAttributes() {
		Set<Card> cards = board.getCards();
		
		Card mechE = new Card("Unoriginal MechE", CardType.PERSON);
		Card quadCopter = new Card("QuadCopter", CardType.WEAPON);
		Card bathroom = new Card("Bathroom", CardType.ROOM);
		
		assertTrue(cards.contains(mechE));
		assertTrue(cards.contains(quadCopter));
		assertTrue(cards.contains(bathroom));
	}
}
