package com.systalk.sys.web.form.view.bean;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.systalk.sys.model.NewsArea;
import com.systalk.sys.model.NewsAreaTypeRef;
import com.systalk.sys.web.form.view.bean.base.BaseBean;

/**
 * 前台 - 新聞專區 物件.
 * */
public class NewsAreaBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// 分類流水號
	private Integer typeSeq;
	
	// 英文 分類流水號
	private Integer typeSeqEn;
	
	// 檔案名稱
	private String fileName;
	
	// 分類名稱
	private String displayType = "";
	
	// 英文分類名稱
	private String displayTypeEn = "";
	
	// 上架日期顯示
	private String displayPushingDate= "";

	public NewsAreaBean () {
	}

	/**
	 * 後臺顯示.
	 *
	 * @param na the na
	 */
	public NewsAreaBean (NewsArea na) {
		this.setSeq(na.getNewsSeq());
		this.setCreateDate(na.getCreateDate());
		this.setPushingDate(na.getPublishingDate());
		this.setInvalidDate(na.getInvalidDate());
		this.setStatus(na.getStatus());
		this.setUpdateDate(na.getUpdateDate());
		this.setFileName(na.getFileName());

		// bean 設置多語系對應欄位 (title、content)
		setLocaleTransData(na.getLocaleTransList());
		
		// 設置對應語系分類seq.
		this.setLocaleTypeSeq(na.getNewsAreaTypeRefList());
	}
	
	/**
	 * 前臺顯示.
	 *
	 * @param na the na
	 * @param url the url
	 */
	public NewsAreaBean (NewsArea na, String url) {
		this.setSeq(na.getNewsSeq());
		this.setCreateDate(na.getCreateDate());
		this.setPushingDate(na.getPublishingDate());
		this.setStatus(na.getStatus());
		this.setUpdateDate(na.getUpdateDate());
		this.setFileName(na.getFileName());
		this.setUrl(url);
		
		// bean 設置多語系對應欄位 (title、content)
		setLocaleTransData(na.getLocaleTransList());
		
		// 設置對應語系分類seq.
		this.setLocaleTypeSeq(na.getNewsAreaTypeRefList());
	}
	
	/**
	 * 設置對應語系分類seq.
	 *
	 * @param newsTypeRefList the new locale type seq
	 */
	private void setLocaleTypeSeq(List<NewsAreaTypeRef> newsTypeRefList) {
		if(CollectionUtils.isEmpty(newsTypeRefList)) {
			return;
		}

		for (NewsAreaTypeRef newsAreaTypeRef : newsTypeRefList) {
			String locale = newsAreaTypeRef.getLocale();
			// 中文分類
			if (StringUtils.equals(Locale.TAIWAN.toString(), locale)) {
				this.typeSeq = newsAreaTypeRef.getNewsTypeSeq();
			}
			// 英文分類
			if (StringUtils.equals(Locale.US.toString(), locale)) {
				this.typeSeqEn = newsAreaTypeRef.getNewsTypeSeq();
			}
		}
	}

	public int getTypeSeq() {
		return typeSeq;
	}

	public void setTypeSeq(Integer typeSeq) {
		this.typeSeq = typeSeq;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public Integer getTypeSeqEn() {
		return typeSeqEn;
	}

	public void setTypeSeqEn(Integer typeSeqEn) {
		this.typeSeqEn = typeSeqEn;
	}

	public String getDisplayTypeEn() {
		return displayTypeEn;
	}

	public void setDisplayTypeEn(String displayTypeEn) {
		this.displayTypeEn = displayTypeEn;
	}

	public String getDisplayPushingDate() {
		return displayPushingDate;
	}

	public void setDisplayPushingDate(String displayPushingDate) {
		this.displayPushingDate = displayPushingDate;
	}
}
