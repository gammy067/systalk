package com.systalk.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.controller.base.FrontendBaseContoller;
import com.systalk.sys.service.FrontendIndexService;

/**
 * 產品專區Contoller.
 * @author Richard
 * 2019.10.09
 * */
@Controller
public class FrontendChatContoller extends FrontendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/** The frontend index service. */
	@Autowired
	FrontendIndexService frontendIndexService;

	/**
	 * 產品專區.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
    @RequestMapping(value = {"/chat"},  
    method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView index(Model model) throws Exception {
		logger.info(">>> /chat");
		ModelAndView modelAndView = new ModelAndView("/chat");

		return modelAndView;
	}
}
