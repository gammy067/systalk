package com.systalk.sys.web.form.view.pageViewForm.backend;

import java.io.Serializable;

import javax.validation.Valid;

import com.systalk.sys.web.form.component.combo.ComboBox;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 後臺 - 新聞專區 新增、編輯EditNewsAreaViewForm.
 * */
public class EditNewsAreaViewForm implements Serializable {


	/** 頁面物件. */
//	private NewsAreaBean newsAreaBean;
	
	/** 設定欄位. */
	@Valid
	private UploadFileForm uploadFileForm = new UploadFileForm();
	
	/** 新聞分類下拉選單. */
	private ComboBox typeCombo;
	
	/** 英文新聞分類下拉選單. */
	private ComboBox typeComboEn;
	
	/** 圖片顯示url */
	private String imageUrl;
	
	/** 分類下拉選項值 */
	private Integer typeSeq;
	
	/** 英文分類下拉選項值 */
	private Integer typeSeqEn;

	/** 編輯器 內文. */
//	private String editorContent = "";

//	/**
//	 * Gets the news area bean.
//	 *
//	 * @return the news area bean
//	 */
//	public NewsAreaBean getNewsAreaBean() {
//		return newsAreaBean;
//	}
//
//	/**
//	 * Sets the news area bean.
//	 *
//	 * @param newsAreaBean the new news area bean
//	 */
//	public void setNewsAreaBean(NewsAreaBean newsAreaBean) {
//		this.newsAreaBean = newsAreaBean;
//	}


//	public String getEditorContent() {
//		return editorContent;
//	}

	public UploadFileForm getUploadFileForm() {
		return uploadFileForm;
	}

	public void setUploadFileForm(UploadFileForm uploadFileForm) {
		this.uploadFileForm = uploadFileForm;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ComboBox getTypeCombo() {
		return typeCombo;
	}

	public void setTypeCombo(ComboBox typeCombo) {
		this.typeCombo = typeCombo;
	}

	public ComboBox getTypeComboEn() {
		return typeComboEn;
	}

	public void setTypeComboEn(ComboBox typeComboEn) {
		this.typeComboEn = typeComboEn;
	}

	public Integer getTypeSeq() {
		return typeSeq;
	}

	public void setTypeSeq(Integer typeSeq) {
		this.typeSeq = typeSeq;
	}

	public Integer getTypeSeqEn() {
		return typeSeqEn;
	}

	public void setTypeSeqEn(Integer typeSeqEn) {
		this.typeSeqEn = typeSeqEn;
	}


//	public void setEditorContent(String editorContent) {
//		this.editorContent = editorContent;
//	}
}
