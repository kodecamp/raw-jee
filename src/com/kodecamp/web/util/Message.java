package com.kodecamp.web.util;

public final class Message implements IMessage {

	private final Severity severityLevel;
	private final String shortMessage;
	private final String message;
	
	public Message(final String shortMessage,final String message,final Severity sl){
		this.shortMessage = shortMessage;
		this.message = message;
		this.severityLevel = sl;
	}
	
	public Message(final String message,final Severity sl){
		this("",message,sl);
	}
	
	@Override
	public String getSeverity() {
		return severityLevel.getValue();
	}

	@Override
	public String getShortMessage() {
		return shortMessage;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
