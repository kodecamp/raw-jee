package com.kodecamp.web.util;

import java.util.ArrayList;
import java.util.List;

public class Page {
	
	private List<Object> items = null;
	private int pageNo = 0;
	
	public Page(final int pageNo,final List<Object> items){
		this.items = items;
		this.pageNo = pageNo;
	}
	
	public List<Object> getItems(){
		return items;
	}
	
	public int getPageNo(){
		return pageNo;
	}
}
