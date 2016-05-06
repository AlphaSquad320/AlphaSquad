package as.project.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import as.project.Main;
import as.project.objects.ChatHistory;
import as.project.objects.GameCharacter;

public class CharacterTable extends TableBase {

	public static String CHARACTER_ID_COLUMN = "CHARACTER_ID";
	public static String USER_ID_COLUMN = "USER_ID";
	public static String STR_COLUMN = "STR";
	public static String DEX_COLUMN = "DEX";
	public static String CHR_COLUMN = "CHR";
	public static String INT_COLUMN = "INTEL";
	public static String WIS_COLUMN = "WIS";
	public static String CON_COLUMN = "CON";
	public static String MONEY_COLUMN = "MONEY";
	public static String HP_COLUMN = "HP";
	public static String MP_COLUMN = "MP";
	public static String CUR_HP_COLUMN = "CUR_HP";
	public static String CUR_MP_COLUMN = "CUR_MP";
	public static String LEVEL_COLUMN = "LEVEL";
	public static String CUR_XP_COLUMN = "CUR_XP";
	public static String CLASS_COLUMN = "CLASS";
	public static String ALIGNMENT_COLUMN = "ALIGNMENT";
	public static String CHARACTER_NAME_COLUMN = "CHARACTER_NAME";
	public static String RACE_COLUMN = "RACE";
	
	public static String TABLE_NAME = "character";
	
	
	/**
	 * Create the user table with the given attributes
	 * 
	 * @param conn: the database connection to work with
	 */
	public static void createCharacterTable(Connection conn) throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
			     	 + CHARACTER_ID_COLUMN + " INT PRIMARY KEY,"
				     + USER_ID_COLUMN + " INT,"
				     + STR_COLUMN + " INT,"
				     + DEX_COLUMN + " INT,"
				     + CHR_COLUMN + " INT,"
				     + INT_COLUMN + " INT,"
				     + WIS_COLUMN + " INT,"
				     + CON_COLUMN + " INT,"
				     + MONEY_COLUMN + " INT,"
				     + HP_COLUMN + " INT,"
				     + MP_COLUMN + " INT,"
				     + CUR_HP_COLUMN + " INT,"
				     + CUR_MP_COLUMN + " INT,"
				     + LEVEL_COLUMN + " INT,"
				     + CUR_XP_COLUMN + " INT,"
				     + CLASS_COLUMN + " VARCHAR(255),"
				     + ALIGNMENT_COLUMN + " VARCHAR(255),"
				     + CHARACTER_NAME_COLUMN + " VARCHAR(255),"
				     + RACE_COLUMN + " VARCHAR(255),"
				     + ");" ;
		
		/**
		 * Create a query and execute
		 */
		Statement stmt = conn.createStatement();
		stmt.execute(query);
	}
	
	/**
	 * Adds a single character to the database
	 * 
	 * @param conn connection string
	 * @param id userId
	 */
	public static void addCharacter(Connection conn,
            					 int characterId,
			                     int userId,
			                     int str,
			                     int dex,
			                     int chr,
			                     int intel,
			                     int wis,
			                     int con,
			                     int money,
			                     int hp,
			                     int mp,
			                     int curHp,
			                     int curMp,
			                     int level,
			                     int curXp,
			                     String cClass,
			                     String alignment,
			                     String cName,
			                     String race){
		
		/**
		 * SQL insert statement
		 */
		String query = String.format("INSERT INTO " + TABLE_NAME + " "
                                   + "(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) "
				                   + "VALUES(%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,\'%s\',\'%s\',\'%s\',\'%s\');",
				                    CHARACTER_ID_COLUMN, USER_ID_COLUMN, STR_COLUMN, DEX_COLUMN, CHR_COLUMN, INT_COLUMN, WIS_COLUMN, CON_COLUMN,
				                    MONEY_COLUMN, HP_COLUMN, MP_COLUMN, CUR_HP_COLUMN, CUR_MP_COLUMN, LEVEL_COLUMN, CUR_XP_COLUMN, CLASS_COLUMN, ALIGNMENT_COLUMN, CHARACTER_NAME_COLUMN, RACE_COLUMN,
				                   	characterId, userId, str, dex, chr, intel, wis, con, 
				                   	money, hp, mp, curHp, curMp, level, curXp, cClass, alignment, cName, race);
		try {
			/**
			 * create and execute the query
			 */
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			System.out.println("Issue adding character: " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user user to add
	 */
	public static void addCharacter(Connection conn, GameCharacter character){
		System.out.println(""+character.getCharacterId() + character.getUserId() + character.getStr() + character.getDex() + character.getChr() + character.getIntel() + character.getWis() + character.getCon() + 
               	character.getMoney() + character.getHp() + character.getMp() + character.getCurHp() + character.getCurMp() + character.getLevel() + character.getCurXp() + character.getCharacterClass() + character.getAlignment() + character.getCharacterName() + character.getRace());
		addCharacter(conn, character.getCharacterId(), character.getUserId(), character.getStr(), character.getDex(), character.getChr(), character.getIntel(), character.getWis(), character.getCon(), 
               	character.getMoney(), character.getHp(), character.getMp(), character.getCurHp(), character.getCurMp(), character.getLevel(), character.getCurXp(), character.getCharacterClass(), character.getAlignment(), character.getCharacterName(), character.getRace());
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user list of users to add
	 */
	public static void addCharacterList(Connection conn, List<GameCharacter> characterList){
		for(int i = 0; i < characterList.size(); i++){
			addCharacter(conn, characterList.get(i));
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
	public static ResultSet queryCharacterTable(Connection conn,
			                                 ArrayList<String> columns,
			                                 ArrayList<String> whereClauses,
			                                 String orderBy){
		return queryCurrentTable(conn, TABLE_NAME, columns, whereClauses, orderBy);
	}
	

	
	/**
	 * Queries and print the table
	 * @param conn
	 */
	public static void printCharacterTable(Connection conn){
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next()){
				System.out.printf("%s %d: \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %d \n%s %s \n%s %s \n%s %s \n%s %s\n\n",
								  CHARACTER_ID_COLUMN,
						          result.getInt(1),
						          USER_ID_COLUMN, 
						          result.getInt(2),
						          STR_COLUMN, 
						          result.getInt(3),
						          DEX_COLUMN,
						          result.getInt(4),
						          CHR_COLUMN, 
						          result.getInt(5),
						          INT_COLUMN,
						          result.getInt(6),
						          WIS_COLUMN,
						          result.getInt(7),
						          CON_COLUMN,
						          result.getInt(8),
						          MONEY_COLUMN,
						          result.getInt(9),
						          HP_COLUMN,
						          result.getInt(10),
						          MP_COLUMN,
						          result.getInt(11),
						          CUR_HP_COLUMN,
						          result.getInt(12),
						          CUR_MP_COLUMN, 
						          result.getInt(13),
						          LEVEL_COLUMN, 
						          result.getInt(14),
						          CUR_XP_COLUMN, 
						          result.getInt(15),
						          CLASS_COLUMN, 
						          result.getString(16),
						          ALIGNMENT_COLUMN, 
						          result.getString(17),
						          CHARACTER_NAME_COLUMN, 
						          result.getString(18),
						          RACE_COLUMN,
						          result.getString(19));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<GameCharacter> getUserCharacters(Connection conn, int uid){
		ArrayList<GameCharacter> result = new ArrayList<GameCharacter>();
		
		ArrayList<String> cols = new ArrayList<String>();
		ArrayList<String> where = new ArrayList<String>();
		where.add(USER_ID_COLUMN + "=" + uid);
		
		ResultSet query = queryCharacterTable(conn, cols, where, null);
		

		try{
			while(query.next()){
				result.add(getGameCharacterFromResultSet(query));
			}
		}
		catch (Exception e){
			System.out.print("Could not get characters for user:" + uid);
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public static ArrayList<GameCharacter> geNPCCharactersByString(Connection conn, String str){
		ArrayList<GameCharacter> result = new ArrayList<GameCharacter>();
		
		ArrayList<String> cols = new ArrayList<String>();
		ArrayList<String> where = new ArrayList<String>();
		where.add(USER_ID_COLUMN + "=" + UserTable.NPC_USER_ID);
		StringBuilder subQuery = new StringBuilder();
		subQuery.append("(SELECT ").append(NPCTable.CHAR_ID_COLUMN).append(" FROM ").append(NPCTable.TABLE_NAME).append(" WHERE UPPER(").append(NPCTable.DESCRIPTION_COLUMN).append(") like UPPER('%").append(str).append("%'))");
		StringBuilder orQuery = new StringBuilder();
		orQuery.append("(UPPER(").append(CHARACTER_NAME_COLUMN).append(") like UPPER('%").append(str).append("%')").append(" OR ");
		orQuery.append(USER_ID_COLUMN).append(" in ").append(subQuery.toString()).append(")");
		where.add(orQuery.toString());
		
		ResultSet query = queryCharacterTable(conn, cols, where, null);
		
		try{
			while(query.next()){
				result.add(getGameCharacterFromResultSet(query));
			}
		}
		catch (Exception e){
			System.out.print("Could not get npc characters for string:" + str);
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	private static GameCharacter getGameCharacterFromResultSet(ResultSet r) throws SQLException{
		return new GameCharacter(r.getInt(CHARACTER_ID_COLUMN),
				r.getInt(USER_ID_COLUMN),
				r.getInt(STR_COLUMN),
				r.getInt(DEX_COLUMN),
				r.getInt(CHR_COLUMN),
				r.getInt(INT_COLUMN),
				r.getInt(WIS_COLUMN),
				r.getInt(CON_COLUMN),
				r.getInt(MONEY_COLUMN),
				r.getInt(HP_COLUMN),
				r.getInt(MP_COLUMN),
				r.getInt(CUR_HP_COLUMN),
				r.getInt(CUR_MP_COLUMN),
				r.getInt(LEVEL_COLUMN),
				r.getInt(CUR_XP_COLUMN),
				r.getString(CLASS_COLUMN),
				r.getString(ALIGNMENT_COLUMN),
				r.getString(CHARACTER_NAME_COLUMN),
				r.getString(RACE_COLUMN));
	}
	
	

}
