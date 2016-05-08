/* File: UserWindow.java
 * 
 */ 

package as.project.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import as.project.objects.*;
import as.project.tables.CharacterTable;
import as.project.tables.FriendsTable;

/**
 * This class represents the content pane of the main GUI window. It is intended
 * that this pane(l) be swapped out with others throughout normal usage of the
 * program, although this should likely be the default display following
 * successful login.
 * @author Tommy Bohde
 */
public class UserPane extends JPanel implements ListSelectionListener, ActionListener
{
    private JLabel friendsLabel = new JLabel();
    private JLabel welcomeLabel = new JLabel();
    private DefaultListModel<User> fListModel = new DefaultListModel<>();
    private JList<User> friendsList = new JList<>(fListModel);
	private JButton removeButton = new JButton("Remove Friend");
	private JButton friendCharsButton = new JButton("Friend's Characters");
	private boolean areUsersCharsShowing = true;
    private JScrollPane scrollPane = new JScrollPane();
    private JTabbedPane characterPanes = new JTabbedPane();
    private ChatWindow chatWindow = null;
    private String chatWindowTabTitle = "";
    private Border border = BorderFactory.createLineBorder(new Color(0,0,0));
	
	private User user;

    public UserPane(User user) 
	{
		//build that pane
		super();

		//load user info from database
		this.user = user;
		chatWindow = new ChatWindow(user);

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
        welcomeLabel.setText("Welcome, " + user.getDisplayName() + "!");
        welcomeLabel.setFont(new Font("Tahoma", 0, 24)); 

		//set up friends list
        friendsList.setBorder(border);
        friendsList.setFont(new Font("Tahoma", 0, 14)); 
        
        removeButton.addActionListener(this);
        friendCharsButton.addActionListener(this);
	    
	    friendsList.addListSelectionListener(this);
        scrollPane.setViewportView(friendsList);
        Dimension d = scrollPane.getPreferredSize();
        d.width = MainGUI.SIDEBAR_WIDTH;
        scrollPane.setPreferredSize(d);
        reloadFriendsList();

		//set up character panes
        characterPanes.setBorder(border);
        showCharacterTabs(user);
        
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
                    .addComponent(scrollPane)
                    .addComponent(friendCharsButton)
                	.addComponent(removeButton))
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(characterPanes)
                    .addGroup(layout.createSequentialGroup()
                		.addComponent(scrollPane)
                		.addComponent(friendCharsButton)
                    	.addComponent(removeButton)))
					//, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addContainerGap())
        );
    }
    
    public void reloadFriendsList(){
    	fListModel.clear();
	    ArrayList<User> friends = FriendsTable.getFriendsOfUser(MainGUI.getConnection(),user.getUserId());
	    for(User f: friends){
	    	fListModel.addElement(f);
	    }
    }
    
    private void removeChatTab(){
    	int index = characterPanes.indexOfTab(chatWindowTabTitle);
		if(index != -1){
			characterPanes.removeTabAt(index);
		}
    }
    
    private void removeCharacterTabs(){
    	int chatIndex = characterPanes.indexOfTab(chatWindowTabTitle);
		for(int i = 0; i < characterPanes.getTabCount(); i++){
			if(i != chatIndex){
				characterPanes.removeTabAt(i);
				chatIndex = characterPanes.indexOfTab(chatWindowTabTitle);
				i--;
			}
		}
    }
    
    private void showCharacterTabs(User u){
        ArrayList<GameCharacter> characterList = CharacterTable.getUserCharacters(MainGUI.getConnection(), u.getUserId());
        for(GameCharacter gc: characterList){
        	characterPanes.addTab(gc.getCharacterName(),
        			new JGameCharacterPanel(gc));
        }
    }

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()){
			if(e.getSource() == friendsList){
				User f = friendsList.getSelectedValue();
				if(f != null){
					chatWindow.setFriend(f);
					chatWindowTabTitle = "Chat: " + f;
					characterPanes.addTab(chatWindowTabTitle, chatWindow);
					int index = characterPanes.indexOfTab(chatWindowTabTitle);
					if(index != -1){
						characterPanes.setSelectedIndex(index);
					}
				}
				else{
					removeChatTab();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == removeButton){
			User u = friendsList.getSelectedValue();
			if(u != null){
				Friends f = new Friends(user.getUserId(), u.getUserId());
				FriendsTable.removeFriends(MainGUI.getConnection(), f, true);
				removeChatTab();
				reloadFriendsList();
			}
		}
		else if(e.getSource() == friendCharsButton){
			if(areUsersCharsShowing){
				User f = friendsList.getSelectedValue();
				if(f != null)
				{
					//show friends characters
					removeCharacterTabs();
					showCharacterTabs(f);
					friendCharsButton.setText("Your Characters");
					areUsersCharsShowing = !areUsersCharsShowing;
				}
			} else {
				//show your characters
				removeCharacterTabs();
				showCharacterTabs(user);
				friendCharsButton.setText("Friend's Characters");
				areUsersCharsShowing = !areUsersCharsShowing;
			}
		}
	}
}
