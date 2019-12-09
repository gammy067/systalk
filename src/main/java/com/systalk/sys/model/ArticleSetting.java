package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the article_setting database table.
 * 
 */
@Entity
@Table(name="article_setting")
@NamedQuery(name="ArticleSetting.findAll", query="SELECT a FROM ArticleSetting a")
public class ArticleSetting  {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ArticleSettingPK id;

	private int order;

	private String status;

	//bi-directional many-to-one association to Article
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "atc_seq", referencedColumnName = "atc_seq", insertable = false, updatable = false)
	private Article article;

	public ArticleSetting() {
	}

	public ArticleSettingPK getId() {
		return this.id;
	}

	public void setId(ArticleSettingPK id) {
		this.id = id;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}