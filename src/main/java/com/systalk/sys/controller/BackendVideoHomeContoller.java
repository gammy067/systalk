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

import com.systalk.sys.controller.base.BackendBaseContoller;
import com.systalk.sys.enums.Status;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.web.form.view.bean.VideoSettingBean;

/**
 * 後臺行銷影片首頁 設定Contoller.
 * @author Richard
 * */
@Controller
@RequestMapping("/backend")
public class BackendVideoHomeContoller extends BackendBaseContoller{

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BackendService backendService;
	
	@Autowired
	private BackendSettingService backendSettingService;
	
	/**
	 *  行銷影片設定 首頁.
	 *
	 * @param request the request
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@GetMapping(value = { "/videoHome" })
	public ModelAndView videoHome(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/videoHome");
		List<VideoSettingBean> videoSettingBeanList = backendService.queryVideoSetting();
		
		// TODO 現行改為依據最新上架的播放, 不用限制上架數
		// 行銷影片上架個數(上架1個不允許新增)
		int effVideoCount = videoSettingBeanList.stream().filter(v -> StringUtils.equals(v.getStatus(), Status.EFFECTIVE.getCode())).collect(Collectors.toList()).size();
		int maxcount = CommonUtil.getVideoMaxCount();
		if(effVideoCount == maxcount) {
			modelAndView.addObject("validateMessage", messageUtil.getMessage("validate.max.effect" , new String[] {String.valueOf(maxcount)}));
		}
		
		modelAndView.addObject("effVideoCount", effVideoCount);

		modelAndView.addObject("videoSettingBeanList", videoSettingBeanList);
		return modelAndView;
	}

   /**
    *  行銷影片後臺管理 刪除.
   *
   * @param model the model
   * @param request the request
   * @return the model and view
   * @throws Exception the exception
   */
  @RequestMapping(value = {"/doDeleteVideoAction"},  method = {RequestMethod.POST})
	public @ResponseBody String doDeleteVideoAction(HttpServletRequest request,
			Model model,
			@RequestParam("seq") Integer seq) throws Exception {

	  backendSettingService.doDeleteVideoAction(seq);

	  return getAjaxSuccessJson();
	}
}
