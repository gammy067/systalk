package com.systalk.sys.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the news_area database table.
 * 
 */
@Entity
@Table(name="news_area_type")
@NamedQuery(name="NewsAreaType.findAll", query="SELECT n FROM NewsAreaType n")
public class NewsAreaType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="news_area_type_seq")
	private int newsAreaTypeSeq;

	@Column(name="type_code")
	private String typeCode;

	@Column(name="type_name")
	private String typeName;

	@Column(name="lcoale")
	private String locale;

	public NewsAreaType() {
	}


	public int getNewsAreaTypeSeq() {
		return newsAreaTypeSeq;
	}


	public void setNewsAreaTypeSeq(int newsAreaTypeSeq) {
		this.newsAreaTypeSeq = newsAreaTypeSeq;
	}


	public String getTypeCode() {
		return typeCode;
	}


	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getLocale() {
		return locale;
	}


	public void setLocale(String locale) {
		this.locale = locale;
	}

}