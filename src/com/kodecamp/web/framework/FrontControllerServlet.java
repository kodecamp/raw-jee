package com.kodecamp.web.framework;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontControllerServlet extends HttpServlet{

	private final String ERROR_PAGE = "/views/error.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = req.getRequestURI().replaceFirst(req.getServletContext().getContextPath(), "");
		System.out.println("updated url : " + url);
		IActionProcessor actionProcessor = ActionProcessorFactory.processor(url);
		IAction action = actionProcessor.processAction(req, resp);
		System.out.println("action : " + action);
		String navigateTo = action == null ? ERROR_PAGE : action.processAction(req, resp);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(navigateTo);
		rd.forward(req, resp);
		
	}
	
}
