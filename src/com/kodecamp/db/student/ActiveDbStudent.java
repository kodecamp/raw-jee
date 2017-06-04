package com.kodecamp.db.student;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.kodecamp.db.util.IDbRowObject;

public class ActiveDbStudent implements IDbStudent, IDbRowObject<IDbStudent> {

	private String name;
	private String address;
	private String collegeName;
	private final IDbStudent org;
	private String id;

	public ActiveDbStudent() {
		this(null, null, null, null);
	}

	public ActiveDbStudent(final IDbStudent student, final String name, final String address,
			final String collegeName) {
		org = student;
		this.name = name;
		this.address = address;
		this.collegeName = collegeName;
	}

	public ActiveDbStudent(final IDbStudent student) {
		this(student, null, null, null);
	}

	@Override
	public String id() {
		id = id == null ? org.id() : id;
		return id;
	}

	@Override
	public String name() {
		name = name == null ? org.name() : name;
		return name;
	}

	@Override
	public String address() {
		address = address == null ? org.address() : address;
		return address;
	}

	@Override
	public void changeAddress(String newAddress) throws SQLException {
		this.org.changeAddress(newAddress);

	}

	@Override
	public String collegeName() {
		collegeName = collegeName == null ? org.collegeName() : collegeName;
		return collegeName;
	}

	@Override
	public IDbStudent mappedInstance(final ResultSet rs) throws Exception{

		id = rs.getString("id");
		name = rs.getString("name");
		address = rs.getString("address");
		collegeName = rs.getString("college_name");

		return this;
	}

}
