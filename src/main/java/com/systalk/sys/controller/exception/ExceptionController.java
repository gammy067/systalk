package com.systalk.sys.controller.exception;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ExceptionController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping("/error")
	public ModelAndView errorPage(Principal user, Model model, RedirectAttributes redirectAttributes){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorSomething", "somerror");
		modelAndView.setViewName("/error");
		return modelAndView;
	}
//	
//	@RequestMapping("/tomcatErrorPage")
//	public String tomcatErrorPage(HttpServletRequest request, HttpServletResponse response, 
//			RedirectAttributes redirectAttributes) {
//		logger.error("[TomcatErrorPage] Start");
//		String goingTo = null;
//		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
//		if (requestUri == null) {
//			requestUri = "Unknown";
//		}
//		logger.error("requestUri:" + requestUri);
//		// retrieve some useful information from the request
//		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
//		logger.error("statusCode:" + statusCode);
//		//直接key URL進來，此兩者會是null
//		if(statusCode == null && throwable == null){
//			logger.error("[TomcatErrorPage] not from error, redirect to index!");
//			return "redirect:/index";
//		}
//		
//		if(statusCode.intValue() == 404){//找不到頁面
//			
//			redirectAttributes.addFlashAttribute("msg", messageUtil.getMessage("error.404.msg"));
//			
//			goingTo = "redirect:/index";
//		}else{//
//			if(throwable != null){
//				logger.error("Exception occurred, " + throwable.getMessage());
//				throwable.printStackTrace();
//			}else{
//				logger.error("Unknow error occurred");
//			}
//			redirectAttributes.addFlashAttribute("msg", messageUtil.getMessage("error.500.msg"));
////			redirectView = this.redirectToView("error.500.msg", "/error", request);
//			goingTo = "redirect:/error";
//		}
//		
//		logger.error("[TomcatErrorPage] End");
//		return goingTo;
//	}
}