package com.kodecamp.db.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ApplicationManagedDataSource extends AbsDataSource{

	private final String url;
	private final String userName;
	private final String password;

	
	public ApplicationManagedDataSource(final String configFileName) {
		Properties prop = loadConfig(configFileName);
		this.url = prop.getProperty("url");
		this.userName = prop.getProperty("username");
		this.password = prop.getProperty("password");
		
	}
	
	private Properties loadConfig(final String fileName){
		Properties prop = null;
		try {
			prop = new Properties();
			InputStream istream = getClass().getClassLoader().getResourceAsStream(fileName);
			prop.load(istream);
			
		} catch (IOException e) {
			System.out.println("Error while loading config file");
			e.printStackTrace();
		}
		
		return prop;
	    
	}

//	public Connection connection() {
//		try {
//			// This class is responsible for translating the object calls to
//			// rdbms calls.
//			Class.forName("org.h2.Driver");
//			/**
//			 * client is aksing to the database management(DriverManager) to
//			 * give the access to the data database management system gives a
//			 * token(Connection) which will be used to access the data.
//			 */ // database url(prot:subproto/ip/db) username password
//			
//			conn = conn == null ? DriverManager.getConnection("jdbc:h2:tcp://"+url, userName, password) : conn;
//			
//
//		} catch (Exception ex) {
//			System.out.println("Error occured while creating conenction." + ex.getMessage());
//			ex.printStackTrace();
//			
//		}
//		
//		return conn;
//	}
	
//	public void closeConnection(){
//		try {
//			this.conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Override
	public IDataSource connect() throws SQLException {
		
		Connection conn = null;
		try {
			// This class is responsible for translating the object calls to
			// rdbms calls.
			Class.forName("org.h2.Driver");
			/**
			 * client is aksing to the database management(DriverManager) to
			 * give the access to the data database management system gives a
			 * token(Connection) which will be used to access the data.
			 */ // database url(prot:subproto/ip/db) username password
			
			conn = DriverManager.getConnection("jdbc:h2:tcp://"+url, userName, password);
			
		} catch (Exception ex) {
			System.out.println("Error occured while creating conenction." + ex.getMessage());
			ex.printStackTrace();
			
		}
		System.out.println("Connection created : " + conn);
		super.connection(conn);
		return this;
	}

	
	
	
}
