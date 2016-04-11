package as.project.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import as.project.objects.Friends;
import as.project.objects.User;

public class FriendsTable extends TableBase {
	
	public static final String SENDER_COLUMN = "SENDER";
	public static final String RECEIVER_COLUMN = "RECEIVER";
	
	public static final String TABLE_NAME = "friends";
	
	
	/**
	 * Create the user table with the given attributes
	 * 
	 * @param conn: the database connection to work with
	 */
	public static void createFriendsTable(Connection conn) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
				     + SENDER_COLUMN + " INT,"
				     + RECEIVER_COLUMN + " INT,"
				     + "PRIMARY KEY(" + SENDER_COLUMN +", " + RECEIVER_COLUMN + "),"
				     + "FOREIGN KEY(" + SENDER_COLUMN + ") REFERENCES " + UserTable.TABLE_NAME + "(" + UserTable.USER_ID_COLUMN + "),"
				     + "FOREIGN KEY(" + RECEIVER_COLUMN + ") REFERENCES " + UserTable.TABLE_NAME + "(" + UserTable.USER_ID_COLUMN + ")"
				     + ");" ;
		
		/**
		 * Create a query and execute
		 */
		Statement stmt = conn.createStatement();
		stmt.execute(query);
	}
	
	/**
	 * Adds a single user to the database
	 * 
	 * @param conn connection string
	 * @param id userId
	 * @param fName firstName
	 * @param lName lastName
	 * @param dName displayName
	 * @param email emailAddress
	 * @param pw password
	 */
	public static void addFriends(Connection conn,
			                     int senderId,
			                     int receiverId){
		
		/**
		 * SQL insert statement
		 */
		String query = String.format("INSERT INTO " + TABLE_NAME + " " + "VALUES(%d,%d);",senderId, receiverId);
		try {
			/**
			 * create and execute the query
			 */
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			System.out.println("Issue adding friends: " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user user to add
	 */
	public static void addFriends(Connection conn, Friends friends){
		addFriends(conn, friends.getSenderId(), friends.getReceiverId());
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user list of users to add
	 */
	public static void addFriendsList(Connection conn, List<Friends> friendsList){
		for(int i = 0; i < friendsList.size(); i++){
			addFriends(conn, friendsList.get(i));
		}
	}
	
	/**
	 * Makes a query to the person table 
	 * with given columns and conditions
	 * 
	 * @param conn
	 * @param columns: columns to return
	 * @param whereClauses: conditions to limit query by
	 * @return
	 */
	public static ResultSet queryFriendsTable(Connection conn,
			                                 ArrayList<String> columns,
			                                 ArrayList<String> whereClauses){
		return queryCurrentTable(conn, TABLE_NAME, columns, whereClauses);
	}
	

	
	/**
	 * Queries and print the table
	 * @param conn
	 */
	public static void printFriendsTable(Connection conn){
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()){
				System.out.printf("Friends %d is friends with %d\n",
						          result.getInt(1),
						          result.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
