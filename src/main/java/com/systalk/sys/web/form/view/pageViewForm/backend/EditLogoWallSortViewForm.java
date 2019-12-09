package com.systalk.sys.web.form.view.pageViewForm.backend;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.systalk.sys.web.form.view.bean.LogoWallBean;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.SettingForm;

/**
 * 後臺 - 品牌牆排序編輯 EditLogoWallSortViewForm.
 * */
public class EditLogoWallSortViewForm implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 頁面物件. */
	private List<LogoWallBean> logoWallList;
	
	/** 設定欄位. */
	@Valid
	private SettingForm settingForm = new SettingForm();


	public List<LogoWallBean> getLogoWallList() {
		return logoWallList;
	}

	public void setLogoWallList(List<LogoWallBean> logoWallList) {
		this.logoWallList = logoWallList;
	}

	/**
	 * Gets the setting form.
	 *
	 * @return the setting form
	 */
	public SettingForm getSettingForm() {
		return settingForm;
	}

	/**
	 * Sets the setting form.
	 *
	 * @param settingForm the new setting form
	 */
	public void setSettingForm(SettingForm settingForm) {
		this.settingForm = settingForm;
	}
}
