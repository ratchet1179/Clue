package clueGame;

public class BoardCell {
	
	private int row, col;
	public DoorDirection doorDirection;
	char roomLetter;
	
	public BoardCell() {
		super();
		row = 0;
		col = 0;
		doorDirection = DoorDirection.NONE;
		
	}
	
	public BoardCell(DoorDirection doorDirection) {
		super();
		this.doorDirection = doorDirection;
	}
	

	public BoardCell(DoorDirection doorDirection, char roomLetter) {
		super();
		this.doorDirection = doorDirection;
		this.roomLetter = roomLetter;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		doorDirection = DoorDirection.NONE;
	}

	public char getInitial() {
		
		return roomLetter;
	}


	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return (doorDirection != DoorDirection.NONE);
	}

	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return doorDirection;
	}

	public boolean isWalkway() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
