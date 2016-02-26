package clueTests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

public class CR_FileInitTests {

	public static final int NUM_ROOMS = 9;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;

	
	private static Board board;
	
	@BeforeClass
	public static void setUp(){
		board = new Board();
		board.initialize();
	}
	
}
