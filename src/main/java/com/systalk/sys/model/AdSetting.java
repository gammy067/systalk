package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ad_setting database table.
 * 
 */
@Entity
@Table(name="ad_setting")
@NamedQuery(name="AdSetting.findAll", query="SELECT a FROM AdSetting a")
public class AdSetting  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="ad_seq")
	private int adSeq;

	@Column(name="ad_title")
	private String adTitle;

	@Column(name="ad_url")
	private String adUrl;

	@Temporal(TemporalType.DATE)
	@Column(name="publishing_date")
	private Date publishingDate;

	private String status;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_seq")
	private User user;

	public AdSetting() {
	}

	public int getAdSeq() {
		return this.adSeq;
	}

	public void setAdSeq(int adSeq) {
		this.adSeq = adSeq;
	}

	public String getAdTitle() {
		return this.adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public String getAdUrl() {
		return this.adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public Date getPublishingDate() {
		return this.publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}