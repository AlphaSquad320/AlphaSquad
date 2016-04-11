package as.project.tables;
import as.project.objects.Ability;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Martin
 * Class made to create and update the ability table.
 */
public class AbilityTable extends TableBase{
	
	public static String tableName = "ability";
	public static String abilityID = "abilityID";
	public static String reqLevel = "reqLevel";
	public static String cost = "cost";
	public static String baseDamage = "baseDamage";
	public static String range = "range";
	public static String radius = "radius";
	public static String duration = "duration";
	public static String type = "type";
	public static String description = "description";
	public static String additionalEffects = "additionalEffects";
	
	/**
	 * Create a new ability table with Ability class attributes.
	 * 
	 * @param conn: Database connection.
	 */
	public static void createAbilityTable( Connection conn ) {
		try {
			String query = 
			"CREATE TABLE IF NOT EXISTS " + tableName + " ("
			+ abilityID + " INT PRIMARY KEY," 
			+ reqLevel + " INT,"
			+ cost + " INT,"
			+ baseDamage + " INT,"
			+ range + " NUMERIC(8,2),"
			+ radius + " NUMERIC(8,2),"
			+ duration + " NUMERIC(8,2),"
			+ type + " varchar(20),"
			+ description + " varchar(40),"
			+ additionalEffects + " varchar(200),"
			+ ");";
			
			// Create query and execute it.
			Statement stm = conn.createStatement();
			stm.execute(query);
					
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * Adds a single ability to the table.
	 * @param conn
	 * @param abilityID
	 * @param reqLevel
	 * @param cost
	 * @param baseDamage
	 * @param range
	 * @param radius
	 * @param duration
	 * @param type
	 * @param description
	 * @param additionalEffects
	 */
	public static void addAbility( 
			Connection conn,
			int abilityID,
			int reqLevel,
			int cost,
			int baseDamage,
			float range,
			float radius,
			float duration,
			String type,
			String description,
			String additionalEffects) {
		
		try {
			PreparedStatement pStmt = conn.prepareStatement(
					"INSERT INTO ability VALUES(?,?,?,?,?,?,?,?,?,?);");
			pStmt.setInt(1, abilityID);
			pStmt.setInt(2, reqLevel);
			pStmt.setInt(3, cost);
			pStmt.setInt(4, baseDamage);
			pStmt.setFloat(5, range);
			pStmt.setFloat(6, radius);
			pStmt.setFloat(7, duration);
			pStmt.setString(8, type);
			pStmt.setString(9, description);
			pStmt.setString(10, additionalEffects);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a single ability to the table.
	 * @param conn
	 * @param a - Ability instance
	 */
	public static void addAbility( Connection conn, Ability a) {
		addAbility( conn,
				a.getID(),
				a.getReqLevel(),
				a.getCost(),
				a.getBaseDamage(),
				a.getRange(),
				a.getRadius(),
				a.getDuration(),
				a.getType(),
				a.getDescription(),
				a.getAdditionalEffects());
	}
	
	public static void printAbilityTable(Connection conn) {
		String query = "SELECT * FROM " + tableName + ";";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) {
				System.out.printf(
						"Ability %d: \n"
						+ reqLevel + ": %d \n"
						+ cost + ": %d \n"
						+ baseDamage + ": %d \n"
						+ range + ": %f \n" 
						+ radius + ": %f \n" 
						+ duration + ": %f \n" 
						+ type + ": %s \n" 
						+ description + ": %s \n" 
						+ type + ": %s \n" 
						, 
						result.getInt(1),
						result.getInt(2),
						result.getInt(3),
						result.getInt(4),
						result.getFloat(5),
						result.getFloat(6),
						result.getFloat(7),
						result.getString(8),
						result.getString(9),
						result.getString(10)
						);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
