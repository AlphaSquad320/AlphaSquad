package as.project.tables ;

//sql imports
import java.sql.Connection ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Statement ;

//util imports
import java.util.ArrayList ;
import java.util.List ;

//project imports
import as.project.objects.Item ;
import as.project.objects.User;

public class ItemTable extends TableBase
{
	public static final String TABLE_NAME = "item";
	public static final String ID_COLUMN = "ITEM_ID";
	public static final String EFFECTS_COLUMN = "ADD_EFF";
	public static final String DESCRIPTION_COLUMN = "DESC";
	public static final String CONSUMABLE_COLUMN = "CONS";
	public static final String TYPE_COLUMN = "TYPE";
	public static final String BONUS_COLUMN = "BONUS";
	public static final String ITEM_CLASS_COLUMN = "ITEM_CLASS";
	
  /**
   * createItemTable method
   * create item table with given attributes
   *
   * @param conn - database connection
  **/
  public static void createItemTable( Connection conn ) throws SQLException
  {
    String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
      + ID_COLUMN + " INT PRIMARY KEY,"
      + EFFECTS_COLUMN + " VARCHAR(255),"
      + DESCRIPTION_COLUMN + " VARCHAR(255),"
      + CONSUMABLE_COLUMN + " BOOLEAN,"
      + TYPE_COLUMN + " VARCHAR(255),"
      + BONUS_COLUMN + " INT,"
      + ITEM_CLASS_COLUMN + " VARCHAR(255),"
      + ");" ;

    Statement state = conn.createStatement() ;
    state.execute( query ) ;
  }

  /**
   * addItem method
   * adds an item to the database
   *
   * @param conn - database connection
   * @param itemID - item ID number
   * @param addEff - Additional Effect text string
   * @param desc - Description text string
   * @param cons - Is item consumable
   * @param type - Item type (Armor, Weapon, etc.)
   * @param bonus - Item stat bonus value
   * @param itemClass - Specific subclass of type
  **/
  public static void addItem(
    Connection conn,
    int itemID,
    String addEff,
    String desc,
    boolean cons,
    String type,
    int bonus,
    String itemClass)
  {
    type = type.toLowerCase() ;

    String query = String.format( "INSERT INTO item "
      + "VALUES(%d,\'%s\',\'%s\',\'%s\',\'%s\',\'%d\',\'%s\');",
        itemID, addEff, desc, cons, type, bonus, itemClass ) ;

    runStatement( conn, query ) ;
  }

  /**
   * runStatement method
   * runs the given statement
   *
   * @param conn - database connection
   * @param query - statement to be run
  **/
  public static void runStatement( Connection conn, String query )
  {
    try
    {
      Statement state = conn.createStatement() ;
      state.execute( query ) ;
    }
    catch( SQLException e ){ e.printStackTrace() ; }
  }

  /**
   * addItem method
   * adds an item to the database
   *
   * @param conn - database connection
   * @param item - item to be added
  **/
  public static void addItem( Connection conn, Item item )
  {
    addItem(
      conn,
      item.getID(),
      item.getEffect(),
      item.getDescription(),
      item.isConsumable(),
      item.getType(),
      item.getBonus(),
      item.getItemClass() ) ;
  }

  /**
   * addItemList method
   * adds a list of items to the database
   *
   * @param conn - database connection
   * @param itemList - list of items to be added
  **/
  public static void addItemList( Connection conn, List<Item> itemList )
  {
    for( int i = 0 ; i < itemList.size() ; i++ )
    {
      addItem( conn, itemList.get( i ) ) ;
    }
  }

  /**
   * findByType method
   * finds all items of the given type
   *
   * @param conn - database connection
   * @param type - type to be found
  **/
  public static void findByType( Connection conn, String type )
  {
    type = type.toLowerCase() ;
    String query = String.format( "SELECT DESC, ADD_EFF, BONUS, ITEM_CLASS, CONS"
      + "FROM item"
      + "WHERE TYPE=\'%s\'", type ) ;

    runStatement( conn, query ) ;
  }
  
  public static ArrayList<Item> getItemsByCharacter(Connection conn, int characterId){
	  ArrayList<Item> result = new ArrayList<Item>();

	  ArrayList<String> cols = new ArrayList<String>();
	  ArrayList<String> where = new ArrayList<String>();
	  StringBuilder queryText = new StringBuilder();
	  queryText.append(ID_COLUMN).append(" IN (SELECT ");
	  queryText.append(CharacterItemTable.ITEM_ID_COLUMN).append(" FROM ").append(CharacterItemTable.TABLE_NAME).append(" WHERE ");
	  queryText.append(CharacterItemTable.CHARACTER_ID_COLUMN).append("=").append(characterId).append(")");
	  where.add(queryText.toString());

	  ResultSet sqlResults = queryCurrentTable(conn, TABLE_NAME, cols, where, null);
	  
	  try{
			while(sqlResults.next()){
				result.add(getItemFromResultSet(sqlResults));
			}
			
		} catch (Exception e){
			System.out.println("Could not get items for character:" + characterId);
			e.printStackTrace();
		}
	  
	  return result;
	  
  }
  


	private static Item getItemFromResultSet(ResultSet r) throws SQLException{
		return new Item(r.getInt(ID_COLUMN),
					r.getString(EFFECTS_COLUMN),
					r.getString(DESCRIPTION_COLUMN),
					r.getBoolean(CONSUMABLE_COLUMN),
					r.getString(TYPE_COLUMN),
					r.getInt(BONUS_COLUMN),
					r.getString(ITEM_CLASS_COLUMN));
	}
}
