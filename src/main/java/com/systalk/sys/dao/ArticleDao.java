package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.systalk.sys.dao.custom.ArticleDaoCustom;
import com.systalk.sys.model.Article;


/**
 * 思拓研究室文章 dao.
 */
@Repository
public interface ArticleDao extends JpaRepository<Article, Long>, ArticleDaoCustom<Article> {
	
	/**
	 * Find by status.
	 *
	 * @param stauts the status
	 * @return the list
	 */
	List<Article> findByStatus(String status);

	/**
	 * 查詢文章 上架日期排序.
	 *
	 * @param status the status
	 * @return the list
	 */
	List<Article> findByStatusOrderByPublishingDateDesc(String status);


//	/**
//	 * 模糊查詢(作者、標題、標籤).
//	 *
//	 * @param queryParam the query param
//	 * @return the list
//	 */
//	@Query("select a from Article a inner join CodeTable c on a.categorySeq = c.codeSeq where a.status=1 and"
//			+ " a.atcTopic like %?1% or a.author like %?1% or c.codeName like %?1%")
//	List<Article> fuzzyQueryByJoinCodeTable(String queryParam);
	
}
