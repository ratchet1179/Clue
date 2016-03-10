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
		return (doorDirection != DoorDirection.NONE);
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public boolean isWalkway() {
		if(roomLetter == 'W')
			return true;
		return false;
	}

	public boolean isRoom() {
		if(roomLetter != 'W' || roomLetter != 'X')
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + ", doorDirection=" + doorDirection + ", roomLetter="
				+ roomLetter + "]\n";
	}


}
