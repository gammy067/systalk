package com.systalk.sys.controller;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.systalk.sys.auth.model.CustomUserDetails;
import com.systalk.sys.controller.base.BackendBaseContoller;
import com.systalk.sys.model.User;
import com.systalk.sys.service.FrontendIndexService;
import com.systalk.sys.util.MessageUtil;
import com.systalk.sys.util.SessionUtil;

/**
 * 後臺登入Contoller.
 * @author Richard
 * 2019.06.06
 * */
@Controller
public class BackendLoginContoller extends BackendBaseContoller{

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MessageUtil messageUtil;

	@GetMapping(value = {"/login", "/backend/login"})
	public ModelAndView login(Model model, Principal user, HttpServletRequest request, Authentication authentication,
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) throws ServletException {
		logger.info(">>> /backend/login");
		
		LocaleContextHolder.getLocale().equals(Locale.US);
		LocaleContextHolder.getLocale();

		User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
		// 該session已經登入過
		if(loginUser != null) {
			return new ModelAndView("redirect:/backend/index");
		}
		
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/backend/login");
        if (error != null) {
        	modelAndView.addObject("error", messageUtil.getMessage("backend.login.error"));
        }
 
        if (logout != null) {
        	modelAndView.addObject("msg", messageUtil.getMessage("backend.logout.msg"));
        }

		return modelAndView;
	}
}
