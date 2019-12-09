package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the article_setting database table.
 * 
 */
@Embeddable
public class ArticleSettingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="atc_seq", insertable=false, updatable=false)
	private int atcSeq;

	private String type;

	public ArticleSettingPK() {
	}
	public int getAtcSeq() {
		return this.atcSeq;
	}
	public void setAtcSeq(int atcSeq) {
		this.atcSeq = atcSeq;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ArticleSettingPK)) {
			return false;
		}
		ArticleSettingPK castOther = (ArticleSettingPK)other;
		return 
			(this.atcSeq == castOther.atcSeq)
			&& this.type.equals(castOther.type);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.atcSeq;
		hash = hash * prime + this.type.hashCode();
		
		return hash;
	}
}