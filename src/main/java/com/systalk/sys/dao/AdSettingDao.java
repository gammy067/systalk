package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.AdSetting;


/**
 * 廣告版位設定 dao.
 */
@Repository("AdSettingDao")
public interface AdSettingDao extends JpaRepository<AdSetting, Integer> {
	
	/**
	 * Find by status.
	 *
	 * @param stauts the status
	 * @return the list
	 */
	List<AdSetting> findByStatus(String status);
}
