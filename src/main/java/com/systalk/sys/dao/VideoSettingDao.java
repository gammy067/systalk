package com.systalk.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.VideoSetting;


/**
 * The Interface VideoSettingDao.
 */
@Repository("VideoSettingDao")
public interface VideoSettingDao extends JpaRepository<VideoSetting, Integer> {
	
	/**
	 * Find by status.
	 *
	 * @param stauts the status
	 * @return the list
	 */
	VideoSetting findOneByStatus(String status);
	
	
	VideoSetting findTopByStatusOrderByPublishingDateDesc(String status);
	
	/**
	 * 查詢 by 更新日期
	 *
	 * @param status the status
	 * @return the video setting
	 */
	VideoSetting findTopByStatusOrderByUpdateDateDesc(String status);
	
}
