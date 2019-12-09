package com.systalk.sys.web.form.view.bean;

import java.util.Date;

import com.systalk.sys.model.AdSetting;

/**
 * 廣告版位設定 bean.
 * */
public class AdBean {
	
	/** 流水號. */
	private int adSeq;
	
	/** 標題. */
	private String adTitle = "";
	
	/** 網址. */
	private String adUrl = "";
	
	/** 上架日. */
	private Date publishingDate;
	
	/** 狀態(下架 - 0 ,上架 - 1 ). */
	private String status = "";
	
	/** 編輯者SEQ. */
	private int userSeq;

	/**
	 * Instantiates a new ad bean.
	 */
	public AdBean() {}
	
	/**
	 * Instantiates a new ad bean.
	 *
	 * @param ad the ad
	 */
	public AdBean(AdSetting ad) {
		this.setAdSeq(ad.getAdSeq());
		this.setAdTitle(ad.getAdTitle());
		this.setAdUrl(ad.getAdUrl());
		this.setPublishingDate(ad.getPublishingDate());
		this.setStatus(ad.getStatus());
		this.setUserSeq(ad.getUser().getUserSeq());
	}

	/**
	 * Gets the ad seq.
	 *
	 * @return the ad seq
	 */
	public int getAdSeq() {
		return adSeq;
	}

	/**
	 * Sets the ad seq.
	 *
	 * @param adSeq the new ad seq
	 */
	public void setAdSeq(int adSeq) {
		this.adSeq = adSeq;
	}

	/**
	 * Gets the ad title.
	 *
	 * @return the ad title
	 */
	public String getAdTitle() {
		return adTitle;
	}

	/**
	 * Sets the ad title.
	 *
	 * @param adTitle the new ad title
	 */
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	/**
	 * Gets the ad url.
	 *
	 * @return the ad url
	 */
	public String getAdUrl() {
		return adUrl;
	}

	/**
	 * Sets the ad url.
	 *
	 * @param adUrl the new ad url
	 */
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	/**
	 * Gets the publishing date.
	 *
	 * @return the publishing date
	 */
	public Date getPublishingDate() {
		return publishingDate;
	}

	/**
	 * Sets the publishing date.
	 *
	 * @param publishingDate the new publishing date
	 */
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the user seq.
	 *
	 * @return the user seq
	 */
	public int getUserSeq() {
		return userSeq;
	}

	/**
	 * Sets the user seq.
	 *
	 * @param userSeq the new user seq
	 */
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

}
