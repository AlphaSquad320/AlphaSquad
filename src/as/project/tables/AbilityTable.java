package as.project.tables;
import as.project.objects.Ability;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

/**
 * @author Martin
 * Class made to create and update the ability table.
 */
public class AbilityTable extends TableBase{
	
	/**
	 * Create a new ability table with Ability class attributes.
	 * 
	 * @param conn: Database connection.
	 */
	public static void createAbilityTable( Connection conn ) {
		try {
			String query = 
			"CREATE TABLE IF NOT EXISTS ability ("
			+ "abilityID INT PRIMARY KEY," 
			+ "reqLevel INT,"
			+ "cost INT,"
			+ "damage INT,"
			+ "range NUMERIC(8,2)"
			+ "radius NUMERIC(8,2),"
			+ "duration NUMERIC(8,2),"
			+ "type varchar(20),"
			+ "description varchar(40),"
			+ "additionalEffects varchar(200),"
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
}
