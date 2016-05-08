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
				     + "FOREIGN KEY(" + SENDER_COLUMN + ") REFERENCES " + UserTable.TABLE_NAME + "(" + UserTable.USER_ID_COLUMN + "),"
				     + "FOREIGN KEY(" + RECEIVER_COLUMN + ") REFERENCES " + UserTable.TABLE_NAME + "(" + UserTable.USER_ID_COLUMN + ")"
				     + ");" ;
		executeGeneralQuery(conn, query);
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
			                     int receiverId,
			                     boolean doLog){
		
		String query = String.format("INSERT INTO " + TABLE_NAME + " " + "VALUES(%d,%d);",senderId, receiverId);
		executeGeneralQuery(conn, query, doLog);
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user user to add
	 */
	public static void addFriends(Connection conn, Friends friends, boolean doLog){
		addFriends(conn, friends.getSenderId(), friends.getReceiverId(), doLog);
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user list of users to add
	 */
	public static void addFriendsList(Connection conn, List<Friends> friendsList, boolean doLog){
		for(int i = 0; i < friendsList.size(); i++){
			addFriends(conn, friendsList.get(i), doLog);
		}
	}
	
	public static void removeFriends(Connection conn, int senderId, int receiverId, boolean doLog){
		ChatHistoryTable.removeChatHistoryByFriends(conn, senderId, receiverId, doLog);
		String query = String.format("DELETE FROM " + TABLE_NAME + " WHERE " + SENDER_COLUMN + "=%d AND " + RECEIVER_COLUMN + " =%d;",senderId, receiverId);
		executeGeneralQuery(conn, query, doLog);
	}
	
	public static void removeFriends(Connection conn, Friends friends, boolean doLog){
		removeFriends(conn, friends.getSenderId(), friends.getReceiverId(), doLog);
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
			                                 ArrayList<String> whereClauses,
			                                 String orderBy){
		return queryCurrentTable(conn, TABLE_NAME, columns, whereClauses, orderBy);
	}
	

	
	/**
	 * Queries and print the table
	 * @param conn
	 */
	public static void printFriendsTable(Connection conn){
		try {
			ResultSet result = queryCurrentTable(conn, TABLE_NAME, null, null, null);
			
			while(result.next()){
				System.out.printf("Friends %d is friends with %d\n",
						          result.getInt(1),
						          result.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<User> getFriendsOfUser(Connection conn, int uid){
		ArrayList<String> where = new ArrayList<String>();
		where.add(SENDER_COLUMN + "=" + uid);
		ResultSet query = queryFriendsTable(conn, new ArrayList<String>(), where, null);
		
		ArrayList<User> resultList = new ArrayList<User>();
		try{
			while(query.next()){
				User f = UserTable.getUserByUID(conn, query.getInt(RECEIVER_COLUMN));
				if(f != null){
					resultList.add(f);
				}
			}
		}
		catch (Exception e){
			System.out.println("failed getting friends of user: " + uid);
			e.printStackTrace();
		}
		return resultList;
	}

}
