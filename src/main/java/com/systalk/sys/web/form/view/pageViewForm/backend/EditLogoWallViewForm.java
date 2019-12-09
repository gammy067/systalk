package com.systalk.sys.web.form.view.pageViewForm.backend;

import java.io.Serializable;

import javax.validation.Valid;

import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
* 後臺 - 品牌牆新增、編輯 EditLogoWallViewForm.
 * */
public class EditLogoWallViewForm implements Serializable {
	
	/** 設定欄位. */
	@Valid
	private UploadFileForm uploadFileForm = new UploadFileForm();
	
	private String imageUrl;

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
}
