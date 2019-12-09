package com.systalk.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.controller.base.FrontendBaseContoller;
import com.systalk.sys.service.FrontendNewsAreaContentService;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendNewsAreaContentForm;

/**
 * 最新消息內文 Contoller.
 * @author Richard
 * */
@Controller
public class FrontendNewsAreaContentContoller extends FrontendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/** service. */
	@Autowired
	FrontendNewsAreaContentService frontendNewsAreaContentService;

	/**
	 * 最新消息內文 newsAreaContent.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
    @RequestMapping(value = {"/newsAreaContent/{seq}"},  method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView newsAreaContent(Model model, @PathVariable (value = "seq") Integer seq) throws Exception {
		logger.info("newsAreaContent()");

		FrontendNewsAreaContentForm viewForm = frontendNewsAreaContentService.getNewsAreaContentForm(seq);

		model.addAttribute("viewForm", viewForm);

		return new ModelAndView("/newsAreaContent");
	}
}
