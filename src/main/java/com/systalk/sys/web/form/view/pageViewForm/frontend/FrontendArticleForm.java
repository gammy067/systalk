package com.systalk.sys.web.form.view.pageViewForm.frontend;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.systalk.sys.web.form.component.combo.ComboBox;
import com.systalk.sys.web.form.view.bean.AdBean;
import com.systalk.sys.web.form.view.bean.ArticleBean;
import com.systalk.sys.web.form.view.bean.PopularArticleBean;
import com.systalk.sys.web.form.view.bean.TopArticleBean;

/** 前臺- 思拓研究室 -顯示 form. */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FrontendArticleForm {
	/** 主題分類下拉選單. */
	private ComboBox typeCombo;
	
	/** 排序 下拉選單. */
	private ComboBox sortCombo;

	/** 思拓研究室 - 置頂文章文章 list物件 (輪播). */
	private List<TopArticleBean> topArticleList;
	
	/** 思拓研究室 -熱門文章 list物件. */
	private List<PopularArticleBean> popularArticleList;
	
	/** 思拓研究室 -文章 list物件. */
	private List<ArticleBean> articleList;
	
	/** 思拓研究室 - 廣告版位設定. */
	private List<AdBean> adList;


	public List<TopArticleBean> getTopArticleList() {
		return topArticleList;
	}

	public void setTopArticleList(List<TopArticleBean> topArticleList) {
		this.topArticleList = topArticleList;
	}

	public List<PopularArticleBean> getPopularArticleList() {
		return popularArticleList;
	}

	public void setPopularArticleList(List<PopularArticleBean> popularArticleList) {
		this.popularArticleList = popularArticleList;
	}

	public List<ArticleBean> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleBean> articleList) {
		this.articleList = articleList;
	}

	public List<AdBean> getAdList() {
		return adList;
	}

	public void setAdList(List<AdBean> adList) {
		this.adList = adList;
	}

	public ComboBox getTypeCombo() {
		return typeCombo;
	}

	public void setTypeCombo(ComboBox typeCombo) {
		this.typeCombo = typeCombo;
	}

	public ComboBox getSortCombo() {
		return sortCombo;
	}

	public void setSortCombo(ComboBox sortCombo) {
		this.sortCombo = sortCombo;
	}
}
