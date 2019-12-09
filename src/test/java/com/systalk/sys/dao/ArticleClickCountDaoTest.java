package com.systalk.sys.dao;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.systalk.sys.BaseConfigTest;
import com.systalk.sys.model.ArticleClickCount;
import com.systalk.sys.util.JsonUtil;


/**
 * 文章點擊次數(熱門文章)dao.
 */
public class ArticleClickCountDaoTest extends BaseConfigTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ArticleClickCountDao articleClickCountDao;
	
    @Test
	public void findByStatus () {
    	//List<ArticleClickCount> list = articleClickCountDao.findTop5ByOrderByClickCountDesc();
    	//logger.debug("JsonString == " + JsonUtil.toJsonString(list));
	}

}
