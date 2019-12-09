package com.systalk.sys.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.systalk.sys.dao.BlocksizeSettingDao;
import com.systalk.sys.dao.NewsAreaTypeDao;
import com.systalk.sys.enums.BlocksizeSettingEnum;
import com.systalk.sys.model.BlocksizeSetting;
import com.systalk.sys.model.NewsAreaType;
import com.systalk.sys.web.form.component.combo.ComboBox;
import com.systalk.sys.web.form.component.combo.ComboItem;

public class CommonUtil {
	
	/** 日期格式 yyyyMMdd. */
	public static final String formatyyyyMMdd = "yyyyMMdd";
	
	/** 日期格式 yyyy-MM-dd. */
	public static final String formatyyyy_MM_dd = "yyyy-MM-dd";
	
	/** 日期格式 yyyy/MM/dd. */
	public static final String dateFormatCommon = "yyyy/MM/dd";
	
	/** 日期格式 yyyyMMddmmssSSSS. */
	public static final String formatyyyyMMddmmssSSSS = "yyyyMMddmmssSSSS";

	/** 下拉最多顯示幾年前選項 */
	private static final int MAX_YEAR = 3;
	
	/** 新聞專區每頁顯示最大筆數 */
	public static final int PAGE_MAX_COUNT = 9;

	
	/**
	 * Http請求的基本路徑.
	 *
	 * @param request
	 * @return
	 */
	public static String getBaseURL(HttpServletRequest request) {
		String url = "";
		url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		return url;
	}

	/**
	 * 產生檔案路徑.
	 *
	 * @param path the path
	 * @param fileName the file name
	 * @return the string
	 */
	public static String generalFileUrl (String requestUrl, String fileName) {
		if(StringUtils.isBlank(requestUrl) || StringUtils.isBlank(fileName)) {
			return "";
		} else {
			return requestUrl + fileName;
		}
	}
	
	/**
	 * 編輯器圖片上傳資料夾路徑 + date ("yyyyMMdd").
	 *
	 * @return the editor upload image forder path
	 * @throws Exception the exception
	 */
	public static String getEditorUploadImageForderPath(String forderPath) {
		String path = forderPath + DateFormatUtils.format(new Date(), formatyyyyMMdd) + "/";
		return path;
	}
	
	/**
	 * 取得 ajax success json.
	 *
	 * @return the ajax success json
	 * @throws JsonGenerationException the json generation exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getAjaxSuccessJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 取得 ajax success json.
	 *
	 * @return the ajax success json
	 * @throws JsonGenerationException the json generation exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getAjaxSuccessJson(String msg) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		map.put(msg, msg);
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 取得 ajax success json.
	 *
	 * @return the ajax success json
	 * @throws JsonGenerationException the json generation exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getAjaxFailJson(String msg) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", false);
		map.put("msg", msg);
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 取得初始上架日期(今天).
	 *
	 * @return the inits the publishing date
	 */
	public static String getInitPublishingDate() {
		Date today = new Date();
		return DateFormatUtils.format(today, dateFormatCommon);
	}
	
	/**
	 * 取得日期字串格式
	 * Date date null -> "".
	 *
	 * @param date the date
	 * @return the common date str
	 */
	public static String getCommonDateStr(Date date) {
		if(date != null) {
			return DateFormatUtils.format(date, CommonUtil.dateFormatCommon);
		} else {
			return "";
		}
	}

	/**
	 * 取得日期字串格式
	 * String date empty -> null.
	 *
	 * @param date the date
	 * @return the common date str
	 */
	public static Date getCommonDate(String date) {
		if(StringUtils.isNotEmpty(date)) {
			try {
				return DateUtils.parseDate(date, CommonUtil.dateFormatCommon);
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * html 資料結構處理 (escapeJava).
	 *
	 * @param content the content
	 * @return the html content
	 */
	public static String getHtmlContent(String content) {
		String htmlContent = "";
		if(StringUtils.isNotEmpty(content)) {
			// html 資料結構處理
			htmlContent = StringEscapeUtils.escapeJava(StringUtils.defaultString(content));
		}
		return htmlContent;
	}

	/**
	 * 嵌入式影片 加入https: 字首.
	 *
	 * @param iframeHtmlContent the iframe html content
	 */
	public static String videoUrlAddHttpPrefix(String iframeHtmlContent) {
		Document doc = Jsoup.parse(iframeHtmlContent); 
		Elements es = doc.select("iframe");

		for(Element e : es) {
			String url = e.attr("src");
			if(StringUtils.indexOf(url, "https:") == -1) {
				e.attr("src", "https:" + url);
			}
		}
		return doc.body().html();
	}
	
	/**
	 * 嵌入式影片 加入autoplay 自動播放.
	 *
	 * @param iframeHtmlContent the iframe html content
	 */
	public static String videoUrlAddAutoplay(String iframeHtmlContent) {
		Document doc = Jsoup.parse(iframeHtmlContent); 
		Elements es = doc.select("iframe");

		for(Element e : es) {
			String url = e.attr("src");
			if(StringUtils.indexOf(url, "autoplay=1") == -1) {
				e.attr("src", url + "?autoplay=1");
			}
		}
		return doc.body().html();
	}
	
	/**
	 * 最新消息 分類下拉選單.
	 *
	 * @return the type combo
	 */
	public static ComboBox getNewsTypeCombo(String locale) {
		ComboBox combo = new ComboBox();
		// combo.addDefaultComboItem();

		NewsAreaTypeDao dao = SpringUtil.getDao(NewsAreaTypeDao.class);
		List<NewsAreaType> typeList = dao.findByLocale(locale);
		
		for(NewsAreaType type : typeList) {
			ComboItem item = new ComboItem(type.getTypeName(),String.valueOf(type.getNewsAreaTypeSeq()));
			combo.add(item);
		}
		return combo;
	}
	
	/**
	 * 年分下拉選單.
	 *
	 * @return the type combo
	 */
	public static ComboBox getDateCombo() {
		ComboBox combo = new ComboBox();
		//combo.addDefaultComboItem();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		int nowYear = calendar.get(Calendar.YEAR);
		List<String> yearList = new ArrayList<>();
		yearList.add(String.valueOf(nowYear));
		// 取 MAX_YEAR 年內年分
		for (int i = 1; i <= MAX_YEAR ; i++) {
			yearList.add(String.valueOf(nowYear - i));
		}
		
		for(String year : yearList) {
			ComboItem item = new ComboItem(year, year);
			combo.add(item);
		}
		return combo;
	}
	
	/**
	 * 最新消息 分類名稱顯示.
	 *
	 * @param typeSeq the type seq
	 * @return the news area type display
	 */
	public static String getNewsAreaTypeDisplay(Integer typeSeq, String locale) {
		if(typeSeq == null) {
			return "";
		}
		
		NewsAreaTypeDao dao = SpringUtil.getDao(NewsAreaTypeDao.class);
		// 新聞專區分類對應名稱
		NewsAreaType newsAreaType = dao.findByNewsAreaTypeSeqAndLocale(typeSeq, locale);
		if(newsAreaType != null) {
			return newsAreaType.getTypeName();
		} else {
			return "";
		}
	}
	
	/**
	 * 取得最新消息一頁最多顯示數 (分頁).
	 *
	 * @return the banner max count
	 */
	public static Integer getNewsPageMaxCount() {
		Integer count = 0;
		count = getMaxCountByBlockName(BlocksizeSettingEnum.NEWS_PAGE_MAX_COUNT.getBlockName());
		return count;
	}
	
	/**
	 * 取得輪播圖最大上架數.
	 *
	 * @return the banner max count
	 */
	public static Integer getBannerMaxCount() {
		Integer count = 0;
		count = getMaxCountByBlockName(BlocksizeSettingEnum.BANNER_MAX_COUNT.getBlockName());
		return count;
	}
	
	/**
	 * 取得行銷影片最大上架數.
	 *
	 * @return the banner max count
	 */
	public static Integer getVideoMaxCount() {
		Integer count = 0;
		count = getMaxCountByBlockName(BlocksizeSettingEnum.VIDEO_MAX_COUNT.getBlockName());
		return count;
	}
	
	/**
	 * 取得最大上架數.
	 *
	 * @return the banner max count
	 */
	private static Integer getMaxCountByBlockName(String blockName) {
		Integer count = 0;
		BlocksizeSetting blocksizeSetting = getBlocksizeSetting(blockName);
		if(blocksizeSetting != null) {
			count = blocksizeSetting.getSize();
		}
		return count;
	}
	
	private static BlocksizeSetting getBlocksizeSetting(String blockName) {
		BlocksizeSettingDao dao = SpringUtil.getDao(BlocksizeSettingDao.class);
		return dao.findByBlockName(blockName);
	}
	
	/**
	 * 取得語系 Locale object.
	 *
	 * @return the map
	 */
	public static Locale getChangeLocale(String lang) {
		if(StringUtils.isEmpty(lang)) {
			return Locale.TAIWAN;
		}
		
		if(StringUtils.containsNone(lang, "_")) {
			return Locale.TAIWAN;
		}
		
		String[] lanArr = StringUtils.split(lang, "_");

		return new Locale(lanArr[0], lanArr[1]);
	}
	
	/**
	 * 支援的語系 map.
	 *
	 * @return the map
	 */
	public static Map<String, Locale> getLocaleMap() {
		Map<String, Locale> localeMap = new LinkedHashMap<String, Locale>();
		// 支援的語系 list. TAIWAN、US
		List<Locale> localeList = getLocaleList();
		for(Locale locale : localeList) {
			localeMap.put(locale.toString(), locale);
		}
		return localeMap;
	}
	
	/**
	 * 支援的語系 list.
	 * TAIWAN、US
	 *
	 * @return the map
	 */
	public static List<Locale> getLocaleList() {
		List<Locale> localeList = new LinkedList<Locale>();
		localeList.add(Locale.TAIWAN);
		localeList.add(Locale.US);

		return localeList;
	}
}
