package com.systalk.sys.web.form.component.pageObject;

public class PageObject {
	// new 給予初始值 起始頁數1 全部頁數1
	private Integer startPage = 1;
	private Integer totalPages = 1;
	
	public PageObject(Integer startPage, Integer totalPages) {
		this.startPage = startPage;
		this.totalPages = totalPages;
	}

	public PageObject() {
	}
	
	public Integer getStartPage() {
		return startPage;
	}
	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}