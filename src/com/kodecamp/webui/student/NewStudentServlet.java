package com.kodecamp.webui.student;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kodecamp.db.student.ActiveDbStudents;
import com.kodecamp.db.student.IDbStudents;
import com.kodecamp.db.util.ContainerManagedDataSource;
import com.kodecamp.db.util.IDataSource;
import com.kodecamp.web.util.Message;
import com.kodecamp.web.util.Severity;
import com.kodecamp.web.validator.IValidator;
import com.kodecamp.web.validator.NullOrEmptyValidator;
import com.kodecamp.web.validator.ValidInputValidator;

public class NewStudentServlet extends HttpServlet {

	private IDataSource dataSource = null;
	private Message message = null;
	public NewStudentServlet() {
		System.out.println("Constructor : AddNewStudentServlet");
		dataSource = new ContainerManagedDataSource();
		
	}

	@Override
	public void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AddNewStudentServelt---------->");

		doPost(request, response);
	}

	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		processActions(request, response);
	}

	private void processActions(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getAttribute("action") == null ? request.getParameter("action")
				: (String) request.getAttribute("action");
		System.out.println("action : " + action);

		RequestDispatcher rd = null;

		if ("VIEW_NEW_STUDENT".equals(action)) {
			System.out.println("add new student");
			rd = getServletContext().getRequestDispatcher("/views/students/new_student.jsp");
		}

		if ("ADD_NEW_STUDENT".equals(action)) {
//			addStudent(request);
			request.setAttribute("action", "VIEW_NEW_STUDENT");
			Message msg = validateForm(request);
			String navigateTo = msg == null ? pageWithSuccess(request) : pageWithValidationError(request,msg);
			rd = getServletContext().getRequestDispatcher(navigateTo);
		}

		if ("VIEW_LIST".equals(action)) {
			rd = getServletContext().getRequestDispatcher("/studentList");
		}
		rd.forward(request, response);
	}

	private void addStudent(final HttpServletRequest request) {
			IDbStudents students = new ActiveDbStudents(dataSource);
			try {
				students.add(request.getParameter("name"), request.getParameter("address"),
				request.getParameter("collegeName"));
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error occured while adding record.");
				request.setAttribute("message", new Message("Something went wrong. Try again.",Severity.ERROR));
			}
	}

	private Message validateForm(final HttpServletRequest request) {
		
		IValidator validator = new ValidInputValidator(new NullOrEmptyValidator());
		Message msgName = validateName(request,validator);
		Message msgAddress = validateAddress(request,validator);
		Message msgCollegeName = validateCollegeName(request, validator);
		
		Message msg = msgName == null ? 
						(msgAddress == null ? 
								(msgCollegeName == null ? 
										null : msgCollegeName) 
								: msgAddress) 
						: msgName;
		 
		return msg;
	}
	
	private Message validateName(HttpServletRequest request,final IValidator validator){
		boolean isValidName = validator.validate(request.getParameter("name"));
		Message message = isValidName ? null : new Message("'NAME' is not valid.", Severity.ERROR);
		return message;
	}
	
	private Message validateAddress(HttpServletRequest request,final IValidator validator){
		boolean isValidAddress = validator.validate(request.getParameter("address"));
		Message message = isValidAddress ? null : new Message("'ADDRESS' is not valid.", Severity.ERROR);
		return message;
	}
	

	private Message validateCollegeName(HttpServletRequest request,final IValidator validator){
		boolean isValidCollegeName = validator.validate(request.getParameter("collegeName"));
		Message message = isValidCollegeName ? null : new Message("'COLLEGE NAME' is not valid.", Severity.ERROR);
		return message;
	}
	
	private String pageWithValidationError(HttpServletRequest request,Message msg){
		request.setAttribute("name", request.getParameter("name"));
		request.setAttribute("address", request.getParameter("address"));
		request.setAttribute("collegeName", request.getParameter("collegeName"));
		request.setAttribute("message", msg);
		return "/views/students/new_student.jsp";
	}
	
	private String pageWithSuccess(HttpServletRequest request){
		request.setAttribute("name", null);
		request.setAttribute("address", null);
		request.setAttribute("collegeName", null);
		addStudent(request);
		request.setAttribute("message", new Message("Record Saved Successfully.",Severity.INFO));
		return "/views/students/new_student.jsp";
	}
}
