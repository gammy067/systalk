package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the logowall database table.
 * 
 */
@Entity
@Table(name="logowall")
@NamedQuery(name="Logowall.findAll", query="SELECT l FROM Logowall l")
public class Logowall  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="logoWall_seq")
	private int logoWallSeq;

	@Column(name="file_name")
	private String fileName;

	@Column(name="logo_order")
	private int logoOrder;

	@Temporal(TemporalType.DATE)
	@Column(name="publishing_date")
	private Date publishingDate;

	private String status;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_seq")
	private User user;

	public Logowall() {
	}

	public int getLogoWallSeq() {
		return logoWallSeq;
	}


	public void setLogoWallSeq(int logoWallSeq) {
		this.logoWallSeq = logoWallSeq;
	}


	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getLogoOrder() {
		return this.logoOrder;
	}

	public void setLogoOrder(int logoOrder) {
		this.logoOrder = logoOrder;
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

}