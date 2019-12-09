package com.systalk.sys.web.form.view.vo.news;

import java.io.Serializable;

/**
 * 後臺 - 新聞專區分類VO.
 * */
public class NewsTypeVo implements Serializable {

	private Integer seq;
	private String typeValue;

	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
}
