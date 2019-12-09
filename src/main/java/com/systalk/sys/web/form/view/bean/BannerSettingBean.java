package com.systalk.sys.web.form.view.bean;

import com.systalk.sys.model.BannerSetting;
import com.systalk.sys.web.form.view.bean.base.BaseBean;

/**
 * 前台 - 首頁輪播 物件.
 * */
public class BannerSettingBean extends BaseBean {
	/** 檔案名稱. */
	private String fileName;
	
	/** mobile圖片格式 檔案名稱. */
	private String fileName2;

	/** mobile圖片格式 檔案路徑. */
	private String url2 = "";

	public BannerSettingBean() {
	}
	
	/**
	 * 前臺首頁顯示bean constructor.
	 *
	 * @param setting the setting
	 * @param url the url
	 */
	public BannerSettingBean(BannerSetting setting, String url, String url2) {	
		this.setSeq(setting.getBannerSeq());
		this.setUserSeq(setting.getUser().getUserSeq());
		this.setUserName(setting.getUser().getUserName());
		this.setCreateDate(setting.getCreateDate());
		this.setUpdateDate(setting.getUpdateDate());
		this.setPushingDate(setting.getPublishingDate());
		this.setInvalidDate(setting.getInvalidDate());
		this.setStatus(setting.getStatus());
		
		// bean 設置多語系對應欄位 (title、content)
		setLocaleTransData(setting.getLocaleTransList());

		this.setUrl(url);
		this.setUrl2(url2);
	}
	
	/**
	 * 後臺查詢顯示bean constructor.
	 *
	 * @param setting the setting
	 */
	public BannerSettingBean(BannerSetting setting) {	
		this.setSeq(setting.getBannerSeq());
		this.setUserSeq(setting.getUser().getUserSeq());
		this.setUserName(setting.getUser().getUserName());
		this.setCreateDate(setting.getCreateDate());
		this.setUpdateDate(setting.getUpdateDate());
		this.setPushingDate(setting.getPublishingDate());
		this.setInvalidDate(setting.getInvalidDate());
		this.setStatus(setting.getStatus());
		this.setFileName(setting.getFileName());
		this.setFileName2(setting.getFileName2());
		
		// bean 設置多語系對應欄位 (title、content)
		setLocaleTransData(setting.getLocaleTransList());
	}

	public String getFileName() {
		return fileName;
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

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	
}
