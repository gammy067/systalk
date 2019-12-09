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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the banner_setting database table.
 * 
 */
@Entity
@Table(name="banner_setting")
@NamedQuery(name="BannerSetting.findAll", query="SELECT b FROM BannerSetting b")
public class BannerSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="banner_seq")
	private int bannerSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_name2")
	private String fileName2;

	@Temporal(TemporalType.DATE)
	@Column(name="publishing_date")
	private Date publishingDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="invalid_date")
	private Date invalidDate;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;
	
	@Column(name="function_type")
	private String functionType;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_seq")
	private User user;
	
	// join多語系table
    @OneToMany
    @JoinColumns({
        @JoinColumn(
            name = "ref_seq",
            referencedColumnName = "banner_seq"),
        @JoinColumn(
            name = "function_type",
            referencedColumnName = "function_type")
    })
    private List<LocaleTranslation> localeTransList;

    
	public BannerSetting() {
	}

	public int getBannerSeq() {
		return this.bannerSeq;
	}

	public void setBannerSeq(int bannerSeq) {
		this.bannerSeq = bannerSeq;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

}