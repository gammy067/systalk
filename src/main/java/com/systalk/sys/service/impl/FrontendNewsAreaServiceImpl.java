package com.systalk.sys.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systalk.sys.dao.NewsAreaDao;
import com.systalk.sys.model.NewsArea;
import com.systalk.sys.service.FrontendNewsAreaService;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.web.form.component.pageObject.PageObject;
import com.systalk.sys.web.form.view.bean.NewsAreaBean;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendNewsAreaForm;

/**
 * 新聞專區 serviceImpl.
 */
@Transactional(rollbackFor = Exception.class)
@Service("FrontendNewsAreaService")
public class FrontendNewsAreaServiceImpl implements FrontendNewsAreaService {
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private HttpServletRequest request;
	
	/** 取得最新消息一頁最多顯示數 (分頁).*/
	private int PAGE_MAX_COUNT = CommonUtil.getNewsPageMaxCount();

	@Autowired
	NewsAreaDao newsAreaDao;
	
	@Value("${newsArea.image.folder.path}")
	private String newsAreaFolderPath;
	
	/**
	 * 取得初始新聞專區List.
	 *
	 * @return the top article list
	 */
	@Override
	public FrontendNewsAreaForm initNewsAreaList(FrontendNewsAreaForm viewForm, int startPage) throws Exception {
		// 當前語系
		String currentLocale = LocaleContextHolder.getLocale().toString();
		// PAGE_MAX_COUNT新聞專區每頁顯示最大筆數
		Pageable pageable = new PageRequest(startPage -1, PAGE_MAX_COUNT);
		// 前臺最新消息初始查詢. 依上架日期排序 (全部)
		Page<NewsArea> page = newsAreaDao.initNewsAreaQuery(pageable);
		
		// 設置分頁物件
		viewForm.setPageObject(new PageObject(startPage, page.getTotalPages()));

		// 最新消息資料
		List<NewsAreaBean> newsAreaBeanList = new ArrayList<>();

		// Http請求的基本路徑.
		String requestUrl = CommonUtil.getBaseURL(request);

		List<NewsArea> newsList = page.getContent();
		for(NewsArea news : newsList) {
			NewsAreaBean bean = new NewsAreaBean(news, CommonUtil.generalFileUrl(requestUrl + newsAreaFolderPath, news.getFileName()));

			// 顯示當前語系的分類
			if(StringUtils.equals(Locale.TAIWAN.toString(), currentLocale)) {
				bean.setDisplayType(CommonUtil.getNewsAreaTypeDisplay(bean.getTypeSeq(), currentLocale));
			}
			
			if(StringUtils.equals(Locale.US.toString(), currentLocale)) {
				bean.setDisplayType(CommonUtil.getNewsAreaTypeDisplay(bean.getTypeSeqEn(), currentLocale));
			}
			
			// 上架日期顯示
			bean.setDisplayPushingDate(DateFormatUtils.format(news.getPublishingDate(), CommonUtil.formatyyyy_MM_dd));

			newsAreaBeanList.add(bean);
		}
		viewForm.setNewsAreaBeanList(newsAreaBeanList);
		
		// 主題分類下拉選單 (前後端共用 寫成util)
		
		// 顯示對應語系 分類下拉
		viewForm.setTypeCombo(CommonUtil.getNewsTypeCombo(currentLocale));
		// 年分下拉選單
		viewForm.setDateCombo(CommonUtil.getDateCombo());
		return viewForm;
	}

	/**
	 *  最新消息 查詢(status:上架).
	 *
	 * @param typeSeq the type seq
	 * @param dateYear the date year
	 * @param searchText the search text
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	public FrontendNewsAreaForm queryNewsAreaByCondition(FrontendNewsAreaForm viewForm, int startPage) throws Exception {
		// 當前語系
		String currentLocale = LocaleContextHolder.getLocale().toString();
		// PAGE_MAX_COUNT新聞專區每頁顯示最大筆數
		Pageable pageable = new PageRequest(startPage -1, PAGE_MAX_COUNT);

		// 取得一整年區間 (ex: 2018 < = y < 2019)
		Date startDate = getQueryStartDate(viewForm.getDateYear());
		Date endDate = DateUtils.addYears(startDate, 1);
		
		// 新聞專區資料
		List<NewsAreaBean> newsAreaBeanList = new ArrayList<>();
		// Http請求的基本路徑.
		String requestUrl = CommonUtil.getBaseURL(request);
		
		Page<NewsArea> page = newsAreaDao.queryNewsAreaByCondition(viewForm.getTypeSeq(), startDate, endDate, viewForm.getSearchText(), currentLocale, pageable);
		// 設置分頁物件
		viewForm.setPageObject(new PageObject(startPage, page.getTotalPages() == 0 ? 1 : page.getTotalPages()));

		List<NewsArea> newsList = page.getContent();
		for(NewsArea news : newsList) {
			NewsAreaBean bean = new NewsAreaBean(news, CommonUtil.generalFileUrl(requestUrl + newsAreaFolderPath, news.getFileName()));

			// 顯示當前語系的分類
			if(StringUtils.equals(Locale.TAIWAN.toString(), currentLocale)) {
				bean.setDisplayType(CommonUtil.getNewsAreaTypeDisplay(bean.getTypeSeq(), currentLocale));
			}
			
			if(StringUtils.equals(Locale.US.toString(), currentLocale)) {
				bean.setDisplayType(CommonUtil.getNewsAreaTypeDisplay(bean.getTypeSeqEn(), currentLocale.toString()));
			}
			
			// 上架日期顯示
			bean.setDisplayPushingDate(DateFormatUtils.format(news.getPublishingDate(), CommonUtil.formatyyyy_MM_dd));

			newsAreaBeanList.add(bean);
		}
		
		viewForm.setNewsAreaBeanList(newsAreaBeanList);
		
		// 顯示對應語系 分類下拉
		viewForm.setTypeCombo(CommonUtil.getNewsTypeCombo(currentLocale));
		viewForm.setDateCombo(CommonUtil.getDateCombo());
		
		return viewForm;
	}
	
	/**
	 * 查詢 起始年份.
	 *
	 * @param dateYear the date year
	 * @return the query start date
	 */
	private Date getQueryStartDate(Integer dateYear) {
		Calendar cal = Calendar.getInstance();
		if(dateYear == null) {
			cal.setTime(new Date());
			dateYear = cal.get(Calendar.YEAR);
		}
		cal.set(Calendar.YEAR, dateYear);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date startDate = cal.getTime();
		return startDate;
	}
}
