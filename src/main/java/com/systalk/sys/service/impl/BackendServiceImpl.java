package com.systalk.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systalk.sys.dao.BannerSettingDao;
import com.systalk.sys.dao.LogowallDao;
import com.systalk.sys.dao.NewsAreaDao;
import com.systalk.sys.dao.NewsAreaTypeDao;
import com.systalk.sys.dao.SuccessCaseDao;
import com.systalk.sys.dao.VideoSettingDao;
import com.systalk.sys.enums.Status;
import com.systalk.sys.enums.SuccessCaseStatus;
import com.systalk.sys.model.BannerSetting;
import com.systalk.sys.model.Logowall;
import com.systalk.sys.model.NewsArea;
import com.systalk.sys.model.NewsAreaType;
import com.systalk.sys.model.SuccessCase;
import com.systalk.sys.model.VideoSetting;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.util.DisplayUtil;
import com.systalk.sys.util.MessageUtil;
import com.systalk.sys.web.form.view.bean.BannerSettingBean;
import com.systalk.sys.web.form.view.bean.LogoWallBean;
import com.systalk.sys.web.form.view.bean.NewsAreaBean;
import com.systalk.sys.web.form.view.bean.NewsAreaTypeBean;
import com.systalk.sys.web.form.view.bean.SuccessCaseBean;
import com.systalk.sys.web.form.view.bean.VideoSettingBean;

/**
 * 後臺維護serviceImpl.
 */
@Transactional(rollbackFor = Exception.class)
@Service("BackendService")
public class BackendServiceImpl implements BackendService {
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BannerSettingDao bannerSettingDao;
	
	@Autowired
	private NewsAreaDao newsAreaDao;
	
	@Autowired
	private NewsAreaTypeDao newsAreaTypeDao;
	
	@Autowired
	private VideoSettingDao videoSettingDao;
	
	@Autowired
	private SuccessCaseDao successCaseDao;
	
	@Autowired
	private LogowallDao logowallDao;
	
	@Value("${banner.image.folder.path}")
	private String bannerFolderPath;
	
	@Value("${logoWall.image.folder.path}")
	private String logoWallFolderPath;
	
	@Value("${successCase.image.folder.path}")
	private String successCaseFolderPath;

	@Value("${newsArea.image.folder.path}")
	private String newsAreaFolderPath;
	
	@Autowired
	HttpServletRequest request;
	
	@Resource
	private MessageUtil messageUtil;
	
	/**
	 * 查詢首頁輪播設定.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	public List<BannerSettingBean> queryBannerSetting() throws Exception {
		List<BannerSetting> bannerSettingList = bannerSettingDao.findAll(new Sort(Sort.Direction.ASC, "publishingDate"));
		
		List<BannerSettingBean> bannerSettingBeanList = new ArrayList<>();
		for (BannerSetting setting : bannerSettingList) {
			BannerSettingBean bean = new BannerSettingBean(setting);
			
			if(Status.ASSIGN.getCode().equals(bean.getStatus())) {
				// 草稿案件顯示處理.
				Boolean draft = draftItemDisplayHandle(bean.getPushingDate(), bean.getInvalidDate());
				bean.setDraft(draft);
			}
			
			// 上架/ 指定時間上架 文字顯示
			bean.setDisplayStatus(DisplayUtil.getDisplayStatus(bean.getStatus()));

			bannerSettingBeanList.add(bean);
		}
		logger.debug(">>>bannerSettingBeanList = " + bannerSettingBeanList);
		return bannerSettingBeanList;
	}


	/**
	 *  查詢 seq 輪播圖片編輯頁. 
	 *
	 * @param seq the seq
	 * @return the banner setting bean
	 * @throws Exception the exception
	 */
	@Override
	public BannerSettingBean queryBannerSettingBySeq(Integer seq) throws Exception {
		BannerSettingBean bean = new BannerSettingBean(bannerSettingDao.findOne(seq));
		String urlPath = request.getServletContext().getContextPath() + bannerFolderPath;

		// 圖片檔案路徑
//		SpringUtil.getResourcePath(bannerFolderPath);
		if(StringUtils.isNotEmpty(bean.getFileName())) {
			
			bean.setUrl(urlPath + bean.getFileName());
		}
		
		if(StringUtils.isNotEmpty(bean.getFileName2())) {
			// mobile 圖片檔案路徑
			bean.setUrl2(urlPath + bean.getFileName2());
		}

		return bean;
	}


	/**
	 * 查詢新聞專區設定.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	public List<NewsAreaBean> queryNewsArea() throws Exception {
		List<NewsArea> newsAreaList = newsAreaDao.findAll(new Sort(Sort.Direction.ASC, "updateDate"));
		
		List<NewsAreaBean> newsAreaBeanList = new ArrayList<>();
		for (NewsArea newsArea : newsAreaList) {
			NewsAreaBean bean = new NewsAreaBean(newsArea);
			
			if(Status.ASSIGN.getCode().equals(bean.getStatus())) {
				// 草稿案件顯示處理.
				Boolean draft = draftItemDisplayHandle(bean.getPushingDate(), bean.getInvalidDate());
				bean.setDraft(draft);
			}

			// 上架/ 指定時間上架 文字顯示
			bean.setDisplayStatus(DisplayUtil.getDisplayStatus(bean.getStatus()));

			newsAreaBeanList.add(bean);
		}
		return newsAreaBeanList;
	}


	/**
	 * 查詢 seq 新聞專區編輯頁. 
	 *
	 * @param seq the seq
	 * @return the news area bean
	 * @throws Exception the exception
	 */
	@Override
	public NewsAreaBean queryNewsAreaBySeq(Integer seq) throws Exception {
		NewsAreaBean bean = new NewsAreaBean(newsAreaDao.findOne(seq));

		// 圖片檔案路徑
		if(StringUtils.isNotEmpty(bean.getFileName())) {
			bean.setUrl(request.getServletContext().getContextPath() + newsAreaFolderPath + bean.getFileName());
		}

		return bean;
	}
			
	/**
	 * 查詢最新消息分類.
	 *
	 * @return the list
	 */
	@Override
	public Map<Locale, List<NewsAreaTypeBean>> queryNewsAreaType() {
		List<NewsAreaType> newsAreaTypeList = newsAreaTypeDao.findAll(new Sort(Sort.Direction.ASC, "newsAreaTypeSeq"));
		Map<Locale, List<NewsAreaTypeBean>> localeNewsTypeMap = new HashMap<>();
		List<NewsAreaTypeBean> newsAreaTypeListTw = new ArrayList<>();
		List<NewsAreaTypeBean> newsAreaTypeListEn = new ArrayList<>();
		
		
		List<NewsAreaTypeBean> newsAreaTypeBeanList = new ArrayList<>();
		for(NewsAreaType nt : newsAreaTypeList) {
			NewsAreaTypeBean bean = new NewsAreaTypeBean(nt);
			// 中文分類
			if(StringUtils.equals(Locale.TAIWAN.toString(), nt.getLocale())  ) {
				newsAreaTypeListTw.add(bean);
			}
			// 英文分類
			if(StringUtils.equals(Locale.US.toString(), nt.getLocale())  ) {
				newsAreaTypeListEn.add(bean);
			}
			newsAreaTypeBeanList.add(bean);
		}
		localeNewsTypeMap.put(Locale.TAIWAN, newsAreaTypeListTw);
		localeNewsTypeMap.put(Locale.US, newsAreaTypeListEn);
		return localeNewsTypeMap;
	}


	/**
	 * 查詢行銷影片設定.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	public List<VideoSettingBean> queryVideoSetting() throws Exception {
		List<VideoSetting> videoSettinList = videoSettingDao.findAll(new Sort(Sort.Direction.ASC, "publishingDate"));
		
		List<VideoSettingBean> videoSettingBeanList = new ArrayList<>();
		for (VideoSetting videoSetting : videoSettinList) {
			VideoSettingBean bean = new VideoSettingBean(videoSetting);
			
			// 草稿/上架中: 只有分上架或刪除, 所以只會顯示上架中

			// 上架/ 指定時間上架 文字顯示
			bean.setDisplayStatus(DisplayUtil.getDisplayStatus(bean.getStatus()));

			videoSettingBeanList.add(bean);
		}
		return videoSettingBeanList;
	}

	/**
	 * 查詢 seq 行銷影片編輯頁. 
	 *
	 * @param seq the seq
	 * @return the video setting bean
	 * @throws Exception the exception
	 */
	@Override
	public VideoSettingBean queryVideoSettingBySeq(Integer seq) throws Exception {
		VideoSettingBean bean = new VideoSettingBean(videoSettingDao.findOne(seq));
		return bean;
	}

	/**
	 * 查詢品牌牆.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	public List<LogoWallBean> queryLogoWall() throws Exception {
		List<Logowall> logoWallList = logowallDao.findAll(new Sort(Sort.Direction.ASC, "publishingDate"));
		
		List<LogoWallBean> logoWallBeanList = new ArrayList<>();
		for(Logowall logoWall : logoWallList) {
			LogoWallBean bean = new LogoWallBean(logoWall);
			
			// 草稿/上架中: 只有分上架或刪除, 所以只會顯示上架中
			
			// 上架/ 指定時間上架 文字顯示
			bean.setDisplayStatus(DisplayUtil.getDisplayStatus(bean.getStatus()));
	
			logoWallBeanList.add(bean);
		}
		return logoWallBeanList;
	}
	
	/**
	 * 查詢 seq 品牌牆編輯頁.
	 *
	 * @param seq the seq
	 * @return the logo wall bean
	 * @throws Exception the exception
	 */
	@Override
	public LogoWallBean queryLogoWallBySeq(Integer seq) throws Exception {
		LogoWallBean bean = new LogoWallBean(logowallDao.findOne(seq));
		// 圖片檔案路徑
		bean.setUrl(request.getServletContext().getContextPath() + logoWallFolderPath + bean.getFileName());
		return bean;
	}

	/**
	 * 查詢品牌牆 查詢條件:依優先順序排序.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	public List<LogoWallBean> queryLogoWallBySort(Sort sort) throws Exception {
		List<Logowall> logoWallList = logowallDao.findAll(sort);
		
		List<LogoWallBean> logoWallBeanList = new ArrayList<>();
		for(Logowall logoWall : logoWallList) {
			LogoWallBean bean = new LogoWallBean(logoWall);
			
			// 圖片檔案路徑
			if(StringUtils.isNotEmpty(bean.getFileName())) {
				bean.setUrl(request.getServletContext().getContextPath() + logoWallFolderPath + bean.getFileName());
			}
			
			logoWallBeanList.add(bean);
		}
		return logoWallBeanList;
	}

	/**
	 * 成功案例查詢.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	public List<SuccessCaseBean> querySuccessCase() throws Exception {
		List<SuccessCase> successCaseList = successCaseDao.findAll(new Sort(Sort.Direction.ASC, "publishingDate"));
		
		List<SuccessCaseBean> successCaseBeanList = new ArrayList<>();
		for(SuccessCase successCase : successCaseList) {
			SuccessCaseBean bean = new SuccessCaseBean(successCase);
			
			
			// SuccessCaseStatus:0 下架 -> 草稿案件
			if(SuccessCaseStatus.INVALID.getCode().equals(successCase.getStatus())) {
				bean.setDraft(true);
			}

			// 成功案例 上架/下架 i18n文字顯示.
			bean.setDisplayStatus(DisplayUtil.getDisplaySuccessCaseStatus(bean.getStatus()));
	
			successCaseBeanList.add(bean);
		}
		logger.debug(">>>successCaseBeanList = " + successCaseBeanList);
		return successCaseBeanList;
	}
	
	/**
	 * 查詢 seq 成功案例編輯頁.
	 *
	 * @param seq the seq
	 * @return the success case bean
	 * @throws Exception the exception
	 */
	@Override
	public SuccessCaseBean querySuccessCaseBySeq(Integer seq) throws Exception {
		SuccessCaseBean bean = new SuccessCaseBean(successCaseDao.findOne(seq));
		
		// 圖片檔案路徑
		if(StringUtils.isNotEmpty(bean.getFileName())) {
			bean.setUrl(request.getServletContext().getContextPath() + successCaseFolderPath + bean.getFileName());
		}
		return bean;
	}
	
	/**
	 * 草稿案件顯示處理.
	 *
	 * @param invalidDate the invalid date
	 * @param publishingDate the publishing date
	 * @return the boolean
	 */
	private Boolean draftItemDisplayHandle(Date publishingDate, Date invalidDate) {
		Boolean draft = false;
		Date today = new Date();
		// 上架日 > 今天 為草稿案件
		if(publishingDate.after(today)) {
			draft = true;
		}

		// 失效日 <= 今天 為草稿案件
		if(invalidDate != null) {
			if(!invalidDate.after(today)) {
				draft = true;
			}
		}
		return draft;
	}
}
