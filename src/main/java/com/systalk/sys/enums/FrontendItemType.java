package com.systalk.sys.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *  首頁項目 enum.
 * */
public enum FrontendItemType {

	/** banner: 輪播功能.*/
	BANNER(0, "BANNER"),
	
	
	UNKNOW(-9, "UNKNOW");

	private int index;

	private String type;


	private FrontendItemType(int index, String type) {
		this.index = index;
		this.type = type;
	}

	public static FrontendItemType getItemType(String s) {
		for (FrontendItemType itemType : values()) {
			if (StringUtils.equals(itemType.getType(), s)) {
				return itemType;
			}
		}
		return UNKNOW;
	}

	public static FrontendItemType getItemType(int i) {
		for (FrontendItemType itemType : values()) {
			if (itemType.getIndex() == i) {
				return itemType;
			}
		}
		return UNKNOW;
	}

	public int getIndex() {
		return index;
	}

	public String getType() {
		return type;
	}
}
