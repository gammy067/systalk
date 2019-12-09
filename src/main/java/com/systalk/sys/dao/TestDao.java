package com.systalk.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.systalk.sys.dao.custom.ArticleDaoCustom;
import com.systalk.sys.model.Article;
import com.systalk.sys.model.Test;



/**
 * 思拓研究室文章 dao.
 */
@Repository
public interface TestDao extends JpaRepository<Test, Long> {
	
	 @Query(value = "select auto_increment from information_schema.tables where table_schema='systalk' and table_name='test'", nativeQuery = true)
	 int getNextSeq();
}
