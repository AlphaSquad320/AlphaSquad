package as.project.objects ;

public class Item
{
  private int itemID ;
  private String addEff ; //Additional Effects
  private String desc ;   //Description
  private int reqLevel ;  //Required Level
  private boolean cons ;  //Consumable
  private String type ;
  private int bonus ;
  private String itemClass ;

  /**
   * Item constructor
   *
   * @param ItemID - Item ID number
   * @param AddEff - Additional Effect text string
   * @param Desc - Item description
   * @param ReqLevel - Required level for item use
   * @param Cons - Is item consumable
   * @param Type - Item type (Armor, Weapon, etc.)
   * @param Bonus - Item stat bonus amount
   * @param UseClass - specific subclass of type
  **/
  public Item(
    int ItemID, 
    String AddEff,
    String Desc,
    int ReqLevel,
    boolean Cons,
    String Type,
    int Bonus,
    String ItemClass )
  {
    itemID = ItemID ;
    addEff = AddEff ;
    desc = Desc ;
    reqLevel = ReqLevel ;
    cons = Cons ;
    type = Type ;
    bonus = Bonus ;
    itemClass = ItemClass ;
  }

  public int getID(){ return itemID ; }
  public void setID( int newID ){ itemID = newID ; }

  public String getEffect(){ return addEff ; }
  public void setEffect( String newEffect ){ addEff = newEffect ; }

  public String getDescription(){ return desc ; }
  public void setDescription( String newDescription )
  { desc = newDescription ; }

  public int getReqLevel(){ return reqLevel ; }
  public void setReqLevel( int newReqLevel ){ reqLevel = newReqLevel ; }

  public boolean isConsumable(){ return cons ; }
  public void setConsumable( boolean newConsumable )
  { cons = newConsumable ; }

  public String getType(){ return type ; }
  public void setType( String newType ){ type = newType ; }

  public int getBonus(){ return bonus ; }
  public void setBonus( int newBonus ){ bonus = newBonus ; }

  public String getItemClass(){ return itemClass ; }
  public void setItemClass( String newClass ){ itemClass = newClass ; }
}
