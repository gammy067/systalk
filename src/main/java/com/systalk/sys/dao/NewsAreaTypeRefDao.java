package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.NewsAreaTypeRef;

/**
 * The Interface NewsAreaTypeRefDao.
 */
@Repository("NewsAreaTypeRefDao")
public interface NewsAreaTypeRefDao extends JpaRepository<NewsAreaTypeRef, Integer> {

	List<NewsAreaTypeRef> findByRefNewsSeq(Integer newsSeq);
	
	List<NewsAreaTypeRef> findByLocale(String locale);
	
	List<NewsAreaTypeRef> findByNewsTypeSeqIn(List<Integer> newsTypeSeq);
	
	
	/**
	 * 刪除多語系關聯的最新消息 .
	 *
	 * @param newsSeqs the news seqs
	 * @return the int
	 */
	int deleteByRefNewsSeq(Integer newsSeq);
}
