package as.project.objects;

/**
 * Holds data on a Non-Player Character (NPC)
 * @author Colin Thompson
 *
 */
public class NPC {
	private int npcID;
	private int characterID;
	private String name;
	private boolean isHostile;
	private String assocQuest;
	private String description;
	
	/**
	 * constructor for the NPC class
	 * @param id		- the npcID
	 * @param charID	- the characterID
	 * @param name		- the NPC's name
	 * @param hostile	- if the NPC is hostile
	 * @param quest		- the quest associated with the NPC
	 * @param desc		- the description of the NPC
	 */
	public NPC (int id, int charID, String name, boolean hostile, String quest, String desc){
		npcID = id;
		characterID = charID;
		this.name = name;
		isHostile = hostile;
		assocQuest = quest;
		description = desc;
	}

	/**
	 * @param npcID - the npcID to set
	 */
	public void setNpcID(int npcID) {
		this.npcID = npcID;
	}

	/**
	 * @param characterID - the characterID to set
	 */
	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}

	/**
	 * @param name - the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param isHostile - whether or not the NPC is hostile
	 */
	public void setHostile(boolean isHostile) {
		this.isHostile = isHostile;
	}

	/**
	 * @param assocQuest - the quest associated with the NPC
	 */
	public void setAssocQuest(String assocQuest) {
		this.assocQuest = assocQuest;
	}

	/**
	 * @param description - the description of the NPC
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the npcID
	 */
	public int getNpcID() {
		return npcID;
	}

	/**
	 * @return the characterID
	 */
	public int getCharacterID() {
		return characterID;
	}

	/**
	 * @return the NPC's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return true if the NPC is hostile
	 */
	public boolean isHostile() {
		return isHostile;
	}

	/**
	 * @return the quest associated with the NPC
	 */
	public String getAssocQuest() {
		return assocQuest;
	}

	/**
	 * @return the description of the NPC
	 */
	public String getDescription() {
		return description;
	}
}
