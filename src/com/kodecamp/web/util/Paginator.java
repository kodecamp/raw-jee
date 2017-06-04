package com.kodecamp.web.util;

import java.util.List;
import java.util.ArrayList;

import java.util.ListIterator;

public class Paginator implements IPaginator{

	private int pageSize = 5;
	private int pageIndex = 0;
	private ListIterator<Page> pagesIterator = null;
	List<Page> pages;
	private int currentPageIndex = -1;

	public Paginator(List<Object> allItems){
		
		pages = createPages(allItems);
		pagesIterator = pages.listIterator(0);
	}
	
	@Override
	public Page nextPage() {
		
		Page page = null;
		if(isNext()){
			currentPageIndex = currentPageIndex + 1;
			page = this.pages.get(currentPageIndex);
			
		}else{
			page = null;
		}
//		System.out.println("current index : " + this.currentPageIndex);
		return page;
	}

	@Override
	public Page previousPage() {
//		System.out.println("current index : " + this.currentPageIndex);
		Page page = null;
		if(isPrevious()){
			currentPageIndex = currentPageIndex - 1;
			page = this.pages.get(currentPageIndex);
			
		}else{
			page = null;
		}
		return page;
	}
	
	private List<Page> createPages(final List<Object> allItems){
		List<Page> pages = new ArrayList<>();
		int size = allItems.size();
		
		for(int i = 0; i < size ; i=i+pageSize){
			Page page = null;
			if((i+pageSize) > size){
//				System.out.println("page created : from : " + i + " to : " + size);
				page = new Page(pageIndex,allItems.subList(i, size));
				
			}else{
//				System.out.println("page created : from : " + i + " to : " + (i+pageSize));
				page = new Page(pageIndex,allItems.subList(i, i+pageSize));
			}
			
			pages.add(page);
			pageIndex = pageIndex + 1;
		}
		return pages;
		
	}

	public boolean isNext() {
		boolean flag = this.currentPageIndex < this.pages.size()-1 && this.currentPageIndex >= -1;
//		System.out.println("isNext : " + flag);
		return flag;
	}

	public boolean isPrevious() {
		boolean flag = this.currentPageIndex > 0;
//		System.out.println("isPrevious : " + flag);
		return flag;
	}
	
	public int getPageCount(){
		return this.pageIndex;
	}

}
