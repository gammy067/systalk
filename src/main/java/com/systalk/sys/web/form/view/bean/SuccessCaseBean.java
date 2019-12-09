package com.systalk.sys.web.form.view.bean;

import com.systalk.sys.model.SuccessCase;
import com.systalk.sys.web.form.view.bean.base.BaseBean;

/**
 * 前台 - 成功案例 物件.
 * */
public class SuccessCaseBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	private String fileName;

	// 語系(英文 - en ,中文 - ch)
	private String language = "";

	public SuccessCaseBean() {
	}

	public SuccessCaseBean(SuccessCase successCase) {
		this.setSeq(successCase.getCaseSeq());
		this.setUserSeq(successCase.getUser().getUserSeq());
		this.setUserName(successCase.getUser().getUserName());
		this.setCreateDate(successCase.getCreateDate());
		this.setUpdateDate(successCase.getUpdateDate());
		this.setPushingDate(successCase.getPublishingDate());
		this.setInvalidDate(successCase.getInvalidDate());
		this.setStatus(successCase.getStatus());
		this.setFileName(successCase.getFileName());
		this.setLanguage(successCase.getLanguage());
		
		// bean 設置多語系對應欄位 (title、content)
		setLocaleTransData(successCase.getLocaleTransList());
	}

	public SuccessCaseBean(SuccessCase successCase, String url) {
		this.setSeq(successCase.getCaseSeq());
		this.setUserSeq(successCase.getUser().getUserSeq());
		this.setUserName(successCase.getUser().getUserName());
		this.setCreateDate(successCase.getCreateDate());
		this.setUpdateDate(successCase.getUpdateDate());
		this.setPushingDate(successCase.getPublishingDate());
		this.setStatus(successCase.getStatus());
		this.setFileName(successCase.getFileName());
		this.setLanguage(successCase.getLanguage());
		this.setUrl(url);
		
		// bean 設置多語系對應欄位 (title、content)
		setLocaleTransData(successCase.getLocaleTransList());
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
