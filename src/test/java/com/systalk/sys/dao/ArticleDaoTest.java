package com.systalk.sys.dao;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.systalk.sys.BaseConfigTest;
import com.systalk.sys.model.TestPK;
import com.systalk.sys.web.form.view.bean.ArticleBean;


/**
 * 文章點擊次數(熱門文章)dao.
 */
public class ArticleDaoTest extends BaseConfigTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private TestDao testDao;
	
    @Test
	public void fuzzyQueryByJoinCodeTable () {
	
    	com.systalk.sys.model.Test test = new com.systalk.sys.model.Test();
    	TestPK pk = new TestPK();
    	pk.setCategoryId(testDao.getNextSeq());
    	pk.setTestValue("222");

    	test.setId(pk);
    	test.setName("111");
    	//List<ArticleBean> list = articleDao.fuzzyQueryByJoinCodeTable(1, "test", "author");
    	testDao.save(test);
    	//System.out.print("size() = " + list.size());
	}

}
