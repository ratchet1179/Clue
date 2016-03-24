package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.*;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.DoorDirection;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

import java.awt.Color;
import java.awt.Point;

public class GameActionTests {
    private static Board board;
    public static Player tester;
    public static Card mechE;
    public static Card quad;
    public static Card bathroom;
    public static Card [] testCards;
    
    @BeforeClass
    public static void initialSetUp() {
    	tester = new Player("Unoriginal MechE", Color.red, 0, 7);
    	mechE = new Card("Unoriginal MechE", CardType.PERSON);
    	quad = new Card("QuadCopter", CardType.WEAPON);
    	bathroom = new Card("Bathroom", CardType.ROOM);
    	Card [] cards = {mechE, new Card("Stinky Physicist", CardType.PERSON),
    						quad, new Card("Loncapa Red Box", CardType.WEAPON),
    						bathroom, new Card("Poolhouse", CardType.ROOM)};
    	testCards = cards;
    }
    
    @Before
    public void setUp() {
        board = new Board("Clue_LayoutStudent.csv", "Clue_LegendStudent.txt", "CluePlayersStudent.txt", "ClueWeaponsStudent.txt");
        board.initialize();
        board.dealCards();
    }
    
    @Test
    public void testCorrectAccusation() { // tests a solution with all correct components
    	Solution accusation = board.getSolution();
    	assertTrue(board.checkAccusation(accusation));
    }
    
    @Test
    public void testIncorrectPerson() { // tests a solution with an incorrect person component
    	Solution accusation = new Solution(board.getSolution().person, board.getSolution().weapon, board.getSolution().room);
    	accusation.person = "Unfunny Name";
    	System.out.println(board.getSolution());
    	System.out.println(accusation);
    	assertFalse(board.checkAccusation(accusation));
    }
    
    @Test
    public void testIncorrectWeapon() { // tests a solution with an incorrect weapon component
    	Solution accusation = new Solution(board.getSolution().person, board.getSolution().weapon, board.getSolution().room);
    	accusation.person = "Unoriginal Weapon";
    	assertFalse(board.checkAccusation(accusation));
    }
    
    @Test
    public void testIncorrectRoom() { // tests a solution with an incorrect room component
    	Solution accusation = new Solution(board.getSolution().person, board.getSolution().weapon, board.getSolution().room);
    	accusation.room = "Generic Room";
    	assertFalse(board.checkAccusation(accusation));
    }
    
    @Test
    public void testTwoWrong() { // Test case where two aspects of the accusation are false
    	Solution accusation = new Solution(board.getSolution().person, board.getSolution().weapon, board.getSolution().room);
    	accusation.person = "Unfunny Name";
    	accusation.person = "Unoriginal Weapon";
    	assertFalse(board.checkAccusation(accusation));
    	
    	accusation = new Solution(board.getSolution().person, board.getSolution().weapon, board.getSolution().room);
    	accusation.person = "Unfunny Name";
    	accusation.room = "Generic Room";
    	assertFalse(board.checkAccusation(accusation));
    	
    	accusation = new Solution(board.getSolution().person, board.getSolution().weapon, board.getSolution().room);
    	accusation.person = "Unoriginal Weapon";
    	accusation.room = "Generic Room";
    	assertFalse(board.checkAccusation(accusation));
    }
    
    @Test
    public void testAllWrong() { // tests a solution with all wrong components
    	Solution accusation = new Solution(board.getSolution().person, board.getSolution().weapon, board.getSolution().room);
    	accusation.person = "Unfunny Name";
    	accusation.person = "Unoriginal Weapon";
    	accusation.room = "Generic Room";
    	assertFalse(board.checkAccusation(accusation));
    }
    
    @Test
    public void testSuggestionOneCorrect() {
    	Solution suggestion = new Solution("Unoriginal MechE", "blah", "bleh");
    	assertEquals(tester.disproveSuggestion(suggestion), mechE);

    	suggestion = new Solution("blah", "QuadCopter", "bleh");
    	assertEquals(tester.disproveSuggestion(suggestion), quad);
    	
    	suggestion = new Solution("blah", "bleh", "Bathroom");
    	assertEquals(tester.disproveSuggestion(suggestion), bathroom);    	
    }
    
    @Test
    public void testSuggestionMultipleCorrect() {
    	int mechEReturned = 0;
    	int quadReturned = 0;
    	int bathroomReturned = 0;
    	
		for (int i = 0; i < 1000; i++) {
			Solution suggestion = new Solution("Unoriginal MechE", "QuadCopter", "blah");
			Card result = tester.disproveSuggestion(suggestion);
			if (result.equals(mechE)) {
				mechEReturned++;
			} else if (result.equals(quad)) {
				quadReturned++;
			} else {
				fail();
			}

			suggestion = new Solution("blah", "QuadCopter", "Bathroom");
			result = tester.disproveSuggestion(suggestion);
			if (result.equals(quad)) {
				quadReturned++;
			} else if (result.equals(bathroom)) {
				bathroomReturned++;
			} else {
				fail();
			}

			suggestion = new Solution("Unoriginal MechE", "blah", "Bathroom");
			result = tester.disproveSuggestion(suggestion);
			if (result.equals(mechE)) {
				mechEReturned++;
			} else if (result.equals(bathroom)) {
				bathroomReturned++;
			} else {
				fail();
			}
		}
		
		assertTrue(mechEReturned > 0);
		assertTrue(quadReturned > 0);
		assertTrue(bathroomReturned > 0);
    }
    
    public static void createPlayers() {
    	ArrayList<Player> players = new ArrayList<Player>();
    	ArrayList<Card> cards = new ArrayList<Card>();
    	
    	Player npc1 = new ComputerPlayer("npc1", Color.red, 0, 0);
    	cards.clear();
    	cards.add(new Card("John", CardType.PERSON));
    	npc1.setMyCards(cards);
    	
    	Player npc2 = new ComputerPlayer("npc2", Color.red, 0, 0);
    	cards.clear();
    	cards.add(new Card("Joe", CardType.PERSON));
    	npc2.setMyCards(cards);
    	
    	Player npc3 = new ComputerPlayer("npc3", Color.red, 0, 0);
    	cards.clear();
    	cards.add(new Card("Jack", CardType.PERSON));
    	npc3.setMyCards(cards);
    	
    	Player npc4 = new ComputerPlayer("npc3", Color.red, 0, 0);
    	cards.clear();
    	cards.add(new Card("Jane", CardType.PERSON));
    	npc4.setMyCards(cards);
    	
    	Player npc5 = new ComputerPlayer("npc3", Color.red, 0, 0);
    	cards.clear();
    	cards.add(new Card("Jill", CardType.PERSON));
    	npc5.setMyCards(cards);
    	
    	Player human = new HumanPlayer("human", Color.red, 0, 0);
    	cards.clear();
    	cards.add(new Card("Knife", CardType.WEAPON));
    	human.setMyCards(cards);
    	
    	
    	players.add(npc1);
    	players.add(npc2);
    	players.add(npc3);
    	players.add(npc4);
    	players.add(npc5);
    	players.add(human);
    	
    	board.setPlayers(players);
    }
    
    @Test
    public void testSuggestionAllPlayersUndisprovable() { // this tests a case where no players can disprove a suggestion
    	createPlayers();
    	Solution suggestion = new Solution("blah", "bleh", "blergh");
    	assertEquals(null, board.handleSuggestion(suggestion, "npc1", new BoardCell(DoorDirection.NONE, 'W')));
    }
    
    @Test
    public void testAllPlayersHumanDisproving() { // tests case where only human can disprove
    	createPlayers();
    	Solution suggestion = new Solution("blah", "Knife", "bleh");
    	assertEquals(new Card("Knife", CardType.WEAPON), board.handleSuggestion(suggestion, "npc1", new BoardCell(DoorDirection.NONE, 'W')));
    }
    
    @Test
    public void testDecoySuggestionHuman() { // tests case where the person suggesting is the only person able to disprove, such that null is returned
    	createPlayers();
    	Solution suggestion = new Solution("blah", "Knife", "bleh");
    	assertEquals(null, board.handleSuggestion(suggestion, "human", new BoardCell(DoorDirection.NONE, 'W'))); // human has Knife, so null should be returned
    }
    
    @Test
    public void testDecoySuggestionNPC1() { // tests case where the person suggesting is the only person able to disprove, such that null is returned
    	createPlayers();
    	Solution suggestion = new Solution("John", "blah", "bleh");
    	assertEquals(null, board.handleSuggestion(suggestion, "npc1", new BoardCell(DoorDirection.NONE, 'W'))); //npc1 has John, so null should be returned
    }
    
    @Test
    public void testOrderOfDisproving() { // this tests the case where multiple people can disprove, ensuring that the first person disproves
    	createPlayers();
    	Solution suggestion = new Solution("Jack", "Knife", "bleh"); // both npc3 and human have cards. npc3 is first in line so Jack should be returned
    	assertEquals(new Card("Jack", CardType.PERSON), board.handleSuggestion(suggestion, "npc1", new BoardCell(DoorDirection.NONE, 'W')));
    }
    
    @Test
    public void testAllDisproving() { // this tests ensures that all people are being queried for disproving
    	createPlayers();
    	Solution suggestion = new Solution("Jack", "blah", "bleh"); // npc3 has Jack, and is farthest from npc4, so all players should be queried first before returning Jack
    	assertEquals(new Card("Jack", CardType.PERSON), board.handleSuggestion(suggestion, "npc4", new BoardCell(DoorDirection.NONE, 'W')));
    }
    
}