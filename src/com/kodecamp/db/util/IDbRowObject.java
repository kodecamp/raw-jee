package com.kodecamp.db.util;

import java.sql.ResultSet;

public interface IDbRowObject<T> {
	public T mappedInstance(final ResultSet rs) throws Exception;
}
