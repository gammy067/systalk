package com.systalk.sys.web.form.view.bean.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import com.systalk.sys.model.LocaleTranslation;

public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// 是否為草稿案件
	private Boolean draft = false;

	// 流水號
	private Integer seq;
	
	// 標題
	private String title = "";
	
	// 英文標題
	private String titleEn = "";
	
	// 編輯人員序號
	private Integer userSeq = 0;
	
	// 編輯人員名稱
	private String userName;
	
	// 建立時間
	private Date createDate;
	
	// 更新時間 YYYYMMDDHHMISS
	private Date updateDate;
	
	// 上架時間 YYYY/mm/dd
	private Date pushingDate;
	
	// 下架時間 YYYY/mm/dd
	private Date invalidDate;
	
	// 狀態(下架 - 0 ,上架 - 1 )
	private String status;
	
	// 狀態顯示(下架 - 0 ,上架 - 1 )
	private String displayStatus;
	
	// 內容
	private String content = "";
	
	// 英文內容
	private String contentEn = "";

	// 檔案路徑
	private String url = "";
	
	// 新聞分類
	private String newsType = "";
	
	// 英文新聞分類
	private String newsTypeEn = "";
	
	// 前臺顯示對應語系標題
	private String displayTitle = "";
	
	// 前臺顯示對應語系內文
	private String displayContent = "";
	
	// 前臺顯示對應語系 新聞分類
	private String displayNewsType = "";

	public Boolean getDraft() {
		return draft;
	}

	public void setDraft(Boolean draft) {
		this.draft = draft;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getPushingDate() {
		return pushingDate;
	}

	public void setPushingDate(Date pushingDate) {
		this.pushingDate = pushingDate;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getStatus() {
		return status;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	public String getContentEn() {
		return contentEn;
	}

	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}
	
	public String getDisplayTitle() {
		return displayTitle;
	}

	public void setDisplayTitle(String displayTitle) {
		this.displayTitle = displayTitle;
	}

	public String getDisplayContent() {
		return displayContent;
	}

	public void setDisplayContent(String displayContent) {
		this.displayContent = displayContent;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsTypeEn() {
		return newsTypeEn;
	}

	public void setNewsTypeEn(String newsTypeEn) {
		this.newsTypeEn = newsTypeEn;
	}

	public String getDisplayNewsType() {
		return displayNewsType;
	}

	public void setDisplayNewsType(String displayNewsType) {
		this.displayNewsType = displayNewsType;
	}

	/**
	 * bean 設置多語系對應欄位 (title、content).
	 *
	 * @param localeTransList the new locale trans data
	 */
	public void setLocaleTransData(List<LocaleTranslation> localeTransList) {
		if(CollectionUtils.isEmpty(localeTransList)) {
			return;
		}

		Map<String, LocaleTranslation> transMap =  localeTransList.stream().collect(Collectors.toMap(LocaleTranslation :: getLocaleCode, l -> l));
		// 後臺對應欄位
		this.title = transMap.get(Locale.TAIWAN.toString()).getTransTitle();
		this.content = transMap.get(Locale.TAIWAN.toString()).getTransContent();

		this.titleEn = transMap.get(Locale.US.toString()).getTransTitle();
		this.contentEn = transMap.get(Locale.US.toString()).getTransContent();
		
		/** 當前語系 */
		Locale  currentLocale = LocaleContextHolder.getLocale();
		// 前臺顯示對應語系
		this.displayTitle = transMap.get(currentLocale.toString()).getTransTitle();
		this.displayContent = transMap.get(currentLocale.toString()).getTransContent();
	}
}
