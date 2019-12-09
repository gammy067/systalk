package com.systalk.sys.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.NewsArea;

/**
 * The Interface BannerSettingDao.
 */
@Repository("NewsAreaDao")
public interface NewsAreaDao extends JpaRepository<NewsArea, Integer> {
	
	/**
	 * Find by stauts.
	 *
	 * @param stauts the stauts
	 * @return the user
	 */
	List<NewsArea> findByStatus(String status);
	
	/**
	 * 前臺最新消息初始查詢.
	 * 上架.依上架日期排序 (分頁處理).
	 *
	 * @param status the status
	 * @return the list
	 */
	@Query(value = "SELECT n FROM NewsArea n WHERE "
				  + "( n.status = '1' OR "
				  + "  n.status = '0' AND (n.publishingDate <= NOW() AND n.invalidDate > NOW()) "
	 			  + ") "
	 			  + "ORDER BY n.publishingDate")
	 Page<NewsArea> initNewsAreaQuery(Pageable pageable);
	
    /**
     * 最新消息 搜尋(status:上架).
     *
     * @param typeSeq the type seq
     * @param queryDate the query date
     * @param searchText the search text
     * @param status the status
     * @return the list
     * 
     *  status 狀態(指定時間上架: - 0 , 立即上架 - 1 )
     *  functionType = 'NEWS'
     */
    @Query(value = "SELECT n FROM NewsArea n "
    		+ " left join NewsAreaTypeRef r on n.newsSeq = r.refNewsSeq"
    		+ " left join LocaleTranslation l on n.newsSeq = l.refSeq and l.functionType = 'NEWS' and l.localeCode=:locale"
    		+ " WHERE r.newsTypeSeq =:typeSeq AND "
    		+ "( n.status = '1' OR " 
    		+ "( n.status = '0' AND (n.publishingDate <= NOW() AND n.invalidDate > NOW())) "
    		+ ") "
    		+ "AND n.publishingDate >=:startDate AND n.publishingDate <:endDate "
    		+ "AND l.transTitle LIKE %:searchText% ORDER BY n.publishingDate")
    Page<NewsArea> queryNewsAreaByCondition(
    		@Param("typeSeq")Integer typeSeq,
    		@Param("startDate")Date startDate,
    		@Param("endDate")Date endDate,
    		@Param("searchText") String searchText,
    		@Param("locale") String locale,
    		Pageable pageable);
    
    
}
