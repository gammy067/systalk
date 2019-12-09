package com.systalk.sys.web.form.view.pageViewForm.frontend;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.systalk.sys.web.form.view.bean.BannerSettingBean;
import com.systalk.sys.web.form.view.bean.EmailContactInfo;
import com.systalk.sys.web.form.view.bean.LogoWallBean;
import com.systalk.sys.web.form.view.bean.SuccessCaseBean;
import com.systalk.sys.web.form.view.bean.VideoSettingBean;

/** 前臺首頁顯示 form. */

public class FrontendIndexForm implements Serializable{

	private static final long serialVersionUID = 1L;

	// banner 輪播文章
	List<BannerSettingBean> bannerList;
	
	// 行銷影片
	VideoSettingBean videoSetting;
	
	// 品牌牆
	List<LogoWallBean> logoWallList;
	
	// 成功案例
	List<SuccessCaseBean> successCaseList;
	
	// 聯絡我們
	@Valid
	EmailContactInfo emailContactInfo;


	public List<BannerSettingBean> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<BannerSettingBean> bannerList) {
		this.bannerList = bannerList;
	}

	public VideoSettingBean getVideoSetting() {
		return videoSetting;
	}

	public void setVideoSetting(VideoSettingBean videoSetting) {
		this.videoSetting = videoSetting;
	}

	public List<LogoWallBean> getLogoWallList() {
		return logoWallList;
	}

	public void setLogoWallList(List<LogoWallBean> logoWallList) {
		this.logoWallList = logoWallList;
	}

	public List<SuccessCaseBean> getSuccessCaseList() {
		return successCaseList;
	}

	public void setSuccessCaseList(List<SuccessCaseBean> successCaseList) {
		this.successCaseList = successCaseList;
	}

	public EmailContactInfo getEmailContactInfo() {
		return emailContactInfo;
	}

	public void setEmailContactInfo(EmailContactInfo emailContactInfo) {
		this.emailContactInfo = emailContactInfo;
	}

}
