package com.systalk.sys.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *  狀態(指定時間上架 - 0 ,立即上架 - 1 ) enum.
 * */
public enum Status {
	
	/** 指定時間上架: 0.*/
	ASSIGN("0"),
	
	/** 立即上架上架: 1.*/
	EFFECTIVE("1"),
	
	UNKNOW("UNKNOW");

	private String status;

	private Status(String status) {
		this.status = status;
	}

	public static Status getRoleType(String s) {
		for (Status status : values()) {
			if (StringUtils.equals(status.getCode(), s)) {
				return status;
			}
		}
		return UNKNOW;
	}

	public String getCode() {
		return status;
	}
}
