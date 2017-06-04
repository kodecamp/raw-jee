package com.kodecamp.db.util;

import java.sql.SQLException;

public interface IDataSource {
	
	public IDataSource connect() throws SQLException;
	public IDataSource execute(final String query) throws SQLException;
	public SingleOutcome singleOutcome() throws SQLException;
	public ListOutcome listOutcome(final Class dbClass) throws SQLException;
	public void executeUpdate(final String query) throws SQLException;
}
