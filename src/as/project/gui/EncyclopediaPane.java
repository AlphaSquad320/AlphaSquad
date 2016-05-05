/* File: EncyclopediaPane.java
 * vim: ts=4:sw=4:tw=120
 *
 * TODO: Clean up this code
 * TODO: make each pane its own class
 */
package as.project.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;	//for constants

/**
 * This class is the content pane for the encyclopedia view.
 * @author Tommy Bohde
 */
public class EncyclopediaPane extends JPanel implements ActionListener
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
	private JScrollPane iScrollPane		= new JScrollPane();
    private JList<String> itemList		= new JList<>();
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
    private JScrollPane aScrollPane		= new JScrollPane();
    private JList<String> abilityList		= new JList<>();
    private JLabel abilityTitle			= createJLabel("ABILITY TITLE",			titleFont);
	private JLabel abilityDescription	= createJLabel("Description",			fieldFont);
    private JLabel typeHeader1			= createJLabel("Type",					headerFont);
    private JLabel typeText1			= createJLabel("[[type1]]",				fieldFont);
    private JLabel bonusHeader1			= createJLabel("Bonus",					headerFont);
    private JLabel bonusText1			= createJLabel("[[bonus1]]",			fieldFont);
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
		iSearchBar.setText("Search");
        iSearchBar.addActionListener(this);

        itemDescription.setVerticalAlignment(SwingConstants.TOP);
        addEffText.setVerticalAlignment(SwingConstants.TOP);

		//TODO: populate the list with actual items
        itemList.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        iScrollPane.setViewportView(itemList);

        GroupLayout itemTabLayout = new GroupLayout(itemTab);
        itemTab.setLayout(itemTabLayout);
        itemTabLayout.setHorizontalGroup(
            itemTabLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(itemTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemTabLayout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(iScrollPane)
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
        aSearchBar.setText("Search");
        aSearchBar.addActionListener(this);

        abilityDescription.setVerticalAlignment(SwingConstants.TOP);
        addEffText1.setVerticalAlignment(SwingConstants.TOP);
		
		//TODO: populate the list with actual abilities
        abilityList.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        aScrollPane.setViewportView(abilityList);

        GroupLayout abilityTabLayout = new GroupLayout(abilityTab);
        abilityTab.setLayout(abilityTabLayout);
        abilityTabLayout.setHorizontalGroup(
            abilityTabLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(abilityTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(aScrollPane)
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
                                    .addComponent(bonusText1, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(radiusText, DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(durationText, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(durationHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(radiusHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bonusHeader1, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING, false)
                                    .addComponent(costHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(reqLevelText, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(reqLevelHeader, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(costText, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGroup(abilityTabLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(aScrollPane)
                    .addGroup(abilityTabLayout.createSequentialGroup()
                        .addComponent(abilityDescription, PREFERRED_SIZE, 60, PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abilityTabLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(typeHeader1)
                            .addComponent(bonusHeader1, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costHeader))
                        .addGap(6, 6, 6)
                        .addGroup(abilityTabLayout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(typeText1)
                            .addComponent(bonusText1)
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
        GroupLayout npcTabLayout = new GroupLayout(npcTab);
        npcTab.setLayout(npcTabLayout);
        npcTabLayout.setHorizontalGroup(
            npcTabLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );
        npcTabLayout.setVerticalGroup(
            npcTabLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );
	}

	public void actionPerformed(ActionEvent e)
	{
		//TODO: implement search features
		//(this might not be the best way to do that)
	}

    public static void main(String[] args)
    {
        JFrame testFrame = new JFrame("Encyclopedia");
        EncyclopediaPane ep = new EncyclopediaPane();
        testFrame.setMinimumSize(new Dimension(550, 400));
        testFrame.setContentPane(ep);
        testFrame.pack();
        testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
