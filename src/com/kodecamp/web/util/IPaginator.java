package com.kodecamp.web.util;

import java.util.List;

public interface IPaginator {
	public Page nextPage();
	public Page previousPage();
	public boolean isNext();
	public boolean isPrevious();
	public int getPageCount();
}
