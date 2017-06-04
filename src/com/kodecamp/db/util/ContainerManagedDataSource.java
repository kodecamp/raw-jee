package com.kodecamp.db.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 
 * @author sunil
 *
 */
public class ContainerManagedDataSource extends AbsDataSource {

	private final String dataSourceName;
	
	public ContainerManagedDataSource(final String dsName){
		dataSourceName = dsName;
	}
	
	public ContainerManagedDataSource(){
		this(null);
	}
	
	public IDataSource connect() throws SQLException    {
		Context ctx = null;
		DataSource ds = null;
		Connection conn = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/kodecampDB");
			
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println("Unable to access database");
		}catch (SQLException sqlEx){
			System.out.println("Error occured while getting connection.");
			int count = 0;
			while(true){
				count = count + 1;
				System.out.println("Retrying ...");
				try {
					Thread.sleep(5000);
					try {
						conn = ds.getConnection();
						if(conn != null){
							System.out.println("Hurray ! Retrying makes sense.Connection established.");
							break;
						}
					} catch (SQLException exception) {
						System.err.println("Unable to connect. Attempts made : " +count);
						if(count > 3){
							throw exception;
						}
					}
				} catch (InterruptedException e) {
					System.out.println("Error occured in waiting.");
				}
			}
		}
		
		
		System.out.println("CONNECTION : " + conn);
//		System.out.println("Connection created : " + conn);
		super.connection(conn);
		
		return this;
	}

//	public IDataSource execute(final String query) {
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(query);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return this;
//
//	}
//
//	@Override
//	public SingleOutcome singleOutcome() throws SQLException {
//		SingleOutcome singleOutcome = null;
//		while (rs.next()) {
//			singleOutcome = new SingleOutcome(rs.getString(1));
//		}
//		conn.close();
//		return singleOutcome;
//	}
//	
//	@Override
//	public ListOutcome listOutcome(final Class dbClass) throws SQLException {
//		ListOutcome<IDbRowObject> listOutcome = new ListOutcome();
//		
//		while(rs.next()){
//			IDbRowObject student = null;
//			try {
//				student = (IDbRowObject) ((IDbRowObject<IDbStudent>)dbClass.newInstance()).mappedInstance(rs);
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//			listOutcome.add(student);
//		}
//		
//		conn.close();
//		return listOutcome;
//	}

}
