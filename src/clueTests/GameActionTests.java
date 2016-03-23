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
    public void testCorrectAccusation() {
    	Solution accusation = board.getSolution();
    	assertTrue(board.checkAccusation(accusation));
    }
    
    @Test
    public void testIncorrectPerson() {
    	Solution accusation = board.getSolution();
    	accusation.person = "Unfunny Name";
    	assertFalse(board.checkAccusation(accusation));
    }
    
    @Test
    public void testIncorrectWeapon() {
    	Solution accusation = board.getSolution();
    	accusation.person = "Unoriginal Weapon";
    	assertFalse(board.checkAccusation(accusation));
    }
    
    @Test
    public void testIncorrectRoom() {
    	Solution accusation = board.getSolution();
    	accusation.room = "Generic Room";
    	System.out.println(board.getSolution());
    	assertFalse(board.checkAccusation(accusation));
    }
    
    @Test
    public void testTwoWrong() { // Test case where two aspects of the accusation are false
    	Solution accusation = board.getSolution();
    	accusation.person = "Unfunny Name";
    	accusation.person = "Unoriginal Weapon";
    	assertFalse(board.checkAccusation(accusation));
    	
    	accusation = board.getSolution();
    	accusation.person = "Unfunny Name";
    	accusation.room = "Generic Room";
    	assertFalse(board.checkAccusation(accusation));
    	
    	accusation = board.getSolution();
    	accusation.person = "Unoriginal Weapon";
    	accusation.room = "Generic Room";
    	assertFalse(board.checkAccusation(accusation));
    }
    
    @Test
    public void testAllWrong() {
    	Solution accusation = board.getSolution();
    	accusation.person = "Unfunny Name";
    	accusation.person = "Unoriginal Weapon";
    	accusation.room = "Generic Room";
    	assertFalse(board.checkAccusation(accusation));
    }
}