package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.Logowall;

/**
 * The Interface LogoWallDao.
 */
@Repository("LogowallDao")
public interface LogowallDao extends JpaRepository<Logowall, Integer> {
	
	/**
	 * Find by stauts.
	 *
	 * @param stauts the stauts
	 * @return the user
	 */
	List<Logowall> findByStatus(String status);

	/**
	 * Find by status order by logo order desc.
	 *
	 * @param status the status
	 * @return the list
	 */
	List<Logowall> findByStatusOrderByLogoOrderAsc(String status);
	
	@Query(value = "SELECT max(l.logoOrder) FROM Logowall l")
	public Integer getMaxLogoOrder();
	
//	@Query("select l from Logowall l where l.status=:status order by l.logoOrder desc")
//	List<Logowall> findStatusOrderByLogoOrder(@Param("status") String status);
}
