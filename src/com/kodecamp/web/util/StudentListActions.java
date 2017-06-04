package com.kodecamp.web.util;

import java.util.HashMap;
import java.util.Map;

public enum StudentListActions{
	
	URL_PATTERN("VIEW_LIST_ACTION");
	
	private Map<String,String> map = new HashMap<>();
	private String urlPattern;
	
	private StudentListActions(final String urlPattern){
		this.urlPattern = urlPattern;
	}
	
	public String getUrlPattern(){
		return urlPattern;
	}

	

	
	
	
}
