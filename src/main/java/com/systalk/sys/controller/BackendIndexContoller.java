package com.systalk.sys.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.auth.model.CustomUserDetails;
import com.systalk.sys.controller.base.BackendBaseContoller;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.util.SessionUtil;
import com.systalk.sys.web.form.view.bean.BannerSettingBean;

/**
 * 後臺首頁Contoller.
 * @author Richard
 * 2019.06.06
 * */
@Controller
@RequestMapping("/backend")
public class BackendIndexContoller extends BackendBaseContoller{

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SessionRegistry sessionRegistry;

	@GetMapping(value = {"/index"})
	public ModelAndView login(Model model, Authentication authentication, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/index");

		//List<BannerSettingBean> bannerSettingBeanList = backendService.queryBannerSetting();

		//modelAndView.addObject("bannerSettingBeanList", bannerSettingBeanList);
		return modelAndView;
	}
	
   /**
     *  前往 首頁輪播 首頁.
    *
    * @param model the model
    * @param request the request
    * @return the model and view
    * @throws Exception the exception
    */
   @RequestMapping(value = {"/doGoToBannerHomeAction"},  method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView doGoToBannerHomeAction(Model model, HttpServletRequest request) throws Exception {
		return new ModelAndView("redirect:/backend/bannerHome");
	}


    /**
      * 前往 新聞專區 首頁.
     *
     * @param model the model
     * @param request the request
     * @return the model and view
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/doGoToEditNewsAreaAction"},  method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView doGoToEditNewsAreaAction(Model model, HttpServletRequest request) throws Exception {
		return new ModelAndView("redirect:/backend/newsAreaHome");
	}

    /**
     * 前往 行銷影片首頁.
    *
    * @param model the model
    * @param request the request
    * @return the model and view
    * @throws Exception the exception
    */
   @RequestMapping(value = {"/doGoToEditVideoAction"},  method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView doGoToEditVideoAction(Model model, HttpServletRequest request) throws Exception {
		return new ModelAndView("redirect:/backend/videoHome");
	}
   
   /**
    * 前往 品牌牆首頁.
   *
   * @param model the model
   * @param request the request
   * @return the model and view
   * @throws Exception the exception
   */
  @RequestMapping(value = {"/doGoToLogoWallAction"},  method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView doGoToLogoWallAction(Model model, HttpServletRequest request) throws Exception {
		return new ModelAndView("redirect:/backend/logoWallHome");
	}
  
  /**
   * 前往 成功案例首頁.
  *
  * @param model the model
  * @param request the request
  * @return the model and view
  * @throws Exception the exception
  */
  @RequestMapping(value = {"/doGoToSuccessCaseAction"},  method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView doGoToSuccessCaseAction(Model model, HttpServletRequest request) throws Exception {
		return new ModelAndView("redirect:/backend/logoWallHome");
	}

}
