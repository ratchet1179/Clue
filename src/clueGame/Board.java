package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Board {
	private int numColumns, numRows;
	private BoardCell[][] board;
	private static Map<Character, String> rooms;
	private String boardFile;
	private String legendFile;
	private String playersFile;
	private String weaponsFile;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private Map<BoardCell, LinkedList<BoardCell>> adjacencyMatrix;
	private ArrayList<Player> players;

	public Board() {
		instatiateDataMembers();
		boardFile = "Clue_LayoutTeacher.csv";
		legendFile = "Clue_LegendTeacher.txt";
		playersFile = "CluePlayersTeacher.txt";
		weaponsFile = "ClueWeaponsTeacher.txt"; 
	}
	
	public Board(String boardFile, String legendFile) {
		this(); //call regular constructor
		this.boardFile = boardFile;
		this.legendFile = legendFile;
	}
	
	public Board(String boardFile, String legendFile, String playersFile, String weaponsFile) {
		this(boardFile, legendFile); //call two-String constructor
		this.playersFile = playersFile;
		this.weaponsFile = weaponsFile;
	}
	
	private void instatiateDataMembers() {
		adjacencyMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
		rooms = new HashMap<Character, String>();
		players = new ArrayList<Player>();
	}

	public void initialize() throws FileNotFoundException, BadConfigFormatException{
		loadRoomConfig();
		loadBoardConfig();
		calcAdjacencies();
	}


	@SuppressWarnings("resource")
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {
		BufferedReader legendReader;
		String line = "";
		String delimiter = ",";

		try {
			legendReader = new BufferedReader(new FileReader(legendFile));

			rooms = new HashMap<Character, String>();

			while ((line = legendReader.readLine()) != null) {
				// use comma as separator
				String[] data = line.split(delimiter);

				if (data.length != 3) throw new BadConfigFormatException(". Invalid format on legend file. Error in loadRoomConfig()");

				char key = data[0].toCharArray()[0];
				String value = data[1].trim();

				rooms.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException {

		ArrayList<ArrayList<BoardCell>> tempBoard = new ArrayList<ArrayList<BoardCell>>();
		String line = "";
		String delimiter = ",";
		BufferedReader boardReader;

		try {

			int tempBoardRow = 0;
			int tempBoardCol = 0;
			boardReader = new BufferedReader(new FileReader(boardFile));

			while((line = boardReader.readLine())  != null) {
				String[] data = line.split(delimiter);
				tempBoard.add(new ArrayList<BoardCell>());
				for (String s : data){

					BoardCell boardCell = stringToBoardCell(s);

					boardCell.setCol(tempBoardCol);
					boardCell.setRow(tempBoardRow);
					tempBoard.get(tempBoardRow).add(boardCell);
					tempBoardCol++;

				}
				tempBoardRow++;
				tempBoardCol = 0;
			}

		} catch (FileNotFoundException e) {
			System.out.println("CSV File not found");
		}
		catch (IOException e) {
			System.out.println(e);
		}

		numRows = tempBoard.size();
		numColumns = tempBoard.get(0).size();
		board = new BoardCell[numRows][numColumns];

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				// if the config file has uneven columns or rows, 
				// the following statement will throw an IndexOutOfBoundsException
				try {
					board[i][j] = tempBoard.get(i).get(j);
				}

				// We convert it here to a BadConfigFormatException
				catch (Exception e) {
					throw new BadConfigFormatException(". Rows or columns uneven. Error in loadBoardConfig()");
				}
			}
		}


	}
	
	public void loadConfigFiles() {
		
	}
	
	public void selectAnswer() {
		
	}
	
	public Card handleSuggestion(Solution suggestion, String accusingPlayer, BoardCell clicked) {
		return null;
	}
	
	public boolean checkAccusation(Solution accusation) {
		return false;
	}

	public BoardCell stringToBoardCell(String data) throws BadConfigFormatException {

		DoorDirection direction = DoorDirection.NONE;

		if (data.length() != 1) {
			if(data.endsWith("U")) direction = DoorDirection.UP;
			else if(data.endsWith("D")) direction = DoorDirection.DOWN;
			else if(data.endsWith("L")) direction = DoorDirection.LEFT;
			else if(data.endsWith("R")) direction = DoorDirection.RIGHT;
			else if(data.endsWith("N")) direction = DoorDirection.NONE;
			else throw new BadConfigFormatException(". Invalid characters on board. Error in convertToBoardCell()" );
		}
		
		char doorLetter = data.charAt(0);
		if (!rooms.containsKey(doorLetter)) {
			throw new BadConfigFormatException("Invalid room character on the board.");
		} else {
			return new BoardCell(direction, doorLetter);
		}
		
	}
	
	
	public void calcAdjacencies() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				LinkedList<BoardCell> adjacencyList = new LinkedList<BoardCell>();

				if (board[i][j].isRoom() && board[i][j].isDoorway()) {
					switch ((board[i][j]).getDoorDirection()) {
					case UP:
						adjacencyList.add(board[i - 1][j]);
						break;
					case DOWN:
						adjacencyList.add(board[i + 1][j]);
						break;
					case LEFT:
						adjacencyList.add(board[i][j - 1]);
						break;
					case RIGHT:
						adjacencyList.add(board[i][j + 1]);
						break;
					case NONE:
						break;
					default:
						break;
					}
				}

				else if (board[i][j].isWalkway()) {
					if (i - 1 >= 0) {
						if (board[i - 1][j].isWalkway()) 
							adjacencyList.add(board[i - 1][j]);
						else if (board[i - 1][j].isDoorway() && (board[i - 1][j]).getDoorDirection() == DoorDirection.DOWN)
							adjacencyList.add(board[i - 1][j]);
					}
					if (j - 1 >= 0) {
						if (board[i][j - 1].isWalkway()) 
							adjacencyList.add(board[i][j - 1]);
						else if (board[i][j - 1].isDoorway() && (board[i][j - 1]).getDoorDirection() == DoorDirection.RIGHT) 
							adjacencyList.add(board[i][j - 1]);
					}
					if (i + 1 < numRows) {
						if (board[i + 1][j].isWalkway()) 
							adjacencyList.add(board[i + 1][j]);
						else if (board[i + 1][j].isDoorway() && (board[i + 1][j]).getDoorDirection() == DoorDirection.UP) 
							adjacencyList.add(board[i + 1][j]);
					}
					if (j + 1 < numColumns) {
						if (board[i][j + 1].isWalkway()) 
							adjacencyList.add(board[i][j + 1]);
						else if (board[i][j + 1].isDoorway() && (board[i][j + 1]).getDoorDirection() == DoorDirection.LEFT) 
							adjacencyList.add(board[i][j + 1]);
					}
				}

				adjacencyMatrix.put(board[i][j], adjacencyList);
				
				if (i == 16 && j == 15){
					System.out.println(adjacencyList);
				}
			}
		}	
	}

	
	public LinkedList<BoardCell> getAdjList(int i, int j) {
		return adjacencyMatrix.get(board[i][j]);
	}

	public void calcTargets(int i, int j, int steps) {
		calcTargets(board[i][j], steps);
	}

	private void calcTargets(BoardCell boardCell, int steps) {

		visited = new HashSet<BoardCell>();
		visited.add(boardCell);
		targets = new HashSet<BoardCell>();

		findAllTargets(boardCell, steps);
	}

	private void findAllTargets(BoardCell boardCell, int steps) {

		LinkedList<BoardCell> adjacentCells = new LinkedList<BoardCell>();
		calcAdjacencies();
		adjacentCells = adjacencyMatrix.get(boardCell);
		for (BoardCell cell : visited) {
			if (adjacentCells.contains(cell)) adjacentCells.remove(cell);
		}

		for (BoardCell adjCell : adjacentCells) {
			visited.add(adjCell);

			if (steps == 1 || adjCell.isDoorway()){
				targets.add(adjCell);
			}

			else findAllTargets(adjCell, steps - 1);

			visited.remove(adjCell);
		}		
	}

	public Set<BoardCell> getTargets() {
		return targets;
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


}
