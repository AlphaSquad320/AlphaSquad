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
	public UserWindow()
	{
		//build the frame
		super("Welcome");

		//layout stuff

		//final housekeeping
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//pack();
		setLocationRelativeTo(null);
		setVisible(true);

		//display login window
		LoginWindow login = new LoginWindow(this);
		login.setVisible(true);

	}

	public boolean authenticateUser(String username, String password)
	{
		System.out.println("Authenticating user " + username +
							" with password "  + password);
		if (true) //TODO: SQL to find user in database
		{
			loadUser(username);
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

	}
}
