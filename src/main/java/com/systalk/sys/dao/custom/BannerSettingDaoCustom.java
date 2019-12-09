package com.systalk.sys.dao.custom;

import com.systalk.sys.model.BannerSetting;

/**
 * 思拓研究室文章 dao.
 *
 * @param <T> the generic type
 */

public interface BannerSettingDaoCustom <T> {
	
	/**
	 * Find all to view bean.
	 *
	 * @return the list
	 */
	public void saveWithoutNull (BannerSetting bannerSetting);
}
