package message.model.vo;

import java.sql.Date;

public class Message {
	//생성자
	public Message() {}
	
	private int message_No;
	private Date message_Date;
	private String message_Subject;
	private String message_Contents;
	private int message_Check;
	private String sender;
	private String receiver;
	
	//getter, setter
	public int getMessage_No() {
		return message_No;
	}
	public void setMessage_No(int message_No) {
		this.message_No = message_No;
	}
	public Date getMessage_Date() {
		return message_Date;
	}
	public void setMessage_Date(Date message_Date) {
		this.message_Date = message_Date;
	}
	public String getMessage_Subject() {
		return message_Subject;
	}
	public void setMessage_Subject(String message_Subject) {
		this.message_Subject = message_Subject;
	}
	public String getMessage_Contents() {
		return message_Contents;
	}
	public void setMessage_Contents(String message_Contents) {
		this.message_Contents = message_Contents;
	}
	public int getMessage_Check() {
		return message_Check;
	}
	public void setMessage_Check(int message_Check) {
		this.message_Check = message_Check;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	@Override
	public String toString() {
		return "Message [message_No=" + message_No + ", message_Date=" + message_Date + ", message_Subject="
				+ message_Subject + ", message_Contents=" + message_Contents + ", message_Check=" + message_Check
				+ ", sender=" + sender + ", receiver=" + receiver + "]";
	}
	

	
}
