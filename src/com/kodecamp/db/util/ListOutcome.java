package com.kodecamp.db.util;

import java.util.ArrayList;
import java.util.List;



public final class ListOutcome<T> {
	
	private List<T> list = new ArrayList<>();
	
	public List<T> values(){
		return list;
	}
	
	public void add(T t){
		list.add(t);
	}
}
