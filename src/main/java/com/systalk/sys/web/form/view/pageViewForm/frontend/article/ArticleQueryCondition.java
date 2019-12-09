package com.systalk.sys.web.form.view.pageViewForm.frontend.article;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/** 前臺- 思拓研究室 -查詢條件. */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleQueryCondition {

	/** 查詢條件 */
	public String queryStr;
	
	/** 主題分類 下拉選單值. */
	public String queryType;
	
	/** 排序 下拉選單值. */
	public String querySort;

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQuerySort() {
		return querySort;
	}

	public void setQuerySort(String querySort) {
		this.querySort = querySort;
	}
}
