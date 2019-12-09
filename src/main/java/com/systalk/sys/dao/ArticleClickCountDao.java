package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.ArticleClickCount;


/**
 * 文章點擊次數(熱門文章)dao.
 */
@Repository("ArticleClickCountDao")
public interface ArticleClickCountDao extends JpaRepository<ArticleClickCount, Integer> {

	/**
	 * Find by atc seq in.
	 *
	 * @param AtcSeqs the atc seqs
	 * @return the list
	 */
	List<ArticleClickCount> findByAtcSeqIn(List<Integer> AtcSeqs);
	
	/**
	 * 前5熱門文章.
	 *
	 * @return the list
	 */
	List<ArticleClickCount> findTop5ByOrderByClickCountDesc();
}
