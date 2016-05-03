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
    private JLabel abilityDescription;
    private JPanel abilityTab;
    private JLabel abilityTitle;
    private JLabel addEffHeader;
    private JLabel addEffHeader1;
    private JLabel addEffText;
    private JLabel addEffText1;
    private JLabel bonusHeader;
    private JLabel bonusHeader1;
    private JLabel bonusHeader2;
    private JLabel bonusHeader3;
    private JLabel bonusText;
    private JLabel bonusText1;
    private JLabel bonusText2;
    private JLabel bonusText3;
    private JLabel consumableHeader;
    private JLabel consumableText;
    private JLabel damageHeader;
    private JLabel damageText;
    private JLabel durationHeader;
    private JLabel durationText;
    private JLabel itemDescription;
    private JList<String> itemList;
    private JPanel itemTab;
    private JLabel itemTitle;
    private JInternalFrame jInternalFrame1;
    private JList<String> jList3;
    private JScrollPane jScrollPane3;
    private JTextField jTextField3;
    private JPanel npcTab;
    private JLabel radiusHeader;
    private JLabel radiusText;
    private JLabel rangeHeader;
    private JLabel rangeHeader2;
    private JLabel rangeText;
    private JScrollPane scrollPane;
    private JTextField searchBar;
    private JTabbedPane tabbedPane;
    private JLabel typeHeader;
    private JLabel typeHeader1;
    private JLabel typeText;
    private JLabel typeText1;
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
        itemTitle = new JLabel();
        typeHeader = new JLabel();
        typeText = new JLabel();
        bonusHeader = new JLabel();
        consumableHeader = new JLabel();
        bonusText = new JLabel();
        consumableText = new JLabel();
        addEffHeader = new JLabel();
        addEffText = new JLabel();
        itemDescription = new JLabel();
        abilityTab = new JPanel();
        jScrollPane3 = new JScrollPane();
        jList3 = new JList<>();
        jTextField3 = new JTextField();
        abilityTitle = new JLabel();
        typeHeader1 = new JLabel();
        typeText1 = new JLabel();
        bonusHeader1 = new JLabel();
        bonusText1 = new JLabel();
        addEffHeader1 = new JLabel();
        addEffText1 = new JLabel();
        abilityDescription = new JLabel();
        rangeHeader = new JLabel();
        radiusHeader = new JLabel();
        damageHeader = new JLabel();
        rangeText = new JLabel();
        radiusText = new JLabel();
        durationHeader = new JLabel();
        damageText = new JLabel();
        durationText = new JLabel();
        bonusHeader2 = new JLabel();
        bonusHeader3 = new JLabel();
        bonusText2 = new JLabel();
        bonusText3 = new JLabel();
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
        itemTitle.setText("[[ ITEM TITLE ]]");

        typeHeader.setFont(headerFont); // NOI18N
        typeHeader.setText("Type");

        typeText.setFont(fieldFont); // NOI18N
        typeText.setText("[[ type ]]");

        bonusHeader.setFont(headerFont); // NOI18N
        bonusHeader.setText("Bonus");

        consumableHeader.setFont(headerFont); // NOI18N
        consumableHeader.setText("Consumable?");

        bonusText.setFont(fieldFont); // NOI18N
        bonusText.setText("[[ bonus ]]");

        consumableText.setFont(fieldFont); // NOI18N
        consumableText.setText("[[ isConsumable ]]");

        addEffHeader.setFont(headerFont); // NOI18N
        addEffHeader.setText("Additional Effects");

        addEffText.setFont(fieldFont); // NOI18N
        addEffText.setText("[[ additional effects ]]");
        addEffText.setVerticalAlignment(SwingConstants.TOP);

        itemDescription.setFont(fieldFont); // NOI18N
        itemDescription.setText("1234567890123456789012345678901234567890");
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
        typeHeader1.setText("Type");

        typeText1.setFont(fieldFont); // NOI18N
        typeText1.setText("[[ type ]]");

        bonusHeader1.setFont(headerFont); // NOI18N
        bonusHeader1.setText("Bonus");

        bonusText1.setFont(fieldFont); // NOI18N
        bonusText1.setText("[[ bonus ]]");

        addEffHeader1.setFont(headerFont); // NOI18N
        addEffHeader1.setText("Additional Effects");

        addEffText1.setFont(fieldFont); // NOI18N
        addEffText1.setText("[[ additional effects ]]");
        addEffText1.setVerticalAlignment(SwingConstants.TOP);

        abilityDescription.setFont(fieldFont); // NOI18N
        abilityDescription.setText("1234567890123456789012345678901234567890");
        abilityDescription.setVerticalAlignment(SwingConstants.TOP);

        rangeHeader.setFont(headerFont); // NOI18N
        rangeHeader.setText("Range");

        radiusHeader.setFont(headerFont); // NOI18N
        radiusHeader.setText("Radius");

        damageHeader.setFont(headerFont); // NOI18N
        damageHeader.setText("Damage");

        rangeText.setFont(fieldFont); // NOI18N
        rangeText.setText("[[ range ]]");

        radiusText.setFont(fieldFont); // NOI18N
        radiusText.setText("[[ radius ]]");

        durationHeader.setFont(headerFont); // NOI18N
        durationHeader.setText("Duration");

        damageText.setFont(fieldFont); // NOI18N
        damageText.setText("[[ damage ]]");

        durationText.setFont(fieldFont); // NOI18N
        durationText.setText("[[ duration ]]");

        bonusHeader2.setFont(headerFont); // NOI18N
        bonusHeader2.setText("Cost");

        bonusHeader3.setFont(headerFont); // NOI18N
        bonusHeader3.setText("Req Level");

        bonusText2.setFont(fieldFont); // NOI18N
        bonusText2.setText("[[ cost ]]");

        bonusText3.setFont(fieldFont); // NOI18N
        bonusText3.setText("[[ req level ]]");

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
                                    .addComponent(bonusHeader2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bonusText3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bonusHeader3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bonusText2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                            .addComponent(bonusHeader2))
                        .addGap(6, 6, 6)
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(typeText1)
                            .addComponent(bonusText1)
                            .addComponent(bonusText2))
                        .addGap(18, 18, 18)
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(rangeHeader)
                            .addComponent(radiusHeader)
                            .addComponent(bonusHeader3))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abilityTabLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(rangeText)
                            .addComponent(radiusText)
                            .addComponent(bonusText3))
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
    }
}
