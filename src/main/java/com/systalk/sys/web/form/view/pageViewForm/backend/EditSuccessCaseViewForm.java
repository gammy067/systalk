package com.systalk.sys.web.form.view.pageViewForm.backend;

import java.io.Serializable;

import javax.validation.Valid;

import com.systalk.sys.web.form.view.bean.BannerSettingBean;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 後臺 - 成功案例新增、編輯 EditBannerViewForm.
 * */
public class EditSuccessCaseViewForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 設定欄位. */
	@Valid
	private UploadFileForm uploadFileForm = new UploadFileForm();
	
	private String imageUrl;
	
	private String language;
	
	/** 立即上架 checkbox value */
	private boolean statusCheckVal = false;

	public UploadFileForm getUploadFileForm() {
		return uploadFileForm;
	}

	public void setUploadFileForm(UploadFileForm uploadFileForm) {
		this.uploadFileForm = uploadFileForm;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isStatusCheckVal() {
		return statusCheckVal;
	}

	public void setStatusCheckVal(boolean statusCheckVal) {
		this.statusCheckVal = statusCheckVal;
	}

}
