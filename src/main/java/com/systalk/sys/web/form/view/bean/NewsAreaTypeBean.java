package com.systalk.sys.web.form.view.bean;

import com.systalk.sys.model.NewsAreaType;
import com.systalk.sys.web.form.view.bean.base.BaseBean;

/**
 * 最新消息分類 物件.
 * */
public class NewsAreaTypeBean extends BaseBean {

	// 分類代碼
	private String typeCode;

	// 分類名稱
	private String typeName;
	
	// 語系
	private String locale;

	public NewsAreaTypeBean () {
	}

	public NewsAreaTypeBean (NewsAreaType nt) {
		this.setSeq(nt.getNewsAreaTypeSeq());
		this.setTypeCode(nt.getTypeCode());
		this.setTypeName(nt.getTypeName());
		this.setLocale(nt.getLocale());
	}
	

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
}
