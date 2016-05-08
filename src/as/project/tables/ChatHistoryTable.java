package as.project.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import as.project.objects.ChatHistory;
import as.project.objects.Friends;

public class ChatHistoryTable extends TableBase {
	
	public static final String SENDER_COLUMN = "SENDER";
	public static final String RECEIVER_COLUMN = "RECEIVER";
	public static final String TIMESTAMP_COLUMN = "TIMESTAMP";
	public static final String MESSAGE_COLUMN = "MESSAGE";
	public static final String ID_COLUMN = "ID";
	
	public static final String TABLE_NAME = "chat_history";
	
	
	/**
	 * Create the user table with the given attributes
	 * 
	 * @param conn: the database connection to work with
	 */
	public static void createChatHistoryTable(Connection conn) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
					 + ID_COLUMN + " INT PRIMARY KEY,"
				     + SENDER_COLUMN + " INT,"
				     + RECEIVER_COLUMN + " INT,"
				     + TIMESTAMP_COLUMN + " TIMESTAMP,"
				     + MESSAGE_COLUMN + " VARCHAR(255),"
				     + "FOREIGN KEY(" + SENDER_COLUMN + ", " + RECEIVER_COLUMN + ") REFERENCES " + FriendsTable.TABLE_NAME + "(" + FriendsTable.SENDER_COLUMN + ", " + FriendsTable.RECEIVER_COLUMN + ")"
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
	public static void addChatHistory(Connection conn, int chatId, int senderId, int receiverId, Timestamp timestamp, String message, boolean doLog){
		
		/**
		 * SQL insert statement
		 */
		String query = String.format("INSERT INTO " + TABLE_NAME + " VALUES(%d,%d,%d,\'%s\',\'%s\');",chatId, senderId, receiverId, timestamp.toString(), message);
		executeGeneralQuery(conn, query, doLog);
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user user to add
	 */
	public static void addChatHistory(Connection conn, ChatHistory chat, boolean doLog){
		addChatHistory(conn, chat.getChatHistoryId(), chat.getSenderId(), chat.getReceiverId(), chat.getTimestamp(), chat.getMessage(), doLog);
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user list of users to add
	 */
	public static void addChatHistoryList(Connection conn, List<ChatHistory> chatList, boolean doLog){
		for(int i = 0; i < chatList.size(); i++){
			addChatHistory(conn, chatList.get(i), doLog);
		}
	}
	
	public static void removeChatHistoryByFriends(Connection conn, int senderId, int receiverId, boolean doLog){

		String query = String.format("DELETE FROM " + TABLE_NAME + " WHERE " + SENDER_COLUMN +"=%d AND " + RECEIVER_COLUMN + "=%d", senderId, receiverId);
		executeGeneralQuery(conn, query, doLog);
	}
	
	public static void removeChatHistoryByFriends(Connection conn, Friends f, boolean doLog){
		removeChatHistoryByFriends(conn, f.getSenderId(), f.getReceiverId(), doLog);
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
			                                 ArrayList<String> whereClauses,
			                                 String orderBy){
		return queryCurrentTable(conn, TABLE_NAME, columns, whereClauses, orderBy);
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
	
	public static ArrayList<ChatHistory> getConversation(Connection conn, int uid1, int uid2){
		ArrayList<ChatHistory> result = new ArrayList<ChatHistory>();
		
		ArrayList<String> cols = new ArrayList<String>();
		ArrayList<String> where = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("((").append(SENDER_COLUMN).append("=").append(uid1).append(" AND ").append(RECEIVER_COLUMN).append("= ").append(uid2).append(")");
		sb.append(" OR (").append(RECEIVER_COLUMN).append("=").append(uid1).append(" AND ").append(SENDER_COLUMN).append("= ").append(uid2).append("))");
		where.add(sb.toString());
		String order = TIMESTAMP_COLUMN;
		
		ResultSet query = queryChatHistoryTable(conn, cols, where, order);
		
		try{
			while(query.next()){
				ChatHistory ch = new ChatHistory(query.getInt(ID_COLUMN),
						query.getInt(SENDER_COLUMN),
						query.getInt(RECEIVER_COLUMN),
						query.getTimestamp(TIMESTAMP_COLUMN),
						query.getString(MESSAGE_COLUMN));
				result.add(ch);
			}
		}
		catch (Exception e){
			System.out.print("Could not get conversation for users:" + uid1 + ", " + uid2);
			e.printStackTrace();
		}
		
		
		return result;
	}

}
