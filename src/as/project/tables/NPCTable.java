package as.project.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import as.project.objects.NPC;

/**
 * @author Colin Thompson
 *
 */
public class NPCTable extends TableBase {
	
	public static final String NPC_ID_COLUMN = "NPC_ID";
	public static final String CHAR_ID_COLUMN = "CHARACTER_ID";
	public static final String NAME_COLUMN = "NAME";
	public static final String ISHOSTILE_COLUMN = "IS_HOSTILE";
	public static final String QUEST_COLUMN = "ASSOCIATED_QUEST";
	public static final String DESCRIPTION_COLUMN = "DESCRIPTION";
	
	public static final String TABLE_NAME = "npc";
	
	/**
	 * SQL statemtent to create a table named npc if one does not already exist
	 * @param conn - the connection to the database
	 */
	public static void createNPCTable(Connection conn){
		try{
			String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"("
						 + NPC_ID_COLUMN + " INT PRIMARY KEY,"
						 + CHAR_ID_COLUMN + " INT,"
						 + NAME_COLUMN +" VARCHAR(30),"
						 + ISHOSTILE_COLUMN + " BOOLEAN,"
						 + QUEST_COLUMN + " VARCHAR(30),"
						 + DESCRIPTION_COLUMN + " VARCHAR(255),"
						 + "FOREIGN KEY(" + CHAR_ID_COLUMN + ") REFERENCES " + CharacterTable.TABLE_NAME + "(" + CharacterTable.CHARACTER_ID_COLUMN + "),"
						 +");";
			
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a new tuple to the npc table
	 * @param conn - the connection to the database
	 * @param npcID - the ID of the npc
	 * @param charID - the character id of the NPC
	 * @param name - the name of the npc
	 * @param isHostile - whether or not the npc is hostile
	 * @param quest - the quest associated with the npc
	 * @param desc - the description of the npc
	 */
	public static void addNPC(Connection conn, int npcID, int charID, String name,
							  boolean isHostile, String quest, String desc){
		String query = String.format("INSERT INTO " + TABLE_NAME + " "
								   + "VALUES (%d,%d,\'%s\',%b,\'%s\',\'%s\');",
								   npcID, charID, name, isHostile, quest, desc);
		try{
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * adds tuples to the npc table based on a list of NPC objects
	 * @param conn - the connection to the database
	 * @param npcs - the list of NPC objects
	 * @return
	 */
	public static String createNPCInsertSQL(Connection conn, ArrayList<NPC> npcs){
		StringBuilder sb = new StringBuilder();
		
		sb.append("INSERT INTO " + TABLE_NAME +" (" + NPC_ID_COLUMN + "," 
				+ CHAR_ID_COLUMN + "," + NAME_COLUMN + "," + ISHOSTILE_COLUMN + ","
				+ QUEST_COLUMN + "," + DESCRIPTION_COLUMN + ") VALUES ");
		for(int i = 0; i < npcs.size(); i++){
			NPC n = npcs.get(i);
			sb.append(String.format("(%d,%d,\'%s\',%b,\'%s\',\'%s\')",
					   n.getNpcID(), n.getCharacterID(), n.getName(),
					   n.isHostile(), n.getAssocQuest(), n.getDescription()));
			if(i != npcs.size()-1){
				sb.append(",");
			}else{
				sb.append(";");
			}
		}
		return sb.toString();
	}
	
	/**
	 * Queries the npc table based on lists of columns and where clauses
	 * @param conn - the connection to the database
	 * @param columns - the columns to be queried
	 * @param whereClauses - the where clauses for the query
	 * @return a ResultSet of the result of the query
	 */
	public static ResultSet queryNPCTable(Connection conn,
            							  ArrayList<String> columns,
            							  ArrayList<String> whereClauses){
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT ");

		if(columns.isEmpty()){
			sb.append("* ");
		}else{
			for(int i = 0; i < columns.size(); i++){
				if(i != columns.size() - 1){
					sb.append(columns.get(i) + ", ");
				}else{
					sb.append(columns.get(i) + " ");
				}
			}
		}
		
		sb.append("FROM person ");

		if(!whereClauses.isEmpty()){
			sb.append("WHERE ");
			for(int i = 0; i < whereClauses.size(); i++){
				if(i != whereClauses.size() -1){
					sb.append(whereClauses.get(i) + " AND ");
				}else{
					sb.append(whereClauses.get(i));
				}
			}
		}

		sb.append(";");

		System.out.println("Query: " + sb.toString());
		try {
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sb.toString());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Prints out the npc table
	 * @param conn - the connection to the database
	 */
	public static void printNPCTable(Connection conn){
		String query = "SELECT * FROM npc;";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()){
				System.out.printf("NPC %d: %d %s %b %s %s\n",
						          result.getInt(1),
						          result.getInt(2),
						          result.getString(3),
						          result.getBoolean(4),
						          result.getString(5),
						          result.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
