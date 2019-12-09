package com.systalk.sys.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.controller.base.BackendBaseContoller;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.web.form.view.bean.NewsAreaBean;
import com.systalk.sys.web.form.view.bean.NewsAreaTypeBean;
import com.systalk.sys.web.form.view.vo.news.NewsTypeReqObject;

/**
 * 後臺新聞專區首頁 設定Contoller.
 * @author Richard
 * */
@Controller
@RequestMapping("/backend")
public class BackendNewsAreaHomeContoller extends BackendBaseContoller{

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
	@GetMapping(value = { "/newsAreaHome" })
	public ModelAndView newsAreaHome(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/newsAreaHome");
		// 最新消息項目
		List<NewsAreaBean> newsAreaBeanList = backendService.queryNewsArea();
		
		// 最新消息分類
		Map<Locale, List<NewsAreaTypeBean>> localeNewsTypeMap = backendService.queryNewsAreaType();

		modelAndView.addObject("newsAreaBeanList", newsAreaBeanList);
		
		// 中文分類
		modelAndView.addObject("newsAreaTypeBeanList", localeNewsTypeMap.get(Locale.TAIWAN));
		// 中英文分類
		modelAndView.addObject("newsAreaTypeBeanListEn", localeNewsTypeMap.get(Locale.US));
		return modelAndView;
	}
	

	/**
	 *  最新消息分類 後臺管理 儲存.
	 *
	 * @param model the model
	 * @param session the session
	 * @param request the request
	 * @param seqSortList the seq sort list
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = {"/doSaveNewsAreaTypeAction"},method=RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	public String doSaveNewsAreaTypeAction(Model model,
			@RequestBody NewsTypeReqObject newsTypeReqObject) throws Exception{
		
		String rtnJsonStr = backendSettingService.doNewsAreaTypeSaveAction(newsTypeReqObject.getNewsTypeVoList(), newsTypeReqObject.getDeleteTypeList(), newsTypeReqObject.getLocale());

		return rtnJsonStr;
	}

   /**
    *  新聞專區 後臺管理 刪除.
   *
   * @param model the model
   * @param request the request
   * @return the model and view
   * @throws Exception the exception
   */
  @RequestMapping(value = {"/doDeleteNewsAreaAction"},  method = {RequestMethod.POST})
	public @ResponseBody String doDeleteNewsAreaAction(HttpServletRequest request,
			Model model,
			@RequestParam("seq") Integer seq) throws Exception {

	  backendSettingService.doDeleteNewsAreaAction(seq);

	  return getAjaxSuccessJson();
	}
}
