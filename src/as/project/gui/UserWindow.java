/* File: UserWindow.java
 * 
 * TODO: Add JMenuBar (delayed functionality)
 */ 

package as.project.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the main window of the GUI.
 * @author Tommy Bohde
 */
public class UserWindow extends JFrame implements ActionListener
{
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JMenu menuUser = new JMenu("User");
	private JMenuItem fileExit = new JMenuItem("Exit");
	private JMenuItem userLogIn = new JMenuItem("Log in");
	private JMenuItem userLogOut = new JMenuItem("Log out");

	private boolean userLoggedIn = false;
	
	public UserWindow()
	{
		//build the frame
		super("Welcome");

		//layout stuff
		layOutWindow();

		//set up action listeners
		fileExit.addActionListener(this);
		userLogIn.addActionListener(this);
		userLogOut.addActionListener(this);

		//final housekeeping
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		//display login window
		LoginWindow login = new LoginWindow(this);
		login.setVisible(true);

	}

	private void layOutWindow()
	{
		//set up the menu bar
		this.setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuBar.add(menuUser);
		menuFile.add(fileExit);
		menuUser.add(userLogIn);
		menuUser.add(userLogOut);
	}

	public boolean authenticateUser(String username, String password)
	{
		System.out.println("Authenticating user " + username +
							" with password "  + password);
		//TODO: SQL to find user in database
		if (true) 
		{
			loadUser(username);
			userLoggedIn = true;
			return true;
		}
		//if not found
		return false;
	}

	private void loadUser(String username)
	{
		//TODO: load all user's data from database
		UserPane pane = new UserPane();
		this.setContentPane(pane);
		validate();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == fileExit)
		{
			//TODO: shutdown function
			//  - log out user
			//  - save/close database
			//  - clean program exit
		}
		else if (e.getSource() == userLogIn)
		{
			if (userLoggedIn)
			{
				//TODO
				//log out user? prompt? nothing?
			}
			else
			{
				//TODO: display login window
			}
		}
		else if (e.getSource() == userLogOut)
		{
			if (userLoggedIn)
			{
				//TODO: log out user
			}
			else
			{
				//TODO
				//prompt? nothing?
			}
		}
	}
}
