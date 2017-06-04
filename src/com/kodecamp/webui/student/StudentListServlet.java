package com.kodecamp.webui.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kodecamp.db.student.ActiveDbStudents;
import com.kodecamp.db.student.IDbStudent;
import com.kodecamp.db.student.IDbStudents;
import com.kodecamp.db.util.ContainerManagedDataSource;
import com.kodecamp.db.util.IDataSource;
import com.kodecamp.web.util.IMessage;
import com.kodecamp.web.util.IPaginator;
import com.kodecamp.web.util.Message;
import com.kodecamp.web.util.Page;
import com.kodecamp.web.util.Paginator;
import com.kodecamp.web.util.Severity;

/**
 * 
 * @author sunil
 *
 */
public class StudentListServlet extends HttpServlet {

	private IDataSource dataSource;
	private IMessage message;
	private IPaginator paginator;
	
	private boolean isNext = true;
	private boolean isPrevious = false;
	@Override
	public void init() {
		System.out.println("Init : StudentListServlet");
		message = null;
		dataSource = new ContainerManagedDataSource();
		// dataSource = new ApplicationManagedDataSource("dbconfig.properties");
	}

	public StudentListServlet() {
		System.out.println("Constructor : StudenetListServelt");
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request,response);
	}

	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		request.setAttribute("message", null);
		processActions(request, response);
		
	}

	private void processActions(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException{
		String action =  request.getAttribute("action") != null ? (String)request.getAttribute("action") : request.getParameter("action") ;
		
		System.out.println("Action in Student List : " + action);
		String viewResource = null;
		
		if(action.equals("VIEW_LIST")){
			System.out.println("StudentList Action -->");
			fetchStudents(request,response);
			// resource is jsp
			viewResource = "/views/students/student_list.jsp";
		}   
		    
		if(action.equals("VIEW_DETAILS")){
			// resource is servlet
			viewResource = "/studentManagement";
			request.setAttribute("action", "VIEW_DETAILS");
		}
		
		if(action.equals("VIEW_NEW_STUDENT")){
			viewResource = "/newStudent";
		}
		
		if(action.equals("NEXT_PAGE")){
			Page currentPage = paginator.nextPage();
			navigationSetting(request,currentPage);
			viewResource = "/views/students/student_list.jsp";
		}
		
		if(action.equals("PREVIOUS_PAGE")){
			Page currentPage = paginator.previousPage();
			navigationSetting(request,currentPage);
			viewResource = "/views/students/student_list.jsp";
		}
		
		if(action.equals("SEARCH")){
			System.out.println("Search Command");
			searchByCollegeName(request, response);
			request.setAttribute("searchText", request.getParameter("searchText"));
			viewResource = "/views/students/student_list.jsp";
		}
		
		
		
		System.out.println("ViewResource : " + viewResource);
		RequestDispatcher view = getServletContext().getRequestDispatcher(viewResource);
		view.forward(request, response);
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void fetchStudents(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		
		List<Object> studentList = new ArrayList<>();
		IDbStudents students = new ActiveDbStudents(dataSource);
		try {
			for (Iterator<IDbStudent> itr = students.iterate(); itr.hasNext();) {
				studentList.add(convert(itr.next()));
			}
			paginator = new Paginator(studentList);
			message = studentList.isEmpty() ? new Message("No records found.", Severity.INFO) : null;
		} catch (SQLException execption) {
			System.out.println("Error occured while fetching records from database.");
			message = new Message("Something went wrong.Please try again.", Severity.ERROR);
		}
		request.setAttribute("message", message);
		
		Page currentPage = paginator.nextPage();
		navigationSetting(request,currentPage);
	}
	
	
	private void searchByCollegeName(final HttpServletRequest request,final HttpServletResponse response) throws ServletException,IOException{
		String collegeName = request.getParameter("searchText");
		Iterator<IDbStudent> itr = null;
		IDbStudents students = new ActiveDbStudents(dataSource);
		List<Object> studentList = new ArrayList<>();
		try {
			itr = "".equals(collegeName) ? students.iterate() : students.fetchByCollege(collegeName);
			while (itr.hasNext()) {
				studentList.add(convert(itr.next()));
			}
			System.out.println("Record Size : " + studentList.size());
			paginator = new Paginator(studentList);
			if(studentList.isEmpty()){
				message = new Message("No matching record found.", Severity.INFO);
				request.setAttribute("message", message);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Page currentPage = paginator.nextPage();
		navigationSetting(request,currentPage);
	}
	
	/**
	 * 
	 * @param request
	 * @param currentPage
	 */
	private void navigationSetting(final HttpServletRequest request,Page currentPage){	
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("paginator", paginator);
	}

	/**
	 * Crates a model which is used to display the data on view
	 * @param dbStudent
	 * @return
	 */
	private StudentModel convert(IDbStudent dbStudent) {
		StudentModel model = new StudentModel(dbStudent.id(), dbStudent.name(), dbStudent.address(),
				dbStudent.collegeName());
		return model;
	}

}
