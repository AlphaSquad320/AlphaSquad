package as.project.gui;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import as.project.objects.GameCharacter;
import as.project.objects.User;
import as.project.tables.CharacterTable;
import as.project.tables.FriendsTable;

public class EditCharactersPane extends JScrollPane implements ListSelectionListener, ActionListener{

    private JLabel charactersLabel = new JLabel();
    private DefaultListModel<GameCharacter> cListModel = new DefaultListModel<>();
    private JList<GameCharacter> charactersList = new JList<>(cListModel);
	private JButton removeButton = new JButton("Remove Character");
    private JScrollPane scrollPane = new JScrollPane();
    private EditableCharacterPanel cPanel = new EditableCharacterPanel();
    private Border border = BorderFactory.createLineBorder(new Color(0,0,0));
    
    private JPanel panel = new JPanel();
    
    private User user;
    
    public EditCharactersPane(){
    	layOutComponents();
    }
    
    public void setUser(User u){
    	user = u;
    	reloadCharacterList();
    }
    
    public void layOutComponents(){
    	//set up friends label
    	charactersLabel.setText("Characters List");
    	charactersLabel.setFont(new Font("Tahoma", 0, 18)); 
    	charactersLabel.setHorizontalAlignment(SwingConstants.CENTER);

		//set up friends list
        charactersList.setBorder(border);
        charactersList.setFont(new Font("Tahoma", 0, 14)); 
        
        removeButton.addActionListener(this);
	    
        charactersList.addListSelectionListener(this);
        scrollPane.setViewportView(charactersList);
        Dimension d = scrollPane.getPreferredSize();
        d.width = MainGUI.SIDEBAR_WIDTH;
        scrollPane.setPreferredSize(d);
        reloadCharacterList();

		//set up character pane
        cPanel.setBorder(border);
        Dimension cd = cPanel.getPreferredSize();
        cd.setSize(288, 496);
        cPanel.setPreferredSize(cd);
        
		//lay it all out
        this.setViewportView(panel);
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(charactersLabel) 
                    .addComponent(scrollPane)
                	.addComponent(removeButton, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cPanel)) 
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(charactersLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(cPanel)
                    .addGroup(layout.createSequentialGroup()
                		.addComponent(scrollPane)
                    	.addComponent(removeButton)))
                .addContainerGap())
        );
    }
    
    public void reloadCharacterList(){
    	cListModel.clear();
    	if(user != null)
    	{
		    ArrayList<GameCharacter> chars = CharacterTable.getUserCharacters(MainGUI.getConnection(),user.getUserId());
		    for(GameCharacter c: chars){
		    	cListModel.addElement(c);
		    }
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == removeButton){
			GameCharacter c = charactersList.getSelectedValue();
			if(c != null){
				CharacterTable.removeCharacterSafe(MainGUI.getConnection(), c, user, true);
				reloadCharacterList();
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()){
			if(e.getSource() == charactersList){
				GameCharacter c = charactersList.getSelectedValue();
				if(c != null){
					cPanel.setCharacter(c);
				}
			}
		}
	}
	
}
