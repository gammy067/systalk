package com.systalk.sys.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *  代碼類型 enum.
 * */
public enum CodeType {

	/** 0: 技術文章 - article.*/
	ARTICLE("1", "article"),

	/** 1: 新聞專區 - news.*/
	NEWS("2", "news"),
	
	/** 2: 標籤 - lable.*/
	LABEL("3", "lable"),
	
	/** 3: 文章排序 - article_sort.*/
	ARTICLE_SORT("4", "article_sort"),
	
	UNKNOW("-9", "UNKNOW");

	private String code;

	private String type;


	private CodeType(String code, String type) {
		this.code = code;
		this.type = type;
	}

	public static CodeType getItemType(String code) {
		for (CodeType itemType : values()) {
			if (StringUtils.equals(itemType.getCode(), code)) {
				return itemType;
			}
		}
		return UNKNOW;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
