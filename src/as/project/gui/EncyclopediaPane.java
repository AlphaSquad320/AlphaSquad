/* File: EncyclopediaPane.java
 * 
 * TODO: Clean up this code
 * TODO: Fix tab resizing issue
 * TODO: add appropriate imports
 * TODO: make each pane its own class
 */
package as.project.gui;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;


/**
 * This class is the content pane for the encyclopedia view.
 * @author Tommy
 */
public class EncyclopediaPane extends JPanel 
{
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Font headerFont = new Font("Tahoma", 1, 16);
    private Font fieldFont = new Font("Tahoma", 0, 14);

	//items tab 
	private JPanel itemTab;
    private JTextField searchBar;
    private JList<String> itemList;
    private JLabel itemTitle			= new JLabel("ITEM TITLE");
    private JLabel itemDescription		= new 
								JLabel("Description\nDescription\nDescription");
    private JLabel typeHeader			= new JLabel("Type");
    private JLabel typeText				= new JLabel("[[type]]");
    private JLabel bonusHeader			= new JLabel("Bonus");
    private JLabel bonusText			= new JLabel("[[bonus]]");
    private JLabel consumableHeader		= new JLabel("Consumable?");
    private JLabel consumableText		= new JLabel("[[consumable?]]");
    private JLabel addEffHeader			= new JLabel("Additional Effects");
    private JLabel addEffText			= new JLabel("[[additional effects]]");
	
	//abilities tab
	private JPanel abilityTab;
    private JLabel abilityTitle			= new JLabel("ABILITY TITLE");
	private JLabel abilityDescription	= new
								JLabel("Description\nDescription\nDescription");
    private JLabel typeHeader1			= new JLabel("Type");
    private JLabel typeText1			= new JLabel("[[type1]]");
    private JLabel bonusHeader1			= new JLabel("Bonus");
    private JLabel bonusText1			= new JLabel("[[bonus1]]");
    
	//TODO: clean this up (UGH)
    private JLabel addEffHeader1		= new JLabel("Additiona Effects");
    private JLabel addEffText1			= new JLabel("[[additional effects]]");
    private JLabel costHeader			= new JLabel("Cost");
    private JLabel reqLevelHeader		= new JLabel("Req Level");
    private JLabel costText				= new JLabel("[[cost]]");
    private JLabel reqLevelText			= new JLabel("[[req level]]");
    private JLabel damageHeader			= new JLabel("Damage");
    private JLabel damageText			= new JLabel("[[damage]]");
    private JLabel durationHeader		= new JLabel("Duration");
    private JLabel durationText			= new JLabel("[[duration]]");
    private JInternalFrame jInternalFrame1;
    private JList<String> jList3;
    private JScrollPane jScrollPane3;
    private JTextField jTextField3;
    private JPanel npcTab;
    private JLabel radiusHeader			= new JLabel("Radius");
    private JLabel radiusText			= new JLabel("[[radius]]");
    private JLabel rangeHeader			= new JLabel("Range");
    private JLabel rangeHeader2			= new JLabel("");
    private JLabel rangeText			= new JLabel("[[range]]");
    private JScrollPane scrollPane;
    private JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
    
	/**
     * Creates new form EncyclopediaPane
     */
    public EncyclopediaPane() {
        initComponents();
    }

    private void initComponents() {

        jInternalFrame1 = new JInternalFrame();
        rangeHeader2 = new JLabel();
        tabbedPane = new JTabbedPane();
        itemTab = new JPanel();
        scrollPane = new JScrollPane();
        itemList = new JList<>();
        searchBar = new JTextField();
        abilityTab = new JPanel();
        jScrollPane3 = new JScrollPane();
        jList3 = new JList<>();
        jTextField3 = new JTextField();
        npcTab = new JPanel();

        jInternalFrame1.setVisible(true);

        GroupLayout jInternalFrame1Layout = new GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        rangeHeader2.setFont(headerFont); // NOI18N
        rangeHeader2.setText("Radius");

        tabbedPane.setMinimumSize(new java.awt.Dimension(550, 450));

        itemList.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollPane.setViewportView(itemList);

        searchBar.setText("Search");
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });

        itemTitle.setFont(new Font("Tahoma", 1, 18)); // NOI18N

        typeHeader.setFont(headerFont); // NOI18N

        typeText.setFont(fieldFont); // NOI18N

        bonusHeader.setFont(headerFont); // NOI18N

        consumableHeader.setFont(headerFont); // NOI18N

        bonusText.setFont(fieldFont); // NOI18N

        consumableText.setFont(fieldFont); // NOI18N

        addEffHeader.setFont(headerFont); // NOI18N

        addEffText.setFont(fieldFont); // NOI18N
        addEffText.setVerticalAlignment(SwingConstants.TOP);

        itemDescription.setFont(fieldFont); // NOI18N
        itemDescription.setVerticalAlignment(SwingConstants.TOP);

        GroupLayout itemTabLayout = new GroupLayout(itemTab);
        itemTab.setLayout(itemTabLayout);
        itemTabLayout.setHorizontalGroup(
            itemTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(itemTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPane)
                    .addComponent(searchBar, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(itemDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(itemTabLayout.createSequentialGroup()
                        .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(itemTitle)
                            .addGroup(itemTabLayout.createSequentialGroup()
                                .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(typeText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(typeHeader, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(bonusText)
                                    .addComponent(bonusHeader)))
                            .addComponent(consumableHeader)
                            .addComponent(consumableText)
                            .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(addEffText, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addEffHeader, GroupLayout.Alignment.LEADING)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        itemTabLayout.setVerticalGroup(
            itemTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(itemTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(searchBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemTitle))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addGroup(itemTabLayout.createSequentialGroup()
                        .addComponent(itemDescription, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(typeHeader)
                            .addComponent(bonusHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(itemTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
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

        tabbedPane.addTab("Items", itemTab);

        jList3.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        jTextField3.setText("Search");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        abilityTitle.setFont(new Font("Tahoma", 1, 18)); // NOI18N
        abilityTitle.setText("[[ ABILITY TITLE ]]");

        typeHeader1.setFont(headerFont); // NOI18N

        typeText1.setFont(fieldFont); // NOI18N

        bonusHeader1.setFont(headerFont); // NOI18N

        bonusText1.setFont(fieldFont); // NOI18N

        addEffHeader1.setFont(headerFont); // NOI18N

        addEffText1.setFont(fieldFont); // NOI18N
        addEffText1.setVerticalAlignment(SwingConstants.TOP);

        abilityDescription.setFont(fieldFont); // NOI18N
        abilityDescription.setVerticalAlignment(SwingConstants.TOP);

        rangeHeader.setFont(headerFont); // NOI18N

        radiusHeader.setFont(headerFont); // NOI18N

        damageHeader.setFont(headerFont); // NOI18N

        rangeText.setFont(fieldFont); // NOI18N

        radiusText.setFont(fieldFont); // NOI18N

        durationHeader.setFont(headerFont); // NOI18N

        damageText.setFont(fieldFont); // NOI18N

        durationText.setFont(fieldFont); // NOI18N

        costHeader.setFont(headerFont); // NOI18N

        reqLevelHeader.setFont(headerFont); // NOI18N

        costText.setFont(fieldFont); // NOI18N

        reqLevelText.setFont(fieldFont); // NOI18N

        GroupLayout abilityTabLayout = new GroupLayout(abilityTab);
        abilityTab.setLayout(abilityTabLayout);
        abilityTabLayout.setHorizontalGroup(
            abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(abilityTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jTextField3, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(abilityDescription, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                    .addGroup(abilityTabLayout.createSequentialGroup()
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(abilityTitle)
                            .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(addEffText1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addEffHeader1, GroupLayout.Alignment.LEADING))
                            .addGroup(abilityTabLayout.createSequentialGroup()
                                .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(typeText1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(typeHeader1, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(rangeText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(damageText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rangeHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(damageHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bonusText1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(radiusText, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(durationText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(durationHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(radiusHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bonusHeader1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(costHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(reqLevelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(reqLevelHeader, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(costText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        abilityTabLayout.setVerticalGroup(
            abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(abilityTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(abilityTitle))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(abilityTabLayout.createSequentialGroup()
                        .addComponent(abilityDescription, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(typeHeader1)
                            .addComponent(bonusHeader1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(costHeader))
                        .addGap(6, 6, 6)
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(typeText1)
                            .addComponent(bonusText1)
                            .addComponent(costText))
                        .addGap(18, 18, 18)
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(rangeHeader)
                            .addComponent(radiusHeader)
                            .addComponent(reqLevelHeader))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(rangeText)
                            .addComponent(radiusText)
                            .addComponent(reqLevelText))
                        .addGap(18, 18, 18)
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(durationHeader)
                            .addComponent(damageHeader))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(damageText)
                            .addComponent(durationText))
                        .addGap(18, 18, 18)
                        .addComponent(addEffHeader1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addEffText1)
                        .addGap(86, 86, 86)))
                .addContainerGap())
        );

        tabbedPane.addTab("Abilities", abilityTab);

        GroupLayout npcTabLayout = new GroupLayout(npcTab);
        npcTab.setLayout(npcTabLayout);
        npcTabLayout.setHorizontalGroup(
            npcTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );
        npcTabLayout.setVerticalGroup(
            npcTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        tabbedPane.addTab("NPCs", npcTab);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBarActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed




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
