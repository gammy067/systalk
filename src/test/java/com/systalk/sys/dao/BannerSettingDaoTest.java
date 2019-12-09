package com.systalk.sys.dao;


import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.systalk.sys.BaseConfigTest;
import com.systalk.sys.enums.Status;
import com.systalk.sys.model.BannerSetting;
import com.systalk.sys.util.JsonUtil;

public class BannerSettingDaoTest extends BaseConfigTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BannerSettingDao bannerSettingDao;
	
    @Test
	public void findByStatus () {
    	//List<BannerSetting> list = bannerSettingDao.findByStatus(Status.EFFECTIVE.getCode());
    	//logger.debug("JsonString == " + JsonUtil.toJsonString(list));
	}
}
