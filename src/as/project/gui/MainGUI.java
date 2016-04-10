/* File: UserWindow.java
 * 
 * TODO: Merge this file into another (possible UserWindow?)
 */ 

package as.project.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the main class of the GUI package. Instantiates a UserWindow
 * and makes it visible on screen.
 */
public class MainGUI
{
	public static void main(String[] args)
	{
		UserWindow login = new UserWindow();
		login.setVisible(true);
	}
}
