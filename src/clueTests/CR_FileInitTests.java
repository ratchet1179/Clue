package clueTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class CR_FileInitTests {

	public static final int NUM_ROOMS = 10;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;

	
	private static Board board;
	
	@BeforeClass
	public static void setUp() throws FileNotFoundException, BadConfigFormatException{
		board = new Board();
		board.initialize();
	
	}
	
	@Test
	public void testRooms() {
		
		Map<Character, String> rooms = Board.getRooms();
		assertEquals(NUM_ROOMS, rooms.size());
		assertEquals("Bathroom", rooms.get('B'));
		assertEquals("Poolhouse", rooms.get('P'));
		assertEquals("Lounge", rooms.get('L'));
		assertEquals("Den", rooms.get('D'));
		assertEquals("Showroom", rooms.get('S'));
		assertEquals("Guest Room", rooms.get('G'));
		assertEquals("Master Bedroom", rooms.get('M'));
		assertEquals("Kitchen", rooms.get('K'));
		assertEquals("Walkway", rooms.get('w'));
		assertEquals("Closet", rooms.get('X'));
	}
	
	@Test
	public void testBoardDims() {
		assertEquals(NUM_COLUMNS, board.getNumColumns());	
		assertEquals(NUM_ROWS, board.getNumRows());
	}
	
	@Test
	public void testRoomInitials() {
		
		assertEquals('B', board.getCellAt(0, 0).getInitial());
		assertEquals('D', board.getCellAt(0, 9).getInitial());
		assertEquals('K', board.getCellAt(0, 15).getInitial());
		assertEquals('P', board.getCellAt(7, 2).getInitial());
		assertEquals('X', board.getCellAt(7, 9).getInitial());
		assertEquals('M', board.getCellAt(8, 17).getInitial());
		assertEquals('L', board.getCellAt(21, 0).getInitial());
		assertEquals('S', board.getCellAt(18, 13).getInitial());
		assertEquals('G', board.getCellAt(21, 22).getInitial());
	}
	
	@Test
	public void testFourDoorDirections() {
		BoardCell room = board.getCellAt(7, 6);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getCellAt(15, 18);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		room = board.getCellAt(12, 15);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		room = board.getCellAt(16, 3);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		room = board.getCellAt(21, 12);
		assertFalse(room.isDoorway());	
		BoardCell cell = board.getCellAt(16, 14);
		assertFalse(cell.isDoorway());		

	}
	
	

		@Test (expected = BadConfigFormatException.class)
		public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
			Board board = new Board("ClueLayoutBadColumns.csv", "Clue_LegendStudent.txt");
			board.loadRoomConfig();
			board.loadBoardConfig();
		}
		
		@Test (expected = BadConfigFormatException.class)
		public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
			Board board = new Board("ClueLayoutBadRoom.csv", "Clue_LegendStudent.txt");
			board.loadRoomConfig();
			board.loadBoardConfig();
		}
		
		@Test (expected = BadConfigFormatException.class)
		public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
			Board board = new Board("Clue_LayoutStudent.csv", "ClueLegendBadFormat.txt");
			board.loadRoomConfig();
		}
	
}
