package com.systalk.sys.web.form.view.bean;

import com.systalk.sys.model.ArticleSetting;
import com.systalk.sys.web.form.view.bean.base.BaseArticleBean;

/**
 * 前台 - 思拓研究室 - 置頂文章 輪播 物件.
 * */
public class TopArticleBean extends BaseArticleBean {
	
	/** The type. */
	// 類型: (置頂文章 - top ) 
	private String type = "";

	/** The order. */
	// 排序
	private int order;
	
	/**
	 * Instantiates a new top article bean.
	 */
	public TopArticleBean() {}
	
	/**
	 * Instantiates a new top article bean.
	 *
	 * @param as the as
	 */
	public TopArticleBean(ArticleSetting as) {
		this.setSeq(as.getId().getAtcSeq());
		this.setTopic(as.getArticle().getAtcTopic());
		this.setUserSeq(as.getArticle().getUser().getUserSeq());
		this.setAuthor(as.getArticle().getAuthor());
		this.setCreateDate(as.getArticle().getCreateDate());
		this.setUpdateDate(as.getArticle().getUpdateDate());
		this.setPushingDate(as.getArticle().getPublishingDate());
		this.setStatus(as.getStatus());
		this.setCategorySeq(as.getArticle().getCategorySeq());
		this.setContent(as.getArticle().getContent());
		this.setType(as.getId().getType());
		this.setOrder(as.getOrder());
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
