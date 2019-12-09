package com.systalk.sys.web.form.view.pageViewForm.backend;

import java.io.Serializable;

import javax.validation.Valid;

import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.SettingForm;

/**
* 後臺 - 行銷影片新增、編輯 EditLogoWallViewForm.
 * */
public class EditVideoViewForm implements Serializable {
	
	/** 設定欄位. */
	@Valid
	private SettingForm settingForm = new SettingForm();

	public SettingForm getSettingForm() {
		return settingForm;
	}

	public void setSettingForm(SettingForm settingForm) {
		this.settingForm = settingForm;
	}
	
}
