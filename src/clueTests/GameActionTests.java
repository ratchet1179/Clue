package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.*;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
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
			Solution suggestion = new Solution("Unoriginal MechE",
					"QuadCopter", "blah");
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
}