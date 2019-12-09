package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.systalk.sys.dao.custom.BannerSettingDaoCustom;
import com.systalk.sys.model.BannerSetting;

/**
 * The Interface BannerSettingDao.
 */
@Repository("BannerSettingDao")
public interface BannerSettingDao extends JpaRepository<BannerSetting, Integer>, BannerSettingDaoCustom<BannerSetting>{
	
	/**
	 * Find by stauts.
	 *
	 * @param stauts the stauts
	 * @return the user
	 */
	List<BannerSetting> findByStatus(String status);
	
	 /**
 	 * 查找狀態1: 立即上架 0:指定日期上架 的項目
 	 * 上架日 <= 系統日
 	 * 下架日 > 系統日.
 	 *
 	 * @return the list
 	 */
 	@Query(value = "SELECT b FROM BannerSetting b WHERE b.status = '1' "
 			+ "OR ( b.status = '0' AND (b.publishingDate <= NOW() AND b.invalidDate > NOW()) ) ORDER BY b.publishingDate DESC")
	 List<BannerSetting> findEffectItems();
}
