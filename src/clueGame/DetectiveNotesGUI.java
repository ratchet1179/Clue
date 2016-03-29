package clueGame;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class DetectiveNotesGUI extends JDialog {
	
	public DetectiveNotesGUI() {
		//TO DO: GET WEAPONS/NAMES/ROOMS
		
		setLayout(new GridLayout(3, 2));
		
		JPanel personPanel = createPersonPanel();
		JPanel guessPersonPanel = createGuessPersonPanel();
		
		JPanel roomPanel = createRoomPanel();
		JPanel guessRoomPanel = createGuessRoomPanel();
		
		JPanel weaponPanel = createWeaponPanel();
		JPanel guessWeaponPanel = createGuessWeaponPanel();
		
		this.add(personPanel);
		this.add(guessPersonPanel);
		this.add(roomPanel);
		this.add(guessRoomPanel);
		this.add(weaponPanel);
		this.add(guessWeaponPanel);
		
	}
	
	private JPanel createPersonPanel() {
		return null;
	}
	
	private JPanel createGuessPersonPanel() {
		return null;
	}
	
	private JPanel createRoomPanel() {
		return null;
	}
	
	private JPanel createGuessRoomPanel() {
		return null;
	}
	
	private JPanel createWeaponPanel() {
		return null;
	}
	
	private JPanel createGuessWeaponPanel() {
		return null;
	}
}
