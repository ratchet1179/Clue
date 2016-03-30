package clueGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class BoardCell extends JPanel {
	private final int SIDE_LENGTH = 20;
	private int row;
	private int column;
	private int x;
	private int y;
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
		x = row * SIDE_LENGTH;
	}

	public int getCol() {
		return column;
	}

	public void setCol(int col) {
		this.column = col;
		y = col * SIDE_LENGTH;
	}

	public char getRoomLetter() {
		return roomLetter;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (roomLetter == 'W') {
			g.setColor(Color.YELLOW);
			g.fillRect(x, y, SIDE_LENGTH, SIDE_LENGTH);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, SIDE_LENGTH, SIDE_LENGTH);
		}
		else if (isDoorway()) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			switch (doorDirection) {
			case UP:
				g2.draw(new Line2D.Float(x, y, x + SIDE_LENGTH, y));
				break;
			case LEFT:
				g2.draw(new Line2D.Float(x, y, x, y + SIDE_LENGTH));
				break;
			case RIGHT:
				g2.draw(new Line2D.Float(x + SIDE_LENGTH, y, x + SIDE_LENGTH, y + SIDE_LENGTH));
				break;
			case DOWN:
				g2.draw(new Line2D.Float(x, y + SIDE_LENGTH, x + SIDE_LENGTH, y + SIDE_LENGTH));
				break;
			default:
				break;
			}
		}
		
	}
}
