package com.systalk.sys.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the code_table database table.
 * 
 */
@Entity
@Table(name="code_table")
@NamedQuery(name="CodeTable.findAll", query="SELECT c FROM CodeTable c")
public class CodeTable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="code_seq")
	private int codeSeq;

	@Column(name="code_name")
	private String codeName;

	private String status;

	private String type;
	
	private String code;

	//bi-directional many-to-one association to NewsArea
//	@OneToMany(mappedBy="codeTable")
//	private List<NewsArea> newsAreas;

	public CodeTable() {
	}

	public int getCodeSeq() {
		return this.codeSeq;
	}

	public void setCodeSeq(int codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
//
//	public List<NewsArea> getNewsAreas() {
//		return this.newsAreas;
//	}
//
//	public void setNewsAreas(List<NewsArea> newsAreas) {
//		this.newsAreas = newsAreas;
//	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	public NewsArea addNewsArea(NewsArea newsArea) {
//		getNewsAreas().add(newsArea);
//		newsArea.setCodeTable(this);
//
//		return newsArea;
//	}
//
//	public NewsArea removeNewsArea(NewsArea newsArea) {
//		getNewsAreas().remove(newsArea);
//		newsArea.setCodeTable(null);
//
//		return newsArea;
//	}

}