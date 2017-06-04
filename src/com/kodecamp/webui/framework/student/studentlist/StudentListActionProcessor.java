package com.kodecamp.webui.framework.student.studentlist;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kodecamp.web.framework.AbstractActionProcessor;
import com.kodecamp.web.framework.IAction;


public class StudentListActionProcessor extends AbstractActionProcessor {

	private Map<String,IAction> actionMap = new HashMap<>();
	
	public StudentListActionProcessor() {
		System.out.println("Constructor : StudentListActionProcess");
		actionMap.put("displayListAction", new DisplayListAction());
		actionMap.put("searchAction",new SearchAction());
	}
	
	@Override
	public IAction processAction(HttpServletRequest request, HttpServletResponse response) {
		String actionName = actionName(request, response);
		return actionMap.get(actionName);
	}
	@Override
	public Map<String, IAction> actionMap() {
		return actionMap;
	}

}
