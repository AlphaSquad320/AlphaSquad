package as.project.tables;

import java.util.List;
import java.util.

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
			+ characterID + " INT PRIMARY KEY," 
			+ abilityID + " INT PRIMARY KEY," 
			+ "PRIMARY KEY( " + characterID + ", " + abilityID + " )," 
			+ "FOREIGN KEY( " + characterID + ", " + abilityID + " )," 
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
	public static void addCharacterAbility( Connection conn, int abID, int chID ) {
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
	 * @param a  - Ability
	 * @param gc - Character
	 */
	public static void addCharacterAbility( Connection conn, Ability a, GameCharacter gc) {
		addCharacterAbility( a.getID(), ch.getID() );
	}
	
}
