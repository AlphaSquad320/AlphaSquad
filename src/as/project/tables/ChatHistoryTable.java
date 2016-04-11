package as.project.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import as.project.objects.ChatHistory;

public class ChatHistoryTable extends TableBase {
	
	public static final String SENDER_COLUMN = "SENDER";
	public static final String RECEIVER_COLUMN = "RECEIVER";
	public static final String TIMESTAMP_COLUMN = "TIMESTAMP";
	public static final String MESSAGE_COLUMN = "MESSAGE";
	
	public static final String TABLE_NAME = "chat_history";
	
	
	/**
	 * Create the user table with the given attributes
	 * 
	 * @param conn: the database connection to work with
	 */
	public static void createChatHistoryTable(Connection conn) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
				     + SENDER_COLUMN + " INT,"
				     + RECEIVER_COLUMN + " INT,"
				     + TIMESTAMP_COLUMN + " TIMESTAMP,"
				     + MESSAGE_COLUMN + " VARCHAR(255),"
				     + "PRIMARY KEY(" + SENDER_COLUMN +", " + RECEIVER_COLUMN + ", " + TIMESTAMP_COLUMN + "),"
				     + "FOREIGN KEY(" + SENDER_COLUMN + ") REFERENCES " + FriendsTable.TABLE_NAME + "(" + FriendsTable.SENDER_COLUMN + "),"
				     + "FOREIGN KEY(" + RECEIVER_COLUMN + ") REFERENCES " + FriendsTable.TABLE_NAME + "(" + FriendsTable.RECEIVER_COLUMN + ")"
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
	public static void addChatHistory(Connection conn, int senderId, int receiverId, Timestamp timestamp, String message){
		
		/**
		 * SQL insert statement
		 */
		String query = String.format("INSERT INTO " + TABLE_NAME + " " + "VALUES(%d,%d,\'%s\',\'%s\');",senderId, receiverId, timestamp.toString(), message);
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
	public static void addChatHistory(Connection conn, ChatHistory chat){
		addChatHistory(conn, chat.getSenderId(), chat.getReceiverId(), chat.getTimestamp(), chat.getMessage());
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user list of users to add
	 */
	public static void addChatHistoryList(Connection conn, List<ChatHistory> chatList){
		for(int i = 0; i < chatList.size(); i++){
			addChatHistory(conn, chatList.get(i));
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
	public static ResultSet queryChatHistoryTable(Connection conn,
			                                 ArrayList<String> columns,
			                                 ArrayList<String> whereClauses){
		return queryCurrentTable(conn, TABLE_NAME, columns, whereClauses);
	}
	

	
	/**
	 * Queries and print the table
	 * @param conn
	 */
	public static void printChatHistoryTable(Connection conn){
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()){
				System.out.printf("Chat from %d to %d at %s: \'%s\'\n",
						          result.getInt(1),
						          result.getInt(2),
						          result.getTimestamp(3).toString(),
						          result.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
