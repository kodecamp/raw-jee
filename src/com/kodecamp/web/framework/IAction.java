package com.kodecamp.web.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {
	public String urlPattern();
	public String processAction(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException;
	public String actionName();
}
