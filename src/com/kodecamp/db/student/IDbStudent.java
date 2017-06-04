package com.kodecamp.db.student;

import java.sql.SQLException;

/**
 * This interface is a contract for each student row of student table.
 * @author sunil
 *
 */
public interface IDbStudent {
	
	// represents id of the row
	public String id();
	
	// represents the name of the row
	// select name from student where id = ?
	public String name();
	
	// represents the address of the row
	// select address from student where id = ?
	public String address();
	
	public void changeAddress(final String newAddress) throws SQLException;
	
	// represents the course of the row.
	// select course from student where id = ?
	public String collegeName();

}
