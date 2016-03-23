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
}