package com.systalk.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.controller.base.BackendBaseContoller;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.web.form.view.bean.SuccessCaseBean;

/**
 * 後臺 成功案例home 設定Contoller.
 * @author Richard
 * */
@Controller
@RequestMapping("/backend")
public class BackendSuccessCaseHomeContoller extends BackendBaseContoller{

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BackendService backendService;
	
	@Autowired
	private BackendSettingService backendSettingService;
	
	/**
	 *  成功案例設定 首頁.
	 *
	 * @param request the request
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@GetMapping(value = { "/successCaseHome" })
	public ModelAndView successCaseHome(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/successCaseHome");
		
		List<SuccessCaseBean> successCaseBeanList = backendService.querySuccessCase();
		modelAndView.addObject("successCaseBeanList", successCaseBeanList);
		
		return modelAndView;
	}


   /**
    *  成功案例後臺管理 刪除.
   *
   * @param model the model
   * @param request the request
   * @return the model and view
   * @throws Exception the exception
   */
  @RequestMapping(value = {"/doDeleteSuccessCaseAction"},  method = {RequestMethod.POST})
	public @ResponseBody String doDeleteBannerAction(HttpServletRequest request,
			Model model,
			@RequestParam("seq") Integer seq) throws Exception {

	  backendSettingService.doDeleteSuccessCaseAction(seq);
	  
	  return getAjaxSuccessJson();
	}
}
