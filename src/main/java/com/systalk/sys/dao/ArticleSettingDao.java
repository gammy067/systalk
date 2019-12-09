package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.ArticleSetting;


/**
 * 置頂文章dao.
 */
@Repository("ArticleSettingDao")
public interface ArticleSettingDao extends JpaRepository<ArticleSetting, Integer> {
	
	/**
	 * Find by status.
	 *
	 * @param stauts the status
	 * @return the list
	 */
	List<ArticleSetting> findByStatus(String status);
}
