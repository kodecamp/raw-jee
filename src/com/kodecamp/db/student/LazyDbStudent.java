package com.kodecamp.db.student;

import java.sql.SQLException;


import com.kodecamp.db.util.IDataSource;

public class LazyDbStudent implements IDbStudent {
	
	private final String id;
	private final IDataSource datasource;
	
	public LazyDbStudent(final IDataSource datasource, final String id){
		this.id = id;
		this.datasource = datasource;
	}

	@Override
	public String id() {	
		return id;
	}

	@Override
	public String name() {
		
		String name = "";
		try{
			String query  = "select name from web_students where id = '" + id+"'";
			System.out.println("query : " + query + "datasource : " + datasource); 
			
			name = (String)datasource.connect().execute(query).singleOutcome().value();
		}catch(SQLException sqlException){
			System.out.println("Exception occured while fetching name from database.");
			
		}
		
		return name;
	}

	@Override
	public String address() {
		
		String address = "";
		try{
			address = (String)datasource.connect().execute("select address from web_students where id = '" + id+"'").singleOutcome().value();
		}catch(SQLException sqlException){
			System.out.println("Exception occured while fetching name from database.");
			
		}
		
		return address;
	}

	@Override
	public String collegeName() {
		
		String collegeName = "";
		try{
			collegeName = (String)datasource.connect().execute("select college_Name from web_students where id = '" + id+"'").singleOutcome().value();
		}catch(SQLException sqlException){
			System.out.println("Exception occured while fetching name from database.");
			
		}
		return collegeName;
		
	}

	@Override
	public void changeAddress(final String newAddress) throws SQLException {
		datasource.connect().executeUpdate("update web_students set address = '"+newAddress + "' where id = '" + id+"'");
	}
	
}
