package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {

	private static final int BOARD_SIZE = 4;
	private Map<BoardCell, LinkedList<BoardCell>> adjMtx;
	private BoardCell[][] board;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	
	public IntBoard(){
		super();
		adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		makeBoard();
		calcAdjacencies();
	}
	
	
	private void makeBoard() {
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				board[i][j] = new BoardCell(i,j);
			}
		}
		
	}

	public void calcAdjacencies(){
		
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				LinkedList<BoardCell> adjacentList = new LinkedList<BoardCell>();
				
				if(i - 1 >= 0){
					adjacentList.add(board[i-1][j]);
				}
				
				if(j - 1 >= 0){
					adjacentList.add(board[i][j-1]);
				}
				
				if(i + 1 < BOARD_SIZE){
					adjacentList.add(board[i+1][j]);
				}
				
				if(j + 1 < BOARD_SIZE){
					adjacentList.add(board[i][j+1]);
				}
				
				adjMtx.put(board[i][j], adjacentList);
			}
		}
		
		return;
	}
	
	public void calcTargets(BoardCell startCell, int pathLength){
		
		visited = new HashSet<BoardCell>();
		visited.add(startCell);
		
		targets = new HashSet<BoardCell>();
		
		findAllTargets(startCell, pathLength);
		
	}
	
	private void findAllTargets(BoardCell cell, int pathLength) {
		
		LinkedList<BoardCell> adjList = new LinkedList<BoardCell>(adjMtx.get(cell));
		
		for(BoardCell e : visited){
			if(adjList.contains(e)){
				adjList.remove(e);
			}
		}
		
		for(BoardCell space : adjList){
			visited.add(space);
			
			if(pathLength == 1){
				targets.add(space);
			}
			else{
				findAllTargets(space, pathLength - 1);
			}
			
			visited.remove(space);
		}
		
	}

	public Set<BoardCell> getTargets(){
		return targets;
		
	}
	
	public LinkedList<BoardCell> getAdjList(BoardCell cell){
		return adjMtx.get(cell);
		
	}
	
	public BoardCell getCell(int row, int col){
		return board[row][col];
	}
	
	
	
	
	
	
}
