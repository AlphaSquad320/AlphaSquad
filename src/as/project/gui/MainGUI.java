/* File: MainGUI.java
 * 
 * TODO: Merge this file into another (possible UserWindow?)
 */ 

package as.project.gui;

import javax.swing.*;
import java.awt.*;

import as.project.Main;

/**
 * This class is the main class of the GUI package. Instantiates a UserWindow
 * and makes it visible on screen.
 */
public class MainGUI
{
	public static void main(String[] args)
	{
		//Create and display main user window
		UserWindow login = new UserWindow();
		login.setVisible(true);

		//execute as.project.Main.main() to create the sample database
		Main main = new Main();
		main.main(new String[0]);
	}
}
