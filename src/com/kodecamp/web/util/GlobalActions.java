package com.kodecamp.web.util;


/**
 * This class is a utility class which maps URL_PATTERN --> ACTION_NAME(This is used by Servlet and Jsp)
 * @author sunil
 *
 */

public enum GlobalActions{
	VIEW_STUDENTS("/viewStudents"),
	STUDENT_MANAGEMENT("/studentManagement"),
	NEW_STUDENT("/newStudent");
	
	public String action;
	
	private GlobalActions(final String action){
		this.action = action;
	}
	
	public String getAction(){
		return action;
	}
}
	
	
	

