package com.systalk.sys.service;

import java.util.List;

import com.systalk.sys.web.form.view.bean.ArticleBean;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendArticleForm;

/**
 * 前臺 思拓研究室 Service interface.
 * @author Richard
 * */
public interface FrontendArticleService {

	/** 思拓研究室顯示 初始 viewForm. 
	 * @throws Exception */
	public FrontendArticleForm initArticleForm() throws Exception;
	
	/**
	 * 思拓研究室 文章搜尋.
	 *
	 * @param parm the parm
	 * @return the list
	 */
	public List<ArticleBean> doQueryArticleAction (String queryTypeSeq, String parm, String querySortSeq);
}
