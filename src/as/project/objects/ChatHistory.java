package as.project.objects;

import java.sql.Timestamp;

public class ChatHistory {


	private int senderId;
	private int receiverId;
	private Timestamp timestamp;
	private String message;
	
	public ChatHistory(int senderId, int receiverId, Timestamp timestamp, String message) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.timestamp = timestamp;
		this.message = message;
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
