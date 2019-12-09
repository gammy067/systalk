package com.systalk.sys.service;

import java.util.concurrent.Future;

import com.systalk.sys.web.form.view.bean.EmailContactInfo;
import com.systalk.sys.web.form.view.bean.EmailRpa;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendIndexForm;

/**
 * 前臺Service interface.
 * @author Richard
 * 2019.06.06
 * */
public interface FrontendService {
	/** 寄送Email - 聯絡我們. 
	 * @throws Exception */
	public Future<String> sendEmail(EmailContactInfo emailContactInfo) throws Exception;
	
	/** 寄送Email - RPA產品頁. 
	 * @throws Exception */
	public Future<String> sendEmail(EmailRpa emailRpa) throws Exception;
}
