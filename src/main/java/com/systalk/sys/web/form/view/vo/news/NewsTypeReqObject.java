package com.systalk.sys.web.form.view.vo.news;

import java.io.Serializable;
import java.util.List;

/**
 * 後臺 - 新聞專區分類VO.
 * */
public class NewsTypeReqObject implements Serializable {

	private List<NewsTypeVo> newsTypeVoList;
	
	private String locale;
	
	private List<DeleteTypeList> deleteTypeList;

	public List<NewsTypeVo> getNewsTypeVoList() {
		return newsTypeVoList;
	}

	public void setNewsTypeVoList(List<NewsTypeVo> newsTypeVoList) {
		this.newsTypeVoList = newsTypeVoList;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public List<DeleteTypeList> getDeleteTypeList() {
		return deleteTypeList;
	}

	public void setDeleteTypeList(List<DeleteTypeList> deleteTypeList) {
		this.deleteTypeList = deleteTypeList;
	}
}
