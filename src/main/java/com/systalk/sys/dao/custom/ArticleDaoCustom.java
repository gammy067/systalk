package com.systalk.sys.dao.custom;

import java.util.List;

import com.systalk.sys.web.form.view.bean.ArticleBean;


/**
 * 思拓研究室文章 dao.
 *
 * @param <T> the generic type
 */

public interface ArticleDaoCustom <T> {
	
	/**
	 * Find all to view bean.
	 *
	 * @return the list
	 */
	public List<ArticleBean> findAllToViewBean();
	
	/**
	 * 模糊查詢(作者、標題、標籤).
	 *
	 * @param queryParam the query param
	 * @return the list
	 */
	public List<ArticleBean> fuzzyQueryByJoinCodeTable(int categorySeq, String pram, String sortField);
}
