package com.systalk.sys.service.impl.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.systalk.sys.dao.BannerSettingDao;
import com.systalk.sys.dao.LogowallDao;
import com.systalk.sys.dao.SuccessCaseDao;
import com.systalk.sys.dao.VideoSettingDao;
import com.systalk.sys.enums.Status;
import com.systalk.sys.model.BannerSetting;
import com.systalk.sys.model.Logowall;
import com.systalk.sys.model.SuccessCase;
import com.systalk.sys.model.VideoSetting;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.util.JsonUtil;
import com.systalk.sys.util.MapUtil;
import com.systalk.sys.util.SpringUtil;
import com.systalk.sys.web.form.view.bean.BannerSettingBean;
import com.systalk.sys.web.form.view.bean.EmailContactInfo;
import com.systalk.sys.web.form.view.bean.EmailRpa;
import com.systalk.sys.web.form.view.bean.LogoWallBean;
import com.systalk.sys.web.form.view.bean.SuccessCaseBean;
import com.systalk.sys.web.form.view.bean.VideoSettingBean;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendIndexForm;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FrontendIndexServiceHelper {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/** 聯絡我們 - 信件格式.  */
	private final static String EMAIL_CONTACT_TEMPLATE = "emailContact.vm";
	
	/** RPA產品 - 信件格式.  */
	private final static String EMAIL_RPA_TEMPLATE = "emailRpa.vm";

	/** 系統信箱 */
	private final static String SYSMAIL = SpringUtil.getProperty("st.sys.mail");
	

	/**
	 * 取得首頁資料.
	 * @throws IOException 
	 * */
	public FrontendIndexForm getFrontendIndexForm(String requestUrl) throws IOException {
		FrontendIndexForm viewForm = new FrontendIndexForm();
		// 首頁banner 資料
		viewForm.setBannerList(getBannerList(requestUrl));
		// 行銷影片資料
		viewForm.setVideoSetting(getVideoSetting());
		// 品牌牆資料
		viewForm.setLogoWallList(getLogoWallList(requestUrl));
		// 成功案例
		viewForm.setSuccessCaseList(getSuccessCaseList(requestUrl));

		// logger.debug("getFrontendIndexForm()" + JsonUtil.toJsonString(viewForm));
		return viewForm;
	}
	
	/**
	 * 首頁banner 資料.
	 *
	 * @return the banner list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private List<BannerSettingBean> getBannerList(String requestUrl) throws IOException {
		List<BannerSettingBean> bannerList = new ArrayList<>();
		String folderPath = SpringUtil.getProperty("banner.image.folder.path");

		BannerSettingDao dao = SpringUtil.getDao(BannerSettingDao.class);
		// 查找狀態1: 立即上架 0:指定日期上架 的項目
		List<BannerSetting> list=  dao.findEffectItems();

		for(BannerSetting setting: list) {
			// 一般 web 圖片路徑
			String url = CommonUtil.generalFileUrl(requestUrl + folderPath, setting.getFileName());
			// mobile 圖片路徑
			String url2 = CommonUtil.generalFileUrl(requestUrl + folderPath, setting.getFileName2());
			BannerSettingBean bean = new BannerSettingBean(setting, url, url2);
			bannerList.add(bean);
		}
		return bannerList;
	}

	/**
	 * 行銷影片資料.
	 *
	 * @return the video setting
	 */
	private VideoSettingBean getVideoSetting() {
		VideoSettingBean bean = new VideoSettingBean();
		VideoSettingDao dao = SpringUtil.getDao(VideoSettingDao.class);
		// 查詢 by 更新日期 2019.10.04 datetime格式能判斷當天上傳順序
		VideoSetting videoSetting = dao.findTopByStatusOrderByUpdateDateDesc(Status.EFFECTIVE.getCode());
		
		if(videoSetting != null) {
			bean = new VideoSettingBean(videoSetting);	
			// html 資料結構處理
			//bean.setContent(CommonUtil.getHtmlContent(bean.getContent()));
			
			// 嵌入式影片 加入autoplay 自動撥放.
			bean.setUrl(CommonUtil.videoUrlAddAutoplay(bean.getUrl()));
			// html 資料結構處理
			bean.setUrl(CommonUtil.getHtmlContent(bean.getUrl()));
		}

		return bean;
	}

	/**
	 * 品牌牆資料.
	 *
	 * @return the logo wall list
	 * @throws IOException 
	 */
	private List<LogoWallBean> getLogoWallList(String requestUrl) throws IOException {
		List<LogoWallBean> logoWallList = new ArrayList<>();
		LogowallDao dao = SpringUtil.getDao(LogowallDao.class);
		String folderPath = SpringUtil.getProperty("logoWall.image.folder.path");
		
		List<Logowall> list=  dao.findByStatusOrderByLogoOrderAsc(Status.EFFECTIVE.getCode());
		
		for(Logowall logowall : list) {
			LogoWallBean bean = new LogoWallBean(logowall, CommonUtil.generalFileUrl(requestUrl + folderPath, logowall.getFileName()));
			logoWallList.add(bean);
		}
		
		return logoWallList;
	}
	
	/**
	 * 成功案例.
	 *
	 * @return the success case list
	 * @throws IOException 
	 */
	private List<SuccessCaseBean> getSuccessCaseList(String requestUrl) throws IOException {
		List<SuccessCaseBean> successCaseList = new ArrayList<>();
		String folderPath = SpringUtil.getProperty("successCase.image.folder.path");

		SuccessCaseDao dao = SpringUtil.getDao(SuccessCaseDao.class);	
		List<SuccessCase> list = dao.findByStatus(Status.EFFECTIVE.getCode());
		
		for(SuccessCase successCase : list) {
			SuccessCaseBean bean = new SuccessCaseBean(successCase, CommonUtil.generalFileUrl(requestUrl + folderPath, successCase.getFileName()));
			
			// html 資料結構處理
			bean.setDisplayContent(CommonUtil.getHtmlContent(bean.getDisplayContent()));
			successCaseList.add(bean);
		}
		
		return successCaseList;
	}

	/**
	 * 設置聯絡我們信件資訊.
	 *
	 * @param messageHelper the message helper
	 * @param emailContactInfo the email contact info
	 * @throws MessagingException the messaging exception
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 * @throws TemplateException 
	 */
	public void setContactInfoEmail(MimeMessageHelper messageHelper, EmailContactInfo emailContactInfo) throws Exception {
		// 收件者 定義在設定檔
		@SuppressWarnings("unchecked")
		List<String> sendToList = SpringUtil.getProperty("st.mail.send.to", List.class);

		// 信件標題 : 官網Footer表單: [SysTalk.ai官網需求表單] 客戶名稱+送出日期
		String emailSubject = getEmailSubject(SpringUtil.getProperty("st.mail.contactInfo.subject"), emailContactInfo.getName()) ; 

		Configuration freemarkerConfiguration = (Configuration) SpringUtil.getBean("getFreeMarkerConfiguration");
		Template template = freemarkerConfiguration.getTemplate(EMAIL_CONTACT_TEMPLATE);
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, MapUtil.objectToMap(emailContactInfo));
		
		// 收件人清單
		sendToList.add(emailContactInfo.getEmail());
		// 設定收件人、寄件人、主題與內文
		setMessageHelperInfo(messageHelper, sendToList.stream().toArray(String[]::new), new InternetAddress(SYSMAIL), emailSubject, text);
		
		ServletContext context = SpringUtil.getBean(ServletContext.class);
		String contextPath = context.getRealPath(File.separator);
		FileSystemResource logo_png = new FileSystemResource(contextPath + "/images/systalk-logo-01.png");
		messageHelper.addInline("logo_png", logo_png);
	}
	
	/**
	 * 設置RPA信件資訊.
	 *
	 * @param messageHelper the message helper
	 * @param emailContactInfo the email contact info
	 * @throws MessagingException the messaging exception
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedTemplateNameException 
	 * @throws TemplateNotFoundException 
	 * @throws TemplateException 
	 */
	public void setRpaEmail(MimeMessageHelper messageHelper, EmailRpa emailRpa) throws Exception {
		// 收件者 定義在設定檔
		@SuppressWarnings("unchecked")
		List<String> sendToList = SpringUtil.getProperty("st.mail.send.to", List.class);

		// 信件標題 : 官網Footer表單: [RPA客戶需求表單] 客戶名稱+送出日期
		String emailSubject = getEmailSubject(SpringUtil.getProperty("st.mail.rpa.subject"), emailRpa.getRpaName()) ; 

		Configuration freemarkerConfiguration = (Configuration) SpringUtil.getBean("getFreeMarkerConfiguration");
		Template template = freemarkerConfiguration.getTemplate(EMAIL_RPA_TEMPLATE);
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, MapUtil.objectToMap(emailRpa));

		// 收件人清單
		sendToList.add(emailRpa.getRpaEmail());
		// 設定收件人、寄件人、主題與內文
		setMessageHelperInfo(messageHelper, sendToList.stream().toArray(String[]::new), new InternetAddress(SYSMAIL), emailSubject, text);
	}
	
	/**
	 * 設定收件人、寄件人、主題與內文.
	 *
	 * @param messageHelper the message helper
	 * @param setTo the set to
	 * @param from the from
	 * @param subject the subject
	 * @param text the text
	 * @throws Exception the exception
	 */
	private void setMessageHelperInfo(MimeMessageHelper messageHelper, String[] setTo, InternetAddress from, String subject, String text) throws Exception {
		// 設定收件人、寄件人、主題與內文
		messageHelper.setTo(setTo);
		
		//messageHelper.setTo("gammy521@gmail.com");
		//寄件者
		messageHelper.setFrom(from);
		messageHelper.setSubject(subject);
		messageHelper.setText(text, true);
	}
	
	/**
	 * Gets the email subject.
	 *
	 * @param subject the subject
	 * @param custName the cust name
	 * @return the email subject
	 */
	private String getEmailSubject(String subject, String custName) {
		String emailSubject = "";
			String nowDateStr = DateFormatUtils.format(new Date(), CommonUtil.dateFormatCommon);
			emailSubject = subject + " " + custName + " " + nowDateStr;
			return emailSubject;
	}

	private FrontendIndexServiceHelper() {
	}

	private static class Loader {
		private static final FrontendIndexServiceHelper instance = new FrontendIndexServiceHelper();
	}

	public static FrontendIndexServiceHelper getInstance() {
		return Loader.instance;
	}
}
