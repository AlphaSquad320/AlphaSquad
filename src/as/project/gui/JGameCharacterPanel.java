package as.project.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import as.project.objects.Ability;
import as.project.objects.GameCharacter;
import as.project.objects.Item;
import as.project.tables.AbilityTable;
import as.project.tables.ItemTable;

public class JGameCharacterPanel extends JScrollPane {
	
	private GameCharacter gc;
	private ArrayList<Item> itemList;
	private ArrayList<Ability> abilityList;
	
	private JPanel panel = new JPanel();
	
	private static String ITEM_HEADER_BASE = "Items(%1$d):";
	private static String ABILITY_HEADER_BASE = "Abilities(%1$d):";
	
    private Font headerFont = new Font("Tahoma", 1, 16);
    private Font fieldFont = new Font("Tahoma", 0, 14);
    
    private JLabel nameHeader = createJLabel("Name", headerFont);
    private JLabel moneyHeader = createJLabel("Money", headerFont);
    private JLabel levelHeader = createJLabel("Level", headerFont);
    private JLabel hpHeader = createJLabel("HP", headerFont);
    private JLabel mpHeader = createJLabel("MP", headerFont);
    private JLabel xpHeader = createJLabel("XP", headerFont);
    private JLabel strHeader = createJLabel("STR", headerFont);
    private JLabel dexHeader = createJLabel("DEX", headerFont);
    private JLabel conHeader = createJLabel("CON", headerFont);
    private JLabel intHeader = createJLabel("INT", headerFont);
    private JLabel wisHeader = createJLabel("WIS", headerFont);
    private JLabel chrHeader = createJLabel("CHR", headerFont);
    private JLabel alignHeader = createJLabel("Alignment", headerFont);
    private JLabel raceHeader = createJLabel("Race", headerFont);
    private JLabel classHeader = createJLabel("Class", headerFont);
    private JLabel itemHeader = createJLabel(String.format(ITEM_HEADER_BASE, 0), headerFont);
    private JLabel abilityHeader = createJLabel(String.format(ABILITY_HEADER_BASE, 0), headerFont);
    
    private JLabel nameText = createJLabel("[[Name]]", fieldFont);
    private JLabel moneyText = createJLabel("[[Money]]", fieldFont);
    private JLabel levelText = createJLabel("[Level]]", fieldFont);
    private JLabel hpText = createJLabel("[[HP]]", fieldFont);
    private JLabel mpText = createJLabel("[[MP]]", fieldFont);
    private JLabel xpText = createJLabel("[[XP]]", fieldFont);
    private JLabel strText = createJLabel("[[STR]]", fieldFont);
    private JLabel dexText = createJLabel("[[DEX]]", fieldFont);
    private JLabel conText = createJLabel("[[CON]]", fieldFont);
    private JLabel intText = createJLabel("[[INT]]", fieldFont);
    private JLabel wisText = createJLabel("[[WIS]]", fieldFont);
    private JLabel chrText = createJLabel("[[CHR]]", fieldFont);
    private JLabel alignText = createJLabel("[[Alignment]]", fieldFont);
    private JLabel raceText = createJLabel("[[Race]]", fieldFont);
    private JLabel classText = createJLabel("[[Class]]", fieldFont);
    private JLabel itemText = createJLabel("[[Items]]", fieldFont);
    private JLabel abilityText = createJLabel("[[Abilities]]", fieldFont);
    

	public JGameCharacterPanel() {
		super();
		layOutComponents();
	}
	public JGameCharacterPanel(GameCharacter gc) {
		super();
		layOutComponents();
		setCharacter(gc);
	}

	/**
	 * function to create a JLabel with provided text and font
	 * @param text the desired text
	 * @param font the desired font
	 * @return a JLabel object with the text and font provided
	 */
	private JLabel createJLabel(String text, Font font)
	{
		JLabel label = new JLabel(text);
		label.setFont(font);
		return label;
	}
	
	private void layOutComponents()
	{
		this.setViewportView(panel);
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
							.addComponent(nameHeader)
							.addComponent(nameText)
							.addComponent(hpHeader)
							.addComponent(hpText)
							.addComponent(strHeader)
							.addComponent(strText)
							.addComponent(intHeader)
							.addComponent(intText)
							.addComponent(alignHeader)
							.addComponent(alignText))
						.addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
							.addComponent(moneyHeader)
							.addComponent(moneyText)
							.addComponent(mpHeader)
							.addComponent(mpText)
							.addComponent(dexHeader)
							.addComponent(dexText)
							.addComponent(wisHeader)
							.addComponent(wisText)
							.addComponent(raceHeader)
							.addComponent(raceText))
						.addGap(18)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
							.addComponent(levelHeader)
							.addComponent(levelText)
							.addComponent(xpHeader)
							.addComponent(xpText)
							.addComponent(conHeader)
							.addComponent(conText)
							.addComponent(chrHeader)
							.addComponent(chrText)
							.addComponent(classHeader)
							.addComponent(classText)))
					.addComponent(itemHeader)
					.addComponent(itemText)
					.addComponent(abilityHeader)
					.addComponent(abilityText));
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameHeader)
						.addComponent(moneyHeader)
						.addComponent(levelHeader))
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameText)
						.addComponent(moneyText)
						.addComponent(levelText))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(hpHeader)
						.addComponent(mpHeader)
						.addComponent(xpHeader))
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(hpText)
						.addComponent(mpText)
						.addComponent(xpText))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(strHeader)
						.addComponent(dexHeader)
						.addComponent(conHeader))
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(strText)
						.addComponent(dexText)
						.addComponent(conText))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(intHeader)
						.addComponent(wisHeader)
						.addComponent(chrHeader))
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(intText)
						.addComponent(wisText)
						.addComponent(chrText))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(alignHeader)
						.addComponent(raceHeader)
						.addComponent(classHeader))
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(alignText)
						.addComponent(raceText)
						.addComponent(classText))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(itemHeader)
					.addComponent(itemText)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(abilityHeader)
					.addComponent(abilityText));
		
	}
	
	public GameCharacter getCharacter() {
		return gc;
	}
	
	public void setCharacter(GameCharacter gc){
		this.gc = gc;
		this.itemList = ItemTable.getItemsByCharacter(MainGUI.getConnection(), gc.getCharacterId());
		this.abilityList = AbilityTable.getAbilitiesByCharacter(MainGUI.getConnection(), gc.getCharacterId());
		displayCharacter();
	}
	
	private void displayCharacter(){
		if(gc != null){
			nameText.setText(gc.getCharacterName());
			moneyText.setText(String.valueOf(gc.getMoney()));
			xpText.setText(String.valueOf(gc.getCurXp()));
			hpText.setText(gc.getCurHp() + "/" + gc.getHp());
			mpText.setText(gc.getCurMp() + "/" + gc.getMp());
			levelText.setText(String.valueOf(gc.getLevel()));
			strText.setText(String.valueOf(gc.getStr()));
			dexText.setText(String.valueOf(gc.getDex()));
			conText.setText(String.valueOf(gc.getCon()));
			intText.setText(String.valueOf(gc.getIntel()));
			wisText.setText(String.valueOf(gc.getWis()));
			chrText.setText(String.valueOf(gc.getChr()));
			alignText.setText(gc.getAlignment());
			raceText.setText(gc.getRace());
			classText.setText(gc.getCharacterClass());
			itemHeader.setText(String.format(ITEM_HEADER_BASE, itemList.size()));
			StringBuilder items = new StringBuilder();
			items.append("<html>");
			for(int i = 0; i < itemList.size(); i++){
				items.append(itemList.get(i));
				if(i != itemList.size() - 1){
					items.append("<br />");
				}
			}
			items.append("</html>");
			itemText.setText(items.toString());
				
			abilityHeader.setText(String.format(ABILITY_HEADER_BASE, abilityList.size()));
			StringBuilder abilities = new StringBuilder();
			abilities.append("<html>");
			for(int i = 0; i < abilityList.size(); i++){
				abilities.append(abilityList.get(i));
				if(i != abilityList.size() - 1){
					abilities.append("<br />");
				}
			}
			abilities.append("</html>");
			abilityText.setText(abilities.toString());
		}
	}
	

}
