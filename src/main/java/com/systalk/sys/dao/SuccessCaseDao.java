package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.SuccessCase;

/**
 * The Interface SuccessCaseDao.
 */
@Repository("SuccessCaseDao")
public interface SuccessCaseDao extends JpaRepository<SuccessCase, Integer> {
	
	/**
	 * Find by stauts.
	 *
	 * @param stauts the stauts
	 * @return the user
	 */
	List<SuccessCase> findByStatus(String status);
}
