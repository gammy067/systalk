package com.systalk.sys.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.controller.base.FrontendBaseContoller;
import com.systalk.sys.controller.validator.response.ValidateJsonRespone;
import com.systalk.sys.service.FrontendService;
import com.systalk.sys.util.MessageUtil;
import com.systalk.sys.util.ValidateUtil;
import com.systalk.sys.web.form.view.bean.EmailRpa;

/**
 * RPA產品專區Contoller.
 * @author Richard
 * 2019.11.08
 * */
@Controller
public class FrontendRpaContoller extends FrontendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/** The frontend index service. */
	@Autowired
	private FrontendService frontendService;
	
	/**
	 * RPA產品專區.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
    @RequestMapping(value = {"/rpa"},  
    method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView index(Model model) throws Exception {
		logger.info(">>> /rpa");
		ModelAndView modelAndView = new ModelAndView("/rpa");

		return modelAndView;
	}
    
    /**
     * Rpa產品頁諮詢 mail.
     *
     * @param emailContactInfo the email contact info
     * @param result the result
     * @return the validate json respone
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/rpa/doSendRpaEmailAction"},  
    method = {RequestMethod.POST }, 
	produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ValidateJsonRespone doSendRpaEmailAction(
			@ModelAttribute @Valid EmailRpa emailRpa, BindingResult result) throws Exception {
		
		logger.info(">>> /rpa/doSendRpaEmailAction");
		// 驗證訊息回傳 json.
		ValidateJsonRespone respone = new ValidateJsonRespone();
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			// Get error message
			errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

			respone.setValidate(false);
			respone.setErrorMessages(errors);
		} else {
			// Google reCAPTCHA V2 驗證
			if(!ValidateUtil.isCaptchaValid(emailRpa.getCaptchaResponse())) {
				// 驗證碼錯誤
				errors.put("captchaResponse", messageUtil.getCurrentLocaleMessage("validate.captcha.error"));

				respone.setValidate(false);
				respone.setErrorMessages(errors);
				return respone;
			}

			try {
				respone.setValidate(true);
				// 寄送Email - 聯絡我們. 
				Future<String> future  = frontendService.sendEmail(emailRpa);

	            Calendar cal = Calendar.getInstance();
	            // 等待毫秒數 (暫不等待)
	            cal.add(Calendar.MILLISECOND, 0);
	            Date endDateTime = cal.getTime();

				Date now = new Date();
	            while (!future.isDone() && now.before(endDateTime)) {
	            	logger.info("寄件中... " + emailRpa.getRpaName());  
	                // Thread.sleep(500);
	                now = new Date();
	            }
				
			} catch(Exception e){
				logger.error(">>> doSendEmailAction error" + e);
				respone.setHasException(true);
			}
		}
		return respone;
	}
}
