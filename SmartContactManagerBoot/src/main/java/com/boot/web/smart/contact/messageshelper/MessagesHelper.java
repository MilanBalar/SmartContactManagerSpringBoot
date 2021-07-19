package com.boot.web.smart.contact.messageshelper;

public class MessagesHelper {

	private String content;
	private String type;


	public MessagesHelper(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
