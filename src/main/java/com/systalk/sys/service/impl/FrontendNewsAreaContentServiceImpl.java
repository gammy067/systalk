package com.systalk.sys.service.impl;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systalk.sys.dao.NewsAreaDao;
import com.systalk.sys.model.NewsArea;
import com.systalk.sys.service.FrontendNewsAreaContentService;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.web.form.view.bean.NewsAreaBean;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendNewsAreaContentForm;

/**
 * 新聞專區 serviceImpl.
 */
@Transactional(rollbackFor = Exception.class)
@Service("FrontendNewsAreaContentService")
public class FrontendNewsAreaContentServiceImpl implements FrontendNewsAreaContentService {
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private HttpServletRequest request;

	@Autowired
	NewsAreaDao newsAreaDao;
	
	@Value("${newsArea.image.folder.path}")
	private String newsAreaFolderPath;

	/**
	 * 最新消息內文viewForm.
	 *
	 * @param seq the seq
	 * @return the news area bean
	 * @throws Exception the exception
	 */
	@Override
	public FrontendNewsAreaContentForm getNewsAreaContentForm(Integer seq) throws Exception {
		FrontendNewsAreaContentForm viewForm = new FrontendNewsAreaContentForm();
		NewsArea news = newsAreaDao.findOne(seq);
		
		// 取得網路資源路徑 (顯示圖片)
		String requestUrl = CommonUtil.getBaseURL(request);
		NewsAreaBean bean = new NewsAreaBean(news, CommonUtil.generalFileUrl(requestUrl + newsAreaFolderPath, news.getFileName()));
		
		Locale currentLocale = LocaleContextHolder.getLocale();
		// 顯示當前語系的分類
		if(StringUtils.equals(Locale.TAIWAN.toString(), currentLocale.toString())) {
			bean.setDisplayType(CommonUtil.getNewsAreaTypeDisplay(bean.getTypeSeq(), currentLocale.toString()));
		}
		
		if(StringUtils.equals(Locale.US.toString(), currentLocale.toString())) {
			bean.setDisplayType(CommonUtil.getNewsAreaTypeDisplay(bean.getTypeSeqEn(), currentLocale.toString()));
		}

		viewForm.setNewsAreaBean(bean);
		return viewForm;
	}
}
