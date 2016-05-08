/* File: MainGUI.java
 * 
 * TODO: Merge this file into another (possible UserWindow?)
 */ 

package as.project.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import as.project.Main;

/**
 * This class is the main class of the GUI package. Instantiates a UserWindow
 * and makes it visible on screen.
 */
public class MainGUI
{
    //The connection to the database
    private static Connection conn;

    private static boolean isDatabaseInitialized;
        
    //Hard drive location of the database
    private static final String DEFAULT_LOCATION = "~/AlphaTeamDatabase/database";
    private static final String DEFAULT_USER = "admin";
    private static final String DEFAULT_PASSWORD = "admin";
    private static final String IF_EXISTS_LOCATION = ";ifexists=true";
    
    public static final Integer SIDEBAR_WIDTH = 141;


    
    /**
     * Create a database connection with the given params
     * @param location: path of where to place the database
     * @param user: user name for the owner of the database
     * @param password: password of the database owner
     */
    private static void createConnection(String location,
                                 String user,
                                 String password,
                                 boolean mustExist){
        try {
            isDatabaseInitialized = mustExist;
            //This needs to be on the front of your location""
            String url = "jdbc:h2:" + location + (mustExist ? IF_EXISTS_LOCATION : "");
            
            //This tells it to use the h2 driver
            Class.forName("org.h2.Driver");
            
            //creates the connection
            conn = DriverManager.getConnection(url,
                                               user,
                                               password);
        } catch (SQLException | ClassNotFoundException e) {
            if(mustExist){
                createConnection(location, user, password, false);
            } else {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * just returns the connection
     * @return: returns class level connection
     */
    public static Connection getConnection(){
        return conn;
    }

	public static void main(String[] args)
	{
        //create the connection
        createConnection(DEFAULT_LOCATION, DEFAULT_USER, DEFAULT_PASSWORD, true);
        if(!isDatabaseInitialized){
            Main.createDatabase(getConnection());
        }

        //TODO: if the connection is null, then we should display an error on the UserWindow, instead of the login view

		//Create and display main user window
		UserWindow login = new UserWindow();
		login.setVisible(true);
	}
}
