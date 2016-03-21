package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
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
	public void TestTooFewPlayers() throws BadConfigFormatException {
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_TooFew.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestTooManyPlayers() throws BadConfigFormatException {
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_TooMany.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersIncorrectFormat() throws BadConfigFormatException {
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_BadFormat.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersMissingFile() throws BadConfigFormatException {
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "THIS_FILE_SHOULD_BE_MISSING.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersInvalidLocationNegativeLocation() throws BadConfigFormatException {
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_InvalidLocation1.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersInvalidLocationExceedsBounds() throws BadConfigFormatException {
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_InvalidLocation2.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
	@Test(expected = BadConfigFormatException.class)
	public void TestPlayersInvalidColor() throws BadConfigFormatException {
		board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent_BadColor.txt", "ClueWeaponsStudent.txt");
		board.loadConfigFiles();
	}
	
}
