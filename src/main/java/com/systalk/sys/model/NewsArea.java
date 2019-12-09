package com.systalk.sys.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the news_area database table.
 * 
 */
@Entity
@Table(name="news_area")
@NamedQuery(name="NewsArea.findAll", query="SELECT n FROM NewsArea n")
public class NewsArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="news_seq")
	private int newsSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name="publishing_date")
	private Date publishingDate;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="invalid_date")
	private Date invalidDate;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="function_type")
	private String functionType;
	
	// join多語系table
    @OneToMany
    @JoinColumns({
        @JoinColumn(
            name = "ref_seq",
            referencedColumnName = "news_seq"),
        @JoinColumn(
            name = "function_type",
            referencedColumnName = "function_type")
    })
    private List<LocaleTranslation> localeTransList;
    
	// 新聞分類 join
    @OneToMany
    @JoinColumn(name = "ref_news_seq", referencedColumnName = "news_seq", insertable=false, updatable=false)
    private List<NewsAreaTypeRef> newsAreaTypeRefList;

	public NewsArea() {
	}

	public int getNewsSeq() {
		return this.newsSeq;
	}

	public void setNewsSeq(int newsSeq) {
		this.newsSeq = newsSeq;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPublishingDate() {
		return this.publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public List<LocaleTranslation> getLocaleTransList() {
		return localeTransList;
	}

	public void setLocaleTransList(List<LocaleTranslation> localeTransList) {
		this.localeTransList = localeTransList;
	}

	public List<NewsAreaTypeRef> getNewsAreaTypeRefList() {
		return newsAreaTypeRefList;
	}

	public void setNewsAreaTypeRefList(List<NewsAreaTypeRef> newsAreaTypeRefList) {
		this.newsAreaTypeRefList = newsAreaTypeRefList;
	}
}