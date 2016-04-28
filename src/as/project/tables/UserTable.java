package as.project.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import as.project.objects.User;

public class UserTable extends TableBase {
	
	public static final String FIRST_NAME_COLUMN = "FIRST_NAME";
	public static final String USER_ID_COLUMN = "USER_ID";
	public static final String LAST_NAME_COLUMN = "LAST_NAME";
	public static final String DISPLAY_NAME_COLUMN = "DISPLAY_NAME";
	public static final String EMAIL_ADDRESS_COLUMN = "EMAIL_ADDRESS";
	public static final String PASSWORD_COLUMN = "PASSWORD";
	
	public static final String TABLE_NAME = "user";
	
	
	/**
	 * Create the user table with the given attributes
	 * 
	 * @param conn: the database connection to work with
	 */
	public static void createUserTable(Connection conn) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
				     + USER_ID_COLUMN + " INT PRIMARY KEY,"
				     + FIRST_NAME_COLUMN + " VARCHAR(255),"
				     + LAST_NAME_COLUMN + " VARCHAR(255),"
				     + DISPLAY_NAME_COLUMN + " VARCHAR(255),"
				     + EMAIL_ADDRESS_COLUMN + " VARCHAR(255),"
				     + PASSWORD_COLUMN + " VARCHAR(255),"
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
		String query = String.format("INSERT INTO " + TABLE_NAME + " "
				                   + "VALUES(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');",
				                     id, fName, lName, dName, email, pw);
		try {
			/**
			 * create and execute the query
			 */
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			System.out.println("Issue adding user: " + e);
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
	public static ResultSet queryUserTable(Connection conn,
			                                 ArrayList<String> columns,
			                                 ArrayList<String> whereClauses){
		return queryCurrentTable(conn, TABLE_NAME, columns, whereClauses);
	}
	

	
	/**
	 * Queries and print the table
	 * @param conn
	 */
	public static void printUserTable(Connection conn){
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()){
				System.out.printf("User %d: %s %s %s %s %s\n",
						          result.getInt(1),
						          result.getString(2),
						          result.getString(3),
						          result.getString(4),
						          result.getString(5),
						          result.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Determines if the user login provided is valid
	 * @param username
	 * @param password
	 * @return returns null if invalid, or the user object if valid
	 */
	public static User authenticateUser(Connection conn, String username, String password){
		User result = null;

		ArrayList<String> whereClauses = new ArrayList<String>();
		whereClauses.add(EMAIL_ADDRESS_COLUMN + " = '" + username + "'");
		whereClauses.add(PASSWORD_COLUMN + " = '" + password + "'");

		ResultSet sqlResults = queryUserTable(conn, new ArrayList<String>(), whereClauses);
		
		try{
			if(sqlResults.next()){
				result = getUserFromResultSet(sqlResults);
			}
			
		} catch (Exception e){
			System.out.println("Authenticating the user: " + username + " failed:");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static User getUserByUID(Connection conn, int uid){
		User result = null;

		ArrayList<String> whereClauses = new ArrayList<String>();
		whereClauses.add(USER_ID_COLUMN + "=" + uid + "");

		ResultSet sqlResults = queryUserTable(conn, new ArrayList<String>(), whereClauses);
		try{
			if(sqlResults.next()){
				result = getUserFromResultSet(sqlResults);
			}
			
		} catch (Exception e){
			System.out.println("Could not get user:" + uid);
			e.printStackTrace();
		}
		
		return result;
	}

	private static User getUserFromResultSet(ResultSet r) throws SQLException{
		return new User(r.getInt(USER_ID_COLUMN),
					r.getString(FIRST_NAME_COLUMN),
					r.getString(LAST_NAME_COLUMN),
					r.getString(DISPLAY_NAME_COLUMN),
					r.getString(EMAIL_ADDRESS_COLUMN),
					r.getString(PASSWORD_COLUMN));
	}
	
	

}
