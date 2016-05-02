package as.project.tables ;

//sql imports
import java.sql.Connection ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Statement ;

//util imports
import java.util.List ;

//project imports
import as.project.objects.GameCharacter ;
import as.project.objects.Item ;

public class CharacterItemTable extends TableBase
{
	public static final String TABLE_NAME = "characterItem";
	public static final String CHARACTER_ID_COLUMN = "CHARACTER_ID";
	public static final String ITEM_ID_COLUMN = "ITEM_ID";
  /**
   * createCharacterItemTable method
   * creates characterItemTable with given attributes
   *
   * @param conn - database connection
  **/
  public static void createCharacterItemTable( Connection conn )
  throws SQLException
  {
    String query = "CREATE TABLE IF NOT EXISTS characterItem("
      + CHARACTER_ID_COLUMN + " INT,"
      + ITEM_ID_COLUMN + " INT,"
      + "FOREIGN KEY (" + CHARACTER_ID_COLUMN +") REFERENCES " + CharacterTable.TABLE_NAME + " (" + CharacterTable.CHARACTER_ID_COLUMN + "),"
      + "FOREIGN KEY (" + ITEM_ID_COLUMN +") REFERENCES " + ItemTable.TABLE_NAME + " (" + ItemTable.ID_COLUMN + ")"
      + ");" ;

    Statement state = conn.createStatement() ;
    state.execute( query ) ;
  }

  /**
   * giveItem method
   * gives the specified character the specified item
   *
   * @param conn - database connection
   * @param charID - ID of the target character
   * @param itemID - ID of the target item
  **/
  public static void giveItem( Connection conn, int charID, int itemID )
  {
    String query = String.format("INSERT INTO characterItem VALUES("
     + "%d, %d);", charID, itemID ) ;

    runStatement( conn, query ) ;
  }

  /**
   * giveItemList method
   * gives all items in the list to the specified character
   *
   * @param conn - database connection
   * @param charID - ID of target character
   * @param itemIDs - list of target item IDs
  **/
  public static void giveItemList( Connection conn, int charID, List<Integer> itemIDs )
  {
    for( int i = 0 ; i < itemIDs.size() ; i++ )
    {
      giveItem( conn, charID, itemIDs.get( i ) ) ;
    }
  }

  /**
   * takeItem method
   * removes the specified item from the specified character's inventory
   *
   * @param conn - database connection
   * @param charID - ID of target character
   * @param itemID - ID of target item
  **/
  public static void takeItem( Connection conn, int charID, int itemID )
  {
    String query = String.format("DELETE FROM characterItem"
      + " WHERE CHARACTER_ID = %d"
      + " AND ITEM_ID = %d ;",
      + charID, itemID ) ;

    runStatement( conn, query ) ;
  }

  /**
   * takeItemList method
   * removes all items in the list from the specified character's inventory
   *
   * @param conn - database connection
   * @param charID - ID of target character
   * @param itemIDs - List of target item IDs
  **/
  public static void takeItemList(
  Connection conn, 
  int charID, 
  List<Integer> itemIDs )
  {
    for( int i = 0 ; i < itemIDs.size() ; i++ )
    {
      takeItem( conn, charID, itemIDs.get( i ) ) ; 
    }
  }

  /**
   * getInventory method
   * returns all items belonging to the specified character
   *
   * @param conn - database connection
   * @param charID - ID of target character
  **/
  public static void getInventory( Connection conn, int charID )
  {
    String query = String.format("SELECT * FROM item"
      + " WHERE ITEM_ID IN"
      + " SELECT ITEM_ID FROM characterItem"
      + " WHERE CHARACTER_ID = %d ;",
      + charID ) ;

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
}
