package com.systalk.sys.web.form.view.pageViewForm.backend;

import java.io.Serializable;

import javax.validation.Valid;

import com.systalk.sys.web.form.view.bean.BannerSettingBean;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 後臺 - 首頁輪播圖新增、編輯 EditBannerViewForm.
 * */
public class EditBannerViewForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 設定欄位. */
	@Valid
	private UploadFileForm uploadFileForm = new UploadFileForm();
	
	private String imageUrl;
	
	/** 首頁輪播圖 手機圖片上傳路徑 */
	private String imageUrl2;

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

	public String getImageUrl2() {
		return imageUrl2;
	}

	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}
}
