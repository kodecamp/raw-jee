package com.kodecamp.web.util;

public enum Severity {
	ERROR("Error"),INFO("Info"),WARNING("Warning");
	
	private String sv;
	
	Severity(String sv){
		this.sv = sv;
	}
	
	public String getValue(){
		return sv;
	}
}
