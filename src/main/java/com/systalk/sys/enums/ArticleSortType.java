package com.systalk.sys.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *  文章排序 類別 enum.
 * */
public enum ArticleSortType {
	
	/** 排序 :上傳時間.*/
	PUBLISHING_DATE("publishingDate"),
	
	/** 排序 :作者*/
	AUTHOR("author"),
	
	UNKNOW("UNKNOW");

	private String code;

	private ArticleSortType(String code) {
		this.code = code;
	}

	public static ArticleSortType getSortType(String s) {
		for (ArticleSortType type : values()) {
			if (StringUtils.equals(type.getCode(), s)) {
				return type;
			}
		}
		return UNKNOW;
	}

	public String getCode() {
		return code;
	}
}
