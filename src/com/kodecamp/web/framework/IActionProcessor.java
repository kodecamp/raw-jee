package com.kodecamp.web.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IActionProcessor {
	public IAction processAction(HttpServletRequest request,HttpServletResponse response);
	public String actionName(HttpServletRequest request,HttpServletResponse response);
}
