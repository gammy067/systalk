package com.systalk.sys.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.systalk.sys.BaseConfigTest;

/**
 * 聯絡我們 寄信測試.
 */
public class EmailContactTest extends BaseConfigTest {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JavaMailSender mailsender;

	@Value("${st.sys.mail}")
	private String sysMail;

	/**
	 * Before.
	 */
	@Before
	public void before() {

	}

	/**
	 * 取得首頁顯示 viewForm.
	 *
	 * @return the frontend index form test
	 * @throws MessagingException 
	 */
	@Test
	public void sendEmail() throws MessagingException {

//		// 建立郵件訊息
//		MimeMessage mailMessage = mailsender.createMimeMessage();
//		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
//
//		// 設定收件人、寄件人、主題與內文
//		messageHelper.setTo("gammy521@gmail.com");
//		//寄件者
//		messageHelper.setFrom(new InternetAddress(sysMail));
//		messageHelper.setSubject("Test");
//		messageHelper.setText(
//				"<html><head></head><body><h1>Hello! Spring!" + "</h1><img src=\"cid:img\">" + "</body></html>",
//				true);
//		
//		FileSystemResource img = new FileSystemResource(new File("C:\\work\\workspace\\st\\src\\main\\webapp\\File\\frontend\\banner\\1.jpg"));
//		messageHelper.addInline("img", img);
//
//		// 傳送郵件
//		mailsender.send(mailMessage);
//
//		logger.debug("郵件傳送成功...");
	}
}
