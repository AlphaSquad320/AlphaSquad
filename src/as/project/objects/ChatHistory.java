package as.project.objects;

import java.sql.Timestamp;

public class ChatHistory {
	
	private static int LATEST_ID = 0;

	private int chatHistoryId;
	private int senderId;
	private int receiverId;
	private Timestamp timestamp;
	private String message;
	
	public ChatHistory(int chatHistoryId, int senderId, int receiverId, Timestamp timestamp, String message) {
		this.chatHistoryId = chatHistoryId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.timestamp = timestamp;
		this.message = message;
	}
	
	public ChatHistory(int senderId, int receiverId, Timestamp timestamp, String message) {
		this.chatHistoryId = getLatestID();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.timestamp = timestamp;
		this.message = message;
	}
	
	public static int getLatestID(){
		LATEST_ID += 1;
		return LATEST_ID;
	}
	
	public int getChatHistoryId() {
		return chatHistoryId;
	}

	public void setChatHistoryId(int chatHistoryId) {
		this.chatHistoryId = chatHistoryId;
	}

	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
