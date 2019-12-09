package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the article_label_rel database table.
 * 
 */
@Entity
@Table(name="article_label_rel")
@NamedQuery(name="ArticleLabelRel.findAll", query="SELECT a FROM ArticleLabelRel a")
public class ArticleLabelRel  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="atc_lable_seq")
	private int atcLableSeq;

	@Column(name="lable_seq")
	private int lableSeq;

	private String status;

	//bi-directional many-to-one association to Article
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="atc_seq")
	private Article article;

	public ArticleLabelRel() {
	}

	public int getAtcLableSeq() {
		return this.atcLableSeq;
	}

	public void setAtcLableSeq(int atcLableSeq) {
		this.atcLableSeq = atcLableSeq;
	}

	public int getLableSeq() {
		return this.lableSeq;
	}

	public void setLableSeq(int lableSeq) {
		this.lableSeq = lableSeq;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}