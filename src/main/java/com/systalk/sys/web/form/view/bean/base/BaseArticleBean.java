package com.systalk.sys.web.form.view.bean.base;

import java.util.Date;

/**
 * 思拓研究室 - base - 文章顯示bean.
 */
public class BaseArticleBean {

	/** The seq. */
	// 流水號
	private Integer seq;
	
	/** The topic. */
	// 文章主題
	private String topic = "";
	
	/** The user seq. */
	// 編輯人員序號
	private int userSeq;
	
	/** The author. */
	// 作者姓名
	private String author = "";
	
	/** The create date. */
	// 建立時間
	private Date createDate;
	
	/** The update date. */
	// 更新時間 YYYYMMDDHHMISS
	private Date updateDate;
	
	/** The pushing date. */
	// 上版時間 YYYYMMDDHHMISS
	private Date pushingDate;
	
	/** The status. */
	// 狀態(下架 - 0 ,上架 - 1 )
	private String status;
	
	/** The category seq. */
	// 主題分類流水號
	private int categorySeq;
	
	/** The content. */
	// 內容
	private String content = "";

	/**
	 * Gets the seq.
	 *
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * Sets the seq.
	 *
	 * @param seq the new seq
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * Gets the topic.
	 *
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Sets the topic.
	 *
	 * @param topic the new topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Gets the user seq.
	 *
	 * @return the user seq
	 */
	public int getUserSeq() {
		return userSeq;
	}

	/**
	 * Sets the user seq.
	 *
	 * @param userSeq the new user seq
	 */
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author the new author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the update date.
	 *
	 * @return the update date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the update date.
	 *
	 * @param updateDate the new update date
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * Gets the pushing date.
	 *
	 * @return the pushing date
	 */
	public Date getPushingDate() {
		return pushingDate;
	}

	/**
	 * Sets the pushing date.
	 *
	 * @param pushingDate the new pushing date
	 */
	public void setPushingDate(Date pushingDate) {
		this.pushingDate = pushingDate;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the category seq.
	 *
	 * @return the category seq
	 */
	public int getCategorySeq() {
		return categorySeq;
	}

	/**
	 * Sets the category seq.
	 *
	 * @param categorySeq the new category seq
	 */
	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
