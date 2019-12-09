package com.systalk.sys.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *  語系enum.
 * */
public enum LocaleEnum {

	/** 語系:zh_TW 繁體中文.*/
	zh_TW("zh_TW"),
	
	/** 語系:en_US 英文.*/
	en_US("en_US"),
	
	UNKNOW("UNKNOW");

	private String locale;

	private LocaleEnum(String locale) {
		this.locale = locale;
	}

	public static LocaleEnum getFunctionType(String s) {
		for (LocaleEnum locale : values()) {
			if (StringUtils.equals(locale.getCode(), s)) {
				return locale;
			}
		}
		return UNKNOW;
	}

	public String getCode() {
		return locale;
	}
}
