package com.systalk.sys.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 語系資料轉換表.
 * 
 */
@Entity
@Table(name="locale_translation")
@NamedQuery(name="LocaleTranslation.findAll", query="SELECT l FROM LocaleTranslation l")
public class LocaleTranslation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trans_id")
	private int transId;
	
	@Column(name="ref_seq")
	private int refSeq;

	@Column(name="function_type")
	private String functionType;

	@Column(name="trans_title")
	private String transTitle;
	
	@Lob
	@Column(name="trans_content")
	private String transContent;
	
	@Column(name="locale_code")
	private String localeCode;
	

	public LocaleTranslation() {
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public int getRefSeq() {
		return refSeq;
	}

	public void setRefSeq(int refSeq) {
		this.refSeq = refSeq;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getTransTitle() {
		return transTitle;
	}

	public void setTransTitle(String transTitle) {
		this.transTitle = transTitle;
	}

	public String getTransContent() {
		return transContent;
	}

	public void setTransContent(String transContent) {
		this.transContent = transContent;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
}