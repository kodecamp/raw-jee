package com.kodecamp.db.student;

import java.sql.SQLException;
import java.util.Iterator;
import com.kodecamp.db.util.IDataSource;

public class LazyDbStudents implements IDbStudents {

	private final IDataSource dataSource;
	
	public LazyDbStudents(final IDataSource datasource){
		this.dataSource = datasource;
	}
	
	@Override
	public IDbStudent add(String name, String address, String collegeName) {
		return null;
	}

	@Override
	public void remove(String studentId) {
		
		
	}

	@Override
	public Iterator<IDbStudent> iterate() {
//		dataSource.connect().e
		return null;
	}

	@Override
	public Iterator<IDbStudent> fetchByCollege(String collegeName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
