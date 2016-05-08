package as.project.objects ;

public class Item
{
  private int itemID ;
  private String name;
  private String addEff ; //Additional Effects
  private String desc ;   //Description
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
   * @param Cons - Is item consumable
   * @param Type - Item type (Armor, Weapon, etc.)
   * @param Bonus - Item stat bonus amount
   * @param UseClass - specific subclass of type
  **/
  public Item(
    int ItemID, 
    String name,
    String AddEff,
    String Desc,
    boolean Cons,
    String Type,
    int Bonus,
    String ItemClass )
  {
	this.name = name;
    itemID = ItemID ;
    addEff = AddEff ;
    desc = Desc ;
    cons = Cons ;
    type = Type ;
    bonus = Bonus ;
    itemClass = ItemClass ;
  }


  	public String getName() {
	  	return name;
  	}

  	public void setName(String name) {
		this.name = name;
	}


  public int getID(){ return itemID ; }
  public void setID( int newID ){ itemID = newID ; }

  public String getEffect(){ return addEff ; }
  public void setEffect( String newEffect ){ addEff = newEffect ; }

  public String getDescription(){ return desc ; }
  public void setDescription( String newDescription )
  { desc = newDescription ; }

  public boolean isConsumable(){ return cons ; }
  public void setConsumable( boolean newConsumable )
  { cons = newConsumable ; }

  public String getType(){ return type ; }
  public void setType( String newType ){ type = newType ; }

  public int getBonus(){ return bonus ; }
  public void setBonus( int newBonus ){ bonus = newBonus ; }

  public String getItemClass(){ return itemClass ; }
  public void setItemClass( String newClass ){ itemClass = newClass ; }
  
  public String toString(){
	  return this.getName();
  }
}
