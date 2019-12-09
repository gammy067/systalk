package com.systalk.sys.controller.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

import com.systalk.sys.util.MessageUtil;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MessageUtil messageUtil;

	@ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception e, WebRequest request) {
		printErrorToLogger(e);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", "somerror");
		modelAndView.setViewName("/error");
		return modelAndView;
	}
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public ModelAndView exception(UsernameNotFoundException e, WebRequest request) {
		printErrorToLogger(e);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", "操作逾時!");
		modelAndView.setViewName("/backend/login");
		return modelAndView;
	}
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
		printErrorToLogger(e);
		
		logger.error("ResourceNotFoundException:, " + e);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", "somerror");
		modelAndView.setViewName("/404");
		return modelAndView;
	}

	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
		printErrorToLogger(e);

		logger.error("NoHandlerFoundException:, " + e);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", "somerror");
		modelAndView.setViewName("/404");
		return modelAndView;
	}
	
	@ExceptionHandler(value = StRuntimeException.class)
	public RedirectView tpuExceptionHandle(StRuntimeException e, HttpServletRequest request,
            HttpServletResponse response){
		printErrorToLogger(e);

		logger.error("StRuntimeException occurred, " + e);
		e.printStackTrace();
		
		return this.redirectToView(e.getMsgKey(), "/error", request);
	}

//	@ExceptionHandler
//	public ResponseEntity<String> handleResourceNotFoundException(NoHandlerFoundException nhre) {
//
//		return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
//	}

	// 如果我們要讓所有的@RequestMapping擁有此鍵值
//	@ModelAttribute
//	public void addAttribute(Model md) {
//		md.addAttribute("message", "你可以設定一些錯誤訊息");
//	}
	
	private RedirectView redirectToView(String msgKey, String targetUrl, HttpServletRequest request){
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(targetUrl);

		redirectView.setExposePathVariables(false);
		HttpSession session = request.getSession();
		session.setAttribute("msg", messageUtil.getMessage(msgKey));
		return redirectView;
	}
	
	private void printErrorToLogger(Throwable e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		logger.error(errors.toString());
	}
}
