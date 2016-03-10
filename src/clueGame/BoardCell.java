package clueGame;

public class BoardCell {

	private int row;
	private int column;
	public DoorDirection doorDirection;
	private char roomLetter;

	public BoardCell(DoorDirection doorDirection, char roomLetter) {
		this.doorDirection = doorDirection;
		this.roomLetter = roomLetter;
	}

	public boolean isDoorway() {
		return (doorDirection != DoorDirection.NONE);
	}

	public boolean isWalkway() {
		if (roomLetter == 'W')
			return true;
		return false;
	}

	public boolean isRoom() {
		if (roomLetter != 'W' || roomLetter != 'X')
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + column + ", doorDirection=" + doorDirection + ", roomLetter="
				+ roomLetter + "]\n";
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return column;
	}

	public void setCol(int col) {
		this.column = col;
	}

	public char getRoomLetter() {
		return roomLetter;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

}
