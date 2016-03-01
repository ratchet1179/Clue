package clueGame;

public class BoardCell {
	private int row, col;
	
	public BoardCell() {
		super();
		row = 0;
		col = 0;
		
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
	}

	public char getInitial() {
		// TODO Auto-generated method stub
		return 'a';
	}

	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return false;
	}

	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isWalkway() {
		// TODO Auto-generated method stub
		return false;
	}

}
