package com.systalk.sys.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 設定區塊資料數量 enum.
 * */
public enum BlocksizeSettingEnum {

	/** BANNER_MAX_COUNT 輪播圖最大上架數.*/
	BANNER_MAX_COUNT("BANNER_MAX_COUNT"),
	
	/** 行銷影片 最大上架數.*/
	VIDEO_MAX_COUNT("VIDEO_MAX_COUNT"),
	
	/** 最新消息一頁顯示幾筆.*/
	NEWS_PAGE_MAX_COUNT("NEWS_PAGE_MAX_COUNT"),
	
	
	UNKNOW("UNKNOW");

	private String blockName;


	private BlocksizeSettingEnum(String blockName) {
		this.blockName = blockName;
	}

	public static BlocksizeSettingEnum getBlockName(String blockName) {
		for (BlocksizeSettingEnum bs : values()) {
			if (StringUtils.equals(bs.getBlockName(), blockName)) {
				return bs;
			}
		}
		return UNKNOW;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
}
