package com.systalk.sys.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the video_setting database table.
 * 
 */
@Entity
@Table(name="video_setting")
@NamedQuery(name="VideoSetting.findAll", query="SELECT v FROM VideoSetting v")
public class VideoSetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="video_seq")
	private int videoSeq;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_date")
	private Date updateDate;

	@Temporal(TemporalType.DATE)
	@Column(name="publishing_date")
	private Date publishingDate;

	private String status;
	
	@Column(name="video_url")
	private String videoUrl;

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
            referencedColumnName = "video_seq"),
        @JoinColumn(
            name = "function_type",
            referencedColumnName = "function_type")
    })
    private List<LocaleTranslation> localeTransList;

	public VideoSetting() {
	}

	public int getVideoSeq() {
		return this.videoSeq;
	}

	public void setVideoSeq(int videoSeq) {
		this.videoSeq = videoSeq;
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

	public Date getPublishingDate() {
		return this.publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
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