package as.project.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import as.project.objects.ChatHistory;
import as.project.objects.GameCharacter;
import as.project.objects.User;

public class CharacterTable extends TableBase {

	public static final String CHARACTER_ID_COLUMN = "CHARACTER_ID";
	public static final String USER_ID_COLUMN = "USER_ID";
	public static final String STR_COLUMN = "STR";
	public static final String DEX_COLUMN = "DEX";
	public static final String CHR_COLUMN = "CHR";
	public static final String INT_COLUMN = "INTEL";
	public static final String WIS_COLUMN = "WIS";
	public static final String CON_COLUMN = "CON";
	public static final String MONEY_COLUMN = "MONEY";
	public static final String HP_COLUMN = "HP";
	public static final String MP_COLUMN = "MP";
	public static final String CUR_HP_COLUMN = "CUR_HP";
	public static final String CUR_MP_COLUMN = "CUR_MP";
	public static final String LEVEL_COLUMN = "LEVEL";
	public static final String CUR_XP_COLUMN = "CUR_XP";
	public static final String CLASS_COLUMN = "CLASS";
	public static final String ALIGNMENT_COLUMN = "ALIGNMENT";
	public static final String CHARACTER_NAME_COLUMN = "CHARACTER_NAME";
	public static final String RACE_COLUMN = "RACE";
	
	public static final String TABLE_NAME = "character";
	
	
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
		
		executeGeneralQuery(conn, query);
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
			                     String race,
			                     boolean doLog){
		
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
		executeGeneralQuery(conn, query, doLog);
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user user to add
	 */
	public static void addCharacter(Connection conn, GameCharacter character, boolean doLog){
		addCharacter(conn, character.getCharacterId(), character.getUserId(), character.getStr(), character.getDex(), character.getChr(), character.getIntel(), character.getWis(), character.getCon(), 
               	character.getMoney(), character.getHp(), character.getMp(), character.getCurHp(), character.getCurMp(), character.getLevel(), character.getCurXp(), character.getCharacterClass(), character.getAlignment(), character.getCharacterName(), character.getRace(), doLog);
	}
	
	/**
	 * 
	 * @param conn connection string
	 * @param user list of users to add
	 */
	public static void addCharacterList(Connection conn, List<GameCharacter> characterList, boolean doLog){
		for(int i = 0; i < characterList.size(); i++){
			addCharacter(conn, characterList.get(i), doLog);
		}
	}
	
	public static void removeCharacterSafe(Connection conn, int cId, int uId, boolean doLog ){
		int validId = -1;
		//first, ensure that the user is removing their own character
		ArrayList<String> cols = new ArrayList<String>();
		cols.add(CHARACTER_ID_COLUMN);
		ArrayList<String> where = new ArrayList<String>();
		where.add(USER_ID_COLUMN + "=" + uId);
		where.add(CHARACTER_ID_COLUMN + "=" + cId);
		ResultSet check = queryCharacterTable(conn, cols, where, null);
		try{
			if(check.next()){
				validId = check.getInt(CHARACTER_ID_COLUMN);
			}
		}catch (Exception e){
			System.out.println("Could not check character:" + cId + " for user:" + uId);
			e.printStackTrace();
		}
		
		//given that the user is actually the owner, remove the character (and other related values
		if(validId != -1){
			CharacterItemTable.takeCharactersItems(conn, cId, doLog);
			CharacterAbilityTable.removeCharactersAbilities(conn, cId, doLog);
		    String query = String.format("DELETE FROM %s WHERE %s = %d;",TABLE_NAME , CHARACTER_ID_COLUMN, cId ) ;
		    executeGeneralQuery(conn, query, doLog);
		}
		
	}
	
	public static void removeCharacterSafe(Connection conn, GameCharacter c, User u, boolean doLog){
		removeCharacterSafe(conn, c.getCharacterId(), u.getUserId(), doLog);
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
		try {
			ResultSet result = queryCharacterTable(conn, null, null, null);
			
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
