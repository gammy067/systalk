package com.systalk.sys.service.impl.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.systalk.sys.dao.AdSettingDao;
import com.systalk.sys.dao.ArticleClickCountDao;
import com.systalk.sys.dao.ArticleDao;
import com.systalk.sys.dao.ArticleSettingDao;
import com.systalk.sys.dao.CodeTableDao;
import com.systalk.sys.enums.CodeType;
import com.systalk.sys.enums.Status;
import com.systalk.sys.model.AdSetting;
import com.systalk.sys.model.Article;
import com.systalk.sys.model.ArticleClickCount;
import com.systalk.sys.model.ArticleSetting;
import com.systalk.sys.model.CodeTable;
import com.systalk.sys.util.JsonUtil;
import com.systalk.sys.util.SpringUtil;
import com.systalk.sys.web.form.component.combo.ComboBox;
import com.systalk.sys.web.form.component.combo.ComboItem;
import com.systalk.sys.web.form.view.bean.AdBean;
import com.systalk.sys.web.form.view.bean.ArticleBean;
import com.systalk.sys.web.form.view.bean.PopularArticleBean;
import com.systalk.sys.web.form.view.bean.TopArticleBean;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendArticleForm;

public class FrontendArticleServiceHelper {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 取得思拓研究室資料. 
	 * */
	public FrontendArticleForm getFrontendArticleForm() {
		FrontendArticleForm viewForm = new FrontendArticleForm();
		
		// 主題分類下拉選單
		viewForm.setTypeCombo(getTypeCombo());
		// 排序下拉選單
		viewForm.setSortCombo(getSortCombo());
		
		// 取得置頂文章List
		viewForm.setTopArticleList(getTopArticleList());
		// 取得熱門文章List
		viewForm.setPopularArticleList(getPopularArticleList());
		// 思拓研究室文章 資料
		viewForm.setArticleList(getArticleList());
		// 廣告版位設定
		viewForm.setAdList(getAdList());
		
		logger.debug("getFrontendArticleForm()" + JsonUtil.toJsonString(viewForm));
		return viewForm;
	}
	
	/**
	 * 主題分類下拉選單.
	 *
	 * @return the type combo
	 */
	private ComboBox getTypeCombo() {
		ComboBox combo = new ComboBox();
		combo.addDefaultComboItem();

		CodeTableDao dao = SpringUtil.getDao(CodeTableDao.class);
		List<CodeTable> codeList = dao.findByStatusAndType(Status.EFFECTIVE.getCode(), CodeType.ARTICLE.getCode());
		
		for(CodeTable code : codeList) {
			ComboItem item = new ComboItem(code.getCodeName(),String.valueOf(code.getCodeSeq()));
			combo.add(item);
		}
		return combo;
	}

	/**
	 * 排序下拉選單.
	 *
	 * @return the type combo
	 */
	private ComboBox getSortCombo() {
		ComboBox combo = new ComboBox();
		combo.addDefaultComboItem();

		CodeTableDao dao = SpringUtil.getDao(CodeTableDao.class);
		List<CodeTable> codeList = dao.findByStatusAndType(Status.EFFECTIVE.getCode(), CodeType.ARTICLE_SORT.getCode());
		
		for(CodeTable code : codeList) {
			ComboItem item = new ComboItem(code.getCodeName(),String.valueOf(code.getCodeSeq()));
			combo.add(item);
		}
		return combo;
	}

	/**
	 * 取得置頂文章List.
	 *
	 * @return the top article list
	 */
	private List<TopArticleBean> getTopArticleList() {
		// 置頂文章
		ArticleSettingDao dao = SpringUtil.getDao(ArticleSettingDao.class);
		List<ArticleSetting> topArticleList = dao.findByStatus(Status.EFFECTIVE.getCode());
		
		List<TopArticleBean> topArticleBeanList = new ArrayList<>();
		for(ArticleSetting as : topArticleList) {
			TopArticleBean bean = new TopArticleBean(as);
			topArticleBeanList.add(bean);
		}
		return topArticleBeanList;
	}
	
	/**
	 *  取得熱門文章List.
	 *
	 * @return the top article list
	 */
	private List<PopularArticleBean> getPopularArticleList() {
		// 置頂文章
		ArticleClickCountDao dao = SpringUtil.getDao(ArticleClickCountDao.class);
		List<ArticleClickCount> popularArticleList = dao.findTop5ByOrderByClickCountDesc();
		
		List<PopularArticleBean> popularArticleBeanList = new ArrayList<>();

		for(ArticleClickCount acc : popularArticleList) {
			PopularArticleBean bean = new PopularArticleBean(acc);
			popularArticleBeanList.add(bean);
		}
		return popularArticleBeanList;
	}

	/**
	 *  取得思拓研究室文章List.
	 *
	 * @return the top article list
	 */
	private List<ArticleBean> getArticleList() {
		// 思拓研究室文章
		ArticleDao dao = SpringUtil.getDao(ArticleDao.class);
		// 查詢文章 上架日期排序
		List<Article> articleList = dao.findByStatusOrderByPublishingDateDesc(Status.EFFECTIVE.getCode());
		
		List<ArticleBean> articleBeanList = new ArrayList<>();
		for(Article ac : articleList) {
			ArticleBean bean = new ArticleBean(ac);
			articleBeanList.add(bean);
		}
		return articleBeanList;
	}

	/**
	 *  取得廣告版位設定.
	 *
	 * @return the top article list
	 */
	private List<AdBean> getAdList() {
		AdSettingDao dao = SpringUtil.getDao(AdSettingDao.class);
		List<AdSetting> adList = dao.findByStatus(Status.EFFECTIVE.getCode());
		
		List<AdBean> adBeanList = new ArrayList<>();
		for(AdSetting ad : adList) {
			AdBean bean = new AdBean(ad);
			adBeanList.add(bean);
		}
		return adBeanList;
	}

	private FrontendArticleServiceHelper() {
	}

	private static class Loader {
		private static final FrontendArticleServiceHelper instance = new FrontendArticleServiceHelper();
	}

	public static FrontendArticleServiceHelper getInstance() {
		return Loader.instance;
	}
}
