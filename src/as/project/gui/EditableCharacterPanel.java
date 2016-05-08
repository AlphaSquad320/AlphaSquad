package as.project.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import as.project.objects.Ability;
import as.project.objects.GameCharacter;
import as.project.objects.Item;
import as.project.objects.NPC;
import as.project.objects.User;
import as.project.tables.AbilityTable;
import as.project.tables.CharacterAbilityTable;
import as.project.tables.CharacterItemTable;
import as.project.tables.ItemTable;
import as.project.tables.NPCTable;

public class EditableCharacterPanel extends JPanel implements ActionListener{
	
	private GameCharacter gc;
	
	private static String ITEM_HEADER_BASE = "Items(%1$d):";
	private static String ABILITY_HEADER_BASE = "Abilities(%1$d):";
	
    private Font headerFont = new Font("Tahoma", 1, 16);
    private Font fieldFont = new Font("Tahoma", 0, 14);
    private Border border = BorderFactory.createLineBorder(new Color(0,0,0));
    
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

    private DefaultListModel<Item> iListModel = new DefaultListModel<>();
    private JList<Item> itemList = new JList<>(iListModel);
	private JButton iButton = new JButton("Remove Items");
    private JScrollPane iScrollPane = new JScrollPane();
    
    private DefaultListModel<Ability> aListModel = new DefaultListModel<>();
    private JList<Ability> abilityList = new JList<>(aListModel);
	private JButton aButton = new JButton("Remove Ability");
    private JScrollPane aScrollPane = new JScrollPane();
    

	public EditableCharacterPanel() {
		super();
		layOutComponents();
	}
	public EditableCharacterPanel(GameCharacter gc) {
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
		itemHeader.setHorizontalAlignment(SwingConstants.CENTER);
		abilityHeader.setHorizontalAlignment(SwingConstants.CENTER);
		
		itemList.setBorder(border);
		iScrollPane.setViewportView(itemList);
		abilityList.setBorder(border);
		aScrollPane.setViewportView(abilityList);
		
		iButton.addActionListener(this);
		aButton.addActionListener(this);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
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
							.addComponent(classText))
						.addGap(36)
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
							.addComponent(itemHeader)
							.addComponent(iScrollPane)
							.addComponent(iButton)
							.addComponent(abilityHeader)
							.addComponent(aScrollPane)
							.addComponent(aButton))));
		
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.BASELINE)
				.addGroup(layout.createSequentialGroup()
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
						.addComponent(classText)))
				.addGroup(layout.createSequentialGroup()
					.addComponent(itemHeader)
					.addComponent(iScrollPane)
					.addComponent(iButton)
					.addComponent(abilityHeader)
					.addComponent(aScrollPane)
					.addComponent(aButton)));
		
	}
	
	public GameCharacter getCharacter() {
		return gc;
	}
	
	public void setCharacter(GameCharacter gc){
		this.gc = gc;
		reloadItems();
		reloadAbilities();
		displayCharacter();
	}
	
	private void reloadItems(){
		if(gc != null){
			ArrayList<Item> items = ItemTable.getItemsByCharacter(MainGUI.getConnection(), gc.getCharacterId());
			itemHeader.setText(String.format(ITEM_HEADER_BASE, items.size()));
			iListModel.clear();
			for(Item i : items){
				iListModel.addElement(i);
			}
		}
	}
	
	private void reloadAbilities(){
		if(gc != null){
			ArrayList<Ability> abilities = AbilityTable.getAbilitiesByCharacter(MainGUI.getConnection(), gc.getCharacterId());
			abilityHeader.setText(String.format(ABILITY_HEADER_BASE, abilities.size()));
			aListModel.clear();
			for(Ability a: abilities){
				aListModel.addElement(a);
			}
		}
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
			itemHeader.setText(String.format(ITEM_HEADER_BASE, iListModel.getSize()));
				
			abilityHeader.setText(String.format(ABILITY_HEADER_BASE, aListModel.getSize()));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == iButton){
			Item i = itemList.getSelectedValue();
			if(i != null){
				CharacterItemTable.takeItem(MainGUI.getConnection(), gc.getCharacterId(), i.getID(), true);
				reloadItems();
			}
		}
		else if(e.getSource() == aButton){
			Ability a = abilityList.getSelectedValue();
			if(a != null){
				CharacterAbilityTable.removeAbility(MainGUI.getConnection(), gc.getCharacterId(), a.getID(), true);
				reloadAbilities();
			}
		}
	}

}
