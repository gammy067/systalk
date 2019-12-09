package com.systalk.sys.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.systalk.sys.controller.base.BackendBaseContoller;
import com.systalk.sys.enums.Status;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.web.form.view.bean.BannerSettingBean;

/**
 * 後臺 首頁輪播home 設定Contoller.
 * @author Richard
 * */
@Controller
@RequestMapping("/backend")
public class BackendBannerHomeContoller extends BackendBaseContoller{

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BackendService backendService;
	
	@Autowired
	private BackendSettingService backendSettingService;

	
	/**
	 *  首頁輪播設定 首頁.
	 *
	 * @param request the request
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@GetMapping(value = { "/bannerHome" })
	public ModelAndView bannerHome(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/bannerHome");
		List<BannerSettingBean> bannerSettingBeanList = backendService.queryBannerSetting();

		// 輪播圖清單
		modelAndView.addObject("bannerSettingBeanList", bannerSettingBeanList);
		
		
		// 輪播圖上架個數(上架10個不允許新增)
		int effBannerCount = bannerSettingBeanList.stream().filter(b -> StringUtils.equals(b.getStatus(), Status.EFFECTIVE.getCode())).collect(Collectors.toList()).size();
		int maxcount = CommonUtil.getBannerMaxCount();
		if(effBannerCount == maxcount) {
			// 輪播圖清單
			modelAndView.addObject("validateMessage", messageUtil.getMessage("validate.max.effect" , new String[] {String.valueOf(maxcount)}));
		}
		modelAndView.addObject("effBannerCount", effBannerCount);

		return modelAndView;
	}


   /**
    *  輪播圖片後臺管理 刪除.
   *
   * @param model the model
   * @param request the request
   * @return the model and view
   * @throws Exception the exception
   */
  @RequestMapping(value = {"/doDeleteBannerAction"},  method = {RequestMethod.POST})
	public @ResponseBody String doDeleteBannerAction(HttpServletRequest request,
			Model model,
			@RequestParam("seq") Integer seq) throws Exception {

	  backendSettingService.doDeleteBannerAction(seq);

	  return getAjaxSuccessJson();
	}
}
