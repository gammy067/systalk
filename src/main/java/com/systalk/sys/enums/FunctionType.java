package com.systalk.sys.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *  功能名稱enum (多語系抓取對應功能).
 * */
public enum FunctionType {

	/** 功能名稱:BANNER.*/
	BANNER("BANNER"),
	
	/** 功能名稱:NEWS.*/
	NEWS("NEWS"),
	
	/** 功能名稱:VIDEO.*/
	VIDEO("VIDEO"),
	
	/** 功能名稱:SUCCESS_CASE.*/
	SUCCESS_CASE("SUCCESS_CASE"),
	
	UNKNOW("UNKNOW");

	private String functionType;

	private FunctionType(String functionType) {
		this.functionType = functionType;
	}

	public static FunctionType getFunctionType(String s) {
		for (FunctionType functionType : values()) {
			if (StringUtils.equals(functionType.getCode(), s)) {
				return functionType;
			}
		}
		return UNKNOW;
	}

	public String getCode() {
		return functionType;
	}
}
