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
import com.kodecamp.db.student.ActiveDbStudent;
import com.kodecamp.db.student.IDbStudent;
import com.kodecamp.db.student.LazyDbStudent;
import com.kodecamp.db.util.ContainerManagedDataSource;
import com.kodecamp.db.util.IDataSource;
import com.kodecamp.web.util.IMessage;
import com.kodecamp.web.util.Message;
import com.kodecamp.web.util.Severity;

public class StudentManagementServlet extends HttpServlet {

	
	IDataSource dataSource = null;
	@Override
	public void init(){
		dataSource = new ContainerManagedDataSource();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processActions(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processActions(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException{
		String action = request.getAttribute("action") != null ? (String)request.getAttribute("action") : request.getParameter("action");
		System.out.println("StudentManagement : action value : " + action);
		RequestDispatcher target = null;
		if("VIEW_DETAILS".equals(action)){
			System.out.println("StudentManagement : STUDENT_MANAGEMENT_ACTION");
			request.removeAttribute("action");
			processDetailsAction(request, response);
			
			target = request.getRequestDispatcher("/views/students/student_management.jsp");
			
		}   
		    
		if("UPDATE_DETAILS".equals(action)){
			System.out.println("Update Details action");
			System.out.println("StudentManagement : UPDATE_DETAILS_ACTION");
			processUpdateAction(request, response);
			
			request.setAttribute("action","VIEW_DETAILS");
			target = getServletContext().getRequestDispatcher("/studentManagement");
			
		}
		    
		if("VIEW_LIST".equals(action)){
			System.out.println("StudentManagement : STUDENT_LIST_ACTION");
			request.removeAttribute("action");
			target = getServletContext().getRequestDispatcher("/studentList");
			

		}
		
		target.forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processDetailsAction(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException{
		System.out.println("StudentDetailsServlet : studentid : " + request.getParameter("param"));
		String studentId = request.getParameter("param");
		System.out.println("student id : " + studentId);
		IDbStudent student = new ActiveDbStudent(new LazyDbStudent(dataSource,studentId)); 
		StudentModel studentModel = new StudentModel(student.id(),student.name(),student.address(),student.collegeName());
		request.setAttribute("studentModel", studentModel);
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processUpdateAction(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("studentId");
		String newAddress = request.getParameter("newAddress");

		System.out.println("id : " + id + " address : " + newAddress);
		IDbStudent dbStudent = new LazyDbStudent(dataSource, id);

		IMessage message = new Message("Address updated successfully.", Severity.INFO);
		;
		try {
			dbStudent.changeAddress(newAddress);
		} catch (SQLException exception) {
			System.out.println("Exception occured while updating record.");
			message = new Message("Error occured while updating address.Try again.", Severity.ERROR);
		}
		request.setAttribute("message", message);
		
	}
	
	
	
}
