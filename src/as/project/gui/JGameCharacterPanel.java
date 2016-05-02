package as.project.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import as.project.objects.GameCharacter;

public class JGameCharacterPanel extends JPanel {
	
	GameCharacter gc;
	JLabel info;
	JLabel items;
	JLabel abilities;

	public GameCharacter getCharacter() {
		return gc;
	}

	public JGameCharacterPanel(GameCharacter gc) {
		super();
		this.gc = gc;

		//TODO: make a real display for characters
		StringBuilder sbInfo = new StringBuilder();
		sbInfo.append("<html>");
		sbInfo.append("<p>").append(gc.getCharacterName()).append(", Level: ").append(gc.getLevel()).append("<br />");
		sbInfo.append("HP: ").append(gc.getCurHp()).append("/").append(gc.getHp()).append("<br />");
		sbInfo.append("MP: ").append(gc.getCurMp()).append("/").append(gc.getMp()).append("<br />");
		sbInfo.append("XP: ").append(gc.getCurXp()).append("<br />");
		sbInfo.append(gc.getMoney()).append(" rupees");
		sbInfo.append("</p>").append("<br />");
		
		sbInfo.append("<p>");
		sbInfo.append("STR: ").append(gc.getStr()).append("<br />");
		sbInfo.append("DEX: ").append(gc.getDex()).append("<br />");
		sbInfo.append("CON: ").append(gc.getCon()).append("<br />");
		sbInfo.append("INT: ").append(gc.getIntel()).append("<br />");
		sbInfo.append("WIS: ").append(gc.getWis()).append("<br />");
		sbInfo.append("CHR: ").append(gc.getChr()).append("<br />");
		sbInfo.append("</p>").append("<br />");
		
		sbInfo.append("<p>");
		sbInfo.append("Alignment: ").append(gc.getAlignment()).append("<br />");
		sbInfo.append("Class: ").append(gc.getCharacterClass()).append("<br />");
		sbInfo.append("Race: ").append(gc.getRace()).append("<br />");
		sbInfo.append("</p>");
		
		sbInfo.append("</html>");
		
		info = new JLabel(sbInfo.toString());
		info.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		layOutComponents();
	}
	
	private void layOutComponents()
	{
		//gridbaglayout tutorial: https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.weightx = 1;
		c.insets = new Insets(0, 0, 20, 0); //bottom padding
		this.add(info, c);
		/*c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		this.add(abilities, c);
		c.gridy = 2;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(0, 0, 0, 0);
		this.add(items, c);
		*/
		
	}
	

}
