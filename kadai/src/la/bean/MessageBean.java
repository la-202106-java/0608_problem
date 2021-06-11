package la.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageBean implements Serializable {

	private ArrayList<String> messages;

	public ArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}

	public MessageBean() {
	}

	public MessageBean(ArrayList<String> messages) {
		super();
		this.messages = messages;
	}

	//	public String getMessage() {
	//		return message;
	//	}
	//
	//	public void setMessage(String message) {
	//		this.message = message;
	//	}
	//
	//	public MessageBean() {
	//	}
	//
	//	public MessageBean(String message) {
	//		this.message = message;
	//	}

}
