import random
import datetime

USER_COUNT=1000
FRIEND_COUNT=3*USER_COUNT
CHAT_COUNT=3*FRIEND_COUNT
CHARACTER_COUNT=3*USER_COUNT
ITEM_COUNT=1000
ABILITY_COUNT=1000
CHARACTER_ITEM_COUNT=2*CHARACTER_COUNT
CHARACTER_ABILITY_COUNT=2*CHARACTER_COUNT
MAX_CHAR_STATS=20
MAX_HP=200
MAX_XP=20000
NPC_CHANCE=0.2
NPC_USER=999999
CLASSES=["Knight", "Barbarian", "Paladin", "Rogue", "Wizard", "Warlock", "Necromancer", "Conjurer"]
ALIGNMENTS=["Lawful Good", "Neutral Good", "Chaotic Good", "Lawful Neutral", "True Neutral", "Chaotic Neutral", "Lawful Evil", "Neutral Evil", "Chaotic Evil"]
NAMES=["Billy", "Mandy", "Bob", "Joe", "Sue", "Erin", "Frank", "Valencia"]
LAST_NAMES=["Smith", "Doe", "Miller", "Williams", "Anderson", "Thompson", "Wilson"]
RACES=["Human", "Dwarf", "Gnome", "Halfling", "Dragon", "Minotaur", "Naga"]
ITEM_NAMES=["Sword", "Staff", "Leather Armor", "Belt", "Potion", "Missile", "Hat"]
ITEM_DESCRIPTIONS=["A sword for cutting things", "A staff for smacking things or channelling", "Provides protection", "Keeps your pants up. Stylish", "Drinkable to give some effect", "A nuke. Used for nuking", "Put it on your head"]
EFFECTS=["Explodes for large AOE damage", "Poisons the target", "Heals the target for a small amount", "Buffs the targets constitution", "Gives protection to enemy attacks", "N/A"]
ITEM_TYPES=["Weapon", "Armor", "Combat Item", "Accessory"]
MESSAGES=["Hello", "How are you?", "This is a message", "Databases are great", "This game is amazing", "Im having fun playing this game"]
ABILITY_NAMES=["Fireball", "Thundershock", "Vampiric Touch", "Magic Dart", "Heroism", "Glaciate", "Dragon Form"]
ABILITY_DESCRIPTIONS=["A blast of fire", "A small burst of lightning", "Drain health from the target", "A weak but accurate attack", "Buff yourself and allies with bravery of heroes", "A huge wave of ice", "Take the form of a dragon"]
ABILITY_TYPES=["Fire", "Ice", "Force", "Necrotic", "Lightning","Buff","Transmutation"]
QUESTS=["Defeate 10 slimes", "Research new tech", "Slay the dragon", "Free my family","Pay off my debts", "Collect 10 pelts"]
NPC_DESCRIPTIONS=["A crazy old person", "A young sprightly person","The town drunk","A fellow adventurer","A dragon in dsiguise","A bear posing as king"]
JOINER=','
STOP_INDICATOR="STOP\n"
NEW_TABLE_INDICATOR="NEW_TABLE:"

NPC_CHARACTER_LIST=[]
FRIENDS=[[] for _ in range(USER_COUNT)]
CHARACTER_ITEMS=[[] for _ in range(CHARACTER_COUNT)]
CHARACTER_ABILITIES=[[] for _ in range(CHARACTER_COUNT)]

def getRandomDatetimeString():
    return str(datetime.datetime.fromtimestamp(random.randint(1, 1500000000)))

def createGameCharacters(f):
    f.write(NEW_TABLE_INDICATOR + "character" + "\n")
    for i in range(CHARACTER_COUNT):
        maxHP = random.randint(2, MAX_HP)
        maxMP = random.randint(2, MAX_HP)
        uid=NPC_USER
        if(random.random() > NPC_CHANCE):
            uid = random.randint(0,USER_COUNT)
        else:
            NPC_CHARACTER_LIST.append(i)
        data=[str(i),
            str(uid),
            str(random.randint(1,MAX_CHAR_STATS)),
            str(random.randint(1,MAX_CHAR_STATS)),
            str(random.randint(1,MAX_CHAR_STATS)),
            str(random.randint(1,MAX_CHAR_STATS)),
            str(random.randint(1,MAX_CHAR_STATS)),
            str(random.randint(1,MAX_CHAR_STATS)),
            str(random.randint(0,MAX_XP)),
            str(maxHP),
            str(maxMP),
            str(random.randint(1,maxHP)),
            str(random.randint(1, maxMP)),
            str(random.randint(1,MAX_CHAR_STATS)),
            str(random.randint(1,MAX_XP)),
            CLASSES[random.randint(0,len(CLASSES)-1)],
            ALIGNMENTS[random.randint(0,len(ALIGNMENTS)-1)],
            NAMES[random.randint(0,len(NAMES)-1)],
            RACES[random.randint(0,len(RACES)-1)]]
        f.write(JOINER.join(data) + "\n")
    f.write(STOP_INDICATOR)

def createUsers(f):
    f.write(NEW_TABLE_INDICATOR + "user" + "\n")
    for i in range(USER_COUNT):
        fname = NAMES[random.randint(0,len(NAMES)-1)]
        lname = LAST_NAMES[random.randint(0,len(LAST_NAMES)-1)]
        data=[str(i),
            fname,
            lname,
            fname+"_"+lname,
            "usr"+str(i+1)+"@rit.edu",
            "pw"+str(i+1)]
        f.write(JOINER.join(data) + "\n")
    #create the special user, npc_user
    f.write(str(NPC_USER)+",NPC,USER,NPC_USER,npc@rit.edu,npc\n")
    f.write(STOP_INDICATOR)

def createFriends(f):
    f.write(NEW_TABLE_INDICATOR + "friends" + "\n")
    for i in range(FRIEND_COUNT):
        s = -1
        r = -1
        valid = False
        while(not valid):
            s=random.randint(0, USER_COUNT-1)
            r=random.randint(0, USER_COUNT-1)
            if((s != r) and (r not in FRIENDS[s])):
                valid = True
                FRIENDS[s].append(r)
        data=[str(s), str(r)]
        f.write(JOINER.join(data) + "\n")
    f.write(STOP_INDICATOR)

def createItems(f):
    f.write(NEW_TABLE_INDICATOR + "item" + "\n")
    for i in range(ITEM_COUNT):
        data=[str(i),
            ITEM_NAMES[random.randint(0, len(ITEM_NAMES)-1)],
            EFFECTS[random.randint(0,len(EFFECTS)-1)],
            ITEM_DESCRIPTIONS[random.randint(0, len(ITEM_DESCRIPTIONS)-1)],
            str(bool(random.getrandbits(1))).lower(),
            ITEM_TYPES[random.randint(0,len(ITEM_TYPES)-1)],
            str(random.randint(1,MAX_CHAR_STATS)),
            CLASSES[random.randint(0,len(CLASSES)-1)]]
        f.write(JOINER.join(data) + "\n")
    f.write(STOP_INDICATOR)

def createChats(f):
    f.write(NEW_TABLE_INDICATOR + "chat_history" + "\n")
    for i in range(CHAT_COUNT):
        s = -1
        r = -1
        valid = False
        while(not valid):
            s = random.randint(0,len(FRIENDS)-1)
            if(len(FRIENDS[s])>0):
                r = FRIENDS[s][random.randint(0,len(FRIENDS[s])-1)]
                valid = True
        index = random.randint(0, len(ITEM_NAMES)-1)
        data=[str(s),
            str(r),
            getRandomDatetimeString(),
            MESSAGES[random.randint(0,len(MESSAGES)-1)]]
        f.write(JOINER.join(data) + "\n")
    f.write(STOP_INDICATOR)

def createAbilities(f):
    f.write(NEW_TABLE_INDICATOR + "ability" + "\n")
    for i in range(ABILITY_COUNT):
        data=[str(i),
            str(random.randint(1,MAX_CHAR_STATS)),
            str(random.randint(1,MAX_XP)),
            str(random.randint(1,MAX_HP)),
            str(random.random()),
            str(random.random()*10),
            str(random.random()*10),
            ABILITY_TYPES[random.randint(0,len(ABILITY_TYPES)-1)],
            ABILITY_NAMES[random.randint(0,len(ABILITY_NAMES)-1)],
            ABILITY_DESCRIPTIONS[random.randint(0,len(ABILITY_DESCRIPTIONS)-1)],
            EFFECTS[random.randint(0,len(EFFECTS)-1)]]
        f.write(JOINER.join(data) + "\n")
    f.write(STOP_INDICATOR)

def createNPCs(f):
    f.write(NEW_TABLE_INDICATOR + "npc" + "\n")
    for i in range(len(NPC_CHARACTER_LIST)):
        data=[str(i),
            str(NPC_CHARACTER_LIST[i]),
            str(bool(random.getrandbits(1))).lower(),
            QUESTS[random.randint(0,len(QUESTS)-1)],
            NPC_DESCRIPTIONS[random.randint(0,len(NPC_DESCRIPTIONS)-1)]]
        f.write(JOINER.join(data) + "\n")
    f.write(STOP_INDICATOR)

def createCharacterItems(f):
    f.write(NEW_TABLE_INDICATOR + "characterItem" + "\n")
    for i in range(CHARACTER_ITEM_COUNT):
        cid = random.randint(0,CHARACTER_COUNT-1)
        iid = -1
        valid = False
        while(not valid):
            iid = random.randint(0,ITEM_COUNT-1)
            if(iid not in CHARACTER_ITEMS[cid]):
                valid = True
                CHARACTER_ITEMS[cid].append(iid)
        data=[str(cid),str(iid)]
        f.write(JOINER.join(data) + "\n")
    f.write(STOP_INDICATOR)

def createCharacterAbilities(f):
    f.write(NEW_TABLE_INDICATOR + "characterAbility" + "\n")
    for i in range(CHARACTER_ABILITY_COUNT):
        cid = random.randint(0,CHARACTER_COUNT-1)
        aid = -1
        valid = False
        while(not valid):
            aid = random.randint(0,ABILITY_COUNT-1)
            if(aid not in CHARACTER_ABILITIES[cid]):
                valid = True
                CHARACTER_ABILITIES[cid].append(aid)
        data=[str(cid),str(aid)]
        f.write(JOINER.join(data) + "\n")
    f.write(STOP_INDICATOR)

def main():
    f = open('data.csv','w')
    createUsers(f)
    createFriends(f)
    createChats(f)
    createGameCharacters(f)
    createItems(f)
    createAbilities(f)
    createNPCs(f)
    createCharacterItems(f)
    createCharacterAbilities(f)
    f.close()

if __name__ == "__main__":
    main()