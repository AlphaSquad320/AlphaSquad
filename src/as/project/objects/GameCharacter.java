package as.project.objects;

public class GameCharacter {

	private int characterId;
	private int userId;
	private int str;
	private int dex;
	private int chr;
	private int intel;
	private int wis;
	private int con;
	private int money;
	private int hp;
	private int mp;
	private int curHp;
	private int curMp;
	private int level;
	private int curXp;
	private String characterClass;
	private String alignment;
	private String characterName;
	private String race;
	
	public GameCharacter(int characterId, int userId, int str, int dex, int chr, int intel, int wis, int con, int money, int hp, int mp, int curHp, int curMp, int level, int curXp, String characterClass, String alignment, String characterName, String race) {
		this.setCharacterId(characterId);
		this.setUserId(userId);
		this.setStr(str);
		this.setDex(dex);
		this.setChr(chr);
		this.setIntel(intel);
		this.setWis(wis);
		this.setCon(con);
		this.setMoney(money);
		this.setHp(hp);
		this.setMp(mp);
		this.setCurHp(curHp);
		this.setCurMp(curMp);
		this.setLevel(level);
		this.setCurXp(curXp);
		this.setCharacterClass(characterClass);
		this.setAlignment(alignment);
		this.setCharacterName(characterName);
		this.setRace(race);
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getDex() {
		return dex;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public int getChr() {
		return chr;
	}

	public void setChr(int chr) {
		this.chr = chr;
	}

	public int getIntel() {
		return intel;
	}

	public void setIntel(int intel) {
		this.intel = intel;
	}

	public int getWis() {
		return wis;
	}

	public void setWis(int wis) {
		this.wis = wis;
	}

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getCurHp() {
		return curHp;
	}

	public void setCurHp(int curHp) {
		this.curHp = curHp;
	}

	public int getCurMp() {
		return curMp;
	}

	public void setCurMp(int curMp) {
		this.curMp = curMp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCurXp() {
		return curXp;
	}

	public void setCurXp(int curXp) {
		this.curXp = curXp;
	}

	public String getCharacterClass() {
		return characterClass;
	}

	public void setCharacterClass(String characterClass) {
		this.characterClass = characterClass;
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
	
	public String toString(){
		return this.getCharacterName();
	}

}
