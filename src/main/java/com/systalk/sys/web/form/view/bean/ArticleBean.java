package com.systalk.sys.web.form.view.bean;

import com.systalk.sys.model.Article;
import com.systalk.sys.web.form.view.bean.base.BaseArticleBean;

/**
 * 前台 - 思拓研究室 -文章 物件.
 * */
public class ArticleBean extends BaseArticleBean {
	
	
	public ArticleBean() {
	}

	/**
	 * Instantiates a new article bean.
	 *
	 * @param ac the ac
	 */
	public ArticleBean(Article ac) {
		this.setSeq(ac.getAtcSeq());
		this.setTopic(ac.getAtcTopic());
		this.setUserSeq(ac.getUser().getUserSeq());
		this.setAuthor(ac.getAuthor());
		this.setCreateDate(ac.getCreateDate());
		this.setUpdateDate(ac.getUpdateDate());
		this.setPushingDate(ac.getPublishingDate());
		this.setStatus(ac.getStatus());
		this.setCategorySeq(ac.getCategorySeq());
		this.setContent(ac.getContent());
	}
}
