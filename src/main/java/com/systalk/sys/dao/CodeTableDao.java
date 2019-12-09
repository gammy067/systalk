package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.CodeTable;


/**
 * 分類代碼表 dao.
 */
@Repository("CodeTableDao")
public interface CodeTableDao extends JpaRepository<CodeTable, Integer> {
	
	/**
	 * Find by status and type.
	 *
	 * @param status the status
	 * @return the list
	 */
	List<CodeTable> findByStatusAndType(String status, String type);
}
