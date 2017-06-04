package com.kodecamp.web.framework;

import java.util.HashMap;
import java.util.Map;

import com.kodecamp.web.util.ActionViewPair;
import com.kodecamp.webui.framework.student.studentlist.StudentListActionProcessor;

public class ActionProcessorFactory {
	private static Map<String,IActionProcessor> actionProcessorMap = new HashMap<>();
	static{
		actionProcessorMap.put("/studentList.do", new StudentListActionProcessor());
	}
	public static IActionProcessor processor(final String processorName){
		
		return actionProcessorMap.get(processorName);
	}
}
