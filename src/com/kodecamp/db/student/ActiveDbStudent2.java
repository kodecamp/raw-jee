package com.kodecamp.db.student;

import java.sql.SQLException;

/**
 * This class will have too many constructors. Watch them carefully.
 * Question 1 : What are optional parameters and what are mandatory.
 * @author sunil
 *
 */
public class ActiveDbStudent2 implements IDbStudent {
	
	private String name;
	private String address;
	private String collegeName;
	private IDbStudent orgStudent;
	
	
	private ActiveDbStudent2(final IDbStudent student,final String name,final String address,final String collegeName){
		orgStudent = student;
		this.name = name;
		this.address = address;
		this.collegeName = collegeName;
	}
	
	private ActiveDbStudent2(final IDbStudent student){
		this(student, null, null, null);
	}
	
	@Override
	public String id() {
		return orgStudent.id();
	}

	@Override
	public String name() {
		name = name == null ? orgStudent.name() : name;
		return name;
	}
	
	
	@Override
	public String address() {
		address = address == null ? orgStudent.address() : address;
		return address;
	}

	

	@Override
	public void changeAddress(String newAddress) throws SQLException {
		orgStudent.changeAddress(newAddress);
	}

	@Override
	public String collegeName() {
		collegeName = collegeName == null ? orgStudent.collegeName() : collegeName;
		return collegeName;
	}
	
	static class DefaultStudent{
		
		private String name;
		private String address;
		private String collegeName;
		
		
		public DefaultStudent(){
			
		}
		
		// Start : optional parameters
		public DefaultStudent withName(final String nme){
			name = nme;
			return this;
		}
		
		public DefaultStudent withAddress(final String addr){
			address = addr;
			return this;
		}
		
		public DefaultStudent withCollegeName(final String collegeNme){
			collegeName = collegeNme;
			return this;
		}
		
		
		// mandatory parameters
		public IDbStudent createStudent(final IDbStudent student){
			return new ActiveDbStudent2(student,name,address,collegeName);
		}
		
		
	}

}
