package com.systalk.sys.dao;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.systalk.sys.BaseConfigTest;
import com.systalk.sys.enums.Status;
import com.systalk.sys.model.ArticleSetting;
import com.systalk.sys.util.JsonUtil;


/**
 * 文章點擊次數(熱門文章)dao.
 */
public class ArticleSettingDaoTest extends BaseConfigTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ArticleSettingDao articleSettingDao;
	
    @Test
	public void findByStatus () {
    	List<ArticleSetting> list = articleSettingDao.findByStatus(Status.EFFECTIVE.getCode());
    	//logger.debug("JsonString == " + JsonUtil.toJsonString(list));
	}

}
