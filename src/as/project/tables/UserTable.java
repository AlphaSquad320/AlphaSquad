package as.project.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import as.project.objects.User;

public class UserTable extends TableBase {
	
	/**
	 * Create the user table with the given attributes
	 * 
	 * @param conn: the database connection to work with
	 */
	public static void createUserTable(Connection conn) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS user("
				     + "USER_ID INT PRIMARY KEY,"
				     + "FIRST_NAME VARCHAR(255),"
				     + "LAST_NAME VARCHAR(255),"
				     + "DISPLAY_NAME VARCHAR(255),"
				     + "EMAIL_ADDRESS VARCHAR(255),"
				     + "PASSWORD VARCHAR(255),"
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
	public static void addUser(Connection conn,
			                     int id,
			                     String fName,
			                     String lName,
			                     String dName,
			                     String email,
			                     String pw){
		
		/**
		 * SQL insert statement
		 */
		String query = String.format("INSERT INTO user "
				                   + "VALUES(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');",
				                     id, fName, lName, dName, email, pw);
		try {
			/**
			 * create and execute the query
			 */
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user user to add
	 */
	public static void addUser(Connection conn, User user){
		addUser(conn, user.getUserId(), user.getFirstName(), user.getLastName(), user.getDisplayName(), user.getEmail(), user.getPassword());
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user list of users to add
	 */
	public static void addUserList(Connection conn, List<User> userList){
		for(int i = 0; i < userList.size(); i++){
			addUser(conn, userList.get(i));
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
	public static ResultSet queryPersonTable(Connection conn,
			                                 ArrayList<String> columns,
			                                 ArrayList<String> whereClauses){
		return queryCurrentTable(conn, "user", columns, whereClauses);
	}
	
	

}
