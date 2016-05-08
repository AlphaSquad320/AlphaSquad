package as.project.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import as.project.objects.ChatHistory;
import as.project.objects.User;
import as.project.tables.ChatHistoryTable;

public class ChatWindow extends JPanel implements ActionListener{
	
	private static String textFormat = "%1s<%2s>:%3s";
	
	private User curUser;
	private User friend;
	
	private JScrollPane chatWindow = new JScrollPane();
	private JTextArea messages = new JTextArea();
	private JTextField input = new JTextField();
	private JButton sendButton = new JButton("Send");

    private Font font = new Font("Courier New", 0, 14);
    
    public ChatWindow(User curUser){
    	super();
    	this.curUser = curUser;
    	layoutComponents();
    }
    
    private void layoutComponents(){
    	chatWindow.setViewportView(messages);
    	messages.setEditable(false);
    	messages.setFont(font);
    	input.setFont(font);
    	sendButton.addActionListener(this);
    	
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(
					layout.createParallelGroup(Alignment.LEADING)
					.addComponent(chatWindow)
					.addGroup(layout.createSequentialGroup()
							.addComponent(input)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(sendButton)))
				.addContainerGap());
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(chatWindow)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(input)
						.addComponent(sendButton))
				.addContainerGap());
    }
    
    private void loadMessages(){
    	StringBuilder log = new StringBuilder();
    	log.append("Chat History:\n");
    	if(friend != null){
	    	ArrayList<ChatHistory> chList = ChatHistoryTable.getConversation(MainGUI.getConnection(), curUser.getUserId(), friend.getUserId());
	    	for(ChatHistory ch : chList){
	    		log.append(getFormattedMessage(ch));
	    		log.append("\n");
	    	}
    	}
    	messages.setText(log.toString());
    }
    
    private void sendMessage(String message){
    	if(friend != null){
	    	ChatHistory ch = new ChatHistory(curUser.getUserId(), friend.getUserId(), Timestamp.from(Instant.now()), message);
	    	ChatHistoryTable.addChatHistory(MainGUI.getConnection(), ch, true);
	    	messages.setText(messages.getText() + getFormattedMessage(ch) + "\n");
    	}
    }
    
    private String getFormattedMessage(ChatHistory ch){
		User sender = (ch.getSenderId() == curUser.getUserId() ? curUser : friend);
    	return String.format(textFormat, sender, ch.getTimestamp(), ch.getMessage());
    }
    
    public void setFriend(User friend){
    	this.friend = friend;
    	loadMessages();
    }
    
    public User getFriend(){
    	return friend;
    }
    
    public User getCurUser(){
    	return curUser;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton){
			sendMessage(input.getText());
			input.setText(null);
		}
	}
	

}
