package com.systalk.sys.service.impl;

import java.util.concurrent.Future;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systalk.sys.service.FrontendService;
import com.systalk.sys.service.impl.helper.FrontendIndexServiceHelper;
import com.systalk.sys.web.form.view.bean.EmailContactInfo;
import com.systalk.sys.web.form.view.bean.EmailRpa;

/**
 * 前臺 serviceImpl.
 */
@Transactional(rollbackFor = Exception.class)
@Service("FrontendServiceImpl")
public class FrontendServiceImpl implements FrontendService {
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private HttpServletRequest request;

	@Autowired
	private JavaMailSender mailsender;

	/**
	 * 發送"聯絡我們"信件.
	 *
	 * @param emailContactInfo the email contact info
	 * @throws Exception the exception
	 */
	@Override
	@Async
	public Future<String> sendEmail(EmailContactInfo emailContactInfo) throws Exception {
		FrontendIndexServiceHelper helper = FrontendIndexServiceHelper.getInstance();
		Future<String> future = new AsyncResult<String>(null);

		// 建立郵件訊息
		MimeMessage mailMessage = mailsender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
		
		try {
			// 設置信件資訊
			helper.setContactInfoEmail(messageHelper, emailContactInfo);

			// 傳送郵件
			mailsender.send(mailMessage);
			logger.debug("郵件傳送成功...  姓名:" + emailContactInfo.getName());
			
			future = new AsyncResult<String>("success");
		} catch(Exception e) {
			future = new AsyncResult<String>("fail :" + e);
		}
		
		return future;
	}

	@Override
	@Async
	public Future<String> sendEmail(EmailRpa emailRpa) throws Exception {
		FrontendIndexServiceHelper helper = FrontendIndexServiceHelper.getInstance();
		Future<String> future = new AsyncResult<String>(null);

		// 建立郵件訊息
		MimeMessage mailMessage = mailsender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
		
		try {
			// 設置RPA信件資訊
			helper.setRpaEmail(messageHelper, emailRpa);

			// 傳送郵件
			mailsender.send(mailMessage);
			logger.debug("郵件傳送成功...  姓名:" + emailRpa.getRpaName());
			
			future = new AsyncResult<String>("success");
		} catch(Exception e) {
			future = new AsyncResult<String>("fail :" + e);
		}
		
		return future;
	}
}
