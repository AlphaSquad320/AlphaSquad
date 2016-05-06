/* File: UserWindow.java
 * 
 * TODO: Create a welcome background for delayed login
 */ 

package as.project.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import as.project.objects.*;
import as.project.tables.UserTable;

/**
 * This class represents the main window of the GUI.
 * @author Tommy Bohde
 */
public class UserWindow extends JFrame implements ActionListener
{
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JMenu menuUser = new JMenu("User");
	private JMenu menuView = new JMenu("View");
	private JMenuItem fileExit = new JMenuItem("Exit");
	private JMenuItem userLogIn = new JMenuItem("Log in");
	private JMenuItem userLogOut = new JMenuItem("Log out");
	private JMenuItem viewUser = new JMenuItem("Profile View");
	private JMenuItem viewEnc = new JMenuItem("Encyclopedia View");
	private UserPane userPane = null;
	private EncyclopediaPane encPane = new EncyclopediaPane();

	private User user;
	
	public UserWindow()
	{
		//build the frame
		super("Welcome");
		user = null;

		//layout stuff
		//TODO: this doesn't set a content pane. should it? (probably)
		layOutWindow();


		//set up action listeners
		fileExit.addActionListener(this);
		userLogIn.addActionListener(this);
		userLogOut.addActionListener(this);
		viewUser.addActionListener(this);
		viewEnc.addActionListener(this);

		//final housekeeping
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//display login window by default
		displayLoginWindow();

	}

	private void layOutWindow()
	{
		//set up the menu bar
		this.setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuBar.add(menuUser);
		menuBar.add(menuView);
		menuFile.add(fileExit);
		menuUser.add(userLogIn);
		menuUser.add(userLogOut);
		menuView.add(viewUser);
		menuView.add(viewEnc);

		userLogIn.setEnabled(true);
		userLogOut.setEnabled(false);
		viewUser.setEnabled(false);
		viewEnc.setEnabled(false);
	}

	private void displayLoginWindow()
	{
		LoginWindow login = new LoginWindow(this);
		login.setVisible(true);
	}
	
	private void logOutUser()
	{
		user = null;
		userLogIn.setEnabled(true);
		userLogOut.setEnabled(false);
		viewUser.setEnabled(false);
		viewEnc.setEnabled(false);
		this.setContentPane(new JPanel());
		validate();
		
	}

	public boolean authenticateUser(String username, String password)
	{
		User loggedIn = UserTable.authenticateUser(MainGUI.getConnection(), username, password);
		if (loggedIn != null) 
		{
			user = loggedIn;
			userPane = new UserPane(user);
			this.setContentPane(userPane);
			validate();
			userLogIn.setEnabled(false);
			userLogOut.setEnabled(true);
			viewUser.setEnabled(true);
			viewEnc.setEnabled(true);
			return true;
		}
		//if not found
		return false;
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == fileExit)
		{
			//TODO: shutdown function
			//  - save/close database
			//  - clean program exit
			logOutUser();
		}
		else if (e.getSource() == userLogIn)
		{
			if (user != null)
			{
				//TODO
				//log out user? prompt? nothing?
			}
			else
			{
				displayLoginWindow();
			}
		}
		else if (e.getSource() == userLogOut)
		{
			logOutUser();
			System.out.println("womp");
		}
		else if(e.getSource() == viewUser){
			this.setContentPane(userPane);
			validate();
		}
		else if(e.getSource() == viewEnc){
			this.setContentPane(encPane);
			validate();
		}
	}
}
