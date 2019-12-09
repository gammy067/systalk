package com.systalk.sys.web.form.view.bean;

import com.systalk.sys.model.Logowall;
import com.systalk.sys.web.form.view.bean.base.BaseBean;

/**
 * 前台 - 品牌牆 物件.
 * */
public class LogoWallBean extends BaseBean {

	// logo順序
	private Integer logoOrder;
	
	// 檔案名稱
	private String fileName;
	
	public LogoWallBean(Logowall lw) {
		this.setSeq(lw.getLogoWallSeq());
		this.setUserSeq(lw.getUser().getUserSeq());
		this.setUserName(lw.getUser().getUserName());
		this.setPushingDate(lw.getPublishingDate());
		this.setStatus(lw.getStatus());
		this.setLogoOrder(lw.getLogoOrder());
		this.setFileName(lw.getFileName());
	}
	
	public LogoWallBean(Logowall lw, String url) {
		this.setSeq(lw.getLogoWallSeq());
		this.setUserSeq(lw.getUser().getUserSeq());
		this.setUserName(lw.getUser().getUserName());
		this.setPushingDate(lw.getPublishingDate());
		this.setStatus(lw.getStatus());
		this.setLogoOrder(lw.getLogoOrder());
		this.setFileName(lw.getFileName());
		this.setUrl(url);
	}

	public LogoWallBean() {

	}


	public Integer getLogoOrder() {
		return logoOrder;
	}

	public void setLogoOrder(Integer logoOrder) {
		this.logoOrder = logoOrder;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
