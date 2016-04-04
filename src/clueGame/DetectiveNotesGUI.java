package clueGame;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetectiveNotesGUI extends JDialog {
	ArrayList<Player> playerList;
	Set<String> roomSet;
	ArrayList<String> weaponList;
	public DetectiveNotesGUI(ArrayList<Player> players, Set<String> rooms, ArrayList<String> weapons) {
	    playerList = players;
	    roomSet = rooms;
	    weaponList = weapons;
	    
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
	    JPanel bestPersonGuess = new JPanel();
	    bestPersonGuess.setLayout(new GridLayout(1, 0));
	    JLabel guessPersonLabel = new JLabel("Person Guess");
	    bestPersonGuess.add(guessPersonLabel);
	    
	    JComboBox<String> personDropdown = new JComboBox<String>();
	    personDropdown.addItem("Unsure");
	    for (Player p : playerList){
	        personDropdown.addItem(p.getName());
	    }
	    bestPersonGuess.add(personDropdown);
		return bestPersonGuess;
	}
	
	private JPanel createRoomPanel() {
	    JPanel roomPanel = new JPanel();
	    roomPanel.setLayout(new GridLayout(0, 2));
	    JLabel roomLabel = new JLabel("People");
	    for (Player p : playerList) {
	        
	    }
		return roomPanel;
	}
	
	private JPanel createGuessRoomPanel() {
	    JPanel bestRoomGuess = new JPanel();
	    bestRoomGuess.setLayout(new GridLayout(1, 0));
	    JLabel guessRoomLabel = new JLabel("Room Guess");
	    bestRoomGuess.add(guessRoomLabel);
	    
	    JComboBox<String> roomDropdown = new JComboBox<String>();
	    roomDropdown.addItem("Unsure");
	    for (String room : roomSet){
	        roomDropdown.addItem(room);
	    }
	    bestRoomGuess.add(roomDropdown);
		return bestRoomGuess;
	}
	
	private JPanel createWeaponPanel() {
		return null;
	}
	
	private JPanel createGuessWeaponPanel() {
	    JPanel bestWeaponGuess = new JPanel();
	    bestWeaponGuess.setLayout(new GridLayout(1, 0));
	    JLabel guessWeaponLabel = new JLabel("Weapon Guess");
	    bestWeaponGuess.add(guessWeaponLabel);
	    
	    JComboBox<String> weaponDropdown = new JComboBox<String>();
	    weaponDropdown.addItem("Unsure");
	    for (String weapon : weaponList){
	        weaponDropdown.addItem(weapon);
	    }
	    bestWeaponGuess.add(weaponDropdown);
		return bestWeaponGuess;
	}
}
