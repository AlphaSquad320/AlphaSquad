package as.project.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import as.project.objects.GameCharacter;

public class JGameCharacterPanel extends JPanel {
	
	GameCharacter gc;

	public GameCharacter getCharacter() {
		return gc;
	}

	public JGameCharacterPanel(GameCharacter gc) {
		super();
		this.gc = gc;

		//TODO: make a real display for characters
		this.add(new JLabel(gc.getCharacterName()));
	}
	

}
