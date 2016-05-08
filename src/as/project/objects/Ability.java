package as.project.objects;

/**
 * @author Martin
 * Holds data for a character ability.
 */
public class Ability {
	// Private Key
	private int abilityID;
	
	// Other attributes
	private int reqLevel;
	private int cost;
	private int baseDamage;
	private float range;
	private float radius;
	private float duration;
	private String type;
	private String name;
	private String description;
	private String additionalEffects;

	/**
	 * @param abilityID: PK - unique ability identifier
	 * @param reqLevel: minimum level to possess this ability
	 * @param cost: mana cost to cast this ability.
	 * @param baseDamage: base damage of the ability
	 * @param range: distance from character this ability can be casted
	 * @param radius: distance from casting point this ability applies
	 * 				  its effects.
	 * @param duration: duration of the ability
	 * @param type: school of magic this ability belongs to.
	 * @param description
	 * @param additionalEffects
	 */
	public Ability( 
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
			String additionalEffects) {
		this.abilityID = abilityID;
		this.reqLevel = reqLevel;
		this.cost = cost;
		this.baseDamage = baseDamage;
		this.range = range;
		this.radius = radius;
		this.duration = duration;
		this.type = type;
		this.name = name;
		this.description = description;
		this.additionalEffects = additionalEffects;
	}
	
	// Get functions
	public int getID() { return abilityID; }
	public int getReqLevel() { return reqLevel; }
	public int getCost() { return cost; }
	public int getBaseDamage() { return baseDamage; }
	public float getRange() { return range; }
	public float getRadius() { return radius; }
	public float getDuration() { return duration; }
	public String getType() { return type; }
	public String getName() { return name; }
	public String getDescription() { return description; }
	public String getAdditionalEffects() { return additionalEffects; }
	public String toString() { return getName(); }
	
}
