package com.kodecamp.web.util;

final public class ActionViewPair {
	private final String action;
	private final String view;
	
	public ActionViewPair(final String action,final String view){
		this.action = action;
		this.view = view;
	}
	
	public String getAction(){
		return action;
	}
	
	public String getView(){
		return view;
	}
}
