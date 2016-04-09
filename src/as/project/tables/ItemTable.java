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

public class ItemTable extends TableBase
{
  /**
   * createItemTable method
   * create item table with given attributes
   *
   * @param conn - database connection
  **/
  public static void createItemTable( Connection conn ) throws SQLException
  {
    String query = "CREATE TABLE IF NOT EXISTS item("
      + "ITEM_ID INT PRIMARY KEY,"
      + "ADD_EFF VARCHAR(255),"
      + "DESC VARCHAR(255),"
      + "CONS BOOLEAN,"
      + "TYPE VARCHAR(255),"
      + "BONUS INT,"
      + "ITEM_CLASS VARCHAR(255),"
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
      addUser( conn, itemList.get( i ) ) ;
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
}
