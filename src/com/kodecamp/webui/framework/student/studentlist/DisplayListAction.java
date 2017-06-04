package com.kodecamp.webui.framework.student.studentlist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kodecamp.db.student.ActiveDbStudents;
import com.kodecamp.db.student.IDbStudent;
import com.kodecamp.db.student.IDbStudents;
import com.kodecamp.db.util.ContainerManagedDataSource;
import com.kodecamp.db.util.IDataSource;
import com.kodecamp.web.framework.IAction;
import com.kodecamp.web.util.IPaginator;
import com.kodecamp.web.util.Message;
import com.kodecamp.web.util.Page;
import com.kodecamp.web.util.Paginator;
import com.kodecamp.web.util.Severity;
import com.kodecamp.webui.student.StudentModel;

public class DisplayListAction implements IAction{

	private IDataSource dataSource;
	
	/**
	 * 
	 */
	public DisplayListAction() {
		System.out.println("DisplayListAction ");
		dataSource = new ContainerManagedDataSource();
	}
	
	
	@Override
	public String urlPattern() {
		
		return null;
	}

	@Override
	public String processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		fetchStudents(request, response);
		return "/views/students/student_list.jsp";
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
		IPaginator paginator = null;
		Message message = null;
		try {
			for (Iterator<IDbStudent> itr = students.iterate(); itr.hasNext();) {
				studentList.add(convert(itr.next()));
			}
			paginator = new Paginator(studentList);
		} catch (SQLException execption) {
			System.out.println("Error occured while fetching records");
			message = new Message("Something went wrong.Please try again.", Severity.ERROR);
			request.setAttribute("message", message);
		}
		Page currentPage = paginator.nextPage();
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

	@Override
	public String actionName() {
		return "displayList";
	}
	
	@Override
	public String toString(){
		return actionName();
	}

}
