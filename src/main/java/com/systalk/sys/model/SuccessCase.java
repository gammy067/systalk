package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the success_case database table.
 * 
 */
@Entity
@Table(name="success_case")
@NamedQuery(name="SuccessCase.findAll", query="SELECT s FROM SuccessCase s")
public class SuccessCase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="case_seq")
	private int caseSeq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="file_name")
	private String fileName;

	private String language;

	@Temporal(TemporalType.DATE)
	@Column(name="publishing_date")
	private Date publishingDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="invalid_date")
	private Date invalidDate;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_seq")
	private User user;
	
	@Column(name="function_type")
	private String functionType;
	
	// join多語系table
    @OneToMany
    @JoinColumns({
        @JoinColumn(
            name = "ref_seq",
            referencedColumnName = "case_seq"),
        @JoinColumn(
            name = "function_type",
            referencedColumnName = "function_type")
    })
    private List<LocaleTranslation> localeTransList;

	public SuccessCase() {
	}

	public int getCaseSeq() {
		return this.caseSeq;
	}

	public void setCaseSeq(int caseSeq) {
		this.caseSeq = caseSeq;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getPublishingDate() {
		return this.publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public List<LocaleTranslation> getLocaleTransList() {
		return localeTransList;
	}

	public void setLocaleTransList(List<LocaleTranslation> localeTransList) {
		this.localeTransList = localeTransList;
	}

}