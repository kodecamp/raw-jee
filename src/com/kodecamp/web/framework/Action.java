package com.kodecamp.web.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Action implements IAction{

	private String urlPattern;
	private String actionName;
	public Action(final String urlPattern,final String actionName){
		this.urlPattern = urlPattern;
		this.actionName = actionName;
	}
	@Override
	public String urlPattern() {
		return this.urlPattern;
	}
	
	

	@Override
	public String processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return null;
	}
	@Override
	public String actionName() {
		
		return this.actionName;
	}

}
