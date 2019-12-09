package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.NewsAreaType;

/**
 * The Interface BannerSettingDao.
 */
@Repository("NewsAreaTypeDao")
public interface NewsAreaTypeDao extends JpaRepository<NewsAreaType, Integer> {
	
	void deleteByNewsAreaTypeSeqInAndLocale(List<Integer> seqs, String locale);
	
	List<NewsAreaType> findByLocale(String locale);
	
	NewsAreaType findByNewsAreaTypeSeqAndLocale(Integer newsAreaTypeSeq,String locale);
}
