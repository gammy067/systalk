package com.systalk.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.BlocksizeSetting;

/**
 * The Interface BannerSettingDao.
 */
@Repository("BlocksizeSettingDao")
public interface BlocksizeSettingDao extends JpaRepository<BlocksizeSetting, Integer> {
	
	/**
	 * Find by blockName.
	 *
	 * @param blockName the blockName
	 * @returnList<BannerSetting>
	 */
	BlocksizeSetting findByBlockName(String blockName);
}
