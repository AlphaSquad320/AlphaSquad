/* File: LoginWindow.java
 *
 * TODO: Handle invalid login information.
 */ 

package as.project.gui;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;

/**
 * This class represents the login dialog displayed when the program
 * is started. It is intended that this dialog be modal to it's parent.
 * @author Tommy Bohde
 */
public class LoginWindow extends JDialog implements ActionListener
{
	private GroupLayout layout;
	private JLabel username = new JLabel("Username:");
	private JTextField usernameField = new JTextField(16);
	private JLabel password = new JLabel("Password:");
	private JPasswordField passwordField = new JPasswordField(16);
	private JButton login = new JButton("Login");
	private JLabel errorText = new JLabel("Invalid Login");

	private UserWindow parent;

	private String usernameValue;
	private String passwordValue;

	public LoginWindow(UserWindow parent)
	{
		//build that frame
		super(parent, "Login", true);

		//know your roots
		this.parent = parent;

		//create and assign the layout manager
		layout = new GroupLayout(this.getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		errorText.setVisible(false);
		setLayout(layout);

		//make things look pretty
		layOutComponents();
		pack();
		setLocationRelativeTo(parent);

		//actionListener setup
		login.addActionListener(this);

		/*
		//dispose parent on close
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosed(WindowEvent e)
			{ parent.dispose(); }
		});
		*/

		//final housekeeping
		this.getRootPane().setDefaultButton(login);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * Private helper function to lay out all the components according to the
	 * layout manager standards.
	 */
	private void layOutComponents()
	{
		//set up the horizontal layout
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(username)
				.addComponent(password)
				.addComponent(login))
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(usernameField)
				.addComponent(passwordField)
				.addComponent(errorText)));

		//set up the vertical layout
		layout.setVerticalGroup(
			layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(username)
					.addComponent(usernameField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(password)
					.addComponent(passwordField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(login)
						.addComponent(errorText)));
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == login)
		{
			usernameValue = usernameField.getText();
			passwordValue = new String(passwordField.getPassword());
			if (!parent.authenticateUser(usernameValue, passwordValue))
			{
				errorText.setVisible(true);
				passwordField.setText(null);
			}
			else
				this.dispose();
		}
	}
}
