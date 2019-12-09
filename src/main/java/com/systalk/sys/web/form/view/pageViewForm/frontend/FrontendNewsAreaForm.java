package com.systalk.sys.web.form.view.pageViewForm.frontend;

import java.util.List;

import com.systalk.sys.web.form.component.combo.ComboBox;
import com.systalk.sys.web.form.component.pageObject.PageObject;
import com.systalk.sys.web.form.view.bean.NewsAreaBean;

/** 最新消息 form. */

public class FrontendNewsAreaForm {
	
	/** 主題分類下拉選單. */
	private ComboBox typeCombo;
	
	/** 年分下拉選單. */
	private ComboBox dateCombo;
	
	/** 最新消息清單 */
	private List<NewsAreaBean> newsAreaBeanList;
	
	/**  分頁處理物件*/
	private PageObject pageObject = new PageObject();
	
	/** 查詢條件. */
	private Integer typeSeq;
	private Integer dateYear;
	private String searchText;
	
	/** 分頁判斷依據: 是否查詢 */
	private boolean isQuery = false;
	
	public ComboBox getTypeCombo() {
		return typeCombo;
	}

	public void setTypeCombo(ComboBox typeCombo) {
		this.typeCombo = typeCombo;
	}

	public ComboBox getDateCombo() {
		return dateCombo;
	}

	public void setDateCombo(ComboBox dateCombo) {
		this.dateCombo = dateCombo;
	}

	public List<NewsAreaBean> getNewsAreaBeanList() {
		return newsAreaBeanList;
	}

	public void setNewsAreaBeanList(List<NewsAreaBean> newsAreaBeanList) {
		this.newsAreaBeanList = newsAreaBeanList;
	}

	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public Integer getTypeSeq() {
		return typeSeq;
	}

	public void setTypeSeq(Integer typeSeq) {
		this.typeSeq = typeSeq;
	}

	public Integer getDateYear() {
		return dateYear;
	}

	public void setDateYear(Integer dateYear) {
		this.dateYear = dateYear;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public boolean isQuery() {
		return isQuery;
	}

	public void setQuery(boolean isQuery) {
		this.isQuery = isQuery;
	}
}
