/* File: EncyclopediaPane.java
 * vim: ts=4:sw=4:tw=120
 *
 * TODO: Clean up this code
 * TODO: make each pane its own class
 */
package as.project.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;	//for constants
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import as.project.objects.Ability;
import as.project.objects.GameCharacter;
import as.project.objects.Item;
import as.project.objects.NPC;
import as.project.tables.AbilityTable;
import as.project.tables.CharacterTable;
import as.project.tables.ItemTable;

/**
 * This class is the content pane for the encyclopedia view.
 * @author Tommy Bohde
 */
public class EncyclopediaPane extends JPanel implements ActionListener, ListSelectionListener
{
	//useful things
	private int DEFAULT_SIZE = GroupLayout.DEFAULT_SIZE;
	private int PREFERRED_SIZE = GroupLayout.PREFERRED_SIZE;

	//class-wide elements
    private JTabbedPane tabbedPane		= new JTabbedPane();
	private Font titleFont				= new Font("Tahoma", 1, 18);
    private Font headerFont				= new Font("Tahoma", 1, 16);
    private Font fieldFont				= new Font("Tahoma", 0, 14);

	//items tab 
	private JPanel itemTab				= new JPanel();
    private JTextField iSearchBar		= new JTextField();
    private JButton iSearchButton       = new JButton("Search");
	private JScrollPane iScrollPane		= new JScrollPane();
    private DefaultListModel<Item> iListModel = new DefaultListModel<>();
    private JList<Item> itemList		= new JList<>(iListModel);
    private JLabel itemTitle			= createJLabel("ITEM TITLE",			titleFont);
    private JLabel itemDescription		= createJLabel("Description",			fieldFont);
    private JLabel typeHeader			= createJLabel("Type",					headerFont);
    private JLabel typeText				= createJLabel("[[type]]",				fieldFont);
    private JLabel bonusHeader			= createJLabel("Bonus",					headerFont);
    private JLabel bonusText			= createJLabel("[[bonus]]",				fieldFont);
    private JLabel consumableHeader		= createJLabel("Consumable?",			headerFont);
    private JLabel consumableText		= createJLabel("[[consumable?]]",		fieldFont);
    private JLabel addEffHeader			= createJLabel("Additional Effects",	headerFont);
    private JLabel addEffText			= createJLabel("[[additional effects]]",fieldFont);
	
	//abilities tab
	private JPanel abilityTab			= new JPanel();
    private JTextField aSearchBar		= new JTextField();
    private JButton aSearchButton       = new JButton("Search");
    private JScrollPane aScrollPane		= new JScrollPane();
    private DefaultListModel<Ability> aListModel = new DefaultListModel<>();
    private JList<Ability> abilityList		= new JList<>(aListModel);
    private JLabel abilityTitle			= createJLabel("ABILITY TITLE",			titleFont);
	private JLabel abilityDescription	= createJLabel("Description",			fieldFont);
    private JLabel typeHeader1			= createJLabel("Type",					headerFont);
    private JLabel typeText1			= createJLabel("[[type1]]",				fieldFont);
    private JLabel costHeader			= createJLabel("Cost",					headerFont);
    private JLabel costText				= createJLabel("[[cost]]",				fieldFont);
    private JLabel rangeHeader			= createJLabel("Range",					headerFont);
    private JLabel rangeText			= createJLabel("[[range]]",				fieldFont);
    private JLabel radiusHeader			= createJLabel("Radius",				headerFont);
    private JLabel radiusText			= createJLabel("[[radius]]",			fieldFont);
    private JLabel reqLevelHeader		= createJLabel("Req Level",				headerFont);
    private JLabel reqLevelText			= createJLabel("[[req level]]",			fieldFont);
    private JLabel damageHeader			= createJLabel("Damage",				headerFont);
    private JLabel damageText			= createJLabel("[[damage]]",			fieldFont);
    private JLabel durationHeader		= createJLabel("Duration",				headerFont);
    private JLabel durationText			= createJLabel("[[duration]]",			fieldFont);
    private JLabel addEffHeader1		= createJLabel("Additional Effects",	headerFont);
    private JLabel addEffText1			= createJLabel("[[additional effects]]",fieldFont);
    
    //NPCs tab
	private JPanel npcTab				= new JPanel();
	private JGameCharacterPanel npcData = new JGameCharacterPanel();
    private JTextField nSearchBar		= new JTextField();
    private JButton nSearchButton       = new JButton("Search");
    private JScrollPane nScrollPane		= new JScrollPane();
    private DefaultListModel<GameCharacter> nListModel = new DefaultListModel<>();
    private JList<GameCharacter> npcList= new JList<>(nListModel);
	

	/**
     * Creates new form EncyclopediaPane
     */
    public EncyclopediaPane() 
	{
        layOutComponents();
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
		//set up the main layout
        tabbedPane.setMinimumSize(new java.awt.Dimension(550, 450));
        tabbedPane.addTab("Items", itemTab);
        tabbedPane.addTab("Abilities", abilityTab);
        tabbedPane.addTab("NPCs", npcTab);
        
		this.setLayout(new GridLayout(1, 1));
		this.add(tabbedPane);
			
		layOutItemTab();
		layOutAbilityTab();
		layOutNPCTab();

    }

	private void layOutItemTab()
	{
		// #################
		// ### ITEM PANE ###
		// #################
        iSearchBar.addActionListener(this);
        iSearchButton.addActionListener(this);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(this);

        itemDescription.setVerticalAlignment(SwingConstants.TOP);
        addEffText.setVerticalAlignment(SwingConstants.TOP);

        iScrollPane.setViewportView(itemList);
        Dimension d = iScrollPane.getPreferredSize();
        d.width = 141;
        iScrollPane.setPreferredSize(d);

        GroupLayout itemTabLayout = new GroupLayout(itemTab);
        itemTab.setLayout(itemTabLayout);
        itemTabLayout.setHorizontalGroup(
            itemTabLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(itemTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemTabLayout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(iScrollPane)
                    .addComponent(iSearchButton, DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(iSearchBar, DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(itemTabLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(itemDescription, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(itemTabLayout.createSequentialGroup()
                        .addGroup(itemTabLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(itemTitle)
                            .addGroup(itemTabLayout.createSequentialGroup()
                                .addGroup(itemTabLayout.createParallelGroup(Alignment.LEADING, false)
                                    .addComponent(typeText, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(typeHeader, PREFERRED_SIZE, 80, PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(itemTabLayout.createParallelGroup(Alignment.LEADING)
                                    .addComponent(bonusText)
                                    .addComponent(bonusHeader)))
                            .addComponent(consumableHeader)
                            .addComponent(consumableText)
                            .addGroup(itemTabLayout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(addEffText, Alignment.LEADING, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addEffHeader, Alignment.LEADING)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        itemTabLayout.setVerticalGroup(
            itemTabLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(itemTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemTabLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(iSearchBar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                    .addComponent(itemTitle))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iSearchButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemTabLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(iScrollPane)
                    .addGroup(itemTabLayout.createSequentialGroup()
                        .addComponent(itemDescription, PREFERRED_SIZE, 60, PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemTabLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(typeHeader)
                            .addComponent(bonusHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(itemTabLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(typeText)
                            .addComponent(bonusText))
                        .addGap(18, 18, 18)
                        .addComponent(consumableHeader)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consumableText)
                        .addGap(18, 18, 18)
                        .addComponent(addEffHeader)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addEffText)))
                .addContainerGap())
        );
	}

	private void layOutAbilityTab()
	{
		// ####################
		// ### ABILITY PANE ###
		// ####################
        aSearchBar.addActionListener(this);
        aSearchButton.addActionListener(this);
        abilityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        abilityList.addListSelectionListener(this);

        abilityDescription.setVerticalAlignment(SwingConstants.TOP);
        addEffText1.setVerticalAlignment(SwingConstants.TOP);
        aScrollPane.setViewportView(abilityList);
        Dimension d = aScrollPane.getPreferredSize();
        d.width = 141;
        aScrollPane.setPreferredSize(d);


        GroupLayout abilityTabLayout = new GroupLayout(abilityTab);
        abilityTab.setLayout(abilityTabLayout);
        abilityTabLayout.setHorizontalGroup(
            abilityTabLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(abilityTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(aScrollPane)
                    .addComponent(aSearchButton, DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(aSearchBar, DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(abilityDescription, DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addGroup(abilityTabLayout.createSequentialGroup()
                        .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(abilityTitle)
                            .addGroup(abilityTabLayout.createParallelGroup(Alignment.TRAILING, false)
                                .addComponent(addEffText1, Alignment.LEADING, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addEffHeader1, Alignment.LEADING))
                            .addGroup(abilityTabLayout.createSequentialGroup()
                                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING, false)
                                    .addComponent(typeText1, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(typeHeader1, DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(rangeText, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(damageText, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rangeHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(damageHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(costHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(radiusText, DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(durationText, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(durationHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(radiusHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(costText, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING, false)
                                    .addComponent(reqLevelText, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(reqLevelHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        abilityTabLayout.setVerticalGroup(
            abilityTabLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(abilityTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(aSearchBar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                    .addComponent(abilityTitle))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aSearchButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(aScrollPane)
                    .addGroup(abilityTabLayout.createSequentialGroup()
                        .addComponent(abilityDescription, PREFERRED_SIZE, 60, PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abilityTabLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(typeHeader1)
                            .addComponent(costHeader))
                        .addGap(6, 6, 6)
                        .addGroup(abilityTabLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(typeText1)
                            .addComponent(costText))
                        .addGap(18, 18, 18)
                        .addGroup(abilityTabLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(rangeHeader)
                            .addComponent(radiusHeader)
                            .addComponent(reqLevelHeader))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abilityTabLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(rangeText)
                            .addComponent(radiusText)
                            .addComponent(reqLevelText))
                        .addGap(18, 18, 18)
                        .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(durationHeader)
                            .addComponent(damageHeader))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abilityTabLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(damageText)
                            .addComponent(durationText))
                        .addGap(18, 18, 18)
                        .addComponent(addEffHeader1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addEffText1)
                        .addGap(86, 86, 86)))
                .addContainerGap())
        );

	}

	private void layOutNPCTab()
	{
		// #################
		// ### NPC  PANE ###
		// #################
        nSearchBar.addActionListener(this);
        nSearchButton.addActionListener(this);
        npcList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        npcList.addListSelectionListener(this);

        nScrollPane.setViewportView(npcList);
        Dimension d = nScrollPane.getPreferredSize();
        d.width = 141;
        nScrollPane.setPreferredSize(d);
        
        
        GroupLayout npcTabLayout = new GroupLayout(npcTab);
        npcTab.setLayout(npcTabLayout);
        npcTabLayout.setHorizontalGroup(
        	npcTabLayout.createSequentialGroup()
            	.addContainerGap()
        		.addGroup(npcTabLayout.createParallelGroup(Alignment.LEADING, false)
        			.addComponent(nSearchBar, PREFERRED_SIZE, 141, Short.MAX_VALUE)
        			.addComponent(nSearchButton, DEFAULT_SIZE, 141, Short.MAX_VALUE)
        			.addComponent(nScrollPane))
                .addGap(18, 18, 18)
        		.addComponent(npcData));
        
        npcTabLayout.setVerticalGroup(
        	npcTabLayout.createSequentialGroup()
        		.addContainerGap()
        		.addGroup(npcTabLayout.createParallelGroup(Alignment.LEADING)
	            	.addGroup(npcTabLayout.createSequentialGroup()
	            		.addComponent(nSearchBar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
	            		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	            		.addComponent(nSearchButton)
	            		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	            		.addComponent(nScrollPane))
	                .addGap(18, 18, 18)
	            	.addComponent(npcData))
        		.addContainerGap());
	}
	
	private void displayItem(Item i){
		if(i != null)
		{
			itemDescription.setText(i.getDescription());
			typeText.setText(i.getType());
			bonusText.setText(String.valueOf(i.getBonus()));
			consumableText.setText(String.valueOf(i.isConsumable()));
			addEffText.setText(i.getEffect());
			itemTitle.setText(i.getName());
		}
	}
	
	private void displayAbility(Ability a){
		if(a != null)
		{
			abilityTitle.setText(a.getName());
			abilityDescription.setText(a.getDescription());
			typeText1.setText(a.getType());
			addEffText1.setText(a.getAdditionalEffects());
			costText.setText(String.valueOf(a.getCost()));
			damageText.setText(String.valueOf(a.getBaseDamage()));
			radiusText.setText(String.valueOf(a.getRadius()));
			rangeText.setText(String.valueOf(a.getRange()));
			reqLevelText.setText(String.valueOf(a.getReqLevel()));
			durationText.setText(String.valueOf(a.getDuration()));
		}
	}
	
	private void displayNPC(GameCharacter gc){
		if(gc != null){
			npcData.setCharacter(gc);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		//TODO: implement search features
		
		if(e.getSource() == iSearchButton){
			String query = iSearchBar.getText();
			iListModel.clear();
			ArrayList<Item> qResults = ItemTable.getItemsByString(MainGUI.getConnection(), query);
			for(Item i : qResults){
				iListModel.addElement(i);
			}
		}
		else if(e.getSource() == aSearchButton){
			String query = aSearchBar.getText();
			aListModel.clear();
			ArrayList<Ability> qResults = AbilityTable.getItemsByString(MainGUI.getConnection(), query);
			for(Ability i : qResults){
				aListModel.addElement(i);
			}
		}
		else if(e.getSource() == nSearchButton){
			String query = nSearchBar.getText();
			nListModel.clear();
			ArrayList<GameCharacter> qResults = CharacterTable.geNPCCharactersByString(MainGUI.getConnection(), query);
			for(GameCharacter i : qResults){
				nListModel.addElement(i);
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()){
			if(e.getSource() == itemList){
				displayItem(itemList.getSelectedValue());
			}
			else if(e.getSource() == abilityList){
				displayAbility(abilityList.getSelectedValue());
			}
			else if(e.getSource() == npcList){
				displayNPC(npcList.getSelectedValue());
			}
		}
	}
}
