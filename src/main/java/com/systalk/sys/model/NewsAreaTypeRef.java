package com.systalk.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the news_area database table.
 * 
 */
@Entity
@Table(name="news_area_type_ref")
@NamedQuery(name="NewsAreaTypeRef.findAll", query="SELECT n FROM NewsAreaTypeRef n")
public class NewsAreaTypeRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="news_area_type_ref_seq")
	private int newsAreaTypeRefSeq;

	@Column(name="ref_news_seq")
	private int refNewsSeq;

	@Column(name="news_type_seq")
	private int newsTypeSeq;
	
	@Column(name="locale")
	private String locale;
	
	// 新聞分類 join
    @ManyToOne
    @JoinColumn(name = "news_type_seq", referencedColumnName = "news_area_type_seq", insertable=false, updatable=false)
    private NewsAreaType newsAreaType;

	public int getNewsAreaTypeRefSeq() {
		return newsAreaTypeRefSeq;
	}

	public void setNewsAreaTypeRefSeq(int newsAreaTypeRefSeq) {
		this.newsAreaTypeRefSeq = newsAreaTypeRefSeq;
	}

	public int getRefNewsSeq() {
		return refNewsSeq;
	}

	public void setRefNewsSeq(int refNewsSeq) {
		this.refNewsSeq = refNewsSeq;
	}

	public int getNewsTypeSeq() {
		return newsTypeSeq;
	}

	public void setNewsTypeSeq(int newsTypeSeq) {
		this.newsTypeSeq = newsTypeSeq;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public NewsAreaType getNewsAreaType() {
		return newsAreaType;
	}

	public void setNewsAreaType(NewsAreaType newsAreaType) {
		this.newsAreaType = newsAreaType;
	}
}