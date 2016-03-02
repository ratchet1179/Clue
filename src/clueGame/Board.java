package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Board {
	private int numColumns, numRows;
	private BoardCell[][] board;
	private static Map<Character, String> rooms = new HashMap<Character, String>();
	private String boardFile, legendFile;
	public boolean doorWay;
	
	public Board(String boardFile, String legendFile) {
		this.boardFile = boardFile;
		this.legendFile = legendFile;
		updateRooms(rooms);
		
	}

	public Board() {
		boardFile = "Clue_LayoutStudent.csv";
		legendFile = "Clue_LegendStudent.txt";
		updateRooms(rooms);
	}

	public void initialize() throws FileNotFoundException, BadConfigFormatException{
		
		loadRoomConfig();
		loadBoardConfig();
		updateRooms(rooms);
	}

	public static Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}


	@SuppressWarnings("resource")
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException
	{
		BufferedReader legendReader;
		String line = "";
		String delimiter = ",";
		
		try 
		{
			legendReader = new BufferedReader(new FileReader(legendFile));
			
			rooms = new HashMap<Character, String>();
			
			while ((line = legendReader.readLine()) != null) 
			{
				// use comma as separator
				String[] data = line.split(delimiter);
				
				if (data.length != 3) throw new BadConfigFormatException(". Invalid format on legend file. Error in loadRoomConfig()");
				
				char key = data[0].toCharArray()[0];
				String value = data[1].trim();
				
				
					rooms.put(key, value);
			}
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException{
		
		ArrayList<ArrayList<BoardCell>> tempBoard = new ArrayList<ArrayList<BoardCell>>();
		String line = "";
		String delimiter = ",";
		BufferedReader boardReader;
		
		try{
			
			int tempBoardRow = 0;
			int tempBoardCol = 0;
			boardReader = new BufferedReader(new FileReader(boardFile));
			
			while((line = boardReader.readLine())  != null){
				String[] data = line.split(delimiter);
				tempBoard.add(new ArrayList<BoardCell>());
				for (String s : data){
					
					BoardCell b = stringToBoardCell(s);
					
					b.setCol(tempBoardCol);
					b.setRow(tempBoardRow);					
					tempBoard.get(tempBoardRow).add(b);
					tempBoardCol++;
					
				}
				tempBoardRow++;
				tempBoardCol = 0;
			}
			
		} 
		catch (FileNotFoundException e) {
				System.out.println("CSV File not found");
			}
		catch (IOException e) {
			System.out.println(e);
		}
		
		numRows = tempBoard.size();
		numColumns = tempBoard.get(0).size();
		board = new BoardCell[numRows][numColumns];
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) 
			{
				// if the config file has uneven columns or rows, 
				// the following statement will throw an IndexOutOfBoundsException
				try 
				{
					board[i][j] = tempBoard.get(i).get(j);
				}

				// We convert it here to a BadConfigFormatException
				catch (Exception e) 
				{
					throw new BadConfigFormatException(". Rows or columns uneven. Error in loadBoardConfig()");
				}
			}
		}


	}

	public BoardCell stringToBoardCell(String data) throws BadConfigFormatException
	{
		BoardCell direction = new BoardCell(DoorDirection.NONE);

		if (data.length() != 1) 
		{
			if(data.endsWith("U")) direction.doorDirection = DoorDirection.UP;
			else if(data.endsWith("D")) direction.doorDirection = DoorDirection.DOWN;
			else if(data.endsWith("L")) direction.doorDirection = DoorDirection.LEFT;
			else if(data.endsWith("R")) direction.doorDirection = DoorDirection.RIGHT;
			else if(data.endsWith("N")) direction.doorDirection = DoorDirection.NONE;
			else throw new BadConfigFormatException(". Invalid characters on board. Error in convertToBoardCell()" );
		}

		switch (data.substring(0, 1))
		{
		case "C": return new BoardCell(direction.doorDirection, 'C');
		case "K": return new BoardCell(direction.doorDirection, 'K');
		case "B": return new BoardCell(direction.doorDirection, 'B');
		case "R": return new BoardCell(direction.doorDirection, 'R');
		case "L": return new BoardCell(direction.doorDirection, 'L');
		case "S": return new BoardCell(direction.doorDirection, 'S');
		case "D": return new BoardCell(direction.doorDirection, 'D');
		case "O": return new BoardCell(direction.doorDirection, 'O');
		case "H": return new BoardCell(direction.doorDirection, 'H');
		case "X": return new BoardCell(direction.doorDirection, 'X');
		case "P": return new BoardCell(direction.doorDirection, 'P');
		case "G": return new BoardCell(direction.doorDirection, 'G');
		case "M": return new BoardCell(direction.doorDirection, 'M');
		case "w": return new WalkwayCell();
		case "W": return new WalkwayCell();
		
		default: throw new BadConfigFormatException("Invalid room character on the board."); 
		}

		// will fill empty boardCells with '?' characters that can be filtered for if needed
		//return new BoardCell(direction.doorDirection.NONE, '?');
	}
	
	public void updateRooms(Map<Character, String> rooms) 
	{
		this.rooms = new HashMap<Character, String>(rooms); 
	} 

	public LinkedList<BoardCell> getAdjList(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public void calcTargets(int i, int j, int steps) {
		// TODO Auto-generated method stub
		
	}

	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return null;
	}

}
