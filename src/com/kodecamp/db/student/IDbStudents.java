package com.kodecamp.db.student;

import java.sql.SQLException;
import java.util.Iterator;


/**
 * This class represents the table Student.
 * @author sunil
 *
 */
public interface IDbStudents {
	
	/**
	 * This method will generate an student id and creates a student.
	 * 
	 * @param name
	 * @param address
	 * @param collegeName
	 * @return Object of type <tt>IStudent</tt>
	 */
	public IDbStudent add(final String name,final String address,final String collegeName) throws SQLException;
	
	/**
	 * This method is used to remove the student. 
	 * @param studentId
	 */
	public void remove(final String studentId) throws SQLException;
	
	/**
	 * This method retrieves the all students from the table.
	 * @return
	 */
	public Iterator<IDbStudent> iterate() throws SQLException;
	
	public Iterator<IDbStudent> fetchByCollege(final String collegeName) throws SQLException;
	
	
	
}
