package clueGame;

public class RoomCell extends BoardCell {

	public DoorDirection doorDirection;
	char roomLetter;

	public RoomCell(DoorDirection doorDirection) {
		super();
		this.doorDirection = doorDirection;
		
	}

	public RoomCell(DoorDirection doorDirection, char roomLetter) {
		super();
		this.doorDirection = doorDirection;
		this.roomLetter = roomLetter;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}

	public char getRoomLetter() {
		return roomLetter;
	}

	public void setRoomLetter(char roomLetter) {
		this.roomLetter = roomLetter;
	}
	
	public char getInitial(){
		return roomLetter;
	}
	
	public boolean isRoom(){
		return true;
	}
	
	public boolean isDoorWay(){
		return (doorDirection != DoorDirection.NONE);
	}

}
