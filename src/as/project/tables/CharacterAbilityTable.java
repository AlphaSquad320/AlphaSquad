package as.project.tables;

import java.util.List;

import as.project.objects.Ability;
import as.project.objects.GameCharacter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Martin
 * Class made to create and update the ability table for a particular character.
 */
public class CharacterAbilityTable extends TableBase {
	
	public static String tableName = "characterAbility";
	public static String characterForeignTableName = "character";
	public static String abilityForeignTableName = "ability";
	public static String characterID = "characterID";
	public static String abilityID = "abilityID";
	
	/**
	 * Create the character ability table.
	 * @param conn
	 */
	public static void createCharacterAbilityTable( Connection conn ) {
		try {
			String query = 
			"CREATE TABLE IF NOT EXISTS " + tableName + "("
			+ characterID + " INT," 
			+ abilityID + " INT," 
			+ "PRIMARY KEY( " + characterID + ", " + abilityID + " )," 
			+ "FOREIGN KEY( " + characterID + ") REFERENCES " + characterForeignTableName + ","
			+ "FOREIGN KEY( " + abilityID + ") REFERENCES " + abilityForeignTableName + ","
			+ ");";
			
			// Create query and execute it.
			Statement stm = conn.createStatement();
			stm.execute(query);
					
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	/**
	 * Add a character ability to the table.
	 * @param conn
	 * @param abID - PK of ability
	 * @param chID - PK of character
	 */
	public static void addCharacterAbility( Connection conn, int chID, int abID ) {
		try {
			PreparedStatement pStmt = conn.prepareStatement(
					"INSERT INTO " + tableName + " VALUES(?,?);");
			pStmt.setInt(1, chID);
			pStmt.setInt(2, abID);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a character ability to the table
	 * @param conn
	 * @param gc - Character
	 * @param a  - Ability
	 */
	public static void addCharacterAbility( Connection conn, GameCharacter gc, Ability a ) {
		addCharacterAbility( conn, gc.getCharacterId(), a.getID() );
	}
	
	/**
	 * Prints the table. Just prints the PK.
	 * @param conn
	 */
	public static void printCharacterAbilityTable(Connection conn) {
		String query = "SELECT * FROM " + tableName + ";";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) {
				System.out.printf(
						"CharacterAbility: \n  "
						+ characterID + ": %d \n  "
						+ abilityID + ": %d \n  "
						+ "\n",
						result.getInt(1),
						result.getInt(2)
						);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}

