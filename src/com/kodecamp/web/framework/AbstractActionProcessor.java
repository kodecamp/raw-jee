package com.kodecamp.web.framework;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractActionProcessor implements IActionProcessor{

	
	@Override
	public IAction processAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String actionName(final HttpServletRequest request,final HttpServletResponse response) {
		String actionName = request.getParameter("action") == null ? (String)request.getAttribute("action") : request.getParameter("action");
		
		return actionName;
	}
	
	abstract public Map<String,IAction> actionMap();

}
