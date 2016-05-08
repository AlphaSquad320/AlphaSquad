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
import as.project.objects.NPC;
import as.project.tables.AbilityTable;
import as.project.tables.CharacterTable;
import as.project.tables.CharacterAbilityTable;
import as.project.tables.ChatHistoryTable;
import as.project.tables.FriendsTable;
import as.project.tables.UserTable;
import as.project.tables.ItemTable ;
import as.project.tables.NPCTable;
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
			ItemTable.createItemTable( conn );
  			CharacterItemTable.createCharacterItemTable( conn );
  			NPCTable.createNPCTable(conn);
			
			User lh = new User(1, "Lukas", "Hillmer", "lhillmer", "leh5618@rit.edu", "test123");
			User sj = new User(2, "Scott", "Johnson", "sjohnson", "sxj@cs.rit.edu", "test456");
			User third = new User(3, "THIRD", "USER", "THIRD_USER", "THIRD_USER@rit.edu", "THIRD");
			User fourth = new User(4, "FOURTH", "USER", "FOURTH_USER", "FOURTH_USER@rit.edu", "FOURTH");
			User NPC_OWNER = new User(UserTable.NPC_USER_ID, "NPC", "OWNER", "NPCOWNER", "none@example.com", "npc");
			Friends f = new Friends(1, 2);
			Friends f2 = new Friends(2, 1);
			Friends f3 = new Friends(1, 3);
			Friends f4 = new Friends(3, 1);
			ChatHistory ch = new ChatHistory(1,2, new Timestamp(123456789), "Hello professor");
			ChatHistory ch2 = new ChatHistory(2,1, new Timestamp(124456789), "Hello Lukas");
			ChatHistory ch4 = new ChatHistory(2,1, new Timestamp(126456789), "so many messages");
			ChatHistory ch3 = new ChatHistory(1,2, new Timestamp(125456789), "one more message");
			ChatHistory ch5 = new ChatHistory(1,3, new Timestamp(123456789), "MESSAGES");
			ChatHistory ch6 = new ChatHistory(1,3, new Timestamp(124456789), "CAPS LOCK");
			ChatHistory ch7 = new ChatHistory(1,3, new Timestamp(126456789), "CRUISE CONTROL");
			ChatHistory ch8 = new ChatHistory(3,1, new Timestamp(125456789), "FOR COOL");
			Ability a1 = new Ability(1, 8, 10, 150, 10.0f, 5.0f, 7.0f,
					"Fire", "Fireball", "Deals damage to a group of enemies.",
					"Burn: 30%.");
			Ability a2 = new Ability(2, 12, 14, 300, 5.0f, 3.0f, 5.0f,
					"Thunder", "Thunderwave", "Casts an electryfing blast.",
					"Shock: 30%.");
			GameCharacter batman = new GameCharacter(1,1, 9,7,3,6,3,9, 1000000000, 100, 40, 80, 30, 11, 544, "Knight", "Lawful-Good", "Batman", "human");
			GameCharacter robin = new GameCharacter(2,1, 6,9,6,4,2,6, 1000, 70, 60, 50, 50, 7, 376, "Rouge", "Neutral-Good", "Robin", "human");
			GameCharacter joker = new GameCharacter(3,UserTable.NPC_USER_ID, 6,9,4,8,1,9, 951753, 80, 40, 70, 30, 12, 900, "Assassin", "Chaotic-Evila", "Joker", "human");
			NPC jNPC = new NPC(1, 3, true, "We kill the Batman", "A crazed madman");
			GameCharacter oracle = new GameCharacter(4,UserTable.NPC_USER_ID, 2,2,2,9,8,5, 60000, 20, 100, 20, 80, 5, 200, "Technomancer", "Lawful-Good", "Oracle", "human");
			NPC oNPC = new NPC(2, 4, true, "Research new technology", "A brilliant computer-wiz");
			Item i = new Item( 1, "Broadsword", "Null","A large sword", false, "weapon", 10, "Fighter" );
			Item i2 = new Item( 2,"Batarang", "Null","A bat shaped boomerang", false, "weapon", 1, "Fighter" );
			Item i3 = new Item( 3, "Utility Belt", "Null","A belt with tons of handy gadgets", false, "accessory", 0, "Fighter" );
			Item i4 = new Item( 4, "Bat Suit", "Null","A heavy suit with lots of protection", false, "armor", 8, "Fighter" );
			Item i5 = new Item( 5, "Robin Suit", "Null","A light suit with lots of movement", false, "armor", 4, "Fighter" );
			Item i6 = new Item( 6, "Batarang", "Null","A bat shaped boomerang", false, "weapon", 1, "Fighter" );
			Item i7 = new Item( 7, "Utility Belt", "Null","A belt with tons of handy gadgets", false, "accessory", 0, "Fighter" );
			Item i8 = new Item( 8, "Bat Suit", "Null","A heavy suit with lots of protection", false, "armor", 8, "Fighter" );
			Item i9 = new Item( 9, "Batarang", "Null","A bat shaped boomerang", false, "weapon", 1, "Fighter" );
			Item i10 = new Item( 10, "Utility Belt", "Null","A belt with tons of handy gadgets", false, "accessory", 0, "Fighter" );
			Item i11 = new Item( 11, "Bat Suit", "Null","A heavy suit with lots of protection", false, "armor", 8, "Fighter" );
			
			UserTable.addUser(conn, lh, false);
			UserTable.addUser(conn, sj, false);
			UserTable.addUser(conn, third, false);
			UserTable.addUser(conn, fourth, false);
			UserTable.addUser(conn, NPC_OWNER, false);
			FriendsTable.addFriends(conn, f, false);
			FriendsTable.addFriends(conn, f2, false);
			FriendsTable.addFriends(conn, f3, false);
			FriendsTable.addFriends(conn, f4, false);
			ChatHistoryTable.addChatHistory(conn, ch, false);
			ChatHistoryTable.addChatHistory(conn, ch2, false);
			ChatHistoryTable.addChatHistory(conn, ch4, false);
			ChatHistoryTable.addChatHistory(conn, ch3, false);
			ChatHistoryTable.addChatHistory(conn, ch5, false);
			ChatHistoryTable.addChatHistory(conn, ch6, false);
			ChatHistoryTable.addChatHistory(conn, ch7, false);
			ChatHistoryTable.addChatHistory(conn, ch8, false);
			AbilityTable.addAbility(conn, a1, false);
			AbilityTable.addAbility(conn, a2, false);
			CharacterTable.addCharacter(conn, batman, false);
			CharacterTable.addCharacter(conn, robin, false);
			CharacterTable.addCharacter(conn, joker, false);
			CharacterTable.addCharacter(conn, oracle, false);
			CharacterAbilityTable.addCharacterAbility(conn, batman, a1, false);
			CharacterAbilityTable.addCharacterAbility(conn, batman, a2, false);
			CharacterAbilityTable.addCharacterAbility(conn, robin, a2, false);
			CharacterAbilityTable.addCharacterAbility(conn, joker, a2, false);
			CharacterAbilityTable.addCharacterAbility(conn, oracle, a1, false);
			NPCTable.addNPC(conn, jNPC, false);
			NPCTable.addNPC(conn, oNPC, false);
			ItemTable.addItem(conn, i, false);
			ItemTable.addItem(conn, i2, false);
			ItemTable.addItem(conn, i3, false);
			ItemTable.addItem(conn, i4, false);
			ItemTable.addItem(conn, i5, false);
			ItemTable.addItem(conn, i6, false);
			ItemTable.addItem(conn, i7, false);
			ItemTable.addItem(conn, i8, false);
			ItemTable.addItem(conn, i9, false);
			ItemTable.addItem(conn, i10, false);
			ItemTable.addItem(conn, i11, false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i.getID(), false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i2.getID(), false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i3.getID(), false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i4.getID(), false);
			CharacterItemTable.giveItem(conn, robin.getCharacterId(), i5.getID(), false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i6.getID(), false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i7.getID(), false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i8.getID(), false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i9.getID(), false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i10.getID(), false);
			CharacterItemTable.giveItem(conn, batman.getCharacterId(), i11.getID(), false);
			CharacterItemTable.giveItem(conn, joker.getCharacterId(), i10.getID(), false);
			CharacterItemTable.giveItem(conn, oracle.getCharacterId(), i11.getID(), false);
			
		} catch (SQLException e) {
			System.out.println("SQLException: " + e);
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("General exception: " + e);
			e.printStackTrace();
		}
	}

}
