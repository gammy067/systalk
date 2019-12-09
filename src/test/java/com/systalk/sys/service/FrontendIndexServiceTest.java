package com.systalk.sys.service;


import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.systalk.sys.BaseConfigTest;
import com.systalk.sys.util.JsonUtil;
import com.systalk.sys.web.form.view.bean.EmailContactInfo;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendIndexForm;

public class FrontendIndexServiceTest extends BaseConfigTest{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FrontendIndexService frontendIndexService;
	
	@Autowired
	private FrontendService frontendService;

	@Before
	public void before(){
		
	}

	/** 取得首頁顯示 viewForm. 
	 * @throws Exception */
	@Test
	@Transactional
	public void getFrontendIndexFormTest() throws Exception {
		FrontendIndexForm indexViewForm = frontendIndexService.initIndexForm();
		
		logger.debug(JsonUtil.toJsonString(indexViewForm));
	}
	
	
	/**
	 *  寄送Email - 聯絡我們. 
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void sendEmail() throws Exception {
		EmailContactInfo emailContactInfo = new EmailContactInfo();
		emailContactInfo.setName("test");
		emailContactInfo.setEmail("gammy521@gmail.com");
		emailContactInfo.setPhone("1234567890");
		emailContactInfo.setContent("test content");

		frontendService.sendEmail(emailContactInfo);
	}
}
