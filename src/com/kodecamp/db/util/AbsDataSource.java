package com.kodecamp.db.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.kodecamp.db.student.IDbStudent;
import com.kodecamp.web.util.Logger;

/**
 * 
 * @author sunil
 *
 */
public abstract class AbsDataSource implements IDataSource {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public AbsDataSource() {

	}

	/**
	 * Do not override this method and always call this method as the last
	 * statement of
	 * 
	 * <pre>
	 * connect()
	 * </pre>
	 * 
	 * @param connection
	 */
	protected void connection(final Connection connection) {
		this.conn = connection;
	}

	@Override
	public abstract IDataSource connect() throws SQLException;

	public IDataSource execute(final String query) {
		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error occured while creating statement.");
		}

		return this;

	}

	public void executeUpdate(final String query) throws SQLException {
		stmt = conn.createStatement();
		stmt.executeUpdate(query);
		conn.close();
	}

	@Override
	public SingleOutcome singleOutcome() throws SQLException {
		SingleOutcome singleOutcome = null;
		while (rs.next()) {
			singleOutcome = new SingleOutcome(rs.getString(1));
		}
		conn.close();
		return singleOutcome;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ListOutcome listOutcome(final Class dbClass) throws SQLException {
		ListOutcome<IDbRowObject> listOutcome = new ListOutcome();

		while (rs.next()) {
			IDbRowObject object = null;
			try {
				object = (IDbRowObject) ((IDbRowObject<IDbStudent>) dbClass.newInstance()).mappedInstance(rs);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			listOutcome.add(object);
		}
		System.out.println("connection closed : " + conn);
		conn.close();
		return listOutcome;
	}

}
