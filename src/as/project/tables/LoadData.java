package as.project.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import as.project.objects.Ability;
import as.project.objects.ChatHistory;
import as.project.objects.Friends;
import as.project.objects.GameCharacter;
import as.project.objects.Item;
import as.project.objects.NPC;
import as.project.objects.User;
import as.project.tables.UserTable;

public class LoadData {
	
	public static String NEW_TABLE_INDICATOR = "NEW_TABLE";
	public static String STOP_INDICATOR = "STOP";
	
	public static void createTables(Connection conn){
		try {
			
				/**
				 * Creates a sample Person table 
				 * and populates it from a csv file
				 */
				UserTable.createUserTable(conn);
				FriendsTable.createFriendsTable(conn);
				ChatHistoryTable.createChatHistoryTable(conn);
				AbilityTable.createAbilityTable(conn);
				CharacterTable.createCharacterTable(conn);
				CharacterAbilityTable.createCharacterAbilityTable(conn);
				ItemTable.createItemTable( conn );
	  			CharacterItemTable.createCharacterItemTable( conn );
	  			NPCTable.createNPCTable(conn);
				
		} catch (SQLException e) {
			System.out.println("SQLException: " + e);
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("General exception: " + e);
			e.printStackTrace();
		}
		
	}
	
	public static void loadData(Connection conn, String fileName){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String currentType = "";
			String line;
			while((line = br.readLine()) != null){
				String[] tableCheck = line.split(":");
				if(tableCheck[0].equals(NEW_TABLE_INDICATOR)){
					currentType = tableCheck[1];
					switch(currentType){
						
						case UserTable.TABLE_NAME:
							loadUsers(conn, br);
							break;
						
						case NPCTable.TABLE_NAME:
							loadNPCs(conn, br);
							break;
						
						case ItemTable.TABLE_NAME:
							loadItems(conn, br);
							break;
							
						case FriendsTable.TABLE_NAME:
							loadFriends(conn, br);
							break;
						
						case ChatHistoryTable.TABLE_NAME:
							loadChatHistory(conn, br);
							break;
						
						case CharacterTable.TABLE_NAME:
							loadGameCharacters(conn, br);
							break;
						
						case CharacterItemTable.TABLE_NAME:
							loadCharacterItems(conn, br);
							break;
						
						case CharacterAbilityTable.TABLE_NAME:
							loadCharacterAbilities(conn, br);
							break;
						
						case AbilityTable.TABLE_NAME:
							loadAbilities(conn, br);
							break;
							
					}
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Error reading file: " + fileName);
			e.printStackTrace();
		}
	}
	
	public static void loadUsers(Connection conn, BufferedReader br){
		try {
			ArrayList<User> users = new ArrayList<User>();
			String line;
			while((line = br.readLine()) != null){
				if(line.equals(STOP_INDICATOR)){
					break;
				}else{
					String[] split = line.split(",");
					users.add(new User(getInt(split[0]),
							split[1].trim(),
							split[2].trim(),
							split[3].trim(),
							split[4].trim(),
							split[5].trim()));
				}
			}
			UserTable.addUserList(conn, users, false);
		} catch (Exception e) {
			System.out.println("Error creating users ");
			e.printStackTrace();
		}
	}
	
	public static void loadNPCs(Connection conn, BufferedReader br){
		try {
			ArrayList<NPC> npcs = new ArrayList<NPC>();
			String line;
			while((line = br.readLine()) != null){
				if(line.equals(STOP_INDICATOR)){
					break;
				}else{
					String[] split = line.split(",");
					npcs.add(new NPC(getInt(split[0]),
							getInt(split[1]),
							getBool(split[2]),
							split[3].trim(),
							split[4].trim()));
				}
			}
			NPCTable.addNPCList(conn, npcs, false);
		} catch (Exception e) {
			System.out.println("Error creating npcs ");
			e.printStackTrace();
		}
	}
	
	public static void loadItems(Connection conn, BufferedReader br){
		try {
			ArrayList<Item> items = new ArrayList<Item>();
			String line;
			while((line = br.readLine()) != null){
				if(line.equals(STOP_INDICATOR)){
					break;
				}else{
					String[] split = line.split(",");
					items.add(new Item(getInt(split[0]),
							split[1].trim(),
							split[2].trim(),
							split[3].trim(),
							getBool(split[4]),
							split[5].trim(),
							getInt(split[6]),
							split[7].trim()));
				}
			}
			ItemTable.addItemList(conn, items, false);
		} catch (Exception e) {
			System.out.println("Error creating items ");
			e.printStackTrace();
		}
	}
	
	public static void loadAbilities(Connection conn, BufferedReader br){
		try {
			ArrayList<Ability> abs = new ArrayList<Ability>();
			String line;
			while((line = br.readLine()) != null){
				if(line.equals(STOP_INDICATOR)){
					break;
				}else{
					String[] split = line.split(",");
					abs.add(new Ability(getInt(split[0]),
							getInt(split[1]),
							getInt(split[2]),
							getInt(split[3]),
							getFloat(split[4]),
							getFloat(split[5]),
							getFloat(split[6]),
							split[7].trim(),
							split[8].trim(),
							split[9].trim(),
							split[10].trim()));
				}
			}
			AbilityTable.addAbilities(conn, abs, false);
		} catch (Exception e) {
			System.out.println("Error creating friends ");
			e.printStackTrace();
		}
	}
	
	public static void loadFriends(Connection conn, BufferedReader br){
		try {
			ArrayList<Friends> friends = new ArrayList<Friends>();
			String line;
			while((line = br.readLine()) != null){
				if(line.equals(STOP_INDICATOR)){
					break;
				}else{
					String[] split = line.split(",");
					friends.add(new Friends(getInt(split[0]),
							getInt(split[1])));
				}
			}
			FriendsTable.addFriendsList(conn, friends, false);
		} catch (Exception e) {
			System.out.println("Error creating friends ");
			e.printStackTrace();
		}
	}
	
	public static void loadChatHistory(Connection conn, BufferedReader br){
		try {
			ArrayList<ChatHistory> chat = new ArrayList<ChatHistory>();
			String line;
			while((line = br.readLine()) != null){
				if(line.equals(STOP_INDICATOR)){
					break;
				}else{
					String[] split = line.split(",");
					chat.add(new ChatHistory(getInt(split[0]),
							getInt(split[1]),
							getTimestamp(split[2]),
							split[3].trim()));
				}
			}
			ChatHistoryTable.addChatHistoryList(conn, chat, false);
		} catch (Exception e) {
			System.out.println("Error creating chat history ");
			e.printStackTrace();
		}
	}
	
	public static void loadGameCharacters(Connection conn, BufferedReader br){
		try {
			ArrayList<GameCharacter> gc = new ArrayList<GameCharacter>();
			String line;
			while((line = br.readLine()) != null){
				if(line.equals(STOP_INDICATOR)){
					break;
				}else{
					String[] split = line.split(",");
					gc.add(new GameCharacter(getInt(split[0]),
							getInt(split[1]),
							getInt(split[2]),
							getInt(split[3]),
							getInt(split[4]),
							getInt(split[5]),
							getInt(split[6]),
							getInt(split[7]),
							getInt(split[8]),
							getInt(split[9]),
							getInt(split[10]),
							getInt(split[11]),
							getInt(split[12]),
							getInt(split[13]),
							getInt(split[14]),
							split[15].trim(),
							split[16].trim(),
							split[17].trim(),
							split[18].trim()));
				}
			}
			CharacterTable.addCharacterList(conn, gc, false);
		} catch (Exception e) {
			System.out.println("Error creating characters ");
			e.printStackTrace();
		}
	}
	
	public static void loadCharacterItems(Connection conn, BufferedReader br){
		try {
			String line;
			while((line = br.readLine()) != null){
				if(line.equals(STOP_INDICATOR)){
					break;
				}else{
					String[] split = line.split(",");
					CharacterItemTable.giveItem(conn, getInt(split[0]), getInt(split[1]), false);
				}
			}
		} catch (Exception e) {
			System.out.println("Error creating character items ");
			e.printStackTrace();
		}
	}
	
	public static void loadCharacterAbilities(Connection conn, BufferedReader br){
		try {
			String line;
			while((line = br.readLine()) != null){
				if(line.equals(STOP_INDICATOR)){
					break;
				}else{
					String[] split = line.split(",");
					CharacterAbilityTable.addCharacterAbility(conn, getInt(split[0]), getInt(split[1]), false);
				}
			}
		} catch (Exception e) {
			System.out.println("Error creating character abilities ");
			e.printStackTrace();
		}
	}
	
	public static int getInt(String str){
		return Integer.valueOf(str.trim());
	}
	
	public static float getFloat(String str){
		return Float.valueOf(str.trim());
	}
	
	public static boolean getBool(String str){
		return Boolean.valueOf(str.trim());
	}
	
	public static Timestamp getTimestamp(String str){
		return Timestamp.valueOf(str);
	}
}
