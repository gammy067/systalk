package com.systalk.sys.web.form.view.bean;

import com.systalk.sys.model.VideoSetting;
import com.systalk.sys.web.form.view.bean.base.BaseBean;

/**
 * 前台 - 行銷影片 物件.
 * */
public class VideoSettingBean extends BaseBean {

	public VideoSettingBean() {
	}
	
	public VideoSettingBean(VideoSetting setting) {
		this.setSeq(setting.getVideoSeq());
		this.setPushingDate(setting.getPublishingDate());
		this.setUserSeq(setting.getUser().getUserSeq());
		this.setUserName(setting.getUser().getUserName());
		this.setStatus(setting.getStatus());
		this.setUrl(setting.getVideoUrl());
		
		// bean 設置多語系對應欄位 (title、content)
		setLocaleTransData(setting.getLocaleTransList());
	}
}
