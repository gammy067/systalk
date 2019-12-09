package com.systalk.sys.web.form.view.bean;

import com.systalk.sys.model.ArticleClickCount;
import com.systalk.sys.web.form.view.bean.base.BaseArticleBean;

/**
 * 前台 - 思拓研究室 - 熱門文章 物件.
 * */
public class PopularArticleBean extends BaseArticleBean {
	
	/** 點擊數. */
	private int clickCount;

	/**
	 * Instantiates a new popular article bean.
	 */
	public PopularArticleBean () {
		
	}

	/**
	 * Instantiates a new popular article bean.
	 *
	 * @param acc the acc
	 */
	public PopularArticleBean(ArticleClickCount acc) {
		this.setSeq(acc.getAtcSeq());
		this.setTopic(acc.getArticle().getAtcTopic());
		this.setUserSeq(acc.getArticle().getUser().getUserSeq());
		this.setAuthor(acc.getArticle().getAuthor());
		this.setCreateDate(acc.getArticle().getCreateDate());
		this.setUpdateDate(acc.getArticle().getUpdateDate());
		this.setPushingDate(acc.getArticle().getPublishingDate());
		this.setStatus(acc.getArticle().getStatus());
		this.setCategorySeq(acc.getArticle().getCategorySeq());
		this.setContent(acc.getArticle().getContent());
		this.setClickCount(acc.getClickCount());
	}

	/**
	 * Gets the click count.
	 *
	 * @return the click count
	 */
	public int getClickCount() {
		return clickCount;
	}

	/**
	 * Sets the click count.
	 *
	 * @param clickCount the new click count
	 */
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

}
