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
import as.project.tables.AbilityTable;
import as.project.tables.CharacterTable;
import as.project.tables.CharacterAbilityTable;
import as.project.tables.ChatHistoryTable;
import as.project.tables.FriendsTable;
import as.project.tables.UserTable;

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
	
	public static void main(String[] args){
		Main db = new Main();
		
		//Hard drive location of the database
		String location = "~/AlphaTeamDatabase/database";
		String user = "admin";
		String password = "admin";
		
		//Create the database connections, basically makes the database
		db.createConnection(location, user, password);
		
		try {
			
			/**
			 * Creates a sample Person table 
			 * and populates it from a csv file
			 */
			UserTable.createUserTable(db.getConnection());
			FriendsTable.createFriendsTable(db.getConnection());
			ChatHistoryTable.createChatHistoryTable(db.getConnection());
			AbilityTable.createAbilityTable(db.getConnection());
			CharacterTable.createCharacterTable(db.getConnection());
			CharacterAbilityTable.createCharacterAbilityTable(db.getConnection());
			
			User lh = new User(1, "Lukas", "Hillmer", "lhillmer", "leh5618@rit.edu", "test123");
			User sj = new User(2, "Scott", "Johnson", "sjohnson", "sxj@cs.rit.edu", "test456");
			Friends f = new Friends(1, 2);
			Friends f2 = new Friends(2, 1);
			ChatHistory ch = new ChatHistory(1,2, new Timestamp(123456789), "Hello professor");
			ChatHistory ch2 = new ChatHistory(2,1, new Timestamp(123459789), "Hello Lukas");
			Ability a1 = new Ability(1, 8, 10, 150, 10.0f, 5.0f, 7.0f,
					"Fire", "Fireball: Deals damage to a group of enemies.",
					"Burn: 30%.");
			Ability a2 = new Ability(2, 12, 14, 300, 5.0f, 3.0f, 5.0f,
					"Thunder", "Thunderwave: Casts an electryfing blast.",
					"Shock: 30%.");
			GameCharacter batman = new GameCharacter(1,1, 9,7,3,6,3,9, 1000000000, 100, 40, 80, 30, 11, 544, "Knight", "Lawful-Good", "Batman", "human");
			GameCharacter robin = new GameCharacter(2,1, 6,9,6,4,2,6, 1000, 70, 60, 50, 50, 7, 376, "Rouge", "Neutral-Good", "Robin", "human");
			
			UserTable.addUser(db.getConnection(), lh);
			UserTable.addUser(db.getConnection(), sj);
			FriendsTable.addFriends(db.getConnection(), f);
			FriendsTable.addFriends(db.getConnection(), f2);
			ChatHistoryTable.addChatHistory(db.getConnection(), ch);
			ChatHistoryTable.addChatHistory(db.getConnection(), ch2);
			AbilityTable.addAbility(db.getConnection(), a1);
			AbilityTable.addAbility(db.getConnection(), a2);
			CharacterTable.addCharacter(db.getConnection(), batman);
			CharacterTable.addCharacter(db.getConnection(), robin);
			CharacterAbilityTable.addCharacterAbility(db.getConnection(), batman, a1);
			CharacterAbilityTable.addCharacterAbility(db.getConnection(), batman, a2);
			CharacterAbilityTable.addCharacterAbility(db.getConnection(), robin, a2);
			
			/**
			 * Just displays the table
			 */
			UserTable.printUserTable(db.getConnection());
			FriendsTable.printFriendsTable(db.getConnection());
			ChatHistoryTable.printChatHistoryTable(db.getConnection());
			AbilityTable.printAbilityTable(db.getConnection());
			CharacterTable.printCharacterTable(db.getConnection());
			CharacterAbilityTable.printCharacterAbilityTable(db.getConnection());
			
			/**
			 * Runs a basic query on the table
			 */
			System.out.println("\n\nPrint results of SELECT * FROM user");
			ResultSet results = UserTable.queryUserTable(
					                     db.getConnection(),
					                     new ArrayList<String>(),
					                     new ArrayList<String>());
			
			/**
			 * Iterates the Result set
			 * 
			 * Result Set is what a query in H2 returns
			 * 
			 * Note the columns are not 0 indexed
			 * If you give no columns it will return them in the
			 * order you created them. To gaurantee order list the columns
			 * you want
			 */
			while(results.next()){
				System.out.printf("\tUser %d: %s %s %s %s\n", 
						          results.getInt(1),
						          results.getString(2),
						          results.getString(3),
						          results.getString(4),
						          results.getString(5),
						          results.getString(6));
			}
			
			/**
			 * A more complex query with columns selected and 
			 * addition conditions
			 */
			System.out.println("\n\nPrint results of SELECT "
					+ UserTable.USER_ID_COLUMN + ", " + UserTable.FIRST_NAME_COLUMN + " "
					+ "FROM " + UserTable.TABLE_NAME + " "
					+ "WHERE " + UserTable.FIRST_NAME_COLUMN + " = \'Lukas\' "
					+ "AND " + UserTable.LAST_NAME_COLUMN + " = \'Hillmer\'");
			
			/**
			 * This is one way to do this, but not the only
			 * 
			 * Create lists to make the whole thing more generic or
			 * you can just construct the whole query here 
			 */
			ArrayList<String> columns = new ArrayList<String>();
			columns.add(UserTable.USER_ID_COLUMN);
			columns.add(UserTable.FIRST_NAME_COLUMN);
			columns.add(UserTable.LAST_NAME_COLUMN);
			
			/**
			 * Conditionals
			 */
			ArrayList<String> whereClauses = new ArrayList<String>();
			whereClauses.add(UserTable.FIRST_NAME_COLUMN + " = \'Lukas\'");
			whereClauses.add(UserTable.LAST_NAME_COLUMN + " = \'Hillmer\'");
			
			/**
			 * query and get the result set
			 * 
			 * parse the result set and print it
			 * 
			 * Notice not all of the columns are here because
			 * we limited what to show in the query
			 */
			ResultSet results2 = UserTable.queryUserTable(
                    db.getConnection(),
                    columns,
                    whereClauses);
			while(results2.next()){
			System.out.printf("\tUser %d: %s %s\n", 
				          results2.getInt(1),
				          results2.getString(2),
				          results2.getString(3));
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e);
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("General exception: " + e);
			e.printStackTrace();
		}
	}

}
