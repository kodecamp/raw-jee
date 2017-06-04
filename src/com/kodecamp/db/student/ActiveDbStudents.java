package com.kodecamp.db.student;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import com.kodecamp.db.util.IDataSource;
import com.kodecamp.db.util.ListOutcome;

public class ActiveDbStudents implements IDbStudents {

	private final IDataSource dataSource;

	public ActiveDbStudents(final IDataSource datasource) {
		this.dataSource = datasource;
	}

	@Override
	public IDbStudent add(String name, String address, String collegeName) throws SQLException {
		String id = generateId(name, address, collegeName);
		String sql = "insert into web_students (id,name,address,college_name) values "
				+ "('"+id+"','"+name+"',"+"'"+address+"',"+"'"+collegeName+"')";
		dataSource.connect().executeUpdate(sql);
		//int id = (int) dataSource.connect().execute("select last_id from web_students_id_counter").singleOutcome().value();
		
		IDbStudent student = new ActiveDbStudent(new LazyDbStudent(dataSource, id)) ;
		return student;
	}

	@Override
	public void remove(String studentId) {

	}

	
//	@Override
//	public Iterator<IDbStudent> iterate() {
//		List<IDbStudent> students = new ArrayList<>();
//		try {
//			ListOutcome listOutcome = dataSource.connect().execute("select * from students")
//					.listOutcome(ActiveDbStudent.class);
//			students.clear();
//			students.addAll(listOutcome.values());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return new Iterator() {
//			int size = students.size();
//			int index = 0;
//
//			@Override
//			public boolean hasNext() {
//
//				return index <= size - 1;
//			}
//
//			@Override
//			public Object next() {
//				index = index + 1;
//				return students.get(index - 1);
//			}
//
//		};
//	}

	@Override
	public Iterator<IDbStudent> iterate() throws SQLException {
		
		ListOutcome	listOutcome = dataSource.connect().execute("select * from web_students").listOutcome(ActiveDbStudent.class);
			
		return new Iterator(){
//			int size = students.size();
			int size = listOutcome == null ? 0 : listOutcome.values().size();
			int index = 0;
			@Override
			public boolean hasNext() {
				
				return index <= size - 1;
			}

			@Override
			public Object next() {
				index = index + 1;
				return listOutcome.values().get(index - 1);
			}
			
		};
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<IDbStudent> fetchByCollege(final String collegeName) throws SQLException {
		
		ListOutcome	listOutcome = dataSource.connect().execute("select * from web_students where college_name like '%" + collegeName + "%'").listOutcome(ActiveDbStudent.class);
			
		return new Iterator(){
//			int size = students.size();
			int size = listOutcome == null ? 0 : listOutcome.values().size();
			int index = 0;
			@Override
			public boolean hasNext() {
				
				return index <= size - 1;
			}

			@Override
			public Object next() {
				index = index + 1;
				return listOutcome.values().get(index - 1);
			}
			
		};
	}
	
	private String generateId(final String name,final String address,final String collegeName){
		int length = collegeName.length();
		
		int randomNumber = ThreadLocalRandom.current().nextInt(0, length);
		String id = name.substring(0, 3) + address.substring(0,3) +  collegeName.substring(0, 3) + randomNumber ;
		return id;
	}
	

}
