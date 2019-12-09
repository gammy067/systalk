package com.systalk.sys.web.form.view.vo.news;

import java.io.Serializable;

/**
 * 後臺 - 新聞分類刪除清單.
 * */
public class DeleteTypeList implements Serializable {

	private Integer areaTypeSeq;
	private String locale;

	public Integer getAreaTypeSeq() {
		return areaTypeSeq;
	}
	public void setAreaTypeSeq(Integer areaTypeSeq) {
		this.areaTypeSeq = areaTypeSeq;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
}
