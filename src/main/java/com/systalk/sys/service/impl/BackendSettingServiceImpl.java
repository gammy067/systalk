package com.systalk.sys.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.systalk.sys.dao.BannerSettingDao;
import com.systalk.sys.dao.CodeTableDao;
import com.systalk.sys.dao.LocaleTranslationDao;
import com.systalk.sys.dao.LogowallDao;
import com.systalk.sys.dao.NewsAreaDao;
import com.systalk.sys.dao.NewsAreaTypeDao;
import com.systalk.sys.dao.NewsAreaTypeRefDao;
import com.systalk.sys.dao.SuccessCaseDao;
import com.systalk.sys.dao.VideoSettingDao;
import com.systalk.sys.enums.FunctionType;
import com.systalk.sys.enums.Status;
import com.systalk.sys.model.BannerSetting;
import com.systalk.sys.model.LocaleTranslation;
import com.systalk.sys.model.Logowall;
import com.systalk.sys.model.NewsArea;
import com.systalk.sys.model.NewsAreaType;
import com.systalk.sys.model.NewsAreaTypeRef;
import com.systalk.sys.model.SuccessCase;
import com.systalk.sys.model.User;
import com.systalk.sys.model.VideoSetting;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.service.impl.helper.BackendSettingServiceHelper;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.util.EditDocUtil;
import com.systalk.sys.web.form.view.pageViewForm.backend.EditNewsAreaViewForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.SettingForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;
import com.systalk.sys.web.form.view.vo.news.DeleteTypeList;
import com.systalk.sys.web.form.view.vo.news.NewsTypeVo;

/**
 * 後臺維護設定serviceImpl.
 */
@Transactional(rollbackFor = Exception.class)
@Service("BackendSettingService")
public class BackendSettingServiceImpl implements BackendSettingService {
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	private BannerSettingDao bannerSettingDao;
	
	@Autowired
	private VideoSettingDao videoSettingDao;
	
	@Autowired
	private LogowallDao logowallDao;
	
	@Autowired
	private NewsAreaDao newsAreaDao;
	
	@Autowired
	private NewsAreaTypeDao newsAreaTypeDao;
	
	@Autowired
	private NewsAreaTypeRefDao newsAreaTypeRefDao;
	
	@Autowired
	private CodeTableDao codeTableDao;
	
	@Autowired
	private SuccessCaseDao successCaseDao;
	
	@Autowired
	private LocaleTranslationDao localeTranslationDao;
	
	// 支援的語系 list.
	private static final List<Locale> LOCALE_LIST = CommonUtil.getLocaleList();

	// File/banner/images/
	@Value("${banner.image.folder.path}")
	private String bannerFolderPath;
	
	// File/logoWall/images/
	@Value("${logoWall.image.folder.path}")
	private String logoWallFolderPath;

	// File/successCase/images/
	@Value("${successCase.image.folder.path}")
	private String successCaseFolderPath;
	
	// File/newsArea/images/
	@Value("${newsArea.image.folder.path}")
	private String newsAreaFolderPath;

	// 編輯器圖片位置
	// File/backend/editor/images/
	@Value("${editorUploadImage.folder.path}")
	private String uploadImageFolderPathPath;
	

	/** 
	 *  輪播圖片設定 (圖片.標題.文字.狀態) 儲存. 
	 * @throws Exception */
	@Override
	public void doBannerSettingSaveAction(UploadFileForm uploadFileForm, User sysUser) throws Exception {
		BannerSetting bannerSetting = new BannerSetting();
		Date today = new Date();
		MultipartFile file = null;
		
		String fileName = uploadFileForm.getFileName();
		
		// 編輯 update by pk -> bannerSeq
		if(uploadFileForm.getSeq() != null) {
			bannerSetting = bannerSettingDao.findOne(uploadFileForm.getSeq());
		} else {
			// 新增 建立日期
			bannerSetting.setCreateDate(today);
		}

		if (!uploadFileForm.getFile().isEmpty()) {
			file = uploadFileForm.getFile();
			if (file.getSize() > 0L) {
				// server 真實路徑
				String realPath = request.getServletContext().getRealPath(File.separator) + File.separator;
				// 上傳資料夾路徑
				String folderPath = bannerFolderPath;
				
				fileName = EditDocUtil.uploadfile(file, realPath + folderPath, FilenameUtils.getBaseName(file.getOriginalFilename()));
				bannerSetting.setFileName(fileName);
			}
		}
		// 行動裝置圖片上傳
		if (!uploadFileForm.getFile2().isEmpty()) {
			file = uploadFileForm.getFile2();
			if (file.getSize() > 0L) {
				// server 真實路徑
				String realPath = request.getServletContext().getRealPath(File.separator) + File.separator;
				// 上傳資料夾路徑
				String folderPath = bannerFolderPath;
				
				fileName = EditDocUtil.uploadfile(file, realPath + folderPath, FilenameUtils.getBaseName(file.getOriginalFilename()) + "_mobile");
				bannerSetting.setFileName2(fileName);
			}
		}
// 		改存多語系table
//		bannerSetting.setBannerTitle(uploadFileForm.getTitle());
//		bannerSetting.setContent(uploadFileForm.getContent());
		bannerSetting.setStatus(uploadFileForm.getStatus());

		// 立即上架:
		if(Status.EFFECTIVE.getCode().equals(uploadFileForm.getStatus())) {
			// 立即上架: 上架日期為今日
			bannerSetting.setPublishingDate(new Date());
		} else {
			// 上架日期字串格式 (非必填)
			bannerSetting.setPublishingDate(CommonUtil.getCommonDate(uploadFileForm.getPushingDate()));
		}

		// 下架日期字串格式 (非必填)
		bannerSetting.setInvalidDate(CommonUtil.getCommonDate(uploadFileForm.getInvalidDate()));
		
		bannerSetting.setUpdateDate(today);
		bannerSetting.setUser(sysUser);
		// 功能分類
		bannerSetting.setFunctionType(FunctionType.BANNER.getCode());

		bannerSetting = bannerSettingDao.save(bannerSetting);
		
		// 儲存多語系欄位資料
		saveLocaleTransData(FunctionType.BANNER, uploadFileForm.getSettingForm(), bannerSetting.getBannerSeq());

		logger.debug(">>> doBannerSettingSaveAction success" + bannerSetting);
	}

	/**
	 * 輪播圖片後臺管理 刪除.
	 *
	 * @param seq the seq
	 * @throws Exception the exception
	 */
	@Override
	public void doDeleteBannerAction(Integer seq) throws Exception {
		bannerSettingDao.delete(seq);
		localeTranslationDao.deleteByRefSeqAndFunctionType(seq, FunctionType.BANNER.getCode());
	}
	
	/**
	 *  新聞專區 後臺管理 儲存.
	 *
	 * @param newsSeq the news seq
	 * @param newsTitle the news title
	 * @param status the status
	 * @param content the content
	 * @param sysUser the sys user
	 * @throws Exception the exception
	 */
	@Override
	public void doNewsAreaSaveAction(EditNewsAreaViewForm viewForm, User sysUser) throws Exception {
		NewsArea newsArea = new NewsArea();
		Date today = new Date();
		MultipartFile file = null;
		UploadFileForm uploadFileForm = viewForm.getUploadFileForm();
		String fileName = uploadFileForm.getFileName();

		// 編輯  update by pk -> newsAreaPK
		if (uploadFileForm.getSeq() != null) {
			newsArea = newsAreaDao.findOne(uploadFileForm.getSeq());
		} else {
			newsArea.setCreateDate(today);
		}

		if (!uploadFileForm.getFile().isEmpty()) {
			file = uploadFileForm.getFile();
			if (file.getSize() > 0L) {
				// server 真實路徑
				String realPath = request.getServletContext().getRealPath(File.separator) + File.separator;
				// 上傳資料夾路徑
				String folderPath = newsAreaFolderPath;
				
				fileName = EditDocUtil.uploadfile(file, realPath + folderPath, FilenameUtils.getBaseName(file.getOriginalFilename()));
				newsArea.setFileName(fileName);
			}
		}

		newsArea.setStatus(uploadFileForm.getStatus());
		
		// 立即上架:
		if(Status.EFFECTIVE.getCode().equals(uploadFileForm.getStatus())) {
			// 立即上架: 上架日期為今日
			newsArea.setPublishingDate(new Date());
		} else {
			// 上架日期字串格式 (非必填)
			newsArea.setPublishingDate(CommonUtil.getCommonDate(uploadFileForm.getPushingDate()));
		}

		// 下架日期字串格式 (非必填)
		newsArea.setInvalidDate(CommonUtil.getCommonDate(uploadFileForm.getInvalidDate()));
		
		newsArea.setUpdateDate(today);
		// 功能分類
		newsArea.setFunctionType(FunctionType.NEWS.getCode());
		newsArea = newsAreaDao.save(newsArea);
		
		// 儲存多語系欄位資料
		saveLocaleTransData(FunctionType.NEWS, uploadFileForm.getSettingForm(), newsArea.getNewsSeq());
		
		// 儲存分類關聯表
		setNewsRefType(viewForm, newsArea.getNewsSeq());
	}
	
	/**
	 * 儲存分類關聯表.
	 *
	 * @param viewForm the view form
	 * @param newsSeq the news seq
	 */
	private void setNewsRefType(EditNewsAreaViewForm viewForm, Integer newsSeq) {
		// 儲存多語系分類關聯表
		List<NewsAreaTypeRef> refList = newsAreaTypeRefDao.findByRefNewsSeq(newsSeq);
		// 新增
		if(CollectionUtils.isEmpty(refList) ) {
			refList = new ArrayList<>();
			NewsAreaTypeRef newsAreaTypeRef = new NewsAreaTypeRef();
			newsAreaTypeRef.setRefNewsSeq(newsSeq);
			newsAreaTypeRef.setNewsTypeSeq(viewForm.getTypeSeq());
			newsAreaTypeRef.setLocale(Locale.TAIWAN.toString());
			
			NewsAreaTypeRef newsAreaTypeRefEn = new NewsAreaTypeRef();
			newsAreaTypeRefEn.setRefNewsSeq(newsSeq);
			newsAreaTypeRefEn.setNewsTypeSeq(viewForm.getTypeSeqEn());
			newsAreaTypeRefEn.setLocale(Locale.US.toString());
			
			refList.add(newsAreaTypeRef);
			refList.add(newsAreaTypeRefEn);
		// 編輯
		} else {
			for(NewsAreaTypeRef ref : refList) {
				if(StringUtils.equals(Locale.TAIWAN.toString(), ref.getLocale())) {
					ref.setNewsTypeSeq(viewForm.getTypeSeq());
				}
				
				if(StringUtils.equals(Locale.US.toString(), ref.getLocale())) {
					ref.setNewsTypeSeq(viewForm.getTypeSeqEn());
				}
			}
		}
		newsAreaTypeRefDao.save(refList);
	}
	
	/**
	 * 最新消息分類 後臺管理 儲存.
	 *
	 * @param newsAreaTypeList the news area type list
	 * @throws Exception the exception
	 */
	@Override
	public String doNewsAreaTypeSaveAction(List<NewsTypeVo> newsAreaTypeList, List<DeleteTypeList> deleteTypeList, String locale) throws Exception {
		List<NewsAreaType> insertList = new ArrayList<>();
		List<NewsAreaType> updateList = new ArrayList<>();
		// 編輯後的所有舊分類 seq 清單
		List<Integer> editSeqList = new ArrayList<>();

		// 處理畫面刪除已存在項目
		if(CollectionUtils.isNotEmpty(deleteTypeList)) {
			// 查詢分類是否使用中
			List<NewsAreaTypeRef> refList = newsAreaTypeRefDao.findByLocale(locale);
			
			List<Integer> deleteSeqs = deleteTypeList.stream().map(n -> n.getAreaTypeSeq()).collect(Collectors.toList());
			for (NewsAreaTypeRef ref: refList) {
				if(deleteSeqs.contains(ref.getNewsTypeSeq())) {
					return CommonUtil.getAjaxFailJson("刪除的分類已使用中, 無法刪除");
				}
			}
			newsAreaTypeDao.deleteByNewsAreaTypeSeqInAndLocale(deleteSeqs, locale);
		}
		
		// 新增或編輯的項目
		if(CollectionUtils.isNotEmpty(newsAreaTypeList)) {
			for (NewsTypeVo newsTypeVo : newsAreaTypeList) {
				NewsAreaType newsAreaType = new NewsAreaType();
				newsAreaType.setTypeName(newsTypeVo.getTypeValue());
				newsAreaType.setLocale(locale);

				// 新增
				if (newsTypeVo.getSeq() == null) {
					insertList.add(newsAreaType);
				// 編輯
				} else {
					newsAreaType.setNewsAreaTypeSeq(newsTypeVo.getSeq());
					updateList.add(newsAreaType);

					// 加入舊分類seq (比對DB 刪除用)
					// editSeqList.add(newsTypeVo.getSeq());
				}
			}

//			// 刪除分類
//			List<NewsAreaType> typeList = newsAreaTypeDao.findByLocale(locale);
//			// 資料庫seq 清單
//			List<Integer> typeSeqList = typeList.stream().map(n -> n.getNewsAreaTypeSeq()).collect(Collectors.toList());
//			
//
//			if(CollectionUtils.isNotEmpty(refList)) {
//				return CommonUtil.getAjaxFailJson("刪除的分類已使用中, 無法刪除");
//			}
//
//			List<Integer> deleteSeqList = new ArrayList<>();
//			for (Integer seq : typeSeqList) {
//				if (!editSeqList.contains(seq)) {
//					deleteSeqList.add(seq);
//				}
//			}
//			if (CollectionUtils.isNotEmpty(deleteSeqList)) {
//				// 刪除畫面移除的分類
//				newsAreaTypeDao.deleteByNewsAreaTypeSeqIn(deleteSeqList);
//			}

			// 新增分類
			newsAreaTypeDao.save(insertList);
			// 更新分類
			newsAreaTypeDao.save(updateList);
		}
		// 成功
		return CommonUtil.getAjaxSuccessJson();
	}

	/**
	 * 新聞專區 後臺管理 刪除.
	 *
	 * @param seq the seq
	 * @throws Exception the exception
	 */
	@Override
	public void doDeleteNewsAreaAction(Integer seq) throws Exception {
		newsAreaDao.delete(seq);
		// 刪除對應功能的多語系資料.
		localeTranslationDao.deleteByRefSeqAndFunctionType(seq, FunctionType.NEWS.getCode());
		// 刪除多語系關聯的最新消息 .
		int deleteRecs = newsAreaTypeRefDao.deleteByRefNewsSeq(seq);

		logger.debug("doDeleteNewsAreaAction newsAreaTypeRef deleteByRefNewsSeq recs: " + deleteRecs);
	}

	/** 行銷影片維護 儲存.
	 * @throws Exception */
	@Override
	public void doVideoSaveAction(SettingForm settingForm, User sysUser) throws Exception {
		VideoSetting videoSetting = new VideoSetting();
		Date today = new Date();
		// 影片發布日期為系統日
		String pushingDate = DateFormatUtils.format(today, CommonUtil.formatyyyy_MM_dd);

		// 編輯  update by pk -> videoSeq
		if (settingForm.getSeq() != null) {
			videoSetting = videoSettingDao.findOne(settingForm.getSeq());
		} else {
			videoSetting.setCreateDate(today);
		}
		
		videoSetting.setStatus(settingForm.getStatus());
		videoSetting.setVideoUrl(settingForm.getUrl());
		videoSetting.setUpdateDate(today);
		videoSetting.setPublishingDate(DateUtils.parseDate(pushingDate, CommonUtil.formatyyyy_MM_dd));
		videoSetting.setUser(sysUser);
		// 功能分類
		videoSetting.setFunctionType(FunctionType.VIDEO.getCode());

		videoSetting = videoSettingDao.save(videoSetting);
		
		// 儲存多語系欄位資料
		saveLocaleTransData(FunctionType.VIDEO, settingForm, videoSetting.getVideoSeq());
	}
	
	/**
	 * 行銷影片維護 刪除.
	 *
	 * @param seq the seq
	 * @throws Exception the exception
	 */
	@Override
	public void doDeleteVideoAction(Integer seq) throws Exception {
		videoSettingDao.delete(seq);
		// 刪除對應功能的多語系資料.
		localeTranslationDao.deleteByRefSeqAndFunctionType(seq, FunctionType.VIDEO.getCode());
	}

	/** logo牆 後臺管理 儲存.
	 * @throws Exception */
	@Override
	public void doSaveLogoWallAction(UploadFileForm uploadFileForm, User sysUser) throws Exception {
		Logowall logowall = new Logowall();
		Date today = new Date();
		MultipartFile file = null;
		
		String fileName = uploadFileForm.getFileName();
		
		// 編輯 update by pk -> bannerSeq
		if(uploadFileForm.getSeq() != null) {
			logowall = logowallDao.findOne(uploadFileForm.getSeq());
		// 新增
		} else {
			Integer maxLogoOrder = logowallDao.getMaxLogoOrder();
			if(maxLogoOrder == null) {
				maxLogoOrder = 0;
			}
			// 排序 = MAX +1
			logowall.setLogoOrder(maxLogoOrder + 1);
		}

		if (!uploadFileForm.getFile().isEmpty()) {
			file = uploadFileForm.getFile();
			if (file.getSize() > 0L) {
				// server 真實路徑
				String realPath = request.getServletContext().getRealPath(File.separator) + File.separator;
				// 上傳資料夾路徑
				String folderPath = logoWallFolderPath;
				
				fileName = EditDocUtil.uploadfile(file, realPath + folderPath, FilenameUtils.getBaseName(file.getOriginalFilename()));
				logowall.setFileName(fileName);
			}
		}

		logowall.setStatus(uploadFileForm.getStatus());
		logowall.setPublishingDate(DateUtils.parseDate(uploadFileForm.getPushingDate(), CommonUtil.dateFormatCommon));
		logowall.setUser(sysUser);

		logowallDao.save(logowall);
	}

	/**
	 * logo牆排序 後臺管理 儲存.
	 *
	 * @param seqSortList the seq sort list
	 * @param sysUser the sys user
	 * @throws Exception the exception
	 */
	@Override
	public void doSaveLogowallSortAction(List<Integer> seqSortList, User sysUser) throws Exception {
		List<Logowall> logowallList =  logowallDao.findAll(seqSortList);

		Integer[] seqSortArray = seqSortList.stream().map(i -> i).toArray(Integer[]::new);
		
		List<Logowall> saveList = new ArrayList<>();
		for(Logowall logowall : logowallList) {
			// 排序
			int idx = ArrayUtils.indexOf(seqSortArray, logowall.getLogoWallSeq());
			
			if(idx != -1) {
				logowall.setLogoOrder(idx);
				saveList.add(logowall);
			}
		}
		logowallDao.save(saveList);
	}

	/**
	 * logo牆刪除.
	 *
	 * @param seq the seq
	 * @throws Exception the exception
	 */
	@Override
	public void doDeleteLogoWallAction(Integer seq) throws Exception {
		logowallDao.delete(seq);
	}
	
	/**
	 * 成功案例後臺管理 儲存.
	 *
	 * @param settingForm the setting form
	 * @param sysUser the sys user
	 * @throws Exception the exception
	 */
	@Override
	public void doSuccessCaseSaveAction(UploadFileForm uploadFileForm, User sysUser) throws Exception {
		SuccessCase successCase = new SuccessCase();
		Date today = new Date();
		MultipartFile file = null;
		
		String fileName = uploadFileForm.getFileName();
		
		// 編輯 update by pk -> bannerSeq
		if(uploadFileForm.getSeq() != null) {
			successCase = successCaseDao.findOne(uploadFileForm.getSeq());
		} else {
			// 新增 建立日期
			successCase.setCreateDate(today);
		}

		if (!uploadFileForm.getFile().isEmpty()) {
			file = uploadFileForm.getFile();
			if (file.getSize() > 0L) {
				// server 真實路徑
				String realPath = request.getServletContext().getRealPath(File.separator) + File.separator;
				// 成功案例 上傳資料夾路徑
				String folderPath = successCaseFolderPath;
				
				fileName = EditDocUtil.uploadfile(file, realPath + folderPath, FilenameUtils.getBaseName(file.getOriginalFilename()));
				successCase.setFileName(fileName);
			}
		}

		successCase.setStatus(uploadFileForm.getStatus());

		// 立即上架:
		if(Status.EFFECTIVE.getCode().equals(uploadFileForm.getStatus())) {
			// 立即上架: 上架日期為今日
			successCase.setPublishingDate(new Date());
		} else {
			// 下架: 上架日期 null
			successCase.setPublishingDate(null);
		}

		successCase.setUpdateDate(today);
		successCase.setUser(sysUser);
		// 功能分類
		successCase.setFunctionType(FunctionType.SUCCESS_CASE.getCode());
		
		successCase = successCaseDao.save(successCase);

		// 儲存多語系欄位資料
		saveLocaleTransData(FunctionType.SUCCESS_CASE, uploadFileForm.getSettingForm(), successCase.getCaseSeq());
		
		logger.debug(">>> doSuccessCaseSaveAction success" + successCase);
	}
	
	/**
	 * 成功案例刪除.
	 *
	 * @param seq the seq
	 * @throws Exception the exception
	 */
	@Override
	public void doDeleteSuccessCaseAction(Integer seq) throws Exception {
		successCaseDao.delete(seq);
		// 刪除對應功能的多語系資料.
		localeTranslationDao.deleteByRefSeqAndFunctionType(seq, FunctionType.SUCCESS_CASE.getCode());
	}
	
	/**
	 * 儲存多語系欄位資料.
	 *
	 * @param functionType the function type
	 * @param settingForm the setting form
	 * @param refSeq the ref seq
	 */
	private void saveLocaleTransData (FunctionType functionType, SettingForm settingForm, Integer refSeq) {
		List<LocaleTranslation> transList = new ArrayList<>();
		// 是否為新增
		boolean isNew = true;
		if (settingForm.getSeq() != null) {
			isNew = false;
		}

		// 新增
		if(isNew) {
			for (Locale locale :LOCALE_LIST) {
				LocaleTranslation localeTrans= new LocaleTranslation();
				localeTrans.setFunctionType(functionType.getCode());
				localeTrans.setRefSeq(refSeq);
				localeTrans.setLocaleCode(locale.toString());

				// 設置多語系欄位儲存(title、content)
				setLocaleTransCol(localeTrans, settingForm, locale.toString());

				transList.add(localeTrans);
			}
		// 編輯
		} else {
			// 找出對應功能的轉換資料
			transList = localeTranslationDao.findByRefSeqAndFunctionType(refSeq, functionType.getCode());

			for(LocaleTranslation localeTrans : transList) {

				String localeCode = localeTrans.getLocaleCode();
				// 設置多語系欄位儲存(title、content)
				setLocaleTransCol(localeTrans, settingForm, localeCode);
			}
		}
		localeTranslationDao.save(transList);
	}

	/**
	 * 設置多語系欄位儲存(title、content).
	 *
	 * @param localeTrans the locale trans
	 * @param settingForm the setting form
	 * @param localeCode the locale code
	 */
	private void setLocaleTransCol (LocaleTranslation localeTrans, SettingForm settingForm, String localeCode) {
		if(StringUtils.equals(Locale.TAIWAN.toString(), localeCode)) {
			localeTrans.setTransTitle(settingForm.getTitle());
			localeTrans.setTransContent(settingForm.getContent());
		}

		if(StringUtils.equals(Locale.US.toString(), localeCode)) {
			localeTrans.setTransTitle (settingForm.getTitleEn());
			localeTrans.setTransContent(settingForm.getContentEn());
		}
	}

	/**
	 * 編輯器 上傳圖片.
	 *
	 * @param typeSeq the type seq
	 * @param file the file
	 * @param type the type
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
	@Override
	public String doEditorUploadImageAction(MultipartFile file, String type, MultipartHttpServletRequest request) throws Exception {
		// 後台設定 service 輔助程式
		BackendSettingServiceHelper heler = BackendSettingServiceHelper.getInstance();

		// server 真實路徑
		String realPath = request.getServletContext().getRealPath(File.separator) + File.separator;
		// 上傳資料夾路徑
		String folderPath = CommonUtil.getEditorUploadImageForderPath(uploadImageFolderPathPath);
		// 檔案名稱
		String baseName = FilenameUtils.getBaseName(file.getOriginalFilename());
		// 轉存的檔案名稱 (baseName + yyyyMMddmmssSSSS)
		String realFileName = baseName + DateFormatUtils.format(new Date(), CommonUtil.formatyyyyMMddmmssSSSS);

		// 刪除相同的檔案
		heler.deleteSameFile(realPath + folderPath, baseName);
		// 上傳
		realFileName = EditDocUtil.uploadfile(file, realPath + folderPath, realFileName);	
		
		// TODO 暫時應該不用壓縮. 圖片壓縮.縮小處理 
		// heler.compressimage(type, realPath + folderPath + realFileName);

		// 伺服器url
		String baseURL = CommonUtil.getBaseURL(request);
			
		return baseURL + folderPath + realFileName;
	}
}
