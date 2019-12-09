package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the article_click_count database table.
 * 
 */
@Entity
@Table(name="article_click_count")
@NamedQuery(name="ArticleClickCount.findAll", query="SELECT a FROM ArticleClickCount a")
public class ArticleClickCount  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="atc_seq")
	private int atcSeq;

	@Column(name="click_count")
	private int clickCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_date")
	private Date lastDate;

	//bi-directional one-to-one association to Article
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="atc_seq")
	private Article article;

	public ArticleClickCount() {
	}

	public int getAtcSeq() {
		return this.atcSeq;
	}

	public void setAtcSeq(int atcSeq) {
		this.atcSeq = atcSeq;
	}

	public int getClickCount() {
		return this.clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}