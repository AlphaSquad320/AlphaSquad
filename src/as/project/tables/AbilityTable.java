package as.project.tables;
import as.project.objects.Ability;
import as.project.objects.Item;

import java.util.ArrayList;
import java.util.List;
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
	
	public static final String TABLE_NAME = "ability";
	public static final String ABILITY_ID_COLUMN = "abilityID";
	public static final String REQUIRED_LEVEL_COLUMN = "reqLevel";
	public static final String COST_COLUMN = "cost";
	public static final String BASE_DAMAGE_COLUMN = "baseDamage";
	public static final String RANGE_COLUMN = "range";
	public static final String RADIUS_COLUMN = "radius";
	public static final String DURATION_COLUMN = "duration";
	public static final String TYPE_COLUMN = "type";
	public static final String NAME_COLUMN = "name";
	public static final String DESCRIPTION_COLUMN = "description";
	public static final String ADDITIONAL_EFFECTS_COLUMN = "additionalEffects";
	
	/**
	 * Create a new ability table with Ability class attributes.
	 * 
	 * @param conn: Database connection.
	 */
	public static void createAbilityTable( Connection conn ) {
		try {
			String query = 
			"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
			+ ABILITY_ID_COLUMN + " INT," 
			+ REQUIRED_LEVEL_COLUMN + " INT,"
			+ COST_COLUMN + " INT,"
			+ BASE_DAMAGE_COLUMN + " INT,"
			+ RANGE_COLUMN + " NUMERIC(8,2),"
			+ RADIUS_COLUMN + " NUMERIC(8,2),"
			+ DURATION_COLUMN + " NUMERIC(8,2),"
			+ TYPE_COLUMN + " VARCHAR(40),"
			+ NAME_COLUMN + " VARCHAR(40),"
			+ DESCRIPTION_COLUMN + " VARCHAR(120),"
			+ ADDITIONAL_EFFECTS_COLUMN + " VARCHAR(255),"
			+ "PRIMARY KEY( " + ABILITY_ID_COLUMN + " )," 
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
			String name,
			String description,
			String additionalEffects,
			boolean doLog) {

		String query = String.format("INSERT INTO " + TABLE_NAME + " VALUES(%d,%d,%d,%d,%f,%f,%f,\'%s\',\'%s\',\'%s\',\'%s\');"
				,abilityID
				,reqLevel
				,cost
				,baseDamage
				,range
				,radius
				,duration
				,type
				,name
				,description
				,additionalEffects);
		
		executeGeneralQuery(conn, query, doLog);
	}

	/**
	 * Adds multiple abilities to the table.
	 * @param conn
	 * @param abilities
	 */
	public static void addAbilities( Connection conn, List<Ability> abilities, boolean doLog ) {
		for (Ability a : abilities) {
			addAbility( conn, a, doLog );
		}
	}
	
	/**
	 * Adds a single ability to the table.
	 * @param conn
	 * @param a - Ability instance
	 */
	public static void addAbility( Connection conn, Ability a, boolean doLog) {
		addAbility( conn,
				a.getID(),
				a.getReqLevel(),
				a.getCost(),
				a.getBaseDamage(),
				a.getRange(),
				a.getRadius(),
				a.getDuration(),
				a.getType(),
				a.getName(),
				a.getDescription(),
				a.getAdditionalEffects(),
				doLog);
	}
	
	public static void printAbilityTable(Connection conn) {
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()) {
				System.out.printf(
						"Ability %d: \n  "
						+ REQUIRED_LEVEL_COLUMN + ": %d \n  "
						+ COST_COLUMN + ": %d \n  "
						+ BASE_DAMAGE_COLUMN + ": %d \n  "
						+ RANGE_COLUMN + ": %f \n  " 
						+ RADIUS_COLUMN + ": %f \n  " 
						+ DURATION_COLUMN + ": %f \n  " 
						+ TYPE_COLUMN + ": %s \n  " 
						+ DESCRIPTION_COLUMN + ": %s \n  " 
						+ ADDITIONAL_EFFECTS_COLUMN + ": %s \n  "
						+ "\n", 
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
	

	  
	  public static ArrayList<Ability> getAbilitiesByCharacter(Connection conn, int characterId){
		  ArrayList<Ability> result = new ArrayList<Ability>();

		  ArrayList<String> cols = new ArrayList<String>();
		  ArrayList<String> where = new ArrayList<String>();
		  StringBuilder queryText = new StringBuilder();
		  queryText.append(ABILITY_ID_COLUMN).append(" IN (SELECT ");
		  queryText.append(CharacterAbilityTable.ABILITY_ID_COLUMN).append(" FROM ").append(CharacterAbilityTable.TABLE_NAME).append(" WHERE ");
		  queryText.append(CharacterAbilityTable.CHARACTER_ID_COLUMN).append("=").append(characterId).append(")");
		  where.add(queryText.toString());

		  ResultSet sqlResults = queryCurrentTable(conn, TABLE_NAME, cols, where, null);
		  
		  try{
				while(sqlResults.next()){
					result.add(getAbilityFromResultSet(sqlResults));
				}
				
			} catch (Exception e){
				System.out.println("Could not get items for character:" + characterId);
				e.printStackTrace();
			}
		  
		  return result;
		  
	  }
	  
	  public static ArrayList<Ability> getItemsByString(Connection conn, String str){
		  ArrayList<Ability> result = new ArrayList<Ability>();
		  
		  if(str != null){
			  ArrayList<String> cols = new ArrayList<String>();
			  ArrayList<String> where = new ArrayList<String>();
			  StringBuilder queryText = new StringBuilder();
			  queryText.append("UPPER(").append(DESCRIPTION_COLUMN).append(") LIKE UPPER('%").append(str).append("%')");
			  where.add(queryText.toString());
		
			  ResultSet sqlResults = queryCurrentTable(conn, TABLE_NAME, cols, where, null);
			  
			  try{
					while(sqlResults.next()){
						result.add(getAbilityFromResultSet(sqlResults));
					}
					
				} catch (Exception e){
					System.out.println("Could not get abilities by string:" + str);
					e.printStackTrace();
				}
		  }
		  
		  return result;
	  }
	  


		private static Ability getAbilityFromResultSet(ResultSet r) throws SQLException{
			return new Ability(r.getInt(ABILITY_ID_COLUMN),
						r.getInt(REQUIRED_LEVEL_COLUMN),
						r.getInt(COST_COLUMN),
						r.getInt(BASE_DAMAGE_COLUMN),
						r.getFloat(RANGE_COLUMN),
						r.getFloat(RADIUS_COLUMN),
						r.getFloat(DURATION_COLUMN),
						r.getString(TYPE_COLUMN),
						r.getString(NAME_COLUMN),
						r.getString(DESCRIPTION_COLUMN),
						r.getString(ADDITIONAL_EFFECTS_COLUMN));
		}
}
