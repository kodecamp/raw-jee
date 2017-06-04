package com.kodecamp.webui.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kodecamp.db.student.IDbStudent;
import com.kodecamp.db.student.LazyDbStudent;
import com.kodecamp.db.util.ContainerManagedDataSource;
import com.kodecamp.db.util.IDataSource;
import com.kodecamp.web.util.IMessage;
import com.kodecamp.web.util.Message;
import com.kodecamp.web.util.Severity;


public class UpdateStudentDetails extends HttpServlet {

	private IDataSource dataSource;
	private Map<String,String> actionResourceMap = new HashMap<>();

	public UpdateStudentDetails() {

	}

	public void init() {
		actionResourceMap.put("back", "/studentList");
		actionResourceMap.put("update", "/veiwDetails");
		actionResourceMap.put("delete", "/studentList");
		dataSource = new ContainerManagedDataSource();
	}

	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {

		String action = request.getParameter("action");
		String view = null;
		
		
		
		
		
		// this enforces the browser to make a new request 
		//response.sendRedirect(getServletContext().getContextPath()+"/studentList?action=studentList");
		
		//processAction(action, request, response);

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void update(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		String id = request.getParameter("studentId");
		String newAddress = request.getParameter("newAddress");

		System.out.println("id : " + id + " address : " + newAddress);
		IDbStudent dbStudent = new LazyDbStudent(dataSource, id);

		IMessage message = new Message("Address updated successfully.", Severity.INFO);
		;
		try {
			dbStudent.changeAddress(newAddress);
		} catch (SQLException exception) {
			System.out.println("Exception occered while updating record.");
			message = new Message("Error occured while updating address.Try again.", Severity.ERROR);
		}
		request.setAttribute("message", message);
		RequestDispatcher viewDetailsServlet = request.getRequestDispatcher("/viewDetails");
		viewDetailsServlet.forward(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void displayList(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("displayList ------>");
		/*
		RequestDispatcher viewList = request.getRequestDispatcher("/studentList");
		viewList.forward(request,response);*/
		
		//response.sendRedirect(request.getContextPath()+"/studentList?action" + ServletActions.STUDENT_LIST_ACTION.value);
		
		/*
		response.setStatus(300);
		response.setHeader("Location", request.getContextPath()+"/studentList");
		*/
	}
	
	private void processAction(final String action,final HttpServletRequest request,final HttpServletResponse response) throws IOException,ServletException{
		
		
		
		
	}
}
