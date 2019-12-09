package com.systalk.sys.web.form.view.pageViewForm.backend.baseForm;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.systalk.sys.enums.Status;
import com.systalk.sys.util.CommonUtil;

/**
 * 設定欄位.
 */
public class SettingForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final String REQUIRE ="欄位必填";

	private Integer seq;
	
	//@NotEmpty(message="{validate.require}")
	@NotEmpty(message=REQUIRE)
	private String title = "";
	
	// 英文標題
	@NotEmpty(message=REQUIRE + "(英文)")
	private String titleEn = "";
	
	@NotEmpty(message=REQUIRE)
	private String content = "";
	
	// 英文內文
	@NotEmpty(message=REQUIRE + "(英文)")
	private String contentEn = "";
	
	// 預設1 立即上架
	private String status = Status.EFFECTIVE.getCode();
	
	private String  fileName = "";
	
	/** mobile圖片格式名稱 */
	private String  fileName2 = "";
	
	// @NotEmpty(message=REQUIRE)  上架日期 改為非必填
	private String pushingDate;
	
	// 下架日期
	private String invalidDate;
	
	private String url;


	/**
	 * 初始上架日期(今天).
	 */
	public SettingForm() {
		this.pushingDate = CommonUtil.getInitPublishingDate();
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

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentEn() {
		return contentEn;
	}

	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public String getPushingDate() {
		return pushingDate;
	}

	public void setPushingDate(String pushingDate) {
		this.pushingDate = pushingDate;
	}

	public String getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public SettingForm getSettingForm() {
		return this;
	}
}
