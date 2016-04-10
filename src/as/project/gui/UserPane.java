/* File: UserWindow.java
 * 
 */ 

package as.project.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This class represents the content pane of the main GUI window. It is intended
 * that this pane(l) be swapped out with others throughout normal usage of the
 * program, although this should likely be the default display following
 * successful login.
 * @author Tommy Bohde
 */
public class UserPane extends JPanel
{
    private JLabel friendsLabel = new JLabel();
    private JLabel welcomeLabel = new JLabel();
    private JList<String> friendsList = new JList<>();
    private JScrollPane scrollPane = new JScrollPane();
    private JTabbedPane characterPanes = new JTabbedPane();
    private Border border = BorderFactory.createLineBorder(new Color(0,0,0));
	
    public UserPane() 
	{
		//build that pane
		super();

		//set up all components
        layOutComponents();
    }

    private void layOutComponents() 
	{
		//set up friends label
        friendsLabel.setText("Friends List");
        friendsLabel.setFont(new Font("Tahoma", 0, 18)); 
        friendsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		//set up welcome label
        welcomeLabel.setText("Welcome, USERNAME!");
        welcomeLabel.setFont(new Font("Tahoma", 0, 24)); 

		//set up friends list
        friendsList.setBorder(border);
        friendsList.setFont(new Font("Tahoma", 0, 14)); 
        friendsList.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollPane.setViewportView(friendsList);

		//set up character panes
        characterPanes.setBorder(border);
		//TODO: get character data from database here
		characterPanes.addTab("Tab 1", 
				new JPanel().add(new JLabel("This is tab 1.")));
		characterPanes.addTab("Tab 2", 
				new JPanel().add(new JLabel("This is tab 2.")));
		characterPanes.addTab("Character 3", 
				new JPanel().add(new JLabel("This is tab 3.")));
		characterPanes.addTab("Really Long Character Name 4", 
				new JPanel().add(new JLabel("This is tab 4.")));

		//lay it all out
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
		
		//commented lines are from the IDE and probably not necessary 
		//(i'm just being safe)
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(friendsLabel) 
					//, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(scrollPane))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(characterPanes)
                    .addComponent(welcomeLabel)) 
					//, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(friendsLabel) 
					//, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(welcomeLabel)) 
					//, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(characterPanes)
                    .addComponent(scrollPane)) 
					//, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addContainerGap())
        );
    }
}
