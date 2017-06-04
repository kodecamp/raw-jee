package com.kodecamp.webui.student;

public class StudentModel {
	private String id;
	private String name;
	private String address;
	private String collegeName;
	
	public StudentModel(final String id,final String name,final String address,final String collegeName){
		this.id = id;
		this.name = name;
		this.address = address;
		this.collegeName = collegeName;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCollegeName() {
		return collegeName;
	}
	
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	
}
