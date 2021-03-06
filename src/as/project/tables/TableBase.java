package as.project.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableBase {
	
	/**
	 * Makes a query to the specified table 
	 * with given columns and conditions
	 * 
	 * @param conn
	 * @param tableName table to query
	 * @param columns: columns to return
	 * @param whereClauses: conditions to limit query by
	 * @return
	 */
	protected static ResultSet queryCurrentTable(Connection conn,
											 String tableName,
			                                 ArrayList<String> columns,
			                                 ArrayList<String> whereClauses,
			                                 String orderBy){
		StringBuilder sb = new StringBuilder();
		
		/**
		 * Start the select query
		 */
		sb.append("SELECT ");
		
		/**
		 * If we gave no columns just give them all to us
		 * 
		 * other wise add the columns to the query
		 * adding a comma top separate
		 */
		if(columns.isEmpty()){
			sb.append("* ");
		}
		else{
			for(int i = 0; i < columns.size(); i++){
				if(i != columns.size() - 1){
				    sb.append(columns.get(i) + ", ");
				}
				else{
					sb.append(columns.get(i) + " ");
				}
			}
		}
		
		/**
		 * Tells it which table to get the data from
		 */
		sb.append("FROM " + tableName + " ");
		
		/**
		 * If we gave it conditions append them
		 * place an AND between them
		 */
		if(!whereClauses.isEmpty()){
			sb.append("WHERE ");
			for(int i = 0; i < whereClauses.size(); i++){
				if(i != whereClauses.size() -1){
					sb.append(whereClauses.get(i) + " AND ");
				}
				else{
					sb.append(whereClauses.get(i));
				}
			}
		}
		
		if(orderBy != null && orderBy.length() != 0){
			sb.append(" ORDER BY ").append(orderBy);
		}
		
		/**
		 * close with semi-colon
		 */
		sb.append(";");
		
		//Print it out to verify it made it right
		System.out.println("Query(Results): " + sb.toString());
		try {
			/**
			 * Execute the query and return the result set
			 */
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sb.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Runs a query that does not return any output
	 * used for creates/updates/alters/drops/etc
	 * 
	 * @param conn
	 * @param query statement to execute
	 */
	protected static void executeGeneralQuery(Connection conn, String command){
		executeGeneralQuery(conn, command, true);
	}
	
	protected static void executeGeneralQuery(Connection conn, String command, boolean doLog){
		//Print it out to verify it made it right
		if(doLog){
			System.out.println("Query(General): " + command);
		}
		try {
			/**
			 * Execute the query
			 */
			Statement stmt = conn.createStatement();
			stmt.execute(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
