package com.systalk.sys.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.controller.base.FrontendBaseContoller;
import com.systalk.sys.controller.validator.response.ValidateJsonRespone;
import com.systalk.sys.service.FrontendIndexService;
import com.systalk.sys.service.FrontendService;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.web.form.view.bean.EmailContactInfo;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendIndexForm;

/**
 * 前臺首頁Contoller.
 * @author Richard
 * 2019.06.06
 * */
@Controller
public class FrontendIndexContoller extends FrontendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/** The frontend index service. */
	@Autowired
	FrontendIndexService frontendIndexService;
	
	@Autowired
	FrontendService frontendService;
	
    @Autowired
    LocaleResolver localeResolver;

//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
//    }

	/**
	 * Index.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = {"/index", "/"},  method = { RequestMethod.GET, RequestMethod.POST })
//    produces = "application/json;charset=utf-8")
	public ModelAndView index(Model model) throws Exception {
		logger.info(">>> /index");
		ModelAndView modelAndView = new ModelAndView("/index");

		FrontendIndexForm viewForm = frontendIndexService.initIndexForm();
		
		modelAndView.addObject("viewForm", viewForm);
		
//		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
//		  if (inputFlashMap != null) {
//			 EmailContactInfo emailContactInfo = (EmailContactInfo) inputFlashMap.get("emailContactInfo");
//		  }
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("indexViewForm", indexViewForm);
//		
//		return mapper.writeValueAsString(map);
		
		return modelAndView;
	}

	/**
	 * 寄送Email - 聯絡我們.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @return the string
	 * @throws Exception 
	 */
//	@RequestMapping(value = {"/doSendEmailAction"}, method = RequestMethod.POST)
//	public ModelAndView doSendEmailAction(Model model,
//	        @Valid @ModelAttribute("viewForm") FrontendIndexForm viewForm,
//	        BindingResult theBindingResult) throws Exception {
//
//		logger.info("doSendEmailAction()");
//		
////		redirectAttributes.addFlashAttribute(emailContactInfo);
//
//		viewForm = frontendIndexService.initIndexForm();
//		model.addAttribute("viewForm", viewForm);
//
//		if (theBindingResult.hasErrors()) {
//
//			return new ModelAndView("/index");
//		} else {
//			// 寄送Email - 聯絡我們. 
//			frontendIndexService.sendEmail(viewForm.getEmailContactInfo());
////			model.addAttribute("emailContactInfo", new EmailContactInfo());
//		}
//		
//		return new ModelAndView("/index");
//	}

    @RequestMapping(value = {"/doSendEmailAction"},  
    method = {RequestMethod.POST }, 
	produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ValidateJsonRespone doSendEmailAction(
			@ModelAttribute @Valid EmailContactInfo emailContactInfo, BindingResult result) throws Exception {
		
		logger.info(">>> /doSendEmailAction");

		// 驗證訊息回傳 json.
		ValidateJsonRespone respone = new ValidateJsonRespone();
		if (result.hasErrors()) {
			// Get error message
			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

			respone.setValidate(false);
			respone.setErrorMessages(errors);
		} else {
			respone.setValidate(true);
			try {
				// 寄送Email - 聯絡我們. 
				Future<String> future  = frontendService.sendEmail(emailContactInfo);

	            Calendar cal = Calendar.getInstance();
	            // 等待毫秒數 (暫不等待)
	            cal.add(Calendar.MILLISECOND, 0);
	            Date endDate = cal.getTime();

				Date now = new Date();
	            while (!future.isDone() && now.before(endDate)) {
	            	logger.info("寄件中... " + emailContactInfo.getName());  
	                // Thread.sleep(500);
	                now = new Date();
	            }
				
			} catch(Exception e){
				logger.error(">>> doSendEmailAction error" + e);
				respone.setHasException(true);
			}
		}
		return respone;

		
//		if (result.hasErrors()) {
////			return getAjaxSuccessJson();
//		} else {
//			// 寄送Email - 聯絡我們. 
////			frontendIndexService.sendEmail(viewForm.getEmailContactInfo());
////			model.addAttribute("emailContactInfo", new EmailContactInfo());
//		}
//
//		
////		寄送Email - 聯絡我們. 
////		frontendIndexService.sendEmail(emailContactInfo);
//		
//		return getAjaxSuccessJson();
	}
    
    @RequestMapping(value = {"/changeLocale"},  method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView changeLocale(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("lang") String lang, 
			@RequestParam("currentUrl") String currentUrl) throws Exception {
		logger.info(">>> /changeLocale");

		
		// 取得語系 Locale object
		Locale changLocale = CommonUtil.getChangeLocale(lang);
		
		localeResolver.setLocale(request, response, changLocale);
		
		if(StringUtils.contains(currentUrl, "doQueryNewsAction")) {
			return new ModelAndView("redirect:/newsArea");
		}

		return new ModelAndView("redirect:" + StringUtils.replace(currentUrl, request.getContextPath(), ""));
	}

}
