package as.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import as.project.objects.GameCharacter;
import as.project.objects.User;
import as.project.objects.Ability;
import as.project.objects.ChatHistory;
import as.project.objects.Friends;
import as.project.objects.Item ;
import as.project.tables.AbilityTable;
import as.project.tables.CharacterTable;
import as.project.tables.CharacterAbilityTable;
import as.project.tables.ChatHistoryTable;
import as.project.tables.FriendsTable;
import as.project.tables.UserTable;
import as.project.tables.ItemTable ;
import as.project.tables.CharacterItemTable ;


public class Main {
	//The connection to the database
	private Connection conn;
	
	/**
	 * Create a database connection with the given params
	 * @param location: path of where to place the database
	 * @param user: user name for the owner of the database
	 * @param password: password of the database owner
	 */
	public void createConnection(String location,
			                     String user,
			                     String password){
		try {
			
			//This needs to be on the front of your location
			String url = "jdbc:h2:" + location;
			
			//This tells it to use the h2 driver
			Class.forName("org.h2.Driver");
			
			//creates the connection
			conn = DriverManager.getConnection(url,
					                           user,
					                           password);
		} catch (SQLException | ClassNotFoundException e) {
			//You should handle this better
			e.printStackTrace();
		}
	}
	
	/**
	 * just returns the connection
	 * @return: returns class level connection
	 */
	public Connection getConnection(){
		return conn;
	}
	
	/**
	 * When your database program exits 
	 * you should close the connection
	 */
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createDatabase(Connection conn){
		
		//Hard drive location of the database
		String location = "~/AlphaTeamDatabase/database";
		String user = "admin";
		String password = "admin";
		
		try {
			
			/**
			 * Creates a sample Person table 
			 * and populates it from a csv file
			 */
			UserTable.createUserTable(conn);
			FriendsTable.createFriendsTable(conn);
			ChatHistoryTable.createChatHistoryTable(conn);
			AbilityTable.createAbilityTable(conn);
			CharacterTable.createCharacterTable(conn);
			CharacterAbilityTable.createCharacterAbilityTable(conn);
			ItemTable.createItemTable( conn ) ;
  			CharacterItemTable.createCharacterItemTable( conn ) ;
			
			User lh = new User(1, "Lukas", "Hillmer", "lhillmer", "leh5618@rit.edu", "test123");
			User sj = new User(2, "Scott", "Johnson", "sjohnson", "sxj@cs.rit.edu", "test456");
			Friends f = new Friends(1, 2);
			Friends f2 = new Friends(2, 1);
			ChatHistory ch = new ChatHistory(1,2, new Timestamp(123456789), "Hello professor");
			ChatHistory ch2 = new ChatHistory(2,1, new Timestamp(124456789), "Hello Lukas");
			ChatHistory ch4 = new ChatHistory(2,1, new Timestamp(126456789), "so many messages");
			ChatHistory ch3 = new ChatHistory(1,2, new Timestamp(125456789), "one more message");
			Ability a1 = new Ability(1, 8, 10, 150, 10.0f, 5.0f, 7.0f,
					"Fire", "Fireball: Deals damage to a group of enemies.",
					"Burn: 30%.");
			Ability a2 = new Ability(2, 12, 14, 300, 5.0f, 3.0f, 5.0f,
					"Thunder", "Thunderwave: Casts an electryfing blast.",
					"Shock: 30%.");
			GameCharacter batman = new GameCharacter(1,1, 9,7,3,6,3,9, 1000000000, 100, 40, 80, 30, 11, 544, "Knight", "Lawful-Good", "Batman", "human");
			GameCharacter robin = new GameCharacter(2,1, 6,9,6,4,2,6, 1000, 70, 60, 50, 50, 7, 376, "Rouge", "Neutral-Good", "Robin", "human");
			Item i = new Item( 1, "Null", "Broadsword", false, "weapon", 10, "Fighter" );
			Item i2 = new Item( 2, "Null", "Batarang", false, "weapon", 1, "Fighter" );
			Item i3 = new Item( 3, "Null", "Utility Belt", false, "accessory", 0, "Fighter" );
			Item i4 = new Item( 4, "Null", "Bat Suit", false, "armor", 8, "Fighter" );
			Item i5 = new Item( 5, "Null", "Robin Suit", false, "armor", 4, "Fighter" );
			Item i6 = new Item( 6, "Null", "Batarang", false, "weapon", 1, "Fighter" );
			Item i7 = new Item( 7, "Null", "Utility Belt", false, "accessory", 0, "Fighter" );
			Item i8 = new Item( 8, "Null", "Bat Suit", false, "armor", 8, "Fighter" );
			Item i9 = new Item( 9, "Null", "Batarang", false, "weapon", 1, "Fighter" );
			Item i10 = new Item( 10, "Null", "Utility Belt", false, "accessory", 0, "Fighter" );
			Item i11 = new Item( 11, "Null", "Bat Suit", false, "armor", 8, "Fighter" );
			
			UserTable.addUser(conn, lh);
			UserTable.addUser(conn, sj);
			FriendsTable.addFriends(conn, f);
			FriendsTable.addFriends(conn, f2);
			ChatHistoryTable.addChatHistory(conn, ch);
			ChatHistoryTable.addChatHistory(conn, ch2);
			ChatHistoryTable.addChatHistory(conn, ch4);
			ChatHistoryTable.addChatHistory(conn, ch3);
			AbilityTable.addAbility(conn, a1);
			AbilityTable.addAbility(conn, a2);
			CharacterTable.addCharacter(conn, batman);
			CharacterTable.addCharacter(conn, robin);
			CharacterAbilityTable.addCharacterAbility(conn, batman, a1);
			CharacterAbilityTable.addCharacterAbility(conn, batman, a2);
			CharacterAbilityTable.addCharacterAbility(conn, robin, a2);
			ItemTable.addItem(conn, i);
			ItemTable.addItem(conn, i2);
			ItemTable.addItem(conn, i3);
			ItemTable.addItem(conn, i4);
			ItemTable.addItem(conn, i5);
			ItemTable.addItem(conn, i6);
			ItemTable.addItem(conn, i7);
			ItemTable.addItem(conn, i8);
			ItemTable.addItem(conn, i9);
			ItemTable.addItem(conn, i10);
			ItemTable.addItem(conn, i11);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i.getID());
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i2.getID());
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i3.getID());
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i4.getID());
			CharacterItemTable.giveItem(conn, robin.getCharacterId(), i5.getID());
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i6.getID());
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i7.getID());
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i8.getID());
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i9.getID());
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i10.getID());
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i11.getID());
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e);
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("General exception: " + e);
			e.printStackTrace();
		}
	}

}
