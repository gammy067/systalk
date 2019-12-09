package com.systalk.sys.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *  成功案例狀態(下架 - 0 ,上架 - 1 ) enum.
 * */
public enum SuccessCaseStatus {
	
	/** 下架: 0.*/
	INVALID("0"),
	
	/** 上架: 1.*/
	EFFECTIVE("1"),
	
	UNKNOW("UNKNOW");

	private String status;

	private SuccessCaseStatus(String status) {
		this.status = status;
	}

	public static SuccessCaseStatus getRoleType(String s) {
		for (SuccessCaseStatus status : values()) {
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
